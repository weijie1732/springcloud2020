package com.atwj.springcloud.lb;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);  //用来标记第几次运行

    public final int getAndIncrement(){  //此方法用于计算这是第几次访问
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current  >= 2147483647 ? 0 : current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("******第几次访问，次数next: "+next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();  //用访问次数对service数取余
        return serviceInstances.get(index);
    }
}
