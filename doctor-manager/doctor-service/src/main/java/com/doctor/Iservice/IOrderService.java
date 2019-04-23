package com.doctor.Iservice;

import com.doctor.api.Vo.OrderVo;
import com.doctor.pojo.Order;
import com.doctor.pojo.vo.OrderListVO;

import java.util.HashMap;
import java.util.List;

public interface IOrderService {
    //创建订单
    HashMap<String,Object> createOrder(OrderVo orderVo);
    //用户未支付订单
    List<OrderVo> getAllOrderByUserid(Integer userId);

    List<Order> getAllOrderByUserid_v2(Integer userId);
    //用户支付但未就诊的订单
    List<OrderVo> getPayOrderByUserid(Integer userId);
    //支付方法
    int toapply(Integer id,Integer point,String type);
    //取消未支付订单
    int quxiaoOrder(Integer userId);

    List<OrderListVO> orderList();

    //查询用户支付成功未就诊的订单
    List<OrderVo> getOKOrderByUserid(Integer userId);

    //查询用户的多有订单
    List<OrderVo> orderLoge(Integer userId);

    //查询医生今天的所有订单
    List<OrderVo> getAllOrderByDoctorID(Integer doctorId);

}
