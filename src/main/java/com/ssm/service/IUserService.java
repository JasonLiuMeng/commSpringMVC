package com.ssm.service;

import java.util.List;

import com.ssm.pojo.User;

public interface IUserService {
	
	public User getUserById(int userId);
	public List<String> getUserNameById(int userId);
}
