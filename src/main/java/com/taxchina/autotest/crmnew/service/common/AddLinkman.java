package com.taxchina.autotest.crmnew.service.common;
import com.taxchina.autotest.crmnew.dao.entity.AddLinkmanCase;
import com.taxchina.autotest.crmnew.service.entity.Customer;
import com.taxchina.autotest.crmnew.service.entity.Lkm;
import com.taxchina.autotest.crmnew.service.entity.User;
import com.taxchina.autotest.crmnew.service.utils.EncodeConvert;
import com.taxchina.autotest.crmnew.service.utils.HttpClientUtil;
import com.taxchina.autotest.crmnew.service.utils.Log;
import java.util.List;
import static com.taxchina.autotest.crmnew.service.common.Common.TEST_DOMAIN;

/** 添加联系人*/
public class AddLinkman {
    public String addLinkmanByCase(User user, AddLinkmanCase addLinkmanCase){
        String url =  TEST_DOMAIN + "/crm/linkman/add";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName = encode.urlEncoder(addLinkmanCase.getCustName(), "utf-8");
        String lkmName = encode.urlEncoder(addLinkmanCase.getLinkmanName(),"utf-8");
        String lkmMobile = encode.urlEncoder(addLinkmanCase.getLinkmanMobile(),"utf-8");
        String lkmTel = encode.urlEncoder(addLinkmanCase.getLinkmanTel(),"utf-8");
        String lkmPostion = encode.urlEncoder(addLinkmanCase.getLinkmanPostion(),"utf-8");
        String lkmWebchat = encode.urlEncoder(addLinkmanCase.getLinkmanWechat(),"utf-8");
        String lkmQq = encode.urlEncoder(addLinkmanCase.getLinkmanQq(),"utf-8");
        String lkmEmail = encode.urlEncoder(addLinkmanCase.getLinkmanEmail(),"utf-8");
        String lkmAddr = encode.urlEncoder(addLinkmanCase.getLinkmanAddr(),"utf-8");
        String lkmRemake = encode.urlEncoder(addLinkmanCase.getLinkmanRemake(),"utf-8");
        String param = "custId="+addLinkmanCase.getCustId()+"&custName="+custName+"&lkmName="+lkmName+"&lkmSex="
                +addLinkmanCase.getLinkmanSex()+"&lkmMobile="+lkmMobile+"&lkmTel="+lkmTel+"&lkmPostion="+lkmPostion+"&lkmWebchat="
                +lkmWebchat+"&lkmQq="+lkmQq+"&lkmEmail="+lkmEmail+"&lkmAddr"+lkmAddr+"&lkmRemake="+lkmRemake+"&isMainLkm="+addLinkmanCase.getIsMainLkm();
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        return res;
    }

    public Lkm addLinkman(User user, Customer customer, Lkm lkm){
        String url =  TEST_DOMAIN + "/crm/linkman/add";
        EncodeConvert<String> encode = new EncodeConvert<>();
        String custName = encode.urlEncoder(customer.getCustName(), "utf-8");
        String lkmName = encode.urlEncoder(lkm.getLkmName(),"utf-8");
        String lkmMobile = encode.urlEncoder(lkm.getLkmMobile(),"utf-8");
        String lkmTel = encode.urlEncoder(lkm.getLkmTel(),"utf-8");
        String lkmPostion = encode.urlEncoder(lkm.getLkmPostion(),"utf-8");
        String lkmWebchat = encode.urlEncoder(lkm.getLkmWebchat(),"utf-8");
        String lkmQq = encode.urlEncoder(lkm.getLkmQq(),"utf-8");
        String lkmEmail = encode.urlEncoder(lkm.getLkmEmail(),"utf-8");
        String lkmAddr = encode.urlEncoder(lkm.getLkmAddr(),"utf-8");
        String lkmRemake = encode.urlEncoder(lkm.getLkmRemake(),"utf-8");
        String param = "custId="+customer.getCustId()+"&custName="+custName+"&lkmName="+lkmName+"&lkmSex="
                +lkm.getLkmSex()+"&lkmMobile="+lkmMobile+"&lkmTel="+lkmTel+"&lkmPostion="+lkmPostion+"&lkmWebchat="
                +lkmWebchat+"&lkmQq="+lkmQq+"&lkmEmail="+lkmEmail+"&lkmAddr"+lkmAddr+"&lkmRemake="+lkmRemake+"&isMainLkm="+lkm.getIsMainLkm();
        String res = HttpClientUtil.doPost(url, param, user.getHeaders(),"utf-8");
        Log.info("添加联系人请求返回结果："+res);
        //查联系人id
        GetlkmInfo getlkmInfo = new GetlkmInfo();
        getlkmInfo.getlkmInfo(user,customer);
        List<Lkm> lkmList = customer.getLkms();
        lkmList.forEach(lkm2->{
            if(lkm2.getLkmName().equals(lkm.getLkmName())){
                lkm.setLkmId(lkm2.getLkmId());
            }
        });
//        for(Lkm lkm1 : lkmList){
//            if(lkm1.getLkmName().equals(lkm.getLkmName())){
//                lkm.setLkmId(lkm1.getLkmId());
//            }
//        }
        return lkm;
    }
}
