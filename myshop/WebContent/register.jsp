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
<title>用户注册界面</title>
</head>
<body>
	<com:navbar site="register"></com:navbar>
	<div class="container">
		<div class="row">
			<div class="text-center whitetext">
				<h1 class="loginhead">
					<strong>恭喜你！</strong> 离成为本站会员还有一步
				</h1>
				<div class="description loginhead">
					<p>只需要填写完下面表单中的内容并点击提交，即可加入我们温暖的大家庭！</p>
				</div>
			</div>
			<div class="jumbotron" id="jumbotron">
				<c:if test="${!empty fail }">
					<div class="alert alert-danger">${fail }</div>
				</c:if>
				<div class="form">
					<form class="form-horizontal" action="RegisterServlet"
						method="post" name="form">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="username"
									name="username" placeholder="请输入用户名" required
									onblur="return validUsr()"><span id="usr"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="请输入至少八位数的密码" required
									onblur="return validPwd()"><span id="pwd1"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="password2" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password2"
									name="password2" placeholder="确认密码" required
									onblur="return validPwd2()"><span id="pwd2"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="gender" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<label><input type="radio" name="gender" id="gender"
									value="男" checked>男&nbsp;&nbsp;</label> <label><input
									type="radio" name="gender" id="gender2" value="女">女&nbsp;&nbsp;</label>
								<label><input type="radio" name="gender" id="gender3"
									value="保密">保密&nbsp;&nbsp;</label>
							</div>
						</div>
						<div class="form-group">
							<label for="age" class="col-sm-2 control-label">年龄</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="age" name="age"
									min="0" max="100" placeholder="请输入年龄" required>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="email" name="email"
									placeholder="请输入邮箱" required onblur="return validEmail()"><span
									id="eml"></span>
							</div>
						</div>
						<div class="form-group">
							<div class="text-center">
								<button type="submit" class="btn btn-primary" value="提交">提交</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="login.jsp"><button
										type="button" class="btn ">返回登录页面</button></a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<com:footer></com:footer>
</body>
</html>