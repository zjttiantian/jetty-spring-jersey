package com.tiantian.service;


import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tiantian.bean.UserBean;
import com.tiantian.mapper.UserMapper;


@Service("userService")

public class UserService extends BaseService<UserBean> {
	private final static Logger log = Logger.getLogger(UserService.class);

	@Autowired
	private UserMapper<UserBean> mapper;

	public UserMapper<UserBean> getMapper() {
		return mapper;
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void addTest(int id){
		UserBean u=new UserBean();
		u.setName("test");
		u.setPassword("test");
		mapper.add(u);
		mapper.test(id);
	}

}
