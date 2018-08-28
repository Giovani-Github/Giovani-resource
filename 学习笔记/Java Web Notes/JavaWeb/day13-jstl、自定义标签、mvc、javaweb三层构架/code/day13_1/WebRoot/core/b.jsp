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
    
    <title>My JSP 'b.jsp' starting page</title>
    
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
    <c:url value="/core/b.jsp"/> <br/> <%-- 会在前面自动加上项目名：day13_1/core/b.jsp --%>
    ${pageContext.request.contextPath}/core/b.jsp <%-- 输出结果同上 --%>
  
  	<hr/>
  	<c:url value="/core/b.jsp">
  		<c:param name="username" value="张三"></c:param><%--会对参数进行url编码 --%>
  		<c:param name="password" value="密码"/>
  	</c:url>
  	
  	<hr/>
  	<c:url value="/core/b.jsp" var="url" scope="request"/><%-- 把url保存到request域中,名为url --%>
  	<c:out value="${url }"/>
  	
  	<hr/>
  	<h1>也可以使用这个标签来代替get和post请求的路径</h1>
	<a href="<c:url value='/core/b.jsp'/>">点击这里</a> 
	<form action="<c:url value='/core/b.jsp'/>" method="post">
		<input type="submit" value="提交"/>
	</form>
  </body>
</html>
