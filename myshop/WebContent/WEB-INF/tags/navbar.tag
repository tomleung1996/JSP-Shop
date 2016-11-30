<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="site" required="true" %>
<%@ tag import="cn.tomleung.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#example-navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="ShowAllServlet">MySpace</a>
		</div>
		<div class="collapse navbar-collapse" id="example-navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="<%if(site.equals("main")){out.print("active");}%>"><a href="ShowAllServlet">首页</a></li>
				<%if ((session.getAttribute("user") instanceof User) && ((User) session.getAttribute("user")).getUsername() != null) {%>
				<li class="<%if(site.equals("cart")){out.print("active");}%>"><a href="CartShowServlet">购物车</a></li>
				<li class="<%if(site.equals("order")){out.print("active");}%>"><a href="OrderShowServlet">已买到的宝贝</a></li>
				<li class="dropdown <%if(site.equals("profile")||site.equals("modifyinfo")||site.equals("modifypwd")){out.print("active");}%>">
				<a href="profile.jsp"
					class="dropdown-toggle" data-toggle="dropdown">${user.privilege }：${user.username }<b
						class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li class="<%if(site.equals("profile")){out.print("active");}%>"><a href="profile.jsp">查看个人信息</a></li>
						<li class="<%if(site.equals("modifyinfo")){out.print("active");}%>"><a href="modifyinfo.jsp">修改个性信息</a></li>
						<li class="<%if(site.equals("modifypwd")){out.print("active");}%>"><a href="modifypwd.jsp">修改密码</a></li>
						<c:if test="${user.privilege eq \"超级管理员\" }">
						<li class="divider"></li>
						<li class="<%if(site.equals("goodinsert")){out.print("active");}%>"><a href="goodinsert.jsp">添加商品</a></li>
						<li class="<%if(site.equals("goodmanage")){out.print("active");}%>"><a href="ShowAllServlet?flag=1">商品管理</a></li>
						</c:if>
						<li class="divider"></li>
						<li><a href="LogoutServlet">退出登录</a></li>
					</ul></li>
				<li>
					<form class="form-search" id="searchbar" action="GoodSearchServlet" method="post">
  						<input type="text" class="input-medium search-query" placeholder="请输入商品名字" name="gname">
  						<button type="submit" class="btn">搜索</button>
					</form>
				</li>
				<%} %>
			</ul>
		</div>
	</div>
</nav>
