package com.atwj.springcloud.service;

import com.atwj.springcloud.entities.CommonResult;
import com.atwj.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @RequestMapping("payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id);
}
