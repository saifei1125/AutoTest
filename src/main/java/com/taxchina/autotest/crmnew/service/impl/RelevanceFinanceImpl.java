package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.service.common.RelevanceFinance;
import com.taxchina.autotest.crmnew.service.entity.Contract;
import com.taxchina.autotest.crmnew.service.entity.Finance;
import com.taxchina.autotest.crmnew.service.entity.User;
import org.springframework.stereotype.Service;

/**关联回款*/
@Service
public class RelevanceFinanceImpl {
    public void relevanceFinance(User user, Contract contract, Finance finance){
        RelevanceFinance relevanceFinance = new RelevanceFinance();
        relevanceFinance.testRelevanceFinance(user,contract,finance);
    }
}
