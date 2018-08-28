<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a.jsp' starting page</title>
    
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
   	<%
   		Date date = new Date();
   		request.setAttribute("date", date);
   		
   		double i = 3.1415926;
   		request.setAttribute("i",i);
   	%>
   	<fmt:formatDate value="${date }" pattern="yyyy-MM-dd HH:mm:ss"/><br/>
   	<fmt:formatNumber value="${i }" pattern="0.000"/><br/><%-- 保留小数点后2位，位数不足会补0 --%>
   	<fmt:formatNumber value="${i }" pattern="#.###"/><br/><%-- 保留小数点后2位，位数不足不会补0 --%>
  </body>
</html>
