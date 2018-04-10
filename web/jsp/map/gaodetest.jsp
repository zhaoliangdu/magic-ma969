<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../top.jsp"></jsp:include>
<jsp:include page="../main.jsp"></jsp:include>
<script
	src="//webapi.amap.com/loca?v=1.0.0&key=100ac2f2d36ccc13f62f1f86617ca9e3"></script>
<div id="page-wrapper" style="min-width: 600px;">
	<div class="row">
		<div class="col-lg-12" style="min-width: 600px;">
			<!-- /.panel -->
			<div class="panel panel-default">
				<div class="panel-heading">高德地图</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<strong>数据类型选择：</strong><select
										style="width: 100px; height: 33px;" id="testmodeId"
										class="inptcs" onchange="getTestMode(this)">
										<option value="1">数字电视</option>
										<option value="2">调频/调幅</option>
										<option value="3">CDR</option>
										<option value="4">模拟电视</option>
									</select> &nbsp;&nbsp;<strong>模式选择：</strong><select id="typeid"
										style="width: 100px; height: 33px;" class="inptcs">
										<option value="0">场强</option>
										<option value="1">信噪比</option>
										<option value="2">误包率</option>
									</select> <input type="button" class="btn btn-info" value="加载采样点"
										onclick="addgpoints()" /> <input type="button"
										class="btn btn-info" value="生成场强图" />&nbsp;&nbsp;<strong>
										选择主题：</strong> <select id="mapstyle"
										style="width: 100px; height: 33px;" class="inptcs">
										<option value="amap://styles/normal">标准</option>
										<option value="amap://styles/whitesmoke">远山黛</option>
										<option value="amap://styles/macaron">马卡龙</option>
										<option value="amap://styles/graffiti">涂鸦</option>
										<option value="amap://styles/darkblue">极夜蓝</option>
										<option value="amap://styles/blue">靛青蓝</option>
										<option value="amap://styles/fresh">草色青</option>
										<option value="amap://styles/dark">幻影黑</option>
										<option value="amap://styles/light">月光银</option>
										<option value="amap://styles/grey">雅士灰</option>
									</select>&nbsp;<input type="button" value="设置" onclick="changestyle()"
										class="btn btn-info" />
								</div>
							</div>
							<div id="my-map" style="width: 100%; height: 700px;"></div>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>
<jsp:include page="../bottom.jsp"></jsp:include>

<script type="text/javascript">
	function getTestMode(val) {
		if (val.value == 2) {
			$("#typeid option[value='2']").remove();
		} else {
			$("#typeid").empty();
			$("#typeid").append("<option value='0'>场强</option>");
			$("#typeid").append("<option value='1'>信噪比</option>");
			$("#typeid").append("<option value='2'>误包率</option>");
		}
	}

	var loca = new Loca('my-map', {
		mapStyle : $("#mapstyle").val(),
		zoom : 4,
		center : [ 106.001, 37.2000 ]
	});
	function changestyle() {
		loca = new Loca('my-map', {
			mapStyle : $("#mapstyle").val(),
			zoom : 4,
			center : [ 106.001, 37.2000 ]
		});
	}

	//加载采样点
	function addgpoints() {
		var type = $("#typeid").val();
		$("#loadingTip").remove();
		$('<div id="loadingTip">加载数据，请稍候...</div>').appendTo($("#my-map"));
		testmodeId = $("#testmodeId").val();
		$
				.ajax({
					url : "getpoints",
					type : "post",
					data : {
						"uid" : $("#uid").val(),
						"testModeId" : testmodeId,
						"typeId" : type
					},
					success : function(val) {
						$("#loadingTip").remove();
						$(
								'<div id="loadingTip" style="background-color:#6699ff">加载完成!</div>')
								.appendTo($("#my-map"));

						var data = val[1];
						var layer = new Loca.VisualLayer();

						layer.setData(data);
						layer
								.setOptions({
									type : 'point',
									lnglat : 'center',
									style : {
										radius : 10,
										fill : function(res) {
											var d;
											if (type == 0) {
												d = res.value.field;
											} else if (type == 1) {
												d = res.value.snr;
											} else {
												d = res.value.ldpc;
											}
											for (i = 0; i < val[0].length; i++) {
												if (d >= val[0][i].split("-")[0]
														&& d < val[0][i]
																.split("-")[1]) {
													var latlng = res.value.center;
													return val[2][i];
												}
											}
										}
									}
								});
						layer.addTo(loca);
					}
				});
	}
</script>