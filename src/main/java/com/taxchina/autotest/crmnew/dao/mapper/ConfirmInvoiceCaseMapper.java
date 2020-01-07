package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.ConfirmInvoiceCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ConfirmInvoiceCaseMapper {

    int setConfirmInvoiceCase(ConfirmInvoiceCase confirmInvoiceCase);

    ConfirmInvoiceCase getConfirmInvoiceCaseById(int caseId);

}
