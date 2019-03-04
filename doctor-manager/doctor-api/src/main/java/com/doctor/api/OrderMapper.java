package com.doctor.api;

import com.doctor.pojo.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    //更新订单
    int updateByPrimaryKey(Order record);

    //查询用户未支付订单
    List <Order> getAllOrderByUserid(Integer userId);

    //查询用户支付订单
    List <Order> getPayOrderByUserid(Integer userId);

}