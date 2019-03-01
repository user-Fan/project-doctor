package com.doctor.Iservice;

import java.util.List;

import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;

public interface IUserService {
    TestUSer getTestUSer();

    //获取登陆用户的用户集合信息
    List<User> getUserUserLogin(String userLogin );

    //更新用户信息
    int updataUserInfo(User user);

    //获取登陆用户的用户信息
    User getUserUserLogin_v2(String userLogin);
}
