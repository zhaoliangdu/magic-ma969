<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../../jsp/top.jsp"></jsp:include>
<jsp:include page="../../jsp/main.jsp"></jsp:include>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.4.0&key=100ac2f2d36ccc13f62f1f86617ca9e3"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>

<!-- 右侧主体开始 -->
<div class="page-content">
	<div class="content">
		<!-- 右侧内容框架，更改从这里开始 -->
		<fieldset class="layui-elem-field layui-field-title site-title">
			<legend>
				<a name="default">高德地图</a>
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
				onclick="addgpoints()" /> <input type="button"
				class="layui-btn layui-btn-normal" value="距离量测"
				onClick="javascript:startRuler2()" /> <input type="button"
				class="layui-btn layui-btn-normal" value="面积计算" onclick="mainji()"><input
				type="checkbox" onclick="gdcheck()" id="gdcheck" title="" />&nbsp;&nbsp;显示台站

		</div>
		</xblock>
		<div id="container"
			style="width: 100%; min-height: 800px; min-width: 900px; height: 100%; color: black;"></div>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->

<jsp:include page="../bottom.jsp"></jsp:include>

<script>
	//高德地图  
	var map = new AMap.Map('container', {
		resizeEnable : true,
		rotateEnable : true,
		pitchEnable : true,
		expandZoomRange : true,
		zoom : 4,
		center : [ 106.001, 37.2000 ]
	});
	var clickEventListener = map.on('mousemove', function(e) {
		document.getElementById("lnglat").innerHTML = "经度："
				+ e.lnglat.getLng().toFixed(4) + ',纬度：'
				+ e.lnglat.getLat().toFixed(4)
	});
	$("<div id='lnglat' ></div>").appendTo($("#container"));
	AMap.event.addDomListener(document.getElementById('gdcheck'), 'click',
			function() {
				map.remove(markers);
			}, false);
	var markers = [], positions = [];

	function gdcheck() {
		var gdck = document.getElementById("gdcheck").checked;
		if (gdck) {
			$.ajax({
				url : "${pageContext.servletContext.contextPath }/emitter/gettranlocation",
				type : "post",
				success : function(tran) {
					var infoWindow = new AMap.InfoWindow({
						offset : new AMap.Pixel(0, -30)
					});
					for (var i = 0; i < tran.length; i++) {
						marker = new AMap.Marker({
							map : map,
							position : [ tran[i].longitude, tran[i].latitude ]
						});
						marker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
							offset : new AMap.Pixel(18, 0),//修改label相对于maker的位置
							content : tran[i].tName
						});
						marker.content = "地区：" + tran[i].area + "<br>" + "经度："
								+ tran[i].longitude + ",纬度：" + tran[i].latitude
								+ "<br>" + "高度：" + tran[i].height + "<br>"
								+ "负责人：" + tran[i].supervisor + "<br>"
								+ "测试人员：" + tran[i].testPeople;
						marker.on('click', markerClick);
					}
					function markerClick(e) {
						infoWindow.setContent(e.target.content);
						infoWindow.open(map, e.target.getPosition());
					}
				}
			});
		}
	}
	var ruler1, ruler2;
	map
			.plugin(
					[ "AMap.RangingTool" ],
					function() {
						ruler1 = new AMap.RangingTool(map);
						AMap.event.addListener(ruler1, "end", function(e) {
							ruler1.turnOff();
						});
						var sMarker = {
							icon : new AMap.Icon(
									{
										size : new AMap.Size(19, 30),//图标大小
										image : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b1.png"
									})
						};
						var eMarker = {
							icon : new AMap.Icon(
									{
										size : new AMap.Size(19, 30),//图标大小
										image : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b2.png"
									}),
							offset : new AMap.Pixel(-10, -30)
						};
						var lOptions = {
							strokeStyle : "solid",
							strokeColor : "#6699ff",
							strokeOpacity : 1,
							strokeWeight : 2
						};
						var rulerOptions = {
							startMarkerOptions : sMarker,
							endMarkerOptions : eMarker,
							lineOptions : lOptions
						};
						ruler2 = new AMap.RangingTool(map, rulerOptions);
					});
	function startRuler2() {
		ruler1.turnOff();
		ruler2.turnOn();
	}
	var contextMenu = new AMap.ContextMenu(); //创建右键菜单
	//右键放大
	contextMenu.addItem("放大一级", function() {
		map.zoomIn();
	}, 0);
	//右键缩小
	contextMenu.addItem("缩小一级", function() {
		map.zoomOut();
	}, 1);
	//右键显示全国范围
	contextMenu.addItem("缩放至全国范围", function(e) {
		map.setZoomAndCenter(4, [ 108.95, 34.26 ]);
	}, 2);
	//右键添加Marker标记
	contextMenu.addItem("添加标记", function(e) {
		var marker = new AMap.Marker({
			map : map,
			position : contextMenuPositon
		//基点位置
		});
	}, 3);

	//地图绑定鼠标右击事件——弹出右键菜单
	map.on('rightclick', function(e) {
		contextMenu.open(map, e.lnglat);
		contextMenuPositon = e.lnglat;
	});
	function mainji() {
		map.plugin([ "AMap.MouseTool" ], function() {
			var mouseTool = new AMap.MouseTool(map);
			//鼠标工具插件添加draw事件监听
			AMap.event.addListener(mouseTool, "draw", function callback(e) {
				var eObject = e.obj;//obj属性就是鼠标事件完成所绘制的覆盖物对象。
			});
			mouseTool.measureArea(); //调用鼠标工具的面积量测功能
		});
	}

	//加载采样点
	function addgpoints() {
		$('<div id="loadingTip">加载数据，请稍候...</div>').appendTo($("#container"));
		testmodeId = $("#testmodeId").val(); 
		 
		$
				.ajax({
					url : "${pageContext.servletContext.contextPath }/datapoint/getpoints",
					type : "post",
					data : {
						"uid" : $("#uid").val(),
						"testModeId" : testmodeId,
						"typeId" : 0
					},
					success : function(val) {
						var colorArray  = val[2];
						$("#loadingTip").empty();
						$(
								'<div id="loadingTip" style="background-color:#6699ff">加载完成!</div>')
								.appendTo($("#container"));
						setTimeout("$('#loading').remove()", 3000);
						AMapUI.load([ 'ui/misc/PointSimplifier', 'lib/utils',
								'lib/$' ], function(PointSimplifier, utils, $) {

							if (!PointSimplifier.supportCanvas) {
								alert('当前环境不支持 Canvas！');
								return;
							}
							//启动页面
							initPage(PointSimplifier, utils, $);
						});

						function initPage(PointSimplifier, utils, $) {
							function MyCanvasRender(pointSimplifierIns, opts) {
								//直接调用父类的构造函数
								MyCanvasRender.__super__.constructor.apply(
										this, arguments);
							}

							//继承自默认的Canvas引擎（http://webapi.amap.com/ui/1.0/ui/misc/PointSimplifier/render/canvas.js）
							utils.inherit(MyCanvasRender,
									PointSimplifier.Render.Canvas);
							utils
									.extend(
											MyCanvasRender.prototype,
											{
												renderNormalPoints : function(
														zoom, activePoints,
														shadowPoints) {
													//先按默认逻辑处理shadowPoints
													MyCanvasRender.__super__.renderNormalPoints
															.call(this, zoom,
																	null,
																	shadowPoints);
													var pointStyle = this
															.getOption('pointStyle'), getPointsGroupKey = this
															.getOption('getPointsGroupKey'), pointStyleGroup = this
															.getOption('pointStyleGroup'), pointSimplifierIns = this
															.getPointSimplifierInstance(), groups = {};
													//按key分组
													for (var i = 0, len = activePoints.length; i < len; i++) {

														var point = activePoints[i], dataIndex = point.idx, dataItem = pointSimplifierIns
																.getDataItemByIndex(dataIndex);

														var key = getPointsGroupKey
																.call(
																		this,
																		dataItem,
																		dataIndex);
														if (!groups[key]) {
															groups[key] = [];
														}
														groups[key]
																.push(activePoints[i]);
													}
													//分组绘制
													for ( var k in groups) {

														//继承pointStyle中的默认属性
														var styleOptions = utils
																.extend(
																		{},
																		pointStyle,
																		pointStyleGroup[k]);

														//调用父类的绘制函数
														this
																.drawPointsWithStyleOptions(
																		groups[k],
																		styleOptions);
													}
												}
											});
							var cindex;
							var pointSimplifierIns = new PointSimplifier(
									{
										zIndex : 300,
										map : map,
										getPosition : function(item) {
											if (!item) {
												return null;
											}
											return item.position;
										},
										getHoverTitle : function(dataItem, i) {
											return "<hr>时间："
													+ val[1][i].time
															.substring(
																	0,
																	(val[1][i].time.length) - 2)
													+ "<br>频率："
													+ val[1][i].frequency
													+ "(MHz)<br>经度："
													+ val[1][i].lon
													+ "&nbsp;&nbsp;纬度："
													+ val[1][i].lat
													+ "<br>场强值："
													+ val[1][i].field
													+ "<br>高度："
													+ val[1][i].height
													+ "<br>距离："
													+ val[1][i].distance
													+ "<br>信噪比："
													+ val[1][i].snr
													+ "<br>方位角："
													+ val[1][i].angle;
										},
										//赋值为 MyCanvasRender
										renderConstructor : MyCanvasRender,
										renderOptions : {
											getPointsGroupKey : function(
													dataItem, dataIndex) {
												//这里直接按索引取余  
												for (var i = 0; i < val[1].length; i++) {
													if (dataItem.position[0] == val[1][i].lon
															&& dataItem.position[1] == val[1][i].lat) {
														for (var c = 0; c < val[0].length; c++) {
															if (val[1][i].field > val[0][c]
																	.split("-")[0]
																	&& val[1][i].field <= val[0][c]
																			.split("-")[1]) {
																cindex = c;
																break;
															}
														}
														break;
													}

												}

												return "g" + cindex;
											},
											//分组配置
											pointStyleGroup : {
												'g0' : {
													fillStyle : colorArray[0],
													width : 16,
													height : 16
												},
												'g1' : {
													fillStyle : colorArray[1],
													width : 16,
													height : 16
												},
												'g2' : {
													fillStyle : colorArray[2],
													width : 16,
													height : 16
												},
												'g3' : {
													fillStyle : colorArray[3],
													width : 16,
													height : 16
												},
												'g4' : {
													fillStyle : colorArray[4],
													width : 16,
													height : 16
												},
												'g5' : {
													fillStyle : colorArray[5],
													width : 16,
													height : 16
												},
												'g6' : {
													fillStyle : colorArray[6],
													width : 16,
													height : 16
												},
												'g7' : {
													fillStyle : colorArray[7],
													width : 16,
													height : 16
												},
												'g8' : {
													fillStyle : colorArray[8],
													width : 16,
													height : 16
												},
												'g9' : {
													fillStyle : colorArray[9],
													width : 16,
													height : 16
												},
												'g10' : {
													fillStyle : colorArray[10],
													width : 16,
													height : 16
												},
												'g11' : {
													fillStyle : colorArray[11],
													width : 16,
													height : 16
												},
												'g12' : {
													fillStyle : colorArray[12],
													width : 16,
													height : 16
												},
												'g13' : {
													fillStyle : colorArray[13],
													width : 16,
													height : 16
												},
												'g14' : {
													fillStyle : colorArray[14],
													width : 16,
													height : 16
												}
											}

										}
									});

							//创建数据点
							var data = createPoints(val[1]);

							//设置数据源，data需要是一个数组
							pointSimplifierIns.setData(data);

							//监听事件
							pointSimplifierIns.on(
									'pointClick pointMouseover pointMouseout',
									function(e, record) {
										console.log(e.type, record);
									});
						}

						//创建数据
						function createPoints(val) {
							var data = [];
							for (var i = 0; i < val.length; i++) {
								data.push({
									position : [ val[i].lon, val[i].lat ],
									map : map
								});
							}
							return data;
						}
					}
				});
	}
</script>