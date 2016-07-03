<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/common/include/base.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>总控后台</title>
<script type="text/javascript" src="${base}/common/js/md5.js"></script>
</head>
<body>
	<jsp:include page="/agent/include/bootstrap.jsp"></jsp:include>
	<div class="container">
		<div class="form-group">
			<h2 class="form-signin-heading">管理员登录</h2>
			<label for="inputEmail" class="sr-only">用户名</label> <input type="email" id="username" class="form-control" placeholder="用户名" required autofocus> <label for="inputPassword" class="sr-only">密
				码</label> <input type="password" id="password" class="form-control" placeholder="密码" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me"> 记住密码
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" onclick="doLogin();">登录</button>
		</div>
	</div>
	<script language="javascript">
		function doLogin() {
			var account = $("#username").val().trim();
			var pwd = $("#password").val();
			if (account == '') {
				Msg.info("请输入账号");
				$("#username").focus();
				return;
			}
			if (pwd == '') {
				Msg.info("请输入密码");
				$("#password").focus();
				return;
			}
			pwd = hex_md5("~!@#$wc_" + pwd);
			$.ajax({
				url : "${base}/admin/login.do",
				data : {
					account : account,
					password : pwd
				},
				success : function(result) {
					window.location.href = "${base}";
				}
			});
		}
	</script>
</body>
</html>