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
<title>商品信息修改</title>
</head>
<body>
	<com:navbar site="goodmanage"></com:navbar>
	<div class="container">
		<div class="row">
			<div class="text-center whitetext">
				<h1 class="loginhead">请在下方表格重新填写信息</h1>
			</div>
		</div>
		<div class="jumbotron" id="jumbotron">
			<c:if test="${!empty fail }">
				<div class="alert alert-danger">${fail }</div>
			</c:if>
			<div class="form">
				<form class="form-horizontal" action="GoodUpdateServlet"
					enctype="multipart/form-data" method="post" name="form">
					<div class="form-group">
						<label for="gname" class="col-sm-2 control-label">商品名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="gname" name="gname"
								placeholder="请输入商品名称" value="${good.gname}" required
								onblur="return validEmail()">
						</div>
					</div>
					<div class="form-group">
						<label for="gprice" class="col-sm-2 control-label">商品价格</label>
						<div class="col-sm-10">
							<input type="number" class="form-control" id="gprice"
								name="gprice" min="0" max="99999" placeholder="请输入商品价格"
								value="${good.gprice}" required>
						</div>
					</div>
					<div class="form-group">
						<label for="gorigin" class="col-sm-2 control-label">商品产地</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="gorigin"
								name="gorigin" placeholder="请输入商品产地" value="${good.gorigin}"
								required>
						</div>
					</div>
					<div class="form-group">
						<label for="gdes" class="col-sm-2 control-label">商品描述</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="6" cols="50"
								placeholder="请输入两百五十字以内的商品描述" name="gdes" id="gdes"
								maxlength="250">${good.gdes }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="gpic2" class="col-sm-2 control-label">商品图片</label>
						<div class="col-sm-10">
							<img id="gpic2" class="img-responsive center-block"
								alt="${good.gname }" src="${good.gpic }"
								class="img-responsive center-block">
						</div>
					</div>
					<div class="form-group">
						<label for="gpic" class="col-sm-2 control-label">上传图片</label>
						<div class="col-sm-10">
							<input type="file" class="form-control" id="gpic" name="gpic"
								accept="image/png,image/jpeg"> <span>只接受不超过2M的jpg或png图片</span>
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
		<br>
	</div>
	<com:footer></com:footer>
</body>
</html>