package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.SignContractCase;
import com.taxchina.autotest.crmnew.service.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface SignContractCaseMapper {

    int setSignContractCase(SignContractCase signContractCase);

    void setSuiteProduct(SignContractCase signContractCase);

    SignContractCase getSignContractCaseById(int caseId);

    List<Product> getSuiteProductInfo(String suiteId);
}
