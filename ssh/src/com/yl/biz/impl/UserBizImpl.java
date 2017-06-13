package com.yl.biz.impl;

import java.util.List;

import com.yl.entity.Admin;
import com.yl.entity.Users;
import com.yl.biz.UserBiz;
import com.yl.dao.UserDAO;

public class UserBizImpl implements UserBiz {

	UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public List login(Users condition) {
		return userDAO.search(condition);
	}

	//����û�
	@Override
	public void addUsers(Users users) {
		userDAO.addUsers(users);		
	}

	//����Ա��¼��֤
	@Override
	public List login(Admin condition) {
		return userDAO.search(condition);
	}

	//�޸ĸ�����Ϣ
	@Override
	public void modifyUsers(Users users) {
		userDAO.modifyUsers(users);
	}
}
