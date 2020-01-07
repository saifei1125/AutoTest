package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.LoginCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginCaseResMapper {

    int setLoginCaseRes(LoginCaseRes loginCaseRes);

}
