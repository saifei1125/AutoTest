package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.dao.entity.LoginCase;
import com.taxchina.autotest.crmnew.dao.entity.LoginCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.LoginCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.LoginCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.UserLogin;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import io.restassured.response.Response;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.getEntity;
@Service
public class LoginImpl {
    @Autowired
    private LoginCaseMapper loginCaseMapper;
    @Autowired
    private LoginCaseResMapper loginCaseResMapper;

    //插入登录用例
    public LoginCase insertLoginCase(LoginCase loginCase){
        loginCaseMapper.insertLoginCase(loginCase);
        Log.info("新增登录用例id:"+loginCase.getCaseId());
        return loginCase;
    }
    public LoginCaseRes runLoginCase(int caseId){
        //获取登录用例
        LoginCase loginCase = loginCaseMapper.getLoginCaseById(caseId);
        Log.info("登录用例编号："+loginCase.getCaseId()+"  登录名："+loginCase.getLoginName()+"  密码："+loginCase.getLoginPassword()+"  预期结果："+loginCase.getLoginExpect());
        User user = new User();
        user.setLoginName(loginCase.getLoginName());
        user.setPassword(loginCase.getLoginPassword());
        UserLogin userLogin = new UserLogin();
        CloseableHttpResponse httpResponse = userLogin.userLogin1(user);
        String res = getEntity(httpResponse,"utf-8");
        Log.info("登录结果："+res);
        String sessionId = null;
        if(res!=null) {
            //回写登录结果
            Header[] header = HttpClientUtil.getHeader(httpResponse, "Set-Cookie");
            Map<String, String> newHeaders = new HashMap<>();
            for (int i = 0; i < header.length; i++) {
                if (header[i].getValue().contains("tx.session.id")) {//JSESSIONID
                    sessionId = header[i].getValue().substring(0, header[i].getValue().indexOf("Path"));
//               Log.info(sessionId);
                    newHeaders.put("Content-Type", "application/x-www-form-urlencoded");
                    newHeaders.put("Cookie", sessionId);
                    user.setHeaders(newHeaders);
                }
            }
        }
        try {
            httpResponse.close();
        } catch (IOException e) {
//            e.printStackTrace();
            Log.error("出错啦！！！错误信息："+e.getMessage());
        }

        LoginCaseRes loginCaseRes = new LoginCaseRes();
        loginCaseRes.setCaseId(loginCase.getCaseId());
        loginCaseRes.setResult(res);
        loginCaseRes.setCookie(sessionId);
        int  insertRes = loginCaseResMapper.setLoginCaseRes(loginCaseRes);
        if(insertRes == 1){
            Log.info("登录结果保存成功");
        }else {
            Log.info("登录结果保存失败");
        }

        return loginCaseRes;
    }

    public LoginCaseRes runLoginCaseNew(int caseId){
        //获取登录用例
        LoginCase loginCase = loginCaseMapper.getLoginCaseById(caseId);
        Log.info("登录用例编号："+loginCase.getCaseId()+"  登录名："+loginCase.getLoginName()+"  密码："+loginCase.getLoginPassword()+"  预期结果："+loginCase.getLoginExpect());
        User user = new User();
        user.setLoginName(loginCase.getLoginName());
        user.setPassword(loginCase.getLoginPassword());
        UserLogin userLogin = new UserLogin();
        Response response =userLogin.userLoginNew(user);
        String res = response.getBody().print();
        String sessionId = null;
        Map<String,String> headers1 = response.getCookies();
        Set<String> set = headers1.keySet();
        for(String key : set){
            if(key.equals("tx.session.id")){
                sessionId = headers1.get(key);
                user.setHeaders(headers1);
            }
        }
        LoginCaseRes loginCaseRes = new LoginCaseRes();
        loginCaseRes.setCaseId(loginCase.getCaseId());
        loginCaseRes.setResult(res);
        loginCaseRes.setCookie(sessionId);
        int  insertRes = loginCaseResMapper.setLoginCaseRes(loginCaseRes);
        if(insertRes == 1){
            Log.info("登录结果保存成功");
        }else {
            Log.info("登录结果保存失败");
        }
        return loginCaseRes;
    }

    public User loginNew(int caseId){
        LoginCase loginCase = loginCaseMapper.getLoginCaseById(caseId);
        Log.info("登录用例编号："+loginCase.getCaseId()+"  登录名："+loginCase.getLoginName()+"  密码："+loginCase.getLoginPassword()+"  预期结果："+loginCase.getLoginExpect());
        User user = new User();
        user.setLoginName(loginCase.getLoginName());
        user.setPassword(loginCase.getLoginPassword());
        UserLogin userLogin = new UserLogin();
        Response response =userLogin.userLoginNew(user);
        String res = response.getBody().print();
        String sessionId = null;
        Map<String,String> headers1 = response.getCookies();
        Set<String> set = headers1.keySet();
        for(String key : set){
            if(key.equals("tx.session.id")){
                sessionId = headers1.get(key);
                Map<String, String> newHeaders = new HashMap<>();
                newHeaders.put("Content-Type", "application/x-www-form-urlencoded");
                newHeaders.put("Cookie", "tx.session.id="+sessionId);//tx.session.id  JSESSIONID
                user.setHeaders(newHeaders);
            }
        }
        LoginCaseRes loginCaseRes = new LoginCaseRes();
        loginCaseRes.setCaseId(loginCase.getCaseId());
        loginCaseRes.setResult(res);
        loginCaseRes.setCookie(sessionId);
        int  insertRes = loginCaseResMapper.setLoginCaseRes(loginCaseRes);
        if(insertRes == 1){
            Log.info("登录结果保存成功");
        }else {
            Log.info("登录结果保存失败");
        }
        Assert.assertEquals(loginCaseRes.getResult(),loginCase.getLoginExpect());
        return user;
    }


    //根据id运行登录用例
    public User login(int caseId){
        //获取登录用例
        LoginCase loginCase = loginCaseMapper.getLoginCaseById(caseId);
        Log.info("登录用例编号："+loginCase.getCaseId()+"  登录名："+loginCase.getLoginName()+"  密码："+loginCase.getLoginPassword()+"  预期结果："+loginCase.getLoginExpect());
        User user = new User();
        user.setLoginName(loginCase.getLoginName());
        user.setPassword(loginCase.getLoginPassword());
        UserLogin userLogin = new UserLogin();
        CloseableHttpResponse httpResponse = userLogin.userLogin1(user);
        String res = getEntity(httpResponse,"utf-8");
        Log.info("登录结果："+res);
        String sessionId = null;
        if(res!=null) {
            Header[] header = httpResponse.getHeaders("Set-Cookie");
            Map<String, String> newHeaders = new HashMap<>();
            for (int i = 0; i < header.length; i++) {
                if (header[i].getValue().contains("tx.session.id")) {
                    sessionId = header[i].getValue().substring(0, header[i].getValue().indexOf("Path"));
//                Log.info("sessionId:----"+sessionId);
                    newHeaders.put("Content-Type", "application/x-www-form-urlencoded");
                    newHeaders.put("Cookie", sessionId);
                    user.setHeaders(newHeaders);
                }
            }
        }
        try {
            httpResponse.close();
        } catch (IOException e) {
            Log.error("出错啦！！！错误信息："+e.getMessage());
        }

        LoginCaseRes loginCaseRes = new LoginCaseRes();
        loginCaseRes.setCaseId(loginCase.getCaseId());
        loginCaseRes.setResult(res);
        loginCaseRes.setCookie(sessionId);
        int  insertRes = loginCaseResMapper.setLoginCaseRes(loginCaseRes);
        if(insertRes == 1){
            Log.info("登录结果保存成功");
        }else {
            Log.info("登录结果保存失败");
        }
        Assert.assertEquals(loginCaseRes.getResult(),loginCase.getLoginExpect());
        return user;
    }


    public User login(String loginname,String password) {
        User user = new User();
        user.setLoginName(loginname);
        user.setPassword(password);
        UserLogin userLogin = new UserLogin();
        userLogin.userLogin(user);

        return user;
    }
}
