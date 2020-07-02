package com.atwj.springcloudalibaba.service.impl;

import com.atwj.springcloudalibaba.dao.AccountDao;
import com.atwj.springcloudalibaba.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("----> account-service中扣减余额开始");

        accountDao.decrease(userId, money); //模拟超时异常，全局事务回滚

        LOGGER.info("----> account-service中扣减余额结束");
    }
}
