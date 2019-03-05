package com.doctor.service;

import com.doctor.Iservice.IOrderService;
import com.doctor.api.DoctorMapper;
import com.doctor.api.OrderMapper;
import com.doctor.api.TbUserMapper;
import com.doctor.api.Vo.OrderVo;
import com.doctor.common.Formate;
import com.doctor.common.OrderUtil;
import com.doctor.pojo.Doctor;
import com.doctor.pojo.Order;
import com.doctor.pojo.User;
import com.doctor.pojo.vo.OrderListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TbUserMapper userMapper;
    @Autowired
    DoctorMapper doctorMapper;



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
      return getOrderVoList(list);
    }

    @Override
    public int toapply( Integer id,Integer point,String type) {
        Order order = new Order();
        order.setId(id);
        if(OrderUtil.ADD_CODE.equals(type)){
            //设置退款成功
            order.setPay(2);
        }else if (OrderUtil.REDUCE_CODE.equals(type)){
            //1.支付状态
            order.setPay(1);
            //2.支付积分
            order.setOrderPoint(200);
            //查询此订单的医生id，和就诊时间
            Order orderDoctorIdAndDate= orderMapper.selectByPrimaryKey(id);
            //医生id获取
            int doctorId =orderDoctorIdAndDate.getDoctorId();
            //就诊时间获取
            Date date = orderDoctorIdAndDate.getEndTime();
            //查询就诊时间内的此医生的所用订单
            int num = orderMapper.selectCountbyTimeAndDoctorId(doctorId,date);
            //3.设置就诊号码
            order.setOrderNumber(Integer.toString(num));
            //4.设置就诊状态为挂号成功
            order.setStatus(1);
        }
        int result= orderMapper.updateByPrimaryKey(order);

        return  result;
    }

    @Override
    public List<OrderVo> getPayOrderByUserid(Integer userId) {
        List<Order> list = orderMapper.getPayOrderByUserid(userId);
        return getOrderVoList(list);
    }

    private List<OrderVo> getOrderVoList(List<Order> list){
        List<OrderVo> result = new ArrayList<>();
        for (int i =0;i<list.size();i++){
            OrderVo orderVo = new OrderVo();
            orderVo.setDoctorName(list.get(i).getDoctorName());
            orderVo.setPutReason(list.get(i).getPutReason());
            orderVo.setBeginTime(Formate.getStringDate(list.get(i).getBeginTime()));
            orderVo.setEndTime(Formate.getStringDate(list.get(i).getEndTime()));
            orderVo.setId(list.get(i).getId());
            //设置订单积分
            orderVo.setOrderPoint(200);
            //设置订单挂号号码
            orderVo.setOrderNumber(list.get(i).getOrderNumber());
            //设置订单状态描述
            if (null!=list.get(i).getStatus()&&null!=list.get(i).getPay()){
                String orderMsg = getStatusMsg(list.get(i).getPay(),list.get(i).getStatus());
                orderVo.setStatusMsg(orderMsg);
            }
            result.add(orderVo);

        }
        return result;
    }

    @Override
    public int quxiaoOrder(Integer id) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(-1);
       return orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public List<OrderVo> getOKOrderByUserid(Integer userId) {
        List<Order> list = orderMapper.getOKOrderByUserid(userId);
        return getOrderVoList(list);
    }

    @Override
    public List<OrderVo> orderLoge(Integer userId) {
        List<Order> list = orderMapper.orderLoge(userId);
        return getOrderVoList(list);
    }

    private String getStatusMsg(int pay,int status){
     if (-1==status){
         return "已取消订单";
     }else if(2==status){
         return "已完成订单";
     }else if(1==pay&&1==status){
         return "已支付订单";
     }else if(2==pay){
         return "已退款订单";
     }else if (0==status&&0==pay){
         return "未支付订单";
     }
     return "错误订单";
    }

    @Override
    public List<OrderListVO> orderList() {
        try {
            List<Order> orders = orderMapper.selectOrderList();
            List<OrderListVO> orderListVOS = new ArrayList<>();
            for (int i = 0; i <orders.size() ; i++) {
                OrderListVO orderListVO = new OrderListVO();
                User user = userMapper.findByIds(orders.get(i).getUserId());
                Doctor doctor = doctorMapper.findByIds(orders.get(i).getDoctorId());
                //用户姓名
                orderListVO.setUserName(user.getUserName());
                //医生姓名
                orderListVO.setDoctorName(doctor.getDoctorName());
                //挂号号码
                if (orders.get(i).getOrderNumber()==null){
                    orderListVO.setRegistrationNum("无");
                }else {
                    orderListVO.setRegistrationNum(orders.get(i).getOrderNumber());
                }
                //挂号时间
                orderListVO.setRegistrationTime(Formate.getStringDate(orders.get(i).getBeginTime()));
                //就诊时间
                orderListVO.setAttendanceTime(Formate.getStringDate(orders.get(i).getEndTime()));
                //挂号原因
                orderListVO.setRegistrationCause(orders.get(i).getPutReason());
                //订单状态 交易积分
                if (orders.get(i).getPay()==0 && orders.get(i).getStatus()==0){
                    orderListVO.setOrderState("未支付");
                    orderListVO.setIntegral("支付0积分");
                }else if (orders.get(i).getPay()==0 && orders.get(i).getStatus()==-1){
                    orderListVO.setOrderState("未支付,取消订单");
                    orderListVO.setIntegral("支付0积分");
                }else if (orders.get(i).getPay()==1 && orders.get(i).getStatus()==1){
                    orderListVO.setOrderState("已支付,挂号成功");
                    orderListVO.setIntegral("支付200积分");
                }else if (orders.get(i).getPay()==1 && orders.get(i).getStatus()==2){
                    orderListVO.setOrderState("已支付,订单完成");
                    orderListVO.setIntegral("200积分已支付");
                }else if (orders.get(i).getPay()==2 && orders.get(i).getStatus()==1){
                    orderListVO.setOrderState("已支付,取消订单");
                    orderListVO.setIntegral("退回200积分");
                }else {
                    orderListVO.setOrderState("订单错误");
                    orderListVO.setIntegral("0积分");
                }
                orderListVOS.add(orderListVO);
            }
            return orderListVOS;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
