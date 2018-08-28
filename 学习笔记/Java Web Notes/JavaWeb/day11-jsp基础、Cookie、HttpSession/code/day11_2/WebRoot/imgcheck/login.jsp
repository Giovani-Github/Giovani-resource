<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    <!-- 使用<img>标签请求VerfiyServlet，页面中显示图形验证码
    > 表单给出用户输入的验证码 -->
    <img src="/day11_2/VerifyServlet"/>
    
    <form action="/day11_2/LoginServlet2" method="post">
    	<input type="text" name="verify" value="验证码区分大小写"/>
    	<input type="submit" value="提交"/>
    </form>
    
    <!-- 显示错误信息 -->
    <% 
    	String errors = ""; 
    	String error = (String)request.getAttribute("error");
    	if(error != null) {
    		errors = error;
    	}
    %>
    
    <font color="red"><b><%= errors %></b></font>
  </body>
</html>
