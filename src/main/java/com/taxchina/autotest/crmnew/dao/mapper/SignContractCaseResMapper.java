package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.SignContractCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SignContractCaseResMapper {

    int setSignContractCaseRes(SignContractCaseRes signContractCaseRes);

}
