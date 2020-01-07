package com.taxchina.autotest.crmnew.service.impl;

import com.taxchina.autotest.crmnew.dao.entity.AddInvoiceCase;
import com.taxchina.autotest.crmnew.dao.entity.AddInvoiceCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.AddInvoiceCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.AddInvoiceCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.AddInvoice;
import com.taxchina.autotest.crmnew.service.common.GetInvoiceInfo;
import com.taxchina.autotest.crmnew.service.common.GetUserInfo;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Invoice;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.taxchina.autotest.crmnew.service.utils.HttpClientUtil.getEntity;
@Service
public class AddInvoiceImpl {
    @Autowired
    private AddInvoiceCaseMapper addInvoiceCaseMapper;
    @Autowired
    private AddInvoiceCaseResMapper addInvoiceCaseResMapper;

    public AddInvoiceCase insertAddInvoiceCase(AddInvoiceCase addInvoiceCase){
        addInvoiceCaseMapper.setAddInvoiceCase(addInvoiceCase);
        Log.info("新增添加发票用例id:"+addInvoiceCase.getCaseId());
        return addInvoiceCase;
    }
    public AddInvoiceCaseRes runAddInvoiceCaseById(User sell, int caseId){
        AddInvoiceCase addInvoiceCase = addInvoiceCaseMapper.getAddInvoiceCaseById(caseId);
        //提交发票
        AddInvoice addInvoice = new AddInvoice();
        CloseableHttpResponse response = addInvoice.addInvoiceByCase(sell,addInvoiceCase);
        String res = getEntity(response,"utf-8");
        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("出错啦！！！错误信息："+e.getMessage());
        }
        Log.info("添加发票请求返回结果："+ res);
        //回写发票申请结果
        AddInvoiceCaseRes addInvoiceCaseRes = new AddInvoiceCaseRes();
        addInvoiceCaseRes.setCaseId(addInvoiceCase.getCaseId());
        addInvoiceCaseRes.setResult(res);
        int insertRes = addInvoiceCaseResMapper.setAddInvoiceCaseRes(addInvoiceCaseRes);
        if(insertRes == 1){
            Log.info("添加发票请求结果保存成功");
        }else {
            Log.info("添加发票请求结果保存失败");
        }
        return addInvoiceCaseRes;
    }

    public Invoice addInvoice(User user, Customer customer){
        //生成开票用例
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        Invoice invoice = getInvoiceInfo.getInvoiceInfo(user,customer);
        invoice.setInvoiceDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        AddInvoiceCase addInvoiceCase = new AddInvoiceCase();
        addInvoiceCase.setInvoiceNo(invoice.getInvoiceNo());
        addInvoiceCase.setCustomerId(customer.getCustId());
        addInvoiceCase.setCustomerName(customer.getCustName());
        addInvoiceCase.setInvoiceType(1);//开票种类  1 增值税专票  2 增值税普票
        addInvoiceCase.setInvoiceProject(1);
        addInvoiceCase.setCustType(0);
        addInvoiceCase.setInvoiceLabel(0);
        addInvoiceCase.setInvoiceTitle("测试开票"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        addInvoiceCase.setApplyDate(invoice.getInvoiceDate());//开票日期，默认当天
        addInvoiceCase.setApplyAmount("10000");
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        User user1 = getUserInfo.getUserInfoByLoginName(user);
        user.setUsername(user1.getUsername());
        addInvoiceCase.setApplyUserame(user.getUsername());
        addInvoiceCase.setTin("16874657465765");
        addInvoiceCase.setOpeningBank("招商银行");
        addInvoiceCase.setOpeningBankAccount("6222600910047845104");
        addInvoiceCase.setBusinessAddress("测试地址");
        addInvoiceCase.setBusinessTel("67001111");
        addInvoiceCase.setSiuteType("BJK");
        addInvoiceCase.setArrivalMoneyFlag(1);//是否已到款  1 未到款 2 已到款 0 未知
        addInvoiceCaseMapper.setAddInvoiceCase(addInvoiceCase);
        //提交发票
        AddInvoice addInvoice = new AddInvoice();
        CloseableHttpResponse response = addInvoice.addInvoiceByCase(user,addInvoiceCase);
        String res = getEntity(response,"utf-8");
        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("出错啦！！！错误信息："+e.getMessage());
        }
        Log.info("添加发票请求返回结果："+ res);
        //回写发票申请结果
        AddInvoiceCaseRes addInvoiceCaseRes = new AddInvoiceCaseRes();
        addInvoiceCaseRes.setCaseId(addInvoiceCase.getCaseId());
        addInvoiceCaseRes.setResult(res);
        int insertRes = addInvoiceCaseResMapper.setAddInvoiceCaseRes(addInvoiceCaseRes);
        if(insertRes == 1){
            Log.info("添加发票请求结果保存成功");
        }else {
            Log.info("添加发票请求结果保存失败");
        }
        return invoice;
    }

    /**验证发票提交信息与列表查询的结果一致*/
    public Boolean checkInvoiceInfo(User user,Invoice invo,AddInvoiceCase addInvoiceCase){
        Boolean b = false;
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        List<Invoice> list = getInvoiceInfo.getInvoiceId(user);
        for(Invoice invoice : list){
            if(invoice.getInvoiceNo().equals(invo.getInvoiceNo())){
                Assert.assertEquals(addInvoiceCase.getApplyUserame(),invoice.getApplyUserName());
                Assert.assertEquals(addInvoiceCase.getCustomerId(),invoice.getCustomerId());
                Assert.assertEquals(invo.getStatus(),invoice.getStatus());
                return true;
            }
        }
        return b;
    }










    public Invoice addInvoiceImpl(User user, Customer customer){
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        Invoice invoice = getInvoiceInfo.getInvoiceInfo(user,customer);
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);
        user.setUsername(user.getUsername());
        //提交发票申请
        AddInvoice addInvoice = new AddInvoice();
        addInvoice.testAddInvoice(user,customer,invoice);
        return invoice;
    }

    public Invoice addInvoiceByLoginnameAndAmount(User user, Customer customer,String amount){
        GetInvoiceInfo getInvoiceInfo = new GetInvoiceInfo();
        Invoice invoice = getInvoiceInfo.getInvoiceInfo(user,customer);
        invoice.setAmount(amount);
        //拿到业务员信息
        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfoByLoginName(user);
        user.setUsername(user.getUsername());
        //提交发票申请
        AddInvoice addInvoice = new AddInvoice();
        addInvoice.testAddInvoice(user,customer,invoice);

        return invoice;
    }
}
