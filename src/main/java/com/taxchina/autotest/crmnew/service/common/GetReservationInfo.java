package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.AppointmentCoursesCase;
import com.taxchina.autotest.crmnew.service.entity.Course;
import com.taxchina.autotest.crmnew.service.entity.ReservationList;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.EncodeConvert;

import java.util.List;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;
import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.doPost;

public class GetReservationInfo {
    public List<ReservationList> getReservationInfoByCase(User user, AppointmentCoursesCase appointmentCoursesCase){
        String url = TEST_DOMAIN+"/crm/course/reservationList?courseId="+appointmentCoursesCase.getCourseId();
        String param = "pageSize=50&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = doPost(url, param, user.getHeaders(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(res);
        String rows = jsonObject.getString("rows");
        List<ReservationList> list = JSONArray.parseArray(rows,ReservationList.class);
        return list;
    }

    public List<ReservationList> getReservationInfo(User user, Course course){
        String url = TEST_DOMAIN+"/crm/course/reservationList?courseId="+course.getCourseId();
        String param = "pageSize=50&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = doPost(url, param, user.getHeaders(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(res);
        String rows = jsonObject.getString("rows");
        List<ReservationList> list = JSONArray.parseArray(rows,ReservationList.class);
        return list;
    }

    public List getChooseCourseCustList(User user,int membershipFlag,String custName) {
        String param = "";
        String url = TEST_DOMAIN + "/crm/customer/chooseCourseCustList?membershipFlag=" + membershipFlag;
        if (custName != null) {
            EncodeConvert encodeConvert = new EncodeConvert();
            String custname = encodeConvert.urlEncoder(custName, "utf-8");
            param = "custName=" + custname + "&pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        } else {
            param = "pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        }
        String res = doPost(url, param, user.getHeaders(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(res);
        String rows = jsonObject.getString("rows");
        JSONArray list = JSONArray.parseArray(rows);
        return list;
    }
}
