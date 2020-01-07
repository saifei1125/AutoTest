package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.CheckCustCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CheckCustCaseMapper {

    int setCheckCustCase(CheckCustCase checkCustCase);

    CheckCustCase getCheckCustCaseById(int caseId);

}
