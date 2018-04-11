<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../top.jsp"></jsp:include>
<jsp:include page="../main.jsp"></jsp:include>
<!-- 右侧主体开始 -->
<div class="page-content">
	<div class="content">
		<!-- 右侧内容框架，更改从这里开始 -->
		<fieldset class="layui-elem-field layui-field-title site-title">
			<legend>
				<a name="default">百度地图</a>
			</legend>
		</fieldset>
		<xblock>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">数据类型：</label>
				<div class="layui-input-inline">
					<select id="testmodeId" class="layui-input "
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
				onclick="addpotions()" /> <input type="button"
				class="layui-btn layui-btn-normal" value="生成场强图"
				onclick="fieldOverlay()" /> <input type="button" value="计算距离"
				class="layui-btn layui-btn-normal" onclick="openDis()"> <input
				type="checkbox" onclick="showtransmit()" id="ischeck" title="" />&nbsp;显示台站
			<div class="layui-inline">
				<label class="layui-form-label">选择主题：</label>
				<div class="layui-input-inline">
					<select id="stylelist" onchange="changeMapStyle(this.value)"
						class="layui-input"></select>
				</div>
			</div>
		</div>
		</xblock>
		<div id="baidumap"
			style="width: 100%; min-height: 800px; min-width: 900px; height: 100%;">
		</div>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->

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
</script>
<script>
	//初始化模板选择的下拉框
	var sel = document.getElementById('stylelist');
	for ( var key in mapstyles) {
		var style = mapstyles[key];
		var item = new Option(style.title, key);
		sel.options.add(item);
	}
	// 初始化百度地图
	var bdmap = new BMap.Map("baidumap", {
		mapType : BMAP_HYBRID_MAP
	});
	bdmap.centerAndZoom(new BMap.Point(110.200, 37.500), 5);
	bdmap.enableScrollWheelZoom(true);
	bdmap.enableInertialDragging();
	bdmap.addControl(new BMap.MapTypeControl()); //添加地图类型控件
	bdmap.enableContinuousZoom();

	function changeMapStyle(style) {
		bdmap.setMapStyle({
			style : style
		});

	}
	//城市列表
	var size = new BMap.Size(10, 20);
	bdmap.addControl(new BMap.CityListControl({
		anchor : BMAP_ANCHOR_TOP_LEFT,
		offset : size,
	}));
	//鼠标移动显示经纬度
	bdmap.addEventListener("mousemove", function(e) {
		$("#lnglat").text(
				"经度：" + e.point.lng.toFixed(4) + ",纬度："
						+ e.point.lat.toFixed(4));
	});
	$("<span id='lnglat'></span>").appendTo("#baidumap");
	var bottom_left_control = new BMap.ScaleControl({
		anchor : BMAP_ANCHOR_BOTTOM_LEFT
	});// 右下角，添加比例尺

	var overView = new BMap.OverviewMapControl();
	var overViewOpen = new BMap.OverviewMapControl({
		isOpen : true,
		anchor : BMAP_ANCHOR_BOTTOM_RIGHT
	});

	bdmap.addControl(overView); //添加默认缩略地图控件
	bdmap.addControl(overViewOpen); //右下角，打开
	bdmap.addControl(bottom_left_control);//比例尺
	//鼠标测距
	//开启鼠标测距
	function openDis() {
		new BMapLib.DistanceTool(bdmap).open();
	}

	var markers = new Array();
	var labels = new Array();
	var content = new Array();

	function showtransmit() {
		var opts = {
			width : 280, // 信息窗口宽度
			height : 150, // 信息窗口高度
			title : "台站信息", // 信息窗口标题
			enableMessage : true
		//设置允许信息窗发送短息
		};
		var transmitck = document.getElementById("ischeck").checked;
		if (transmitck) {
			$.ajax({
				url : "gettranlocation",
				type : "post",
				success : function(tran) {

					for (var i = 0; i < tran.length; i++) {
						markers[i] = new BMap.Marker(new BMap.Point(
								tran[i].longitude, tran[i].latitude));
						bdmap.addOverlay(markers[i]);
						labels[i] = new BMap.Label(tran[i].tName, {
							offset : new BMap.Size(20, -10)
						});
						labels[i].setStyle({
							maxWidth : "none"
						});
						markers[i].setLabel(labels[i]);
						content[i] = "地区：" + tran[i].area + "<br>" + "经度："
								+ tran[i].longitude + "<br>纬度："
								+ tran[i].latitude + "<br>" + "高度："
								+ tran[i].height + "<br>" + "负责人："
								+ tran[i].supervisor + "<br>" + "测试人员："
								+ tran[i].testPeople;
						addClickHandler(content[i], markers[i]);
					}
				}
			});

			function addClickHandler(content, marker) {
				marker.addEventListener("click", function(e) {
					openInfo(content, e)
				});
			}
			function openInfo(content, e) {
				var p = e.target;
				var point = new BMap.Point(p.getPosition().lng,
						p.getPosition().lat);
				var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象 
				bdmap.openInfoWindow(infoWindow, point); //开启信息窗口
			}
		} else {
			var allOverlay = bdmap.getOverlays();
			for (var i = 0; i < allOverlay.length; i++) {
				bdmap.removeOverlay(allOverlay[i]);
			}
		}
	}

	//定义海量点集合
	var pointCollections = new Array();

	function addpotions() {
		$("#loadingTip").remove();
		$("<div id='loadingTip' >加载数据，请稍候...</div>").appendTo($("#baidumap"));
		var allOverlay = bdmap.getOverlays();
		for (var i = 0; i < allOverlay.length; i++) {
			bdmap.removeOverlay(allOverlay[i]);
		}

		var opts = {
			width : 250, // 信息窗口宽度
			height : 280, // 信息窗口高度
			title : "采样点详细信息", // 信息窗口标题
		};
		var options = {
			size : BMAP_POINT_SIZE_BIGGER,
			shape : BMAP_POINT_SHAPE_CIRCLE,
		}

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
						$("#loadingTip").remove();
						$(
								"<div id='loadingTip'  style='background-color:#6699ff'>加载完成！</div>")
								.appendTo($("#baidumap"));
						if (document.createElement('canvas').getContext) {
							var pointss = new Array(val[0].length);
							for (var i = 0; i < pointss.length; i++) {
								pointss[i] = [];
							}
							var pointvals = new Array();
							for (var i = 0; i < val[1].length; i++) {
								pointvals[i] = new BMap.Point(val[1][i].lon,
										val[1][i].lat);
								for (var j = 0; j < val[0].length; j++) {
									if ($("#typeid").val() == 0) {
										if (val[1][i].field >= parseInt(val[0][j]
												.split("-")[0])
												&& val[1][i].field < parseInt(val[0][j]
														.split("-")[1])) {
											pointss[j].push(pointvals[i]);
										}
									} else if ($("#typeid").val() == 1) {
										if (val[1][i].snr >= parseInt(val[0][j]
												.split("-")[0])
												&& val[1][i].snr < parseInt(val[0][j]
														.split("-")[1])) {
											pointss[j].push(pointvals[i]);
										}
									} else {
										if (val[1][i].ldpc >= parseInt(val[0][j]
												.split("-")[0])
												&& val[1][i].ldpc < parseInt(val[0][j]
														.split("-")[1])) {
											pointss[j].push(pointvals[i]);
										}
									}
								}
							}

							var pointCollection;
							for (var i = 0; i < pointss.length; i++) {
								var array = [];
								array = pointss[i];
								var options = {
									size : BMAP_POINT_SIZE_BIG,
									shape : BMAP_POINT_SHAPE_CIRCLE,
									color : val[2][i]
								}
								pointCollectioni = new BMap.PointCollection(
										pointss[i], options);
								//点击采样点显示详细信息
								pointCollectioni.addEventListener('click',
										function(e) {
											var infoSample = getSampleInfo(
													e.point.lng, e.point.lat);
											openInfo(infoSample, e.point.lng,
													e.point.lat);
										});
								pointCollections[i] = pointCollectioni;
								bdmap.addOverlay(pointCollectioni); // 添加Overlay

								function openInfo(content, lng, lat) {
									var point = new BMap.Point(lng, lat);
									var infoWindow = new BMap.InfoWindow(
											content, opts); // 创建信息窗口对象
									bdmap.openInfoWindow(infoWindow, point); //开启信息窗口
								}
							}
							//根据经纬度加载采样点信息
							function getSampleInfo(lng, lat) {
								for (var i = 0; i < val[1].length; i++) {
									var everyLng = val[1][i].lon;
									var everyLat = val[1][i].lat;
									var point = new BMap.Point(lng, lat);
									var pointEvery = new BMap.Point(everyLng,
											everyLat);
									if (pointEvery.equals(point)) {
										var sampleInfo;
										if ($("#testmodeId").val() == 1) {
											sampleInfo = "<br>时间："
													+ val[1][i].time
															.substring(
																	0,
																	(val[1][i].time.length) - 2)
													+ "<br>频率："
													+ val[1][i].frequency
													+ "(MHz)<br>经度："
													+ val[1][i].lon + "<br>纬度："
													+ val[1][i].lat
													+ "<br>场强值："
													+ val[1][i].field
													+ "<br>误包率："
													+ val[1][i].ldpc
													+ "<br>信噪比："
													+ val[1][i].snr + "<br>高度："
													+ val[1][i].height
													+ "<br>距离："
													+ val[1][i].distance
													+ "<br>方位角："
													+ val[1][i].angle;
										} else {
											sampleInfo = "<br>时间："
													+ val[1][i].time
															.substring(
																	0,
																	(val[1][i].time.length) - 2)
													+ "<br>频率："
													+ val[1][i].frequency
													+ "(MHz)<br>经度："
													+ val[1][i].lon + "<br>纬度："
													+ val[1][i].lat
													+ "<br>场强值："
													+ val[1][i].field
													+ "<br>信噪比："
													+ val[1][i].snr + "<br>高度："
													+ val[1][i].height
													+ "<br>距离："
													+ val[1][i].distance
													+ "<br>方位角："
													+ val[1][i].angle;
										}
										return sampleInfo;
									}
								}
							}
							function delpoint(time) {
								alert(time)
							}
						} else {
							alert('请在chrome、safari、IE8+以上浏览器查看本示例');
						}
					}
				});
	}
	function fieldOverlay() {
		$("#loadingTip").remove();
		$("<div id='loadingTip' >正在生成场强图，请稍候...</div>").appendTo($("#bdmap"));

		var colorArray = new Array();
		//请求后台返回采样点对应颜色 放进数组
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
		$.ajax({
			url : "fieldRectangle",
			type : "post",
			data : {
				"testModeId" : $("#testmodeId").val()
			},
			success : function(pointmap) {
				var polygon = new BMap.Polygon([
						new BMap.Point(pointmap.maxlng, pointmap.minlat),
						new BMap.Point(pointmap.maxlng, pointmap.maxlat),
						new BMap.Point(pointmap.minlng, pointmap.maxlat),
						new BMap.Point(pointmap.minlng, pointmap.minlat) ], {
					strokeColor : "black",
					strokeWeight : 2,
					strokeOpacity : 0.9,
					fillColor : colorArray[0]
				}); //创建多边形
				bdmap.addOverlay(polygon); //增加多边形
			}
		});
		$
				.ajax({
					url : "fieldOverlay",
					type : "post",
					data : {
						"uid" : $("#uid").val(),
						"testModeId" : $("#testmodeId").val(),
						"typeId" : $("#typeid").val()
					},
					success : function(points) {
						$("#loadingTip").remove();
						$(
								"<div id='loadingTip'  style='background-color:#6699ff'>加载完成！</div>")
								.appendTo($("#bdmap"));
						if (document.createElement('canvas').getContext) {

							var polygonArr = new Array(points.length);
							for (i = 0; i < points.length; i++) {
								var secRing = new Array(points[i].length);
								for (x = 0; x < points[i].length; x++) {
									secRing[x] = new BMap.Point(
											points[i][x].lng, points[i][x].lat);
								}
								bdmap.addOverlay(new BMap.Polygon(secRing, {
									strokeColor : "black",
									fillColor : colorArray[i],
									strokeWeight : 1
								}));
							}
						} else {
							alert('请在chrome、safari、IE8+以上浏览器查看本示例');
						}
					}
				});
	}
</script>
