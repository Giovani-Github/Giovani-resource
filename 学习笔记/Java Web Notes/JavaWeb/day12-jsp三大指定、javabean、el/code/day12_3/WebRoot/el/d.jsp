<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- 导入自定义函数库，因为我们自定义的函数库还没打成jar包，所以暂时写真实地址 --%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/my.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'd.jsp' starting page</title>
    
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
    <%-- 使用自定义函数库中的函数 --%>
   <h1>${my:fun("kdkf") }</h1> </br>
   <h1>${my:fun2() }</h1>
   <h1>${my:fun3("") }</h1></br>
  </body>
</html>
