package com.atwj.springcloud.service;

import com.atwj.springcloud.entities.Payment;


public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);

}
