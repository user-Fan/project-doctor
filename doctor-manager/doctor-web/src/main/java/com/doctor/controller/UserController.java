package com.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.doctor.Iservice.IDoctorService;
import com.doctor.Iservice.IUserPasswordSevice;
import com.doctor.Iservice.IUserService;
import com.doctor.common.ReturnUtil;
import com.doctor.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;

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
    public User userinfopage(HttpServletRequest request, String name, Model model) {
        HttpSession session = request.getSession(false);
        User userinfo = (User) session.getAttribute("userinfo");
        if (!(userinfo == null) && StringUtils.isNotBlank(userinfo.getUserLogin())) {
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
    public String editUserInfo(User user, HttpServletRequest request, String name, Model model) {
        logger.info("editUserInfo||修改User{}", JSONObject.toJSONString(user));
        //修改信息为空直接返回登陆页面
        if (user == null) {
            return "loginPage";
        }
        HttpSession session = request.getSession(false);
        //获取session中的用户信息
        User userinfo = (User) session.getAttribute("userinfo");
        //判断用户信息是否为空，登录名是否存在
        if (userinfo != null && StringUtils.isNotBlank(userinfo.getUserLogin())) {
            //获取用户修改信息
            user.setUserLogin(userinfo.getUserLogin());
            int a = UserService.updataUserInfo(user);
            //获取修改以后的用户信息
            User userInfo_ = UserService.getUserUserLogin_v2(userinfo.getUserLogin());
            session.removeAttribute("userinfo");
            session.setAttribute("userinfo", userInfo_);
        }
        //获取更新之后的用户信息重返回用户页面
        return "user";
    }

    @ApiOperation(value = "验证手机号")
    @RequestMapping(value = "/isPhone", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public int isPhone(@RequestParam("phones") String phones, HttpServletRequest request) {
        try {
            if (phones == "") {
                return 3;
            } else {
                HttpSession session = request.getSession(false);
                //获取session中的用户信息
                User userinfo = (User) session.getAttribute("userinfo");
                if (null==userinfo){
                    return 5;
                }
                if (!phones.equals(userinfo.getUserPhone())) {
                    return 1;
                } else {
                    return 2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 4;
        }
    }
    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changePassword(User user, HttpServletRequest request ) {
        try {
            if (null == user) {
                return ReturnUtil.toJSONString(1, "没有修改信息", null);
            } else if (null == user.getUserPhone()) {
                return ReturnUtil.toJSONString(1, "请输入手机号", null);
            } else if (null == user.getPassword()) {
                return ReturnUtil.toJSONString(1, "请输入密码", null);
            }
            HttpSession session = request.getSession(false);
            //获取session中的用户信息
            User userinfo = (User) session.getAttribute("userinfo");
            String rel = userService.updatePassword(user,userinfo);
            session.removeAttribute("userinfo");
            return rel;
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    @ApiOperation(value = "验证Session")
    @RequestMapping(value = "/ifSession",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String ifSession(HttpServletRequest request){
        try {
            HttpSession session = request.getSession(false);
            //获取session中的用户信息
            User userinfo = (User) session.getAttribute("userinfo");
            if (null == userinfo){
                return ReturnUtil.toJSONString(1, "session无效", null);
            }else {
                return ReturnUtil.toJSONString(0, "session有效", userinfo);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    @ApiOperation(value = "退出")
    @RequestMapping(value = "exit",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public int exit(HttpServletRequest request){
        try {
            HttpSession session = request.getSession(false);
            //获取session中的用户信息
            User userinfo = (User) session.getAttribute("userinfo");
            session.removeAttribute("userinfo");
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    //用户list
    @ApiOperation(value = "用户list")
    @RequestMapping(value = "/userList",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String userList(){
        try {
            List<User> userList = userService.selectUserList();
            return ReturnUtil.toJSONString(0, "查询成功", userList);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    //用户禁用启用
    @RequestMapping(value = "/activateU", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public int activateU(@RequestParam("id") int id) {
        try {
            User user = userService.findById(id);
            int status = 0;
            if (user.getUserStatus() == 0) {
                //未激活 需要启用
                status = 1;
            } else if (user.getUserStatus() == 1) {
                //已启用 需要禁用
                status = 0;
            }
            int rel = userService.updateStatus(id, status);
            if (rel >= 1) {
                return 0;
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    //用户详情
    @ApiOperation(value = "用户详情")
    @RequestMapping("/findByUId")
    @ResponseBody
    public String findByUId(@RequestParam("id") int id){
        try {
            User user = userService.findById(id);
            if (null == user){
                return ReturnUtil.toJSONString(1, "查询失败", null);
            }
            return ReturnUtil.toJSONString(0, "查询成功", user);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    //编辑用户
    @ApiOperation(value = "编辑用户")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateUser(User user){
        try {
            if (null == user){
                return ReturnUtil.toJSONString(1, "用户信息为空", null);
            }
            int rel = userService.updateUser(user);
            if (rel == 0){
                return ReturnUtil.toJSONString(1, "修改失败", null);
            }
            return ReturnUtil.toJSONString(0, "修改成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    //删除用户
    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public int deleteUser(@RequestParam("id") int id){
        try {
            int rel = userService.deleteUser(id);
            if (rel == 0){
                return 1;
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

}

