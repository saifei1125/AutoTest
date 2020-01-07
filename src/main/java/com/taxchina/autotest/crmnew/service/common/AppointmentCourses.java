package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.AppointmentCoursesCase;
import com.taxchina.autotest.crmnew.dao.mapper.AppointmentCoursesCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.AppointmentCoursesCaseResMapper;
import com.taxchina.autotest.crmnew.service.entity.Course;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Lkm;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;


public class AppointmentCourses {
    public String appointmentCoursesByCase(User user,AppointmentCoursesCase appointmentCoursesCase){
        String url = TEST_DOMAIN+"/crm/course/appointmentCourses";
        Map<String,String> headers = user.getHeaders();
        headers.put("Content-Type","application/json");
        List<JSONObject> list = new ArrayList<JSONObject>(){};
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lkmName",appointmentCoursesCase.getLkmName());
        jsonObject.put("lkmMobile",appointmentCoursesCase.getLkmMobile());
        list.add(jsonObject);
//        Log.info("约课联系人信息："+list.toString());
        JSONObject param = new JSONObject();
        param.put("linkMap",list.toString());
        param.put("courseName",appointmentCoursesCase.getCourseName());
        param.put("couresId",appointmentCoursesCase.getCourseId());
        param.put("companyId",String.valueOf(appointmentCoursesCase.getCompanyId()));
        param.put("companyName",appointmentCoursesCase.getCompanyName());
        Log.info("约课请求参数----"+param.toJSONString());
        String res = HttpClientUtil.doPost(url,param.toJSONString(),headers,"utf-8");
        Log.info("约课返回结果："+res);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        return res;
    }
    public void appointmentCourses(User user, Customer customer, Course course){
        String url = TEST_DOMAIN+"/crm/course/appointmentCourses";
        Map<String,String> headers = user.getHeaders();
        headers.put("Content-Type","application/json");
        List<Lkm> Lkms= customer.getLkms();
        Lkm lkm = Lkms.get(0);
        List<JSONObject> list = new ArrayList<JSONObject>(){};
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lkmName",lkm.getLkmName());
        jsonObject.put("lkmMobile",lkm.getLkmMobile());
        list.add(jsonObject);
        Log.info("约课联系人信息："+list.toString());
        JSONObject param = new JSONObject();
        param.put("linkMap",list.toString());
        param.put("courseName",course.getCourseName());
        param.put("couresId",course.getCourseId());
        param.put("companyId",String.valueOf(customer.getCustId()));
        param.put("companyName",customer.getCustName());
        Log.info("约课请求参数----"+param.toJSONString());
        String res = HttpClientUtil.doPost(url,param.toJSONString(),headers,"utf-8");
        Log.info("约课返回结果："+res);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
    }
}
