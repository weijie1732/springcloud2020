package com.atwj.springalibaba.contorller;

import com.atwj.springalibaba.service.StorageService;
import com.atwj.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageContorller {

    @Autowired
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId,Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功");
    }

}
