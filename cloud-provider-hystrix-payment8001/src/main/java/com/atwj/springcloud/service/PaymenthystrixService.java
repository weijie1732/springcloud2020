package com.atwj.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymenthystrixService {

    public String paymentInfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_ok,id: " +id+"  成功";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = { //fallbackMethod指定备用方法
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")//规定三秒钟以内属于正常，唱超过三秒调用备用方法
    })
    public String paymentInfo_timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);  //程序暂停五秒，模拟超时的情况
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_timeout,id: " +id+"  超时";
    }
    //出现异常时调用的备用方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName( )+"  paymentInfo_TimeOutHandler,id: " +id+"  fallback";
    }


    //-------服务熔断-------------------------------------------------------------------------------------------------------------------------

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期 10秒中有多少次失败
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    //fallback方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能为负数，稍后再试";
    }

}
