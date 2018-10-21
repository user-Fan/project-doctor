package com.doctor.service;

import com.doctor.api.UserApi;
import com.doctor.pojo.TestUSer;
import com.doctor.Iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator
 * @Date: 2018/10/20 16:21
 * @Description:
 */
@Service
public class UserService implements IUserService {
    @Autowired
    UserApi userApi;
    @Override
    public TestUSer getTestUSer() {
        return null;
    }
}
