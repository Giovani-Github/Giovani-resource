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
  				2.打开与服务器的连接
  				需要三个参数：
  					请求方式
  					请求的url：http://localhost:8080/day23_1/Ajax1Servlet
  					请求是否为异步
  			*/
  			xmlHttp.open("GET", "<c:url value='/Ajax4Servlet'/>", true);
  			
  			//3.发送请求
  			xmlHttp.send(null);//get请求没有请求体，但是也必须给出null，不然某些浏览器可能不会发送，如：火狐
  			
  			//4.得到相应内容
  			xmlHttp.onreadystatechange = function() {//异步对象的状态发生变化时会调用
  				//双重判断：判断xmlHttp的状态是否为4（服务器响应结束）。以及服务器响应的状态码为200（响应成功）
  				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
  					//获取服务器响应的xml内容，已经解析成document
  					var doc = xmlHttp.responseXML;
  					
  					///////////////////////////////////////////
  					//解析docment，w3c提供的domAPI
  					//得到student元素
  					//得到所有的student元素数组，然后去的第一个
  					var stu = doc.getElementsByTagName("student")[0];
  					var number = stu.getAttribute("number");//获取属性名为number的值
  					var name;//name元素
  					var age;//age元素
  					
  					if(window.addEventListener) {//返回true，表示不是ie浏览器
  	  					//获取name元素，然后得到他的内容	
  	  					name = stu.getElementsByTagName("name")[0].textContent;//大多数浏览器支持
  						age = stu.getElementsByTagName("age")[0].textContent;//大多数浏览器支持
  					} else {
  	  					name = stu.getElementsByTagName("name")[0].text;//ie支持
  	  					age = stu.getElementsByTagName("age")[0].text;//ie支持
  					}

  					///////////////////////////////////////////
  					
  					//获取id为h1的元素
  					var h1 = document.getElementById("h1");
  					//为h1元素设置内容
  					h1.innerHTML = "number:" + number + " name:" + name + " age:" + age;
  				}
  			}
  			
  		}
  	}
  </script>
</html>
