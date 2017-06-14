<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="index_top.jsp" />
<script language="javascript">
	function clickdel(){
		return confirm("删除请点击确认");
	}	
</script>
	<div class="content">
		<img src="images/icon_order.gif" align="top" />
			<span style="color:red">您的订单中有以下内容:</span>
					<br />
					<hr />	
				<table align="center" width="947px" cellspacing="0" cellpadding="3"
						style="text-align:center; border:1px #cccccc solid;">
						<tr style="background-color:#CCCCFF;">
							<td>订单编号</td>
							<td>订单时间</td>
							<td>订单状态</td>
							<td>总额</td>
							<td>明细</td>
							<td>删除</td>
						</tr>				
						<s:set var="total" value="0" />								
						<s:iterator id="myOrders" value="#request.myOrdersList">
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
								<a href="/ssh/toOrdersDetails?oid=${oid }">查看</a>
							</td>
							<td>
							    <s:if test="#myOrders.orderState=='未处理'">
								  <a href="/ssh/deleteOrders?oid=${ oid}">删除</a>
								</s:if>
							</td>
						</tr>	
						<s:set var="total" value="#total+orderPrice" />    					    			
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
								￥：<s:property value="#total"/>
							</td>
							<td>
								
							</td>
							<td>
								
							</td>
						</tr>					
					</table>
	</div>