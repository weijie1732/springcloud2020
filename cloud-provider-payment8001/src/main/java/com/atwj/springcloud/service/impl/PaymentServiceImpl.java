package com.atwj.springcloud.service.impl;

import com.atwj.springcloud.entities.Payment;
import com.atwj.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public int create(Payment payment) {
        //return paymentDao.create(payment);
        return 1;
    }
    @Override
    public Payment getPaymentById(Long id) {
        //return paymentDao.getPaymentById(id);
        return new Payment(1,"111");
    }
}
