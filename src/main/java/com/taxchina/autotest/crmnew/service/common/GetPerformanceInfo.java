package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Finance;
import com.taxchina.autotest.crmnew.service.entity.Performance;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;

import java.util.List;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;
import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.doPost;

//根据回款编号查找对应业绩编号
public class GetPerformanceInfo {
    public Performance getPerformanceNo(User user , Finance finance){
        Performance performance = new Performance();
        String url = TEST_DOMAIN + "/crm/performance/list";
        String param = "pageSize=50&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = doPost(url, param, user.getHeaders(), "utf-8");
        JSONObject res1 = JSONObject.parseObject(res);
        String res2 = res1.getString("rows");
//        Log.info(res2);
        List<Performance> list = JSONObject.parseArray(res2,Performance.class);
        for (Performance performance1 : list) {
            if(performance1.getCollections().equals(finance.getCollectionNo())){
//                Log.info("---业绩编号:"+performance1.getPerformanceNo());
                performance = performance1;
            }
        }
        return performance;
    }
}
