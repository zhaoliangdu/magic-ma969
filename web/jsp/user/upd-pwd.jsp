<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../resource/resource.jsp"></jsp:include>

<center>
	<form action="${pageContext.servletContext.contextPath }/user/updatepwd" method="post" id="upwdfrom" class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">用户编号：</label>
			<div class="layui-input-inline">
				<input type="type" readonly="readonly" id='upwid' name="uid"
					required class="layui-input" value="${uid }" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">旧的密码：</label>
			<div class="layui-input-inline">
				<input type="password" placeholder="旧的密码" name="oldpwd" required
					class="layui-input" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新的密码：</label>
			<div class="layui-input-inline">
				<input type="password" placeholder="新的密码" name="newpwd" id="newpwd"
					required class="layui-input" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">再次输入：</label>
			<div class="layui-input-inline">
				<input type="password" placeholder="再次输入" name="newpwd1"
					id="newpwd1" required class="layui-input" />
			</div>
		</div> 
		<input type="submit" class="layui-btn" value="确定修改" />  
	</form>
</center>
