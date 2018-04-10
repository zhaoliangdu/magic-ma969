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
				<a name="default">数字电视数据</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->
		<xblock>
		<form class="layui-form xbs" method="get" id="datasearch"
			action="${pageContext.servletContext.contextPath }/digitaldata">
			<input hidden="hidden" name="uid" value="${user.userId }">
			<div class="layui-form-pane" style="text-align: center;">
				<div class="layui-form-item" style="display: inline-block;">
					<label class="layui-form-label xbs768">日期范围</label>
					<div class="layui-input-inline xbs768">
						<input class="layui-input" placeholder="开始时间" id="date_s"
							name="startTime" value="${stTime }">
					</div>
					<div class="layui-input-inline xbs768">
						<input class="layui-input" placeholder="截止时间" id="date_e"
							name="endTime" value="${enTime }">
					</div>
					<div class="layui-input-inline">
						<input type="text" name="area" id="areas" value="${sareas }"
							placeholder="请输入地区" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-input-inline">
						<input type="text" name="username" placeholder="请输入频率"
							autocomplete="off" class="layui-input" name="frequency"
							id="frequencys" value="${sfrequencys }">
					</div>
					<div class="layui-input-inline" style="width: 80px">
						<button class="layui-btn layui-btn-normal" lay-filter="sreach">
							<i class="layui-icon">&#xe615;查询</i>
						</button>
					</div>
				</div>
			</div>
		</form>

		<a class="layui-btn layui-btn-normal"
			href="${pageContext.servletContext.contextPath }/exportdigitaldata?type=txt">导出到txt</a>
		<a class="layui-btn layui-btn-normal"
			href="${pageContext.servletContext.contextPath }/exportdigitaldata?type=excel">导出到excel</a>
		</xblock>
		<table class="layui-table">
			<thead>
				<tr>

					<th>ID</th>
					<th>时间</th>
					<th>地区</th>
					<th>经度</th>
					<th>纬度</th>
					<th>高度</th>
					<th>速度</th>
					<th>测试模式</th>
					<th>频率</th>
					<th>带宽</th>
					<th>音频场强</th>
					<th>信噪比</th>
					<th>MER</th>
					<th>误包率</th>
					<th>距离</th>
					<th>方位角</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${digitaldatas }" var="digitaldata">
					<tr> 
						<td>${digitaldata.id }</td>
						<td>${digitaldata.time }</td>
						<td>${digitaldata.area }</td>
						<td>${digitaldata.transforLng }</td>
						<td>${digitaldata.transforLat }</td>
						<td>${digitaldata.height }</td>
						<td>${digitaldata.speed }</td>
						<td>数字电视</td>
						<td>${digitaldata.frequency }</td>
						<td>${digitaldata.wideBand }</td>
						<td>${digitaldata.fieldStrength }</td>
						<td>${digitaldata.snr }</td>
						<td>${digitaldata.mer }</td>
						<td>${digitaldata.ldpc }</td>
						<td>${digitaldata.distance }</td>
						<td>${digitaldata.angle }</td>
						<c:choose>
							<c:when test="${auth==2 }">
								<td><a href="#" onclick="deldigital(${digitaldata.id })"
									class="layui-btn layui-btn-danger">删除</a></td>
							</c:when>
							<c:otherwise>
								<td>无操作权限</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${pageNum<8}">
				<c:set var="sta" value="1">
				</c:set>
				<c:set var="end" value="${pageNum }">
				</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="sta" value="${pageNo-4}">
				</c:set>
				<c:set var="end" value="${pageNo+3}">
				</c:set>
				<c:if test="${sta<=0 }">
					<c:set var="sta" value="1">
					</c:set>
					<c:set var="end" value="8">
					</c:set>
				</c:if>
				<c:if test="${end>=pageNum}">
					<c:set var="end" value="${pageNum}">
					</c:set>
					<c:set var="sta" value="${end-7}">
					</c:set>
				</c:if>
			</c:otherwise>
		</c:choose>
		<center>
			<table style="width: 40%;">
				<thead>
					<tr>
						<th>共${count}条/${pageNum }页</th>
						<th><a class="layui-btn layui-btn-normal"
							href="${pageContext.servletContext.contextPath }/digitaldata?pageNo=1&startTime=${stTime}&endTime=${enTime}&area=${sareas}&testModeId=${stestModes}&frequency=${sfrequencys}&pageNumr=${pageNumr}"
							pageNo="0"> 首页 </a></th>
						<c:if test="${pageNo-1 <=0}">
							<th><a class="layui-btn layui-btn-normal"
								href="${pageContext.servletContext.contextPath }/digitaldata?pageNo=1&startTime=${stTime}&endTime=${enTime}&area=${sareas}&testModeId=${stestModes}&frequency=${sfrequencys}&pageNumr=${pageNumr}">
									上一页 </a></th>
						</c:if>
						<c:if test="${pageNo-1>0}">
							<th><a class="layui-btn layui-btn-normal"
								href="${pageContext.servletContext.contextPath }/digitaldata?pageNo=${pageNo-1 }&startTime=${stTime}&endTime=${enTime}&area=${sareas}&testModeId=${stestModes}&frequency=${sfrequencys}&pageNumr=${pageNumr}"
								pageNo="${pageNo-1 }"> 上一页 </a></th>
						</c:if>
						<c:forEach begin="${sta }" end="${end }" var="er">
							<c:if test="${pageNo==er }">
								<th><a class="layui-btn layui-btn-normal"
									href="${pageContext.servletContext.contextPath }/digitaldata?pageNo=${er}&startTime=${stTime}&endTime=${enTime}&area=${sareas}&testModeId=${stestModes}&frequency=${sfrequencys}&pageNumr=${pageNumr}"
									pageNo="${er }"> ${er } </a></th>
							</c:if>
							<c:if test="${pageNo!=er }">
								<th><a class="layui-btn layui-btn-normal"
									href="${pageContext.servletContext.contextPath }/digitaldata?pageNo=${er}&startTime=${stTime}&endTime=${enTime}&area=${sareas}&testModeId=${stestModes}&frequency=${sfrequencys}&pageNumr=${pageNumr}"
									pageNo="${er }"> ${er } </a></th>
							</c:if>
						</c:forEach>
						<c:if test="${pageNo+1 >pageNum}">
							<th><a class="layui-btn layui-btn-normal"
								href="${pageContext.servletContext.contextPath }/digitaldata?pageNo=${pageNum}&startTime=${stTime}&endTime=${enTime}&area=${sareas}&testModeId=${stestModes}&frequency=${sfrequencys}&pageNumr=${pageNumr}"
								pageNo="${pageNum }"> 下一页 </th>
							</td>
						</c:if>
						<c:if test="${pageNo+1<=pageNum}">
							<th><a class="layui-btn layui-btn-normal"
								href="${pageContext.servletContext.contextPath }/digitaldata?pageNo=${pageNo+1}&startTime=${stTime}&endTime=${enTime}&area=${sareas}&testModeId=${stestModes}&frequency=${sfrequencys}&pageNumr=${pageNumr}"
								pageNo="${pageNo+1 })"> 下一页 </a></th>
						</c:if>
						<th><a class="layui-btn layui-btn-normal"
							href="${pageContext.servletContext.contextPath }/digitaldata?pageNo=${pageNum}&startTime=${stTime}&endTime=${enTime}&area=${sareas}&testModeId=${stestModes}&frequency=${sfrequencys}&pageNumr=${pageNumr}"
							pageNo="${pageNum})"> 尾页 </a></th>
					</tr>
				</thead>
			</table>
		</center>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
</div>
<script>
layui.use('table', function(){
  var table = layui.table; 
  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 315
    ,url: '/digitaldata' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
      ,{field: 'username', title: '用户名', width:80}
      ,{field: 'sex', title: '性别', width:80, sort: true}
      ,{field: 'city', title: '城市', width:80} 
      ,{field: 'sign', title: '签名', width: 177}
      ,{field: 'experience', title: '积分', width: 80, sort: true}
      ,{field: 'score', title: '评分', width: 80, sort: true}
      ,{field: 'classify', title: '职业', width: 80}
      ,{field: 'wealth', title: '财富', width: 135, sort: true}
    ]]
  });
  
});
</script>
<script> 
 
	function deldigital(id) {
		if (window.confirm("确定删除吗？")) {
			$.ajax({
				url : "deldigitaldata",
				type : "post",
				data : {
					"id" : id
				},
				success : function(val) {
					if (val == 1) {
						alert("删除成功");
						window.location.reload();
					} else {
						alert("删除失败");
					}
				}
			});
		} else {
			return false;
		}
	}
</script>
<jsp:include page="../bottom.jsp"></jsp:include>
<script>
	layui.use([ 'laydate' ], function() {
		laydate = layui.laydate;//日期插件 
		//以上模块根据需要引入
		// 
		var start = {
			min : '0001-01-01 00:00:00',
			max : '2999-12-31 23:59:59',
			istoday : false,
			choose : function(datas) {
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
			}
		};

		var end = {
			min : '0001-01-01 00:00:00',
			max : '2999-12-31 23:59:59',
			istoday : false,
			choose : function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		document.getElementById('date_s').onclick = function() {
			start.elem = this;
			laydate(start);
		}
		document.getElementById('date_e').onclick = function() {
			end.elem = this
			laydate(end);
		}
	}); 
</script>
