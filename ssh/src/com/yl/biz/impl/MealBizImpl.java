package com.yl.biz.impl;

import java.util.List;

import com.yl.entity.Meal;
import com.yl.entity.Pager;
import com.yl.biz.MealBiz;
import com.yl.dao.MealDAO;

public class MealBizImpl implements MealBiz {

	MealDAO mealDAO;
	public void setMealDAO(MealDAO mealDAO) {
		this.mealDAO = mealDAO;
	}
	
	@Override
	public List getAllMeal(int page) {
		return mealDAO.getAllMeal(page);
	}
	
	@Override
	public Pager getPagerOfMeal() {
		int count= mealDAO.getCountOfAllMeal();
		//ʹ�÷�ҳ��Pager�������
		Pager pager=new Pager();
		//����pager�����е�perPageRows���ԣ���ʾÿҳ��ʾ�ļ�¼��
		pager.setPerPageRows(9);
		//����pager�����е�rowCount���ԣ���ʾ��¼����
		pager.setRowCount(count);
	    //����pager����
		return pager;
	}
	
	/*@Override
	public List getMealBySeries(int mealSeries, int page) {
		return mealDAO.getMealBySeries(mealSeries, page);
	}*/
	/*@Override
	public Pager getPagerOfMeal(int mealSeries) {
		int count= mealDAO.getCountOfMeal(mealSeries);
		//ʹ�÷�ҳ��Pager�������
		Pager pager=new Pager();
		//����pager�����е�perPageRows���ԣ���ʾÿҳ��ʾ�ļ�¼��
		pager.setPerPageRows(6);
		//����pager�����е�rowCount���ԣ���ʾ��¼����
		pager.setRowCount(count);
	    //����pager����
		return pager;
	}*/
	
	@Override
	public List getMealByCondition(Meal condition, int page) {		
		return mealDAO.getMealByCondition(condition, page);
	}
	
	//ͳ�Ʒ��ϲ�ѯ�����Ĳ�Ʒ����,��ʼ����ҳ��Pager��������perPageRows��rowCount����
	@Override
	public Pager getPagerOfMeal(Meal condition) {
		int count= mealDAO.getCountOfMeal(condition);
		//ʹ�÷�ҳ��Pager�������
		Pager pager=new Pager();
		//����pager�����е�perPageRows���ԣ���ʾÿҳ��ʾ�ļ�¼��
		pager.setPerPageRows(9);
		//����pager�����е�rowCount���ԣ���ʾ��¼����
		pager.setRowCount(count);
	    //����pager����
		return pager;
	}
	
	@Override
	public Meal getMealByMealId(int mealId) {
		return mealDAO.getMealByMealId(mealId);
	}
	
	//��Ӳ�Ʒ
	@Override
	public void addMeal(Meal meal) {
		mealDAO.addMeal(meal);		
	}
	
	//ɾ��ָ����Ų�Ʒ
	@Override
	public void deleteMeal(int mealId) {		
		Meal meal=mealDAO.getMealByMealId(mealId);
		mealDAO.deleteMeal(meal);
	}

	//�޸Ĳ�Ʒ
	@Override
	public void updateMeal(Meal meal) {
		mealDAO.updateMeal(meal);		
	}
}
