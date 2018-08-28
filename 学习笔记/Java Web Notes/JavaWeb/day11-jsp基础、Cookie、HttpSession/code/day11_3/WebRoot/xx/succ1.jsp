<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'succ1.jsp' starting page</title>
    
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
   	<!-- 
   		1.从session中获取用户信息
   		2.如果存在，说明已经登录，显示用户名！
   		3.如果不存在，向request域中保存错误信息， 转发到login.jsp
   	 -->
   	 
   	 <%
   	 	//从session中获取用户信息
   	 	String username = (String)session.getAttribute("username");
   	 	
   	 	//判断username是否不为null
   	 	if(username != null) {
   	 		out.print(username);
   	 	} else {
   	 		//向request域中保存错误信息
   	 		request.setAttribute("error", "你没有登录");
   	 		//转发到login.jsp
   	 		request.getRequestDispatcher("/index.jsp").forward(request, response);
   	 	}
   	 	
   	 %>
  </body>
</html>
