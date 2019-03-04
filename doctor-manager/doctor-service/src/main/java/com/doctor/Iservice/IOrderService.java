package com.doctor.Iservice;

import com.doctor.api.Vo.OrderVo;
import com.doctor.pojo.Order;

import java.util.List;

public interface IOrderService {
    //创建订单
    int createOrder(OrderVo orderVo);
    //用户未支付订单
    List<OrderVo> getAllOrderByUserid(Integer userId);
    //用户支付但未就诊的订单
    List<OrderVo> getPayOrderByUserid(Integer userId);
    //支付方法
    int toapply(Integer userId,Integer id,Integer point,String type);
}
