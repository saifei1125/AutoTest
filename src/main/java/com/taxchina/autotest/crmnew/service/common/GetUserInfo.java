package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.impl.LoginImpl;
import com.taxchina.autotest.crmnew.service.utils.EncodeConvert;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class GetUserInfo {
    //财务根据业务员姓名查询业务员id
    public User getUserInfoByUserName(User user){
        LoginImpl loginImpl = new LoginImpl();
        User jszc = loginImpl.login("jszc", "m1111111");
        String url = TEST_DOMAIN+"/system/user/list";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String userName1 = encode.urlEncoder(user.getLoginName(),"utf-8");
        String param = "deptId=&parentId=&loginName="+userName1+"&userName=&phonenumber=&status=&params%5BbeginTime%5D=&params%5BendTime%5D=&pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = HttpClientUtil.doPost(url, param, jszc.getHeaders(),"utf-8");
        Log.info("根据业务员姓名查询返回结果："+res);
        JSONObject res2 = JSONObject.parseObject(res);
        String res3 = res2.getString("rows");
        List<User> list = JSONObject.parseArray(res3,User.class);
        for (User user1 : list) {
//            Log.info(user1.getUserId() + "  " + user1.getUsername());
            if(user1.getLoginName().equals(user.getLoginName())){
                user.setUserId(user1.getUserId());
                user.setUsername(user.getLoginName());
                user.setLoginName(user1.getLoginName());
                user.setPhonenumber(user1.getPhonenumber());
            }
        }
        return user;
    }
    //根据业务员登录名查询业务员id
    public User getUserInfoByLoginName(User user){
        LoginImpl loginImpl = new LoginImpl();
        User jszc = loginImpl.login("jszc", "m1111111");
//        User user = loginImpl.login(6);
        String url = TEST_DOMAIN+"/system/user/list";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String loginName1 = encode.urlEncoder(user.getLoginName(),"utf-8");
        String param = "deptId=&parentId=&loginName="+loginName1+"&userName=&phonenumber=&status=&params%5BbeginTime%5D=&params%5BendTime%5D=&pageSize=10&pageNum=1&orderByColumn=createTime&isAsc=desc";
        String res = HttpClientUtil.doPost(url, param, jszc.getHeaders(),"utf-8");
//        Log.info(param);
        Log.info("根据业务员登录名查询返回结果："+res);
        JSONObject res2 = JSONObject.parseObject(res);
        String res3 = res2.getString("rows");
        List<User> list = JSONObject.parseArray(res3,User.class);
        for (User user1 : list) {
//            Log.info(user1.getUserId() + "  " + user1.getLoginName());
            if(user1.getLoginName().equals(user.getLoginName())){
                user.setUserId(user1.getUserId());
                user.setUsername(user1.getUsername());
                user.setPhonenumber(user1.getPhonenumber());
                user.setLoginName(user.getLoginName());
                user.setSecLevelDeptName(user1.getSecLevelDeptName());
            }
        }
        return user;
    }
}
