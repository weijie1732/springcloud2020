package com.atwj.springcloud.contorller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderContorller {

    public static final String payment_url = "http://CONSUL-PROVIDER-PAYMENT";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/consul/payment")
    public String paymentInfo(){
        String result = restTemplate.getForObject(payment_url+"/consul/payment",String.class);
        return result;
    }

}
