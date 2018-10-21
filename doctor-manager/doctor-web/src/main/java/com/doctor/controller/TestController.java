package com.doctor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doctor.Iservice.IUserService;
import com.doctor.pojo.TestUSer;

/**
 * @Auther: Administrator
 * @Date: 2018/10/20 16:16
 * @Description:
 */
@Controller
public class TestController {
	public final Logger logger =  LoggerFactory.getLogger(TestController.class);
    @Autowired
    IUserService UserService;
    @RequestMapping("/index")
    @ResponseBody
    public TestUSer getTestUSer(){
        int a =(1>2)?1:2;
       return UserService.getTestUSer()!=null? UserService.getTestUSer():new TestUSer("1","1","1","1","1");

    }
    @RequestMapping("/")
    public String index(){
    	logger.info("进入TestController跳转index.html");
        return "index";
    }
  
}
