package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.MoveCustLibCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddMoveCustLibCaseMapper {
    int setAddMoveCustLibCase(MoveCustLibCase moveCustLibCase);
    MoveCustLibCase getMoveCustLibCaseById(int caseId);
}
