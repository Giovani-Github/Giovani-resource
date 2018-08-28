<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--导包 --%>
<%@ page import="test1.domain.*" %>

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
    <%
    	//创建地址
    	Address address = new Address();
    	address.setCity("北京");
    	address.setStreet("西三旗");
    	
    	//创建员工
    	Employee emp = new Employee();
    	emp.setName("李小四");
    	emp.setSalary(123);
    	emp.setAddress(address);
    	
    	//保存员工到request域中
    	request.setAttribute("emp", emp);
    %>
    
    <h3>使用el表达式获取request域中的emp</h3>
    ${requestScope.emp }
    
    <%-- 获取Address的street属性值 --%>
    ${requestScope.emp.address.street }<%--这就是javabean导航，相当于request.getAttribute("emp").getAddress().getStreet() --%>
  	
  	<%-- 使用javabean导航，获取hehe属性，相当于:request.getAttribute("emp").getHehe() --%>
  	${emp.hehe }
  	${requestScope.emp.hehe }<%--效果和上面是一样的 --%>
  </body>
</html>
