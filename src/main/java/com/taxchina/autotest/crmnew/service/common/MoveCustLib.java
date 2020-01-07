package com.taxchina.autotest.crmnew.service.common;

import com.taxchina.autotest.crmnew.service.entity.CustLib;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.impl.LoginImpl;
import com.taxchina.autotest.crmnew.service.utils.EncodeConvert;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.testng.annotations.Test;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class MoveCustLib {
        public CustLib moveCustLib(User user, Customer customer, CustLib custLib) {
        String url=TEST_DOMAIN + "/crm/customer/doMovie";
        EncodeConvert<String> encode = new EncodeConvert<>();
        int    libId=custLib.getId();
        String libName=custLib.getTitle();
        int    params=customer.getCustId();
        String param="libId="+libId+"&libName="+libName+"&params[ids]="+params;
        String res= HttpClientUtil.doPost(url,param,user.getHeaders(),"utf-8");
        Log.info("转移客户库请求返回结果："+res);
        return custLib;
    }

    @Test
    public void testMoveCustLib(){
       LoginImpl login=new LoginImpl();
       CustLib custLib=new CustLib();
       Customer customer=new Customer();
       MoveCustLib moveCustLib=new MoveCustLib();
       customer.setCustId(1607);
       custLib.setId(15);
       custLib.setTitle("12月");
       User user= login.login("saifeiFZ","Abc123123");
       moveCustLib.moveCustLib(user,customer,custLib);



    }
}
