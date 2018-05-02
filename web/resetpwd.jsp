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
			action='${pageContext.servletContext.contextPath}/resetpwd'
			method="post">
			<h3>修改密码</h3>
			<label class="login-title">输入新密码</label>
			<div class="layui-form-item">
				<label class="layui-form-label login-form"><i
					class="iconfont">&#xe6b8;</i></label>
				<div class="layui-input-inline login-inline">
					<input type="password" name="newpwd" lay-verify="required"
						placeholder="请输入新密码" required="required" autocomplete="off"
						class="layui-input" id="newpwd">
				</div>
			</div>
			<label class="login-title">再输入一次</label>
			<div class="layui-form-item">
				<label class="layui-form-label login-form"><i
					class="iconfont">&#xe82b;</i></label>
				<div class="layui-input-inline login-inline">
					<input type="password" name="newpwd1" id="newpwd1"
						lay-verify="required" placeholder="请再输入一次" autocomplete="off"
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
