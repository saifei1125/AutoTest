package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.service.common.RelevanceInvoice;
import com.taxchina.autotest.crmnew.service.entity.Contract;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import org.springframework.stereotype.Service;

/**关联发票*/
@Service
public class RelevanceInvoiceImpl {
    public void relevanceInvoice(User user, Invoice invoice, Contract contract){
        RelevanceInvoice relevanceInvoice = new RelevanceInvoice();
        relevanceInvoice.testRelevanceInvoice(user,invoice,contract);

    }
}
