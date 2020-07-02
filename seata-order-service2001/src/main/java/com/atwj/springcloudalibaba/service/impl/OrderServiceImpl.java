package com.atwj.springcloudalibaba.service.impl;

import com.atwj.springcloudalibaba.dao.OrderDao;
import com.atwj.springcloudalibaba.domain.Order;
import com.atwj.springcloudalibaba.service.AccountService;
import com.atwj.springcloudalibaba.service.OrderService;
import com.atwj.springcloudalibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    public void create(Order order) {
        log.info("----> 开始新建订单");
        orderDao.create(order);

        log.info("----> 订单微服务调用库存，做扣减");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----> 扣减完成");

        log.info("----> 订单微服务调用账户，做扣减");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----> 账户扣减完成");

        //4.修改订单状态
        log.info("----> 开始修改订单状态");
        orderDao.update(order.getUserId(),0);
        log.info("----> 修改订单状态完毕");

        log.info("----> 订单完毕");
    }
}
