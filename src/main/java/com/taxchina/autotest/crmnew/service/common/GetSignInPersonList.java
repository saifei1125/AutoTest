package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Course;
import com.taxchina.autotest.crmnew.service.entity.SignInPersonList;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class GetSignInPersonList {
    public SignInPersonList getSignInPersonList(User user, Course course, String clientNumber){
        String url = TEST_DOMAIN+"/api/course/signInPersonList";
        JSONObject param = new JSONObject();
        param.put("courseId",course.getCourseId());
        param.put("clientNumber",clientNumber);
        param.put("companyId","");
        param.put("courseName","");
        param.put("endTime","");
        param.put("startTime","");
        param.put("pageNum","1");
        param.put("pageSize","1000");
        param.put("partCompany","");
//        Log.info(param.toJSONString());
        user.getHeaders().put("Content-Type","application/json; charset=UTF-8");
        String httpResponse = HttpClientUtil.doPost(url,param.toJSONString(),user.getHeaders(),"utf-8");

        JsonPath jsonPath = new JsonPath(httpResponse);
        int code = jsonPath.getInt("code");
        if(code==0){
//            JSONObject jsonObject = JSON.parseObject(httpResponse);
//            String data1 = jsonObject.getString("data");
//            Log.info(httpResponse);
//            Log.info(data1);
//            List<SignInPersonList> list = JSON.parseArray(data1,SignInPersonList.class);
            List<SignInPersonList> list1 = jsonPath.getList("data",SignInPersonList.class);
            return list1.get(0);
        }else {
            Log.error(httpResponse);
            return null;
        }
    }
}
