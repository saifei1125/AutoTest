package com.taxchina.autotest.crmnew.dao.mapper;


import com.taxchina.autotest.crmnew.dao.entity.AddFinanceCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddFinanceCaseResMapper {

    int setAddFinanceCaseRes(AddFinanceCaseRes addFinanceCaseRes);

}
