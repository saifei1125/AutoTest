package com.taxchina.autotest.crmnew.service.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.taxchina.autotest.crmnew.dao.entity.AddCustCase;
import com.taxchina.autotest.crmnew.dao.entity.AddCustCaseRes;
import com.taxchina.autotest.crmnew.dao.entity.CheckCustCase;
import com.taxchina.autotest.crmnew.dao.entity.CheckCustCaseRes;
import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.entity.CustLib;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.impl.AddCustImpl;
import com.taxchina.autotest.crmnew.service.impl.GetCustLibImpl;
import com.taxchina.autotest.crmnew.service.impl.LoginImpl;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class AddCustCaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AddCustImpl addCustImpl;
    @Autowired
    private LoginImpl loginImpl;

    /**成功添加个人客户的情况*/
    @Test(dataProvider = "user")
    public void addPersonalCustCase1(User user){
        //生成添加客户的用例，用户信息随机生成
        GetCustLibImpl getCustLib = new GetCustLibImpl();
        String custLib = getCustLib.getCustLibToUser(user).getCustlib();
        List<CustLib> list = JSONArray.parseArray(custLib,CustLib.class);
        //默认用第一个客户库
        CustLib lib = list.get(0);
        int libId = lib.getId();
        String libName = lib.getTitle();
        String name = "测试个人客户"+new Random().nextInt(10000);
        String phoneNo = "1500000"+(int)(Math.random()*(9999-1000+1)+1000);
        AddCustCase addCustCase = new AddCustCase();
        addCustCase.setCustName(name);
        addCustCase.setCustPhone(phoneNo);
        addCustCase.setCustType(0);
        addCustCase.setCustImplevel(1);
        addCustCase.setRegAddr("测试地址");
        addCustCase.setCustEmail(phoneNo+"@123.com");
        addCustCase.setLibId(libId);
        addCustCase.setLibName(libName);
        addCustCase.setAddcustExpect("操作成功");
        addCustImpl.insertAddPersonalCustCase(addCustCase);
        AddCustCaseRes addCustCaseRes = addCustImpl.runAddPersonalCustCaseById(user,addCustCase.getCaseId());
        String res = addCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String msg = (String) JSONPath.eval(jo,"$.msg");
        String expext = addCustCase.getAddcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,msg);
        Reporter.log("用例执行成功");
    }

    /**客户库数量已满的情况*/
    @Test(dataProvider = "user")
    public void addPersonalCustCase2(User user){
        //生成添加客户的用例，用户信息随机生成
        GetCustLibImpl getCustLib = new GetCustLibImpl();
        String custLib = getCustLib.getCustLibToUserNew(user).getCustlib();
        List<CustLib> list = JSONArray.parseArray(custLib,CustLib.class);
        //默认用第一个客户库
        int libId = -1;
        for(CustLib lib : list){
            if(lib.getTitle().equals("一人库")){
                libId  = lib.getId();
            }
        }
        String name = "测试个人客户"+new Random().nextInt(10000);
        String phoneNo = "";
        AddCustCase addCustCase = new AddCustCase();
        addCustCase.setCustName(name);
        addCustCase.setCustPhone(phoneNo);
        addCustCase.setCustType(0);
        addCustCase.setCustImplevel(1);
        addCustCase.setRegAddr("测试地址");
        addCustCase.setCustEmail(phoneNo+"@123.com");
        addCustCase.setLibId(libId);
        addCustCase.setLibName("一人库");
        addCustCase.setAddcustExpect("一人库此客户库数量已满，请选择其它库！");
        addCustImpl.insertAddPersonalCustCase(addCustCase);
        AddCustCaseRes addCustCaseRes = addCustImpl.runAddPersonalCustCaseByIdNew(user,addCustCase.getCaseId());
        String res = addCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String msg = (String) JSONPath.eval(jo,"$.msg");
        String expext = addCustCase.getAddcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(expext,msg);
        Reporter.log("用例执行成功");
    }

    /**企业客户判重-未存在的情况*/
    @Test(dataProvider = "user")
    public void checkCustNameUnique1(User user){
        CheckCustCase checkCustCase = new CheckCustCase();
        checkCustCase.setCustType(1);
        checkCustCase.setCustName("小米科技有限责任公司1");//不存在的公司
        checkCustCase.setCheckcustExpect("0");
        addCustImpl.insertCheckCustCase(user,checkCustCase);
        CheckCustCaseRes checkCustCaseRes = addCustImpl.runCheckCustCase(user,checkCustCase.getCaseId());
        String res = checkCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String code =JSONPath.eval(jo,"$.code").toString();
        String expext = checkCustCase.getCheckcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(code,expext);
        Reporter.log("用例执行成功");
    }

    /**企业客户判重-已存在的情况*/
    @Test(dataProvider = "user")
    public void checkCustNameUnique2(User user){
        CheckCustCase checkCustCase = new CheckCustCase();
        checkCustCase.setCustType(1);
        checkCustCase.setCustName("小米科技有限责任公司");//查询自己的会员
        checkCustCase.setCheckcustExpect("1");
        addCustImpl.insertCheckCustCase(user,checkCustCase);
        CheckCustCaseRes checkCustCaseRes = addCustImpl.runCheckCustCase(user,checkCustCase.getCaseId());
        String res = checkCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String code =JSONPath.eval(jo,"$.code").toString();
        String expext = checkCustCase.getCheckcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(code,expext);
        Reporter.log("用例执行成功");
    }

    /**企业客户判重-已存在的情况*/
    @Test(dataProvider = "user")
    public void checkCustNameUnique3(User user){
        CheckCustCase checkCustCase = new CheckCustCase();
        checkCustCase.setCustType(1);
        checkCustCase.setCustName("紫光集团有限公司");//查询他人的会员
        checkCustCase.setCheckcustExpect("1");
        addCustImpl.insertCheckCustCase(user,checkCustCase);
        CheckCustCaseRes checkCustCaseRes = addCustImpl.runCheckCustCase(user,checkCustCase.getCaseId());
        String res = checkCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String code =JSONPath.eval(jo,"$.code").toString();
        String expext = checkCustCase.getCheckcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(code,expext);
        Reporter.log("用例执行成功");
    }

    /**企业客户判重-已存在的情况*/
    @Test(dataProvider = "user")
    public void checkCustNameUnique4(User user){
        CheckCustCase checkCustCase = new CheckCustCase();
        checkCustCase.setCustType(1);
        checkCustCase.setCustName("北京七月在线科技有限公司");//查询本公司公海池的会员
        checkCustCase.setCheckcustExpect("1");
        addCustImpl.insertCheckCustCase(user,checkCustCase);
        CheckCustCaseRes checkCustCaseRes = addCustImpl.runCheckCustCase(user,checkCustCase.getCaseId());
        String res = checkCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String code =JSONPath.eval(jo,"$.code").toString();
        String expext = checkCustCase.getCheckcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(code,expext);
        Reporter.log("用例执行成功");
    }

    /**企业客户判重-已存在的情况*/
    @Test(dataProvider = "user")
    public void checkCustNameUnique5(User user){
        CheckCustCase checkCustCase = new CheckCustCase();
        checkCustCase.setCustType(1);
        checkCustCase.setCustName("小米之家科技有限公司");//查询其他公司公海池的会员
        checkCustCase.setCheckcustExpect("1");
        addCustImpl.insertCheckCustCase(user,checkCustCase);
        CheckCustCaseRes checkCustCaseRes = addCustImpl.runCheckCustCase(user,checkCustCase.getCaseId());
        String res = checkCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String code =JSONPath.eval(jo,"$.code").toString();
        String expext = checkCustCase.getCheckcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(code,expext);
        Reporter.log("用例执行成功");
    }

    /**企业客户判重-已存在的情况*/
    @Test(dataProvider = "user")
    public void checkCustNameUnique6(User user){
        CheckCustCase checkCustCase = new CheckCustCase();
        checkCustCase.setCustType(1);
        checkCustCase.setCustName("阿里巴巴(中国)网络技术有限公司");//查询离职员工的会员
        checkCustCase.setCheckcustExpect("1");
        addCustImpl.insertCheckCustCase(user,checkCustCase);
        CheckCustCaseRes checkCustCaseRes = addCustImpl.runCheckCustCase(user,checkCustCase.getCaseId());
        String res = checkCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String code =JSONPath.eval(jo,"$.code").toString();
        String expext = checkCustCase.getCheckcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(code,expext);
        Reporter.log("用例执行成功");
    }

    /**企业客户判重-已存在的情况*/
    @Test(dataProvider = "user")
    public void checkCustNameUnique7(User user){
        CheckCustCase checkCustCase = new CheckCustCase();
        checkCustCase.setCustType(1);
        checkCustCase.setCustName("山东七月网网络科技有限公司");//查询已删除员工的会员
        checkCustCase.setCheckcustExpect("1");
        addCustImpl.insertCheckCustCase(user,checkCustCase);
        CheckCustCaseRes checkCustCaseRes = addCustImpl.runCheckCustCase(user,checkCustCase.getCaseId());
        String res = checkCustCaseRes.getResult();
        JSONObject jo = JSON.parseObject(res);
        String code =JSONPath.eval(jo,"$.code").toString();
        String expext = checkCustCase.getCheckcustExpect();
        Log.info("预期结果："+expext);
        Assert.assertEquals(code,expext);
        Reporter.log("用例执行成功");
    }

    /**通过关键字模糊搜索，成功添加企业客户*/
    @Test(dataProvider = "user")
    public void addCustByLikeSearchCustName(User user){
        Customer customer = addCustImpl.addBusinessCustByLikeSearchCustName(user,"北京公司");
        if(customer!=null) {
            Log.info("---新加客户id：" + customer.getCustId() + "-----客户名：" + customer.getCustName());
            Assert.assertNotEquals(0,customer.getCustId());
            Reporter.log("用例执行成功");
        }else {
            Log.error("企业未添加成功！！！");
            Reporter.log("用例执行失败");
        }
    }

    /**通过关键字模糊搜索，未查询到企业*/
    @Test(dataProvider = "user")
    public void addCustByLikeSearchCustName1(User user){
        Customer customer = addCustImpl.addBusinessCustByLikeSearchCustName(user,"afaghalkghalk");
        Assert.assertNull(customer);
        Reporter.log("用例执行成功");
    }

    /**通过关键字模糊搜索，查询到的企业都已存在库中*/
    @Test(dataProvider = "user")
    public void addCustByLikeSearchCustName2(User user){
        Customer customer = addCustImpl.addBusinessCustByLikeSearchCustName(user,"测试");
        Assert.assertNull(customer);
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

    @DataProvider(name = "user")
    public Object[][] dataProvider(){
        User user = loginImpl.login("xiaoshou","m1111111");
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);

        return new Object[][]{{user}};
    }


}
