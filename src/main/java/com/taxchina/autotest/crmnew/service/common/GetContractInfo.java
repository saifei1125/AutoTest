package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Contract;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;

import java.util.List;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class GetContractInfo {
    public List<Contract> getContractInfo(User user){
        String url = TEST_DOMAIN + "/crm/contract/list";
        String param = "pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        Log.info("获取合同信息请求返回结果："+res);
        JSONObject jsonObject = JSON.parseObject(res);
        String rows = jsonObject.getString("rows");
        List<Contract> contractList = JSON.parseArray(rows,Contract.class);
        return contractList;
    }
}
