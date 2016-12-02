<%@page import="org.apache.catalina.connector.Response"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
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
					<img alt="myspace" src="image/thisNightWhite.png"
						class="img-responsive center-block">&nbsp;商品管理<br>
				</h1>
			</div>
		</div>
		<c:if test="${!empty fail }">
			<div class="alert alert-danger">${fail }</div>
		</c:if>
		<div class="goodlist" id="fakecrop">
			<c:forEach items="${all }" var="good" varStatus="status">
				<c:if test="${status.count eq 1 || (status.count-1) % 4 eq 0}">
					<div class="row">
				</c:if>
				<div class="col-md-3 col-sm-4 col-xs-12 column">
				<div>
					<a href="GoodDetailServlet?gid=${good.gid }"> <img
						alt="${good.gname }" src="${good.gpic }"></a>
					<h4 class="whitetext goodes">${good.gname }</h4>
					<h4 class="whitetext goodes">
						仅售：￥${good.gprice }<br>
					</h4>
					</div>
					<div class="text-center">
					<c:if test="${user.privilege eq \"超级管理员\" }">
						<form class="nobr" action="GoodSearchByIDServlet" method="post">
							<input type="hidden" value="${good.gid }" name="gid">
							<button type="submit" class="btn btn-sm btn-primary" value="修改">修改</button>
						</form>&nbsp;&nbsp;&nbsp;
					<form class="nobr" action="GoodDeleteServlet" method="post">
							<input type="hidden" value="${good.gid }" name="gid">
							<button type="submit" class="btn btn-sm btn-danger" value="删除"
								onclick="javascript:return deleteConfirm()">删除</button>
						</form>
					</c:if>
					</div>
					<br>
				</div>
				<c:if test="${status.count % 4 eq 0 || status.count eq 4 || status.count eq length}">
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="row"></div>
		<div class="row text-center">
			<ul class="pagination">
				<c:if test="${currentPage>1 }">
					<li><a
						href="ShowAllServlet?currentPage=${currentPage-1 }&flag=1">&laquo;</a></li>
				</c:if>
				<c:forEach var="page" begin="1" end="${totalPages }">
					<li <c:if test="${currentPage eq page }">class="active"</c:if>><a
						href="ShowAllServlet?currentPage=${page }&flag=1">${page }</a></li>
				</c:forEach>
				<c:if test="${currentPage<totalPages }">
					<li><a
						href="ShowAllServlet?currentPage=${currentPage+1 }&flag=1">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<com:footer></com:footer>
</body>
</html>