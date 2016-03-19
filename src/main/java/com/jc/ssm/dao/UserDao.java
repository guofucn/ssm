package com.jc.ssm.dao;

import org.springframework.stereotype.Component;

import com.jc.ssm.model.User;

@Component
public interface UserDao {
	public User getById(int id);
}
