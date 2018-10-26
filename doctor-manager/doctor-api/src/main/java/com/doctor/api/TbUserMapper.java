package com.doctor.api;

import java.util.List;


import com.doctor.pojo.User;

public interface TbUserMapper {
	
    public List<User> getUserByUserLogin(String wuwukai);
}
