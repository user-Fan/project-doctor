package com.doctor.api;


import com.doctor.pojo.UserPassword;

public interface UserPasswordMapper {
    UserPassword findByUserId(Integer userId);

}