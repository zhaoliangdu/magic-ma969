<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../top.jsp"></jsp:include>
<jsp:include page="../main.jsp"></jsp:include>

<fieldset class="layui-elem-field layui-field-title site-title">
	<legend>
		<a>可视化数据显示</a>
	</legend>
</fieldset>
<div class="layui-form-item">
	<div class="layui-inline">
		<label class="layui-form-label">模式选择：</label>
		<div class="layui-input-inline">
			<select class="layui-input" id="testmodeId"><option
					value="1">数字电视</option>
				<option value="2">调频/调幅</option>
				<option value="3">CDR</option>
				<option value="4">模拟电视</option>
			</select>
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">地区：</label>
		<div class="layui-input-inline">
			<select class="layui-input" id='area'>
				<c:forEach var="area" items="${areas }">
					<option value="${area }">${area }</option>
				</c:forEach>
			</select>
		</div>
		<input type="button" value="确定" onclick="showChart()"
			class="layui-btn layui-btn-normal" />
	</div>
</div>
<div id="dataview">
	<div id="chor" style="height: 700px;"></div>
	<div id="hist" style="height: 700px;"></div>
</div>

<script type="text/javascript">
	// 基于准备好的dom，初始化echarts图表
	var histChart = echarts.init(document.getElementById('hist'));
	var chorChart = echarts.init(document.getElementById('chor'));
	//根据数据加载直方图和饼状图
	uid = $("#uid").val();
	function showChart() {
		testmodeId = $("#testmodeId option:selected").val();
		area = $("#area option:selected").val();
		$.ajax({
			url : "getfieldStrength",
			type : "post",
			data : {
				"uid" : uid,
				"testModeId" : testmodeId,
				"area" : area
			},
			success : function(fieldstrength) {
				// 为echarts对象加载数据 
				histChart.setOption(hist_option(fieldstrength));
				chorChart.setOption(chor_option(fieldstrength));
			}
		});
	}
	$(function($) {
		testmodeId = $("#testmodeId").val();
		$.ajax({
			url : "getfieldStrength",
			type : "post",
			data : {
				"uid" : uid,
				"testModeId" : testmodeId,
				"area" : $("#area option:selected").val()
			},
			success : function(fieldstrength) {
				// 为echarts对象加载数据 
				histChart.setOption(hist_option(fieldstrength));
				chorChart.setOption(chor_option(fieldstrength));
			}
		});
	});
	//加载柱状图 饼状图
	function hist_option(field) {
		var fields = field[0];
		var array = fields.toString().split(",");
		var min_max = array[0].split('-')[0] + "-"
				+ array[array.length - 1].split('-')[1];
		var cha = array[0].split('-')[1] - array[0].split('-')[0];
		var count = array.length;
		var histOption = {
			title : {
				text : '场强分布直方图',
				subtext : "场强范围：" + min_max + " 差值：" + cha + " 分段：" + count,
				x : 'center'
			},
			tooltip : {
				show : true,
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			xAxis : [ {
				type : 'category',
				data : field[0],
				axisTick : {
					alignWithLabel : true
				}
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ],
						option : {
							funnel : {
								x : '25%',
								width : '50%',
								funnelAlign : 'left',
							}
						}
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			series : [ {
				name : "场强分布",
				type : "bar",
				data : field[2],
				label : {
					normal : {
						show : true,
						position : 'top'
					}
				},
				itemStyle : {
					//通常情况下：
					//每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
					normal : {
						color : function(params) {
							var colorList = field[1];
							return colorList[params.dataIndex];
						}
					}
				},
				grid : {
					left : '0%',
					right : '0%',
					bottom : '0%',
					containLabel : true
				}
			} ]
		};
		return histOption;
	}
	function chor_option(field) {
		var fields = field[0];
		var array = fields.toString().split(",");
		var min_max = array[0].split('-')[0] + "-"
				+ array[array.length - 1].split('-')[1];
		var cha = array[0].split('-')[1] - array[0].split('-')[0];
		var count = array.length;
		function chor_data(field) {
			var chor_dataName = [];
			var chor_dataVlaue = [];
			for (var i = 0; i < field[0].lenght; i++) {
				chor_dataName[i] = field[2][i];
			}
			return chor_dataName;
		}
		chorOption = {
			title : {
				text : '场强分布饼状图',
				subtext : "场强范围：" + min_max + " 差值：" + cha + " 分段：" + count,
				x : 'center'
			},
			color : field[1],
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'left',
				data : field[0]
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'pie', 'funnel' ],
						option : {
							funnel : {
								x : '20%',
								width : '50%',
								funnelAlign : 'left',
							}
						}
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			grid : {
				left : '0%',
				right : '0%',
				bottom : '0%',
				containLabel : true
			},
			calculable : true,
			series : [ {
				name : '场强分布',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [ {
					name : field[0][0],
					value : field[2][0]
				}, {
					name : field[0][1],
					value : field[2][1]
				}, {
					name : field[0][2],
					value : field[2][2]
				}, {
					name : field[0][3],
					value : field[2][3]
				}, {
					name : field[0][4],
					value : field[2][4]
				}, {
					name : field[0][5],
					value : field[2][5]
				}, {
					name : field[0][6],
					value : field[2][6]
				}, {
					name : field[0][7],
					value : field[2][7]
				}, {
					name : field[0][8],
					value : field[2][8]
				}, {
					name : field[0][9],
					value : field[2][9]
				}, {
					name : field[0][10],
					value : field[2][10]
				}, {
					name : field[0][11],
					value : field[2][11]
				}, {
					name : field[0][12],
					value : field[2][12]
				} ]
			} ]
		};
		return chorOption;
	}
</script>
<jsp:include page="../bottom.jsp"></jsp:include>