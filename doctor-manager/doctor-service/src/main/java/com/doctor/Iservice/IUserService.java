package com.doctor.Iservice;

import java.util.List;

import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;

public interface IUserService {
    TestUSer getTestUSer();
    List<User> getUserUserLogin(String userLogin );
}
