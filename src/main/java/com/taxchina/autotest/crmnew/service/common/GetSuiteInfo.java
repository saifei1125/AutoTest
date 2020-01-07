package com.taxchina.autotest.crmnew.service.common;

import com.alibaba.fastjson.JSONObject;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Product;
import com.taxchina.autotest.crmnew.service.entity.Suite;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

public class GetSuiteInfo {
    private Suite getSuiteInfoById(User user, Customer customer, String suiteId){
        Suite suite = new Suite();
        String url = TEST_DOMAIN + "/crm/contract/signIndex?customerId="+customer.getCustId();
        String response = HttpClientUtil.doGet(url,user.getHeaders(),"utf-8");
//        Log.info(response);
        Document doc = Jsoup.parse(response);
        Elements input = doc.select("input[name=suiteId]");
        Map<Element,String> map = new HashMap<>();
            for (Element element : input){
                map.put(element,element.attr("value"));
            }
        Set<Element> set = map.keySet();
            for(Element element : set){
//                Log.info(map.get(element));
                if(map.get(element).equals(suiteId)) {
                    suite.setSuiteId(map.get(element));
                    suite.setSuiteName(element.parents().get(0).text());
                    suite.setSuiteDesc(element.parents().get(0).select("input[name=suiteDes]").attr("value"));
                    suite.setSuitePrice(element.parents().get(0).select("input[name=suitPrice]").attr("value"));
                    suite.setSuiteType(element.parents().get(0).select("input[name=suitSort]").attr("value"));
                    suite.setTemplateId(element.parents().get(0).select("input[name=contTempId]").attr("value"));
                }
            }
        return suite;
    }

    private Suite getSuiteInfoByName(User user, Customer customer,String suitename){
        Suite suite = new Suite();
        String url = TEST_DOMAIN + "/crm/contract/signIndex?customerId="+customer.getCustId();
        String response = HttpClientUtil.doGet(url,user.getHeaders(),"utf-8");
//        Log.info(response);
        Document doc = Jsoup.parse(response);
        Elements input = doc.select("input[name=suite]");
        for(int i=0;i<input.size();i++){
            String suiteName = input.get(i).parents().get(0).text();
            if (suiteName.equals(suitename)){
                suite.setSuiteName(suiteName);
                suite.setSuiteDesc(input.get(i).parents().select("input[name=suiteDes]").attr("value"));
                suite.setSuiteId(input.get(i).parents().select("input[name=suiteId]").attr("value"));
                suite.setSuitePrice(input.get(i).parents().select("input[name=suitPrice]").attr("value"));
                suite.setSuiteType(input.get(i).parents().select("input[name=suitSort]").attr("value"));
                suite.setTemplateId(input.get(i).parents().select("input[name=contTempId]").attr("value"));
            }
        }
        return suite;
    }

    private Suite getSuiteInfo(User user, Customer customer){
        Suite suite = new Suite();
        String url = TEST_DOMAIN + "/crm/contract/signIndex?customerId="+customer.getCustId();
        String response = HttpClientUtil.doGet(url,user.getHeaders(),"utf-8");
        Document doc = Jsoup.parse(response);
        Elements input = doc.select("input[name=suiteId]");
        String suiteId = input.attr("value");
//        Log.info(suiteId);
        suite.setSuiteId(suiteId);
        Elements input2 = doc.select("input[name=suiteDes]");
        String suiteDes= input2.attr("value");
        suite.setSuiteDesc(suiteDes);
        Elements input3 = doc.select("input[name=suitPrice]");
        String suitPrice= input3.attr("value");
        suite.setSuitePrice(suitPrice);
        Elements input4 = doc.select("input[name=suitSort]");
        String suiteType= input4.attr("value");
        suite.setSuiteType(suiteType);
        Elements input5 = doc.select("input[name=contTempId]");
        String contTempId= input5.attr("value");
        suite.setTemplateId(contTempId);
        return suite;
    }


    public Suite getSuiteProductsInfoById(User user, Customer customer,String suiteId){
        Suite suite = getSuiteInfoById(user,customer,suiteId);
        String url = TEST_DOMAIN + "/crm/suite/getSuitProds/"+suite.getSuiteId();
        String param = "";
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        Log.info("获取套餐产品信息请求返回结果："+res);
        List<Product> productList = JSONObject.parseArray(res,Product.class);
        suite.setProductList(productList);
        return suite;
    }
    public Suite getSuiteProductsInfoByName(User user, Customer customer,String suiteName){
        Suite suite = getSuiteInfoByName(user,customer,suiteName);
        String url = TEST_DOMAIN + "/crm/suite/getSuitProds/"+suite.getSuiteId();
        String param = "";
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        Log.info("获取套餐产品信息请求返回结果："+res);
        List<Product> productList = JSONObject.parseArray(res,Product.class);
        suite.setProductList(productList);
        return suite;
    }
    public Suite getSuiteProductsInfo(User user, Customer customer){
        Suite suite = getSuiteInfo(user,customer);
        String url = TEST_DOMAIN + "/crm/suite/getSuitProds/"+suite.getSuiteId();
        String param = "";
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        Log.info("获取套餐产品信息请求返回结果："+res);
        List<Product> productList = JSONObject.parseArray(res,Product.class);
        suite.setProductList(productList);
        return suite;
    }
}
