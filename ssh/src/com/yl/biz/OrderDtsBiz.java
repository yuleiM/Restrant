package com.yl.biz;

import java.util.List;

import com.yl.entity.Orderdts;

public interface OrderDtsBiz {
	
	//���ɶ����ӱ�������ϸ��
    public void addOrderDts(Orderdts dts);
    
    //���ݶ��������Ż�ȡ������ϸ�б�
  	public List getOrderDtsByOid(int oid);	

}
