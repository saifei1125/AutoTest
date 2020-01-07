package com.taxchina.autotest.crmnew.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.taxchina.autotest.crmnew.dao.entity.SignContractCase;
import com.taxchina.autotest.crmnew.dao.entity.SignContractCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.SignContractCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.SignContractCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.*;
import com.taxchina.autotest.crmnew.service.entity.*;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.Assert;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/** 签合同 */
@Service
public class SignContractImpl {
    @Autowired
    private SignContractCaseMapper signContractCaseMapper;

    @Autowired
    private SignContractCaseResMapper signContractCaseResMapper;

    public SignContractCase insertSignContractCase(SignContractCase signContractCase){
        signContractCaseMapper.setSignContractCase(signContractCase);
        Log.info("新增签合同用例id:"+signContractCase.getCaseId());
        List<Product> list = signContractCase.getProductList();
        for(Product product : list){
            signContractCase.setProductId(product.getProdId());
            signContractCase.setProductName(product.getProdName());
            signContractCase.setProductUnit(product.getProdUnit());
            signContractCase.setProductPrice(product.getProdSalePrice());
            signContractCase.setProductType(product.getProdType());
            signContractCase.setSuiteProdNum(product.getSuitProdNum());
            signContractCaseMapper.setSuiteProduct(signContractCase);
        }
        return signContractCase;
    }

    public SignContractCaseRes runSignContractCaseById(User user, int caseId){
        SignContractCase signContractCase = signContractCaseMapper.getSignContractCaseById(caseId);
        List<Product> list = signContractCaseMapper.getSuiteProductInfo(signContractCase.getSuiteId());
        signContractCase.setProductList(list);
        SignContract signContract = new SignContract();
        String res = signContract.signContractByCase(user,signContractCase);
        Log.info("签合同请求返回结果：" + res);
        Contract contract = new Contract();
        JSONObject responseBody = JSONObject.parseObject(res);
        JSONObject jo = JSONObject.parseObject(responseBody.getString("data"));
        contract.setContractNo(jo.getString("contractNo"));

        //回写签合同结果
        SignContractCaseRes signContractCaseRes = new SignContractCaseRes();
        signContractCaseRes.setCaseId(signContractCase.getCaseId());
        signContractCaseRes.setResult(res);
        signContractCaseRes.setContractNo(contract.getContractNo());
        int insertRes = signContractCaseResMapper.setSignContractCaseRes(signContractCaseRes);
        if(insertRes == 1){
            Log.info("签合同结果保存成功");
        }else {
            Log.info("签合同结果保存失败");
        }
        return signContractCaseRes;
    }

    public Contract signContract(User user, Customer customer, Lkm lkm,String suiteName){
//        //查询联系人信息
//        GetlkmInfo g = new GetlkmInfo();
//        g.getlkmInfo(user,customer);
        //查询产品套餐信息
        SignContractCase signContractCase = new SignContractCase();
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = new Suite();
        suite.setSuiteName("测试套餐");
        suite = getSuiteInfo.getSuiteProductsInfoByName(user,customer,suiteName);
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setProductList(suite.getProductList());
        List<Product> list = signContractCase.getProductList();
        for(Product product : list){
            //存入套餐中产品信息
            signContractCase.setProductId(product.getProdId());
            signContractCase.setProductName(product.getProdName());
            signContractCase.setProductUnit(product.getProdUnit());
            signContractCase.setProductPrice(product.getProdSalePrice());
            signContractCase.setProductType(product.getProdType());
            signContractCase.setSuiteProdNum(product.getSuitProdNum());
            signContractCase.setCustId(customer.getCustId());
            signContractCaseMapper.setSuiteProduct(signContractCase);
        }
        //生成签合同的用例
        signContractCase.setCustId(customer.getCustId());
        signContractCase.setCustName(customer.getCustName());
        signContractCase.setSignTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setSuiteId(suite.getSuiteId());
        signContractCase.setSuiteName(suite.getSuiteName());
        signContractCase.setSuiteDesc(suite.getSuiteDesc());
        signContractCase.setSuiteAmount(suite.getSuitePrice());
        signContractCase.setSuiteType(suite.getSuiteType());
        signContractCase.setTemplateId(suite.getTemplateId());
        signContractCase.setLkmId(lkm.getLkmId());//customer.getLkms().get(0).getLkmId()
        signContractCase.setLkmName(lkm.getLkmName());
        signContractCase.setLkmTel(lkm.getLkmMobile());
//        signContractCase.setReduceAmount("100");//合同扣减金额
        signContractCase.setOriginalAmount(suite.getSuitePrice());//合同原始金额
        signContractCase.setActualAmount(suite.getSuitePrice());//合同实际金额（合同最终价）
        signContractCase.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setEndTime("2020-08-26");
        signContractCase.setActualStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//当前时间
        signContractCase.setActualEndTime("2020-08-26");
        Integer setRes = signContractCaseMapper.setSignContractCase(signContractCase);
        if(setRes == 1){
            Log.info("签合同用例保存成功");
        }else {
            Log.info("签合同用例保存失败");
        }
        SignContract signContract = new SignContract();
        String res = signContract.signContractByCase(user,signContractCase);
        Log.info("签合同请求返回结果：" + res);
        Contract contract = new Contract();
        JSONObject responseBody = JSONObject.parseObject(res);
        JSONObject jo = JSONObject.parseObject(responseBody.getString("data"));
        contract.setContractNo(jo.getString("contractNo"));

        //回写签合同结果
        SignContractCaseRes signContractCaseRes = new SignContractCaseRes();
        signContractCaseRes.setCaseId(signContractCase.getCaseId());
        signContractCaseRes.setResult(res);
        signContractCaseRes.setContractNo(contract.getContractNo());
        int insertRes = signContractCaseResMapper.setSignContractCaseRes(signContractCaseRes);
        if(insertRes == 1){
            Log.info("签合同结果保存成功");
        }else {
            Log.info("签合同结果保存失败");
        }

        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,contract.getContractNo());
        return contract;
    }

    /**验证合同提交信息与列表查询的结果一致*/
    public Boolean checkContractInfo(User user,Contract cont,SignContractCase signContractCase) {
        Boolean b = false;
        GetContractInfo getContractInfo = new GetContractInfo();
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                Log.error(e);
            }
            List<Contract> contractList = getContractInfo.getContractInfo(user);
            if (contractList != null) {
                for (Contract contract : contractList) {
                    if (contract.getContractNo().equals(cont.getContractNo())) {
                        Assert.assertEquals(signContractCase.getCustName(), contract.getCustomerName());
                        Assert.assertEquals(signContractCase.getCustId(), contract.getCustomerId());
                        Assert.assertEquals(user.getUserId(),contract.getLeaderId());
                        Assert.assertEquals(user.getUsername(),contract.getLeaderName());
                        Assert.assertEquals(signContractCase.getSuiteName(), contract.getSuiteName());
                        Assert.assertEquals(signContractCase.getSuiteType(), contract.getSuiteType());
                        Assert.assertEquals(signContractCase.getActualAmount(), contract.getActualAmount());
                        Assert.assertEquals(signContractCase.getActualEndTime(), contract.getActualEndTime());
                        Assert.assertEquals(contract.getContractStatus(), cont.getContractStatus());
                        Assert.assertEquals(contract.getServiceStatus(), cont.getServiceStatus());
                        Assert.assertEquals(contract.getCollectionStatus(), cont.getCollectionStatus());
                        Assert.assertEquals(contract.getPaymentsFlag(),cont.getPaymentsFlag());
                        Assert.assertEquals(contract.getInvoiceFlag(),cont.getInvoiceFlag());
                        return true;
                    }
                }
            }
        }
        return b;
    }


    public Contract signContract(User user, Customer customer){
        //查询联系人信息
        GetlkmInfo g = new GetlkmInfo();
        g.getlkmInfo(user,customer);
        //查询产品套餐信息
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfo(user,customer);
        //签订合同
        SignContract signContract = new SignContract();
        Contract contract = signContract.testSignContract(user,customer,suite);
        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,contract.getContractNo());
        return contract;
    }


    public Contract signContractByName(User user, Customer customer,String suiteName){
        //查询联系人信息
        GetlkmInfo g = new GetlkmInfo();
        g.getlkmInfo(user,customer);
        //查询产品套餐信息
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfoByName(user,customer,suiteName);
        //签订合同
        SignContract signContract = new SignContract();
        Contract contract = signContract.testSignContract(user,customer,suite);
        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,contract.getContractNo());
        return contract;
    }
    public Contract signContractById(User user, Customer customer,String suiteId){
        //查询联系人信息
        GetlkmInfo g = new GetlkmInfo();
        g.getlkmInfo(user,customer);
        //查询产品套餐信息
        GetSuiteInfo getSuiteInfo = new GetSuiteInfo();
        Suite suite = getSuiteInfo.getSuiteProductsInfoById(user,customer,suiteId);
        //签订合同
        SignContract signContract = new SignContract();
        Contract contract = signContract.testSignContract(user,customer,suite);
        //提交合同
        SubmitContract submitContract = new SubmitContract();
        submitContract.testSubmitContract(user,contract.getContractNo());
        return contract;
    }




}
