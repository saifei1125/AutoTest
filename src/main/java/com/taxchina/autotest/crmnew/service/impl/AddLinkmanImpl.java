package com.taxchina.autotest.crmnew.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.AddLinkmanCase;
import com.taxchina.autotest.crmnew.dao.entity.AddLinkmanCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.AddLinkmanCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.AddLinkmanCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.AddLinkman;
import com.taxchina.autotest.crmnew.service.common.GetlkmInfo;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Lkm;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddLinkmanImpl {
    @Autowired
    private AddLinkmanCaseMapper addLinkmanCaseMapper;
    @Autowired
    private AddLinkmanCaseResMapper addLinkmanCaseResMapper;
    //插入添加联系人用例
    public AddLinkmanCase insertAddLinkmanCase(AddLinkmanCase addLinkmanCase){
        addLinkmanCaseMapper.setAddLinkmanCase(addLinkmanCase);
        Log.info("新增添加联系人用例id:"+addLinkmanCase.getCaseId());
        return addLinkmanCase;
    }
    public AddLinkmanCaseRes runAddLinkmanCaseById(User user, Customer customer, int caseId){
        AddLinkmanCase addLinkmanCase = addLinkmanCaseMapper.getAddLinkmanCaseById(caseId);
        Lkm lkm = new Lkm();
        lkm.setLkmName(addLinkmanCase.getLinkmanName());
        lkm.setLkmSex(addLinkmanCase.getLinkmanSex());
        lkm.setLkmMobile(addLinkmanCase.getLinkmanMobile());
        lkm.setLkmTel(addLinkmanCase.getLinkmanTel());
        lkm.setLkmAddr(addLinkmanCase.getLinkmanAddr());
        lkm.setLkmPostion(addLinkmanCase.getLinkmanPostion());
        lkm.setLkmWebchat(addLinkmanCase.getLinkmanWechat());
        lkm.setLkmQq(addLinkmanCase.getLinkmanQq());
        lkm.setLkmEmail(addLinkmanCase.getLinkmanEmail());
        lkm.setLkmRemake(addLinkmanCase.getLinkmanRemake());
        lkm.setIsMainLkm(addLinkmanCase.getIsMainLkm());

        //执行添加
        AddLinkman addLinkman = new AddLinkman();
        String res = addLinkman.addLinkmanByCase(user,addLinkmanCase);
        Log.info("添加联系人请求返回结果："+res);
        if(res!=null) {
            JSONObject jsonObject = JSON.parseObject(res);
            String code = jsonObject.getString("code");
            if (code.equals("0")) {
                //查联系人id
                GetlkmInfo getlkmInfo = new GetlkmInfo();
                getlkmInfo.getlkmInfo(user, customer);
                List<Lkm> lkmList = customer.getLkms();
                for (Lkm lkm1 : lkmList) {
                    if (lkm1.getLkmName().equals(addLinkmanCase.getLinkmanName())) {
                        lkm.setLkmId(lkm1.getLkmId());
                    }
                }
            }
        }
        AddLinkmanCaseRes addLinkmanCaseRes = new AddLinkmanCaseRes();
        addLinkmanCaseRes.setCaseId(addLinkmanCase.getCaseId());
        addLinkmanCaseRes.setResult(res);
        addLinkmanCaseRes.setLinkmanId(lkm.getLkmId());
        int insertRes = addLinkmanCaseResMapper.setAddLinkmanCaseRes(addLinkmanCaseRes);
        if(insertRes == 1){
            Log.info("添加联系人结果保存成功");
        }else {
            Log.info("添加联系人结果保存失败");
        }
        return addLinkmanCaseRes;
    }


    public Lkm addLinkmanByCase(User user,Customer customer){
        //生成添加联系人用例
        AddLinkmanCase addLinkmanCase = new AddLinkmanCase();
        addLinkmanCase.setCustName(customer.getCustName());
        addLinkmanCase.setCustId(customer.getCustId());
        addLinkmanCase.setLinkmanName(customer.getCustName()+"的联系人");//customer.getCustName()+
        addLinkmanCase.setLinkmanSex(0);
        addLinkmanCase.setLinkmanMobile("1510111"+(int)(Math.random()*(9999-1000+1)+1000));
        addLinkmanCase.setLinkmanTel("010"+(int)(Math.random()*(999999-100000+1)+100000));
        addLinkmanCase.setLinkmanAddr("测试地址");
        addLinkmanCase.setLinkmanPostion("经理");
        addLinkmanCase.setLinkmanWechat("");
        addLinkmanCase.setLinkmanQq("");
        addLinkmanCase.setLinkmanEmail("");
        addLinkmanCase.setLinkmanRemake("测试备注");
        addLinkmanCase.setIsMainLkm(0);
        addLinkmanCase.setAddLinkmanExpect("{\"msg\":\"操作成功\",\"code\":0}");
        int setRes = addLinkmanCaseMapper.setAddLinkmanCase(addLinkmanCase);
        if(setRes == 1){
            Log.info("添加联系人用例保存成功");
        }else {
            Log.info("添加联系人用例保存失败");
        }

        Lkm lkm = new Lkm();
        lkm.setLkmName(addLinkmanCase.getLinkmanName());
        lkm.setLkmSex(addLinkmanCase.getLinkmanSex());
        lkm.setLkmMobile(addLinkmanCase.getLinkmanMobile());
        lkm.setLkmTel(addLinkmanCase.getLinkmanTel());
        lkm.setLkmAddr(addLinkmanCase.getLinkmanAddr());
        lkm.setLkmPostion(addLinkmanCase.getLinkmanPostion());
        lkm.setLkmWebchat(addLinkmanCase.getLinkmanWechat());
        lkm.setLkmQq(addLinkmanCase.getLinkmanQq());
        lkm.setLkmEmail(addLinkmanCase.getLinkmanEmail());
        lkm.setLkmRemake(addLinkmanCase.getLinkmanRemake());
        lkm.setIsMainLkm(addLinkmanCase.getIsMainLkm());

        //执行添加
        AddLinkman addLinkman = new AddLinkman();
        String res = addLinkman.addLinkmanByCase(user,addLinkmanCase);
        Log.info("添加联系人请求返回结果："+res);
        if(res!=null) {
            //查联系人id
            GetlkmInfo getlkmInfo = new GetlkmInfo();
            getlkmInfo.getlkmInfo(user, customer);
            List<Lkm> lkmList = customer.getLkms();
            for (Lkm lkm1 : lkmList) {
                if (lkm1.getLkmName().equals(addLinkmanCase.getLinkmanName())) {
                    lkm.setLkmId(lkm1.getLkmId());
                }
            }
        }
        AddLinkmanCaseRes addLinkmanCaseRes = new AddLinkmanCaseRes();
        addLinkmanCaseRes.setCaseId(addLinkmanCase.getCaseId());
        addLinkmanCaseRes.setResult(res);
        addLinkmanCaseRes.setLinkmanId(lkm.getLkmId());
        int insertRes = addLinkmanCaseResMapper.setAddLinkmanCaseRes(addLinkmanCaseRes);
        if(insertRes == 1){
            Log.info("添加联系人结果保存成功");
        }else {
            Log.info("添加联系人结果保存失败");
        }
        return lkm;
    }

    public Lkm addLinkman(User user, Customer customer){
        Lkm lkm = new Lkm();
        lkm.setLkmName("测试");
        lkm.setLkmMobile("1500000"+(int)(Math.random()*(9999-1000+1)+1000));
        lkm.setLkmTel("");
        lkm.setLkmAddr("");
        lkm.setLkmPostion("");
        lkm.setLkmWebchat("");
        lkm.setLkmQq("");
        lkm.setLkmEmail("");
        lkm.setLkmAddr("");
        lkm.setLkmRemake("");
        lkm.setIsMainLkm(0);//主要联系人 0 不是 1是
        AddLinkman addLinkman = new AddLinkman();
        addLinkman.addLinkman(user,customer,lkm);
        return lkm;
    }

}
