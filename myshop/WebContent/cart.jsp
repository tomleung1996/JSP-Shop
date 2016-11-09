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
	<div class="container" id="cart">
		<div class="jumbotron">
			<h3 class="text-center">${user.username }的购物车</h3>
			<c:if test="${!empty carts }">
				<c:set var="sum" value="0" scope="page"></c:set>
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
						<c:forEach items="${carts }" var="cart" varStatus="status">
							<tr>
								<td width="50%">${cart.gname }</td>
								<td width="10%">￥${cart.gprice }</td>
								<td width="15%">${cart.qty }</td>
								<td width="10%">￥${cart.subsum }</td>
								<td width="15%">
									<form class="nobr" action="CartAddServlet" method="post">
										<input type="hidden" name="gid" value="${cart.gid }">
										<button type="submit" class="btn btn-sm btn-success"
											value="增加"><strong>+</strong></button>
									</form>
									<form class="nobr" action="CartDeleteServlet" method="post">
										<input type="hidden" name="gid" value="${cart.gid }">
										<button type="submit" class="btn btn-sm btn-danger" value="减少"><strong>-</strong></button>
									</form>
								</td>
								<c:set var="sum" value="${sum+cart.subsum }" scope="page"></c:set>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" class="text-right">总计：
								<h3 class="nobr">￥${sum }</h3>
							</td>
						</tr>
						<tr>
							<td colspan="5" class="text-right"><form class="nobr"
									action="CartTruncateServlet" method="post">
									<button type="submit" class="btn btn-sm btn-danger"
										value="清空购物车" onclick="javascript:return deleteConfirm()">清空购物车</button>
								</form></td>
						</tr>
					</tbody>
				</table>
			</c:if>
			<c:if test="${empty carts }">
				<br>
				<h4 class="text-center">————您的购物车空空如也————</h4>
			</c:if>
		</div>
	</div>
</body>
</html>