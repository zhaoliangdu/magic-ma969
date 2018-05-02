<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>宝盈科技车载移动数据云端在线管理系统和数据分析管理软件【MA969YDYZM-S】V1.0</title>
<meta name="description"
	content="Restyling jQuery UI Widgets and Elements" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" href="resource/css/font.css">
<link rel="stylesheet" href="resource/css/xadmin.css">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
<script src="resource/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="resource/js/xadmin.js"></script>
<jsp:include page="resource/resource.jsp"></jsp:include>
</head>
<body>
	<div class="login-logo">
		<h1>
			宝盈科技车载移动数据云端在线管理系统<br>和数据分析管理软件【MA969YDYZM-S】V1.0
		</h1>
	</div>
	<br>
	<div class="login-box">
		<form class="layui-form layui-form-pane"
			action='${pageContext.servletContext.contextPath}/checkcode'
			method="post">
			<span style="color: red;">${ msg}</span>
			<h3>验证邮箱</h3>
			<label class="login-title" for="username">帐号</label>
			<div class="layui-form-item">
				<label class="layui-form-label login-form"><i
					class="iconfont">&#xe6b8;</i></label>
				<div class="layui-input-inline login-inline">
					<input type="text" name="username" lay-verify="required"
						placeholder="请输入你的帐号" required="required" autocomplete="off"
						class="layui-input" id="username">
				</div>
			</div>
			<label class="login-title" for="email">邮箱</label>
			<div class="layui-form-item">
				<label class="layui-form-label login-form"><i
					class="iconfont">&#xe82b;</i></label>
				<div class="layui-input-inline login-inline">
					<input type="email" name="email" id="email" lay-verify="required"
						placeholder="请输入你的邮箱" autocomplete="off" class="layui-input">
					<input type="button" id="btn" class="layui-btn" value="免费获取验证码"
						required="required" onclick="sendemail()"
						pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" />

				</div>
			</div>
			<label class="login-title">验证码</label>
			<div class="layui-form-item">
				<label class="layui-form-label login-form"><i
					class="iconfont">&#xe82b;</i></label>
				<div class="layui-input-inline login-inline">
					<input type="text" name="code" lay-verify="required"
						placeholder="请输入你的验证码" autocomplete="off" required="required"
						class="layui-input">
				</div>
			</div>
			<div class="form-actions">
				<button class="pull-right layui-btn" type="submit">提交</button>
			</div>
		</form>
	</div>
</body>
</html>
<script>
	var countdown = 60;

	function sendemail() {
		var username = $("#username").val();
		var email = $("#email").val();

		if (username != "" && email != "") {
			var obj = $("#btn");
			settime(obj);
			$.ajax({
				url : "${pageContext.servletContext.contextPath}/sendemail",
				type : "post",
				data : {
					"username" : username,
					"email" : email
				},
				success : function(code) {
					if ("账号错误" == code) {
						alert(code);
					}
				}
			});
		} else {
			alert("请输入完整信息！");
			return false;
		}
	}

	function settime(obj) { //发送验证码倒计时
		if (countdown == 0) {
			obj.attr('disabled', false);
			//obj.removeattr("disabled"); 
			obj.val("免费获取验证码");
			countdown = 60;
			return;
		} else {
			obj.attr('disabled', true);
			obj.val("重新发送(" + countdown + ")");
			countdown--;
		}
		setTimeout(function() {
			settime(obj);
		}, 1000);
	}
</script>