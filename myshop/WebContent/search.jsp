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
<title>搜索结果</title>
</head>
<body>
	<com:navbar site="search"></com:navbar>
	<div class="container" id="main">
		<div class="row">
			<div class="text-center whitetext">
				<h1 class="loginhead">
					<img alt="myspace" src="image/thisNightWhite.png"
						class="img-responsive center-block">&nbsp;搜索结果<br>
				</h1>
			</div>
		</div>
		<c:if test="${!empty fail }">
			<div class="alert alert-danger">${fail }</div>
		</c:if>
		<c:if test="${!empty success }">
			<div class="alert alert-success">${success }</div>
		</c:if>
		<c:set var="currentPage" value="1" scope="session"></c:set>
		<c:set var="length" value="${fn:length(all) }" />
		<div class="goodlist" id="fakecrop">
			<c:forEach items="${all }" var="good" varStatus="status">
				<c:if test="${status.count eq 1 || (status.count-1) % 4 eq 0}">
					<div class="row">
				</c:if>
					<div class="col-md-3 col-sm-4 col-xs-12 column">
						<a href="GoodDetailServlet?gid=${good.gid }"><img
							alt="${good.gname }" src="${good.gpic }"></a>
						<h4 class="whitetext goodes">${good.gname }</h4>
						<h4 class="whitetext goodes">仅售：￥${good.gprice }</h4>
						<div class="row text-center">
							<form class="nobr"
								action="CartInsertServlet?currentPage=${currentPage }"
								method="post">
								<input type="hidden" value="${good.gid }" name="gid">
								<button type="submit" class="btn btn-sm btn-success"
									value="加入购物车">加入购物车</button>
							</form>
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
					<li><a href="GoodSearchServlet?currentPage=${currentPage-1 }">&laquo;</a></li>
				</c:if>
				<c:forEach var="page" begin="1" end="${totalPages }">
					<li <c:if test="${currentPage eq page }">class="active"</c:if>><a
						href="GoodSearchServlet?currentPage=${page }">${page }</a></li>
				</c:forEach>
				<c:if test="${currentPage<totalPages }">
					<li><a href="GoodSearchServlet?currentPage=${currentPage+1 }">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<com:footer></com:footer>
</body>
</html>