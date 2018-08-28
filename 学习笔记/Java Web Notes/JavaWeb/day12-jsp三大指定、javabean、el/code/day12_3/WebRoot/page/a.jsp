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
    <%--获取项目名称:/day12_3 --%>
    ${pageContext.request.contextPath }<%-- 相当于：pageContext.getRequest().getContextPath()--%>

	<h1>以后：get请求的href，和post请求的action,都用el表达式来代替项目名</h1>
	<a href="${pageContext.request.contextPath }/page/b.jsp">点击这里</a>
	
	<form action="${pageContext.request.contextPath }/page/b.jsp" method="post">
		<input type="submit" value="提交"/>
	</form>
  </body>
</html>
