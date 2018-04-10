<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../resource/resource.jsp"></jsp:include>
</head>
<body>
	<span id="dmsg" style="color: red;"><h2>${dmsg }</h2></span>
	<form class="layui-form"
		action="${pageContext.servletContext.contextPath }/addtvsignal">
		<div class="layui-form-item">
			<label for="L_email" class="layui-form-label"> 地区： </label>
			<div class="layui-input-inline">
				<input type="text" iplaceholder="地区" name="area" required="required"
					required="required" placeholder="地区" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 经度： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="经度" name="longitude"
					required="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 纬度： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="纬度" name="latitude"
					required="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 型号： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="型号" name="machineModel"
					required="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 负责人： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="负责人" name="chargePerson"
					required="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 台站名称： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="台站名称" name="transmitName"
					required="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> </label>
			<button class="layui-btn layui-btn-normal" lay-filter="add"
				type='submit'>增加</button>
		</div>
	</form>
</body>
</html>