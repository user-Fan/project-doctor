package com.doctor.service;

import com.doctor.api.DoctorMapper;
import com.alibaba.fastjson.JSONObject;
import com.doctor.api.TbUserMapper;
import com.doctor.api.UserPasswordMapper;
import com.doctor.common.MD5Utill;
import com.doctor.common.ReturnUtil;
import com.doctor.pojo.Doctor;
import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;
import com.doctor.Iservice.IUserService;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.doctor.pojo.UserPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator
 * @Date: 2018/10/20 16:21
 * @Description:
 */
@Service
public class UserService implements IUserService {

    public final Logger logger = LoggerFactory.getLogger(IUserService.class);
    @Autowired
    TbUserMapper tbUserMapper;
    @Autowired
    UserPasswordMapper userPasswordMapper;
    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public TestUSer getTestUSer() {
        return null;
    }


    /**
     * 查询用户信息
     *
     * @param userLogin
     * @return
     */
    @Override
    public List<User> getUserUserLogin(String userLogin) {
        logger.info("进入service");
        logger.info("进入mapper");
        List<User> users = tbUserMapper.getUserByUserLogin(userLogin);
        logger.info("mapper查询结束");
        if (users != null && users.size() > 0) {
            if (users.get(0) != null && !users.equals(""))
                logger.info("取出结果集合" + users.toString());
            return users;
        }
        logger.info("users为空");
        return null;
    }

    @Override
    public List<User> findByPhone(String phone) {
        logger.info("进入service");
        logger.info("进入mapper");
        List<User> users = tbUserMapper.findByPhone(phone);
        logger.info("mapper查询结束");
        if (users != null && users.size() > 0) {
            if (users.get(0) != null && !users.equals("")) {
                logger.info("取出结果集合" + users.toString());
                return users;
            }
        }
        logger.info("users为空");
        return null;
    }

    @Override
    public String register(User user) {
        try {
            //判断值为1 用户添加
            if (user.getUserAge() == 1) {
                //判断手机号和登录名是否存在
                String verify = yz(user);
                if (verify.equals("验证无误")) {
                    //添加用户创建时间
                    user.setCreatTime(new Date());
                    //接收User信息将密码MD5加密
                    String pw = MD5Utill.md5Encryp(user.getUserLogin(), user.getPassword());
                    //将加密后的密码添加到User类中
                    user.setPassword(pw);
                    //接收User信息添加数据库
                    tbUserMapper.register(user);
                    //根据登录名查询User信息
                    User user1 = tbUserMapper.findByUserLogin(user.getUserLogin()).get(0);
                    //将查询的User1信息的ID添加到User类中
                    user.setUserId(user1.getUserId());
                    //将User1信息添加到password表中
                    tbUserMapper.insertPassword(user);
                    //根据User1的id获取password表中得信息
                    UserPassword userPassword = userPasswordMapper.findByUserId(user1.getUserId());
                    //将passwordId添加到user表中
                    int rel = tbUserMapper.updatePasswordId(user.getUserId(), userPassword.getPasswordId());
                    if (rel >= 1) {
                        return ReturnUtil.toJSONString(0, "注册成功", null);
                    } else {
                        return ReturnUtil.toJSONString(1, "注册失败", null);
                    }
                }
                //只为2 医生添加
            } else if (user.getUserAge() == 2) {
                //判断手机号和登录名是否存在
                String verify = yz(user);
                if (verify.equals("验证无误")) {
                    //将用户信息添加到Doctor表中
                    Doctor doctor = new Doctor();
                    //帐号
                    doctor.setDoctorAccount(user.getUserLogin());
                    //邮箱
                    doctor.setDoctorEmail(user.getUserEmail());
                    //手机号
                    doctor.setDoctorPhone(user.getUserPhone());
                    //密码
                    String pw = MD5Utill.md5Encryp(user.getUserLogin(), user.getPassword());
                    doctor.setDoctorPassword(pw);
                    //添加用户创建时间
                    doctor.setCreatTime(new Date());
                    //添加医生数据
                    int rel = doctorMapper.insertDoctor(doctor);
                    if (rel >= 1) {
                        return ReturnUtil.toJSONString(0, "注册成功", null);
                    } else {
                        return ReturnUtil.toJSONString(1, "注册失败", null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
        return ReturnUtil.toJSONString(1, "非法操作", null);
    }

    @Override
    public List<User> findByUserLogin(String userLogin) {
        return tbUserMapper.findByUserLogin(userLogin);
    }

    @Override
    public int insertPassword(User user) {
        return tbUserMapper.insertPassword(user);
    }

    @Override
    public int updatePasswordId(Integer userId, Integer passwordId) {
        return tbUserMapper.updatePasswordId(userId, passwordId);
    }

    @Override
    public String updatePassword(User user, User userinfo) {
        try {
            if (!user.getUserPhone().equals(userinfo.getUserPhone())) {
                return ReturnUtil.toJSONString(1, "手机号不相同", null);
            } else {
                String pw = MD5Utill.md5Encryp(userinfo.getUserLogin(), user.getPassword());
                if (pw.equals(userinfo.getPassword())) {
                    return ReturnUtil.toJSONString(1, "新密码不能和旧密码一样", null);
                } else {
                    UserPassword userPassword = new UserPassword();
                    userPassword.setUserId(userinfo.getUserId());
                    userPassword.setPasswordValue(pw);
                    int rel = userPasswordMapper.updatePassword(userPassword);
                    if (rel == 1) {
                        return ReturnUtil.toJSONString(0, "修改密码成功", null);
                    }
                    return ReturnUtil.toJSONString(1, "修改密码失败", null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    @Override
    public List<User> selectUserList() {
        return tbUserMapper.selectUserList();
    }

    @Override
    public User findById(int id) {
        User user = tbUserMapper.findById(id);
        if (user.getUserGender().equals("0")){
            user.setUserGender("保密");
        }else if (user.getUserGender().equals("1")){
            user.setUserGender("男");
        }else if (user.getUserGender().equals("2")){
            user.setUserGender("女");
        }
        return user;
    }

    @Override
    public int updateStatus(int id, int status) {
        return tbUserMapper.updateStatus(id,status);
    }

    @Override
    public int updateUser(User user) {
        if(!user.getUserGender().equals("")){
            if (user.getUserGender().equals("保密")){
                user.setUserGender("0");
            }else if (user.getUserGender().equals("男")){
                user.setUserGender("1");
            }else if (user.getUserGender().equals("nv")){
                user.setUserGender("2");
            }
        }
        return tbUserMapper.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        try {
            return tbUserMapper.deleteUser(id);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    //封装手机号登录名验证方法
    public String yz(User user) {
        //判断用户手机号和登录名是否存在
        List<User> users = tbUserMapper.findByPhone(user.getUserPhone());
        List<User> users1 = tbUserMapper.findByUserLogin(user.getUserLogin());
        if (users.size() != 0) {
            return ReturnUtil.toJSONString(1, "手机号已存在", null);
        } else if (users1.size() != 0) {
            return ReturnUtil.toJSONString(1, "登录名已存在", null);
        }
        //判断医生手机号和登录名是否存在
        Doctor doctor1 = doctorMapper.findByPhone(user.getUserPhone());
        Doctor doctor2 = doctorMapper.findByAccount(user.getUserLogin());
        if (doctor1 != null) {
            return ReturnUtil.toJSONString(1, "手机号已存在", null);
        } else if (doctor2 != null) {
            return ReturnUtil.toJSONString(1, "登录名已存在", null);
        }
        return "验证无误";
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public int updataUserInfo(User user) {
        logger.info("进入updataUserInfo||user{}", JSONObject.toJSONString(user));
        logger.info("user{}开始更新", JSONObject.toJSONString(user));
        int result = tbUserMapper.updataUserInfo(user);
        System.out.println(result);
        if (result == 0) {
            logger.error("user{}更新失败", JSONObject.toJSONString(user));
            return result;
        }
        logger.info("user{}更新成功", JSONObject.toJSONString(user));
        return result;
    }

    @Override
    public User getUserUserLogin_v2(String userLogin) {
        List<User> userLoginInfo = getUserUserLogin(userLogin);
        if (null != userLogin && userLoginInfo.size() >= 0 && null != userLoginInfo.get(0) && StringUtils.isNotBlank(userLoginInfo.get(0).getUserLogin())) {
            return userLoginInfo.get(0);
        }
        return null;
    }

    @Override
    public int updateUserPoint(Integer serPoint,Integer id) {
      return   tbUserMapper.updatePoint(serPoint,id);
    }
}
