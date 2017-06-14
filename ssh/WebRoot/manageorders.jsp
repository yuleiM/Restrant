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
			
				<div class="soso" style="float: left;">
						<s:form theme="simple" method="post" action="toManageOrders">
		                <s:label value="订单号：" />
		        	    <s:textfield name="orders.oid" />
						<s:label value="订单状态：" />
		        	    <s:select list="#{'全部':'全部','未处理':'未处理','已处理':'已处理'}" name="orders.orderState" listKey="key" listValue="value" />
		        	    <s:submit value="查询" />						
					  </s:form>
				</div>
	</div>
	<div class="items">
		<img src="images/icon_order.gif" align="top" />
					订单列表
					<br />
					<hr />
					<table align="center" width="947px" cellspacing="0" cellpadding="3"
						style="text-align:center; border:1px #cccccc solid;">
						<tr style="background-color:#CCCCFF;">
							<td>订单编号</td>
							<td>订单时间</td>
							<td>订单状态</td>
							<td>总额</td>
							<td>处理</td>
						</tr>				
						<s:set var="total" value="0" />								
						<s:iterator id="orders" value="#request.ordersList">
						<tr style="background-color:#FFFFFF;">						  
							<td>								
								<s:property value="oid"/>
							</td>
							<td>								
								<s:date name="orderTime" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:property value="orderState"/>
							</td>
							<td>
								<s:property value="orderPrice"/>
							</td>							
							<td>
							    <s:if test="#orders.orderState=='未处理'">
								  <a href="/ssh/handleOrders?oid=${oid}"><img src="images/handle.gif"
								width="12" height="12" /></a>
								</s:if>
							</td>
						</tr>										    			
						</s:iterator>
						<!-- 分页超链接开始 -->
						<table align="right">
						  <tr>
							<td width="130"></td>
							<td width="80">
								<s:if test="pager.curPage>1">
								
									<A href='/ssh/toManageOrders?pager.curPage=1&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>首页</A>
									<A href='/ssh/toManageOrders?pager.curPage=${pager.curPage-1 }&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>上一页</A>
								
								
								</s:if>
							</td>
							<td width="80">
								<s:if test="pager.curPage < pager.pageCount">
									<A href='/ssh/toManageOrders?pager.curPage=${pager.curPage+1}&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>下一页</A>
									<A href='/ssh/toManageOrders?pager.curPage=${pager.pageCount }&meal.mealseries.seriesId=${requestScope.seriesId}&meal.mealName=${requestScope.mealName}'>尾页</A>
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