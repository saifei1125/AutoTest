package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.dao.entity.AppointmentCoursesCase;
import com.taxchina.autotest.crmnew.dao.entity.AppointmentCoursesCaseRes;
import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.entity.*;
import com.taxchina.autotest.crmnew.service.impl.*;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

@SpringBootTest
public class AppointmentCoursesCaseTest extends AbstractTestNGSpringContextTests {
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
    private RemoveReservationImpl removeReservationImpl;

    /**会员成功约课的情况*/
    @Test(dataProvider = "userCust")
    public void appointmentCoursesCase1(User user,Customer customer){
        AppointmentCoursesCase appointmentCoursesCase = new AppointmentCoursesCase();
        appointmentCoursesCase.setCourseName("黑龙江1202");
        appointmentCoursesCase.setCourseId("KCBH-201912021007329009");
        appointmentCoursesCase.setCompanyId(customer.getCustId());
        appointmentCoursesCase.setCompanyName(customer.getCustName());
        appointmentCoursesCase.setLkmName(customer.getLkms().get(0).getLkmName());
        appointmentCoursesCase.setLkmMobile(customer.getLkms().get(0).getLkmMobile());
        appointmentCoursesCase.setAppointmentCoursesExpect("{\"msg\":\"操作成功\",\"code\":0}");
        //查询客户约课前剩余可用次数
        int num = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),1);
        Log.info("客户可用约课次数："+num);
        //约课
        appointmentCoursesImpl.insertAppointmentCoursesCase(appointmentCoursesCase);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase.getCaseId());
        String res = appointmentCoursesCaseRes.getResult();
        String expext = appointmentCoursesCase.getAppointmentCoursesExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,res);
        //查询客户约课后剩余可用次数
        int num1 = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),1);
        Log.info("客户可用约课次数："+num1);
        Assert.assertEquals(num,num1+1);
        Reporter.log("用例执行成功");
    }

    /**非会员成功约课的情况*/
    @Test(dataProvider = "userUnCust")
    public void appointmentCoursesCase2(User user,Customer customer){
        AppointmentCoursesCase appointmentCoursesCase = new AppointmentCoursesCase();
        appointmentCoursesCase.setCourseName("黑龙江1202");
        appointmentCoursesCase.setCourseId("KCBH-201912021007329009");
        appointmentCoursesCase.setCompanyId(customer.getCustId());
        appointmentCoursesCase.setCompanyName(customer.getCustName());
        appointmentCoursesCase.setLkmName(customer.getLkms().get(0).getLkmName());
        appointmentCoursesCase.setLkmMobile(customer.getLkms().get(0).getLkmMobile());
        appointmentCoursesCase.setAppointmentCoursesExpect("{\"msg\":\"操作成功\",\"code\":0}");
        //查询客户约课前剩余可用次数
        int num = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),0);
        Log.info("客户可用约课次数："+num);
        //约课
        appointmentCoursesImpl.insertAppointmentCoursesCase(appointmentCoursesCase);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase.getCaseId());
        String res = appointmentCoursesCaseRes.getResult();
        String expext = appointmentCoursesCase.getAppointmentCoursesExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,res);
        //查询客户约课后剩余可用次数
        int num1 = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),0);
        Log.info("客户可用约课次数："+num1);
        Assert.assertEquals(num,num1+1);
        Reporter.log("用例执行成功");
    }

    /**会员成功约课后，取消约课的情况*/
    @Test(dataProvider = "userCust")
    public void appointmentCoursesCase3(User user,Customer customer){
        AppointmentCoursesCase appointmentCoursesCase = new AppointmentCoursesCase();
        appointmentCoursesCase.setCourseName("黑龙江1202");
        appointmentCoursesCase.setCourseId("KCBH-201912021007329009");
        appointmentCoursesCase.setCompanyId(customer.getCustId());
        appointmentCoursesCase.setCompanyName(customer.getCustName());
        appointmentCoursesCase.setLkmName(customer.getLkms().get(0).getLkmName());
        appointmentCoursesCase.setLkmMobile(customer.getLkms().get(0).getLkmMobile());
        appointmentCoursesCase.setAppointmentCoursesExpect("{\"msg\":\"操作成功\",\"code\":0}");
        //查询客户约课前剩余可用次数
        int num = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),1);
        Log.info("客户可用约课次数："+num);
        //约课
        appointmentCoursesImpl.insertAppointmentCoursesCase(appointmentCoursesCase);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase.getCaseId());
        String res = appointmentCoursesCaseRes.getResult();
        String expext = appointmentCoursesCase.getAppointmentCoursesExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,res);
        //查询客户约课后剩余可用次数
        int num1 = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),1);
        Log.info("客户可用约课次数："+num1);
        Assert.assertEquals(num,num1+1);
        //取消约课
        Course course = new Course();
        course.setCourseId(appointmentCoursesCase.getCourseId());
        course.setCourseName(appointmentCoursesCase.getCourseName());
        removeReservationImpl.removeReservation(user,customer,course);
        //查询客户约课后剩余可用次数
        int num2 = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),1);
        Log.info("客户可用约课次数："+num2);
        Assert.assertEquals(num,num2);
        Reporter.log("用例执行成功");
    }

    /**非会员成功约课,不允许取消约课*/
    @Test(dataProvider = "userUnCust")
    public void appointmentCoursesCase4(User user,Customer customer){
        AppointmentCoursesCase appointmentCoursesCase = new AppointmentCoursesCase();
        appointmentCoursesCase.setCourseName("黑龙江1202");
        appointmentCoursesCase.setCourseId("KCBH-201912021007329009");
        appointmentCoursesCase.setCompanyId(customer.getCustId());
        appointmentCoursesCase.setCompanyName(customer.getCustName());
        appointmentCoursesCase.setLkmName(customer.getLkms().get(0).getLkmName());
        appointmentCoursesCase.setLkmMobile(customer.getLkms().get(0).getLkmMobile());
        appointmentCoursesCase.setAppointmentCoursesExpect("{\"msg\":\"操作成功\",\"code\":0}");
        //查询客户约课前剩余可用次数
        int num = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),0);
        Log.info("客户可用约课次数："+num);
        //约课
        appointmentCoursesImpl.insertAppointmentCoursesCase(appointmentCoursesCase);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase.getCaseId());
        String res = appointmentCoursesCaseRes.getResult();
        String expext = appointmentCoursesCase.getAppointmentCoursesExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,res);
        //查询客户约课后剩余可用次数
        int num1 = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),0);
        Log.info("客户可用约课次数："+num1);
        Assert.assertEquals(num,num1+1);
        //取消约课
        Course course = new Course();
        course.setCourseId(appointmentCoursesCase.getCourseId());
        course.setCourseName(appointmentCoursesCase.getCourseName());
        String res1 = removeReservationImpl.removeReservation(user,customer,course);
        Log.info("取消约课返回结果："+res1);
        Assert.assertEquals(res1,"{\"msg\":\"试听课无法取消\",\"code\":500}");
        Reporter.log("用例执行成功");
    }

    /**会员成功约课，再次预约同一课程*/
    @Test(dataProvider = "userCust")
    public void appointmentCoursesCase5(User user,Customer customer){
        AppointmentCoursesCase appointmentCoursesCase = new AppointmentCoursesCase();
        appointmentCoursesCase.setCourseName("黑龙江1202");
        appointmentCoursesCase.setCourseId("KCBH-201912021007329009");
        appointmentCoursesCase.setCompanyId(customer.getCustId());
        appointmentCoursesCase.setCompanyName(customer.getCustName());
        appointmentCoursesCase.setLkmName(customer.getLkms().get(0).getLkmName());
        appointmentCoursesCase.setLkmMobile(customer.getLkms().get(0).getLkmMobile());
        appointmentCoursesCase.setAppointmentCoursesExpect("{\"msg\":\"操作成功\",\"code\":0}");
        //查询客户约课前剩余可用次数
        int num = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),1);
        Log.info("客户可用约课次数："+num);
        //约课
        appointmentCoursesImpl.insertAppointmentCoursesCase(appointmentCoursesCase);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase.getCaseId());
        String res = appointmentCoursesCaseRes.getResult();
        String expext = appointmentCoursesCase.getAppointmentCoursesExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,res);
        //查询客户约课后剩余可用次数
        int num1 = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),1);
        Log.info("客户可用约课次数："+num1);
        Assert.assertEquals(num,num1+1);
        //再次约课
        AppointmentCoursesCaseRes appointmentCoursesCaseRes1 = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase.getCaseId());
        Log.info(appointmentCoursesCaseRes1.getResult());
        Assert.assertEquals(appointmentCoursesCaseRes1.getResult(),"{\"msg\":\"客户["+appointmentCoursesCase.getLkmMobile()+"]已预约该课程\",\"code\":500}");
        Reporter.log("用例执行成功");
    }

    /**会员没有手机号，约课失败*/
    @Test(dataProvider = "userCust")
    public void appointmentCoursesCase6(User user,Customer customer){
        AppointmentCoursesCase appointmentCoursesCase = new AppointmentCoursesCase();
        appointmentCoursesCase.setCourseName("黑龙江1202");
        appointmentCoursesCase.setCourseId("KCBH-201912021007329009");
        appointmentCoursesCase.setCompanyId(customer.getCustId());
        appointmentCoursesCase.setCompanyName(customer.getCustName());
        appointmentCoursesCase.setLkmName(customer.getLkms().get(0).getLkmName());
        appointmentCoursesCase.setLkmMobile("");
        appointmentCoursesCase.setAppointmentCoursesExpect("{\"msg\":\"抱歉，您的客户["+appointmentCoursesCase.getCompanyName()+"]下联系人["+appointmentCoursesCase.getLkmName()+"]没有联系方式，暂时不能约课。请去【联系人管理】中添加手机号以后再约课\",\"code\":500}");
        //查询客户约课前剩余可用次数
        int num = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),1);
        Log.info("客户可用约课次数："+num);
        //约课
        appointmentCoursesImpl.insertAppointmentCoursesCase(appointmentCoursesCase);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase.getCaseId());
        String res = appointmentCoursesCaseRes.getResult();
        String expext = appointmentCoursesCase.getAppointmentCoursesExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,res);
        Reporter.log("用例执行成功");
    }

    /**非会员成功约课,再约其他课程，由于次数不足约课失败*/
    @Test(dataProvider = "userUnCust")
    public void appointmentCoursesCase7(User user,Customer customer){
        AppointmentCoursesCase appointmentCoursesCase = new AppointmentCoursesCase();
        appointmentCoursesCase.setCourseName("黑龙江1202");
        appointmentCoursesCase.setCourseId("KCBH-201912021007329009");
        appointmentCoursesCase.setCompanyId(customer.getCustId());
        appointmentCoursesCase.setCompanyName(customer.getCustName());
        appointmentCoursesCase.setLkmName(customer.getLkms().get(0).getLkmName());
        appointmentCoursesCase.setLkmMobile(customer.getLkms().get(0).getLkmMobile());
        appointmentCoursesCase.setAppointmentCoursesExpect("{\"msg\":\"操作成功\",\"code\":0}");
        //查询客户约课前剩余可用次数
        int num = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),0);
        Log.info("客户可用约课次数："+num);
        //约课
        appointmentCoursesImpl.insertAppointmentCoursesCase(appointmentCoursesCase);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase.getCaseId());
        String res = appointmentCoursesCaseRes.getResult();
        String expext = appointmentCoursesCase.getAppointmentCoursesExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,res);
        //查询客户约课后剩余可用次数
        int num1 = appointmentCoursesImpl.getCustServUsableNum(user,appointmentCoursesCase.getCompanyName(),0);
        Log.info("客户可用约课次数："+num1);
        Assert.assertEquals(num,num1+1);
        //预约其他课程
        AppointmentCoursesCase appointmentCoursesCase1 = new AppointmentCoursesCase();
        appointmentCoursesCase1.setCourseName("黑龙江--自动化测试课程");
        appointmentCoursesCase1.setCourseId("KCBH-201910281437394968");
        appointmentCoursesCase1.setCompanyId(customer.getCustId());
        appointmentCoursesCase1.setCompanyName(customer.getCustName());
        appointmentCoursesCase1.setLkmName(customer.getLkms().get(0).getLkmName());
        appointmentCoursesCase1.setLkmMobile(customer.getLkms().get(0).getLkmMobile());
        appointmentCoursesCase1.setAppointmentCoursesExpect("{\"msg\":\"约课失败，剩余次数不足\",\"code\":500}");
        //约课
        appointmentCoursesImpl.insertAppointmentCoursesCase(appointmentCoursesCase1);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes1 = appointmentCoursesImpl.runAppointmentCoursesCase(user,appointmentCoursesCase1.getCaseId());
        String res1 = appointmentCoursesCaseRes1.getResult();
        Log.info(res1);
        Assert.assertEquals(res1,appointmentCoursesCase1.getAppointmentCoursesExpect());
        Reporter.log("用例执行成功");
    }













    @BeforeTest
    public void beforeTest(){
        Log.startTestCase(getClass().getName());
    }
    @AfterTest
    public void afterTest(){
        Log.endTestCase(getClass().getName());
    }

    @DataProvider(name = "userCust")
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
        Contract contract = signContractImpl.signContract(sell,customer,lkm,"测试套餐");
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
        return new Object[][]{{user,customer}};
    }

    @DataProvider(name = "userUnCust")
    public Object[][] dataProvider1(){
        User user = loginImpl.login("xiaoshou","m1111111");
        GetUserInfo getUserInfo = new GetUserInfo();
        User sell = getUserInfo.getUserInfoByLoginName(user);
        Customer customer = addCustImpl.addPersonalCustByCase(sell);
        Log.info("---新加客户id：" + customer.getCustId() + "-----客户名：" + customer.getCustName());
        Assert.assertNotEquals(0,customer.getCustId());
        Lkm lkm = addLinkmanImpl.addLinkmanByCase(sell,customer);
        Log.info("---新加联系人id："+lkm.getLkmId()+"---新加联系人姓名："+lkm.getLkmName()+"---新加联系人手机号："+lkm.getLkmMobile());
        Assert.assertNotEquals(0,lkm.getLkmId());
        return new Object[][]{{user,customer}};
    }
}
