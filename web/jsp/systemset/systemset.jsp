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
				<a name="default">系统设置</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->
		<center>
			<form role="form" action="${pageContext.servletContext.contextPath }/systemset/updatesystemset" method="post"
				id="syssetform">
				<div class="panel-title" style="font-size: 1.2em; color: red">${sysmsg }</div>
				<input type="hidden" value="${uid}" name="uid" /><strong></strong>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label"> 类型：</label>
						<div class="layui-input-inline">
							<select class="layui-input" id="chosev" onchange="changeset()">
								<option value="0">场强</option>
								<option value="1">信噪比</option>
								<option value="2">误包率</option>
							</select><input type="hidden" value="${typeId }" id="tid" name="typeId" />
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">范围：</label>
						<div class="layui-input-inline">
							<input type="number" id="min" name="min" class="layui-input"
								value="${min }" />
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline">
							<input type="number" id="max" name="max" class="layui-input"
								value="${max }" />
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">分类数量：</label>
						<div class="layui-input-inline">
							<input type="number" id="score" name="score" class="layui-input"
								value="${score}" />
						</div>
					</div>
				</div>
				<table border="0px" cellspacing="0">
					<tr id="ctr">
						<th>数值：</th>
						<th>颜色：</th>
					</tr>
					<tr align="center">
						<td>
							<table class="layui-table" style="text-align: left; float: left;"
								id="range">
								<c:forEach items="${rangelist }" var="range">
									<tr>
										<td><input type='text' readonly='readonly'
											value='${range }' class='layui-input'></td>
									</tr>
								</c:forEach>
							</table>
						</td>
						<td>
							<table class="layui-table" id="colors">
								<c:forEach items="${colorlist }" var="colors">
									<tr>
										<td><input type='text' class='jscolor layui-input'
											value='${colors }' name='colors' /></td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
				<br>
				<button type="submit" class="layui-btn" onclick="save()">保&nbsp;&nbsp;存</button>
				&nbsp;&nbsp; <a
					href="${pageContext.servletContext.contextPath }/systemset/resetsystemset?uid=${uid }"
					class="layui-btn layui-btn-primary">恢复默认设置</a>
			</form>
		</center>
		<hr>
		<div class="layui-form-item">

			<div class="layui-inline">
				<label class="layui-form-label">设置字号：</label>
				<div class="layui-input-inline">
					<input type="number" id="fontsize" class="layui-input"
						value="${fontSize }" style="width: 60px;" />
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="button" value="设置" class="layui-btn"
						onclick="changebg()" />
				</div>
			</div> 
			<label class="layui-form-label">缓存大小：</label>
			<div class="layui-input-inline">
				<span id="tempsize"></span>&nbsp;&nbsp;<a
					href="${pageContext.servletContext.contextPath}/systemset/cleartempfiles"
					class="layui-btn">清理缓存</a>
			</div>
			<input hidden="hidden" id="locationset"
				value="${pageContext.servletContext.contextPath}/systemset/getsystemset?typeId=" />
		</div>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
	<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
</div>
<jsp:include page="../bottom.jsp"></jsp:include>
<script src="resource/js/jscolor.js"></script>
<script>
	var type = $("#chosev option:selected").text();
	var typeval = $("#chosev option:selected").val();
	$(document).ready(function() {
		var tid = $("#tid").val();
		if (tid != "") {
			$("#chosev").val(tid);
		}
	});

	$("#showtype").text(type);
	$("#typeval").text(typeval);
	function changeset() {
		var type = $("#chosev option:selected").text();
		var typeId = $("#chosev option:selected").val();
		$("#showtype").text(type);
		window.location.href = $("#locationset").val() + typeId;
	}
</script>

<script>
 
	//设置字体
	function changebg() {
		var fontstyle = $("#setfont").val();
		var fontsize = $("#fontsize").val();
		$.ajax({
			url : "${pageContext.servletContext.contextPath }/systemset/updatefont",
			type : "post",
			data : {
				"fontsize" : fontsize,
				"uid" : $("#uid").val()
			},
			success : function(val) {
				if (val == 1) {
					$("body").css("font-size", fontsize + "px");
				} else {
					alert("设置失败！");
				}
			}
		});
	}
</script>
<script>
	$(document).ready(function() {
		$.ajax({
			url : "${pageContext.servletContext.contextPath }/systemset/gettempsize",
			type : "post",
			success : function(val) {
				$("#tempsize").text(val);
			}
		});
	});
	function save() {
		if ($("#score").val() >= 15) {
			$("#score").val(15);
			layer.msg("请输入小于15的整数！");
			return false;
		}
	}
</script>