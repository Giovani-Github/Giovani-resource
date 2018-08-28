<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <h1>json例子</h1>
  </body>
  <script type="text/javascript">
  	window.onload = function() {
  		//用json表示一个person对象
  		var person = {"name":"zhangsan", "age":18, "sex":"male"};
  		alert(person.name + "," + person.age + "," + person.sex);
  		
  		//json所表示的对象，使用字符串表示这个json串,双引号需要转义
  		var json = "{\"name\":\"zhangsan\", \"age\":18, \"sex\":\"male\"}";
  		//使用eval函数，把json字符串当做JavaScript代码执行。因为这个字符串是json串，所以会得到一个perosn对象
  		var person = eval("("+ json+ ")");
  		alert(person.name + "," + person.age + "," + person.sex);
  	}
  </script>
</html>
