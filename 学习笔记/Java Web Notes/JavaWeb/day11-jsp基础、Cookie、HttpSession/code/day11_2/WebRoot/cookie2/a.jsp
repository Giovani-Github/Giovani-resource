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
    <h1>演示cookie生命时长</h1>
   	<%
   		//创建cookie
   		Cookie cookie1 = new Cookie("aa", "AA");
   		Cookie cookie2 = new Cookie("bb", "BB");
   		Cookie cookie3 = new Cookie("cc", "CC");
   		
   		//设置cookie生命时长
   		cookie1.setMaxAge(60);//把cookie保存到客户端硬盘上，有效时间为60秒
   		cookie2.setMaxAge(-1);//保存到客户端内存中，客户端关闭时cookie死亡
   		cookie3.setMaxAge(60*60);//把cookie保存到客户端硬盘上，有效时间为一小时
   		
   		//把cookie保存到客户端
   		response.addCookie(cookie1);
   		response.addCookie(cookie2);
   		response.addCookie(cookie3);
   		

   	 %>
  </body>
</html>
