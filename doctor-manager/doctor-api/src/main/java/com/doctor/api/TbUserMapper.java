package com.doctor.api;

import java.util.List;


import com.doctor.pojo.User;

public interface TbUserMapper {
	//获取用户信息
    public List<User> getUserByUserLogin(String userLogin);

    //更新用户信息
    public int updataUserInfo(User user);


}
