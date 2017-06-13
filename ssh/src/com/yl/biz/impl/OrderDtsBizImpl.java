package com.yl.biz.impl;

import java.util.List;

import com.yl.entity.Orderdts;
import com.yl.biz.OrderDtsBiz;
import com.yl.dao.OrderDtsDAO;

public class OrderDtsBizImpl implements OrderDtsBiz {

	OrderDtsDAO orderDtsDAO;	
	public void setOrderDtsDAO(OrderDtsDAO orderDtsDAO) {
		this.orderDtsDAO = orderDtsDAO;
	}
	
	//���ɶ����ӱ�������ϸ��
	@Override
	public void addOrderDts(Orderdts dts) {
		orderDtsDAO.addOrderDts(dts);		
	}
	
	//���ݶ��������Ż�ȡ������ϸ�б�
	@Override
	public List getOrderDtsByOid(int oid) {		
		return orderDtsDAO.getOrderDtsByOid(oid);
	}

}
