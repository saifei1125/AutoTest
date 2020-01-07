package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Lkm;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;

import java.util.List;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

//获取联系人
public class GetlkmInfo {
    public Customer getlkmInfo(User user, Customer customer){
        String url = TEST_DOMAIN+"/crm/linkman/getLkmsByCustId";
        Map<String,String> headers = user.getHeaders();
        String param = "customerId="+customer.getCustId();
        String res = HttpClientUtil.doPost(url, param, headers,"utf-8");
        Log.info("获取联系人请求返回结果："+res);
        //直接转对象
        List<Lkm> lkmList = JSONObject.parseArray(res,Lkm.class);
        customer.setLkms(lkmList);
        return customer;
    }
}
