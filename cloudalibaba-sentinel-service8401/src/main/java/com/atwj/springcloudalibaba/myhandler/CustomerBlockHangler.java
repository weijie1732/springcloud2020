package com.atwj.springcloudalibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atwj.springcloud.entities.CommonResult;

public class CustomerBlockHangler {
    public static CommonResult handlerException(BlockException Exception){
        return new CommonResult(4444,"global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException Exception){
        return new CommonResult(4444,"global handlerException----2");
    }
}
