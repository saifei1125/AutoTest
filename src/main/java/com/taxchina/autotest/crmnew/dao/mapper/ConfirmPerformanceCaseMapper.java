package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.ConfirmPerformanceCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ConfirmPerformanceCaseMapper {

    int setConfirmPerformanceCase(ConfirmPerformanceCase confirmPerformanceCase);

}
