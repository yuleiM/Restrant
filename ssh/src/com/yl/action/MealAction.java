package com.yl.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yl.biz.MealBiz;
import com.yl.biz.MealSeriesBiz;
import com.yl.entity.Meal;
import com.yl.entity.Mealseries;
import com.yl.entity.Pager;

public class MealAction extends ActionSupport implements RequestAware {
	
	//����Meal�������ԣ����ڷ�װ������
	private Meal meal;	
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	MealBiz mealBiz;
	public void setMealBiz(MealBiz mealBiz) {
		this.mealBiz = mealBiz;
	}
	MealSeriesBiz mealSeriesBiz;	
	public void setMealSeriesBiz(MealSeriesBiz mealSeriesBiz) {
		this.mealSeriesBiz = mealSeriesBiz;
	}
	//��ҳʵ����
	private Pager pager;
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	private File doc;                //��װ�ϴ��ļ�������
	private String docFileName;      //��װ�ϴ��ļ�����������
	private String docContentType;   //��װ�ϴ��ļ�����������
	
	Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;		
	}
	
	//��ȡָ��ҳ�롢���ϲ�ѯ�����Ĳ�Ʒ�б���ת����Ʒ��ʾҳshow.jsp
	public String toShowMeal() throws Exception {		
		int curPage=1;
		if(pager!=null)
			curPage=pager.getCurPage();
		List mealList=null;		
		if(meal!=null){  
			//meal��Ϊ�գ���ʾ���������˲�ѯ��������ʱ����ȡָ��ҳ�롢���ϲ�ѯ�����Ĳ�Ʒ�б�
			mealList=mealBiz.getMealByCondition(meal, curPage);
			//ͳ�Ʒ��ϲ�ѯ�����Ĳ�Ʒ����,��ʼ����ҳ��Pager��������perPageRows��rowCount����
			pager=mealBiz.getPagerOfMeal(meal);
			//����ѯ��������request��Χ������Ϊ��ҳ�������еĲ���ֵ
			if((meal.getMealseries()!=null) && (meal.getMealseries().getSeriesId()!=null))
				request.put("seriesId", new Integer(meal.getMealseries().getSeriesId()) );
		    if((meal.getMealName()!=null) && !meal.getMealName().equals(""))
				request.put("mealName", meal.getMealName());
		}else{    
			//mealΪ�գ���ʾû��ָ����ѯ��������ʱ����ȡָ��ҳ��Ĳ�Ʒ�б�
			mealList=mealBiz.getAllMeal(curPage);
			//��ȡ���в�Ʒ����,������ʼ����ҳ��Pager���󣬲�������perPageRows��rowCount����
			pager=mealBiz.getPagerOfMeal();
		}
		
		//����Pager�����еĴ���ʾҳҳ��
		pager.setCurPage(curPage);
		//����ѯ��õ��б����request��Χ
		request.put("mealList", mealList);
		//��ȡ��ϵ�б�����request��Χ
		List mealSeriesList=mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);		
		return "toShowMeal";
	}
	
	public String toShowDetails() throws Exception {		
		Meal aMeal=mealBiz.getMealByMealId(meal.getMealId());
		request.put("aMeal", aMeal);		
		return "toShowDetails";
	}
	
	//��ȡ��ϵ�б���ת����Ӳ�ƷҳaddMeal.jsp
	public String toAddMeal() throws Exception {		
		List<Mealseries> mealSeriesList=mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);	
		return "addMeal";
		
		
	}
	
	//�ϴ���ƷͼƬ����Ӳ�Ʒ��Ϣ����ת��toShowMeal
	public String doAddMeal() throws Exception {
		if(docFileName!=null){     //�ж��Ƿ�ѡ�����ϴ�ͼƬ
			// �õ���ǰweb�����µ�uploadĿ¼���ڱ����ľ���·�������û������ļ�����ᴴ��        
		    String targetDirectory = ServletActionContext.getServletContext().getRealPath("/mealimages");          
		    //�������ϴ��ļ�         
		    String targetFileName = generateFileName(docFileName);          
		    //��ָ��Ŀ¼�����ļ�          
		    File target = new File(targetDirectory, targetFileName);          
		    //��Ҫ�ϴ����ļ�copy��ȥ         
		    FileUtils.copyFile(doc, target); 		
			meal.setMealImage(targetFileName);
			mealBiz.addMeal(meal);
		}		
		return "toShowMeal";
	}
	
	//�������ϴ��ļ�    
    public String generateFileName(String fileName){          
    	String formatDate = new SimpleDateFormat("yyMMddHHmmss").format(new Date());          
    	int random = new Random().nextInt(10000);          
    	int position = fileName.lastIndexOf(".");          
    	String extension = fileName.substring(position);           
    	return formatDate + random + extension;      
    }    
    
    
    //��ȡָ��ҳ�롢���ϲ�ѯ�����Ĳ�Ʒ�б���ת����Ʒ����ҳmanagemeal.jsp
    public String toManageMeal() throws Exception {		
		int curPage=1;
		if(pager!=null)
			curPage=pager.getCurPage();
		List mealList=null;		
		if(meal!=null){
			mealList=mealBiz.getMealByCondition(meal, curPage);
			pager=mealBiz.getPagerOfMeal(meal);
			if((meal.getMealseries()!=null) && (meal.getMealseries().getSeriesId()!=null))
				request.put("seriesId", new Integer(meal.getMealseries().getSeriesId()) );
		    if((meal.getMealName()!=null) && !meal.getMealName().equals(""))
				request.put("mealName", meal.getMealName());
		}else{
			mealList=mealBiz.getAllMeal(curPage);
			//��ȡ���в�Ʒ����,������ʼ����ҳ��Pager���󣬲�������perPageRows��rowCount����
			pager=mealBiz.getPagerOfMeal();
		}		
		//����Pager�����еĴ���ʾҳҳ��
		pager.setCurPage(curPage);
		request.put("mealList", mealList);
		//��ȡ��ϵ�б�����request��Χ
		List mealSeriesList=mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);		
		return "managemeal";
	}
    
    //��ȡҪ�޸ĵĲ�Ʒ���󣬴���request��Χ����ת����Ʒ��Ϣ�޸�ҳ
    public String toUpdateMeal() throws Exception {	
    	//��ȡҪ�޸ĵĲ�Ʒ���󣬴���request��Χ
    	Meal updatedMeal=mealBiz.getMealByMealId(meal.getMealId());
    	request.put("updatedMeal", updatedMeal);		
    	//��ȡ��ϵ�б�����request��Χ
		List mealSeriesList=mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);	
		return "updateMeal";
    }
    
    //ִ�в�Ʒ��Ϣ�޸ģ���ת��toShowMeal
    public String doUpdateMeal() throws Exception {	
    	if(docFileName!=null){
	    	// �õ���ǰweb�����µ�uploadĿ¼���ڱ����ľ���·�������û������ļ�����ᴴ��        
		    String targetDirectory = ServletActionContext.getServletContext().getRealPath("/mealimages");          
		    //�������ϴ��ļ�         
		    String targetFileName = generateFileName(docFileName);          
		    //��ָ��Ŀ¼�����ļ�          
		    File target = new File(targetDirectory, targetFileName);          
		    //��Ҫ�ϴ����ļ�copy��ȥ         
		    FileUtils.copyFile(doc, target); 		
		    //�޸Ĳ�ƷͼƬ
			meal.setMealImage(targetFileName);
    	}
		//���²�Ʒ����
		mealBiz.updateMeal(meal);
		return "toShowMeal";    	
    } 
    
    //����ɾ����Ʒ����������ת��toManageMeal
    public String deleteMeal() throws Exception {	
    	mealBiz.deleteMeal(meal.getMealId());
    	return "toManageMeal";
    }
	
	
	public File getDoc() {
		return doc;
	}
	public void setDoc(File doc) {
		this.doc = doc;
	}
	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public String getDocContentType() {
		return docContentType;
	}
	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

}
