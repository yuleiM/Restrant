package com.yl.dao;

import java.util.List;

import com.yl.entity.Orderdts;

public interface OrderDtsDAO {
	//���ɶ����ӱ�������ϸ��
	public void addOrderDts(Orderdts dts);
	
	//���ݶ��������Ż�ȡ������ϸ�б�
	public List getOrderDtsByOid(int oid);
}
