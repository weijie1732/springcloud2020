package com.atwj.springcloudalibaba.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contorller {

    @Value("${server.port}")
    private String port;

    @GetMapping("/nacos/payment/{id}")
    public String EchoContorller(@PathVariable("id") Integer id){
        return "Nacos---"+port+"---"+id;
    }
}
