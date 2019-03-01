package com.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.doctor.Iservice.IUserService;
import com.doctor.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 */
@Controller
public class userController {

    public final Logger logger = LoggerFactory.getLogger(userController.class);

    @Autowired
    private IUserService UserService;

    @RequestMapping("userinfo")
    @ResponseBody
    public User userinfopage(HttpServletRequest request,String name, Model model){
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if (!(userinfo==null)&&StringUtils.isNotBlank(userinfo.getUserName())){
            return userinfo;
        }
        return null;
    }

    @RequestMapping("editUserInfo")
    public String editUserInfo(User user,HttpServletRequest request,String name, Model model){
        logger.info("editUserInfo||修改User{}", JSONObject.toJSONString(user));
        //修改信息为空直接返回登陆页面
        if(user==null){
            return "loginPage";
        }
        HttpSession session = request.getSession(false);
        //获取session中的用户信息
        User userinfo = (User) session.getAttribute("userinfo");
        //判断用户信息是否为空，登录名是否存在
        if (userinfo!=null&& StringUtils.isNotBlank(userinfo.getUserLogin())){
            //获取用户修改信息
            user.setUserLogin(userinfo.getUserLogin());
            int a =UserService.updataUserInfo(user);
            //获取修改以后的用户信息
            User userInfo_  = UserService.getUserUserLogin_v2(userinfo.getUserLogin());
            session.removeAttribute("userinfo");
            session.setAttribute("userinfo",userInfo_);
        }
        //获取更新之后的用户信息重返回用户页面
        return "user";
    }
}
