package com.doctor.api;

import java.util.List;


import com.doctor.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {

    public List<User> getUserByUserLogin(String userLogin);

	//更新用户信息
    public int updataUserInfo(User user);

    List<User> findByPhone(String phone);

    int register(User user);

    List<User> findByUserLogin(String userLogin);

    int insertPassword(User user);

    int updatePasswordId(@Param("userId") Integer userId,@Param("passwordId")  Integer passwordId);
}
