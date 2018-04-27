<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../top.jsp"></jsp:include>
<jsp:include page="../main.jsp"></jsp:include>
<!-- 右侧主体开始 -->
<div class="page-content">
	<div class="content">
		<fieldset class="layui-elem-field layui-field-title site-title">
			<legend>
				<a name="default">用户管理</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->
		<xblock> <a class="layui-btn layui-btn-normal"
			onclick="addUser('添加用户','${pageContext.servletContext.contextPath }/user/adduserview','500','720')">添加用户</a> <c:if
			test="${loginuser.auth==2}">
			<button class="layui-btn layui-btn-warm" onclick="userRecovery()">黑名单</button>
		</c:if> </xblock>
		<table class="layui-table">
			<thead>
				<tr> 
					<th>用户ID</th>
					<th>权限名称</th>
					<th>用户账号</th>
					<th>用户名称</th>
					<th>联系地址</th>
					<th>出生日期</th>
					<th>联系电话</th>
					<th>所在单位</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userlist}">
					<tr>

						<td>${user.userId }</td>
						<c:choose>
							<c:when test="${user.auth==0}">
								<td>普通员工</td>
							</c:when>
							<c:when test="${user.auth==1}">
								<td>管理员</td>
							</c:when>
							<c:otherwise>
								<td>超级管理员</td>
							</c:otherwise>
						</c:choose>
						<td>${user.userName }</td>
						<td>${user.name }</td>
						<td>${user.userAddress }</td>
						<td>${user.userBirthday }</td>
						<td>${user.userPhone }</td>
						<td>${user.userCompany }</td>
						<c:choose>
							<c:when test="${username==user.userName}">
								<td><a href="#" idu="upd" class="layui-btn layui-btn-xs"
									onclick="updateUser('修改用户信息','${pageContext.servletContext.contextPath }/user/findUserById?uid=${user.userId }','500','700')">修改</a>
									<a href="#" class="layui-btn layui-btn-xs"
									onclick="updatePwd('修改用户密码','${pageContext.servletContext.contextPath }/user/updpwd?uid=${user.userId }','500','350')">修改密码</a>
								</td>
							</c:when>
							<c:when test="${auth==2}">
								<td style="color: blue;"><a href="#" idu="upd"
									class="layui-btn layui-btn-xs"
									onclick="updateUser('修改用户信息','${pageContext.servletContext.contextPath }/user/findUserById?uid=${user.userId }','500','700')">修改</a>&nbsp;<a
									href="#" class="layui-btn layui-btn-xs"
									onclick="updatePwd('修改用户密码','${pageContext.servletContext.contextPath }/user/updpwd?uid=${user.userId }','500','350')">修改密码</a>&nbsp;<a
									href="${pageContext.servletContext.contextPath}/user/deleteUser?id=${user.userId}"
									onclick="confirmdel()" class="layui-btn layui-btn-danger">删除</a></td>
							</c:when>
							<c:otherwise>
								<td>无操作权限</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<jsp:include page="../bottom.jsp"></jsp:include>

<script>
	function getuid() {
		var uid = $("#uuid").val();
		$("#upwid").val(uid);
	}

	//查找用户
	function finduser(uid) {
		$.ajax({
			url : "${pageContext.servletContext.contextPath }/user/findById",
			type : "post",
			data : {
				"uid" : uid
			},
			success : function(user) {
				if (user != 0) {
					$("#uuid").val(user.userId);
					$("#usename").val(user.userName);
					$("#uname").val(user.name);
					$("#ubirthday").val(user.userBirthday);
					$("#uaddress").val(user.userAddress);
					$("#uphone").val(user.userPhone);
					$("#ucompany").val(user.userCompany);
				} else {
					alert("用户未找到");
				}
			}
		});
	}
	function confirmdel() {
		if (window.confirm("确认删除吗！")) {
			return true;
		} else {
			$("a").attr("href", "");
			return false;
		}
	}
	//验证提交
	function submitTest() {
		var uuid = $("#uuid").val();
		var uid = $("#uid").val();
		var uauth = $("#userauth").val();
		if (uuid === null || uuid === '') {
			layer.msg("请选择要修改的用户！");
			return false;
		}
		if (uauth < 2) {
			if (uid != uuid) {
				layer.msg("没有操作权限！");
				return false;
			}
		}
	}
	//恢复已删除的用户
	function userRecovery() {
		layer
				.open({
					type : 1,
					content : "<center><table id='blacklist' class='layui-table'></table></center>", //这里content是一个普通的String
					area : [ '500px', '300px' ]
				});
		$("#blacklist").empty();
		$
				.ajax({
					url : "${pageContext.servletContext.contextPath }/user/blacklist",
					type : "get",
					success : function(blist) {
						for (var i = 0; i < blist.length; i++) {
							$("#blacklist")
									.append(
											"<tr><td>"
													+ (i + 1)
													+ "</td><input type='hidden' value="+blist[i].userId+"/><td>"
													+ blist[i].userName
													+ "</td> <td><a href='userrecovery?id="
													+ blist[i].userId
													+ "' class='layui-btn'>恢复</a></td></tr>");
						}
					}
				});
	}

	/*-添加*/
	function addUser(title, url, w, h) {
		x_admin_show(title, url, w, h);
	}
	/*-修改*/
	function updateUser(title, url, w, h) {
		x_admin_show(title, url, w, h);
	}
	function updatePwd(title, url, w, h) {
		x_admin_show(title, url, w, h);
	}
</script>