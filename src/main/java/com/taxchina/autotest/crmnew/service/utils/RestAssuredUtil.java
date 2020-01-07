package com.taxchina.autotest.crmnew.service.utils;

import java.util.Map;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class RestAssuredUtil {
//    private final static int MAX_SOCKET_TIMEOUT = 1;
//    private final static int MAX_CONNECT_TIMEOUT = 1;
//    private final static int MAX_CONNECTION_REQUEST_TIMEOUT = 1;
//    private static RequestConfig requestConfig = null;
//    static {
//        requestConfig = RequestConfig.custom().setSocketTimeout(MAX_SOCKET_TIMEOUT).setConnectTimeout(MAX_CONNECT_TIMEOUT)
//                .setConnectionRequestTimeout(MAX_CONNECTION_REQUEST_TIMEOUT).build();
//
//        config = config().httpClient(httpClientConfig().httpClientFactory(() -> HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build()));
//    }

    public static Response getRequestWithHeaders(String url,Map<String,String> headers){
        Response response = given()//.log().all()
                    .relaxedHTTPSValidation()
                    .headers(headers)
                .then()//.log().all()
                    .statusCode(200)
                .when()
                    .get(url);
        return response;
    }

    //POST请求，设置头信息，String参数
    public static Response postRequestWithHeadersAndStringParameters(String url, String param, Map<String,String> headers){
        Response response = given()//.log().all()
//                .config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                    .relaxedHTTPSValidation()
                    .headers(headers)
                    .body(param)
                .then()//.log().all()
                    .statusCode(200)
                .when()
                    .post(url);
        return response;
    }

    //POST请求，设置头信息，form-data格式
    public static Response postRequestWithHeadersAndMapParameters(String url,Map<String,String> headers, Map<String,String> entity){
        Response response = given()//.log().all()
//                .config(RestAssuredConfig.config().encoderConfig(encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                    .relaxedHTTPSValidation()
                    .headers(headers)
                    .formParams(entity)
                .then()//.log().all()
                    .statusCode(200)
                .when()
                    .post(url);
        return response;
    }


}


