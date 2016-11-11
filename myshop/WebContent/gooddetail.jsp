<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>商品详情</title>
</head>
<body>
	<com:navbar site="goodinsert"></com:navbar>
	<div class="container">
		<div class="row">
			<div class="jumbotron text-center">
				<img alt="${good.gname }" src="${good.gpic }" id="goodpic"><br>
				<form class="nobr"
					action="CartInsertServlet?currentPage=${currentPage }"
					method="post">
					<input type="hidden" value="${good.gid }" name="gid">
					<button type="submit" class="btn btn-sm btn-success" value="加入购物车">加入购物车</button>
				</form>
				<c:if test="${user.privilege eq \"超级管理员\" }">
				&nbsp;&nbsp;&nbsp;
					<form class="nobr" action="GoodSearchByIDServlet" method="post">
						<input type="hidden" value="${good.gid }" name="gid">
						<button type="submit" class="btn btn-sm btn-primary" value="修改">修改</button>
					</form>
						&nbsp;&nbsp;&nbsp;
						<form class="nobr" action="GoodDeleteServlet" method="post">
						<input type="hidden" value="${good.gid }" name="gid">
						<button type="submit" class="btn btn-sm btn-danger" value="删除"
							onclick="javascript:return deleteConfirm()">删除</button>
					</form>
				</c:if>
				<br><br>
				<table class="table table-striped table-bordered text-left">
					<tr>
						<td class="col-sm-3"><h4>商品名称：</h4></td>
						<td class="col-sm-9"><h4>${good.gname}</h4></td>
					<tr>
						<td><h4>商品价格：</h4></td>
						<td><h4>${good.gprice}</h4></td>
					<tr>
						<td><h4>商品产地：</h4></td>
						<td><h4>${good.gorigin}</h4></td>
					<tr>
						<td><h4>商品描述：</h4></td>
						<td><h4>${good.gdes}</h4></td>
				</table>
				<div class="text-center">
					<a href="ShowAllServlet"><button type="button"
							class="btn btn-primary">返回首页</button></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>