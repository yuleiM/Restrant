package com.yl.biz;

import java.util.List;

import com.yl.entity.Admin;
import com.yl.entity.Users;

public interface UserBiz {
	//�û���¼��֤
	public List login(Users condition);
	
	//����û�
	public void addUsers(Users users);
	
	//����Ա��¼��֤
	public List login(Admin condition);
	
	//�޸ĸ�����Ϣ
	public void modifyUsers(Users users);
}
