package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.LoginCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginCaseMapper {

    LoginCase getLoginCaseById(int caseId);

    int insertLoginCase(LoginCase loginCase);
}
