package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.dao.entity.LoginCase;
import com.taxchina.autotest.crmnew.dao.entity.LoginCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.LoginCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.LoginCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.AppUserLogin;
import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import io.restassured.path.json.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppLoginImpl {
    @Autowired
    private LoginCaseMapper loginCaseMapper;
    @Autowired
    private LoginCaseResMapper loginCaseResMapper;

    public User appLogin(User user){
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);

        //登录
        AppUserLogin appUserLogin = new AppUserLogin();
        appUserLogin.appUserLogin(user);
        return user;
    }

    public LoginCaseRes runAppLoginCase(int caseId){
        LoginCase loginCase = loginCaseMapper.getLoginCaseById(caseId);
        Log.info("登录用例编号："+loginCase.getCaseId()+"  手机号："+loginCase.getPhoneNumber()+"  密码："+loginCase.getLoginPassword()+"  预期结果："+loginCase.getLoginExpect());
        AppUserLogin appUserLogin = new AppUserLogin();
        String res = appUserLogin.appLoginByCase(loginCase);
        Log.info(res);
        JsonPath jsonPath = new JsonPath(res);
        String token = jsonPath.getString("data.token");
        LoginCaseRes loginCaseRes = new LoginCaseRes();
        loginCaseRes.setResult(res);
        loginCaseRes.setCaseId(loginCase.getCaseId());
        loginCaseRes.setCookie(token);
        int  insertRes = loginCaseResMapper.setLoginCaseRes(loginCaseRes);
        if(insertRes == 1){
            Log.info("登录结果保存成功");
        }else {
            Log.info("登录结果保存失败");
        }
        return loginCaseRes;
    }
}
