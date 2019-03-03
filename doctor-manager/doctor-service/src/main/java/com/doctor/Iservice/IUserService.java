package com.doctor.Iservice;

import java.util.List;


import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
    TestUSer getTestUSer();

    //获取登陆用户的用户集合信息
    List<User> getUserUserLogin(String userLogin );
    //更新用户信息
    int updataUserInfo(User user);

    //获取登陆用户的用户信息
    User getUserUserLogin_v2(String userLogin);

    List<User> findByPhone(String phone);

    @Transactional(rollbackFor = {Exception.class})
    String register(User user);

    List<User> findByUserLogin(String userLogin);

    @Transactional
    int insertPassword(User user);

    int updatePasswordId(Integer userId,Integer passwordId);


    String updatePassword(User user, User userinfo);

    List<User> selectUserList();

    User findById(int id);

    int updateStatus(int id, int status);
}
