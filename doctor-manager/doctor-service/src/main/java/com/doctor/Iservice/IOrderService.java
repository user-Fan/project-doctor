package com.doctor.Iservice;

import com.doctor.api.Vo.OrderVo;

public interface IOrderService {
    int createOrder(OrderVo orderVo);
}
