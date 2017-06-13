package com.yl.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yl.entity.CartItemBean;
import com.yl.entity.Meal;
import com.yl.biz.MealBiz;
import com.yl.biz.MealSeriesBiz;

public class CartAction extends ActionSupport implements SessionAware {	
	//��װ���������Ĳ�Ʒ���mealId����ֵ
	private Integer mealId;
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}	
	public Integer getMealId() {
		return mealId;
	}
	//��װ���������Ĳ�Ʒ����quantity����ֵ
	int quantity;	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	MealBiz mealBiz;
	public void setMealBiz(MealBiz mealBiz) {
		this.mealBiz = mealBiz;
	}
	MealSeriesBiz mealSeriesBiz;	
	public void setMealSeriesBiz(MealSeriesBiz mealSeriesBiz) {
		this.mealSeriesBiz = mealSeriesBiz;
	}
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	//����Ʒ��ӵ����ﳵ
	public String addtoshopcart() throws Exception {
		//��session��ȡ�����ﳵ������Map����cart��
		Map cart=(Map)session.get("cart");
		//��ȡ��ǰҪ��ӵ����ﳵ�Ĳ�Ʒ
		Meal meal=mealBiz.getMealByMealId(mealId);
		//������ﳵ�����ڣ��򴴽����ﳵ(ʵ����HashMap��)��������session��
		if(cart==null){
			cart=new HashMap();
			session.put("cart", cart);
		}
		//������ڹ��ﳵ�����жϲ�Ʒ�Ƿ��ڹ��ﳵ��
		CartItemBean cartItem=(CartItemBean)cart.get(meal.getMealId());
		if(cartItem!=null){
		    //�����Ʒ�ڹ��ﳵ�У�����������
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}else{
			//���򣬴���һ����Ŀ��Map��
			cart.put(meal.getMealId(),new CartItemBean(meal,1));
		}
		//ҳ��ת��shopCart.jsp����ʾ���ﳵ
		return "shopCart";
	}	
	
	//��������
	public String updateSelectedQuantity() throws Exception {
		//��session��ȡ�����ﳵ������Map����cart��
	    Map cart=(Map)session.get("cart");
	    CartItemBean cartItem=(CartItemBean)cart.get(mealId);
	    cartItem.setQuantity(quantity);
	    return "shopCart";
	}
	
	//�ӹ��ﳵ���Ƴ�ָ����Ŷ���
	public String deleteSelectedOrders() throws Exception {
		//��session��ȡ�����ﳵ������Map����cart��
	    Map cart=(Map)session.get("cart");
	    cart.remove(mealId);
	    return "shopCart";
	}	
	
	//��չ��ﳵ
	public String clearCart() throws Exception {
		//��session��ȡ�����ﳵ������Map����cart��
	    Map cart=(Map)session.get("cart");
	    cart.clear();
	    return "shopCart";
	}	
	
}
