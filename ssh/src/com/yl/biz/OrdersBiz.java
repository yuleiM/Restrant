package com.yl.biz;

import java.util.List;

import com.yl.entity.Meal;
import com.yl.entity.Orderdts;
import com.yl.entity.Orders;
import com.yl.entity.Pager;

public interface OrdersBiz {
		
	//���ݶ�����ż��ض�������
    public Orders getOrdersByOid(int oid);
    
    //��ȡָ���û��Ķ����б�
    public List getOrdersByUserId(int userId);
    
    //��ȡָ��ҳ��ʾ�Ķ����б�
    public List getAllOrders(int page);
    
    //��ȡ���ж�������,������ʼ����ҳ��Pager���󣬲�������perPageRows��rowCount����
    public Pager getPagerOfOrders();	
    
    //��ȡ����������ָ��ҳ��ʾ�Ķ����б�
    public List getOrdersByCondition(Orders condition,int page);
	
    //��ȡ���������Ķ�������,������ʼ����ҳ��Pager���󣬲�������perPageRows��rowCount����
	public Pager getPagerOfOrders(Orders condition);
	
	//ɾ��ָ����ŵĶ���
	public void deleteOrdersByOid(int oid);
	
	//������
	public void handleOrders(Orders orders);	
	
}
