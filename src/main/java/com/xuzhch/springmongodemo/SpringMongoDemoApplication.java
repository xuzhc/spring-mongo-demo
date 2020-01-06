package com.xuzhch.springmongodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xuzhch.springmongodemo.demo.dao")
public class SpringMongoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoDemoApplication.class, args);
    }

}
