package com.doctor.controller;

import com.doctor.Iservice.IUserService;
import com.doctor.pojo.TestUSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Administrator
 * @Date: 2018/10/20 16:16
 * @Description:
 */
@Controller
public class TestController {
    @Autowired
    IUserService UserService;
    @RequestMapping("action")
    @ResponseBody
    public TestUSer getTestUSer(){
        int a =(1>2)?1:2;
       return UserService.getTestUSer()!=null? UserService.getTestUSer():new TestUSer("1","1","1","1","1");

    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
