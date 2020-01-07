package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.dao.entity.MoveCustLibCase;
import com.taxchina.autotest.crmnew.dao.mapper.AddMoveCustLibCaseMapper;
import com.taxchina.autotest.crmnew.service.common.MoveCustLib;
import com.taxchina.autotest.crmnew.service.entity.CustLib;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddMoveCustLibImpl {
    @Autowired
    private AddMoveCustLibCaseMapper addMoveCustLibCaseMapper;
    @Autowired
    private GetCustLibImpl getCustLibImpl;

    //将用例插入到数据库中
    public MoveCustLibCase insert(MoveCustLibCase moveCustLibCase){
        addMoveCustLibCaseMapper.setAddMoveCustLibCase(moveCustLibCase);
        Log.info("转移客户库用例id:"+moveCustLibCase.getCaseId());
        return moveCustLibCase;
    }

    //执行http连接具体方法
    public CustLib moveCustLib(User user, Customer customer){
        getCustLibImpl.getCustLib(user).getCustlib();
        CustLib custLib=new CustLib();
        custLib.setTitle("12月");
        custLib.setId(15);
        MoveCustLib moveCustLib=new MoveCustLib();
        moveCustLib.moveCustLib(user,customer,custLib);
        return custLib;
    }
}
