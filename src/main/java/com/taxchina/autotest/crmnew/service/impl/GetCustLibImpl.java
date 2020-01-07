package com.taxchina.autotest.crmnew.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.taxchina.autotest.crmnew.service.common.GetCustLibInfo;
import com.taxchina.autotest.crmnew.service.entity.CustLib;
import com.taxchina.autotest.crmnew.service.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetCustLibImpl {

    public User getCustLib(User user){
        GetCustLibInfo getCustLibInfo = new GetCustLibInfo();
        String custLib = getCustLibInfo.getCustLib(user);
        List<CustLib> list = JSONArray.parseArray(custLib,CustLib.class);
//        System.out.println(list);
        user.setCustLibs(list);
        return user;
    }

    public User getCustLibNew(User user){
        GetCustLibInfo getCustLibInfo = new GetCustLibInfo();
        String custLib = getCustLibInfo.getCustLibNew(user);
        List<CustLib> list = JSONArray.parseArray(custLib,CustLib.class);
//        System.out.println(list);
        user.setCustLibs(list);
        return user;
    }

    public User getCustLibToUser(User user){
        GetCustLibInfo getCustLibInfo = new GetCustLibInfo();
        String custLib = getCustLibInfo.getCustLib(user);
        user.setCustlib(custLib);
        return user;
    }
    public User getCustLibToUserNew(User user){
        GetCustLibInfo getCustLibInfo = new GetCustLibInfo();
        String custLib = getCustLibInfo.getCustLibNew(user);
        user.setCustlib(custLib);
        return user;
    }
}
