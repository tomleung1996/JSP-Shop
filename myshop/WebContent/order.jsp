<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="com"%>
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
<title>已买到的宝贝</title>
</head>
<body>
	<com:navbar site="order"></com:navbar>
	<div class="container" id="cart">
		<h1 class="text-center whitetext loginhead">${user.username }的历史订单</h1>
		<c:if test="${!empty orders }">
		<c:set var="sum" value="0" scope="page"></c:set>
			<c:forEach items="${orders }" var="orderhead" varStatus="status">
				<div class="jumbotron">
					<h5>
						<strong>订单号：${orderhead.oid }</strong>
					</h5>
					<h5>
						<strong>时间：${orderhead.otime }</strong>
					</h5>
					<table class="table">
						<thead>
							<tr>
								<th>商品名称</th>
								<th>单价</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderhead.order }" var="suborder"
								varStatus="status">
								<tr>
									<td width="65%"><a href="GoodDetailServlet?gid=${suborder.good.gid }">${suborder.good.gname }</a></td>
									<td width="15%">￥${suborder.good.gprice }</td>
									<td width="15%">${suborder.qty }</td>
									<td width="15%">￥${suborder.subsum }</td>
								</tr>
								<c:set var="sum" value="${sum+suborder.subsum }" scope="page"></c:set>
							</c:forEach>
							<tr>
								<td colspan="5" class="text-right">总计：
									<h3 class="nobr">￥${sum }</h3>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${empty orders }">
			<div class="jumbotron">
				<h4 class="text-center">————您的购买记录是空的————</h4>
			</div>
		</c:if>
	</div>
	<com:footer></com:footer>
</body>
</html>