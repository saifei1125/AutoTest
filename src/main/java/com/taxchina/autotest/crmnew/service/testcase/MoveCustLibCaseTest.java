package com.taxchina.autotest.crmnew.service.testcase;

import com.taxchina.autotest.crmnew.dao.entity.MoveCustLibCase;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.impl.AddMoveCustLibImpl;
import com.taxchina.autotest.crmnew.service.impl.LoginImpl;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SpringBootTest
public class MoveCustLibCaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private LoginImpl loginImpl;
    @Autowired
    private AddMoveCustLibImpl addMoveCustLibImpl;

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
        User user = loginImpl.login("saifeiFZ","Abc123123");
        Customer customer = new Customer();
        customer.setCustId(1607);//1255
        customer.setCustName("北京五八信息技术有限公司");
        return new Object[][]{{user,customer}};
    }

/*转移客户库到12月库*/
    @Test(dataProvider = "param")
    public void toMoveCase(User user,Customer customer){
        MoveCustLibCase moveCustLibCase=new MoveCustLibCase();
        moveCustLibCase.setLibId(15);
        moveCustLibCase.setLibName("12月");
        moveCustLibCase.setParams(customer.getCustId()+"");
        moveCustLibCase.setIsMainLkm(0);
        moveCustLibCase.setAddLinkmanExpect("{\"msg\":\"操作成功\",\"code\":0}");
        addMoveCustLibImpl.insert(moveCustLibCase);
        addMoveCustLibImpl.moveCustLib(user,customer);
        Reporter.log("用例执行成功");

    }


}
