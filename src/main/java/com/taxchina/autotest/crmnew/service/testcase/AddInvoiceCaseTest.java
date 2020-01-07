package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.dao.entity.AddInvoiceCase;
import com.taxchina.autotest.crmnew.dao.entity.AddInvoiceCaseRes;
import com.taxchina.autotest.crmnew.service.common.GetInvoiceInfo;
import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.impl.AddInvoiceImpl;
import com.taxchina.autotest.crmnew.service.impl.ConfirmInvoiceImpl;
import com.taxchina.autotest.crmnew.service.impl.LoginImpl;
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
public class AddInvoiceCaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private LoginImpl loginImpl;
    @Autowired
    private AddInvoiceImpl addInvoiceImpl;
    @Autowired
    private ConfirmInvoiceImpl confirmInvoiceImpl;

    /**成功提交发票,未审核*/
    @Test(dataProvider = "user")
    public void addInvoiceCase1(User sell, Customer customer){
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        Invoice invoice = getInvoiceInfo.getInvoiceInfo(sell,customer);
        invoice.setInvoiceDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        AddInvoiceCase addInvoiceCase = new AddInvoiceCase();
        addInvoiceCase.setInvoiceNo(invoice.getInvoiceNo());
        addInvoiceCase.setCustomerId(customer.getCustId());
        addInvoiceCase.setCustomerName(customer.getCustName());
        addInvoiceCase.setInvoiceType(1);//开票种类  1 增值税专票  2 增值税普票
        addInvoiceCase.setInvoiceProject(1);
        addInvoiceCase.setCustType(1);//个人
        addInvoiceCase.setInvoiceLabel(0);
        addInvoiceCase.setInvoiceTitle("测试开票"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//"测试开票"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()) customer.getCustName()+"111"
        addInvoiceCase.setApplyDate(invoice.getInvoiceDate());//开票日期，默认当天
        addInvoiceCase.setApplyAmount("1000");
        GetUserInfo getUserInfo = new GetUserInfo();
        User user1 = getUserInfo.getUserInfoByLoginName(sell);
        sell.setUsername(user1.getUsername());
        addInvoiceCase.setApplyUserame(sell.getUsername());
        addInvoiceCase.setTin("16874657465765");
        addInvoiceCase.setOpeningBank("招商银行");
        addInvoiceCase.setOpeningBankAccount("6222600910047845104");
        addInvoiceCase.setBusinessAddress("测试地址");
        addInvoiceCase.setBusinessTel("67001111");
        addInvoiceCase.setSiuteType("BJK");
        addInvoiceCase.setArrivalMoneyFlag(1);//是否已到款  1 未到款 2 已到款 0 未知
        addInvoiceCase.setAddinvoiceExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addInvoiceImpl.insertAddInvoiceCase(addInvoiceCase);
        AddInvoiceCaseRes addInvoiceCaseRes = addInvoiceImpl.runAddInvoiceCaseById(sell,addInvoiceCase.getCaseId());
        Assert.assertEquals(addInvoiceCaseRes.getResult(),addInvoiceCase.getAddinvoiceExpect());
        invoice.setStatus(1);
        Boolean b = addInvoiceImpl.checkInvoiceInfo(sell,invoice,addInvoiceCase);
        if(b) {
            Log.info("通过列表查询发票信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询发票信息或状态验证失败！！！");
        }
    }

    /**成功提交发票,审核通过*/
    @Test(dataProvider = "user")
    public void addInvoiceCase2(User sell,Customer customer){
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        Invoice invoice = getInvoiceInfo.getInvoiceInfo(sell,customer);
        invoice.setInvoiceDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        AddInvoiceCase addInvoiceCase = new AddInvoiceCase();
        addInvoiceCase.setInvoiceNo(invoice.getInvoiceNo());
        addInvoiceCase.setCustomerId(customer.getCustId());
        addInvoiceCase.setCustomerName(customer.getCustName());
        addInvoiceCase.setInvoiceType(1);//开票种类  1 增值税专票  2 增值税普票
        addInvoiceCase.setInvoiceProject(1);
        addInvoiceCase.setCustType(1);
        addInvoiceCase.setInvoiceLabel(1);
        addInvoiceCase.setInvoiceTitle("测试开票"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        addInvoiceCase.setApplyDate(invoice.getInvoiceDate());//开票日期，默认当天
        addInvoiceCase.setApplyAmount("100");
        GetUserInfo getUserInfo = new GetUserInfo();
        User user1 = getUserInfo.getUserInfoByLoginName(sell);
        sell.setUsername(user1.getUsername());
        addInvoiceCase.setApplyUserame(sell.getUsername());
        addInvoiceCase.setTin("16874657465765");
        addInvoiceCase.setOpeningBank("招商银行");
        addInvoiceCase.setOpeningBankAccount("6222600910047845104");
        addInvoiceCase.setBusinessAddress("测试地址");
        addInvoiceCase.setBusinessTel("67001111");
        addInvoiceCase.setSiuteType("SWKJ");
        addInvoiceCase.setArrivalMoneyFlag(1);//是否已到款  1 未到款 2 已到款 0 未知
        addInvoiceCase.setAddinvoiceExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addInvoiceImpl.insertAddInvoiceCase(addInvoiceCase);
        AddInvoiceCaseRes addInvoiceCaseRes = addInvoiceImpl.runAddInvoiceCaseById(sell,addInvoiceCase.getCaseId());
        Assert.assertEquals(addInvoiceCaseRes.getResult(),addInvoiceCase.getAddinvoiceExpect());
        User accountant = loginImpl.login("caiwu","m1111111");
        confirmInvoiceImpl.confirmInvoice(accountant, invoice);
        //验证发票信息
        invoice.setStatus(2);
        Boolean b = addInvoiceImpl.checkInvoiceInfo(sell,invoice,addInvoiceCase);
        if(b) {
            Log.info("通过列表查询发票信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询发票信息或状态验证失败！！！");
        }
    }

    /**成功提交发票,审核拒绝*/
    @Test(dataProvider = "user")
    public void addInvoiceCase3(User sell,Customer customer){
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        Invoice invoice = getInvoiceInfo.getInvoiceInfo(sell,customer);
        invoice.setInvoiceDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        AddInvoiceCase addInvoiceCase = new AddInvoiceCase();
        addInvoiceCase.setInvoiceNo(invoice.getInvoiceNo());
        addInvoiceCase.setCustomerId(customer.getCustId());
        addInvoiceCase.setCustomerName(customer.getCustName());
        addInvoiceCase.setInvoiceType(1);//开票种类  1 增值税专票  2 增值税普票
        addInvoiceCase.setInvoiceProject(1);
        addInvoiceCase.setCustType(0);
        addInvoiceCase.setInvoiceLabel(0);
        addInvoiceCase.setInvoiceTitle("测试开票"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        addInvoiceCase.setApplyDate(invoice.getInvoiceDate());//开票日期，默认当天
        addInvoiceCase.setApplyAmount("100");
        GetUserInfo getUserInfo = new GetUserInfo();
        User user1 = getUserInfo.getUserInfoByLoginName(sell);
        sell.setUsername(user1.getUsername());
        addInvoiceCase.setApplyUserame(sell.getUsername());
        addInvoiceCase.setTin("16874657465765");
        addInvoiceCase.setOpeningBank("招商银行");
        addInvoiceCase.setOpeningBankAccount("6222600910047845104");
        addInvoiceCase.setBusinessAddress("测试地址");
        addInvoiceCase.setBusinessTel("67001111");
        addInvoiceCase.setSiuteType("ZSB");
        addInvoiceCase.setArrivalMoneyFlag(1);//是否已到款  1 未到款 2 已到款 0 未知
        addInvoiceCase.setAddinvoiceExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addInvoiceImpl.insertAddInvoiceCase(addInvoiceCase);
        AddInvoiceCaseRes addInvoiceCaseRes = addInvoiceImpl.runAddInvoiceCaseById(sell,addInvoiceCase.getCaseId());
        Assert.assertEquals(addInvoiceCaseRes.getResult(),addInvoiceCase.getAddinvoiceExpect());
        //审核拒绝
        User accountant = loginImpl.login("caiwu","m1111111");
        confirmInvoiceImpl.dismissedInvoice(accountant,invoice);
        //验证发票信息
        invoice.setStatus(4);
        Boolean b = addInvoiceImpl.checkInvoiceInfo(sell,invoice,addInvoiceCase);
        if(b) {
            Log.info("通过列表查询发票信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询发票信息或状态验证失败！！！");
        }
    }

    /**成功提交发票,审核通过后作废*/
    @Test(dataProvider = "user")
    public void addInvoiceCase4(User sell,Customer customer){
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        Invoice invoice = getInvoiceInfo.getInvoiceInfo(sell,customer);
        invoice.setInvoiceDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        AddInvoiceCase addInvoiceCase = new AddInvoiceCase();
        addInvoiceCase.setInvoiceNo(invoice.getInvoiceNo());
        addInvoiceCase.setCustomerId(customer.getCustId());
        addInvoiceCase.setCustomerName(customer.getCustName());
        addInvoiceCase.setInvoiceType(1);//开票种类  1 增值税专票  2 增值税普票
        addInvoiceCase.setInvoiceProject(1);
        addInvoiceCase.setCustType(0);
        addInvoiceCase.setInvoiceLabel(0);
        addInvoiceCase.setInvoiceTitle("测试开票"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        addInvoiceCase.setApplyDate(invoice.getInvoiceDate());//开票日期，默认当天
        addInvoiceCase.setApplyAmount("1000");
        GetUserInfo getUserInfo = new GetUserInfo();
        User user1 = getUserInfo.getUserInfoByLoginName(sell);
        sell.setUsername(user1.getUsername());
        addInvoiceCase.setApplyUserame(sell.getUsername());
        addInvoiceCase.setTin("16874657465765");
        addInvoiceCase.setOpeningBank("招商银行");
        addInvoiceCase.setOpeningBankAccount("6222600910047845104");
        addInvoiceCase.setBusinessAddress("测试地址");
        addInvoiceCase.setBusinessTel("67001111");
        addInvoiceCase.setSiuteType("JK");
        addInvoiceCase.setArrivalMoneyFlag(1);//是否已到款  1 未到款 2 已到款 0 未知
        addInvoiceCase.setAddinvoiceExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addInvoiceImpl.insertAddInvoiceCase(addInvoiceCase);
        AddInvoiceCaseRes addInvoiceCaseRes = addInvoiceImpl.runAddInvoiceCaseById(sell,addInvoiceCase.getCaseId());
        Assert.assertEquals(addInvoiceCaseRes.getResult(),addInvoiceCase.getAddinvoiceExpect());
        User accountant = loginImpl.login("caiwu","m1111111");
        confirmInvoiceImpl.confirmInvoice(accountant, invoice);
        //作废
        confirmInvoiceImpl.obsoleteInvoice(accountant,invoice);
        //验证发票信息
        invoice.setStatus(3);
        Boolean b = addInvoiceImpl.checkInvoiceInfo(sell,invoice,addInvoiceCase);
        if(b) {
            Log.info("通过列表查询发票信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询发票信息或状态验证失败！！！");
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

    @DataProvider(name = "user")
    public Object[][] userProvider() {
        User sell = loginImpl.login("xiaoshou", "m1111111");
        Customer customer = new Customer();
        customer.setCustName("北京良植化妆品有限公司");
        customer.setCustId(1579);
        return new Object[][]{{sell,customer}};
    }
}
