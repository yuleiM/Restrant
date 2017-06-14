<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="index_top.jsp" />
 
<div class="content">
	
	<div class="list">
			<ul class="list_ul">
			<li>全部分类：</li>
				<s:iterator id="mealSeries" value="#request.mealSeriesList">
						
						
						<li><a href="toShowMeal?meal.mealseries.seriesId=${mealSeries.seriesId}">${mealSeries.seriesName }</a></li>
						
				</s:iterator>
				</ul>
				<div class="soso">
						<s:form theme="simple" method="post" action="toShowMeal">
		        	    <s:textfield name="meal.mealName" placeholder="菜名"/>
		        	    <!-- 通过隐藏表单域保存用户选择过的菜系编号，可根据餐品名称和菜系组合查询 -->
						<s:hidden name="meal.mealseries.seriesId" value="%{#request.seriesId}"  />
						<s:submit value="查询" class="sub"/>					
					  </s:form>
				</div>
	</div>
	<div class="items">
		<table cellpadding="5" cellspacing="1" style="font-size: 12px;">
					    <!-- 餐品循环开始 -->
						<s:iterator id="mealItem" value="#request.mealList" status="st">
								<s:if test="#st.getIndex()%3==0">
						<tr>
						</s:if>
							<td>
								<a href="toShowDetails?meal.mealId=${mealItem.mealId}"><img
										src="mealimages/${mealItem.mealImage }" width="148"
										height="126" border="0" /> </a>
							</td>
							<td>
								<div>
									${ mealItem.mealId}
									:
									${ mealItem.mealName}
									<br />
									<span style="text-decoration: line-through; color: gray;">原价：人民币${ mealItem.mealPrice}元</span>
									<br />
									现价：人民币
									${ mealItem.mealPrice*0.9}
									元
								</div>
								<a href="/ssh/toShowDetails?meal.mealId=${mealItem.mealId}"><img src="images/detail_cn.gif" border="0" width="60" height="20" /></a>
								<a href="addtoshopcart?mealId=${mealItem.mealId }"><img src="images/buy_cn.gif" border="0" width="60" height="20" /></a>
							</td>
							<s:if test="#st.getIndex()%3==2">
						</tr>
						</s:if>
						</s:iterator>
						<!-- 餐品循环开始 -->
						
						<!-- 分页超链接开始 -->
						<div class="page">
						<table align="right">
						  <tr>
							<td width="130"></td>
							<td width="80">
								<s:if test="pager.curPage>1">
								
									<A href='/ssh/toShowMeal?pager.curPage=1&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>首页</A>
									<A href='/ssh/toShowMeal?pager.curPage=${pager.curPage-1 }&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>上一页</A>
								
								
								</s:if>
							</td>
							<td width="80">
								<s:if test="pager.curPage < pager.pageCount">
									<A href='/ssh/toShowMeal?pager.curPage=${pager.curPage+1}&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>下一页</A>
									<A href='/ssh/toShowMeal?pager.curPage=${pager.pageCount }&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>尾页</A>
								</s:if>
							</td>
							<td><span class="page">共${pager.rowCount}记录，共${pager.curPage}/${pager.pageCount}页</span>
							
							</td>
						  </tr>
						</table>
						</div>						
						<!-- 分页超链接结束-->
					
					</table>
	</div>
</div>