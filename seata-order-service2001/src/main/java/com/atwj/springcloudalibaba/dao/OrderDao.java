package com.atwj.springcloudalibaba.dao;

import com.atwj.springcloudalibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    //新建订单
    void create(Order Order);

    //修改订单状态，从0改为1
    void update(@Param("userId") Long userId,@Param("status") Integer status);

}
