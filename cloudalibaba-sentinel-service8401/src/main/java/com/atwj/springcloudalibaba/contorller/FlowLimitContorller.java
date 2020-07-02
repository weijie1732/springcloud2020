package com.atwj.springcloudalibaba.contorller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitContorller {

    @GetMapping("/testA")
    public String testA(){
        return "---testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "---testB";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",blockHandler = "deal_testHotkey") //value是设置热点名称
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                                @RequestParam(value = "p2",required = false) String p2){
        return "----testHotKey";
    }
    //备用方法
    public String deal_testHotkey(String p1, String p2, BlockException BlockException){
        return "----deal_testHotkey";
    }

}
