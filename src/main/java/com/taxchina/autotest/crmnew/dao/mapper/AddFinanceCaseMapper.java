package com.taxchina.autotest.crmnew.dao.mapper;


import com.taxchina.autotest.crmnew.dao.entity.AddFinanceCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddFinanceCaseMapper {

    int setAddFinanceCase(AddFinanceCase addFinanceCase);

    AddFinanceCase getAddFinanceCaseById(int caseId);

}
