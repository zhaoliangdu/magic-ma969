<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../top.jsp"></jsp:include>
<jsp:include page="../main.jsp"></jsp:include>
<input value="${ismode }" type="hidden" class="imde" />
<!-- 右侧主体开始 -->
<div class="page-content">
	<div class="content">
		<div class="testmode">
			<p style="color: red; font-size: 50px; margin-top: 5%;">无操作权限！</p>
		</div> 
		<!-- 右侧内容框架，更改从这里开始 -->
		<fieldset class="layui-elem-field layui-field-title site-title">
			<legend>
				<a name="default">数据上传</a>
			</legend>
		</fieldset>
		<form class="layui-form" action="${pageContext.servletContext.contextPath }/datapoint/readfile" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">文件选择：</label>
				<div class="layui-input-inline">
					<input type="file" accept=".txt" name="file" required
						multiple="multiple" id='filec' autocomplete="off"
						placeholder="请选择文件" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">输入地区：</label>
				<div class="layui-input-inline">
					<input type="text" name="area" id='area' placeholder="请输入地区"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">台站选择：</label>
				<div class="layui-input-inline">
					<select name="eid" id="eid" class="layui-input showtran"></select>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-normal" type='submit' lay-filter="demo1"
						onclick="uploadfile()">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
			<input type="hidden" name="auth" value="${auth }" id="auth" />
		</form>
		<fieldset class="layui-elem-field layui-field-title site-title">
			<legend>
				<a name="default">数据备份</a>
			</legend>
		</fieldset>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<a href='${pageContext.servletContext.contextPath }/datapoint/backupdatabase'
					class="layui-btn" lay-filter="demo1">数据备份</a>
			</div>
		</div>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<jsp:include page="../bottom.jsp"></jsp:include>
<script>
	$(document).ready(function() {
		var datamsg = $("#datamsg").text();
		if (datamsg == "导入成功！" || datamsg == "导入失败，数据或已存在！") {
			$("#datamsg").text("");
		}
	});
	function uploadfile() {
		var file = $("#filec")[0].files[0];
		var area = $("#area").val();
		var eid = $("#eid").val();
		var auth = $("#auth").val();
		if (file != null && area != "") {
			$("#datamsg").text("正在导入，请稍后. . .");
			var formData = new FormData();
			formData.append("file", file);
			formData.append("area", area);
			formData.append("eid", eid);
			formData.append("auth", auth);
			$.ajax({
				url : "${pageContext.servletContext.contextPath }/datapoint/readfile",
				type : "post",
				data : formData,
				processData : false,
				// 告诉jQuery不要去设置Content-Type请求头
				contentType : false,
				success : function(val) {
					$("#datamsg").text("");
					if (val == 2) {
						alert("导入成功！");
					} else if (val == 1) {
						alert("无操作权限");
					} else if (val == 0) {
						alert("导入失败");
					} else {
						alert("导入失败");
					}
				}
			});
		}
	}
</script>

