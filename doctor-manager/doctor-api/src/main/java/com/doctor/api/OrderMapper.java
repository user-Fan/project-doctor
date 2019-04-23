package com.doctor.api;

import com.doctor.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    //查询医生当天的订单数量
    int selectCountbyTimeAndDoctorId(@Param("doctorId")Integer doctorId,@Param("date") Date date);

    //查询所有未支付订单
    List <Order> getOKOrderByUserid(Integer userId);

    //查询用户所有订单
    List <Order> orderLoge(Integer userId);

    List<Order> selectOrderList();

    //查询医生今天的所有预约单
    List<Order> getAllOrderByDoctorIDAndTime(Integer doctorId);
}