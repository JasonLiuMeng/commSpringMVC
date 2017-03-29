package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Resource
	private UserMapper userDao;
	
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public List<String> getUserNameById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectName(userId);
	}

}
