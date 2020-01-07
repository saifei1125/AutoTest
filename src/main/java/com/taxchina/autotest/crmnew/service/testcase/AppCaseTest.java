package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.service.entity.Course;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.impl.AppLoginImpl;
import com.taxchina.autotest.crmnew.service.impl.AppSignInImpl;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.*;

@SpringBootTest
public class AppCaseTest {
    @Test
    public void appTestCase(){
        User user = new User();
        user.setPhonenumber("15101103000");
        user.setPassword("m1111111");
        AppLoginImpl appLoginImpl = new AppLoginImpl();
        appLoginImpl.appLogin(user);
        Course course = new Course();
        course.setCourseName("黑龙江--自动化测试课程");
        AppSignInImpl appSignInImpl = new AppSignInImpl();
        appSignInImpl.signIn(user,course,"1613410");
    }









    @BeforeTest
    public void beforeTest(){
        Log.startTestCase(getClass().getName());
    }
    @AfterTest
    public void afterTest(){
        Log.endTestCase(getClass().getName());
    }
}
