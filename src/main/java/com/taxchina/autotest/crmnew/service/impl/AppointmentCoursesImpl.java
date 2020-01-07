package com.taxchina.autotest.crmnew.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.AppointmentCoursesCase;
import com.taxchina.autotest.crmnew.dao.entity.AppointmentCoursesCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.AppointmentCoursesCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.AppointmentCoursesCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.AppointmentCourses;
import com.taxchina.autotest.crmnew.service.common.GetReservationInfo;
import com.taxchina.autotest.crmnew.service.entity.Course;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.ReservationList;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**约课*/
@Service
public class AppointmentCoursesImpl {
    @Autowired
    AppointmentCoursesCaseMapper appointmentCoursesCaseMapper;
    @Autowired
    AppointmentCoursesCaseResMapper appointmentCoursesCaseResMapper;

    public AppointmentCoursesCase insertAppointmentCoursesCase(AppointmentCoursesCase appointmentCoursesCase){
        appointmentCoursesCaseMapper.setAppointmentCoursesCase(appointmentCoursesCase);
        return appointmentCoursesCase;
    }
    public AppointmentCoursesCaseRes runAppointmentCoursesCase(User user,int caseId){
        AppointmentCoursesCase appointmentCoursesCase = appointmentCoursesCaseMapper.getAppointmentCoursesCaseById(caseId);
        AppointmentCourses appointmentCourses = new AppointmentCourses();
        String res = appointmentCourses.appointmentCoursesByCase(user,appointmentCoursesCase);
        Log.info("约课请求返回结果：" + res);
        AppointmentCoursesCaseRes appointmentCoursesCaseRes = new AppointmentCoursesCaseRes();
        //获取预约码
        GetReservationInfo getReservationInfo = new GetReservationInfo();
        List<ReservationList> list = getReservationInfo.getReservationInfoByCase(user,appointmentCoursesCase);
        if(list!=null){
            list.forEach((v)->{
                if(v.getCompanyName().equals(appointmentCoursesCase.getCompanyName())){
                    appointmentCoursesCaseRes.setInvitationCode(v.getInvitationCode());
                }
            });
        }else {
            Log.error("没有找到预约信息");
        }
        appointmentCoursesCaseRes.setCaseId(appointmentCoursesCase.getCaseId());
        appointmentCoursesCaseRes.setResult(res);
        int insertRes = appointmentCoursesCaseResMapper.setAppointmentCoursesCaseRes(appointmentCoursesCaseRes);
        if (insertRes == 1) {
            Log.info("添加约课结果保存成功");
        } else {
            Log.info("添加约课结果保存失败");
        }
        return appointmentCoursesCaseRes;
    }
    //查询指定用户的剩余可用次数
    public int getCustServUsableNum(User user,String custName,int membershipFlag){
        GetReservationInfo getReservationInfo = new GetReservationInfo();
        List<JSONObject> list  = getReservationInfo.getChooseCourseCustList(user,membershipFlag,custName);
        int num = 0;
        if(list!=null){
            for(JSONObject jsonObject : list){
                if(jsonObject.getString("custName").equals(custName)){
                    num = jsonObject.getIntValue("custServUsableNum");
                    return num;
                }
            }
        }
        return 0;
    }

    public ReservationList appointmentCourses(User user, Customer customer, Course course){
        //约课
        AppointmentCourses appointmentCourses = new AppointmentCourses();
        appointmentCourses.appointmentCourses(user,customer,course);
        //获取预约码
        GetReservationInfo getReservationInfo = new GetReservationInfo();
        List<ReservationList> list = getReservationInfo.getReservationInfo(user,course);
        for(ReservationList reservation : list) {
            if (reservation.getCompanyName().equals(customer.getCustName())) {
                return reservation;
            }
        }
        return null;
    }
}
