package com.doctor.controller;

import com.doctor.Iservice.IUserPasswordSevice;
import com.doctor.Iservice.IUserService;
import com.doctor.common.MD5Utill;
import com.doctor.common.ReturnUtil;
import com.doctor.pojo.User;
import com.doctor.pojo.UserPassword;
import com.doctor.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@Api(value = "User", tags = {"User"})
@Controller
@Transactional(rollbackFor = {Exception.class})
public class userController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserPasswordSevice userPasswordSevice;

    public final Logger logger = LoggerFactory.getLogger(userController.class);

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    @ResponseBody
    public String userinfopage(HttpServletRequest request, String name, Model model) {
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if (!(userinfo == null) && "".equals(userinfo.getUserName())) {
            model.addAttribute("userinfo", userinfo);
        }
        return "user";
    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    @ResponseBody
    public int getUserName(@RequestParam("params") String params,
                           @RequestParam("type") int type
    ) {
        //当type等于1时查询是否有此手机号
        if (type == 1) {
            if (null == userService.findByPhone(params)) {
                return 2;
            }
            //当type等于2时查询是否有此登录名
        } else if (type == 2) {
            if (null == userService.getUserUserLogin(params)) {
                return 2;
            }
            //当type不等于1和2时 报错
        } else {
            System.out.println("type不正确" + type);
            return 2;
        }
        return 1;
    }

    @ApiOperation(value = "注册", notes = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String register(User user) {
        try {
            if (null == user) {
                return ReturnUtil.toJSONString(1, "没有注册信息", null);
            } else if (null == user.getUserEmail()) {
                return ReturnUtil.toJSONString(1, "请输入邮箱", null);
            } else if (null == user.getUserPhone()) {
                return ReturnUtil.toJSONString(1, "请输入手机号", null);
            } else if (null == user.getUserLogin()) {
                return ReturnUtil.toJSONString(1, "请输入登录名", null);
            } else if (null == user.getPassword()){
                return ReturnUtil.toJSONString(1, "请输入密码", null);
            }else{
                return userService.register(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }
}
