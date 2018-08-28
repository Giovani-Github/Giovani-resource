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
    
    <title>My JSP 'd.jsp' starting page</title>
    
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
    <c:forEach var="i" begin="1" end="10" step="2">
    	${i }
    </c:forEach>
    <hr/>
    
    <%
    	String[] strs = {"a","b"};
    	request.setAttribute("strs", strs);
    %>
    <c:forEach items="${strs }" var="str">
    	${str }
    </c:forEach>
   	<hr/>
   	
   	<%
   		ArrayList list = new ArrayList();
   		list.add("one");
   		list.add("two");
   		request.setAttribute("list", list);
   	%>
   	<c:forEach items="${list }" var="lel" varStatus="vs"><%-- varStatus:创建循环状态变量 --%>
		${vs.index } <%--循环到的元素的下标 --%>
		${vs.count } <%--循环到的元素为第几个 --%>
		${vs.first } <%-- 循环到的元素是否为第一个元素--%>
		${vs.last } <%--循环到的元素是否为最后一个元素 --%>
		${vs.current }	<%--当前循环到的元素 --%>	 <br/>
   	</c:forEach>
  </body>
</html>
