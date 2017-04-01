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
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
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
		
		//此处为测试事务的代码，第一条插入执行后进入睡眠状态，此时数据库并没有真正插入数据，只有在方法执行完毕之后才会有插入数据库
//		this.userDao.insert(user);
//		try {
//			Thread.sleep(30*1000); //此时并没有插入数据库
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		user.setUserName("new Name");
		/**/
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
