package com.doctor.controller;

import com.doctor.Iservice.IDoctorService;
import com.doctor.Iservice.IUserPasswordSevice;
import com.doctor.Iservice.IUserService;
import com.doctor.common.ReturnUtil;
import com.doctor.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
import javax.servlet.http.HttpSession;

/**
 *
 */
@Api(value = "User", tags = {"User"})
@Controller
@Transactional(rollbackFor = {Exception.class})
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserPasswordSevice userPasswordSevice;
    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IUserService UserService;

    public final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("userinfo")
    @ResponseBody
    public User userinfopage(HttpServletRequest request,String name, Model model){
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if (!(userinfo==null)&&StringUtils.isNotBlank(userinfo.getUserLogin())){
            return userinfo;
        }
        return null;
    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    @ResponseBody
    public int getUserName(@RequestParam("params") String params,
                           @RequestParam("type") int type
    ) {
        //当type等于1时查询是否有此手机号
        if (type == 1) {
            if (null == userService.findByPhone(params) && null == doctorService.findByPhone(params)) {
                return 2;
            }
            //当type等于2时查询是否有此登录名
        } else if (type == 2) {
            if (null == userService.getUserUserLogin(params) && null == doctorService.findByAccount(params)) {
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
            } else if (null == user.getUserAge() || user.getUserAge() == 0) {
                return ReturnUtil.toJSONString(1, "请选择注册人员", null);
            } else if (null == user.getUserEmail()) {
                return ReturnUtil.toJSONString(1, "请输入邮箱", null);
            } else if (null == user.getUserPhone()) {
                return ReturnUtil.toJSONString(1, "请输入手机号", null);
            } else if (null == user.getUserLogin()) {
                return ReturnUtil.toJSONString(1, "请输入登录名", null);
            } else if (null == user.getPassword()) {
                return ReturnUtil.toJSONString(1, "请输入密码", null);
            } else {
                return userService.register(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
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

