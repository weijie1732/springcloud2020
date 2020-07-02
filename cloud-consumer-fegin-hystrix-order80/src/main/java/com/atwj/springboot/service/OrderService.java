package com.atwj.springboot.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = FallbackService.class)
public interface OrderService {

    @RequestMapping("/payment/hystrix/ok/{id}")
    public String PaymentInfo_ok(@PathVariable("id") Integer id);


    @RequestMapping("/payment/hystrix/timeout/{id}")
    public String PaymentInfo_timeout(@PathVariable("id") Integer id);

}
