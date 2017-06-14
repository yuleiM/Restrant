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
			<span style="color:red">订单明细:</span>
					<br />
					<hr />	
			<table align="center" width="947px" cellspacing="0" cellpadding="3"
						style="text-align:center; border:1px #cccccc solid;">
						<tr style="background-color:#CCCCFF;">
							<td>明细编号</td>
							<td>菜名</td>
							<td>价格</td>
							<td>数量</td>
							<td>总额</td>							
						</tr>				
					    <s:set var="count" value="0" />								
						<s:iterator id="ordersDtsItem" value="#request.ordersDtsList">
						<tr style="background-color:#FFFFFF;">						  
							<td>								
								<s:property value="odid"/>								
							</td>
							<td>								
								<s:property value="meal.mealName"/>
							</td>
							<td>
								<s:property value="mealPrice"/>
							</td>
							<td>
								<s:property value="mealCount"/>
							</td>
							<td>
								<s:property value="mealPrice*mealCount"/>
							</td>							
						</tr>	
						<s:set var="count" value="#count+mealPrice*mealCount" />						 					    			
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
								￥：<s:property value="#count"/>
							</td>
							
						</tr>					
					</table>
	</div>