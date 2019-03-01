package com.doctor.Iservice;

import com.doctor.pojo.UserPassword;

public interface IUserPasswordSevice {
    UserPassword finfByUserID(Integer userId);
}
