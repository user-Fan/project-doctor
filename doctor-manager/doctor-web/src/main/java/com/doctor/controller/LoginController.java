package com.doctor.controller;

import java.io.IOException;
import java.util.List;

import com.doctor.api.Vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctor.Iservice.IUserService;
import com.doctor.common.MD5Utill;
import com.doctor.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * @Auther: Administrator
 * @Date: 2018/10/20 16:16
 * @Description:
 */
@Controller
public class LoginController {
    public final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    IUserService UserService;

    @Autowired
    DoctorLogin doctorLogin;

    @RequestMapping("/")
    public String getIndex() {
        logger.info("进入TestController----------跳转index.html");
        return "index";
    }

    @RequestMapping("/loginPage")
    public String getLoginPage() {
        logger.info("进入TestController----------跳转登陆页面");
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        logger.info("进入TestController----------跳转index.html");
        return "index";
    }

    @RequestMapping("/index2")
    public String index2() {
        logger.info("进入TestController----------跳转index.html2");
        return "index2";
    }

    @RequestMapping("/index3")
    public String index3() {
        logger.info("进入TestController----------跳转index.html2");
        return "index3";
    }

    @RequestMapping("/login")
    public String login(LoginVo user, Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.info("获得前台提交用户登陆信息" + user.toString());
        if (user == null || StringUtils.isBlank(user.getType())) {
            return "login";
        }
        String type = user.getType();

        if ("医生".equals(type)) {
            return doctorLogin.Login(user, model, request, response);
        }
        List<User> users = UserService.getUserUserLogin(user.getUserLogin());
        logger.info(String.valueOf(users.size()));

        if (users != null && users.size() > 0) {

            for (User user_ : users) {
                try {
                    logger.info("进入校验");
                    if (MD5Utill.verify(user.getUserLogin(), user.getPassword(), user_.getPassword()) && user_.getUserStatus() == 1) {
                        logger.info("登陆成功---用户为-------" + user_.getUserName());
                        //cookie,和session中设置用户信息
                        setUserInfo(user_, request, response, type);
                        response.sendRedirect("/");
                        return "index";
                    } else if (MD5Utill.verify(user.getUserLogin(), user.getPassword(), user_.getPassword()) && user_.getUserStatus() == 2) {
                        setUserInfo(user_, request, response, "管理员");
                        response.sendRedirect("/");
                        return "index3";
                    } else {
                        logger.info("账号密码不匹配" + user.toString());
                        response.sendRedirect("/");
                        return "index";
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    logger.info("md5校验出错" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        try {
            response.sendRedirect("/");
            return "loginPage";
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return "loginPage";
    }

    public void setUserInfo(User user_, HttpServletRequest request, HttpServletResponse response, String type) {
        HttpSession session = request.getSession();
        logger.info("外部的SessionId:" + session.getId());
        session.setAttribute("userLogin", user_.getUserLogin());
        session.setAttribute("password", user_.getPassword());
        session.setAttribute("userinfo", user_);
        session.setAttribute("type", type);

        //把用户账号设置到Cookie中
        Cookie userInfoCookie = new Cookie("userLogin", user_.getUserLogin());
        doctorLogin.setSession(userInfoCookie, session, request, response);
    }
    /***31232132**/
}
