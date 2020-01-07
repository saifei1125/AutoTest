package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.dao.entity.SignContractCase;
import com.taxchina.autotest.crmnew.dao.entity.SignContractCaseRes;
import com.taxchina.autotest.crmnew.service.common.GetSuiteInfo;
import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.common.GetlkmInfo;
import com.taxchina.autotest.crmnew.service.common.SubmitContract;
import com.taxchina.autotest.crmnew.service.entity.*;
import com.taxchina.autotest.crmnew.service.impl.*;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class SignContractCaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private LoginImpl loginImpl;
    @Autowired
    private SignContractImpl signContractImpl;
    @Autowired
    private AddFinanceImpl addFinanceImpl;
    @Autowired
    private RelevanceFinanceImpl relevanceFinanceImpl;
    @Autowired
    private AddInvoiceImpl addInvoiceImpl;
    @Autowired
    private RelevanceInvoiceImpl relevanceInvoiceImpl;
    @Autowired
    private ConfirmInvoiceImpl confirmInvoiceImpl;
    @Autowired
    private ConfirmPerformanceImpl confirmPerformanceImpl;

    /**合同签订成功,并提交成功的情况*/
    @Test(dataProvider = "cust")
    public void signContractCase1(User user, Customer customer){
        SignContractCase signContractCase = new SignContractCase();
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfoByName(user,customer,"测试套餐");
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setProductList(suite.getProductList());
        //生成签合同的用例
        signContractCase.setCustId(customer.getCustId());
        signContractCase.setCustName(customer.getCustName());
        signContractCase.setSignTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setSuiteName(suite.getSuiteName());
        signContractCase.setSuiteDesc(suite.getSuiteDesc());
        signContractCase.setSuiteAmount(suite.getSuitePrice());
        signContractCase.setSuiteType(suite.getSuiteType());
        signContractCase.setTemplateId(suite.getTemplateId());
        signContractCase.setLkmId(customer.getLkms().get(0).getLkmId());
        signContractCase.setLkmName(customer.getLkms().get(0).getLkmName());
        signContractCase.setLkmTel(customer.getLkms().get(0).getLkmMobile());
        signContractCase.setOriginalAmount(suite.getSuitePrice());//合同原始金额
        signContractCase.setActualAmount(suite.getSuitePrice());//合同实际金额（合同最终价）
        signContractCase.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setEndTime("2020-11-26");
        signContractCase.setActualStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setActualEndTime("2020-11-26");
        signContractImpl.insertSignContractCase(signContractCase);
        SignContractCaseRes signContractCaseRes =  signContractImpl.runSignContractCaseById(user,signContractCase.getCaseId());
        Assert.assertNotNull(signContractCaseRes.getContractNo());
        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,signContractCaseRes.getContractNo());
        //验证合同信息
        Contract contract = new Contract();
        contract.setContractNo(signContractCaseRes.getContractNo());
        contract.setContractStatus(4);//审核通过
        contract.setServiceStatus(0);//未生效
        contract.setCollectionStatus(0);//未到款
        contract.setPaymentsFlag(0);//未交齐
        contract.setInvoiceFlag(0);//未开
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);
        Boolean b = signContractImpl.checkContractInfo(user,contract,signContractCase);
        if(b) {
            Log.info("通过列表查询合同信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询合同信息或状态验证失败！！！");
        }
    }

    /**合同签订成功,且未提交的情况*/
    @Test(dataProvider = "cust")
    public void signContractCase2(User user,Customer customer){
        SignContractCase signContractCase = new SignContractCase();
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfoByName(user,customer,"智能白金卡A-20191217");
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setProductList(suite.getProductList());
        //生成签合同的用例
        signContractCase.setCustId(customer.getCustId());
        signContractCase.setCustName(customer.getCustName());
        signContractCase.setSignTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setSuiteName(suite.getSuiteName());
        signContractCase.setSuiteDesc(suite.getSuiteDesc());
        signContractCase.setSuiteAmount(suite.getSuitePrice());
        signContractCase.setSuiteType(suite.getSuiteType());
        signContractCase.setTemplateId(suite.getTemplateId());
        signContractCase.setLkmId(customer.getLkms().get(0).getLkmId());
        signContractCase.setLkmName(customer.getLkms().get(0).getLkmName());
        signContractCase.setLkmTel(customer.getLkms().get(0).getLkmMobile());
        signContractCase.setOriginalAmount(suite.getSuitePrice());//合同原始金额
        signContractCase.setActualAmount(suite.getSuitePrice());//合同实际金额（合同最终价）
        signContractCase.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setEndTime("2020-11-26");
        signContractCase.setActualStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setActualEndTime("2020-11-26");
        signContractImpl.insertSignContractCase(signContractCase);
        SignContractCaseRes signContractCaseRes =  signContractImpl.runSignContractCaseById(user,signContractCase.getCaseId());
        Assert.assertNotNull(signContractCaseRes.getContractNo());

        //验证合同信息
        Contract contract = new Contract();
        contract.setContractNo(signContractCaseRes.getContractNo());
        contract.setContractStatus(0);//草稿
        contract.setServiceStatus(0);//未生效
        contract.setCollectionStatus(0);//未到款
        contract.setPaymentsFlag(0);//未交齐
        contract.setInvoiceFlag(0);//未开
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);
        Boolean b = signContractImpl.checkContractInfo(user,contract,signContractCase);
        if(b) {
            Log.info("通过列表查询合同信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询合同信息或状态验证失败！！！");
        }
    }

    /**合同签订成功,且全部回款的情况*/
    @Test(dataProvider = "cust")
    public void signContractCase3(User user,Customer customer){
        SignContractCase signContractCase = new SignContractCase();
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfoByName(user,customer,"智能金卡A-20191227");
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setProductList(suite.getProductList());
        //生成签合同的用例
        signContractCase.setCustId(customer.getCustId());
        signContractCase.setCustName(customer.getCustName());
        signContractCase.setSignTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setSuiteName(suite.getSuiteName());
        signContractCase.setSuiteDesc(suite.getSuiteDesc());
        signContractCase.setSuiteAmount(suite.getSuitePrice());
        signContractCase.setSuiteType(suite.getSuiteType());
        signContractCase.setTemplateId(suite.getTemplateId());
        signContractCase.setLkmId(customer.getLkms().get(0).getLkmId());
        signContractCase.setLkmName(customer.getLkms().get(0).getLkmName());
        signContractCase.setLkmTel(customer.getLkms().get(0).getLkmMobile());
        signContractCase.setOriginalAmount(suite.getSuitePrice());//合同原始金额
        signContractCase.setActualAmount(suite.getSuitePrice());//合同实际金额（合同最终价）
        signContractCase.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setEndTime("2020-11-26");
        signContractCase.setActualStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setActualEndTime("2020-11-26");
        signContractImpl.insertSignContractCase(signContractCase);
        SignContractCaseRes signContractCaseRes =  signContractImpl.runSignContractCaseById(user,signContractCase.getCaseId());
        Assert.assertNotNull(signContractCaseRes.getContractNo());
        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,signContractCaseRes.getContractNo());
        //添加回款
        User accountant = loginImpl.login("caiwu","m1111111");
        Finance finance = addFinanceImpl.addFinanceByLoginnameAndAmount(accountant,user,suite.getSuitePrice());
        //回款绑定合同
        Contract contract = new Contract();
        contract.setContractNo(signContractCaseRes.getContractNo());
        relevanceFinanceImpl.relevanceFinance(user,contract,finance);
        //验证合同信息
        contract.setContractStatus(4);//审核通过
        contract.setServiceStatus(0);//未生效
        contract.setCollectionStatus(2);//款已齐
        contract.setPaymentsFlag(1);//已交齐
        contract.setInvoiceFlag(0);//未开
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);
        Boolean b = signContractImpl.checkContractInfo(user,contract,signContractCase);
        if(b) {
            Log.info("通过列表查询合同信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询合同信息或状态验证失败！！！");
        }
    }

    /**合同签订成功,部分回款的情况*/
    @Test(dataProvider = "cust")
    public void signContractCase4(User user,Customer customer){
        SignContractCase signContractCase = new SignContractCase();
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfoByName(user,customer,"智能银卡A-20191227");
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setProductList(suite.getProductList());
        //生成签合同的用例
        signContractCase.setCustId(customer.getCustId());
        signContractCase.setCustName(customer.getCustName());
        signContractCase.setSignTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setSuiteName(suite.getSuiteName());
        signContractCase.setSuiteDesc(suite.getSuiteDesc());
        signContractCase.setSuiteAmount(suite.getSuitePrice());
        signContractCase.setSuiteType(suite.getSuiteType());
        signContractCase.setTemplateId(suite.getTemplateId());
        signContractCase.setLkmId(customer.getLkms().get(0).getLkmId());
        signContractCase.setLkmName(customer.getLkms().get(0).getLkmName());
        signContractCase.setLkmTel(customer.getLkms().get(0).getLkmMobile());
        signContractCase.setOriginalAmount(suite.getSuitePrice());//合同原始金额
        signContractCase.setActualAmount(suite.getSuitePrice());//合同实际金额（合同最终价）
        signContractCase.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setEndTime("2020-11-26");
        signContractCase.setActualStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setActualEndTime("2020-11-26");
        signContractImpl.insertSignContractCase(signContractCase);
        SignContractCaseRes signContractCaseRes =  signContractImpl.runSignContractCaseById(user,signContractCase.getCaseId());
        Assert.assertNotNull(signContractCaseRes.getContractNo());
        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,signContractCaseRes.getContractNo());
        //添加回款
        User accountant = loginImpl.login("caiwu","m1111111");
        Finance finance = addFinanceImpl.addFinanceByLoginnameAndAmount(accountant,user,"10");
        //回款绑定合同
        Contract contract = new Contract();
        contract.setContractNo(signContractCaseRes.getContractNo());
        relevanceFinanceImpl.relevanceFinance(user,contract,finance);
        //验证合同信息
        contract.setContractStatus(4);//审核通过
        contract.setServiceStatus(0);//未生效
        contract.setCollectionStatus(1);//部分到款
        contract.setPaymentsFlag(0);//未交齐
        contract.setInvoiceFlag(0);//未开
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);
        Boolean b = signContractImpl.checkContractInfo(user,contract,signContractCase);
        if(b) {
            Log.info("通过列表查询合同信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询合同信息或状态验证失败！！！");
        }
    }

    /**合同签订成功,并开具发票的情况*/
    @Test(dataProvider = "cust")
    public void signContractCase5(User user,Customer customer){
        SignContractCase signContractCase = new SignContractCase();
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfoByName(user,customer,"智能多功能卡B-20191227");
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setProductList(suite.getProductList());
        //生成签合同的用例
        signContractCase.setCustId(customer.getCustId());
        signContractCase.setCustName(customer.getCustName());
        signContractCase.setSignTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setSuiteName(suite.getSuiteName());
        signContractCase.setSuiteDesc(suite.getSuiteDesc());
        signContractCase.setSuiteAmount(suite.getSuitePrice());
        signContractCase.setSuiteType(suite.getSuiteType());
        signContractCase.setTemplateId(suite.getTemplateId());
        signContractCase.setLkmId(customer.getLkms().get(0).getLkmId());
        signContractCase.setLkmName(customer.getLkms().get(0).getLkmName());
        signContractCase.setLkmTel(customer.getLkms().get(0).getLkmMobile());
        signContractCase.setOriginalAmount(suite.getSuitePrice());//合同原始金额
        signContractCase.setActualAmount(suite.getSuitePrice());//合同实际金额（合同最终价）
        signContractCase.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setEndTime("2020-11-26");
        signContractCase.setActualStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setActualEndTime("2020-11-26");
        signContractImpl.insertSignContractCase(signContractCase);
        SignContractCaseRes signContractCaseRes =  signContractImpl.runSignContractCaseById(user,signContractCase.getCaseId());
        Assert.assertNotNull(signContractCaseRes.getContractNo());
        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,signContractCaseRes.getContractNo());
        //添加发票
        Invoice invoice = addInvoiceImpl.addInvoice(user,customer);
        //审核发票
        User accountant = loginImpl.login("caiwu","m1111111");
        confirmInvoiceImpl.confirmInvoice(accountant,invoice);
        //发票绑定合同
        Contract contract = new Contract();
        contract.setContractNo(signContractCaseRes.getContractNo());
        relevanceInvoiceImpl.relevanceInvoice(user,invoice,contract);
        //验证合同信息
        contract.setContractStatus(4);//审核通过
        contract.setServiceStatus(0);//未生效
        contract.setCollectionStatus(0);//未到款
        contract.setPaymentsFlag(0);//未交齐
        contract.setInvoiceFlag(1);//已开
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);
        Boolean b = signContractImpl.checkContractInfo(user,contract,signContractCase);
        if(b) {
            Log.info("通过列表查询合同信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询合同信息或状态验证失败！！！");
        }
    }

    /**合同签订成功,并确认业绩的情况*/
    @Test(dataProvider = "cust")
    public void signContractCase6(User user,Customer customer){
        SignContractCase signContractCase = new SignContractCase();
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfoByName(user,customer,"测试套餐");
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setProductList(suite.getProductList());
        //生成签合同的用例
        signContractCase.setCustId(customer.getCustId());
        signContractCase.setCustName(customer.getCustName());
        signContractCase.setSignTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setSuiteName(suite.getSuiteName());
        signContractCase.setSuiteDesc(suite.getSuiteDesc());
        signContractCase.setSuiteAmount(suite.getSuitePrice());
        signContractCase.setSuiteType(suite.getSuiteType());
        signContractCase.setTemplateId(suite.getTemplateId());
        signContractCase.setLkmId(customer.getLkms().get(0).getLkmId());
        signContractCase.setLkmName(customer.getLkms().get(0).getLkmName());
        signContractCase.setLkmTel(customer.getLkms().get(0).getLkmMobile());
        signContractCase.setOriginalAmount(suite.getSuitePrice());//合同原始金额
        signContractCase.setActualAmount(suite.getSuitePrice());//合同实际金额（合同最终价）
        signContractCase.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setEndTime("2020-11-26");
        signContractCase.setActualStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setActualEndTime("2020-11-26");
        signContractImpl.insertSignContractCase(signContractCase);
        SignContractCaseRes signContractCaseRes =  signContractImpl.runSignContractCaseById(user,signContractCase.getCaseId());
        Assert.assertNotNull(signContractCaseRes.getContractNo());
        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,signContractCaseRes.getContractNo());
        //添加回款
        User accountant = loginImpl.login("caiwu","m1111111");
        Finance finance = addFinanceImpl.addFinanceByLoginnameAndAmount(accountant,user,suite.getSuitePrice());
        //回款绑定合同
        Contract contract = new Contract();
        contract.setContractNo(signContractCaseRes.getContractNo());
        relevanceFinanceImpl.relevanceFinance(user,contract,finance);
        //确认业绩
        User cfo = loginImpl.login("zongbucaiwu","m1111111");
        confirmPerformanceImpl.confirmPerformanceByCase(cfo,finance);
        //验证合同信息
        contract.setContractStatus(4);//审核通过
        contract.setServiceStatus(1);//已生效
        contract.setCollectionStatus(2);//款已齐
        contract.setPaymentsFlag(1);//已交齐
        contract.setInvoiceFlag(0);//未开
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);
        Boolean b = signContractImpl.checkContractInfo(user,contract,signContractCase);
        if(b) {
            Log.info("通过列表查询合同信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询合同信息或状态验证失败！！！");
        }
    }





















    @BeforeTest
    public void beforeTest(){
        Log.startTestCase(getClass().getName());
    }
    @AfterTest
    public void afterTest(){
        Log.endTestCase(getClass().getName());
    }

    @DataProvider(name = "cust")
    public Object[][] dataProvider(){
        User user = loginImpl.login("xiaoshou","m1111111");
        Customer customer = new Customer();
        customer.setCustName("测试个人客户257");
        customer.setCustId(1400);
        GetlkmInfo getlkmInfo = new GetlkmInfo();
        getlkmInfo.getlkmInfo(user,customer);
        return new Object[][]{{user,customer}};
    }


}
