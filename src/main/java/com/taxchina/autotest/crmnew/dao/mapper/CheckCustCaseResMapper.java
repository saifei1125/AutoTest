package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.CheckCustCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CheckCustCaseResMapper {

    int setCheckCustCaseRes(CheckCustCaseRes checkCustCaseRes);

}
