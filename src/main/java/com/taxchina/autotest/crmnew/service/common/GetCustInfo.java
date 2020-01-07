package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.EncodeConvert;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class GetCustInfo {
    //调用企查查查询客户信息
    public Customer getCustInfo(User user, String custName){
        String url = TEST_DOMAIN + "/crm/customer/getCustInfo";
        EncodeConvert encodeConvert = new EncodeConvert();
        String param = "custName="+encodeConvert.urlEncoder(custName,"utf-8");
        String res = HttpClientUtil.doPost(url,param,user.getHeaders(),"utf-8");
        String data = JSONObject.parseObject(res).getString("data");
        Customer resCustomer = JSON.parseObject(data,Customer.class);
        return resCustomer;
    }

    /**根据客户名，查询客户列表中的客户信息*/
    public String getCustListInfoByName(User user,String custName){
        //get潜客列表
        String url = TEST_DOMAIN+"/crm/customer/list?memberFlag=0";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName1 = encode.urlEncoder(custName, "utf-8");
        String param = "libId=&libName=&custOwnerId=&custQuitOwnerIds=&custProvince=&custCity=&custTown=&isApproved=&custImplevel=&custUserlevel=&membershipTermFlag=&membershipFlag=&custIndustry=&custName="+custName1+"&params%5BbeginTime%5D=&params%5BendTime%5D=&pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(), "utf-8");
        return res;
    }



    //通过客户name找到客户id
    public int getCustId(User user, String name) {
        //get潜客列表
        String checkUrl = TEST_DOMAIN+"/crm/customer/list?memberFlag=0";
        String param = "pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        Map<String,String> headers = user.getHeaders();
        String res = HttpClientUtil.doPost(checkUrl, param, headers, "utf-8");
        Log.info(res);
        JSONObject jo = JSONObject.parseObject(res);
        //JSONArray方式
        JSONArray rows = jo.getJSONArray("rows");
        Map<Integer,String> cust = new HashMap<Integer,String>();
        if(rows.size()>0){
            for(int i=0;i<rows.size();i++){
                JSONObject job = rows.getJSONObject(i);
                cust.put(Integer.parseInt(job.get("custId").toString()),job.get("custName").toString());
            }
        }
        Set<Integer> set = cust.keySet();
        for(Integer id : set){
            String sname = cust.get(id);
//            Log.info(id +":"+ sname);
            if(sname.equals(name)){
                return id;
            }
        }
        return -1;
    }
}
