<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=yes">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>宝盈科技车载移动数据云端在线管理系统和数据分析管理软件【MA969YDYZM-S】V1.0</title>
<jsp:include page="../resource/resource.jsp"></jsp:include>
<script>
	//显示当前时间
	function startTime() {
		var date = new Date().toLocaleString();
		document.getElementById("time").innerHTML = date;
	}
	setInterval("startTime()", 500);
</script>
<style>
.testmode {
	width: 90%;
	margin-left: 13%;
	height: 100%;
	background-color: #000;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 2;
	opacity: 0.7;
	/*兼容IE8及以下版本浏览器*/
	filter: alpha(opacity = 30);
	display: none;
	text-align: center;
	height: 100%;
	height: 100%
}

#loadingTip {
	position: absolute;
	z-index: 9999;
	top: 0;
	left: 45%;
	padding: 3px 10px;
	background: #6699ff;
	color: #fff;
	font-size: 14px;
}

#lnglat {
	position: absolute;
	z-index: 9999;
	top: 0;
	left: 0;
	padding: 3px 10px;
	background: #6699ff;
	color: #fff;
	font-size: 14px;
}
</style>
<!-- bgcolor="#004080" -->
</head>
<body ondragstart="window.event.returnValue=false"
	oncontextmenu="window.event.returnValue=false">
	<!-- 顶部开始 -->
	<div class="container">
		<div class="logo">
			<a href="index">宝盈科技车载移动数据云端在线管理系统和数据分析管理软件【MA969YDYZM-S】V1.0</a><img
				src="resource/images/logo.gif" title="北京宝盈科技" width="120" />
		</div>
		<div class="open-nav">
			<i class="iconfont">&#xe699;</i>
		</div>
		<ul class="layui-nav right">
			<li class="layui-nav-item"><a href="javascript:;">${username }</a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a href="#"
							onclick="updateUser('修改用户信息','findUserById?uid=${uid }','500','700')">个人信息</a>
					</dd>
					<dd>
						<a href="getsystemset?typeId=0">设置</a>
					</dd>
					<dd>
						<a href="userlogout">退出</a>
					</dd>
				</dl></li>
		</ul>
		<input type="hidden" value='${username }' id="username" /> <input
			type="hidden" value='${uid }' id="uid" /><input type="hidden"
			value='${auth }' id="userauth" />
		<div class="layui-nav right">
			<div id="tp-weather-widget" style="margin-top: 15px;">
				<script>
					(function(T, h, i, n, k, P, a, g, e) {
						g = function() {
							P = h.createElement(i);
							a = h.getElementsByTagName(i)[0];
							P.src = k;
							P.charset = "utf-8";
							P.async = 1;
							a.parentNode.insertBefore(P, a)
						};
						T["ThinkPageWeatherWidgetObject"] = n;
						T[n] || (T[n] = function() {
							(T[n].q = T[n].q || []).push(arguments)
						});
						T[n].l = +new Date();
						if (T.attachEvent) {
							T.attachEvent("onload", g)
						} else {
							T.addEventListener("load", g, false)
						}
					}(window, document, "script", "tpwidget",
							"//widget.seniverse.com/widget/chameleon.js"))
				</script>
				<script>
					tpwidget("init", {
						"flavor" : "slim",
						"location" : "WX4FBXXFKE4F",
						"geolocation" : "disabled",
						"language" : "zh-chs",
						"unit" : "c",
						"theme" : "chameleon",
						"container" : "tp-weather-widget",
						"bubble" : "disabled",
						"alarmType" : "badge",
						"color" : "#FFFFFF",
						"uid" : "U9EC08A15F",
						"hash" : "14dff75e7253d3a8b9727522759f3455"
					});
					tpwidget("show");
				</script>
			</div>
			<span id="time" style="color: #ffffff;"></span>
		</div>
	</div>
	<script>
		/*-修改*/
		function updateUser(title, url, w, h) {
			x_admin_show(title, url, w, h);
		}
	</script>