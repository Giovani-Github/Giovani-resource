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
    	request.setAttribute("a","<script>alert('hello');</script>");
    %>
	<c:out value="aa"/><br/><%-- 输出字符串aa --%>
	<c:out value="${a }"></c:out><br/><%-- 全域查找，与${aaa}相同 --%>
	<c:out value="${b }" default="ccc"/><br/><%-- 当${b}不存在时，输出ccc字符串 --%>
  	<c:out value="${a }" default="ccc" escapeXml="false"></c:out><%-- escapeXml：对“<”,“>”进行转义，以避免遭到JavaScript攻击。默认值是true，表示要进行转义 --%>
  	<hr/>
  	<c:set var="b" value="hello"/><%--在pageContext中添加name为b，value为hello的数据。 --%>
  	<c:set var="c" value="hai" scope="session"/><%--在session中添加name为c，value为hai的数据。 --%>
  	${pageScope.b }<br/>
  	<c:out value="${c }"/>
  </body>
</html>
