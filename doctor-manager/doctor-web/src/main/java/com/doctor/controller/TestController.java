package com.doctor.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doctor.Iservice.IUserService;
import com.doctor.common.MD5Utill;
import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;

/**
 * @Auther: Administrator
 * @Date: 2018/10/20 16:16
 * @Description:
 */
@Controller
public class TestController {
	public final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	IUserService UserService;

	@RequestMapping("/index")
	@ResponseBody
	public TestUSer getTestUSer() {
		int a = (1 > 2) ? 1 : 2;
		return UserService.getTestUSer() != null ? UserService.getTestUSer() : new TestUSer("1", "1", "1", "1", "1");

	}

	@RequestMapping("/")
	public String index() {
		logger.info("进入TestController跳转index.html");
		return "login";
	}

	@RequestMapping("/login")
	public String test(User user, Model model) {
		logger.info("获得前台提交用户登陆信息" + user.toString());
		List<User> users = UserService.getUserUserLogin(user.getUserLogin());
		if (users != null && users.size() > 0) {
			for (User user_ : users) {
				try {
					logger.info("进入校验");
					if (MD5Utill.verify(user.getUserLogin(), user.getPassword(), user_.getPassword())) {
						logger.info("登陆成功---用户为-------" + user_.getUserName());
						return "index";
					} else {
						logger.info("账号密码不匹配" + user.toString());
					}
					;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.info("md5校验出错" + user.toString());
					e.printStackTrace();
				}
			}
		}
		return "login";
	}

}
