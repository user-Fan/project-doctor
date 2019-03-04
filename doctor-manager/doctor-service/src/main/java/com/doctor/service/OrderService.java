package com.doctor.service;

import com.doctor.Iservice.IOrderService;
import com.doctor.api.OrderMapper;
import com.doctor.api.TbUserMapper;
import com.doctor.api.Vo.OrderVo;
import com.doctor.common.Formate;
import com.doctor.common.OrderUtil;
import com.doctor.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<OrderVo> getAllOrderByUserid(Integer userId) {
        List<Order> list = orderMapper.getAllOrderByUserid(userId);
        List<OrderVo> result = new ArrayList<>();
        for (int i =0;i<list.size();i++){
            result.add(getOrderVo(list.get(i)));
        }
      return result;
    }

    @Override
    public int toapply(Integer userId, Integer id,Integer point,String type) {
        Order order = new Order();
        order.setId(id);
        if(OrderUtil.ADD_CODE.equals(type)){
            order.setPay(2);
        }else if (OrderUtil.REDUCE_CODE.equals(type)){
            order.setPay(1);
            order.setOrderPoint(200);
        }
        int result= orderMapper.updateByPrimaryKey(order);
        return  result;
    }

    @Override
    public List<OrderVo> getPayOrderByUserid(Integer userId) {
        List<Order> list = orderMapper.getPayOrderByUserid(userId);
        List<OrderVo> result = new ArrayList<>();
        for (int i =0;i<list.size();i++){
            result.add(getOrderVo(list.get(i)));
        }
        return result;
    }

    private OrderVo getOrderVo(Order order){
        OrderVo orderVo = new OrderVo();
        orderVo.setDoctorName(order.getDoctorName());
        orderVo.setPutReason(order.getPutReason());
        orderVo.setBeginTime(Formate.getStringDate(order.getBeginTime()));
        orderVo.setEndTime(Formate.getStringDate(order.getEndTime()));
        orderVo.setId(order.getId());
        //设置订单积分
        orderVo.setOrderPoint(200);
        return orderVo;
    }
}
