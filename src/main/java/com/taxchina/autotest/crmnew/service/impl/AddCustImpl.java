package com.taxchina.autotest.crmnew.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.dao.entity.AddCustCase;
import com.taxchina.autotest.crmnew.dao.entity.AddCustCaseRes;
import com.taxchina.autotest.crmnew.dao.entity.CheckCustCase;
import com.taxchina.autotest.crmnew.dao.entity.CheckCustCaseRes;
import com.taxchina.autotest.crmnew.dao.mapper.AddCustCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.AddCustCaseResMapper;
import com.taxchina.autotest.crmnew.dao.mapper.CheckCustCaseMapper;
import com.taxchina.autotest.crmnew.dao.mapper.CheckCustCaseResMapper;
import com.taxchina.autotest.crmnew.service.common.AddCustomers;
import com.taxchina.autotest.crmnew.service.common.GetCustInfo;
import com.taxchina.autotest.crmnew.service.entity.CustLib;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

/** 添加客户*/
@Service
public class AddCustImpl {
    @Autowired
    private AddCustCaseMapper addCustCaseMapper;
    @Autowired
    private AddCustCaseResMapper addCustCaseResMapper;

    public AddCustCase insertAddPersonalCustCase(AddCustCase addCustCase) {
        addCustCaseMapper.setAddCustCase(addCustCase);
        return addCustCase;
    }

    public AddCustCaseRes runAddPersonalCustCaseById(User user, int caseId) {
        AddCustCase addCustCase = addCustCaseMapper.getAddCustCaseById(caseId);
        //用例执行
        Customer customer = new Customer();
        customer.setCustName(addCustCase.getCustName());
        customer.setCustType(addCustCase.getCustType());
        customer.setCustImplevel(addCustCase.getCustImplevel());
        customer.setCustPhoneNumber(addCustCase.getCustPhone());
        customer.setCustEmail(addCustCase.getCustEmail());
        customer.setRegAddr(addCustCase.getRegAddr());
        customer.setLibId(addCustCase.getLibId());
        customer.setLibName(addCustCase.getLibName());
        AddCustomers addCustomers = new AddCustomers();
        String addRes = addCustomers.testAddPersonalCustomersByCase(user, customer);
        Log.info("添加客户请求返回结果：" + addRes);
        if (addRes != null) {
            int custid = 0;
            JSONObject responseBody = JSONObject.parseObject(addRes);
            if (responseBody.getString("data") != null) {
                custid = Integer.parseInt(responseBody.getString("data"));
                customer.setCustId(custid);
                addCustCase.setCustId(custid);

                Boolean res = checkCustInfo(user,customer);
                if(res==true){
                    Log.info("新加客户已添加在列表中");
                }else {
                    Log.error("新加客户在列表中没找到！！！");
                }
            }
        }
        //回写登录结果
        AddCustCaseRes addCustCaseRes = new AddCustCaseRes();
        addCustCaseRes.setCaseId(addCustCase.getCaseId());
        addCustCaseRes.setResult(addRes);
        addCustCaseRes.setCustId(customer.getCustId());
        int insertRes = addCustCaseResMapper.setAddCustCaseRes(addCustCaseRes);
        if (insertRes == 1) {
            Log.info("添加客户结果保存成功");
        } else {
            Log.info("添加客户保存失败");
        }
        return addCustCaseRes;
    }

    public AddCustCaseRes runAddPersonalCustCaseByIdNew(User user, int caseId) {
        AddCustCase addCustCase = addCustCaseMapper.getAddCustCaseById(caseId);
        //用例执行
        Customer customer = new Customer();
        customer.setCustName(addCustCase.getCustName());
        customer.setCustType(addCustCase.getCustType());
        customer.setCustImplevel(addCustCase.getCustImplevel());
        customer.setCustPhoneNumber(addCustCase.getCustPhone());
        customer.setCustEmail(addCustCase.getCustEmail());
        customer.setRegAddr(addCustCase.getRegAddr());
        customer.setLibId(addCustCase.getLibId());
        customer.setLibName(addCustCase.getLibName());
        AddCustomers addCustomers = new AddCustomers();
        String addRes = addCustomers.testAddPersonalCustomersByCaseNew(user, customer);
        Log.info("添加客户请求返回结果：" + addRes);
        if (addRes != null) {
            int custid = 0;
            JSONObject responseBody = JSONObject.parseObject(addRes);
            if (responseBody.getString("data") != null) {
                custid = Integer.parseInt(responseBody.getString("data"));
                customer.setCustId(custid);
                addCustCase.setCustId(custid);

                Boolean res = checkCustInfo(user,customer);
                if(res==true){
                    Log.info("新加客户已添加在列表中");
                }else {
                    Log.error("新加客户在列表中没找到！！！");
                }
            }
        }
        //回写登录结果
        AddCustCaseRes addCustCaseRes = new AddCustCaseRes();
        addCustCaseRes.setCaseId(addCustCase.getCaseId());
        addCustCaseRes.setResult(addRes);
        addCustCaseRes.setCustId(customer.getCustId());
        int insertRes = addCustCaseResMapper.setAddCustCaseRes(addCustCaseRes);
        if (insertRes == 1) {
            Log.info("添加客户结果保存成功");
        } else {
            Log.info("添加客户保存失败");
        }
        return addCustCaseRes;
    }


    //自动生成用例并执行
    public Customer addPersonalCustByCase(User user) {
        //生成添加客户的用例，用户信息随机生成
        GetCustLibImpl getCustLib = new GetCustLibImpl();
        String custLib = getCustLib.getCustLibToUserNew(user).getCustlib();
        List<CustLib> list = JSONArray.parseArray(custLib, CustLib.class);
        //默认用第一个客户库
        CustLib lib = list.get(0);
        int libId = lib.getId();
        String libName = lib.getTitle();
        String name = "测试个人客户" + new Random().nextInt(10000);
        String phoneNo = "1500000" + (int) (Math.random() * (9999 - 1000 + 1) + 1000);
        AddCustCase addCustCase = new AddCustCase();
        addCustCase.setCustName(name);
        addCustCase.setCustPhone(phoneNo);
        addCustCase.setCustImplevel(1);
        addCustCase.setRegAddr("测试地址");
        addCustCase.setCustEmail(phoneNo + "@123.com");
        addCustCase.setLibId(libId);
        addCustCase.setLibName(libName);
        int res = addCustCaseMapper.setAddCustCase(addCustCase);
        if (res == 1) {
            Log.info("用例添加成功，用例id：" + addCustCase.getCaseId());
        } else {
            Log.info("用例添加失败！！");
        }
        //用例执行
        Customer customer = new Customer();
        customer.setCustName(addCustCase.getCustName());
        customer.setCustImplevel(addCustCase.getCustImplevel());
        customer.setCustPhoneNumber(addCustCase.getCustPhone());
        customer.setCustEmail(addCustCase.getCustEmail());
        customer.setRegAddr(addCustCase.getRegAddr());
        customer.setLibId(addCustCase.getLibId());
        customer.setLibName(addCustCase.getLibName());
        AddCustomers addCustomers = new AddCustomers();
        String addRes = addCustomers.testAddPersonalCustomersByCase(user, customer);
        Log.info("添加客户请求返回结果：" + addRes);
        if (addRes != null) {
            int custid = 0;
            JSONObject responseBody = JSONObject.parseObject(addRes);
            if (responseBody.getString("data") != null) {
                custid = Integer.parseInt(responseBody.getString("data"));
                customer.setCustId(custid);
                addCustCase.setCustId(custid);

                Boolean b = checkCustInfo(user,customer);
                if(b==true){
                    Log.info("新加客户已添加在列表中");
                }else {
                    Log.error("新加客户在列表中没找到！！！");
                }
            }
        }
        //回写登录结果
        AddCustCaseRes addCustCaseRes = new AddCustCaseRes();
        addCustCaseRes.setCaseId(addCustCase.getCaseId());
        addCustCaseRes.setResult(addRes);
        addCustCaseRes.setCustId(customer.getCustId());
        int insertRes = addCustCaseResMapper.setAddCustCaseRes(addCustCaseRes);
        if (insertRes == 1) {
            Log.info("添加客户结果保存成功");
        } else {
            Log.info("添加客户保存失败");
        }
        return customer;
    }

    public Customer addPersonalCust(User user) {
        GetCustLibImpl getCustLib = new GetCustLibImpl();
        getCustLib.getCustLib(user);

        Customer customer = new Customer();
        Random r = new Random();
        int ran = r.nextInt(1000);
        String name = "测试个人客户" + ran;
        customer.setCustName(name);
        customer.setCustImplevel(2);
        int ran1 = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
        String phoneNo = "1500000" + ran1;
        customer.setCustPhoneNumber(phoneNo);
        customer.setCustEmail(phoneNo + "@123.com");
        customer.setRegAddr("测试地址");
        AddCustomers addCust = new AddCustomers();
        addCust.testAddPersonalCustomers(user, customer);
        return customer;
    }

    public Customer addCustByName(User user, String name) {
        GetCustLibImpl getCustLib = new GetCustLibImpl();
        getCustLib.getCustLib(user);

        Customer customer = new Customer();
        customer.setCustName(name);
        customer.setCustImplevel(2);
        int ran1 = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
        String phoneNo = "1500000" + ran1;
        customer.setCustPhoneNumber(phoneNo);
        customer.setCustEmail(phoneNo + "@123.com");
        customer.setRegAddr("测试地址");
        AddCustomers addCust = new AddCustomers();
        addCust.testAddPersonalCustomers(user, customer);
        return customer;
    }

    public Customer addPersonalCustByPhone(User user, String phoneNo) {
        GetCustLibImpl getCustLib = new GetCustLibImpl();
        getCustLib.getCustLib(user);

        Customer customer = new Customer();
        Random r = new Random();
        int ran = r.nextInt(1000);
        String name = "测试个人客户" + ran;
        customer.setCustName(name);
        customer.setCustImplevel(2);
        customer.setCustPhoneNumber(phoneNo);
        customer.setCustEmail(phoneNo + "@123.com");
        customer.setRegAddr("测试地址");
        AddCustomers addCust = new AddCustomers();
        addCust.testAddPersonalCustomers(user, customer);
        return customer;
    }

    public Customer addPersonalCust(User user, String custName, String phoneNo) {
        GetCustLibImpl getCustLib = new GetCustLibImpl();
        getCustLib.getCustLib(user);
        Customer customer = new Customer();
        customer.setCustName(custName);
        customer.setCustImplevel(2);
        customer.setCustPhoneNumber(phoneNo);
        customer.setCustEmail(phoneNo + "@123.com");
        customer.setRegAddr("测试地址");
        AddCustomers addCust = new AddCustomers();
        addCust.testAddPersonalCustomers(user, customer);
        return customer;
    }

    /**企业客户判重*/
    public boolean checkCustNameUnique(User user, String custName) {
        AddCustomers addCust = new AddCustomers();
        String res = addCust.checkCustNameUnique(user, custName);
        JSONObject jsonObject = JSON.parseObject(res);
        String code = jsonObject.getString("code");
        if (code.equals("0")) {
            return false;
        } else if (code.equals("1")) {
            return true;
        }
        return false;
    }

    @Autowired
    private CheckCustCaseMapper checkCustCaseMapper;
    @Autowired
    private CheckCustCaseResMapper checkCustCaseResMapper;

    /**插入客户判重用例*/
    public CheckCustCase insertCheckCustCase(User user, CheckCustCase checkCustCase) {
        checkCustCaseMapper.setCheckCustCase(checkCustCase);
        Log.info("新增客户判重用例id:" + checkCustCase.getCaseId());
        return checkCustCase;
    }

    /**运行客户判重用例*/
    public CheckCustCaseRes runCheckCustCase(User user, int caseId) {
        //查询用例信息运行
        CheckCustCase checkCustCase = checkCustCaseMapper.getCheckCustCaseById(caseId);
        Log.info("用例编号：" + checkCustCase.getCaseId() + "  客户名：" + checkCustCase.getCustName() + "  预期结果：" + checkCustCase.getCheckcustExpect());
        AddCustomers addCust = new AddCustomers();
        String res = addCust.checkCustNameUnique(user, checkCustCase.getCustName());
        Log.info("判重结果：" + res);
        //回写运行结果
        CheckCustCaseRes checkCustCaseRes = new CheckCustCaseRes();
        checkCustCaseRes.setCaseId(checkCustCase.getCaseId());
        checkCustCaseRes.setResult(res);
        int insertRes = checkCustCaseResMapper.setCheckCustCaseRes(checkCustCaseRes);
        if (insertRes == 1) {
            Log.info("添加客户判重结果保存成功");
        } else {
            Log.info("添加客户判重结果保存失败");
        }
        return checkCustCaseRes;
    }

    /**添加企业客户*/
    public Customer addBusinessCustomer(User user, String custName) {
        GetCustLibImpl getCustLib = new GetCustLibImpl();
        getCustLib.getCustLibNew(user);
        AddCustomers addCustomers = new AddCustomers();
        Customer customer = addCustomers.addBusinessCustomers(user, custName);
        if(customer!=null) {
            Boolean b = checkCustInfo(user, customer);
            if (b == true) {
                Log.info("新加客户已添加在列表中");
            } else {
                Log.error("新加客户在列表中没找到！！！");
            }
        }
        return customer;
    }

    /**通过关键字模糊搜索，查询企业信息*/
    public List<String> likeSearchCustName(User user,String keyword) {
        AddCustomers addCustomers = new AddCustomers();
        List<String> names = addCustomers.likeSearchCustName(user,keyword);
        if(names!=null){
            return names;
        }else {
            Log.error("通过模糊搜索，没有查询到企业信息！！！");
        }
        return null;
    }


    /**通过关键字模糊搜索，查询企业信息并添加企业客户*/
    public Customer addBusinessCustByLikeSearchCustName(User user,String keyword){
        List<String> names = likeSearchCustName(user,keyword);
        if(!names.isEmpty() && names!=null) {
            for (String name : names) {
                if (checkCustNameUnique(user, name) != true) {
                    Customer customer = addBusinessCustomer(user, name);
                    if(customer!=null) {
                        Boolean b = checkCustInfo(user, customer);
                        if (b == true) {
                            Log.info("新加客户已添加在列表中");
                        } else {
                            Log.error("新加客户在列表中没找到！！！");
                        }
                    }
                    return customer;
                } else {
                    Log.info(name + "--已存在库中");
                    continue;
                }
            }
            Log.error("企业信息已全部存在库中，没有可添加企业！！！");
            return null;
        }else {
            Log.error("根据关键字未查询找到企业信息！！！");
            return null;
        }
    }

    /**验证新加客户与客户列表查询结果是否一致*/
    public Boolean checkCustInfo(User user,Customer customer){
        Boolean b = false;
        GetCustInfo getCustInfo = new GetCustInfo();
        //查找客户信息，调用3次，如果还没有找到对应客户就返回
        for(int i =0; i<3; i++){
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                Log.error(e);
            }
            String res = getCustInfo.getCustListInfoByName(user,customer.getCustName());
            Log.info(res);
            if(res!=null){
                JSONObject jo = JSON.parseObject(res);
                String rows = jo.getString("rows");
                List<Customer> list = JSONObject.parseArray(rows,Customer.class);
                for(Customer cust:list){
                    if(cust.getCustId()==customer.getCustId()){
                        Assert.assertEquals(cust.getCustName(),customer.getCustName());
                        Assert.assertEquals(cust.getLibId(),customer.getLibId());
                        return true;
                    }
                }
            }
        }
        return b;
    }


}























