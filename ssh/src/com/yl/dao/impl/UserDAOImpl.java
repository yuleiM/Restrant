package com.yl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.yl.entity.Admin;
import com.yl.entity.Users;
import com.yl.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

	SessionFactory sessionFactory;	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List search(Users condition) {
		List list=null;
		//ͨ��sessionFactory���Session
		Session session=sessionFactory.getCurrentSession();
		//����Criteria����
		Criteria c=session.createCriteria(Users.class);
		//ʹ��Example�����ഴ��ʾ������
		Example example=Example.create(condition);
		//ΪCriteria����ָ��ʾ������example��Ϊ��ѯ����		
		c.add(example);			
		list= c.list();	  //ִ�в�ѯ����ý��		
		return list;
	}

	//����û�
	@Override
	public void addUsers(Users users) {
		Session session=sessionFactory.getCurrentSession();
		session.save(users);
	}

	//����Ա��¼��֤
	@Override
	public List search(Admin condition) {
		List list=null;
		//ͨ��sessionFactory���Session
		Session session=sessionFactory.getCurrentSession();
		//����Criteria����
		Criteria c=session.createCriteria(Admin.class);
		//ʹ��Example�����ഴ��ʾ������
		Example example=Example.create(condition);
		//ΪCriteria����ָ��ʾ������example��Ϊ��ѯ����		
		c.add(example);			
		list= c.list();	  //ִ�в�ѯ����ý��		
		return list;
	}

	//�޸ĸ�����Ϣ
	@Override
	public void modifyUsers(Users users) {
		Session session=sessionFactory.getCurrentSession();
		session.update(users);
	}

}
