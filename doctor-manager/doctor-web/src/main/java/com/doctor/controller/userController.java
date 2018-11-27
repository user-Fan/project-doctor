package com.doctor.controller;

import com.doctor.Iservice.IUserService;
import com.doctor.common.MD5Utill;
import com.doctor.pojo.User;
import com.doctor.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 */
@Controller
public class userController {
    @Autowired
    private IUserService userService;

    public final Logger logger = LoggerFactory.getLogger(userController.class);
    @RequestMapping("userinfo")
    public String userinfopage(HttpServletRequest request,String name, Model model){
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if (!(userinfo==null)&&"".equals(userinfo.getUserName())){
            model.addAttribute("userinfo",userinfo);
        }
        return "user";
    }

    @RequestMapping(value = "/getUserName" ,method = RequestMethod.GET)
    @ResponseBody
     public int getUserName(@RequestParam("params")String params,
                            @RequestParam("type")int type
                            ){
        if (type == 1){
            if (null == userService.findByPhone(params)){
                return 2;
            }
        }else if (type == 2){
            if (null==userService.getUserUserLogin(params)){
                return 2;
            }
        }else {
            System.out.println("type不正确"+type);
            return 2;
        }
        return 1;
    }

    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    @ResponseBody
    public int register (User user){
        try {
            String pw = MD5Utill.md5Encryp(user.getUserLogin(),user.getPassword());
            user.setPassword(pw);
            userService.register(user);
            User user1 = userService.findByUserLogin(user.getUserLogin()).get(0);
            user.setUserId(user1.getUserId());
            return userService.insertPassword(user);
        } catch (Exception e) {
            e.printStackTrace();
            return  1;
        }
    }
}
