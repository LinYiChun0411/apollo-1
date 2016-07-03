<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/common/include/base.jsp"></jsp:include>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${base}/common/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="${base}/common/jquery/jquery-1.12.3.min.js"></script>
<!-- 可选的Bootstrap -->
<script src="${base}/common/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
</head>
<body>
	<form action="${base}/login.do">
	<div class="container">
		<div class="form-group">
			<h2 class="form-signin-heading">会员登录</h2>
			<label for="inputEmail" class="sr-only">用户名</label> <input type="text" name="username" class="form-control" placeholder="用户名" required autofocus>
			<label for="inputPassword" class="sr-only">密   码</label> <input type="password" name="password" class="form-control" placeholder="密码" required>
			<button class="btn btn-lg btn-primary btn-block">登录</button>
		</div>
	</div>
	</form>
</body>
</html>