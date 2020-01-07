package com.taxchina.autotest.crmnew;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class CrmNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmNewApplication.class, args);
    }

}
