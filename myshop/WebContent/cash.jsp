<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>收银台</title>
</head>
<body>
	<com:navbar site="cash"></com:navbar>
	<div class="container">
	<h1 class="text-center whitetext loginhead">收银台</h1>
		<div class="jumbotron text-center">
			<h1 class="nobr">
				<strong>爸爸</strong>
			</h1>
			<h3 class="nobr">
				已经替你把钱付了，<br>直接去看订单记录吧
			</h3>
			<h3>什么？你说地址还没写？以后再说嘛</h3>
			<a href="OrderShowServlet">
				<button type="submit" class="btn btn-lg btn-success" value="清空购物车">查看我的订单</button>
			</a>
		</div>
	</div>
	<com:footer></com:footer>
</body>
</html>