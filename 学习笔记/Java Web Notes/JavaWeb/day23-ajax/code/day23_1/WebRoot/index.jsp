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
  </body>
  
  <script type="text/javascript">
	alert("");
//获取异步对象
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

window.onclick = function() {//页面加载完成时执行
//ajax四步
XMLHttpRequest xmlHttp = createXMLHttpRequest();//获取异步对象
xmlHttp.open("GET", "<c:url value='Ajax5ProvinceServlet'/>", "true");//打开与服务器的链接
xmlHttp.send(null);//发送请求

xmlHttp.onreadystatechange = function() { //异步对象状态发生改变时调用
	//双重判断
	if(xmlHttp.readyState ==4 && xmlHttp.status == 200) {
		//获取服务器返回的内容
		var provinces = xmlHttp.responseText;
		alert(provinces);
	}
};
};
  </script>
</html>
