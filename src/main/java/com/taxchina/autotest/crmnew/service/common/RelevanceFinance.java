package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Contract;
import com.taxchina.autotest.crmnew.service.entity.Finance;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.testng.Assert;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;
import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.doPost;

//合同绑定回款
public class RelevanceFinance {
    public void testRelevanceFinance(User user, Contract contract, Finance finance){
        String url = TEST_DOMAIN + "/crm/contract/relevanceCashBack";
        String param = "id="+finance.getId()+"&collectionNo="+finance.getCollectionNo()+"&contractNo="+contract.getContractNo()+"&amount="+finance.getAmount()+"";
        String res = doPost(url, param, user.getHeaders(), "utf-8");
        if(res != null) {
            JSONObject responseBody = JSONObject.parseObject(res);
            Assert.assertEquals(responseBody.getString("code"), "0");
            Log.info("----回款绑定成功-----");
        }
    }
}
