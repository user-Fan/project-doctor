package com.doctor.service;

import com.doctor.api.TbUserMapper;
import com.doctor.pojo.TestUSer;
import com.doctor.pojo.User;
import com.doctor.Iservice.IUserService;

import java.util.List;

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

	@Override
	public List<User> findByPhone(String phone) {
		logger.info("进入service");
		logger.info("进入mapper");
		List<User> users = tbUserMapper.findByPhone(phone);
		logger.info("mapper查询结束");
		if (users != null && users.size() > 0) {
			if (users.get(0) != null && !users.equals("")) {
				logger.info("取出结果集合" + users.toString());
				return users;
			}
		}
		logger.info("users为空");
		return null;
	}

	@Override
	public int register(User user) {
		return tbUserMapper.register(user);
	}

	@Override
	public List<User> findByUserLogin(String userLogin) {
		return tbUserMapper.findByUserLogin(userLogin);
	}

	@Override
	public int insertPassword(User user){
		return  tbUserMapper.insertPassword(user);
	}
}
