package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.entity.*;
import com.taxchina.autotest.crmnew.service.impl.*;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SpringBootTest
public class SignInCaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    AppointmentCoursesImpl appointmentCoursesImpl;
    @Autowired
    private LoginImpl loginImpl;
    @Autowired
    private AddCustImpl addCustImpl;
    @Autowired
    private AddLinkmanImpl addLinkmanImpl;
    @Autowired
    private SignContractImpl signContractImpl;
    @Autowired
    private AddFinanceImpl addFinanceImpl;
    @Autowired
    private RelevanceFinanceImpl relevanceFinanceImpl;
    @Autowired
    private ConfirmPerformanceImpl confirmPerformanceImpl;
    @Autowired
    private AppLoginImpl appLoginImpl;
    @Autowired
    private AppSignInImpl appSignInImpl;

    /**app签到成功的情况*/
    @Test(dataProvider = "userCourseCode")
    public void signInCase1(User user,Course course,String code){
        appSignInImpl.signIn(user,course,code);


    }

    /**app签到码错误的情况*/
    @Test(dataProvider = "userCourse")
    public void signInCase2(User user,Course course){
        appSignInImpl.signIn(user,course,"111111");


    }









    @BeforeTest
    public void beforeTest(){
        Log.startTestCase(getClass().getName());
    }
    @AfterTest
    public void afterTest(){
        Log.endTestCase(getClass().getName());
    }


    @DataProvider(name = "userCourseCode")
    public Object[][] dataProvider(){
        User user = loginImpl.login("xiaoshou","m1111111");
        GetUserInfo getUserInfo = new GetUserInfo();
        User sell = getUserInfo.getUserInfoByLoginName(user);
        Customer customer = addCustImpl.addPersonalCustByCase(sell);
        Log.info("---新加客户id：" + customer.getCustId() + "-----客户名：" + customer.getCustName());
        Assert.assertNotEquals(0,customer.getCustId());
        Lkm lkm = addLinkmanImpl.addLinkmanByCase(sell,customer);
        Log.info("---新加联系人id："+lkm.getLkmId()+"---新加联系人姓名："+lkm.getLkmName()+"---新加联系人手机号："+lkm.getLkmMobile());
        Assert.assertNotEquals(0,lkm.getLkmId());
        //签合同
        Contract contract = signContractImpl.signContract(sell,customer,lkm,"黑龙江测试套餐");
        Log.info("---新签合同id：" + contract.getContractNo());
        Assert.assertNotNull(contract.getContractNo());
        //财务录入回款
        User accountant = loginImpl.login("caiwu","m1111111");
        Finance finance = addFinanceImpl.addFinanceByLoginnameByCase(accountant,sell,null);
        Log.info("---新录入回款编号：" + finance.getCollectionNo() + " 回款id:"+finance.getId());
        Assert.assertNotNull(finance.getCollectionNo());
        //回款绑定合同
        relevanceFinanceImpl.relevanceFinance(sell,contract,finance);
        //总部财务审核业绩
        User cfo = loginImpl.login("zongbucaiwu","m1111111");
        confirmPerformanceImpl.confirmPerformanceByCase(cfo,finance);
        Course course = new Course();
        course.setCourseName("黑龙江1212");
        course.setCourseId("KCBH-201912120841586938");
        ReservationList reservationList = appointmentCoursesImpl.appointmentCourses(sell,customer,course);
        Log.info("预约码："+ reservationList.getInvitationCode());
        appLoginImpl.appLogin(sell);
        return new Object[][]{{sell,course,reservationList.getInvitationCode()}};
    }
    @DataProvider(name = "userCourse")
    public Object[][] dataProvider1(){
        Course course = new Course();
        course.setCourseName("黑龙江1212");
        course.setCourseId("KCBH-201912120841586938");
        User user = loginImpl.login("xiaoshou","m1111111");
        GetUserInfo getUserInfo = new GetUserInfo();
        User sell = getUserInfo.getUserInfoByLoginName(user);
        appLoginImpl.appLogin(sell);
        return new Object[][]{{sell,course}};
    }
}
