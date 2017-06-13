package com.yl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.yl.entity.Orders;
import com.yl.dao.OrdersDAO;

public class OrdersDAOImpl implements OrdersDAO {

	SessionFactory sessionFactory;	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	

	//ɾ����������
	@Override
	public void deleteOrders(Orders orders) {
		Session session=sessionFactory.getCurrentSession();		
		session.delete(orders);
	}

	//���ݶ�����ż��ض�������
	@Override
	public Orders getOrdersByOid(int oid) {
		Session session=sessionFactory.getCurrentSession();
		return (Orders)session.get(Orders.class, oid);
	}

	//��ȡָ���û��Ķ����б�
	@Override
	public List getOrdersByUserId(int userId) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		c.add(Restrictions.eq("users.id", userId));		
		return c.list();
	}

	//���¶�������
	@Override
	public void updateOrders(Orders orders) {
		Session session=sessionFactory.getCurrentSession();
		session.update(orders);
	}

	//��ȡ���ж���
	@Override
	public List getAllOrders(int page) {		
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}
	
	//ͳ�����ж�������
	@Override
	public Integer getCountOfAllOrders() {
		Integer count=null;
		try{
		Session session=sessionFactory.getCurrentSession();
		String hql="select count(o) from Orders o";
		Query query=session.createQuery(hql);
		count= Integer.parseInt(query.uniqueResult().toString()) ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	
	//��ȡ����������ָ��ҳ��ʾ�Ķ����б�
	@Override
	public List getOrdersByCondition(Orders condition, int page) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		if(condition!=null){
			if((condition.getOid()!=null) && (condition.getOid()>0)){
				//�������Ž���ɸѡ
				c.add(Restrictions.eq("oid", condition.getOid()));
			}
			if((condition.getOrderState()!=null) && !condition.getOrderState().equals("") && !condition.getOrderState().equals("ȫ��")){
				//������״̬����ɸѡ
				c.add(Restrictions.eq("orderState", condition.getOrderState()));
			}
		}
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}
	
	//ͳ�����������Ķ�������
	@Override
	public Integer getCountOfOrders(Orders condition) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		if(condition!=null){
			if((condition.getOid()!=null) && (condition.getOid()>0)){
				//�������Ž���ɸѡ
				c.add(Restrictions.eq("oid", condition.getOid()));
			}
			if((condition.getOrderState()!=null) && !condition.getOrderState().equals("") && !condition.getOrderState().equals("ȫ��")){
				//������״̬����ɸѡ
				c.add(Restrictions.eq("orderState", condition.getOrderState()));
			}
		}
		return c.list().size();
	}	
	
}
