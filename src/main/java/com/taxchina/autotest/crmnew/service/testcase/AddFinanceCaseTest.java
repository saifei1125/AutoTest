package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.dao.entity.AddFinanceCase;
import com.taxchina.autotest.crmnew.dao.entity.AddFinanceCaseRes;
import com.taxchina.autotest.crmnew.service.common.GetFinanceInfo;
import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.common.GetlkmInfo;
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
public class AddFinanceCaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private LoginImpl loginImpl;
    @Autowired
    private AddFinanceImpl addFinanceImpl;
    @Autowired
    private SignContractImpl signContractImpl;
    @Autowired
    private RelevanceFinanceImpl relevanceFinanceImpl;
    @Autowired
    private AddInvoiceImpl addInvoiceImpl;
    @Autowired
    private ConfirmInvoiceImpl confirmInvoiceImpl;

    /**成功录入回款*/
    @Test(dataProvider = "user")
    public void addFinanceCase1(User accountant, User sell){
        AddFinanceCase addFinanceCase = new AddFinanceCase();
        //拿到回款编号
        GetFinanceInfo getFinance = new GetFinanceInfo();
        Finance finance = getFinance.getFinanceNo(accountant);
        addFinanceCase.setCollectionNo(finance.getCollectionNo());
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(sell);
        //拿到财务信息
        getUserInfo.getUserInfoByLoginName(accountant);
        accountant.setUsername(accountant.getUsername());
        //生成用例
        addFinanceCase.setBusinessUserId(String.valueOf(sell.getUserId()));
        addFinanceCase.setBusinessUserName(sell.getUsername());
        addFinanceCase.setBusinessCompanyName(accountant.getSecLevelDeptName());
        addFinanceCase.setCustType(0);//个人
        addFinanceCase.setCollectionDate("2019-11-20");//回款日期
        addFinanceCase.setAmount("100");//回款金额
        addFinanceCase.setContractLabel(0);//新签/续费  0 新签  1 续费
        addFinanceCase.setGatheringFinance(accountant.getUsername());
        addFinanceCase.setGatheringType(1);//收款方式 1 转账 2 现金 3 支票
        addFinanceCase.setPaymentName("测试回款公司");
        addFinanceCase.setNoticeFlag("2");//是否通知业务员 1 是 2  否
        addFinanceCase.setInvoiceNo("");
        addFinanceCase.setInvoiceFlag(0);//是否已开票 0 否 1 是
        addFinanceCase.setInvoiceDate("");
        addFinanceCase.setSuitetType("1");
        addFinanceCase.setRemark("测试回款备注");
        addFinanceCase.setAddfinanceExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addFinanceImpl.insertAddFinanceCase(addFinanceCase);
        AddFinanceCaseRes addFinanceCaseRes = addFinanceImpl.runAddFinanceCaseById(accountant,addFinanceCase.getCaseId());
        Assert.assertEquals(addFinanceCase.getAddfinanceExpect(),addFinanceCaseRes.getResult());
        //验证回款信息
        finance.setStatus(0);
        finance.setRejectStatus(0);//正常
        finance.setContractFlag(0);//未关联
        Boolean b = addFinanceImpl.checkFinanceInfo(accountant,finance,addFinanceCase);
        if(b) {
            Log.info("通过列表查询回款信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询回款信息或状态验证失败！！！");
        }
    }

    /**成功录入回款,且填入发票信息,并绑定合同*/
    @Test(dataProvider = "userContract")
    public void addFinanceCase2(User accountant, User sell, Customer customer, Contract contract, Invoice invoice){
        AddFinanceCase addFinanceCase = new AddFinanceCase();
        //拿到回款编号
        GetFinanceInfo getFinance = new GetFinanceInfo();
        Finance finance = getFinance.getFinanceNo(accountant);
        addFinanceCase.setCollectionNo(finance.getCollectionNo());
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(sell);
        //拿到财务信息
        getUserInfo.getUserInfoByLoginName(accountant);
        accountant.setUsername(accountant.getUsername());
        //生成用例
        addFinanceCase.setBusinessUserId(String.valueOf(sell.getUserId()));
        addFinanceCase.setBusinessUserName(sell.getUsername());
        addFinanceCase.setBusinessCompanyName(accountant.getSecLevelDeptName());
        addFinanceCase.setCustType(1);//企业
        addFinanceCase.setCollectionDate("2019-11-20");//回款日期
        addFinanceCase.setAmount("200");//回款金额
        addFinanceCase.setContractLabel(1);//新签/续费  0 新签  1 续费
        addFinanceCase.setGatheringFinance(accountant.getUsername());
        addFinanceCase.setGatheringType(2);//收款方式 1 转账 2 现金 3 支票
        addFinanceCase.setPaymentName(customer.getCustName());
        addFinanceCase.setNoticeFlag("1");//是否通知业务员 1 是 2  否
        addFinanceCase.setInvoiceNo(invoice.getInvoiceNo());
        addFinanceCase.setInvoiceFlag(1);//是否已开票 0 否 1 是
        addFinanceCase.setInvoiceDate(invoice.getInvoiceDate());
        addFinanceCase.setSuitetType("1");
        addFinanceCase.setRemark("测试回款备注");
        addFinanceCase.setAddfinanceExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addFinanceImpl.insertAddFinanceCase(addFinanceCase);
        AddFinanceCaseRes addFinanceCaseRes = addFinanceImpl.runAddFinanceCaseByIdNew(accountant,addFinanceCase.getCaseId());
        Assert.assertEquals(addFinanceCase.getAddfinanceExpect(),addFinanceCaseRes.getResult());
        finance.setAmount(addFinanceCase.getAmount());
        finance.setId(addFinanceCaseRes.getFinanceId());
        relevanceFinanceImpl.relevanceFinance(sell,contract,finance);
        //验证回款信息
        finance.setStatus(0);
        finance.setRejectStatus(0);
        finance.setContractFlag(1);
        Boolean b = addFinanceImpl.checkFinanceInfo(accountant,finance,addFinanceCase);
        if(b) {
            Log.info("通过列表查询回款信息与状态验证无误");
            Reporter.log("用例执行成功");
        }else {
            Log.error("通过列表查询回款信息或状态验证失败！！！");
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
        User accountant = loginImpl.login("caiwu", "m1111111");
        User sell = loginImpl.login("xiaoshou1", "m1111111");
        return new Object[][]{{accountant,sell}};
    }


    @DataProvider(name = "userContract")
    public Object[][] provider(){
        User accountant = loginImpl.login("caiwu","m1111111");
        User sell = loginImpl.login("xiaoshou","m1111111");
        Customer customer = new Customer();
        customer.setCustName("测试个人客户257");
        customer.setCustId(1400);
        GetlkmInfo getlkmInfo = new GetlkmInfo();
        getlkmInfo.getlkmInfo(sell,customer);
        Lkm lkm = customer.getLkms().get(0);
        Contract contract = signContractImpl.signContract(sell,customer,lkm,"测试套餐");
        Invoice invoice = addInvoiceImpl.addInvoice(sell,customer);
        confirmInvoiceImpl.confirmInvoice(accountant, invoice);
        return new Object[][]{{accountant,sell,customer,contract,invoice}};
    }
}
