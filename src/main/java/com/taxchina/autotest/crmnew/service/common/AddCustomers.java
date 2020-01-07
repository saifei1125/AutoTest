package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.taxchina.autotest.crmnew.service.entity.CustLib;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.EncodeConvert;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import com.taxchina.autotest.crmnew.service.utils.RestAssuredUtil;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

/** 添加用户 */
public class AddCustomers {
    /**添加个人客户*/
    public Customer testAddPersonalCustomers(User user, Customer customer){
        String url = TEST_DOMAIN+"/crm/customer/add";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName = encode.urlEncoder(customer.getCustName(), "utf-8");
        int custImplevel = customer.getCustImplevel();
        String custPhoneNumber = encode.urlEncoder(customer.getCustPhoneNumber(), "utf-8");
        String custEmail = encode.urlEncoder(customer.getCustEmail(),"utf-8");
        String regAddr = encode.urlEncoder(customer.getRegAddr(),"utf-8");
        String custProvince = encode.urlEncoder("北京市", "utf-8");
        String custCity = encode.urlEncoder("北京市", "utf-8");
        String custTown = encode.urlEncoder("东城区", "utf-8");
        List<CustLib> custLibs = user.getCustLibs();
        CustLib custLib = custLibs.get(0);
        int libId = custLib.getId();
        String libName = encode.urlEncoder(custLib.getTitle(), "utf-8");
        String param = "custName="+custName+"&custPhoneNumber="+custPhoneNumber+"&custEmail="+custEmail+"&regAddr="+regAddr+"&custImplevel="+custImplevel+"&libId="+libId+"&params%5BbranchLen%5D=&custType=0&custProvinceNo=110000&custProvince="+custProvince+"&custCityNo=110100&custCity="+custCity+"&custTownNo=110101&custTown="+custTown+"&libName="+libName+"";
//        Log.info(param);
        Map<String,String> headers = user.getHeaders();
        String res = HttpClientUtil.doPost(url, param, headers,"utf-8");
        Log.info("添加客户请求返回结果："+res);
        if(res!=null) {
            int custId = (int) JSONPath.eval(res,"$.data");
            customer.setCustId(custId);
            return customer;
        }else {
            return null;
        }
    }

    /**通过用例编号，添加个人客户*/
    public String testAddPersonalCustomersByCase(User user, Customer customer){
        String url = TEST_DOMAIN+"/crm/customer/add";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName = encode.urlEncoder(customer.getCustName(), "utf-8");
        int custType = customer.getCustType();
        int custImplevel = customer.getCustImplevel();
        String custPhoneNumber = encode.urlEncoder(customer.getCustPhoneNumber(), "utf-8");
        String custEmail = encode.urlEncoder(customer.getCustEmail(),"utf-8");
        String regAddr = encode.urlEncoder(customer.getRegAddr(),"utf-8");
        String custProvince = encode.urlEncoder("北京市", "utf-8");
        String custCity = encode.urlEncoder("北京市", "utf-8");
        String custTown = encode.urlEncoder("东城区", "utf-8");
        int custLib = customer.getLibId();
        String custLibName = encode.urlEncoder(customer.getLibName(), "utf-8");
        String param = "custName="+custName+"&custPhoneNumber="+custPhoneNumber+"&custEmail="+custEmail+"&regAddr="+regAddr+"&custImplevel="+custImplevel+"&libId="+custLib+"&params%5BbranchLen%5D=&custType="+custType+"&custProvinceNo=110000&custProvince="+custProvince+"&custCityNo=110100&custCity="+custCity+"&custTownNo=110101&custTown="+custTown+"&libName="+custLibName;
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        return res;
    }
    public String testAddPersonalCustomersByCaseNew(User user, Customer customer){
        String url = TEST_DOMAIN+"/crm/customer/add";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName = encode.urlEncoder(customer.getCustName(), "utf-8");
        int custType = customer.getCustType();
        int custImplevel = customer.getCustImplevel();
        String custPhoneNumber = encode.urlEncoder(customer.getCustPhoneNumber(), "utf-8");
        String custEmail = encode.urlEncoder(customer.getCustEmail(),"utf-8");
        String regAddr = encode.urlEncoder(customer.getRegAddr(),"utf-8");
        String custProvince = encode.urlEncoder("北京市", "utf-8");
        String custCity = encode.urlEncoder("北京市", "utf-8");
        String custTown = encode.urlEncoder("东城区", "utf-8");
        int custLib = customer.getLibId();
        String custLibName = encode.urlEncoder(customer.getLibName(), "utf-8");
        String param = "custName="+custName+"&custPhoneNumber="+custPhoneNumber+"&custEmail="+custEmail+"&regAddr="+regAddr+"&custImplevel="+custImplevel+"&libId="+custLib+"&params%5BbranchLen%5D=&custType="+custType+"&custProvinceNo=110000&custProvince="+custProvince+"&custCityNo=110100&custCity="+custCity+"&custTownNo=110101&custTown="+custTown+"&libName="+custLibName;
//        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        Response response = RestAssuredUtil.postRequestWithHeadersAndStringParameters(url,param,user.getHeaders());
        return response.getBody().print();
    }

    /**添加企业客户*/
    public Customer addBusinessCustomers(User user,String custName){
        EncodeConvert<String> encode = new EncodeConvert<>();
        List<CustLib> custLibs = user.getCustLibs();
        CustLib custLib = custLibs.get(0);
        int libId = custLib.getId();
        String libName = encode.urlEncoder(custLib.getTitle(), "utf-8");
        String url = TEST_DOMAIN + "/crm/customer/add";
        GetCustInfo getCustInfo = new GetCustInfo();
        Customer customer = getCustInfo.getCustInfo(user,custName);
        customer.setLibId(libId);
        customer.setLibName(libName);
        String custName1 = encode.urlEncoder(customer.getCustName(), "utf-8");
        String custLegalPerson = encode.urlEncoder(customer.getCustLegalPerson(),"utf-8");//企业法人
        String custRegBankroll = encode.urlEncoder(customer.getCustRegBankroll(),"utf-8");//企业注册资本
        String custAlias = "";//客户简称，客户别名
        String custHistoryName = encode.urlEncoder(customer.getCustHistoryName(),"utf-8");//企业曾用名
        String custCreditCode = encode.urlEncoder(customer.getCustCreditCode(),"utf-8");//统一社会信用代码
        String regStatus = encode.urlEncoder(customer.getRegStatus(),"utf-8");//客户经营状态
        String companyOrgtype = encode.urlEncoder(customer.getCompanyOrgtype(),"utf-8");//公司类型（企业性质）
        String regInstitute = encode.urlEncoder(customer.getRegInstitute(),"utf-8");//登记机关
        String regAddr = encode.urlEncoder(customer.getRegAddr(),"utf-8");//企业注册地址
        String custWebsite = encode.urlEncoder(customer.getCustWebsite(),"utf-8");//客户网站
        String custEmail = encode.urlEncoder(customer.getCustEmail(),"utf-8");//客户邮箱
        String businessScope = encode.urlEncoder(customer.getBusinessScope(),"utf-8");//经营范围
        int isMicroCompany = 0;//是否为小微企业； 0不是 1是 默认0
        int custImplevel = 1;//客户重要级别 1一般客户；2重点跟踪客户；3战略客户 ;4 长期铺垫客户;5竞争机构客户 默认1
        String industryCategory = encode.urlEncoder(customer.getIndustryCategory(),"utf-8");//行业门类（企查查）
        String custIndustry = "科技";//客户所在行业
        String custProvince = encode.urlEncoder("北京市", "utf-8");
        String custCity = encode.urlEncoder("北京市", "utf-8");
        String custTown = encode.urlEncoder("东城区", "utf-8");
        String param = "custName="+custName1+"&custLegalPerson="+custLegalPerson+"&custRegBankroll="+custRegBankroll+"&custAlias="+custAlias+"&custHistoryName="+custHistoryName+"&custCreditCode="+custCreditCode+"&regStatus="+regStatus+"&companyOrgtype="+companyOrgtype+"&regInstitute="+regInstitute+"&regAddr="+regAddr+"&custWebsite="+custWebsite+"&custPhoneNumber="+customer.getCustPhoneNumber()+"&custEmail="+custEmail+"&businessScope="+businessScope+"&isMicroCompany="+isMicroCompany+"&custImplevel="+custImplevel+"&libId="+libId+"&industryCategory="+industryCategory+"&custIndustry="+custIndustry+"&params%5BbranchLen%5D=&custType=1&custProvinceNo=110000&custProvince="+custProvince+"&custCityNo=110100&custCity="+custCity+"&custTownNo=110101&custTown="+custTown+"&libName="+libName;
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        Log.info("添加客户请求返回结果："+res);
        if(res!=null) {
            JSONObject jsonObject = JSON.parseObject(res);
            int custId = Integer.parseInt(jsonObject.get("data").toString());
            customer.setCustId(custId);
            return customer;
        }else {
            return null;
        }
    }

    /**企业客户判重*/
    public String checkCustNameUnique(User user,String custName){
        String url = TEST_DOMAIN + "/crm/customer/checkCustNameUnique";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName1 = encode.urlEncoder(custName, "utf-8");
        String param = "custType=1&custName="+custName1;
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        return res;
    }
    public String getCustInfo(User user,Customer customer){
        String url = TEST_DOMAIN + "/crm/customer/getCustInfo";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName = encode.urlEncoder(customer.getCustName(), "utf-8");
        String param = "&custName="+custName;
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        return res;
    }

    /**模糊搜索企业客户*/
    public List<String> likeSearchCustName(User user,String keyword){
        String url = TEST_DOMAIN + "/crm/customer/likeSearchCustName";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName = encode.urlEncoder(keyword, "utf-8");
        String param = "&custName="+custName;
        String response = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        List<String> list = (List<String>) JSONPath.eval(response,"$..name");
        return list;
    }


}
