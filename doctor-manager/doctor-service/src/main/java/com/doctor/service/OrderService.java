package com.doctor.service;

import com.doctor.Iservice.IOrderService;
import com.doctor.api.OrderMapper;
import com.doctor.api.Vo.OrderVo;
import com.doctor.common.Formate;
import com.doctor.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public int createOrder(OrderVo orderVo) {
        //创建后台订单对象
        Order order = new Order();
        //设置订单的用户id
        order.setUserId(orderVo.getUserId());
        //设置订单的医生id
        order.setDoctorId(orderVo.getDoctorId());
        //设置订单的用户挂号原因
        order.setPutReason(orderVo.getPutReason());
        //设置订单的用户就诊时间
        order.setEndTime(Formate.format(orderVo.getEndTime()));
        //设置订单创建时间
        order.setBeginTime(new Date());
        return  orderMapper.insert(order);
    }
}
