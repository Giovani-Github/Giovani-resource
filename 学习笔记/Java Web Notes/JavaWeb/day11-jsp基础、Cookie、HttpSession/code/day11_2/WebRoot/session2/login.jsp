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
  <%
  	/*
  		完成附加项：在login.jsp中的用户名文本框中，显示上次登录的用户名
  		1.获取请求中的所有cookie
  		2.判断请求中是否有cookie
  			3.如果有：获取名为uname的cookie，把该cookie的值保存起来
  	
  	*/
  	String uname = "";
  	Cookie[] cs = request.getCookies();//获取请求张的所有cookie
  	if(cs != null) {//判断请求中是否有cookie
  		for(Cookie c : cs) {//如果有：获取名为uname的cookie，把该cookie的值保存起来
  			if("uname".equals(c.getName())) {
  				uname = c.getValue();
  			}
  		}
  	}
  %>
  
  <!-- 
  	1、提供登录表单
  	2、表单提交到LoginServlet
  	3、显示request域中的错误信息
   -->

	<!--   	提供登录表单、表单提交到LoginServlet -->
	<form action="/day11_2/LoginServlet" method="post">
		<%-- 在用户名文本框中显示上次登录的用户名 --%>
		用户名:<input type="text" name="username" value="<%= uname%>"/>	
		<input type="submit" value="提交"/>
	</form>
	
	<!-- 
		显示request域中的错误信息 
		1.获取request域中的错误信息error
		2.判断error是否不为null
		3.如果不为空，就显示错误信息error的值
	-->

	<%
		String error = (String)request.getAttribute("error");
		if(error != null) {
			out.print(error);
		}
	%>


	 
	<%--
	第二种处理方式
	 <%
	 	String errors = "";
	 	String error = (String)request.getAttribute("error");
	 	if(error != null) {
	 		errors = error;
	 	}
	 %>
	 
	 <font color="red"><b><%= errors %></b></font>
	--%>
  </body>
</html>
