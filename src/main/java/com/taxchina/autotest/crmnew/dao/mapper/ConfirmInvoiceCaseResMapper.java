package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.ConfirmInvoiceCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ConfirmInvoiceCaseResMapper {

    int setConfirmInvoiceCaseRes(ConfirmInvoiceCaseRes confirmInvoiceCaseRes);

}
