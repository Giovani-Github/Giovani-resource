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
  	<h1>省市联动-JSON版</h1>
  	<select name="province" id="p">
	  	<option>===请选择省份===</option>
	</select>
	  
	  
	<select name="city" id="c">
		<option>===请选择城市===</option>
	</select>
  </body>
  
  <script type="text/javascript">
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
  	/*
  	第一步：
		1.页面加载完成时，给ProvinceServlet发送请求，得到所有josn串，使用eval函数执行，变成一个数组对象：包含所有的省份对象
		2.循环遍历数组对象，得到每个省份对象
		3.创建<option>元素
			给他的实际值赋值:<option value=pid>，值为：省份的id
  		4.创建文本节点对象，赋值为省份的名称
  		5.把文本对象添加到<option>元素中，即：给<option>添加显示值
  		6.获得<select name="province" id="p">，把每个option添加进去
	*/
	window.onload = function() {//页面加载完成后执行
  		//ajax四步
  		//得到异步对象
  		var xmlHttp = createXMLHttpRequest();
		//与服务器连接
		xmlHttp.open("GET", "<c:url value='/ProvinceServlet'/>", true);
		//发送请求
		xmlHttp.send(null);
		
		//对异步对象的状态改变时间注册监听器
		xmlHttp.onreadystatechange = function() {
			//双重判断
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				//得到json串
				var text = xmlHttp.responseText;
				//使用eval函数执行，得到包含所有省份对象的数组
				var provinceArray = eval("("+ text +")");
				
				//遍历数组
				for(var i = 0; i < provinceArray.length; i++) {
					//得到每一个省份对象
					var province = provinceArray[i];
					//创建<option>元素
					var op = document.createElement("option");
					//给他添加实际值
					op.value = province.pid;
					
					//添加显示值
					//创建文本节点对象，值为省份名称
					var textNode = document.createTextNode(province.name);
					//添加到option元素中
					op.appendChild(textNode);
					
					//得到<select name="province" id="p">元素
					var seEle = document.getElementById("p");
					//把op添加进去
					seEle.appendChild(op);
				}
			}
		};
  	};
	
	/*
	第二步：
		0.清空<select name="city" id="c">中的所有option元素，避免城市叠加
		1.获得<select name="province" id="p">元素
		2.给它的改变事件onchange添加监听器
		3.当选择了一个省份后，向CityServlet发送请求。请求包含参数：pid，省份的id
		4.得到服务器端发送过来的json串，使用eval函数执行它，得到一个包含：这个省份下所有城市对象的，数组对象
		5.遍历数组，得到每个城市对象
		6.创建option元素，添加实际值，添加显示值
		7.得到<select name="city" id="c">元素，把每个option元素添加进去
	*/
	
	var seEle = document.getElementById("p");
	seEle.onchange = function() {//<select name="province" id="p">的内容改变是调用
		//ajax四步
		//得到异步对象
		var xmlHttp = createXMLHttpRequest();
		//与服务器连接
		xmlHttp.open("POST", "<c:url value='/CityServlet'/>", true);
		//添加请求头
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		//发送请求，带上请求体:当前选择的省份id
		xmlHttp.send("pid=" + this.value);//相当于seEle.value
		//给异步对象的状态改变时间注册监听器
		xmlHttp.onreadystatechange = function() {
			//双重判断
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				//清除<select name="city" id="c">元素中的所有option，请选择那一项除外
				var cEle = document.getElementById("c");
				//获取cEle中所有option
				var opArray = cEle.getElementsByTagName("option");
				while(opArray.length > 1) {//如果只有请选择那一项，就不删除了
					cEle.removeChild(opArray[1]);//只删除1下标那一项，因为1下标没了，2下标就变成1下标了
				}
				
				
				
				//得到服务器端发送过来的json串
				var text = xmlHttp.responseText;
				//执行json串，得到数组：包含当前选择省份的所有城市对象
				var cityArray = eval("(" + text + ")");
				
				//遍历数组，得到每个城市对象
				for(var i = 0; i < cityArray.length; i++) {
					//城市对象
					var city = cityArray[i];
					//创建option
					var op = document.createElement("option");
					//添加实际值
					op.value = city.cid;
					
					//添加显示值
					//创建文本节点对象，值为城市的名称
					var textNode = document.createTextNode(city.name);
					//文本节点对象添加到op中
					op.appendChild(textNode);
			
					//op添加到cEle中
					cEle.appendChild(op);
				}
			}
		};
	};
	
  </script>
</html>
