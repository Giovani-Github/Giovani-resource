<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- 导入自定义标签库 --%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/Mytag.tld"%>
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
    <my:myTag4/><!-- 这个标签后面的内容都不会执行 -->
    你们好
    看不到的
    不用看了
    <%
    	request.setAttribute("xx","sfsafd");
    %>
    ${xx }
    后面的都看不到
  </body>
</html>
