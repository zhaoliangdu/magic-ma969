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
		action="${pageContext.servletContext.contextPath }/updatetvsignal"
		method="post">
		<div class="layui-form-item">
			<label for="L_email" class="layui-form-label"> 编号： </label>
			<div class="layui-input-inline">
				<input type="text" name="id" id="id" required="required"
					placeholder="编号" autocomplete="off" class="layui-input"
					readonly="readonly" value="${tvsignal.id }">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_email" class="layui-form-label"> 地区： </label>
			<div class="layui-input-inline">
				<input type="text" name="area" id="area" required="required"
					placeholder="地区" autocomplete="off" class="layui-input"
					value="${tvsignal.area }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 经度： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="经度" name="longitude" id="longitude"
					required="required" autocomplete="off" class="layui-input"
					value="${tvsignal.longitude }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 纬度： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="纬度" name="latitude"
					required="required" autocomplete="off" class="layui-input"
					value="${tvsignal.latitude }">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_repass" class="layui-form-label"> 型号： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="型号" name="machineModel"
					required="required" autocomplete="off" class="layui-input"
					value="${tvsignal.machineModel }">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_repass" class="layui-form-label"> 负责人： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="负责人" name="chargePerson"
					required="required" autocomplete="off" class="layui-input"
					value="${tvsignal.chargePerson }">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_repass" class="layui-form-label"> 台站名称： </label>
			<div class="layui-input-inline">
				<input type="text" placeholder="台站名称" name="transmitName"
					required="required" autocomplete="off" class="layui-input"
					value="${tvsignal.transmitName }">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_repass" class="layui-form-label"> </label>
			<button class="layui-btn" lay-filter="add" type='submit'>保存
			</button>
		</div>
	</form>
	<input hidden="hidden"
		value="${pageContext.servletContext.contextPath }/tvsignalview"
		id="disview" />
</body>
</html>