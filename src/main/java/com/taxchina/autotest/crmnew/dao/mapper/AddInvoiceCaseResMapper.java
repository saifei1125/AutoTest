package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.AddInvoiceCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddInvoiceCaseResMapper {

    int setAddInvoiceCaseRes(AddInvoiceCaseRes addInvoiceCaseRes);

}
