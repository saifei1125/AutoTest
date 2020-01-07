package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.dao.entity.ConfirmInvoiceCase;
import com.taxchina.autotest.crmnew.dao.entity.ConfirmInvoiceCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.ConfirmInvoiceCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.ConfirmInvoiceCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.ConfirmInvoice;
import com.taxchina.autotest.crmnew.service.common.GetInvoiceInfo;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmInvoiceImpl {
    @Autowired
    private ConfirmInvoiceCaseMapper confirmInvoiceCaseMapper;
    @Autowired
    private ConfirmInvoiceCaseResMapper confirmInvoiceCaseResMapper;

    public ConfirmInvoiceCase insertConfirmInvoiceCase(ConfirmInvoiceCase confirmInvoiceCase){
        confirmInvoiceCaseMapper.setConfirmInvoiceCase(confirmInvoiceCase);
        Log.info("新增审核发票用例id:"+confirmInvoiceCase.getCaseId());
        return confirmInvoiceCase;
    }
    public ConfirmInvoiceCaseRes runConfirmInvoiceCaseById(User user, int caseId){
        String res = "";
        ConfirmInvoiceCase confirmInvoiceCase = confirmInvoiceCaseMapper.getConfirmInvoiceCaseById(caseId);
        ConfirmInvoice confirmInvoice = new ConfirmInvoice();
        if(confirmInvoiceCase.getRejectReason().equals("") && confirmInvoiceCase.getRejectReason()==null){
            res = confirmInvoice.confirmInvoiceByCase(user,confirmInvoiceCase);
            Log.info("审核发票返回结果：" + res);
        }else {
            res = confirmInvoice.dismissedInvoiceByCase(user,confirmInvoiceCase);
            Log.info("审核发票返回结果：" + res);
        }
        //回写发票审核结果
        ConfirmInvoiceCaseRes confirmInvoiceCaseRes = new ConfirmInvoiceCaseRes();
        confirmInvoiceCaseRes.setCaseId(confirmInvoiceCase.getCaseId());
        confirmInvoiceCaseRes.setInvoiceId(confirmInvoiceCase.getInvoiceId());
        confirmInvoiceCaseRes.setResult(res);
        int insertRes  = confirmInvoiceCaseResMapper.setConfirmInvoiceCaseRes(confirmInvoiceCaseRes);
        if(insertRes == 1){
            Log.info("审核发票结果保存成功");
        }else {
            Log.info("审核发票结果保存失败");
        }
        return confirmInvoiceCaseRes;
    }


    public Invoice confirmInvoice(User user, Invoice invoice){
        //存入审核发票信息
        ConfirmInvoiceCase confirmInvoiceCase = new ConfirmInvoiceCase();
        confirmInvoiceCase.setInvoiceNo(invoice.getInvoiceNo());
        confirmInvoiceCase.setInvoiceAmount("970");
        confirmInvoiceCase.setInvoiceTaxRate("0.03");
        confirmInvoiceCase.setInvoiceTaxAmount("30");
        confirmInvoiceCase.setInvoiceNoTaxAmount("1000");
        confirmInvoiceCase.setConfirmRemark("测试审核备注");
        confirmInvoiceCase.setInvoiceReceivedPaymentFlag(2);
        //执行审核发票
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        List<Invoice> list = getInvoiceInfo.getInvoiceId(user);
        for(Invoice invoice1 : list){
            if(invoice1.getInvoiceNo().equals(invoice.getInvoiceNo())){
                invoice.setId(invoice1.getId());
            }
        }
        confirmInvoiceCase.setInvoiceId(invoice.getId());

        int setRes = confirmInvoiceCaseMapper.setConfirmInvoiceCase(confirmInvoiceCase);
        if(setRes == 1){
            Log.info("审核发票用例保存成功");
        }else {
            Log.info("审核发票用例保存失败");
        }

        ConfirmInvoice confirmInvoice = new ConfirmInvoice();
        String res = confirmInvoice.confirmInvoiceByCase(user,confirmInvoiceCase);
        Log.info("审核发票返回结果：" + res);
        //回写发票审核结果
        ConfirmInvoiceCaseRes confirmInvoiceCaseRes = new ConfirmInvoiceCaseRes();
        confirmInvoiceCaseRes.setCaseId(confirmInvoiceCase.getCaseId());
        confirmInvoiceCaseRes.setInvoiceId(confirmInvoiceCase.getInvoiceId());
        confirmInvoiceCaseRes.setResult(res);
        int insertRes  = confirmInvoiceCaseResMapper.setConfirmInvoiceCaseRes(confirmInvoiceCaseRes);
        if(insertRes == 1){
            Log.info("审核发票结果保存成功");
        }else {
            Log.info("审核发票结果保存失败");
        }
        return invoice;
    }

    public void dismissedInvoice(User user, Invoice invoice){
        //存入审核发票信息
        ConfirmInvoiceCase confirmInvoiceCase = new ConfirmInvoiceCase();
        confirmInvoiceCase.setRejectReason("拒绝原因");
        //执行审核发票
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        List<Invoice> list = getInvoiceInfo.getInvoiceId(user);
        for(Invoice invoice1 : list){
            if(invoice1.getInvoiceNo().equals(invoice.getInvoiceNo())){
                invoice.setId(invoice1.getId());
            }
        }
        confirmInvoiceCase.setInvoiceId(invoice.getId());
        confirmInvoiceCase.setInvoiceNo(invoice.getInvoiceNo());
        int setRes = confirmInvoiceCaseMapper.setConfirmInvoiceCase(confirmInvoiceCase);
        if(setRes == 1){
            Log.info("审核发票用例保存成功");
        }else {
            Log.info("审核发票用例保存失败");
        }
        ConfirmInvoice confirmInvoice = new ConfirmInvoice();
        String res = confirmInvoice.dismissedInvoiceByCase(user,confirmInvoiceCase);
        Log.info("审核发票返回结果：" + res);
        //回写发票审核结果
        ConfirmInvoiceCaseRes confirmInvoiceCaseRes = new ConfirmInvoiceCaseRes();
        confirmInvoiceCaseRes.setCaseId(confirmInvoiceCase.getCaseId());
        confirmInvoiceCaseRes.setInvoiceId(confirmInvoiceCase.getInvoiceId());
        confirmInvoiceCaseRes.setResult(res);
        int insertRes  = confirmInvoiceCaseResMapper.setConfirmInvoiceCaseRes(confirmInvoiceCaseRes);
        if(insertRes == 1){
            Log.info("审核发票结果保存成功");
        }else {
            Log.info("审核发票结果保存失败");
        }
    }

    public void obsoleteInvoice(User user, Invoice invoice){
        //存入审核发票信息
        ConfirmInvoiceCase confirmInvoiceCase = new ConfirmInvoiceCase();
        confirmInvoiceCase.setRejectReason("拒绝原因");
        //执行审核发票
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        List<Invoice> list = getInvoiceInfo.getInvoiceId(user);
        for(Invoice invoice1 : list){
            if(invoice1.getInvoiceNo().equals(invoice.getInvoiceNo())){
                invoice.setId(invoice1.getId());
            }
        }
        confirmInvoiceCase.setInvoiceId(invoice.getId());
        confirmInvoiceCase.setInvoiceNo(invoice.getInvoiceNo());
        int setRes = confirmInvoiceCaseMapper.setConfirmInvoiceCase(confirmInvoiceCase);
        if(setRes == 1){
            Log.info("审核发票用例保存成功");
        }else {
            Log.info("审核发票用例保存失败");
        }
        ConfirmInvoice confirmInvoice = new ConfirmInvoice();
        String res = confirmInvoice.confirmInvoiceByCase(user,confirmInvoiceCase);
        Log.info("审核发票返回结果：" + res);
        String res1 = confirmInvoice.obsoleteInvoiceByCase(user,confirmInvoiceCase);
        Log.info("作废发票返回结果：" + res1);
        //回写发票审核结果
        ConfirmInvoiceCaseRes confirmInvoiceCaseRes = new ConfirmInvoiceCaseRes();
        confirmInvoiceCaseRes.setCaseId(confirmInvoiceCase.getCaseId());
        confirmInvoiceCaseRes.setInvoiceId(confirmInvoiceCase.getInvoiceId());
        confirmInvoiceCaseRes.setResult(res1);
        int insertRes  = confirmInvoiceCaseResMapper.setConfirmInvoiceCaseRes(confirmInvoiceCaseRes);
        if(insertRes == 1){
            Log.info("审核发票结果保存成功");
        }else {
            Log.info("审核发票结果保存失败");
        }
    }

    public Invoice confirmInvoiceImpl(User user, Invoice invoice){
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        List<Invoice> list = getInvoiceInfo.getInvoiceId(user);
        for(Invoice invoice1 : list){
            if(invoice1.getInvoiceNo().equals(invoice.getInvoiceNo())){
                invoice.setId(invoice1.getId());
            }
        }
        ConfirmInvoice confirmInvoice = new ConfirmInvoice();
        confirmInvoice.testConfirmBilling(user,invoice);
        return invoice;
    }


}
