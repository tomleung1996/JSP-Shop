<%@page import="org.apache.catalina.connector.Response"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>商城主页</title>
</head>
<body>

	<com:navbar site="profile"></com:navbar>
	<div class="container">
		<div class="row">
			<div class="text-center whitetext">
				<h1 class="loginhead">
					<img alt="myspace" src="image/thisNightWhite.png" class="img-responsive center-block">
				</h1>
			</div>
		</div>
		<div class="jumbotron">
			<div class="alert alert-success">
				欢迎您，亲爱的：${user.username}&nbsp;会员</div>
			<table class="table table-striped table-bordered">
				<tr>
					<td><h4>权限：</h4></td>
					<td><h4>${user.privilege}</h4></td>
				<tr>
					<td><h4>性别：</h4></td>
					<td><h4>${user.gender}</h4></td>
				<tr>
					<td><h4>年龄：</h4></td>
					<td><h4>${user.age}</h4></td>
				<tr>
					<td><h4>邮箱：</h4></td>
					<td><h4>${user.email}</h4></td>
			</table>
			<div class="text-center">
				<a href="ShowAllServlet"><button type="button"
						class="btn btn-primary">返回</button></a>
			</div>
		</div>
	</div>
	<com:footer></com:footer>
</body>
</html>