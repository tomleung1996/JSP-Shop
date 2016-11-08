<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.tomleung.entity.User"%>
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
<title>用户密码修改</title>
</head>
<body>
	<com:navbar site="modifypwd"></com:navbar>
	<div class="container">
		<div class="row">
			<div class="text-center whitetext">
				<h1 class="loginhead">请在下方表格重新填写信息</h1>
			</div>
			<div class="jumbotron" id="jumbotron">
				<c:if test="${!empty fail }">
					<div class="alert alert-danger">${fail }</div>
				</c:if>
				<div class="form">
					<form class="form-horizontal" action="ModifyPwdServlet"
						method="post" name="form">
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">原密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="请输入原密码" required><span
									id="pwd1"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="password2" class="col-sm-2 control-label">新密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password2"
									name="password2" placeholder="请输入新密码" required
									onblur="return validPwd3()"><span id="pwd2"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="password3" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password3"
									name="password3" placeholder="请再次输入新密码" required
									onblur="return validPwd4()"><span id="pwd3"></span>
							</div>
						</div>
						<div class="form-group">
							<div class="text-center">
								<button type="submit" class="btn btn-primary" value="提交">提交</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="ShowAllServlet"><button
										type="button" class="btn ">返回</button></a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>