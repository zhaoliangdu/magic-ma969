<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../top.jsp"></jsp:include>
<jsp:include page="../main.jsp"></jsp:include>
<script type='text/javascript'
	src='https://www.bing.com/api/maps/mapcontrol?branch=experimental&callback=loadMapScenario'
	async defer></script>
<div id="page-wrapper" style="min-width: 600px;">
	<div class="row">
		<div class="col-lg-12">
			<!-- /.panel -->
			<div class="panel panel-default">
				<div class="panel-heading">必应地图</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<strong>数据类型选择：</strong><select style="width: 100px; height: 33px;"
										id="testmodeId" class="inptcs" onchange="getTestMode(this)">
										<option value="1">数字电视</option>
										<option value="2">调频/调幅</option>
										<option value="3">CDR</option>
										<option value="4">模拟电视</option>
									</select>&nbsp;&nbsp;<strong>模式选择：</strong><select id="typeid" style="width: 100px; height: 33px;"
										class="inptcs">
										<option value="0">场强</option>
										<option value="1">信噪比</option>
										<option value="2">误包率</option>
									</select> <input type="button" class="btn btn-info" value="加载采样点"
										onclick="addbpoints()" /> &nbsp;&nbsp;<input type="checkbox"
										onclick="showtran()" id="isck" /> <strong>显示发射塔</strong>
								</div>
							</div>
							<div id="bingMap" style="width: 100%; height: 700px;"></div>
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
					center : new Microsoft.Maps.Location(37.200, 106.001),
					zoom : 4
				});
	}
	//地图显示台站
	function showtran() {
		var transmitck = document.getElementById("isck").checked;
		if (transmitck) {
			$.ajax({
				url : "gettranlocation",
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
		$("#loadingTip").remove();
		$('<div id="loadingTip">加载数据，请稍候...</div>').appendTo($("#bingMap"));
		for (var i = bingmap.entities.getLength() - 1; i >= 0; i--) {
			var pushpin = bingmap.entities.get(i);
			if (pushpin instanceof Microsoft.Maps.Pushpin) {
				bingmap.entities.removeAt(i);
			}
		}
		var colorArray = new Array();

		//场强范围对应颜色表
		$.ajax({
			url : "getsamplestyle",
			type : "get",
			data : {
				"uid" : $("#uid").val(),
				"typeId" : $("#typeid").val()
			},
			success : function(colors) {
				for (var i = 0; i < colors.length; i++) {
					colorArray[i] = colors[i];
				}
			}
		});

		$
				.ajax({
					url : "getpoints",
					type : "post",
					data : {
						"uid" : $("#uid").val(),
						"testModeId" : $("#testmodeId").val(),
						"typeId" : $("#typeid").val()
					},
					success : function(val) {
						if (document.createElement('canvas').getContext) {
							$("#loadingTip").remove();
							$(
									'<div id="loadingTip" style="background-color:#6699ff">加载完成！</div>')
									.appendTo($("#bingMap"));
							setTimeout("$('#loading').remove()", 3000);
							var pushpins = new Array(val[1].length);
							for (var i = 0; i < val[1].length; i++) {
								for (var j = 0; j < val[0].length; j++) {
									if ($("#typeid").val() == 0) {
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
									} else if ($("#typeid").val() == 1) {
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
								var pushpin = pushpins[a];

								pushpin.metadata = {
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
												pushpin,
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
								bingmap.entities.push(pushpin);
							}

						} else {
							alert('请在chrome、safari、IE8+以上浏览器查看本示例');
						}
					}
				});
	}
</script>