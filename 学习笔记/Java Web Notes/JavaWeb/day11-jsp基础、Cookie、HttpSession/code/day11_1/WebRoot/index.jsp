<!-- 它是jsp指令，也是一种特殊的标签 -->
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();//获取项目名、
/*
http://localhost:8080/day11_1/
*/
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  	<!-- 向页面输出basePath -->
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    This is my JSP page. <br>
    
    <!-- 相当于在方法体里面定义局部变量 -->
    <%
    	int a = 10;
     %>
     
     <!-- 向页面输出a -->
     <%
     	out.print(a);
      %>
      <br/>
      <!-- 向页面输出a -->
      <%= a%>
      
      <!-- 相当于在类里面定义成员变量 -->
      <%!
      	int a = 100;
       %>
       
      <!-- 向页面输出a -->
      <%= this.a++ %>
      
      <!-- 定义方法fun -->
      <%!
      	public void fun() {
    		System.out.println(a);
	    }
       %>
       
       <!-- 调用方法fun -->
       <%
       	fun();
        %>
        
  </body>
</html>
