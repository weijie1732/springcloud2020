package com.atwj.springcloud.contorller;

import com.atwj.springcloud.service.PaymenthystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixContorller {

    @Resource
    private PaymenthystrixService paymenthystrixService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/hystrix/ok/{id}")
    public String PaymentInfo_ok(@PathVariable("id") Integer id){
        return paymenthystrixService.paymentInfo_ok(id);
    }

    @RequestMapping("/payment/hystrix/timeout/{id}")
    public String PaymentInfo_timeout(@PathVariable("id") Integer id){
        return paymenthystrixService.paymentInfo_timeout(id);
    }

    //------------------服务熔断-----------------
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymenthystrixService.paymentCircuitBreaker(id);
        System.out.println("result__:"+result);
        return result;
    }

}
