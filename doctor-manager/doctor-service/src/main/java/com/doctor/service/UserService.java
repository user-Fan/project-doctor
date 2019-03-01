package com.doctor.service;

import com.doctor.api.TbUserMapper;
import com.doctor.api.UserPasswordMapper;
import com.doctor.common.MD5Utill;
import com.doctor.common.ReturnUtil;
import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;
import com.doctor.Iservice.IUserService;

import java.util.Date;
import java.util.List;

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

    @Override
    public TestUSer getTestUSer() {
        return null;
    }

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
            //判断手机号和登录名是否存在
            List<User> users = tbUserMapper.findByPhone(user.getUserPhone());
            List<User> users1 = tbUserMapper.findByUserLogin(user.getUserLogin());
            if (users.size() != 0) {
                return ReturnUtil.toJSONString(1, "手机号已存在", null);
            } else if (users1.size() != 0) {
                return ReturnUtil.toJSONString(1, "登录名已存在", null);
            }
            //添加创建时间
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
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }


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
}
