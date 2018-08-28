<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 导入el函数库 -->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'c' starting page</title>
    
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
	  	String[] strs = {"a", "b","c"};
		List list = new ArrayList();
		list.add("a");
		pageContext.setAttribute("arr", strs);
		pageContext.setAttribute("list", list);
	%>
	
	${fn:length(arr) }<br/><!--3-->
	${fn:length(pageScope.arr)}</br><!--3-->
	${fn:length(list) }<br/><!--1-->
	${fn:toLowerCase("Hello") }<br/> <!-- hello -->
	${fn:toUpperCase("Hello") }<br/> <!-- HELLO -->
	${fn:contains("abc", "a")}<br/><!-- true -->
	${fn:containsIgnoreCase("abc", "Ab")}<br/><!-- true -->
	${fn:contains(arr, "a")}<br/><!-- true -->
	${fn:containsIgnoreCase(list, "A")}<br/><!-- true -->
	${fn:endsWith("Hello.java", ".java")}<br/><!-- true -->
	${fn:startsWith("Hello.java", "Hell")}<br/><!-- true -->
	${fn:indexOf("Hello-World", "-")}<br/><!-- 5 -->
	${fn:join(arr, ";")}<br/><!-- a;b;c -->
	${fn:replace("Hello-World", "-", "+")}<br/><!-- Hello+World -->
	${fn:join(fn:split("a;b;c;", ";"), "-")}<br/><!-- a-b-c -->
	
	${fn:substring("0123456789", 6, 9)}<br/><!-- 678 -->
	${fn:substring("0123456789", 5, -1)}<br/><!-- 56789 -->
	${fn:substringAfter("Hello-World", "-")}<br/><!-- World -->
	${fn:substringBefore("Hello-World", "-")}<br/><!-- Hello -->
	${fn:trim("     a b c     ")}<br/><!-- a b c -->
	${fn:escapeXml("<html></html>")}<br/> <!-- <html></html> -->
  </body>
</html>
