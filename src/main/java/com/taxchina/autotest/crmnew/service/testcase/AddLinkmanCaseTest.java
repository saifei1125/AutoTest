package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.dao.entity.AddLinkmanCase;
import com.taxchina.autotest.crmnew.dao.entity.AddLinkmanCaseRes;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.impl.AddLinkmanImpl;
import com.taxchina.autotest.crmnew.service.impl.LoginImpl;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

@SpringBootTest
public class AddLinkmanCaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private LoginImpl loginImpl;
    @Autowired
    private AddLinkmanImpl addLinkmanImpl;
    @Test(dataProvider = "param")
    /**全部填写，添加成功的情况*/
    public void addLinkmanCase1(User user, Customer customer){
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName("全部填写用例");
        addLinkmanCase.setLinkmanSex(1);
        addLinkmanCase.setLinkmanMobile("1510111"+(int)(Math.random()*(9999-1000+1)+1000));
        addLinkmanCase.setLinkmanTel("010"+(int)(Math.random()*(999999-100000+1)+100000));
        addLinkmanCase.setLinkmanAddr("测试地址");
        addLinkmanCase.setLinkmanPostion("经理");
        addLinkmanCase.setLinkmanWechat("123123");
        addLinkmanCase.setLinkmanQq("456456");
        addLinkmanCase.setLinkmanEmail("123@123.com");
        addLinkmanCase.setLinkmanRemake("测试备注");
        addLinkmanCase.setIsMainLkm(1);
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addLinkmanImpl.insertAddLinkmanCase(addLinkmanCase);
        AddLinkmanCaseRes addLinkmanCaseRes = addLinkmanImpl.runAddLinkmanCaseById(user,customer,addLinkmanCase.getCaseId());
        Assert.assertEquals(addLinkmanCaseRes.getResult(),addLinkmanCase.getAddLinkmanExpect());
        Reporter.log("用例执行成功");
    }

    /**未填联系信息，添加不成功的情况*/
    @Test(dataProvider = "param")
    public void addLinkmanCase2(User user,Customer customer){
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName("添加失败用例");
        addLinkmanCase.setLinkmanSex(1);
        addLinkmanCase.setLinkmanMobile("");
        addLinkmanCase.setLinkmanTel("");
        addLinkmanCase.setLinkmanAddr("测试地址");
        addLinkmanCase.setLinkmanPostion("经理");
        addLinkmanCase.setLinkmanWechat("");
        addLinkmanCase.setLinkmanQq("");
        addLinkmanCase.setLinkmanEmail("");
        addLinkmanCase.setLinkmanRemake("测试备注");
        addLinkmanCase.setIsMainLkm(0);
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"操作失败\",\"code\":1}");
        addLinkmanImpl.insertAddLinkmanCase(addLinkmanCase);
        AddLinkmanCaseRes addLinkmanCaseRes =addLinkmanImpl.runAddLinkmanCaseById(user,customer,addLinkmanCase.getCaseId());
        Assert.assertEquals(addLinkmanCaseRes.getResult(),addLinkmanCase.getAddLinkmanExpect());
        Reporter.log("用例执行成功");
    }

    /**填写手机号，添加成功的情况*/
    @Test(dataProvider = "param")
    public void addLinkmanCase3(User user,Customer customer){
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName("填手机号用例");
        addLinkmanCase.setLinkmanSex(0);
        addLinkmanCase.setLinkmanMobile("1510111"+(int)(Math.random()*(9999-1000+1)+1000));
        addLinkmanCase.setLinkmanTel("");
        addLinkmanCase.setLinkmanAddr("");
        addLinkmanCase.setLinkmanPostion("");
        addLinkmanCase.setLinkmanWechat("");
        addLinkmanCase.setLinkmanQq("");
        addLinkmanCase.setLinkmanEmail("");
        addLinkmanCase.setLinkmanRemake("");
        addLinkmanCase.setIsMainLkm(0);
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addLinkmanImpl.insertAddLinkmanCase(addLinkmanCase);
        AddLinkmanCaseRes addLinkmanCaseRes =addLinkmanImpl.runAddLinkmanCaseById(user,customer,addLinkmanCase.getCaseId());
        Assert.assertEquals(addLinkmanCaseRes.getResult(),addLinkmanCase.getAddLinkmanExpect());
        Reporter.log("用例执行成功");
    }

    /**填写座机，添加成功的情况*/
    @Test(dataProvider = "param")
    public void addLinkmanCase4(User user,Customer customer){
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName("填座机用例");
        addLinkmanCase.setLinkmanSex(0);
        addLinkmanCase.setLinkmanMobile("");
        addLinkmanCase.setLinkmanTel("010"+(int)(Math.random()*(999999-100000+1)+100000));
        addLinkmanCase.setLinkmanAddr("测试地址");
        addLinkmanCase.setLinkmanPostion("经理");
        addLinkmanCase.setLinkmanWechat("");
        addLinkmanCase.setLinkmanQq("");
        addLinkmanCase.setLinkmanEmail("");
        addLinkmanCase.setLinkmanRemake("测试备注");
        addLinkmanCase.setIsMainLkm(0);
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addLinkmanImpl.insertAddLinkmanCase(addLinkmanCase);
        AddLinkmanCaseRes addLinkmanCaseRes =addLinkmanImpl.runAddLinkmanCaseById(user,customer,addLinkmanCase.getCaseId());
        Assert.assertEquals(addLinkmanCaseRes.getResult(),addLinkmanCase.getAddLinkmanExpect());
        Reporter.log("用例执行成功");
    }

    /**填写微信号，添加成功的情况*/
    @Test(dataProvider = "param")
    public void addLinkmanCase5(User user,Customer customer){
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName("填微信用例");
        addLinkmanCase.setLinkmanSex(0);
        addLinkmanCase.setLinkmanMobile("");
        addLinkmanCase.setLinkmanTel("");
        addLinkmanCase.setLinkmanAddr("测试地址");
        addLinkmanCase.setLinkmanPostion("经理");
        addLinkmanCase.setLinkmanWechat("123123");
        addLinkmanCase.setLinkmanQq("");
        addLinkmanCase.setLinkmanEmail("");
        addLinkmanCase.setLinkmanRemake("测试备注");
        addLinkmanCase.setIsMainLkm(0);
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addLinkmanImpl.insertAddLinkmanCase(addLinkmanCase);
        AddLinkmanCaseRes addLinkmanCaseRes =addLinkmanImpl.runAddLinkmanCaseById(user,customer,addLinkmanCase.getCaseId());
        Assert.assertEquals(addLinkmanCaseRes.getResult(),addLinkmanCase.getAddLinkmanExpect());
        Reporter.log("用例执行成功");
    }

    /**填写QQ号，添加成功的情况*/
    @Test(dataProvider = "param")
    public void addLinkmanCase6(User user,Customer customer){
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName("填QQ用例");
        addLinkmanCase.setLinkmanSex(0);
        addLinkmanCase.setLinkmanMobile("");
        addLinkmanCase.setLinkmanTel("");
        addLinkmanCase.setLinkmanAddr("测试地址");
        addLinkmanCase.setLinkmanPostion("经理");
        addLinkmanCase.setLinkmanWechat("");
        addLinkmanCase.setLinkmanQq("456456");
        addLinkmanCase.setLinkmanEmail("");
        addLinkmanCase.setLinkmanRemake("测试备注");
        addLinkmanCase.setIsMainLkm(0);
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addLinkmanImpl.insertAddLinkmanCase(addLinkmanCase);
        AddLinkmanCaseRes addLinkmanCaseRes =addLinkmanImpl.runAddLinkmanCaseById(user,customer,addLinkmanCase.getCaseId());
        Assert.assertEquals(addLinkmanCaseRes.getResult(),addLinkmanCase.getAddLinkmanExpect());
        Reporter.log("用例执行成功");
    }

    /**填写邮箱号，添加成功的情况*/
    @Test(dataProvider = "param")
    public void addLinkmanCase7(User user,Customer customer){
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName("填邮箱用例");
        addLinkmanCase.setLinkmanSex(0);
        addLinkmanCase.setLinkmanMobile("");
        addLinkmanCase.setLinkmanTel("");
        addLinkmanCase.setLinkmanAddr("测试地址");
        addLinkmanCase.setLinkmanPostion("经理");
        addLinkmanCase.setLinkmanWechat("");
        addLinkmanCase.setLinkmanQq("");
        addLinkmanCase.setLinkmanEmail("123@123.com");
        addLinkmanCase.setLinkmanRemake("测试备注");
        addLinkmanCase.setIsMainLkm(0);
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addLinkmanImpl.insertAddLinkmanCase(addLinkmanCase);
        AddLinkmanCaseRes addLinkmanCaseRes =addLinkmanImpl.runAddLinkmanCaseById(user,customer,addLinkmanCase.getCaseId());
        Assert.assertEquals(addLinkmanCaseRes.getResult(),addLinkmanCase.getAddLinkmanExpect());
        Reporter.log("用例执行成功");
    }

    /**未填联系人姓名，添加不成功的情况*/
    @Test(dataProvider = "param")
    public void addLinkmanCase8(User user,Customer customer){
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName("");
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"请填写联系人姓名！\",\"code\":500}");
        addLinkmanImpl.insertAddLinkmanCase(addLinkmanCase);
        AddLinkmanCaseRes addLinkmanCaseRes =addLinkmanImpl.runAddLinkmanCaseById(user,customer,addLinkmanCase.getCaseId());
        Assert.assertEquals(addLinkmanCaseRes.getResult(),addLinkmanCase.getAddLinkmanExpect());
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

    @DataProvider(name = "param")
    public Object[][] dataProvider(){
        User user = loginImpl.login("xiaoshou","m1111111");
        Customer customer = new Customer();
        customer.setCustId(1332);//1255
        customer.setCustName("测试个人客户46");
        return new Object[][]{{user,customer}};
    }

}
