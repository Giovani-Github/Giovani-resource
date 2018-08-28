<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  
    <%-- 获取单个参数值：地址栏输入: http://localhost:8080/day12_3/param/a.jsp?username=zhangsan--%>
    ${param.username }
    ${param['username'] }
    <hr/>
    
    <%-- 获取多个参数值：地址栏输入: http://localhost:8080/day12_3/param/a.jsp?username=zhangsan&username=lisi--%>
    ${paramValues.username[0] }<%-- paramValues.username，获取到的是一个数组，所以加下标--%>
    ${paramValues.username[1] }
    
  </body>
</html>
