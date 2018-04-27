<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../top.jsp"></jsp:include>
<jsp:include page="../main.jsp"></jsp:include>
<script type='text/javascript'
	src='https://www.bing.com/api/maps/mapcontrol?branch=experimental&callback=loadMapScenario'
	async defer></script>
<!-- 右侧主体开始 -->
<div class="page-content">
	<div class="content">
		<!-- 右侧内容框架，更改从这里开始 -->
		<fieldset class="layui-elem-field layui-field-title site-title">
			<legend>
				<a name="default">必应地图</a>
			</legend>
		</fieldset>
		<xblock>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">数据类型：</label>
				<div class="layui-input-inline">
					<select id="testmodeId" class="layui-input " id="testmodeId"
						onchange="getTestMode(this)">
						<option value="1">数字电视</option>
						<option value="2">调频/调幅</option>
						<option value="3">CDR</option>
						<option value="4">模拟电视</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">模式选择：</label>
				<div class="layui-input-inline">
					<select id="typeid" class="layui-input">
						<option value="0">场强</option>
						<option value="1">信噪比</option>
						<option value="2">误包率</option>
					</select>
				</div>
			</div>
			<input type="button" class="layui-btn layui-btn-normal" value="加载采样点"
				onclick="addbpoints()" /> <input type="checkbox"
				onclick="showtran()" id="isck" />&nbsp;&nbsp;&nbsp;&nbsp;显示台站

		</div>
		</xblock>
		<div id="bingMap"
			style="width: 100%; min-height: 800px; min-width: 900px; height: 100%;"></div>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<jsp:include page="../bottom.jsp"></jsp:include>
<script>
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
	var bingmap;
	//加载bing地图
	function loadMapScenario() {
		bingmap = new Microsoft.Maps.Map(
				document.getElementById('bingMap'),
				{
					credentials : 'AtrHYGI-KJZEpGZS7YyXlC0ABXqFMrBFRrgp_oRG3lJdHOc5Th-VO9F1nIBGkCJe',
					center : new Microsoft.Maps.Location(32.200, 106.001),
					zoom : 4
				});
	}
	//地图显示台站
	function showtran() {
		var transmitck = document.getElementById("isck").checked;
		if (transmitck) {
			$.ajax({
				url : "${pageContext.servletContext.contextPath }/emitter/gettranlocation",
				type : "post",
				success : function(tran) {
					var pushpins = new Array(tran.length);
					for (var i = 0; i < tran.length; i++) {
						pushpins[i] = new Microsoft.Maps.Pushpin(
								new Microsoft.Maps.Location(tran[i].latitude,
										tran[i].longitude), {
									text : 'T',
									title : tran[i].tName,
									subTitle : ''
								});
						bingmap.entities.push(pushpins[i]);
					}
				}
			});
		} else {
			for (var i = bingmap.entities.getLength() - 1; i >= 0; i--) {
				var pushpin = bingmap.entities.get(i);
				if (pushpin instanceof Microsoft.Maps.Pushpin) {
					bingmap.entities.removeAt(i);
				}
			}
		}
	}
	//加载采样点信息
	function addbpoints() {
		var uid = $("#uid").val();
		var testModeId = $("#testmodeId").val();
		var typeId = $("#typeid").val();
		$("#loadingTip").remove();
		$('<div id="loadingTip">加载数据，请稍候...</div>').appendTo($("#bingMap"));
		for (var i = bingmap.entities.getLength() - 1; i >= 0; i--) {
			var pushpin = bingmap.entities.get(i);
			if (pushpin instanceof Microsoft.Maps.Pushpin) {
				bingmap.entities.removeAt(i);
			}
		}

		$
				.ajax({
					url : "${pageContext.servletContext.contextPath }/datapoint/getpoints",
					type : "post",
					data : {
						"uid" : uid,
						"testModeId" : testModeId,
						"typeId" : typeId
					},
					success : function(val) {
						var colorArray = val[2];
						if (document.createElement('canvas').getContext) {
							$("#loadingTip").remove();
							if (val[1] == "") {
								$(
										'<div id="loadingTip" style="background-color:red">数据不存在！</div>')
										.appendTo($("#bingMap"));

							} else {
								$(
										'<div id="loadingTip" style="background-color:#6699ff">加载完成！</div>')
										.appendTo($("#bingMap"));

							}

							var pushpins = new Array(val[1].length);
							for (var i = 0; i < val[1].length; i++) {
								for (var j = 0; j < val[0].length; j++) {
									if (typeId == 0) {
										if (val[1][i].field >= parseInt(val[0][j]
												.split("-")[0])
												&& val[1][i].field < parseInt(val[0][j]
														.split("-")[1])) {
											pushpins[i] = new Microsoft.Maps.Pushpin(
													new Microsoft.Maps.Location(
															val[1][i].lat,
															val[1][i].lon), {
														color : colorArray[j]
													});
										}
									} else if (typeId == 1) {
										if (val[1][i].snr >= parseInt(val[0][j]
												.split("-")[0])
												&& val[1][i].snr < parseInt(val[0][j]
														.split("-")[1])) {
											pushpins[i] = new Microsoft.Maps.Pushpin(
													new Microsoft.Maps.Location(
															val[1][i].lat,
															val[1][i].lon), {
														color : colorArray[j]
													});
										}
									} else {
										if (val[1][i].ldpc >= parseInt(val[0][j]
												.split("-")[0])
												&& val[1][i].ldpc < parseInt(val[0][j]
														.split("-")[1])) {
											pushpins[i] = new Microsoft.Maps.Pushpin(
													new Microsoft.Maps.Location(
															val[1][i].lat,
															val[1][i].lon), {
														color : colorArray[j]
													});
										}
									}

								}
							}
						 
							var infobox = new Microsoft.Maps.Infobox(
									pushpins[0].getLocation(), {
										visible : false
									});
							infobox.setMap(bingmap);
							for (var a = 0; a < pushpins.length; a++) {

								pushpins[a].metadata = {
									title : '采样点信息',
									description : "<hr>时间："
											+ val[1][a].time
													.substring(
															0,
															(val[1][a].time.length) - 2)
											+ "<br>频率：" + val[1][a].frequency
											+ "(MHz)<br>经度：" + val[1][a].lon
											+ "<br>纬度：" + val[1][a].lat
											+ "<br>场强值：" + val[1][a].field
											+ "<br>高度：" + val[1][a].height
											+ "<br>距离：" + val[1][a].distance
											+ "<br>信噪比：" + val[1][a].snr
											+ "<br>方位角：" + val[1][a].angle
								};

								Microsoft.Maps.Events
										.addHandler(
												 pushpins[a],
												'click',
												function(args) {
													infobox
															.setOptions({
																location : args.target
																		.getLocation(),
																title : args.target.metadata.title,
																description : args.target.metadata.description,
																visible : true
															});
												});
								bingmap.entities.push( pushpins[a]);
							}

						} else {
							alert('请在chrome、safari、IE8+以上浏览器查看本示例');
						}
					}
				});
	}
</script>