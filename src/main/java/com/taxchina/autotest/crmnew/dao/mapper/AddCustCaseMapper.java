package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.AddCustCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddCustCaseMapper {

    AddCustCase getAddCustCaseById(int caseId);

    int setAddCustCase(AddCustCase addCustCase);

}
