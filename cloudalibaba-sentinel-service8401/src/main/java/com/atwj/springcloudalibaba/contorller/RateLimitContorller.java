package com.atwj.springcloudalibaba.contorller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atwj.springcloud.entities.CommonResult;
import com.atwj.springcloud.entities.Payment;
import com.atwj.springcloudalibaba.myhandler.CustomerBlockHangler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitContorller {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按照资源名称限流测试ok",new Payment(2020L,"serial001"));

    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t服务不可用");

    }

    @GetMapping("/rateLimit/byUrl")  //url
    @SentinelResource(value = "byUrl")  //资源名称
    public CommonResult byUrl(){
        return new CommonResult(200,"按uri限流测试ok", new Payment(2020L,"serial002"));
    }


    @GetMapping("/rateLimit/customerBlockHangler")  //url
    @SentinelResource(value = "customerBlockHangler",
            blockHandlerClass = CustomerBlockHangler.class,
            blockHandler = "handlerException2")  //资源名称
    public CommonResult customerBlockHangler(){
        return new CommonResult(200,"按客户自定义", new Payment(2020L,"serial003"));
    }

}
