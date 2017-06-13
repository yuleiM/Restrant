package com.yl.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yl.entity.CartItemBean;
import com.yl.entity.Orderdts;
import com.yl.entity.Orders;
import com.yl.entity.Pager;
import com.yl.entity.Users;
import com.yl.biz.OrderDtsBiz;
import com.yl.biz.OrdersBiz;

public class OrdersAction extends ActionSupport implements RequestAware,SessionAware {
	OrdersBiz ordersBiz;	
	public void setOrdersBiz(OrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
	}
	
	OrderDtsBiz orderDtsBiz;
	public void setOrderDtsBiz(OrderDtsBiz orderDtsBiz) {
		this.orderDtsBiz = orderDtsBiz;
	}
	
	//��װ���鿴�������Ӵ������Ĳ���oid��ֵ
	int oid;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	//��װmanageorders.jspҳ���и��ݶ����źͶ���״̬��ѯʱ�������Ĳ���ֵ
	private Orders orders;
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	//���������¶�������
	public String addOrders() throws Exception {
		Orders orders=new Orders();
		orders.setOrderState("δ����");
		orders.setOrderTime(new Date());
		Users user=(Users)session.get("user");
	    orders.setUsers(user);
	    orders.setOrderPrice((Double)session.get("sumPrice"));
	    Map cart=(HashMap)session.get("cart");	    
	    Iterator iter = cart.keySet().iterator();
	    while (iter.hasNext()) {
	    	Object key = iter.next();
	    	CartItemBean cartItem = (CartItemBean)cart.get(key);
	    	Orderdts orderDts=new Orderdts();
	    	orderDts.setMeal(cartItem.getMeal());
	    	orderDts.setMealCount(cartItem.getQuantity());
	    	orderDts.setMealPrice(cartItem.getMeal().getMealPrice());
	    	orderDts.setOrders(orders);
	    	orderDtsBiz.addOrderDts(orderDts);
	    }
	    session.remove("cart");
		return "show";
	}
	
	//��ҳʵ����
	private Pager pager;
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}

	//��ȡָ���û��Ķ����б�,��ת���ҵĶ���ҳmyorders.jsp
	public String toMyOrders() throws Exception {
		Users user=(Users)session.get("user");
		List myOrdersList=ordersBiz.getOrdersByUserId(user.getId());
		request.put("myOrdersList", myOrdersList);
		return "myorders";
	}
	
	//���ݶ��������Ż�ȡ������ϸ�б�,��ת���ҵĶ�����ϸҳmyordersdetails.jsp
	public String toOrdersDetails() throws Exception {
		List ordersDtsList= orderDtsBiz.getOrderDtsByOid(oid);
		request.put("ordersDtsList", ordersDtsList);
		return "toOrdersDetails";
	}
	
	//ɾ��ָ����ŵĶ���,��ת��toMyOrders
	public String deleteOrders() throws Exception {		
		//����ҵ�񷽷������ݱ���ɾ����������ϸ
		ordersBiz.deleteOrdersByOid(oid);
		return "toMyOrders";
	}
	
	//��ȡ���ж����б�,��ת����������ҳmanageorders.jsp
	public String toManageOrders() throws Exception {
		int curPage=1;
		if(pager!=null)
			curPage=pager.getCurPage();
		List ordersList=null;		
		if(orders!=null){
			//ָ����ѯ����,���ȡ����������ָ��ҳ�Ķ����б�
			ordersList=ordersBiz.getOrdersByCondition(orders, curPage);
			pager=ordersBiz.getPagerOfOrders(orders);
			//����ѯ��������request��Χ������Ϊ��ҳ�������еĲ���ֵ
			if(orders.getOid()!=null)
				request.put("oid", orders.getOid());
		    if((orders.getOrderState()!=null) && !orders.getOrderState().equals(""))
				request.put("orderState", orders.getOrderState());
		}else{
			//û��ָ����ѯ��������ȡָ��ҳ�Ķ����б�
			ordersList=ordersBiz.getAllOrders(curPage);
			//��ȡ���в�Ʒ����,������ʼ����ҳ��Pager����
			pager=ordersBiz.getPagerOfOrders();
		}		
		//����Pager�����еĴ���ʾҳҳ��
		pager.setCurPage(curPage);
		//�������б����request��Χ
		request.put("ordersList", ordersList);
		return "manageorders";
	}
	
	//������,��ת��toManageOrders
	public String handleOrders() throws Exception {		
		//����ҵ�񷽷������ݱ���ɾ����������ϸ
		Orders orders=ordersBiz.getOrdersByOid(oid);
		orders.setOrderState("�Ѵ���");
		ordersBiz.handleOrders(orders);
		return "toManageOrders";
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
