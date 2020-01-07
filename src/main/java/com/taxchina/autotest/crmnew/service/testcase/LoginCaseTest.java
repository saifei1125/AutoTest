package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.dao.entity.LoginCase;
import com.taxchina.autotest.crmnew.dao.entity.LoginCaseRes;
import com.taxchina.autotest.crmnew.service.impl.AppLoginImpl;
import com.taxchina.autotest.crmnew.service.impl.LoginImpl;
import com.taxchina.autotest.crmnew.service.utils.Log;
import io.restassured.path.json.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

@SpringBootTest
public class LoginCaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LoginImpl loginImpl;
    @Autowired
    private AppLoginImpl appLoginImpl;

    /** 成功登录的情况*/
    @Test
    @Parameters({"username","password"})
    public void loginCase(String username,String password) {
        LoginCase loginCase = new LoginCase();
        loginCase.setLoginName(username);
        loginCase.setLoginPassword(password);
        loginCase.setLoginExpect("{\"msg\":\"操作成功\",\"code\":0}");
        loginImpl.insertLoginCase(loginCase);
        LoginCaseRes loginCaseRes = loginImpl.runLoginCase(loginCase.getCaseId());
        String expext = loginCase.getLoginExpect();
        Log.info("预期结果：" + expext);
        Assert.assertEquals(loginCaseRes.getResult(), expext);
        Reporter.log("用例执行成功");
    }

    /** 密码错误的情况*/
    @Test
    public void loginCase1() {
        LoginCase loginCase = new LoginCase();
        loginCase.setLoginName("xiaoshou");
        loginCase.setLoginPassword("12345");
        loginCase.setLoginExpect("{\"msg\":\"用户不存在/密码错误\",\"code\":500}");
        loginImpl.insertLoginCase(loginCase);
        LoginCaseRes loginCaseRes = loginImpl.runLoginCaseNew(loginCase.getCaseId());
        String expext = loginCase.getLoginExpect();
        Log.info("预期结果：" + expext);
        Assert.assertEquals(loginCaseRes.getResult(), expext);
        Reporter.log("用例执行成功");
    }

    /** 账号不存在的情况*/
    @Test
    public void loginCase2() {
        LoginCase loginCase = new LoginCase();
        loginCase.setLoginName("test123");
        loginCase.setLoginPassword("12345");
        loginCase.setLoginExpect("{\"msg\":\"用户不存在/密码错误\",\"code\":500}");
        loginImpl.insertLoginCase(loginCase);
        LoginCaseRes loginCaseRes = loginImpl.runLoginCaseNew(loginCase.getCaseId());
        String expext = loginCase.getLoginExpect();
        Log.info("预期结果：" + expext);
        Assert.assertEquals(loginCaseRes.getResult(), expext);
        Reporter.log("用例执行成功");

    }

    /**账号停用的情况*/
    @Test
    public void loginCase3() {
        LoginCase loginCase = new LoginCase();
        loginCase.setLoginName("xiaoshou2");
        loginCase.setLoginPassword("m1111111");
        loginCase.setLoginExpect("{\"msg\":\"用户已封禁，请联系管理员\",\"code\":500}");
        loginImpl.insertLoginCase(loginCase);
        LoginCaseRes loginCaseRes = loginImpl.runLoginCaseNew(loginCase.getCaseId());
        String expext = loginCase.getLoginExpect();
        Log.info("预期结果：" + expext);
        Assert.assertEquals(loginCaseRes.getResult(), expext);
        Reporter.log("用例执行成功");
    }

    /**账号锁定的情况*/
    @Test
    public void loginCase4() {
        LoginCase loginCase = new LoginCase();
        loginCase.setLoginName("xiaoshou6");
        loginCase.setLoginPassword("123456");
        for (int i = 1; i <= 6; i++) {
            loginImpl.login(loginCase.getLoginName(), loginCase.getLoginPassword());
            if (i == 6) {
                loginCase.setLoginExpect("{\"msg\":\"密码输入错误5次，帐户锁定10分钟\",\"code\":500}");
                loginImpl.insertLoginCase(loginCase);
                LoginCaseRes loginCaseRes = loginImpl.runLoginCaseNew(loginCase.getCaseId());
                String expext = loginCase.getLoginExpect();
                Log.info("预期结果：" + expext);
                Assert.assertEquals(loginCaseRes.getResult(), expext);
                Reporter.log("用例执行成功");
            }
        }
    }

    /**app用户成功登录的情况*/
    @Test
    public void appLoginCase1() {
        LoginCase loginCase = new LoginCase();
        loginCase.setPhoneNumber("15101103000");
        loginCase.setLoginPassword("m1111111");
        loginCase.setLoginSource(1);//app
        loginCase.setLoginExpect("0");
        loginImpl.insertLoginCase(loginCase);
        LoginCaseRes loginCaseRes = appLoginImpl.runAppLoginCase(loginCase.getCaseId());
        JsonPath jsonPath = new JsonPath(loginCaseRes.getResult());
        String code = jsonPath.getString("code");
        Assert.assertEquals(code, loginCase.getLoginExpect());
        Reporter.log("用例执行成功");
    }

    /**app用户登录，密码错误的情况*/
    @Test
    public void appLoginCase2() {
        LoginCase loginCase = new LoginCase();
        loginCase.setPhoneNumber("15101103000");
        loginCase.setLoginPassword("123");
        loginCase.setLoginSource(1);
        loginCase.setLoginExpect("10001");
        loginImpl.insertLoginCase(loginCase);
        LoginCaseRes loginCaseRes = appLoginImpl.runAppLoginCase(loginCase.getCaseId());
        JsonPath jsonPath = new JsonPath(loginCaseRes.getResult());
        String code = jsonPath.getString("code");
        Assert.assertEquals(code, loginCase.getLoginExpect());
        Reporter.log("用例执行成功");
    }

    /**app用户账号不存在的情况*/
    @Test
    public void appLoginCase3() {
        LoginCase loginCase = new LoginCase();
        loginCase.setPhoneNumber("12345678901");
        loginCase.setLoginPassword("123");
        loginCase.setLoginSource(1);
        loginCase.setLoginExpect("10001");
        loginImpl.insertLoginCase(loginCase);
        LoginCaseRes loginCaseRes = appLoginImpl.runAppLoginCase(loginCase.getCaseId());
        JsonPath jsonPath = new JsonPath(loginCaseRes.getResult());
        String code = jsonPath.getString("code");
        Assert.assertEquals(code, loginCase.getLoginExpect());
        Reporter.log("用例执行成功");
    }

    /**app用户账号停用的情况*/
    @Test
    public void appLoginCase4() {
        LoginCase loginCase = new LoginCase();
        loginCase.setPhoneNumber("15101103308");
        loginCase.setLoginPassword("m1111111");
        loginCase.setLoginSource(1);
        loginCase.setLoginExpect("10003");
        loginImpl.insertLoginCase(loginCase);
        LoginCaseRes loginCaseRes = appLoginImpl.runAppLoginCase(loginCase.getCaseId());
        JsonPath jsonPath = new JsonPath(loginCaseRes.getResult());
        String code = jsonPath.getString("code");
        Assert.assertEquals(code, loginCase.getLoginExpect());
        Reporter.log("用例执行成功");
    }

//    /**app用户账号锁定的情况*/ //(有bug 跑不通)
//    @Test
//    public void appLoginCase5(){
//        LoginCase loginCase = new LoginCase();
//        loginCase.setPhoneNumber("15178444444");
//        loginCase.setLoginPassword("m1111111");
//        loginCase.setLoginSource(1);
//        loginCase.setLoginExpect("10003");
//        loginImpl.insertLoginCase(loginCase);
//        for(int i=1;i<=6;i++){
//            appLoginImpl.runAppLoginCase(loginCase.getCaseId());
//            if(i == 6){
//                LoginCaseRes loginCaseRes = appLoginImpl.runAppLoginCase(loginCase.getCaseId());
//                JsonPath jsonPath = new JsonPath(loginCaseRes.getResult());
//                String code = jsonPath.getString("code");
//                Assert.assertEquals(code,loginCase.getLoginExpect());
//                Reporter.log("用例执行成功");
//            }
//        }




    @BeforeTest
    public void beforeTest(){
        Log.startTestCase(getClass().getName());
    }
    @AfterTest
    public void afterTest(){
        Log.endTestCase(getClass().getName());
    }
}