package com.yl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.yl.entity.Orderdts;
import com.yl.dao.OrderDtsDAO;

public class OrderDtsDAOImpl implements OrderDtsDAO {

    SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//���ɶ����ӱ�������ϸ��
	@Override
	public void addOrderDts(Orderdts dts) {		
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(dts);
	}
	
	//���ݶ��������Ż�ȡ������ϸ�б�
	@Override
	public List getOrderDtsByOid(int oid) {		
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orderdts.class);
		c.add(Restrictions.eq("orders.oid", oid));
		return c.list();
	}

}
