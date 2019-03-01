package com.doctor.service;


import com.doctor.Iservice.IUserPasswordSevice;
import com.doctor.api.UserPasswordMapper;
import com.doctor.pojo.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userPasswordService")
public class UserPasswordServiceImpl implements IUserPasswordSevice {

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Override
    public UserPassword finfByUserID(Integer userId) {
        return userPasswordMapper.findByUserId(userId);
    }
}
