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
	  <h1>省市联动</h1>
	  <select name="province" id="p">
	  	<option>===请选择省份===</option>
	  </select>
	  
	  
	  <select name="city" id="c">
	  	<option>===请选择城市===</option>
	  </select>
  </body>

<script type="text/javascript">

	/*
		步骤：
		1.	页面加载完成时：
				请求Ajax5ProvinceServlet，得到所有省份的字符串: 北京，天津，....
				使用逗号分隔开，得到装有所有省份的数组
				遍历数组：
					创建option元素，给实际值（<option value="bj">）赋值
					创建文本节点（省份名称），添加到option中
				得到<select name="province" id="p">元素
				把option添加进去
				
				
		2.省份选择框选择了一个省时（给select元素的改变事件添加监听器，onchange）：
			***清空上次选择过省份后，存余的option节点（避免多个省份的城市叠加），不删除请选择的那个option
				得到<select name="city" id="c">元素
				获取所有option元素，装载在数组中
				遍历数组：(arr.length > 1，如果是只有一个option元素，那么就是请选择的那个option，所以就不遍历)
					通过父节点(<select>)删除：select.romoveChild(1);
					始终删除下标为1的节点：因为1下标删除了，2下标就变成了1下标
				
					
			请求Ajax5CityServlet，并且带上参数（选择的省份名称，pname），得到xml：
				<province name="北京">
					<city>西城区</city>
					...
				</province>
			解析这个xml：
				获得所有city节点，存储在数组中
				遍历数组：
					创建option元素，给实际值（<option value="bj">）赋值
					得到节点的文本内容（即省份名称，需处理浏览器差异问题）
					创建文本节点（省份名称）
					把文本节点添加到option元素中
				得到<select name="city" id="c">元素
				把option添加进去
	*/
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
	//第一步
	window.onload = function() {//页面加载完成时执行
		//ajax四步
		var xmlHttp = createXMLHttpRequest();//获取异步对象
		xmlHttp.open("GET", "<c:url value='/Ajax5ProvinceServlet'/>", true);//打开与服务器的链接
		xmlHttp.send(null);//发送请求
		
		xmlHttp.onreadystatechange = function() { //异步对象状态发生改变时调用
			//双重判断
			if(xmlHttp.readyState ==4 && xmlHttp.status == 200) {
				//获取服务器返回的内容
				var provinces = xmlHttp.responseText;
				//使用“,”分割，得到省份的数组
				var parr = provinces.split(",");
				//得到<select name="province" id="p">元素
				var seEle = document.getElementById("p");
				
				//循环遍历该数组
				for(var i = 0; i < parr.length; i++) {
					//得到当前遍历到的省份
					var province = parr[i];
					//创建option元素
					var op = document.createElement("option");
					//设置实际值, <option value="北京"/>
					op.value = province;
					
					//设置显示值，<option value="北京">显示值</option>
					
					//创建文本元素
					var textNode = document.createTextNode(province);
					//把文本元素添加到op元素中
					op.appendChild(textNode);
					
					//把op元素添加到<select name="province" id="p">元素中
					seEle.appendChild(op);
				}
			}
		};
	};
	
	//第二步	
	//得到<select name="province" id="p">元素，给他的onchange时间添加监听器
	var pEle = document.getElementById("p");
	pEle.onchange = function() {
		//得到异步对象
		var xmlHttp = createXMLHttpRequest();
		//与服务器连接
		xmlHttp.opend()
		xmlHttp.open("POST", "<c:url value='/Ajax5CityServlet'/>", true);
		//设置请求头
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		//发送请求，带请求体，即pname参数，选中的省份
		xmlHttp.send("pname=" + pEle.value);//下拉列表的值就是选中的省份
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				//清除上次选择留下的痕迹
				//得到<select name="city" id="c">元素
				var cEle = document.getElementById("c");
				//得到所有option
				var options = cEle.getElementsByTagName("option");
				//遍历
				for(var i = 0; options.length > 1; i++) {//如果只有一个option，那么这个op就是请选择的那个，所以不删除
					cEle.removeChild(options[1]);//请选择的那个op是第0个。只删除第1个，因为1删除了，2就变成了1，又会继续删除
				}
							
				
				var cityDoc = xmlHttp.responseXML;//服务器响应过来的字符形式xml，已经被解析成了document
				var citys = cityDoc.getElementsByTagName("city");//得到所有city标签，数组
				
				//遍历所有city标签
				for(var i = 1; i < citys.length; i++) {
					var cityEle = citys[i];//得到一个city标签
					var city;//city标签的内容，就是城市名称
					
					//需要处理浏览器差异
					if(window.addEventListener) { //如果为true，表示不是ie
						city = cityEle.textContent;
					} else {
						city = cityEle.text;//ie
					}
					
					//创建option元素
					var op = document.createElement("option");
					//添加实际值
					op.value = city;
					
					//添加显示值
					//创建文本元素
					var textNode = document.createTextNode(city);
					//文本元素添加到op
					op.appendChild(textNode);
	
					//把op添加到cEle
					cEle.appendChild(op);
				}
			}
		};
	};
</script>
</html>
