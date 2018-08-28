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
    
    <title>My JSP 'c.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <c:set var="a" value="hello" scope="session"/>
	<c:if test="${not empty a }"> <%-- 判断属性a是否为空 --%>
		<c:out value="${a }"/>
	</c:if>
	
	<hr/>
	
	<h1>地址栏输入：http://localhost:8080/day13_1/core/c.jsp?name=zhangsan</h1>
	<c:choose>
		<c:when test="${not empty param.name }"><%-- if(request.getParameter("name") == null) --%>
			您没有给出名为name的参数
		</c:when>
		<c:otherwise>
			你给出了name的参数
		</c:otherwise>
	</c:choose>
  </body>
</html>
