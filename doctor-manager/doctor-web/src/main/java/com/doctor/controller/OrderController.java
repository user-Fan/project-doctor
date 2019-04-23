package com.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.doctor.Iservice.IOrderService;
import com.doctor.Iservice.IUserService;
import com.doctor.api.Vo.OrderVo;
import com.doctor.common.Formate;
import com.doctor.common.OrderUtil;
import com.doctor.common.ReturnUtil;
import com.doctor.pojo.Doctor;
import com.doctor.pojo.Order;
import com.doctor.pojo.User;
import com.doctor.pojo.vo.OrderListVO;
import io.swagger.annotations.ApiOperation;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> createOrder(String order, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if ((userinfo == null) || StringUtils.isBlank(userinfo.getUserLogin())) {
            result.put("restlt","0");
            return result;
        }
        if (StringUtils.isNotBlank(order)) {
            JSONObject jsonObject = JSONObject.parseObject(order);
            OrderVo orderVo = JSONObject.toJavaObject(jsonObject, OrderVo.class);
            //前台提交信息不为空就创建订单
            if (null != orderVo && null != orderVo.getDoctorId() && StringUtils.isNotBlank(orderVo.getEndTime())) {
                orderVo.setUserId(userinfo.getUserId());
                result =  orderService.createOrder(orderVo);
                List<Order> list = orderService.getAllOrderByUserid_v2(userinfo.getUserId());
                Integer id = 0;
                for (Order order1:list
                     ) {
                  if(id<order1.getId()) {
                      id =  order1.getId();
                  }
                }
                result.put("id",id);
                return result ;
            }
        }
        result.put("restlt","0");
        return result;
    }

    //查询所有未支付订单
    @RequestMapping("getAllOrderByUserid")
    @ResponseBody
    public List<OrderVo> getOrderList(OrderVo orderVo, HttpServletRequest request) {
        User userinfo = getSessionUser(request);
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
        User userinfo = getSessionUser(request);
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
        User userinfo = getSessionUser(request);
        if ((userinfo != null) || StringUtils.isNotBlank(userinfo.getUserLogin())) {
            return orderService.getPayOrderByUserid(userinfo.getUserId());
        }
        return null;
    }

    private String getPayResult(Integer point, Integer userId, Integer idd, String type) {
        int result = userService.updateUserPoint(point, userId);
        int result2 = orderService.toapply(idd, 000, type);
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
        if (1 == result) {
            return "1";
        }
        return "0";
    }

    //获取session 用户信息
    private User getSessionUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        return userinfo;
    }

    //获取session 医生信息
    private Doctor getSessionDoctor(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Doctor doctor = (Doctor) session.getAttribute("userinfo");
        return doctor;
    }
    //查询所有未就诊订单
    @RequestMapping("getOKOrderByUserid")
    @ResponseBody
    public List<OrderVo> getOKOrderByUserid(OrderVo orderVo, HttpServletRequest request) {

        User userinfo = getSessionUser(request);
        if ((userinfo != null) || StringUtils.isNotBlank(userinfo.getUserLogin())) {
            return orderService.getOKOrderByUserid(userinfo.getUserId());
        }
        return null;
    }

    //查询所有未就诊订单
    @RequestMapping("orderLogeV1")
    @ResponseBody
    public List<OrderVo> orderLoge(OrderVo orderVo, HttpServletRequest request) {

        User userinfo = getSessionUser(request);
        if ((userinfo != null) || StringUtils.isNotBlank(userinfo.getUserLogin())) {
            return orderService.orderLoge(userinfo.getUserId());
        }
        return null;
    }

    //查询所有订单
    @ApiOperation(value = "查询所有订单")
    @RequestMapping(value = "/orderList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String orderList() {
        try {
            List<OrderListVO> listVOS = orderService.orderList();
            return ReturnUtil.toJSONString(0, "查询成功", listVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }
    @RequestMapping("getAllOrderByDoctor")
    @ResponseBody
    public List<OrderVo> getAllOrderByDoctor(HttpServletRequest request){
        Doctor doctor = getSessionDoctor(request);
        if(null!=doctor&&null!=doctor.getDoctorId()){
            orderService.getAllOrderByDoctorID(doctor.getDoctorId());
        }
        return null;
    }
}
