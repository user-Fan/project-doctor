package com.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.doctor.Iservice.IOrderService;
import com.doctor.Iservice.IUserService;
import com.doctor.api.Vo.OrderVo;
import com.doctor.common.Formate;
import com.doctor.common.OrderUtil;
import com.doctor.pojo.Order;
import com.doctor.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    public final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    IOrderService orderService;

    @Autowired
    IUserService userService;

    //创建订单
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    @ResponseBody
    public String createOrder(String order, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if ((userinfo == null) || StringUtils.isBlank(userinfo.getUserLogin())) {
            return "0";
        }
        if (StringUtils.isNotBlank(order)) {
            JSONObject jsonObject = JSONObject.parseObject(order);
            OrderVo orderVo = JSONObject.toJavaObject(jsonObject, OrderVo.class);
            //前台提交信息不为空就创建订单
            if (null != orderVo && null != orderVo.getDoctorId() && StringUtils.isNotBlank(orderVo.getEndTime())) {
                orderVo.setUserId(userinfo.getUserId());
                return Integer.toString(orderService.createOrder(orderVo));
            }
        }
        return "0";
    }

    //查询所有未支付订单
    @RequestMapping("getAllOrderByUserid")
    @ResponseBody
    public List<OrderVo> getOrderList(OrderVo orderVo, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if ((userinfo != null) || StringUtils.isNotBlank(userinfo.getUserLogin())) {
            return orderService.getAllOrderByUserid(userinfo.getUserId());
        }
        return null;
    }

    //支付生成挂号单方法
    @RequestMapping("toapply")
    @ResponseBody
    public String toapply(String id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        Integer idd = Integer.parseInt(id);
        User userInfo_ = userService.getUserUserLogin_v2(userinfo.getUserLogin());
        if ((userinfo != null) && StringUtils.isNotBlank(userinfo.getUserLogin()) && userInfo_.getUserPoint() >= 200) {
            Integer point = userInfo_.getUserPoint() - 200;
            //调用积分扣减方法
            String result = getPayResult(point, userinfo.getUserId(), idd, OrderUtil.REDUCE_CODE);
            session.removeAttribute("userinfo");
            session.setAttribute("userinfo", userInfo_);
            return result;
        }
        return null;
    }


    //退款方法
    @RequestMapping("tonoapply")
    @ResponseBody
    public String notoapply(String id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        Integer idd = Integer.parseInt(id);
        User userInfo_ = userService.getUserUserLogin_v2(userinfo.getUserLogin());
        if ((userinfo != null) && StringUtils.isNotBlank(userinfo.getUserLogin()) && userInfo_.getUserPoint() >= 0) {
            Integer point = userInfo_.getUserPoint() + 200;
            String result = getPayResult(point, userinfo.getUserId(), idd, OrderUtil.ADD_CODE);
            session.removeAttribute("userinfo");
            session.setAttribute("userinfo", userInfo_);
            return result;
        }
        return null;
    }

    //查询所有支付订单
    @RequestMapping("getPayOrderByUserid")
    @ResponseBody
    public List<OrderVo> getPayOrderByUserid(OrderVo orderVo, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if ((userinfo != null) || StringUtils.isNotBlank(userinfo.getUserLogin())) {
            return orderService.getPayOrderByUserid(userinfo.getUserId());
        }
        return null;
    }

    private String getPayResult(Integer point, Integer userId, Integer idd, String type) {
        int result = userService.updateUserPoint(point, userId);
        int result2 = orderService.toapply( idd, 000, type);
        if (result == result2) {
            return "1";
        }
        return null;
    }


    //取消未支付订单方法
    @RequestMapping("quxiaoOrderV2")
    @ResponseBody
    public String quxiaoOrder(String id, HttpServletRequest request) {
        Integer idd = Integer.parseInt(id);
        int result = orderService.quxiaoOrder(idd);
        if (1==result){
            return "1";
        }
        return "0";
    }
}
