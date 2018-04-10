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
				<a name="default">权限管理</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->
		<table class="layui-table">
			<thead>
				<tr>

					<th style="text-align: center">用户编号</th>
					<th style="text-align: center">权&nbsp;&nbsp;限</th>
					<th style="text-align: center">用&nbsp;户&nbsp;名</th>
					<th style="text-align: center">权限描述</th>
					<th style="text-align: center">操&nbsp;&nbsp;作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
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
						<c:choose>
							<c:when test="${user.auth==0}">
								<td style="color: #6699ff">查看用户，查看台站，新增台站，查看数据</td>
							</c:when>
							<c:when test="${user.auth==1}">
								<td style="color: green">新增用户，查看用户，查看台站，新增台站，修改台站，数据上传，数据查看</td>
							</c:when>
							<c:otherwise>
								<td style="color: red">最高权限，可进行一切操作</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${auth==2 }">
								<c:if test="${user.auth==2}">
									<td>无法操作</td>
								</c:if>
								<c:if test="${user.auth<2 }">
									<td><a href="#" idu="upd"
										onclick="updataAuth('修改权限','updjru?uid=${user.userId }&username=${user.userName }','500','300')"
										class="layui-btn layui-btn-xs">修改</a></td>
								</c:if>
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
</div>
<jsp:include page="../bottom.jsp"></jsp:include>
<script>
	//更新权限
	/*-修改*/
	function updataAuth(title, url, w, h) {
		x_admin_show(title, url, w, h);
	}
</script>
