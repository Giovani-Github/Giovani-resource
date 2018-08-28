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
    <h1>json案例</h1>
    <%-- 点击这里，在h3中显示服务器发送过来的对象的信息 --%>
    <button id="btn">点击这里</button>
    <h3 id="h3"></h3>
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
  	window.onload = function() {//页面加载完成时执行
  		//给button的点击事件添加监听器
  		var btn = document.getElementById("btn");
  		btn.onclick = function() {
  			/*ajax四步*/
  			//得到异步对象
  			var xmlHttp = createXMLHttpRequest();
  			//与服务器连接
  			xmlHttp.open("GET", "<c:url value='/json1Servlet'/>", true);
  			//发送请求
  			xmlHttp.send(null);
  			
  			//给xmlHttp的状态改变事件添加监听器
  			xmlHttp.onreadystatechange = function() {
  				//双重判断
  				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
  					//获取服务器响应过来的内容
  					//响应过来的是一个json串
  					var text = xmlHttp.responseText;
  					//使用eval()函数执行，得到一个person对象
  					var person = eval("(" + text + ")");
  					var s = person.name + "," + person.age + "," + person.sex;
  					//把perosn的信息在h3中现实
  					document.getElementById("h3").innerHTML = s;		
  				}
  			}
  		}
  	}
  </script>
</html>
