package com.taxchina.autotest.crmnew.service.utils;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpClientUtil {
    //GET请求，无参
    public static String doGet(String url,String charset){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet= null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            httpClient = new CreateSSLClientDefault().createSSLClientDefault();
            httpGet = new HttpGet(url);

            response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
            return result;
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.error("出错啦！！！错误信息："+ex.getMessage());
        }finally {
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //GET请求，带参
    public static String doGet(String url,List<NameValuePair> list,Map<String,String> map,String charset){
        CloseableHttpClient httpClient = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = new CreateSSLClientDefault().createSSLClientDefault();
            String str = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
            HttpGet httpGet = new HttpGet(url+"?"+str);

//            System.out.println(url+"?"+str);
//            Set<String> s = map.keySet();
//            for(String keys: s){
//                String v = map.get(keys);
//                httpGet.addHeader(keys,v);
//            }
            map.forEach((k,v)->httpGet.addHeader(k,v));
            response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
            return result;
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.error("出错啦！！！错误信息："+ex.getMessage());
        }finally {
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //GET请求，带头信息
    public static String doGet(String url,Map<String,String> headers,String charset){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            httpClient = new CreateSSLClientDefault().createSSLClientDefault();
            HttpGet httpGet = new HttpGet(url);

//            Set<String> set = headers.keySet();
//            for(String header : set ){
//                httpGet.addHeader(header, headers.get(header));
//            }
            headers.forEach((k,v)->httpGet.addHeader(k,v));

            response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
            return result;
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.error("出错啦！！！错误信息："+ex.getMessage());
        }finally {
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    //POST请求，参数json格式
    public static String doPost(String url, String jsonstr, String charset) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            httpClient = new CreateSSLClientDefault().createSSLClientDefault();
            httpPost = new HttpPost(url);

            httpPost.addHeader("Content-Type", "application/json");
            StringEntity se = new StringEntity(jsonstr);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
            return result;
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.error("出错啦！！！错误信息："+ex.getMessage());
        }finally {
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //POST请求，设置头信息,json格式
    public static CloseableHttpResponse doPost1(String url, String param, Map<String,String> headers, String charset) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            httpClient = new CreateSSLClientDefault().createSSLClientDefault();
            HttpPost httpPost = new HttpPost(url);

            StringEntity se = new StringEntity(param, Charset.forName(charset));
            httpPost.setEntity(se);

//            Set<String> get = headers.keySet();
//            for(String header : get ){
//                httpPost.addHeader(header, headers.get(header));
//            }
            headers.forEach((k,v)->httpPost.addHeader(k,v));
            //执行请求
            response = httpClient.execute(httpPost);
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.error("出错啦！！！错误信息："+ex.getMessage());
        }
        return response;


    }
    //POST请求，设置头信息,json格式
    public static String doPost(String url, String param, Map<String,String> headers, String charset) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            httpClient = new CreateSSLClientDefault().createSSLClientDefault();
            HttpPost httpPost = new HttpPost(url);

            StringEntity se = new StringEntity(param, Charset.forName(charset));
            httpPost.setEntity(se);

//            Set<String> get = headers.keySet();
//            for(String header : get ){
//                httpPost.addHeader(header, headers.get(header));
//            }
            headers.forEach((k,v)->httpPost.addHeader(k,v));
            //执行请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), charset);
            }
            return result;
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.error("出错啦！！！错误信息："+ex.getMessage());
        }finally {
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //POST请求，设置头信息，form-data格式
    public static CloseableHttpResponse doPost1(String url, Map<String,String> headers,Map<String,String> entity, String charset) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        httpClient = new CreateSSLClientDefault().createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);

//        Set<String> get = headers.keySet();
//        for(String header : get ){
//            httpPost.addHeader(header, headers.get(header));
//        }
        headers.forEach((k,v)->httpPost.addHeader(k,v));
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        Set<String> set = entity.keySet();
        for(String s : set) {
            StringBody sb = new StringBody(entity.get(s),ContentType.TEXT_PLAIN.withCharset(charset));
            builder.addPart(s,sb);
        }

        HttpEntity httpEntity = builder.build();
        httpPost.setEntity(httpEntity);
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("出错啦！！！错误信息："+e.getMessage());
        }
        return response;
    }
    //POST请求，设置头信息，form-data格式
    public static String doPost(String url, Map<String,String> headers,Map<String,String> entity, String charset) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            httpClient = new CreateSSLClientDefault().createSSLClientDefault();
            HttpPost httpPost = new HttpPost(url);

//            Set<String> get = headers.keySet();
//            for(String header : get ){
//                httpPost.addHeader(header, headers.get(header));
//            }
            headers.forEach((k,v)->httpPost.addHeader(k,v));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            builder.setCharset(java.nio.charset.Charset.forName(charset));
//            builder.setContentType(ContentType.DEFAULT_BINARY);
//            builder.setBoundary("=---------------------------168279961491");
//            StringBody c = new StringBody(charset,ContentType.TEXT_PLAIN);
//            builder.addPart("collectionNo",c);
            Set<String> set = entity.keySet();
            for(String s : set) {
//                System.out.println(s +"   "+ entity.get(s));
                StringBody sb = new StringBody(entity.get(s),ContentType.TEXT_PLAIN.withCharset(charset));
                builder.addPart(s,sb);
            }

            HttpEntity httpEntity = builder.build();
            httpPost.setEntity(httpEntity);
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode()==200) {
                result = EntityUtils.toString(response.getEntity(), charset);
            }
            return result;
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.error("出错啦！！！错误信息："+ex.getMessage());
        }finally {
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //POST请求，参数form表单格式
    public static String doPost(String url, Map<String,String> map, String charset) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            httpClient =new CreateSSLClientDefault().createSSLClientDefault();
            HttpPost httpPost = new HttpPost(url);
//            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            //设置参数
            List<NameValuePair> list = new ArrayList<>();
//            Iterator iterator = map.entrySet().iterator();
//            while(iterator.hasNext()){
//                Entry<String,String> elem = (Entry<String, String>) iterator.next();
//                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
//            }
            map.forEach((k,v)->list.add(new BasicNameValuePair(k,v)));
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }

            response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
            return result;
        } catch (Exception ex) {
//            ex.printStackTrace();
            Log.error("出错啦！！！错误信息："+ex.getMessage());
        }finally {
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    //响应中获取响应体
    public static String getEntity (CloseableHttpResponse response , String charset) {
        String result = null;
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                try {
                    result = EntityUtils.toString(resEntity, charset);
                    EntityUtils.consume(resEntity);
                } catch (IOException e) {
//                    e.printStackTrace();
                    Log.error("出错啦！！！错误信息："+e.getMessage());
                }
            }
        }
        return result;
    }


    //响应中获取响应头
    public static Header[] getHeader (CloseableHttpResponse response , String headername) {
        // 获取JSESSIONID
        Header[] header = response.getHeaders(headername);
        return header;
    }


}
