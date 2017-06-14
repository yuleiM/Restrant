<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="index_top.jsp" />
<script language="javascript">
	function clickdel(){
		return confirm("删除请点击确认");
	}	
</script>
	<div class="content">
		<img src="images/zdkf.jpg" align="top" />
			<span style="color:red">您的购物车中有以下商品:</span>
					<br />
					<hr />	
			<table align="center" width="947px" cellspacing="0" cellpadding="3"
						style="text-align:center; border:1px #cccccc solid;">
						<tr style="background-color:#CCCCFF;">
							<td>编号</td>
							<td>商品名称</td>
							<td>单价</td>
							<td>数量</td>
							<td>金额</td>
							<td>删除</td>
						</tr>				
						<s:set var="sumPrice" value="0"  />								
						<s:iterator id="cartItem" value="#session.cart">
						<tr style="background-color:#FFFFFF;">						  
							<td>								
								<s:property value="value.meal.mealId"/>
							</td>
							<td>
								<s:property value="value.meal.mealName"/>
							</td>
							<td>
								￥<s:property value="value.meal.mealPrice"/>
							</td>
							<td>								
								<input type="text"  value="${value.quantity}" size="10" 
								onchange="window.location='updateSelectedQuantity?mealId=${value.meal.mealId}&quantity='+this.value;">
							</td>
							<td>
								￥<s:property value="value.quantity*value.meal.mealPrice"/>
							</td>
							<td>
								<a href="deleteSelectedOrders?mealId=${value.meal.mealId}" onclick="return clickdel()">删除</a>							
							</td>
						</tr>	
						    <s:set var="sumPrice" value="#sumPrice+value.quantity*value.meal.mealPrice" />						    			
						</s:iterator>				
						<tr style="background-color:#CCCCFF;">
							<td>
								合计
							</td>
							<td>
								-
							</td>
							<td>
								-
							</td>
							<td>
								-
							</td>
							<td>
								￥：<s:property value="#sumPrice"  /> 
								<s:set var="sumPrice" value="#sumPrice" scope="session" />
							</td>
							<td>
								-
							</td>
						</tr>					
					</table>
					
					<br />
					<table width="300" cellspacing="0" cellpadding="4" align="center"
						style="text-align:center; border:1px #cccccc solid;">
						<tr style="background-color:#CCCCFF;">
							<td>
								<a href="/ssh/clearCart">清空购物车</a>
							</td>
							<td>
								<a href="/ssh/toShowMeal">继续购物</a>
							</td>
							<td>
							<s:if test="#session.cart!=null">
								<a href="/ssh/addOrders">生成订单</a>
								</s:if>
							</td>
						</tr>
					</table>
	</div>