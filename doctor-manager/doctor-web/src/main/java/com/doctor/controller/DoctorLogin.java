package com.doctor.controller;

import com.doctor.Iservice.IDoctorService;
import com.doctor.Iservice.IUserService;
import com.doctor.api.Vo.LoginVo;
import com.doctor.common.MD5Utill;
import com.doctor.pojo.Doctor;
import com.doctor.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class DoctorLogin {
    public final Logger logger = LoggerFactory.getLogger(DoctorLogin.class);

    @Autowired
    private IDoctorService doctorService;

    public String Login(LoginVo user, Model model, HttpServletRequest request, HttpServletResponse response) {
        Doctor  doctor = doctorService.findByAccount(user.getUserLogin());
        if (doctor != null ) {
                try {
                    if (MD5Utill.verify(user.getUserLogin(), user.getPassword(), doctor.getDoctorPassword())) {
                        //cookie,和session中设置用户信息
                        setUserInfo(doctor, request, response);
                        return "index2";
                    } else {
                        response.sendRedirect("/");
                        return "index2";
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        try {
            response.sendRedirect("/");
            return "loginPage";
        } catch (IOException e) {
        }
        return "loginPage";

    }


    private void setUserInfo(Doctor doctor, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        logger.info("外部的SessionId:" + session.getId());
        session.setAttribute("userLogin", doctor.getDoctorAccount());
        session.setAttribute("password", doctor.getDoctorPassword());
        session.setAttribute("userinfo", doctor);
        session.setAttribute("type", "医生");
        //把用户账号设置到Cookie中
        Cookie userInfoCookie = new Cookie("userLogin", doctor.getDoctorAccount());
        setSession(userInfoCookie,session,request,response);
    }

    public void  setSession(Cookie userInfoCookie,HttpSession session, HttpServletRequest request,HttpServletResponse response){
        userInfoCookie.setMaxAge(500);
        userInfoCookie.setPath("/");
        response.addCookie(userInfoCookie);

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    logger.info("cookie_JSESSIONID||" + session.getId());
                    cookie.setValue(session.getId());
                    cookie.setPath("/");
                    cookie.setMaxAge(500);
                    response.addCookie(cookie);
                }
            }
        } else {
            logger.info("cookie_JSESSIONID||" + session.getId());
            Cookie JSESSIONID_ = new Cookie("JSESSIONID", session.getId());
            response.addCookie(userInfoCookie);
        }

    }
}
