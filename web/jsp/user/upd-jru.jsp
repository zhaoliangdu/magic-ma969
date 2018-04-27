<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../resource/resource.jsp"></jsp:include>
<center>
	<span style="color: red">${updateauthm }</span>
</center>
<form action="${pageContext.servletContext.contextPath }/user/updateauth" method="post">
	<div class="layui-form-item">
		<label class="layui-form-label">用户编号：</label>
		<div class="layui-input-inline">
			<input type="text" readonly="readonly" name="userId" id="userId"
				class="layui-input" value="${uid }" />
		</div>
	</div>
	<input type="hidden" value="${auth }" name="auth" />
	<div class="layui-form-item">
		<label class="layui-form-label">权限：</label>
		<div class="layui-input-inline">
			<select name="uauth" id="uauth" class="layui-input">
				<option value="0">普通用户</option>
				<option value="1">管理员</option>
			</select>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">用户名：</label>
		<div class="layui-input-inline">
			<input type="text" readonly="readonly" id="userName"
				class="layui-input" value="${ username}" />
		</div>
	</div>
	<center>
		<button type="submit" class="layui-btn">提交更改</button>
	</center>
</form>

