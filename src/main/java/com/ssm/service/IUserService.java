package com.ssm.service;

import java.util.List;

import com.ssm.pojo.User;

public interface IUserService {
	
	public User getUserById(int userId);
	public List<String> getUserNameById(int userId);
	public int doInsert(User user);
	public int doUpdateSelective(User user);
	public int doUpdate(User user);
	public int doDelete(User user);
}
