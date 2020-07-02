package com.atwj.springboot.contorller;

import com.atwj.springboot.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")   //指定全局方法使用的fallback方法
public class OrderContorller {

    @Resource
    private OrderService orderService;

    @RequestMapping("/consumer/payment/hystrix/ok/{id}")
    public String PaymentInfo_ok(@PathVariable("id") Integer id){
        return orderService.PaymentInfo_ok(id);
    }


//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = { //fallbackMethod指定备用方法
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")//规定三秒钟以内属于正常，唱超过三秒调用备用方法
//    })
    @HystrixCommand             //这个标签表示该方法故障时，调用类名上@DefaultProperties所指定的类
    @RequestMapping("/consumer/payment/hystrix/timeout/{id}")
    public String PaymentInfo_timeout(@PathVariable("id") Integer id){
        return orderService.PaymentInfo_timeout(id);
    }

    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id){
        return "我是消费者80，支付系统繁忙";
    }

    //下边是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "信息处理异常，稍后再试";
    }
}
