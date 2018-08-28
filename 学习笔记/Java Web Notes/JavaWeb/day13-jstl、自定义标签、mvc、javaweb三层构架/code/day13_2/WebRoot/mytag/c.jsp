<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--导入标签库 --%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/Mytag.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'c.jsp' starting page</title>
    
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
  	<%-- jsp真身中：test属性会被处理好，传递给标签处理类的setTest(boolean test)方法并且调用这个方法 --%>
    <my:myTag5 test="${empty param.xxx }"><%--如果xxx参数为空，即地址栏没带xxx参数 --%>
    	<my:myTag4/><%-- 后面的内容都不执行 --%>
    </my:myTag5>
    
    hahah
    hasfskldfj s法saf是f收到fs
  </body>
</html>
