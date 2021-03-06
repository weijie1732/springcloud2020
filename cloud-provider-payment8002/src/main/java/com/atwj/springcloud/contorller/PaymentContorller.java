package com.atwj.springcloud.contorller;

import com.atwj.springcloud.entities.CommonResult;
import com.atwj.springcloud.entities.Payment;
import com.atwj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentContorller {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;    //获取端口号来标注是哪个模块在运行

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        //log.info("插入结果："+result);

        if (result>0){
            return new CommonResult(200,"插入数据成功,端口号："+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据失败,端口号："+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    @ResponseBody
    public CommonResult get(@PathVariable("id") long id){
        //System.out.println("-----------------："+id);
        Payment payment = paymentService.getPaymentById(id);
        //log.info("O(∩_∩)O哈哈~--插入结果："+payment);

        if (payment != null){
            return new CommonResult(200,"查询成功,端口号："+serverPort,payment);
        }else {
            return new CommonResult(444,"查询失败,端口号："+serverPort,null);
        }
    }

    @GetMapping("/payment/lb")
    public String  getPaymentLb(){
        return serverPort;
    }

}
