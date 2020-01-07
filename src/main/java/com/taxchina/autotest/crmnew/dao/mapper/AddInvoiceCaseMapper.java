package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.AddInvoiceCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddInvoiceCaseMapper {

    int setAddInvoiceCase(AddInvoiceCase addInvoiceCase);

    AddInvoiceCase getAddInvoiceCaseById(int caseId);

}
