package com.jc.ssm.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jc.ssm.dao.UserDao;
import com.jc.ssm.model.User;

@Component
public class UserService {
	public UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserById(int id){
		return userDao.getById(id);
	}
}
