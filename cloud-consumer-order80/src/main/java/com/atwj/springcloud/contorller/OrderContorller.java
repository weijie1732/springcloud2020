package com.atwj.springcloud.contorller;

import com.atwj.springcloud.entities.CommonResult;
import com.atwj.springcloud.entities.Payment;
import com.atwj.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderContorller {


    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalance loadBalance;
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("/consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances==null || instances.size()<=0){
            return null;
        }

        //调用我们自定义的算法，返回要调用的那个ServiceInstance对象
        ServiceInstance serviceInstance = loadBalance.instance(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"payment/lb",String.class);
    }

    //public static final String payment_url = "http://localhost:8001";
    public static final String payment_url = "http://CLOUD-PAYMENT-SERVICE";


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(payment_url+"/payment/create",payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") long id){
        return restTemplate.getForObject(payment_url+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getforentity/{id}")
    public CommonResult<Payment> getgetForEntity(@PathVariable("id") long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(payment_url+"/payment/get/"+id,CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping("/consumer/payment/zipkin")
    public String  PaymentZipkin(){
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/",String.class);
        return result;
    }


}
