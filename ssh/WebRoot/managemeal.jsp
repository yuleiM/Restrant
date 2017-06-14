<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="index_top.jsp" />
<script language="javascript">
	function clickdel(){
		return confirm("删除请点击确认");
	}	
</script>
<div class="content">
<div class="list">
			<ul class="list_ul">
			<li>全部分类：</li>
				<s:iterator id="mealSeries" value="#request.mealSeriesList">
						
						
						<li><a href="toManageMeal?meal.mealseries.seriesId=${mealSeries.seriesId}">${mealSeries.seriesName }</a></li>
						
				</s:iterator>
				</ul>
				<div class="soso">
						<s:form theme="simple" method="post" action="toManageMeal">
		        	    <s:textfield name="meal.mealName" placeholder="菜名"/>
		        	    <!-- 通过隐藏表单域保存用户选择过的菜系编号，可根据餐品名称和菜系组合查询 -->
						<s:hidden name="meal.mealseries.seriesId" value="%{#request.seriesId}"  />
						<s:submit value="查询" class="sub"/>					
					  </s:form>
				</div>
	</div>
	<div class="items">
		<table width="947px" cellspacing="0" cellpadding="4" align="center"
						style="text-align:center; border:1px #cccccc solid;">
						<tr style="background-color:#CCCCFF;">
							
							<td>
								菜名
							</td>
							<td>
								摘要
							</td>
							<td>
								价格
							</td>	
							<td>
								修改
							</td>		
							<td>
								删除
							</td>					
						</tr>						
						<s:iterator id="mealItem" value="#request.mealList" status="st">
						<tr>							
							
							<td>
								<s:property value="mealName"/>
							</td>
							<td>
								<s:property value="mealSummarize"/>
							</td>
							<td>
								<s:property value="mealPrice"/>
							</td>	
							<td>
							    <a href="toUpdateMeal?meal.mealId=${mealItem.mealId}">修改</a>								
							</td>	
							<td>
								<a href="deleteMeal?meal.mealId=${mealItem.mealId}" onclick="return clickdel()">删除</a>
							</td>				
						</tr>						
						</s:iterator>
						<!-- 分页超链接开始 -->
						<div class="page">
						<table align="right">
						  <tr>
							<td width="130"></td>
							<td width="80">
								<s:if test="pager.curPage>1">
								
									<A href='/ssh/toManageMeal?pager.curPage=1&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>首页</A>
									<A href='/ssh/toManageMeal?pager.curPage=${pager.curPage-1 }&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>上一页</A>
								
								
								</s:if>
							</td>
							<td width="80">
								<s:if test="pager.curPage < pager.pageCount">
									<A href='/ssh/toManageMeal?pager.curPage=${pager.curPage+1}&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>下一页</A>
									<A href='/ssh/toManageMeal?pager.curPage=${pager.pageCount }&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>尾页</A>
								</s:if>
							</td>
							<td><span class="page">共${pager.rowCount}记录，共${pager.curPage}/${pager.pageCount}页</span>
							
							</td>
						  </tr>
						</table>
						</div>						
						<!-- 分页超链接结束-->
	</div>
	</div>
	</table>
	</div>
	</div>