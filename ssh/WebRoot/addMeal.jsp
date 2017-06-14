<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加菜品</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	font: 14px;
	background: #F5F5F5;
}

table {
	border-collapse: collapse;
}

img {
	border: 0;
}

li {
	list-style: none;
}

a {
	text-decoration: none;
	color: #000408;
	cursor: pointer;
}

A:hover {
	COLOR: #0000cc;
	TEXT-DECORATION: none
}

.header {
	width: 100%;
	height: 60px;
	background: #1e89e0;
}

.nav {
	width: 650px;
	height: 60px;
	margin: 0 auto;
}

.nav_st {
	display: block;
	height: 60px;
	line-height: 60px;
	text-align: center;
	font-size: 22px;
	color: #FFFFFF;
}

.mainbox {
	width: 100%;
	margin-top: 25px;
}

.context {
	width: 650px;
	margin: 0 auto;
}

.usna {
	display: block;
	width: 650px;
	height: 60px;
	font-size: 22px;
}
.se{
	display: block;
	width: 350px;
	height: 60px;
	font-size: 22px;
}
.sub {
	display: block;
	width: 500px;
	height: 60px;
	margin: 25px auto;
	background: #4CD964;
	border-radius: 10px;
	font-size: 22px;
	color: #FFFFFF;
}
</style>

</head>
<body>

	<!--
        	头部
        -->
	<div class="header">
		<div class="nav">
			<strong class="nav_st">添加菜品</strong>
		</div>
	</div>

	<div class="mainbox">
		<div class="context">
			<div class="list">
				<s:form action="doAddMeal" method="post" enctype="multipart/form-data">
							<s:textfield name="meal.mealName"  placeholder="菜名" cssClass="usna" />
							<s:select name="meal.mealseries.seriesId" placeholder="菜系" list="#request.mealSeriesList"  cssClass="se" listKey="seriesId" listValue="seriesName" />  
							<s:textfield name="meal.mealSummarize" placeholder="摘要"  cssClass="usna"/>
							<s:textfield name="meal.mealDescription" placeholder="介绍"  cssClass="usna"/>
							<s:textfield name="meal.mealPrice" placeholder="价格"  cssClass="usna"/>
							<s:file name="doc" placeholder="图片"  cssClass="usna"/>
							<s:submit value="确定" align="center"  cssClass="sub"/>
					</s:form>
					<a href="javascript :;" onClick="javascript :history.back(-1);" style="display: block;width: 500px;height: 60px;margin: 25px auto;background: #4CD964;border-radius: 10px;font-size: 22px;color: #FFFFFF;line-height: 60px;text-align: center;">返回</a>
			</div>
		</div>
	</div>
	
 	
</body>
</html>
