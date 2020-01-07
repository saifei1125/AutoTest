package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.service.common.RemoveReservation;
import com.taxchina.autotest.crmnew.service.entity.Course;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

/**取消约课*/
@Service
public class RemoveReservationImpl {

    public String removeReservation(User user,Customer customer,Course course){
        RemoveReservation removeReservation = new RemoveReservation();
        String res = removeReservation.removeReservation(user,course,customer);
        return res;
    }
    public void removeReservation(User user, Customer customer){
        Course course = new Course();
        course.setCourseId("KCBH-201910281437394968");
        course.setCourseName("黑龙江--自动化测试课程");
        RemoveReservation removeReservation = new RemoveReservation();
        String res = removeReservation.removeReservation(user,course,customer);
//        String res = getEntity(response,"utf-8");
//        Log.info("取消约课结果："+res);
//        try {
//            response.close();
//        } catch (IOException e) {
//            Log.error("出错啦！！！错误信息："+e.getMessage());
//        }
    }
}
