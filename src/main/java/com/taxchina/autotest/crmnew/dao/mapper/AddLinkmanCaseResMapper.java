package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.AddLinkmanCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddLinkmanCaseResMapper {

    int setAddLinkmanCaseRes(AddLinkmanCaseRes addLinkmanCaseRes);

}
