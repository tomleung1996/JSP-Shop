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
<title>商城登录</title>
</head>
<body>
	<com:navbar site="login"></com:navbar>
	<div class="row">
		<div class="text-center whitetext">
			<h1 class="loginhead">
				<img alt="myspace" src="image/thisNightWhite.png" class="img-responsive center-block"><br>登录
			</h1>
		</div>
	</div>
	<div class="container" id="login">

		<div class="jumbotron" id="jumbotron">
			<c:if test="${!empty param.success }">
				<div class="alert alert-success">注册成功！欢迎登陆</div>
			</c:if>
			<c:if test="${!empty fail }">
				<div class="alert alert-danger">${fail }</div>
			</c:if>
			<div class="form">
				<form class="form-horizontal" action="LoginServlet" method="post"
					name="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="请输入用户名" required>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="请输入密码" required>
						</div>
					</div>
					<div class="form-group">
						<div class="text-center">
							<button type="submit" class="btn btn-primary" value="登录">登录</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="register.jsp"><button type="button" class="btn"
									value="注册">注册</button></a> 

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<com:footer></com:footer>
</body>
</html>