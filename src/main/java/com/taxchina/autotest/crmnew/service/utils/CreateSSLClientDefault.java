package com.taxchina.autotest.crmnew.service.utils;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class CreateSSLClientDefault {
    private final static int MAX_SOCKET_TIMEOUT = 3000;
    private final static int MAX_CONNECT_TIMEOUT = 10000;
    private final static int MAX_CONNECTION_REQUEST_TIMEOUT = 10000;

    private static RequestConfig requestConfig = null;
    private static ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy = null;
    private static SSLConnectionSocketFactory sslsf = null;
    private static  PoolingHttpClientConnectionManager pool = null;
    private static  HttpRequestRetryHandler httpRequestRetryHandler = null;
    private static SSLContext sslContext = null;

    static {
        // 根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(MAX_SOCKET_TIMEOUT).setConnectTimeout(MAX_CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(MAX_CONNECTION_REQUEST_TIMEOUT).build();

        httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int retryTimes, HttpContext context) {
                Log.error("retryRequest:"+retryTimes);
                if (retryTimes >= 3) {
                    return false;
                }
                if (exception instanceof ConnectTimeoutException || exception instanceof NoHttpResponseException || exception instanceof SocketTimeoutException
                        || !(exception instanceof UnknownHostException) || !(exception instanceof InterruptedIOException) || !(exception instanceof SSLException)
                        || !(exception instanceof SSLHandshakeException)) {
                    return true;
                }else {
                    Log.error("未记录的请求异常：" + exception.getClass());
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    // 如果请求被认为是幂等的，那么就重试。即重复执行不影响程序其他效果的
                    return true;
                }
                return false;
            }
        };
        serviceUnavailableRetryStrategy = new ServiceUnavailableRetryStrategy() {
            /**
             * retry逻辑
             */
            @Override
            public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
                Log.error("retryRequest:"+executionCount);
                if (executionCount <= 3)
                    return true;
                else
                    return false;
            }

            /**
             * retry间隔时间
             */
            @Override
            public long getRetryInterval() {
                Log.error("getRetryInterval");
                return 3000;
            }
        };

    }


    public static CloseableHttpClient createSSLClientDefault() {

        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                @Override
                public boolean isTrusted(X509Certificate[] xcs, String string) {
                    return true;
                }
            }).build();

            sslsf = new SSLConnectionSocketFactory(sslContext);

            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslsf).build();

            // 初始化连接管理器
            pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 设置连接池的最大连接数
            pool.setMaxTotal(200);
            // 设置每个路由上的默认连接个数
            pool.setDefaultMaxPerRoute(20);


            CloseableHttpClient httpClient = HttpClients.custom()
                    // 设置连接池管理
                    .setConnectionManager(pool)
                    // 设置请求配置
                    .setDefaultRequestConfig(requestConfig)
                    // 设置重试次数
                    .setRetryHandler(httpRequestRetryHandler)
//                    .setServiceUnavailableRetryStrategy(serviceUnavailableRetryStrategy)
                    .build();
            return httpClient;

        } catch (KeyStoreException ex) {
            Log.error(ex);
        } catch (NoSuchAlgorithmException ex) {
            Log.error(ex);
        } catch (KeyManagementException ex) {
            Log.error(ex);
        }
        return HttpClients.createDefault();
    }
}
