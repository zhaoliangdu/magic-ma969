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
				<a name="default">添加发射台站</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->
		<div>
			<form action="addtransmit" method="post"
				onsubmit="return checkform()">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label"> 台站名称：</label>
						<div class="layui-input-inline">
							<input type="tel" name="atransmitName" id="atransmitName"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">台站地区：</label>
						<div class="layui-input-inline">
							<input type="text" name="atransmitArea" id="atransmitArea"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">经度：</label>
						<div class="layui-input-inline">
							<input type="text" name="alongitude" id="alongitude"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">纬度：</label>
						<div class="layui-input-inline">
							<input type="text" name="alatitude" id="alatitude"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">高度：</label>
						<div class="layui-input-inline">
							<input type="text" name="aheight" id="aheight" autocomplete="off"
								class="layui-input">
						</div>
					</div>
				</div>
				<hr class="layui-bg-red">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">天线型号：</label>
						<div class="layui-input-inline">
							<input type="text" name="aantennaType" id="aantennaType"
								autocomplete="off" class="layui-input"><input
								type="hidden" id="antennaId" name="antennaId" />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">节目源接引方案：</label>
						<div class="layui-input-inline">
							<input type="text" name="aprogramSource" id="aprogramSource"
								autocomplete="off" class="layui-input">
						</div>
						<input type="hidden" id="antennaId" name="antennaId" />
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">天线场形：</label>
						<div class="layui-input-inline">
							<input type="text" name="afieldPattern" id="afieldPattern"
								autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-inline">
						<label class="layui-form-label">天线安装设计：</label>
						<div class="layui-input-inline">
							<input type="text" name="ainstallPlan" id="ainstallPlan"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<table>
						<tr>
							<td><div class="layui-inline">
									<label class="layui-form-label">频道：</label>
									<div class="layui-input-inline">
										<select id="achannels" class="layui-input">
											<c:forEach var="channel" items="${list }">
												<option value="${channel.channel }">${channel.channel }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="layui-inline">
									<button type="button" class="layui-btn" onclick="addaChannel()">添加</button>
									<button type="button" class="layui-btn layui-btn-danger"
										onclick="adelChannel()">删除</button>
								</div></td>
							<td><div class="layui-inline">
									<label class="layui-form-label">频率：</label>
									<div class="layui-input-inline">
										<input class="layui-input" type="number" id="afrequency" />
									</div>
								</div>
								<div class="layui-inline">
									<button type="button" class="layui-btn"
										onclick="addaFrequency()">添加</button>
									<button type="button" class="layui-btn layui-btn-danger"
										onclick="adelFrequency()">删除</button>
								</div></td>
						</tr>
						<tr>
							<td>
								<div class="layui-inline">
									<label class="layui-form-label">频道列表：</label>
									<div class="layui-inline">
										<select id="aschannel" class="layui-input"
											style="height: 100px; width: 200px;" id="aschannel"
											multiple="multiple" name="aschannel">
										</select>
									</div>
								</div>
							</td>
							<td><div class="layui-inline">
									<label class="layui-form-label">频率列表：</label>
									<div class="layui-inline">
										<select style="width: 200px; height: 100px;"
											name="asfrequency" id="asfrequency" multiple="multiple"
											class="layui-input">
										</select>
									</div>
								</div></td>
						</tr>
					</table>
				</div>
				<input type="button" class="layui-btn" value="添加天线"
					onclick="showcf()" />
				<hr>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">塔桅型号：</label>
						<div class="layui-input-inline">
							<input type="text" name="atowerType" id="atowerType"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">塔桅结构：</label>
						<div class="layui-input-inline">
							<input type="text" name="atowerFramework" id="atowerFramework"
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">塔桅报告：</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入内容" name="atowerReport"
							id="atowerReport" class="layui-textarea"></textarea>
					</div>
				</div>
				<hr>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">机房位置：</label>
						<div class="layui-input-inline">
							<input type="text" name="aroomPosition" id="aroomPosition"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">设备状况：</label>
						<div class="layui-input-inline">
							<input type="text" name="aroomDeviceStatus"
								id="aroomDeviceStatus" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">供 配 电：</label>
						<div class="layui-input-inline">
							<input type="text" name="apowerDistribution"
								id="apowerDistribution" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">防雷接地：</label>
						<div class="layui-input-inline">
							<input type="text" name="agrounding" id="agrounding"
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">空调通风及采暖：</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入内容" name="aairConditioner"
							id="aairConditioner" class="layui-textarea"></textarea>
					</div>
				</div>
				<hr>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label"> 负责人：</label>
						<div class="layui-input-inline">
							<input type="text" name="asupervisor" id="asupervisor"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">测试人员：</label>
						<div class="layui-input-inline">
							<input type="text" name="atestPeople" id="atestPeople"
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">负责人制度：</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入内容" name="aresponsiblePerson"
							id="aresponsiblePerson" class="layui-textarea"></textarea>
					</div>
				</div>
				<hr>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
				<input type="hidden" name="username" value="${username }" />
			</form>
		</div>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->

<script>
	function checkform() {
		if (($("#atransmitName").val() == '')
				|| ($("#atransmitArea").val() == '')
				|| ($("#alongitude").val() == '')
				|| ($("#alatitude").val() == '') || ($("#aheight").val() == '')
				|| ($("#atowerType").val() == '')
				|| ($("#atowerFramework").val() == '')
				|| ($("#atowerReport").val() == '')
				|| ($("#atowerType").val() == '')
				|| ($("#aroomPosition").val() == '')
				|| ($("#aroomDeviceStatus").val() == '')
				|| ($("#apowerDistribution").val() == '')
				|| ($("#agrounding").val() == '')
				|| ($("#aairConditioner").val() == '')) {
			alert("请填写完整信息");
			return false;
		} else {
			return true;
		}
	}
</script>
<jsp:include page="../bottom.jsp"></jsp:include>
<jsp:include page="transmitjs.jsp"></jsp:include>