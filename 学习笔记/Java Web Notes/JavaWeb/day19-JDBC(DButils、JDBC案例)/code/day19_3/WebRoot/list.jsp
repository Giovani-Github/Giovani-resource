<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>
    
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
<h3 align="center">客户列表</h3>
<table border="1" width="70%" align="center">
	<tr>
		<th>客户姓名</th>
		<th>性别</th>
		<th>生日</th>
		<th>手机</th>
		<th>邮箱</th>
		<th>描述</th>
		<th>操作</th>
	</tr>
<%--循环遍历request域中的pb里的beanList，即所有用户的对象Customer var=每次从beanList遍历出来的一个用户  itmes=遍历的list--%>
<c:forEach items="${pb.beanList}" var="cstm">
	<tr>
		<td>${cstm.cname }</td>
		<td>${cstm.gender }</td>
		<td>${cstm.birthday }</td>
		<td>${cstm.cellphone }</td>
		<td>${cstm.email }</td>
		<td>${cstm.description }</td>
		<td>
			<a href="<c:url value='/CustomerServlet?method=preEdit&cid=${cstm.cid}'/>">编辑</a>
			<a href="<c:url value='/CustomerServlet?method=delete&cid=${cstm.cid }'/>">删除</a>
		</td>
	</tr>
</c:forEach>
</table>
<center>
	第${pb.pc }页/共${pb.tp}页
	<a href="<c:url value="/CustomerServlet?method=query&pc=1${pb.url }"/>">首页</a>
	<%-- 如果当前页面大于1，才显示上一页。即：当前页面不是第一页，才显示上一页 --%>
	<c:if test="${pb.pc > 1 }">
	<a href="<c:url value="/CustomerServlet?method=query&pc=${pb.pc - 1}${pb.url }"/>">上一页</a>
	</c:if>
	
	
	<%-- 显示页码列表：计算begin和end--%>
	<c:choose>
		<%-- 如果总页数不足或刚好10页，那么把所有页数显示出来 --%>
		<c:when test="${pb.tp <= 10 }">
			<%-- 创建名为begin的变量，没有指定域，默认保存在page域 --%>
			<c:set var="begin" value="1"/>
			<c:set var="end" value="${pb.tp }"/>
		</c:when>
		<c:otherwise>
			<%-- 如果总页面大于10页：使用公式计算；begin=pc-5, end=pc + 4 --%>
			<c:set var="begin" value="${pb.pc-5 }"/>
			<c:set var="end" value="${pb.pc+4 }"/>
			<%-- 头溢出 --%>
			<c:if test="${begin < 1 }">
				<c:set var="begin" value="1"/>
				<c:set var="end" value="10"/>
			</c:if>
			<%-- 尾溢出 --%>
			<c:if test="${end > pb.tp }">
				<c:set var="begin" value="${pb.tp - 9 }"/>
				<c:set var="end" value="${pb.tp }"/>
			</c:if>
		</c:otherwise>
	</c:choose>

	<%-- 循环遍历出页码列表 --%>		
	<c:forEach var="i" begin="${begin }" end="${end }">
		<c:choose>
			<%-- 如果遍历到的是当前页码，就不用使用超链接 --%>
			<c:when test="${i eq pb.pc }">
				[${i }]
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/CustomerServlet?method=query&pc=${i }${pb.url }'/>">[${i }]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<%-- 如果当前页面小于总页数，才显示下一页。即：当前页面不是第最后一页，才显示下一页 --%>
	<c:if test="${pb.pc < pb.tp }">
	<a href="<c:url value="/CustomerServlet?method=query&pc=${pb.pc + 1 }${pb.url }"/>">下一页</a>
	</c:if>
	<a href="<c:url value="/CustomerServlet?method=query&pc=${pb.tp }${pb.url }"/>">尾页</a>
</center>
  </body>
</html>
