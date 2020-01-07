package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.service.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface UserMapper {

    List<User> getUserList() ;

}
