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
  <button id="btn">点击这里</button>
  <h1 id="h1"></h1>
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
  	window.onload = function() {//文档 加载完成后执行
  		var btn = document.getElementById("btn");
  		btn.onclick = function() {//给按钮的 点击事件注册监听，当按钮被点击一下时调用
  			/*
  				ajax四步操作
  			*/
  			
  			//1.获取异步对象
  			var xmlHttp = createXMLHttpRequest();
  			
  			/*
  				2.打开域服务器的连接
  				需要三个参数：
  					请求方式
  					请求的url：http://localhost:8080/day23_1/Ajax1Servlet
  					请求是否为异步
  			*/
  			/*******************请求改为post*********************/
  			xmlHttp.open("POST", "<c:url value='/Ajax2Servlet'/>", true);
  			
  			/*********************添加请求头：Content-Type:application/x-www-form-urlencoded******************************/
  			xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  			//3.发送请求
  			/********************发送时指定请求体*******************************/
  			xmlHttp.send("username=张三&password=123");
  			
  			//4.得到响应内容
  			xmlHttp.onreadystatechange = function() {//异步对象的状态发生变化时会调用
  				//双重判断：判断xmlHttp的状态是否为4（服务器响应结束）。以及服务器响应的状态码为200（响应成功）
  				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
  					//获取服务器响应的文本内容
  					var text = xmlHttp.responseText;
  					
  					//获取id为h1的元素
  					var h1 = document.getElementById("h1");
  					//把服务器响应的内容设置到h1元素中
  					h1.innerHTML = text;
  				}
  			}
  			
  		}
  	}
  </script>
</html>
