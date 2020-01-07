package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.testng.Assert;

import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class SubmitContract {
    public void testSubmitContract(User user, String contractNp){
        String url = TEST_DOMAIN + "/crm/contract/signContractSubmit";
        Map<String,String > headers = user.getHeaders();
        String param = "contractNo="+contractNp;
        String res = HttpClientUtil.doPost(url, param, headers, "utf-8");
        Log.info("提交合同请求返回结果：" + res);
        JSONObject responseBody = JSONObject.parseObject(res);
        Assert.assertEquals(responseBody.getString("code"), "0");
    }
}
