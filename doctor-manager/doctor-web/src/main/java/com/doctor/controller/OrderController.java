package com.doctor.controller;

import com.doctor.Iservice.IOrderService;
import com.doctor.api.Vo.OrderVo;
import com.doctor.common.Formate;
import com.doctor.pojo.Order;
import com.doctor.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    public final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    IOrderService orderService;

    @RequestMapping("createOrder")
    @ResponseBody
    public String createOrder(OrderVo orderVo, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if ((userinfo == null)||StringUtils.isBlank(userinfo.getUserLogin())) {
            return "0";
        }
        //前台提交信息不为空就创建订单
        if (null != orderVo && orderVo.getDoctorId() != 0 && StringUtils.isNotBlank(orderVo.getEndTime())) {
            orderVo.setUserId(userinfo.getUserId());
            orderService.createOrder(orderVo);
        }
        return "0";
    }
}
