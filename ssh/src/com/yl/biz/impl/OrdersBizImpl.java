package com.yl.biz.impl;

import java.util.List;

import com.yl.entity.Orders;
import com.yl.entity.Pager;
import com.yl.biz.OrdersBiz;
import com.yl.dao.OrdersDAO;

public class OrdersBizImpl implements OrdersBiz {

	OrdersDAO ordersDAO;
	
	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}
	
	//ɾ��ָ����ŵĶ���
	@Override
	public void deleteOrdersByOid(int oid) {
		Orders orders=ordersDAO.getOrdersByOid(oid);	
		ordersDAO.deleteOrders(orders);
	}

	@Override
	public Orders getOrdersByOid(int oid) {
		return ordersDAO.getOrdersByOid(oid);
	}

	//��ȡָ���û��Ķ����б�
	@Override
	public List getOrdersByUserId(int userId) {
		return ordersDAO.getOrdersByUserId(userId);
	}

	//������
	@Override
	public void handleOrders(Orders orders) {
		ordersDAO.updateOrders(orders);
	}

	//��ȡָ��ҳ��ʾ�Ķ����б�
	@Override
	public List getAllOrders(int page) {
		return ordersDAO.getAllOrders(page);
	}
	
	//��ȡ���ж�������,������ʼ����ҳ��Pager���󣬲�������perPageRows��rowCount����
	@Override
	public Pager getPagerOfOrders() {
		int count= ordersDAO.getCountOfAllOrders();
		//ʹ�÷�ҳ��Pager�������
		Pager pager=new Pager();
		//����pager�����е�perPageRows���ԣ���ʾÿҳ��ʾ�ļ�¼��
		pager.setPerPageRows(6);
		//����pager�����е�rowCount���ԣ���ʾ��¼����
		pager.setRowCount(count);
	    //����pager����
		return pager;
	}

	//��ȡ����������ָ��ҳ��ʾ�Ķ����б�
	@Override
	public List getOrdersByCondition(Orders condition, int page) {
		return ordersDAO.getOrdersByCondition(condition, page);
	}

	//��ȡ���������Ķ�������,������ʼ����ҳ��Pager���󣬲�������perPageRows��rowCount����
	@Override
	public Pager getPagerOfOrders(Orders condition) {
		int count= ordersDAO.getCountOfOrders(condition);
		//ʹ�÷�ҳ��Pager�������
		Pager pager=new Pager();
		//����pager�����е�perPageRows���ԣ���ʾÿҳ��ʾ�ļ�¼��
		pager.setPerPageRows(6);
		//����pager�����е�rowCount���ԣ���ʾ��¼����
		pager.setRowCount(count);
	    //����pager����
		return pager;
	}
	

}
