package com.atwj.springboot.service;

import org.springframework.stereotype.Component;

@Component
public class FallbackService implements OrderService {
    @Override
    public String PaymentInfo_ok(Integer id) {
        return "---FallBack方法PaymentInfo_ok";
    }

    @Override
    public String PaymentInfo_timeout(Integer id) {
        return "---FallBack方法PaymentInfo_timeout 超时";
    }
}
