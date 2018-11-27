package com.doctor.Iservice;

import java.util.List;

import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
    TestUSer getTestUSer();
    List<User> getUserUserLogin(String userLogin );
    List<User> findByPhone(String phone);

    @Transactional
    int register(User user);

    List<User> findByUserLogin(String userLogin);

    @Transactional
    int insertPassword(User user);

}
