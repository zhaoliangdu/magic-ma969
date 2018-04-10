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
				<a name="default">发射台站管理</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->

		<xblock> <!-- 导入 -->
		<form
			action="${pageContext.servletContext.contextPath }/uploadTransmitTxt"
			method="post" enctype="multipart/form-data">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">选择文件：</label>
					<div class="layui-input-inline">
						<input type="file" name="transmittxt" accept=".txt"
							required="required" class="layui-input" />
					</div>
				</div>
				<div class="layui-inline">
					<input type="submit" class="layui-btn" value="提交" />
				</div>
		</form>
		<div class="layui-inline">
			<c:choose>
				<c:when test="${auth==2 }">
					<a class="layui-btn"
						href="${pageContext.servletContext.contextPath }/exporttransmit?type=excel">导出excel</a>
                              &nbsp; <a class="layui-btn"
						href="${pageContext.servletContext.contextPath }/exporttransmit?type=txt">导出txt</a>
				</c:when>
			</c:choose>
		</div>
		<div class="layui-inline">
			<span id="lastupt"><span> <span
					style="text-align: center; color: red">${tranmsg }</span>
		</div>
	</div>
	<hr class="layui-bg-red">
	<!-- 查询 -->
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">台站名称：</label>
			<div class="layui-input-inline">
				<input type="text" name="stName" id="stName" class="layui-input" />
			</div>
			<label class="layui-form-label">台站位置：</label>
			<div class="layui-input-inline">
				<input type="text" name="slon" style="width: 100px" id="slon"
					class="layui-input" placeholder="经度" />
			</div>
			<div class="layui-input-inline">
				<input type="text" name="slat" style="width: 100px" id="slat"
					class="layui-input" placeholder="纬度" />
			</div>
			<label class="layui-form-label">天线型号：</label>
			<div class="layui-input-inline">
				<input type="text" name="satype" id="satype" class="layui-input">
			</div>
			<label class="layui-form-label">塔桅型号：</label>
			<div class="layui-input-inline">
				<input type="text" name="sttype" id="sttype" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<button type="button" class="layui-btn" onclick="selectTransmit()">查询</button>
		</div>
	</div>
	</xblock>
	<!-- 台站信息 -->
	<div class="layui-form-item ">
		<label class="layui-form-label">台站名称：</label>
		<div class="layui-input-block">
			<select id="transmitS" onchange="getTransmitId()" autocomplete="off"
				class="layui-input" multiple="multiple"
				style="height: 200px; font-size: 1em;">
				<c:forEach var="transmit" items="${map }">
					<option value="${transmit.key}">${transmit.value}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<hr class="layui-bg-orange">
	<form>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label"> 台站名称：</label>
				<div class="layui-input-inline">
					<input type="text" name="utransmitName" id="transmitName"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">台站地区：</label>
				<div class="layui-input-inline">
					<input type="text" name="transmitArea" id="transmitArea"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">经度：</label>
				<div class="layui-input-inline">
					<input type="text" name="ulongitude" id="clongitude"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">纬度：</label>
				<div class="layui-input-inline">
					<input type="text" name="ulatitude" id="clatitude"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">高度：</label>
				<div class="layui-input-inline">
					<input type="text" name="uheight" id="height" autocomplete="off"
						class="layui-input">
				</div>
			</div>
		</div>
		<hr class="layui-bg-green">
		<div class="layui-form-item">
			<div class="layui-inline">
				<input hidden="hidden" id="aid" /> <label class="layui-form-label">天线型号：</label>
				<div class="layui-input-inline">
					<input type="text" name="uantennaType" id="antennaType"
						autocomplete="off" class="layui-input"><input type="text"
						hidden="hidden" id="aid" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">节目源接引方案：</label>
				<div class="layui-input-inline">
					<input type="text" name="uprogramSource" id="programSource"
						autocomplete="off" class="layui-input">
				</div>
				<input type="hidden" id="antennaId" name="antennaId" />
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">天线场形：</label>
				<div class="layui-input-inline">
					<input type="text" name="ufieldPattern" id="fieldPattern"
						autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label">天线安装设计：</label>
				<div class="layui-input-inline">
					<input type="text" name="uinstallPlan" id="installPlan"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<table>
				<tr>
					<td><div class="layui-inline">
							<label class="layui-form-label">频道：</label>
							<div class="layui-input-inline">
								<select id="channels" class="layui-input">
									<c:forEach var="channel" items="${list }">
										<option value="${channel.channel }">${channel.channel }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<button type="button" class="layui-btn" onclick="addChannel()">添加</button>
							<button type="button" class="layui-btn layui-btn-danger"
								onclick="delChannel()">删除</button>
						</div></td>
					<td><div class="layui-inline">
							<label class="layui-form-label">频率：</label>
							<div class="layui-input-inline">
								<input id="frequency" type="text" class="layui-input" />
							</div>
						</div>
						<div class="layui-inline">
							<button type="button" class="layui-btn" onclick="addFrequency()">添加</button>
							<button type="button" class="layui-btn layui-btn-danger"
								onclick="delFrequency()">删除</button>
						</div></td>
				</tr>
				<tr>
					<td><div class="layui-inline">
							<label class="layui-form-label">频道列表：</label>
							<div class="layui-input-inline">
								<select id="schannel" multiple="multiple" class="layui-input"
									style="height: 100px;">
								</select>
							</div>
						</div></td>
					<td><div class="layui-inline">
							<label class="layui-form-label">频率列表：</label>
							<div class="layui-input-inline">
								<select id="sfrequency" multiple="multiple" class="layui-input"
									style="height: 100px;">
								</select>
							</div>
						</div></td>
				</tr>
			</table>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline"></div>
			<div class="layui-inline"></div>
		</div>
		<input type="button" class="layui-btn" value="添加天线" onclick="showcf()" />
		<hr>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">塔桅型号：</label>
				<div class="layui-input-inline">
					<input type="text" name="towerType" id="towerType"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">塔桅结构：</label>
				<div class="layui-input-inline">
					<input type="text" name="towerFramework" id="towerFramework"
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">塔桅报告：</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" name="utowerReport" id="towerReport"
					class="layui-textarea"></textarea>
			</div>

			<input class="layui-btn" type="button" onclick="doPrint()" value='打印' />&nbsp;&nbsp;还可输入：<label
				id="count"></label>
		</div>
		<hr class="layui-bg-cyan">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">机房位置：</label>
				<div class="layui-input-inline">
					<input type="text" name="uroomPosition" id="roomPosition"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">设备状况：</label>
				<div class="layui-input-inline">
					<input type="text" name="uroomDeviceStatus" id="roomDeviceStatus"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">供 配 电：</label>
				<div class="layui-input-inline">
					<input type="text" name="upowerDistribution" id="powerDistribution"
						" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">防雷接地：</label>
				<div class="layui-input-inline">
					<input type="text" name="ugrounding" id="grounding"
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">空调通风及采暖：</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" name="uairConditioner"
					id="airConditioner" class="layui-textarea"></textarea>
			</div>
		</div>
		<hr class="layui-bg-gray">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label"> 负责人：</label>
				<div class="layui-input-inline">
					<input type="text" ame="supervisor" id="supervisor"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">测试人员：</label>
				<div class="layui-input-inline">
					<input type="text" name="utestPeople" id="testPeople"
						autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">负责人制度：</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" name="uresponsiblePerson"
					id="responsiblePerson" class="layui-textarea"></textarea>
			</div>
		</div>
		<hr class="layui-bg-blue">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="button" class="layui-btn" onclick="updateTransmit()"
					value="保存" /> &nbsp;
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				<input type="button" value="删除" class="layui-btn layui-btn-danger"
					onclick="deleteTransmit()" />
			</div>
		</div>
		<input type="hidden" name="username" value="${username }" />
	</form>
</div>
</div>
<!-- 右侧内容框架，更改从这里结束 -->
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<jsp:include page="../bottom.jsp"></jsp:include>
<jsp:include page="transmitjs.jsp"></jsp:include>
<script>
	function doPrint() {
		$("#reportprint").printArea();
	}
	var maxCount = 510; // 最高字数，这个值可以自己配置
	$("#towerReport").on('keyup', function() {
		var len = getStrLength(this.value);
		$("#count").html(maxCount - len);
	})
	// 中文字符判断
	function getStrLength(str) {
		var len = str.length;
		var reLen = 0;
		for (var i = 0; i < len; i++) {
			if (str.charCodeAt(i) < 30 || str.charCodeAt(i) > 255) {
				// 全角    
				reLen += 2;
			} else {
				reLen++;
			}
		}
		return reLen;
	}
</script>

