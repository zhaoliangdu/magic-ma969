<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../resource/resource.jsp"></jsp:include>
<center> 
	<span style="color: red; font-size: 1.5em;">${updumessage }</span>
	<form class="layui-form" method="post" id="registform"
		action="${pageContext.servletContext.contextPath }/updateUser">
		<input type="hidden" name="userauth" value="${user.auth }" />
		<div class="layui-form-item">
			<label class="layui-form-label">用户编号：</label>
			<div class="layui-input-inline">
				<input class="layui-input required" required placeholder="请输入名称"
					type="text" name="id" id="id" readonly="readonly"
					value="${user.userId }" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户权限： </label>
			<div class="layui-input-inline">
				<select class="layui-input" style="width: 200px;" id="auth"
					name="auth">
					<option value="0">普通用户</option>
					<option value="1">管理员</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户账号：</label>
			<div class="layui-input-inline">
				<input class="layui-input required" required placeholder="请输入名称"
					type="text" name="username" id="username" value="${user.userName }" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户姓名：</label>
			<div class="layui-input-inline">
				<input class="layui-input required" placeholder="请输入姓名" type="text"
					name="uname" required id="uname" value="${user.name }" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">出生日期：</label>
			<div class="layui-input-inline">
				<input type="date" class="layui-input required" name="userBirthday"
					required id="date_s" max="2000-03-29"
					value="${user.userBirthday }" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">家庭住址：</label>
			<div class="layui-input-inline">
				<input class="layui-input required" required placeholder="请输入地址"
					type="text" name="userAddress" id="userAddress"
					value="${user.userAddress }" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">联系电话：</label>
			<div class="layui-input-inline">
				<input class="layui-input required" required placeholder="请输入电话"
					type="tel" name="userPhone" id="userPhone" pattern="1[34578]\d{9}"
					value="${user.userPhone }" />
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_email" class="layui-form-label">所属单位：</label>
			<div class="layui-input-inline">
				<input class="layui-input required" placeholder="请输入单位" type="text"
					name="userCompany" id="userCompany" required
					value="${user.userCompany }" />
			</div>
		</div>
		<input type="submit" class="layui-btn" id="send" value="修改" /> <input
			type="reset" id="res" class="layui-btn layui-btn-primary" value="重置" />
	</form>
</center>
<script>
	$(function() {
		$("form :input.required")
				.each(
						function() {
							var $required = $("<strong class='high' style='color:#DE1010'>*</strong>"); //创建元素
							$(this).parent().append($required); //然后将它追加到文档中
						});
		//文本框失去焦点后
		$('form :input')
				.blur(
						function() {
							var $parent = $(this).parent();
							$parent.find(".formtips").remove();

							var myReg = /^[a-zA-Z0-9_]{0,}$/;
							var phoneReg = /^1[34578]\d{9}$/;
							var phone = $('#userPhone').val();

							if (!myReg.test($("#username").val())) {
								var errorMsg = '请输入a-z A-z 1-9等字符.';
								$parent
										.append("<span class='formtips onError' style='color:red;font-size:12px'>"
												+ errorMsg + "</span>");
							} else {
								if ($(this).is('#username')) {
									if (this.value == ""
											|| this.value.length < 6) {
										var errorMsg = '请输入至少6位的用户名.';

										$parent
												.append("<span class='formtips onError' style='color:red;font-size:12px'>"
														+ errorMsg + "</span>");
									} else {
										var okMsg = '输入正确.';
										$parent
												.append("<span class='formtips onSuccess' style='color:green;font-size:12px'>"
														+ okMsg + '</span>');
									}
								}
							}
							//验证密码
							if ($(this).is('#password')) {
								if (this.value == "" || this.value.length < 6) {
									var errorMsg = '请输入至少6位的密码.';
									$parent
											.append("<span class='formtips onError' style='color:red;font-size:12px'>"
													+ errorMsg + "</span>");
								} else {
									var okMsg = '输入正确.';
									$parent
											.append("<span class='formtips onSuccess' style='color:green;font-size:12px'>"
													+ okMsg + '</span>');
								}
							}
							if ($(this).is('#password1')) {
								if (this.value == ""
										|| this.value != $("#password").val()) {
									var errorMsg = '两次密码输入不一致.';
									$parent
											.append("<span class='formtips onError' style='color:red;font-size:12px'>"
													+ errorMsg + "</span>");
								} else {
									var okMsg = '输入正确.';
									$parent
											.append("<span class='formtips onSuccess' style='color:green;font-size:12px'>"
													+ okMsg + '</span>');
								}
							}
							//验证电话
							if ($(this).is('#userPhone')) {
								if (this.value == "" || (!phoneReg.test(phone))) {
									var errorMsg = '请输入正确的电话.';
									$parent
											.append("<span class='formtips onError' style='color:red;font-size:12px'>"
													+ errorMsg + "</span>");
								} else {
									var okMsg = '输入正确.';
									$parent
											.append("<span class='formtips onSuccess' style='color:green;font-size:12px'>"
													+ okMsg + '</span>');
								}
							}
							if ($(this).is('#uname')) {
								if (this.value == "") {
									var errorMsg = '请输入姓名.';
									$parent
											.append("<span class='formtips onError' style='color:red;font-size:12px'>"
													+ errorMsg + "</span>");
								} else {
									var okMsg = '输入正确.';
									$parent
											.append("<span class='formtips onSuccess' style='color:green;font-size:12px'>"
													+ okMsg + '</span>');
								}
							}
							if ($(this).is('#date_s')) {
								if (this.value == "") {
									var errorMsg = '请输入正确出生日期.';
									$parent
											.append("<span class='formtips onError' style='color:red;font-size:12px'>"
													+ errorMsg + "</span>");
								} else {
									var okMsg = '输入正确.';
									$parent
											.append("<span class='formtips onSuccess' style='color:green;font-size:12px'>"
													+ okMsg + '</span>');
								}
							}
							if ($(this).is('#userAddress')) {
								if (this.value == "") {
									var errorMsg = '请输入地址.';
									$parent
											.append("<span class='formtips onError' style='color:red;font-size:12px'>"
													+ errorMsg + "</span>");
								} else {
									var okMsg = '输入正确.';
									$parent
											.append("<span class='formtips onSuccess' style='color:green;font-size:12px'>"
													+ okMsg + '</span>');
								}
							}
							if ($(this).is('#userCompany')) {
								if (this.value == "") {
									var errorMsg = '请输入单位名称.';
									$parent
											.append("<span class='formtips onError' style='color:red;font-size:12px'>"
													+ errorMsg + "</span>");
								} else {
									var okMsg = '输入正确.';
									$parent
											.append("<span class='formtips onSuccess' style='color:green;font-size:12px'>"
													+ okMsg + '</span>');
								}
							}
						}).keyup(function() {
					$(this).triggerHandler("blur");
				}).focus(function() {
					$(this).triggerHandler("blur");
				});
		//提交，最终验证。
		$('#send').click(function() {
			$("form :input.required").trigger('blur');
			var numError = $('form .onError').length;
			if (numError) {
				return false;
			}
		});
		//重置
		$('#res').click(function() {
			$(".formtips").remove();
		});
	});
</script>