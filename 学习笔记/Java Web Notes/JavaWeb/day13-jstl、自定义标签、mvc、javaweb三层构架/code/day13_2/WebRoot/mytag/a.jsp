<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- 导入标签库，因为我们的标签库还没有打包成jar包，所以使用真实地址 --%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/Mytag.tld" %>
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
  	<%-- 使用我们自定义的标签 --%>
    <my:myTag1/><br/>
    <my:myTag2/><hr/>
 	
    <%-- 使用我们自定义的，有标签体的标签 --%>
    <%
    	request.setAttribute("xxx", "hahah");
    %>
    <my:myTag3>
    	${xxx }<br/>
    	${requestScope.xxx }<br/>
    	哈哈哈，你们好
    </my:myTag3>
  </body>
</html>
