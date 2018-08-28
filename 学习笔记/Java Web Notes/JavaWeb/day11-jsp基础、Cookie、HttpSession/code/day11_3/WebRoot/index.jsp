<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
  
  <!-- 完善登录项目，加入验证码 -->
  <body>
  		
      <%
  	/*
  		完成附加项：在login.jsp中的用户名文本框中，显示上次登录的用户名
  		1.获取请求中的所有cookie
  		2.判断请求中是否有cookie
  			3.如果有：获取名为uname的cookie，把该cookie的值保存起来
  	
  	*/
  	String uname = "";
  	Cookie[] cs = request.getCookies();//获取请求张的所有cookie
  	if(cs != null) {//判断请求中是否有cookie
  		for(Cookie c : cs) {//如果有：获取名为uname的cookie，把该cookie的值保存起来
  			if("uname".equals(c.getName())) {
  				uname = c.getValue();
  			}
  		}
  	}
  %>
  
  <!-- 
  	1、提供登录表单
  	2、表单提交到LoginServlet
  	3、显示request域中的错误信息
   -->

	<!--   	提供登录表单、表单提交到LoginServlet -->
	<form action="/day11_3/LoginServlet" method="post">
		<%-- 在用户名文本框中显示上次登录的用户名 --%>
		用户名:<input type="text" name="username" value="<%= uname%>"/>	<br/>
		密码:<input type="password" name="password"/> <br/>
		验证码:<input type="text" name="imgcheck" size="3"/>
			 <img id="img" src="/day11_3/VerifyServlet"/> 
			 <!-- 调用JavaScrip的change方法 -->
			<a href="javascript:_change()">换一张</a>
			<br/>
		<input type="submit" value="登录"/> 
	</form>
	
	<!-- 
		显示request域中的错误信息 
		1.获取request域中的错误信息error
		2.判断error是否不为null
		3.如果不为空，就显示错误信息error的值
	-->

	<%
		String errors = "";
		String error = (String)request.getAttribute("error");
		if(error != null) {
			errors = error;
		}
	%>
	<font color="red"><b><%= errors %></b></font>
  </body>

<%-- javaScript代码,完成验证码换一张操作 --%>
  <script type="text/javascript">
  /*
  	1.得到img元素
  	2.修改其src为：/day11_3/VerifyServlet
  */
  	function _change() {
  		var imgElement = document.getElementById("img");
  		imgElement.src = "/day11_3/VerifyServlet?a=" + new Date().getTime(); 
  		/*为了解决浏览器缓存，在路径后面加上参数（每次点击a超链接的时候，路径参数都不同），
  		这样浏览器就不会认为这个请求是跟上次一样的请求，就不会有缓存*/
  	}
  </script>
</html>
