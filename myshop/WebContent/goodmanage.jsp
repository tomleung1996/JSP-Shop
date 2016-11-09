<%@page import="org.apache.catalina.connector.Response"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="com"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/button.css" rel="stylesheet">
<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/myscript.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<Link Rel="SHORTCUT ICON" href="image/favicon.ico">
<title>商品管理</title>
</head>
<body>
	<com:navbar site="goodmanage"></com:navbar>
	<div class="container" id="main">
		<div class="row">
			<div class="text-center whitetext">
				<h1 class="loginhead">
					<img alt="myspace" src="image/thisNightWhite.png">&nbsp;商品管理<br>
				</h1>
			</div>
		</div>
		<c:if test="${!empty fail }">
			<div class="alert alert-danger">${fail }</div>
		</c:if>
		<div class="goodlist" id="fakecrop">
			<c:forEach items="${all }" var="good" varStatus="status">
				<c:if test="${status.count eq 1||status.count % 4 eq 1}">
					<div class="row">
				</c:if>
				<div class="col-md-3 column">
					<img alt="${good.gname }" src="${good.gpic }">
					<h4 class="whitetext goodes">${good.gname }</h4>
					<h4 class="whitetext goodes">仅售：￥${good.gprice }<br></h4>
					<c:if test="${user.privilege eq \"超级管理员\" }">
					<div class="row text-center">
					<form class="nobr" action="GoodSearchByIDServlet" method="post">
					<input type="hidden" value="${good.gid }" name="gid">
					<button type="submit" class="btn btn-sm btn-primary" value="修改">修改</button>
					</form>&nbsp;&nbsp;&nbsp;
					<form class="nobr" action="GoodDeleteServlet" method="post">
					<input type="hidden" value="${good.gid }" name="gid">
					<button type="submit" class="btn btn-sm btn-danger" value="删除" onclick="javascript:return deleteConfirm()">删除</button>
					</form>
					</c:if>
					</div>
					<br>
				</div>
				<c:if test="${status.count % 4 eq 0}">
		</div>
		</c:if>
		</c:forEach>
	</div>
	<div class="row text-center">
		<ul class="pagination">
		<c:forEach var="page" begin="1" end="${totalPages }">
		<li><a href="ShowAllServlet?currentPage=${page }&flag=1">${page }</a></li>
		</c:forEach>
		</ul>
	</div>
	</div>
</body>
</html>