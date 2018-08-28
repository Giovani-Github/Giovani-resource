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
    
    <title>My JSP 'json1.jsp' starting page</title>
    
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
    <h1>使用自己编写的ajax小工具，完成ajax操作</h1>
    <h3 id="h3"></h3>
  </body>
  <%-- 导入我们自己编写的ajaxutils.js --%>
  <script type="text/javascript" src="<c:url value='/ajax-lib/ajaxutils.js'/>"></script>
  <script type="text/javascript">
   window.onload = function() {
	   /*
		使用ajaxutils中的一个方法：ajax(option)
		给出一个对象option，其中属性包括：
			method:请求方式，可以不给，默认为true
			url:请求路径
			asyn:是否异步，true/false，可以不给，默认为true
			params:请求体，get请求需要给出null
			callback：回调方法。我们给出匿名方法：function(data){}，data为服务器响应的数据，在这个方法里面，对响应数据进行处理
			type:服务器响应的数据是什么类型
	   */
	   
	   //{}表示对象，这里对象用json来创建
	   ajax(
			{
				"url":"<c:url value='/AServlet'/>",
				"type":"json",
				"callback":function(data, params) {
					document.getElementById("h3").innerHTML = data.name + "," + data.age + "," + data.sex;
				}
			}	   
	   );
	   
   }
  </script>
</html>
