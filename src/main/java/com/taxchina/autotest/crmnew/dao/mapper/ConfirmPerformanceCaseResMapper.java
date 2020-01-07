package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.ConfirmPerformanceCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ConfirmPerformanceCaseResMapper {

    int setConfirmPerformanceCaseRes(ConfirmPerformanceCaseRes confirmPerformanceCaseRes);

}
