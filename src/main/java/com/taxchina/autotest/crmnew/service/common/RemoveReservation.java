package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Course;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.ReservationList;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.apache.http.HttpResponse;

import java.util.List;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

/**取消约课*/
public class RemoveReservation {
    public String removeReservation(User user, Course course, Customer customer){
        String url = TEST_DOMAIN+"/crm/course/removeReservation";
        Map<String,String> headers = user.getHeaders();
        headers.put("Content-Type","application/json");
        GetReservationInfo getReservationInfo = new GetReservationInfo();
        List<ReservationList> list = getReservationInfo.getReservationInfo(user,course);
        HttpResponse response = null;
        for(ReservationList reservation : list){
            if(reservation.getCompanyName().equals(customer.getCustName())){
                JSONObject json = new JSONObject();
                json.put("id",reservation.getId());
                json.put("companyId",reservation.getCompanyId());
                json.put("contractProductId",reservation.getContractProductId());
                json.put("contractNo",reservation.getContractNo());
                Log.info("取消约课参数："+json.toJSONString());
                String res = HttpClientUtil.doPost(url,json.toJSONString(),headers,"utf-8");
                return res;
            }
        }
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        return null;
    }
}
