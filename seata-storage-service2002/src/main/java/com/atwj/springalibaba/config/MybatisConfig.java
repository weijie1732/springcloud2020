package com.atwj.springalibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.atwj.springcloud.alibaba.dao"})
public class MybatisConfig {
}
