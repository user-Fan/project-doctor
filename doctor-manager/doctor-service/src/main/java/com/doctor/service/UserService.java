package com.doctor.service;

import com.alibaba.fastjson.JSONObject;
import com.doctor.api.TbUserMapper;
import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;
import com.doctor.Iservice.IUserService;

import java.util.List;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator
 * @Date: 2018/10/20 16:21
 * @Description:
 */
@Service
public class UserService implements IUserService {
	public final Logger logger = LoggerFactory.getLogger(IUserService.class);
	@Autowired
	TbUserMapper tbUserMapper;

	@Override
	public TestUSer getTestUSer() {
		return null;
	}

	/**
	 *
	 * 查询用户信息
	 * @param userLogin
	 * @return
	 */
	@Override
	public List<User> getUserUserLogin(String userLogin) {
		logger.info("进入service");
		logger.info("进入mapper");
		List<User> users = tbUserMapper.getUserByUserLogin(userLogin);
		logger.info("mapper查询结束");
		if (users != null && users.size() > 0) {
			if (users.get(0) != null && !users.equals(""))
				logger.info("取出结果集合" + users.toString());
			return users;
		}
		logger.info("users为空");
		return null;
	}

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	@Override
	public int updataUserInfo(User user) {
		logger.info("进入updataUserInfo||user{}",JSONObject.toJSONString(user));
		logger.info("user{}开始更新", JSONObject.toJSONString(user));
		int result = tbUserMapper.updataUserInfo(user);
		System.out.println(result);
		if(result == 0 ){
			logger.error("user{}更新失败", JSONObject.toJSONString(user));
			return result;
		}
		logger.info("user{}更新成功", JSONObject.toJSONString(user));
		return result;
	}

	@Override
	public User getUserUserLogin_v2(String userLogin) {
		List<User> userLoginInfo = getUserUserLogin(userLogin);
		if (null!=userLogin&&userLoginInfo.size()>=0&&null!=userLoginInfo.get(0)&& StringUtils.isNotBlank(userLoginInfo.get(0).getUserLogin())){
			return userLoginInfo.get(0);
		}
		return null;
	}
}
