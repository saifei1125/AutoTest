package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.service.common.AppSignIn;
import com.taxchina.autotest.crmnew.service.common.GetSignInList;
import com.taxchina.autotest.crmnew.service.common.GetSignInPersonList;
import com.taxchina.autotest.crmnew.service.entity.Course;
import com.taxchina.autotest.crmnew.service.entity.SignInList;
import com.taxchina.autotest.crmnew.service.entity.SignInPersonList;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppSignInImpl {
    public void signIn(User user, Course course, String clientNumber){
        //获取列表中课程信息
        GetSignInList getSignInList = new GetSignInList();
        List<SignInList> list = getSignInList.getSignInList(user);
        //判断是否有预期课程
        for(SignInList SignInList : list){
            if(SignInList.getCourseName().equals(course.getCourseName())){
                //查询预约码对应cid
                GetSignInPersonList getSignInPersonList = new GetSignInPersonList();
                SignInPersonList signInPersonList = getSignInPersonList.getSignInPersonList(user, course, clientNumber);
                if(signInPersonList!=null) {
                    Log.info("签到前签到状态：" + signInPersonList.getReservationStatus());
                    //用cid进行签到
                    AppSignIn appSignIn = new AppSignIn();
                    appSignIn.appSignIn(user, signInPersonList);
                    //验证签到状态
                    SignInPersonList signInPersonList1 = getSignInPersonList.getSignInPersonList(user, course, clientNumber);
                    Log.info("签到后签到状态：" + signInPersonList1.getReservationStatus());
//                Assert.assertEquals(signInPersonList1.getReservationStatus(),"已签到");

                }else {
                    Log.error("预约码有误！！");
                    return;
                }
                return;
            }else {
                continue;
            }
        }
        Log.error("没有找到要预约的课程！！");
    }
}
