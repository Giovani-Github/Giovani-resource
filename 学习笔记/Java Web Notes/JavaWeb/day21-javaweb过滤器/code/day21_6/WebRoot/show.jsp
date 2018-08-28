<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%-- 因为这个页面的数据会被输出到html文件中，所以，要在这里加个响应头，用来告诉浏览器html使用的是utf-8做出响应的，这样就不会乱码了 --%>
	<%-- 响应头加在这里，相当于加在html文件中，因为最终这个页面的数据都会被输出到html文件中 --%>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<h1 align="center">查看 结果</h1>
	
	<table align="center" border="1" width="50%">
		<tr>
			<th>书名</th>
			<th>单价</th>
			<th>分类</th>
		</tr>
		<c:forEach items="${booklist}" var="book">
			<tr>
				<td>${book.bname}</td>
				<td>${book.price}</td>
				<c:choose>
					<c:when test="${book.category eq 1 }"><td style="color:red">JavaSE</td></c:when>
					<c:when test="${book.category eq 2 }"><td style="color:blue">JavaSE</td></c:when>
					<c:when test="${book.category eq 3 }"><td style="color:gray">JavaSE</td></c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
  </body>
</html>
