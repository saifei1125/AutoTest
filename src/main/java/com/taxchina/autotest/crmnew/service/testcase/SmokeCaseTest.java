package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.service.entity.*;
import com.taxchina.autotest.crmnew.service.impl.*;
import com.taxchina.autotest.crmnew.service.utils.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

@SpringBootTest
public class SmokeCaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private LoginImpl loginImpl;
    @Autowired
    private AddCustImpl addCustImpl;
    @Autowired
    private AddLinkmanImpl addLinkmanImpl;
    @Autowired
    private SignContractImpl signContractImpl;
    @Autowired
    private AddInvoiceImpl addInvoiceImpl;
    @Autowired
    private ConfirmInvoiceImpl confirmInvoiceImpl;
    @Autowired
    private AddFinanceImpl addFinanceImpl;
    @Autowired
    private RelevanceFinanceImpl relevanceFinanceImpl;
    @Autowired
    private RelevanceInvoiceImpl relevanceInvoiceImpl;
    @Autowired
    private ConfirmPerformanceImpl confirmPerformanceImpl;
    @Autowired
    private AppointmentCoursesImpl appointmentCoursesImpl;
    @Autowired
    private RemoveReservationImpl removeReservationImpl;
    @Autowired
    private AppLoginImpl appLoginImpl;
    @Autowired
    private AppSignInImpl appSignInImpl;
    @Test
    public void smokeCase(){
        //登录
        User sell = loginImpl.loginNew(1);
        //添加客户
        Customer customer = addCustImpl.addPersonalCustByCase(sell);//添加个人客户
//        Customer customer = addCustImpl.addBusinessCustomer(sell,"江苏宏顺高空工程有限公司");//添加企业客户，客户名必须用未录入在系统中的
        Log.info("---新加客户id：" + customer.getCustId() + "-----客户名：" + customer.getCustName());
        Assert.assertNotEquals(0,customer.getCustId());
        //添加联系人
//        Lkm lkm = addLinkmanImpl.addLinkman(sell,customer);
        Lkm lkm = addLinkmanImpl.addLinkmanByCase(sell,customer);
        Log.info("---新加联系人id："+lkm.getLkmId()+"---新加联系人姓名："+lkm.getLkmName()+"---新加联系人手机号："+lkm.getLkmMobile());
        Assert.assertNotEquals(0,lkm.getLkmId());
        //签合同
        Contract contract = signContractImpl.signContract(sell,customer,lkm,"测试套餐");
        Log.info("---新签合同id：" + contract.getContractNo());
        Assert.assertNotNull(contract.getContractNo());
        //提交发票
        Invoice invoice =addInvoiceImpl.addInvoice(sell,customer);
        //财务审核发票
        User accountant = loginImpl.login("caiwu","m1111111");
        confirmInvoiceImpl.confirmInvoice(accountant, invoice);
        //财务录入回款
        Finance finance = addFinanceImpl.addFinanceByLoginnameByCase(accountant,sell,invoice);
        Log.info("---新录入回款编号：" + finance.getCollectionNo() + " 回款id:"+finance.getId());
        Assert.assertNotNull(finance.getCollectionNo());
        //回款绑定合同
        relevanceFinanceImpl.relevanceFinance(sell,contract,finance);
        //回款绑定发票
        relevanceInvoiceImpl.relevanceInvoice(sell,invoice,contract);
        //总部财务审核业绩
        User cfo = loginImpl.login("zongbucaiwu","m1111111");
        confirmPerformanceImpl.confirmPerformanceByCase(cfo,finance);
        //销售给客户约课
        Course course = new Course();
        course.setCourseName("黑龙江--自动化测试课程");
        course.setCourseId("KCBH-201910281437394968");
        ReservationList reservationList = appointmentCoursesImpl.appointmentCourses(sell,customer,course);
        Log.info("预约码："+reservationList.getInvitationCode());
        Assert.assertNotNull(reservationList.getInvitationCode());
        //销售给客户取消约课
//        removeReservationImpl.removeReservation(sell,customer);
        //模拟app签到
        appLoginImpl.appLogin(sell);
        appSignInImpl.signIn(sell,course,reservationList.getInvitationCode());
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
