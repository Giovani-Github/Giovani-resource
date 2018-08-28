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
    
    <title>My JSP 'regist.jsp' starting page</title>
    
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
  <h1>注册</h1>
  	<%-- 显示错误信息 --%>
    <p style="color:red; font-weight:900">${msg }</p>
    
    <%-- 注册表单 --%>
    <form action="<c:url value='/RegistServlet'/>" method="post">
    	<%-- 其中value是回显数据：
    		我们填入的用户名已经被注册，在显示错误信息的同时，也不能清空我们输入的数据
    	--%>
    	用户名: <input type="text" name="username" value="${user.username }"/>
    			${errors.username }<br/> <%-- 显示错误信息，错误信息在RegistServlet中被写到了一个map中，并且把map保存到了reuqest域中，名为：errors --%>
    	密	码: <input type="password" name="password" value="${user.password }"/>${errors.password }<br/>
    	验证码: <input type="text" name="verifyCode" value="${user.verifyCode }" size="3"/>
    			<img id="Vcode" src="<c:url value='/VerifyCodeServlet'/>"/>${errors.verifyCode }
    			<a href="javascript:fun()">换一张</a><br/>
    	<input type="submit" value="注册"/>
    </form>
  </body>
  
  <%-- javaScript代码,完成验证码换一张操作 --%>
<script type="text/javascript">
	function fun() {
		var vcode = document.getElementById("Vcode");
		vcode.src = "<c:url value='/VerifyCodeServlet'/>?xxx=" + new Date().getTime();
	}
</script>
</html>
