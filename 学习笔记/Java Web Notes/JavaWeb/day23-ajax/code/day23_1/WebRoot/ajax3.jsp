<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  
  <body>
<form>
	用户名：<input type="text" name="username" id="name"/><span id="error"></span><br/>
	密	码：<input type="text" name="password"/><br/>
	<input type="submit" value="提交"/>
</form>
  </body>
  
  <script type="text/javascript">
 	//得到异步对象XMLHttpRequest
function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();
	} catch (e) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("哥们儿，你用的是什么浏览器啊？");
				throw e;
			}
		}
	}
}
 	window.onload = function() {//页面加载完调用
 		//获取用户名文本框
 		var nameEle = document.getElementById("name");
 		//给用户名文本框添加失去焦点事件
 		nameEle.onblur = function() {
 			//获取异步对象
 			var xmlHttp = createXMLHttpRequest();
 			//打开与服务器的连接
 			xmlHttp.open("POST", "<c:url value='/Ajax3Servlet'/>", true);
 			//添加请求头
 			xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 			//发送请求,带上请求体
 			xmlHttp.send("username=" + nameEle.value);
 			//得到响应内容
 			xmlHttp.onreadystatechange = function() {//异步对象的状态发生改变是会调用
 				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
 					/*
 						1.获取服务器返回的内容
 						2.获取span元素
 						3.判断服务器返回的内容
 							为1：表示用户名已经被注册，向span元素设置内容：用户名已经被注册
 							为0：表示用户名未被注册，清楚掉span元素中的内容，什么都不显示
 					*/
 					var text = xmlHttp.responseText;
 					var error = document.getElementById("error");
 					if(text == 1) {
 						error.innerHTML = "用户名已经被注册！"
 					} else {
 						error.innerHTML = "";
 					}
 				}
 			}
 		}
 	}
  </script>
</html>
