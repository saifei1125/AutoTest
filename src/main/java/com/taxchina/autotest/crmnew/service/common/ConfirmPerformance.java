package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.ConfirmPerformanceCase;
import com.taxchina.autotest.crmnew.service.entity.Performance;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.testng.Assert;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;
import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.doPost;


//确认业绩
public class ConfirmPerformance {
    public String confirmPerformanceByCase(User user, ConfirmPerformanceCase confirmPerformanceCase){
        String url = TEST_DOMAIN + "/crm/performance/confirmPerformance?performanceNo="+ confirmPerformanceCase.getPerformanceNo();
        String param = "";
        String response = doPost(url, param, user.getHeaders(), "utf-8");
        return response;
    }

    public void testConfirmPerformance(User user, Performance performance){
        String url = TEST_DOMAIN + "/crm/performance/confirmPerformance?performanceNo="+ performance.getPerformanceNo();
        String param = "";
        String res = doPost(url, param, user.getHeaders(), "utf-8");
//        Log.info("确认业绩请求返回结果："+ res);
        if(res != null) {
            JSONObject responseBody = JSONObject.parseObject(res);
            Assert.assertEquals(responseBody.getString("code"), "0");
            Log.info("----确认业绩成功----");
        }
    }
}
