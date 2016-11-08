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
<title>购物车</title>
</head>
<body>
	<com:navbar site="cart"></com:navbar>
	<div class="container">
		<div class="jumbotron">
		<h4 class="text-center">${user.username }的购物车</h4>
			<table class="table">
				<thead>
					<tr>
						<th>商品名称</th>
						<th>商品单价</th>
						<th>购买数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${all }" var="cart" varStatus="status">
						<tr>
							<td>${cart.gname }</td>
							<td>￥${cart.gprice }</td>
							<td>${cart.qty }</td>
							<td>￥${cart.subsum }</td>
							<td>未开放</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>