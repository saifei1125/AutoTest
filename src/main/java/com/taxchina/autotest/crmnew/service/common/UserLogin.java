package com.taxchina.autotest.crmnew.service.common;

import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import io.restassured.response.Response;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;
import static com.taxchina.autotest.crmnew.service.utils.RestAssuredUtil.postRequestWithHeadersAndStringParameters;

public class UserLogin {
    //传入用户信息，返回头信息
    public User userLogin(User user){
        String url = TEST_DOMAIN+"/login";
        String loginName = user.getLoginName();
        String password = user.getPassword();
        String param = "username=" + loginName + "&password=" + password + "&rememberMe=false";
//        Log.info(param);
        Map<String,String> headers = new HashMap();
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//        headers.put("Cookie","");
        CloseableHttpResponse response = HttpClientUtil.doPost1(url, param, headers, "utf-8");
        if(response!=null) {
            Map<String, String> newHeaders = new HashMap<>();
//        Header[] header = HttpClientUtil.getHeader(response,"Set-Cookie");
            Header[] header = response.getHeaders("Set-Cookie");
            for (int i = 0; i < header.length; i++) {
                if (header[i].getValue().contains("tx.session.id")) {//JSESSIONID
                    String sessionId = header[i].getValue().substring(0, header[i].getValue().indexOf("Path"));
//                Log.info(sessionId);
                    newHeaders.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                    newHeaders.put("Cookie", sessionId);
                    user.setHeaders(newHeaders);
                }
            }
        }
        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("出错啦！！！错误信息："+e.getMessage());
        }
        return user;
    }

    public Response userLoginNew(User user){
        String url = TEST_DOMAIN+"/login";
        String loginName = user.getLoginName();
        String password = user.getPassword();
        String param = "username=" + loginName + "&password=" + password + "&rememberMe=false";
        Map<String,String> headers = new HashMap();
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        Response response = postRequestWithHeadersAndStringParameters(url,param,headers);
        return response;
    }

    //传入用户信息，返回头信息
    public CloseableHttpResponse userLogin1(User user){
        String url = TEST_DOMAIN+"/login";
        String loginName = user.getLoginName();
        String password = user.getPassword();
        String param = "username=" + loginName + "&password=" + password + "&rememberMe=false";
//        Log.info(param);
        Map<String,String> headers = new HashMap();
        headers.put("Content-Type","application/x-www-form-urlencoded");
//        headers.put("Cookie","");
        CloseableHttpResponse response = HttpClientUtil.doPost1(url, param, headers, "utf-8");
        return response;
    }
}
