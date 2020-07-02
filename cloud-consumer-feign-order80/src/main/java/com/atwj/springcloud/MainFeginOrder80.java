package com.atwj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MainFeginOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(MainFeginOrder80.class,args);
    }
}
