package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.AddLinkmanCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddLinkmanCaseMapper {

    int setAddLinkmanCase(AddLinkmanCase addLinkmanCase);

    AddLinkmanCase getAddLinkmanCaseById(int caseId);

}
