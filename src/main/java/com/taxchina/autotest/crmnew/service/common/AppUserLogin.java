package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.LoginCase;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;


public class AppUserLogin {
    public User appUserLogin(User user){
        String url = TEST_DOMAIN+"/api/user/sysLogin";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile",user.getPhonenumber());
        jsonObject.put("password",user.getPassword());
        Map<String,String> headers = new HashMap();
        headers.put("Content-Type","application/json; charset=UTF-8");
        String response = HttpClientUtil.doPost(url, jsonObject.toJSONString(), headers, "utf-8");
        JSONObject jsonRes = JSON.parseObject(response);
        String data = jsonRes.getString("data");
        JSONObject jsonData = JSON.parseObject(data);
        String token = jsonData.getString("token");
//        Log.info("token:"+token);
        headers.put("Authorization",token);
        user.setHeaders(headers);
        return user;
    }

    public String appLoginByCase(LoginCase loginCase){
        String url = TEST_DOMAIN+"/api/user/sysLogin";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile",loginCase.getPhoneNumber());
        jsonObject.put("password",loginCase.getLoginPassword());
        Map<String,String> headers = new HashMap();
        headers.put("Content-Type","application/json; charset=UTF-8");
        String response = HttpClientUtil.doPost(url, jsonObject.toJSONString(), headers, "utf-8");
        return response;
    }
}
