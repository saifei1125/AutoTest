package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.dao.entity.ConfirmPerformanceCase;
import com.taxchina.autotest.crmnew.dao.entity.ConfirmPerformanceCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.ConfirmPerformanceCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.ConfirmPerformanceCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.ConfirmPerformance;
import com.taxchina.autotest.crmnew.service.common.GetPerformanceInfo;
import com.taxchina.autotest.crmnew.service.entity.Finance;
import com.taxchina.autotest.crmnew.service.entity.Performance;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmPerformanceImpl {
    @Autowired
    private ConfirmPerformanceCaseMapper confirmPerformanceCaseMapper;
    @Autowired
    private ConfirmPerformanceCaseResMapper confirmPerformanceCaseResMapper;
    public void confirmPerformanceByCase(User user , Finance finance){
        ConfirmPerformanceCase confirmPerformanceCase = new ConfirmPerformanceCase();
        confirmPerformanceCase.setCollections(finance.getCollectionNo());
        GetPerformanceInfo getPerformanceInfo = new GetPerformanceInfo();
        Performance performance = getPerformanceInfo.getPerformanceNo(user,finance);
        Log.info("---业绩编号："+performance.getPerformanceNo());
        confirmPerformanceCase.setPerformanceNo(performance.getPerformanceNo());
        int setRes = confirmPerformanceCaseMapper.setConfirmPerformanceCase(confirmPerformanceCase);
        if(setRes == 1){
            Log.info("审核业绩用例保存成功");
        }else {
            Log.info("审核业绩用例保存失败");
        }
        ConfirmPerformance confirmPerformance = new ConfirmPerformance();
        String res = confirmPerformance.confirmPerformanceByCase(user,confirmPerformanceCase);
        ConfirmPerformanceCaseRes confirmPerformanceCaseRes = new ConfirmPerformanceCaseRes();
        confirmPerformanceCaseRes.setCaseId(confirmPerformanceCase.getCaseId());
        confirmPerformanceCaseRes.setResult(res);
        int insertRes =confirmPerformanceCaseResMapper.setConfirmPerformanceCaseRes(confirmPerformanceCaseRes);
        if(insertRes == 1){
            Log.info("审核业绩结果保存成功");
        }else {
            Log.info("审核业绩结果保存失败");
        }

    }
    public void confirmPerformance(User user ,Finance finance){
        GetPerformanceInfo getPerformanceInfo = new GetPerformanceInfo();
        Performance performance = getPerformanceInfo.getPerformanceNo(user,finance);
        Log.info("---业绩编号："+performance.getPerformanceNo());

        ConfirmPerformance confirmPerformance = new ConfirmPerformance();
        confirmPerformance.testConfirmPerformance(user,performance);

    }
}
