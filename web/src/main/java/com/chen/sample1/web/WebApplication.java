package com.chen.sample1.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @desc
 * @Author Chentian
 * @date 2021/7/19
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.chen.sample1.web.**.dao")
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
