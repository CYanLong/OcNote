package com.ocnote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ocnote.entity.User;
import com.ocnote.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public void register(User user) {
		userMapper.register(user);
	}
	
	
}
