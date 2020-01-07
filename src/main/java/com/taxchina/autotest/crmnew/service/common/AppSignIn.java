package com.taxchina.autotest.crmnew.service.common;

import com.taxchina.autotest.crmnew.service.entity.SignInPersonList;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;

import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;


public class AppSignIn {
    public void appSignIn(User user, SignInPersonList signInPersonList){
        String url = TEST_DOMAIN+"/api/course/singIn?cid="+signInPersonList.getCid();
        Log.info(url);
        Map<String,String> headers = user.getHeaders();
        headers.put("Content-Type","application/json; charset=UTF-8");
        String res = HttpClientUtil.doPost(url,"",headers,"utf-8");
        Log.info("签到接口返回结果："+res);
    }
}
