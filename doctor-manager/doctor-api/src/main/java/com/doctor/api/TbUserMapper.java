package com.doctor.api;

import java.util.List;


import com.doctor.pojo.User;

public interface TbUserMapper {

    public List<User> getUserByUserLogin(String userLogin);

    List<User> findByPhone(String phone);

    int register(User user);

    List<User> findByUserLogin(String userLogin);

    int insertPassword(User user);

}
