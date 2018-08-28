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
    <!--
           如果客户端禁用了Cookie，那么就无法得到sessionId，那么session也就无用了！
  	* 也可以使用URL重写来替代Cookie
    	> 让网站的所有超链接、表单中都添加一个特殊的请求参数，即sessionId
   		> 这样服务器可以通过获取请求参数得到sessionId，从而找到session对象。
   	-->
   	<a href="/day11_2/BServlet;JSESSIONID=<%=session.getId()%>">点击这里</a>
   	<form action="/day11_2/BServlet;JSESSIONID=<%=session.getId()%>" method="post">
   		<input type="submit" value="提交"/>
   	</form>
   	
   	<!-- 
   		也可以使用另一种智能的方法
   		response.encodeURL(String url)
    		> 该方法会对url进行智能的重写：当请求中没有归还sessionid这个cookie，那么该方法会重写url，否则不重写！当然url必须是指向本站的url。
   	 -->
   	 <a href="<%= response.encodeURL("/day11_2/BServlet")%>">点击这里2</a>
  </body>
</html>
