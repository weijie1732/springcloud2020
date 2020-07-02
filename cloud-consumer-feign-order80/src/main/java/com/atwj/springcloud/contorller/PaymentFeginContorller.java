package com.atwj.springcloud.contorller;

import com.atwj.springcloud.entities.CommonResult;
import com.atwj.springcloud.entities.Payment;
import com.atwj.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentFeginContorller {

    @Resource
    private PaymentFeignService paymentFeignService;

    @RequestMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

}
