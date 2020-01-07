package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.dao.entity.AddFinanceCase;
import com.taxchina.autotest.crmnew.dao.entity.AddFinanceCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.AddFinanceCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.AddFinanceCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.AddFinance;
import com.taxchina.autotest.crmnew.service.common.GetFinanceInfo;
import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.entity.Finance;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import io.restassured.response.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.getEntity;
@Service
public class AddFinanceImpl {
    @Autowired
    private AddFinanceCaseMapper addFinanceCaseMapper;
    @Autowired
    private AddFinanceCaseResMapper addFinanceCaseResMapper;

    public AddFinanceCase insertAddFinanceCase(AddFinanceCase addFinanceCase){
        addFinanceCaseMapper.setAddFinanceCase(addFinanceCase);
        Log.info("新增添加回款用例id:"+addFinanceCase.getCaseId());
        return addFinanceCase;
    }
    public AddFinanceCaseRes runAddFinanceCaseById(User accountant, int caseId){
        AddFinanceCase addFinanceCase = addFinanceCaseMapper.getAddFinanceCaseById(caseId);
        //添加回款
        AddFinance addFinance = new AddFinance();
        CloseableHttpResponse httpResponse = addFinance.addFinanceByCase(accountant,addFinanceCase);
        String res = getEntity(httpResponse,"utf-8");
        try {
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("出错啦！！！错误信息："+e.getMessage());
        }
        Log.info("添加回款请求返回结果："+ res);
        //回写添加回款结果
        AddFinanceCaseRes addFinanceCaseRes = new AddFinanceCaseRes();
        //查询回款id
        GetFinanceInfo getFinance = new GetFinanceInfo();
        List<Finance> list = getFinance.getFinanceInfo(accountant);
//        for (Finance finance : list) {
//            if(finance.getCollectionNo().equals(addFinanceCase.getCollectionNo())){
//                addFinanceCaseRes.setFinanceId(finance.getId());
//            }
//        }
        list.forEach((finance -> {
            if(finance.getCollectionNo().equals(addFinanceCase.getCollectionNo())){
                addFinanceCaseRes.setFinanceId(finance.getId());
            }
        }));
        addFinanceCaseRes.setCaseId(addFinanceCase.getCaseId());
        addFinanceCaseRes.setResult(res);
        int insertRes =addFinanceCaseResMapper.setAddFinanceCaseRes(addFinanceCaseRes);
        if(insertRes == 1){
            Log.info("添加回款请求结果保存成功");
        }else {
            Log.info("添加回款请求结果保存失败");
        }
        return addFinanceCaseRes;
    }


    public AddFinanceCaseRes runAddFinanceCaseByIdNew(User accountant,int caseId){
        AddFinanceCase addFinanceCase = addFinanceCaseMapper.getAddFinanceCaseById(caseId);
        //添加回款
        AddFinance addFinance = new AddFinance();
        Response response = addFinance.addFinanceByCaseNew(accountant,addFinanceCase);
        String res = response.print();
        Log.info("添加回款请求返回结果："+ res);
        //回写添加回款结果
        AddFinanceCaseRes addFinanceCaseRes = new AddFinanceCaseRes();
        //查询回款id
        GetFinanceInfo getFinance = new GetFinanceInfo();
        List<Finance> list = getFinance.getFinanceInfoNew(accountant);
        for (Finance finance : list) {
            if(finance.getCollectionNo().equals(addFinanceCase.getCollectionNo())){
                addFinanceCaseRes.setFinanceId(finance.getId());
            }
        }
        addFinanceCaseRes.setCaseId(addFinanceCase.getCaseId());
        addFinanceCaseRes.setResult(res);
        int insertRes =addFinanceCaseResMapper.setAddFinanceCaseRes(addFinanceCaseRes);
        if(insertRes == 1){
            Log.info("添加回款请求结果保存成功");
        }else {
            Log.info("添加回款请求结果保存失败");
        }
        return addFinanceCaseRes;
    }

    public Finance addFinanceByLoginnameByCase(User accountant, User sell, Invoice invoice ){
        //生成添加回款用例
        AddFinanceCase addFinanceCase = new AddFinanceCase();
        //拿到回款编号
        GetFinanceInfo getFinance = new GetFinanceInfo();
        Finance finance = getFinance.getFinanceNo(accountant);
        addFinanceCase.setCollectionNo(finance.getCollectionNo());
        finance.setAmount("20000");
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(sell);
        //拿到财务信息
        getUserInfo.getUserInfoByLoginName(accountant);
        accountant.setUsername(accountant.getUsername());
        addFinanceCase.setBusinessUserId(String.valueOf(sell.getUserId()));
        addFinanceCase.setCustType(0);
        addFinanceCase.setBusinessUserName(sell.getUsername());
        addFinanceCase.setBusinessCompanyName(accountant.getSecLevelDeptName());
        addFinanceCase.setCollectionDate("2019-11-13");//回款日期
        addFinanceCase.setAmount(finance.getAmount());//回款金额
        addFinanceCase.setContractLabel(0);//新签/续费  0 新签  1 续费
        addFinanceCase.setGatheringFinance(accountant.getUsername());
        addFinanceCase.setGatheringType(1);//收款方式 1 转账 2 现金 3 支票
        addFinanceCase.setPaymentName("测试回款公司");
        addFinanceCase.setNoticeFlag("2");//是否通知业务员 1 是 2  否

        if (invoice == null) {
            addFinanceCase.setInvoiceNo("");
            addFinanceCase.setInvoiceFlag(0);//是否已开票 0 否 1 是
            addFinanceCase.setInvoiceDate("");
        }else {
            addFinanceCase.setInvoiceNo(invoice.getInvoiceNo());
            addFinanceCase.setInvoiceFlag(1);//是否已开票 0 否 1 是
            addFinanceCase.setInvoiceDate(invoice.getInvoiceDate());
        }

        addFinanceCase.setSuitetType("1");
        addFinanceCase.setRemark("测试回款备注");
        int setRes = addFinanceCaseMapper.setAddFinanceCase(addFinanceCase);
        if(setRes == 1){
            Log.info("添加回款用例保存成功");
        }else {
            Log.info("添加回款用例保存失败");
        }
        //添加回款
        AddFinance addFinance = new AddFinance();
        CloseableHttpResponse httpResponse = addFinance.addFinanceByCase(accountant,addFinanceCase);
        String res = getEntity(httpResponse,"utf-8");
        try {
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("出错啦！！！错误信息："+e.getMessage());
        }
        Log.info("添加回款请求返回结果："+ res);
        //回写添加回款结果
        AddFinanceCaseRes addFinanceCaseRes = new AddFinanceCaseRes();
        //查询回款id
        GetFinanceInfo getFinanceInfo = new GetFinanceInfo();
        List<Finance> list = getFinanceInfo.getFinanceInfo(accountant);
        for(Finance finance1 : list){
            if(finance1.getCollectionNo().equals(addFinanceCase.getCollectionNo())){
                addFinanceCaseRes.setFinanceId(finance1.getId());
                finance.setId(finance1.getId());
            }
        }

        addFinanceCaseRes.setCaseId(addFinanceCase.getCaseId());
        addFinanceCaseRes.setResult(res);
        int insertRes =addFinanceCaseResMapper.setAddFinanceCaseRes(addFinanceCaseRes);
        if(insertRes == 1){
            Log.info("添加回款请求结果保存成功");
        }else {
            Log.info("添加回款请求结果保存失败");
        }
        return finance;
    }

    /**验证回款提交信息与列表查询的结果一致*/
    public Boolean checkFinanceInfo(User user,Finance fin,AddFinanceCase addFinanceCase){
        Boolean b = false;
        GetFinanceInfo getFinanceInfo = new GetFinanceInfo();
        List<Finance> list = getFinanceInfo.getFinanceInfo(user);
        for(Finance finance : list){
            if(finance.getCollectionNo().equals(addFinanceCase.getCollectionNo())){
                Assert.assertEquals(finance.getCreateBy(),user.getUsername());
                Assert.assertEquals(finance.getPaymentName(),addFinanceCase.getPaymentName());
                Assert.assertEquals(finance.getBusinessUserName(),addFinanceCase.getBusinessUserName());
                Assert.assertEquals(finance.getInvoiceFlag(),addFinanceCase.getInvoiceFlag());
                Assert.assertEquals(finance.getStatus(),fin.getStatus());
                Assert.assertEquals(finance.getContractFlag(),fin.getContractFlag());
                Assert.assertEquals(finance.getRejectStatus(),fin.getRejectStatus());
                return true;
            }
        }
        return b;
    }







    public Finance addFinanceByLoginname(User accountant,User sell){
        //拿到回款编号
        GetFinanceInfo getFinance = new GetFinanceInfo();
        Finance finance = getFinance.getFinanceNo(accountant);
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(sell);
        //录入回款
        AddFinance addFinance = new AddFinance();
        addFinance.testAddFinance(accountant,finance,sell,null);
        //查询回款id
        List<Finance> list = getFinance.getFinanceInfo(accountant);
        for(Finance finance1 : list){
            if(finance1.getCollectionNo().equals(finance.getCollectionNo())){
                return finance1;
            }
        }
        return null;
    }
    public Finance addFinanceByUsername(User accountant,User sell){
        //拿到回款编号
        GetFinanceInfo getFinance = new GetFinanceInfo();
        Finance finance = getFinance.getFinanceNo(accountant);
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByUserName(sell);
        //录入回款
        AddFinance addFinance = new AddFinance();
        addFinance.testAddFinance(accountant,finance,sell,null);
        //查询回款id
        List<Finance> list = getFinance.getFinanceInfo(accountant);
        for(Finance finance1 : list){
            if(finance1.getCollectionNo().equals(finance.getCollectionNo())){
                return finance1;
            }
        }
        return null;
    }
    public Finance addFinanceByLoginnameAndAmount(User accountant,User sell,String amount){
        //拿到回款编号
        GetFinanceInfo getFinance = new GetFinanceInfo();
        Finance finance = getFinance.getFinanceNo(accountant);
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByUserName(sell);
        //录入回款
        AddFinance addFinance = new AddFinance();
        addFinance.testAddFinance(accountant,finance,sell,amount);
        //查询回款id
        List<Finance> list = getFinance.getFinanceInfo(accountant);
        for(Finance finance1 : list){
            if(finance1.getCollectionNo().equals(finance.getCollectionNo())){
                return finance1;
            }
        }
        return null;
    }
    public Finance addFinanceByUsernameAndAmount(User accountant,User sell,String amount){
        //拿到回款编号
        GetFinanceInfo getFinance = new GetFinanceInfo();
        Finance finance = getFinance.getFinanceNo(accountant);
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByUserName(sell);
        //录入回款
        AddFinance addFinance = new AddFinance();
        addFinance.testAddFinance(accountant,finance,sell,amount);
        //查询回款id
        List<Finance> list = getFinance.getFinanceInfo(accountant);
        for(Finance finance1 : list){
            if(finance1.getCollectionNo().equals(finance.getCollectionNo())){
                return finance1;
            }
        }
        return null;
    }
}
