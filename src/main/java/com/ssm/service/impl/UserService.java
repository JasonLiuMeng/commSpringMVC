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

	@Override
	public int doInsert(User user) {
		// TODO Auto-generated method stub
		this.userDao.insert(user);
		try {
			Thread.sleep(1000 * 30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setUserName("张三");
		this.userDao.insert(user);
		user.setAge(50);
//		throw new RuntimeException();
		return this.userDao.insert(user);
	}

	@Override
	public int doUpdate(User user) {
		// TODO Auto-generated method stub
		return this.userDao.updateByPrimaryKey(user);
	}

	@Override
	public int doUpdateSelective(User user) {
		// TODO Auto-generated method stub
		return this.userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int doDelete(User user) {
		// TODO Auto-generated method stub
		return this.userDao.deleteByPrimaryKey(user.getId());
	}
	
}
