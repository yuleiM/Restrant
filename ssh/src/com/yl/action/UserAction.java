package com.yl.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yl.entity.Admin;
import com.yl.entity.Users;
import com.yl.biz.UserBiz;

public class UserAction extends ActionSupport implements RequestAware,SessionAware {
	//���ڷ�װ��¼ҳ���¼�û����ͣ���ͨ�û������Ա��
	private String type;	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	//�������ڱ����û���¼����������������
	private String loginName;
	private String loginPwd;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	//��װע�������
	private  Users user;	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	UserBiz userBiz;	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
    //��¼��֤	
	public String validateLogin() throws Exception {
		List list;
		if("userlogin".equals(type)){       //�û���¼��֤
			Users condition=new Users();
			condition.setLoginName(loginName);
			condition.setLoginPwd(loginPwd);	
			list=userBiz.login(condition);
			if(list.size()>0){
				//���û��������Session
				session.put("user", list.get(0));	
			}
			else{
				request.put("message", "�˺Ż��������");
				return "login";
			}
		}
        if("adminlogin".equals(type)){        //����Ա��¼��֤
			Admin condition=new Admin();
			condition.setLoginName(loginName);
			condition.setLoginPwd(loginPwd);	
			list=userBiz.login(condition);
			if(list.size()>0){
				//���û��������Session
				session.put("admin", list.get(0));	
			}
			else{
				request.put("message", "�˺Ż��������");
				return "loginAdmin";
			}
		}
		//ת����ΪtoShowMeal��Action
		return "toShowMeal"; 
	}
	
	//�û�ע��
	public String register() throws Exception {
		userBiz.addUsers(user);
		return "show";
	}
	
	//�޸ĸ�����Ϣ
	public String modifyUsers() throws Exception {
		userBiz.modifyUsers(user);
		return "show";
	}
	
	//�û�ע��
	public String logOut() throws Exception {
		if("userlogout".equals(type)){
			session.remove("user");
		}
		if("adminlogout".equals(type)){
			session.remove("admin");
		}
		return "show";
	}

	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

}
