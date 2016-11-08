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
<jsp:useBean id="user" class="cn.tomleung.entity.User" scope="session" />
<title>用户信息修改</title>
</head>
<body>
	<com:navbar site="modifyinfo"></com:navbar>
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
					<form class="form-horizontal" action="ModifyInfoServlet"
						method="post" name="form">
						<div class="form-group">
							<label for="gender" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<label><input type="radio" name="gender" id="gender"
									value="男"
									<%if (user.getGender().equals("男"))
				out.print("checked");%>>男&nbsp;&nbsp;</label>
								<label><input type="radio" name="gender" id="gender2"
									value="女"
									<%if (user.getGender().equals("女"))
				out.print("checked");%>>女&nbsp;&nbsp;</label>
								<label><input type="radio" name="gender" id="gender3"
									value="保密"
									<%if (user.getGender().equals("保密"))
				out.print("checked");%>>保密&nbsp;&nbsp;</label>
							</div>
						</div>
						<div class="form-group">
							<label for="age" class="col-sm-2 control-label">年龄</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="age" name="age"
									min="0" max="100" placeholder="请输入年龄" value="${user.age}"
									required>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="email" name="email"
									placeholder="请输入邮箱" value="${user.email}" required
									onblur="return validEmail()"><span id="eml"></span>
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