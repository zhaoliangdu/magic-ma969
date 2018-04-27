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
				<a name="default">电视信号分析仪</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->
		
		<xblock> 
		<a class="layui-btn layui-btn-normal"
			onclick="tvsignal_add('添加电视信号分析仪','add-tvsignal','600','500')">添加电视信号分析仪</a>
		</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					 
					<th>ID</th>
					<th>地区</th>
					<th>经度</th>
					<th>纬度</th>
					<th>设备型号</th>
					<th>负责人</th>
					<th>台站名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tvsignal }" var="tvsignal">
					<tr>
						 
						<td>${tvsignal.id }</td>
						<td>${tvsignal.area }</td>
						<td>${tvsignal.longitude }</td>
						<td>${tvsignal.latitude }</td>
						<td>${tvsignal.machineModel }</td>
						<td>${tvsignal.chargePerson }</td>
						<td>${tvsignal.transmitName }</td>
						<td><c:choose>
								<c:when test="${auth==2 }">
									<a href="#" class="layui-btn layui-btn-xs"
										onclick="tvsignal_edit('修改电视信号分析仪','${pageContext.servletContext.contextPath }/datapoint/edit-tvsignal?id=${tvsignal.id}','600','500')">修改</a>
									<a href="#" class="layui-btn layui-btn-danger"
										onclick="deltvsignal(${tvsignal.id })">删除</a>
								</c:when>
								<c:otherwise>
																无操作权限
															</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<input hidden="hidden"
	value="${pageContext.servletContext.contextPath }/datapoint/tvsignalview"
	id="disview" />

<jsp:include page="../bottom.jsp"></jsp:include>
<script>
 
	function findTVSignalById(id){
		$.ajax({
			url:"${pageContext.servletContext.contextPath }/datapoint/findTVSignalById",
			type:"get",
			data:{"id":id},
			success:function(tvsignal){
				if(tvsignal!=null){
					$("#id").val(tvsignal.id);
					$("#area").val(tvsignal.area);
					$("#longitude").val(tvsignal.longitude);
					$("#latitude").val(tvsignal.latitude);
					$("#machineModel").val(tvsignal.machineModel);
					$("#chargePerson").val(tvsignal.chargePerson);
					$("#transmitName").val(tvsignal.transmitName);
				}else{
					alert("未找到");
				}
			}
		});
	}
	function deltvsignal(id){
		if(window.confirm("确定删除吗？")){
			$.ajax({
				url:"${pageContext.servletContext.contextPath }/datapoint/deltvsignal",
				type:"post",
				data:{"id":id},
				success:function(val){
					if(val==1){
						alert("删除成功");
						 window.location.href=$("#disview").val();
					}else{
						alert("删除失败");
					}
				}
			});
		}else{
			return false;
		}
	}
</script>
<script>
$(document).ready(function() {
	$('.example').DataTable({
		language: {
			"sProcessing": "处理中...",
			"sLengthMenu": "每页显示 _MENU_ 条结果",
			"sZeroRecords": "没有匹配结果",
			"sInfo": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 / _TOTAL_ 条记录)",
			"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项结果过滤)", 
			"sSearch": "搜索:", 
			"sEmptyTable": "表中数据为空",
			"sLoadingRecords": "载入中...",
			"sInfoThousands": ",",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上页",
				"sNext": "下页",
				"sLast": "末页"
			}
		}
	});
});
</script>
<script>

    layui.use(['laydate'], function(){
        laydate = layui.laydate;//日期插件

        //以上模块根据需要引入
        //
        var start = {
            min: laydate.now()
            ,max: '2099-06-16 23:59:59'
            ,istoday: false
            ,choose: function(datas){
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };

        var end = {
            min: laydate.now()
            ,max: '2099-06-16 23:59:59'
            ,istoday: false
            ,choose: function(datas){
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        document.getElementById('LAY_demorange_s').onclick = function(){
            start.elem = this;
            laydate(start);
        }
        document.getElementById('LAY_demorange_e').onclick = function(){
            end.elem = this
            laydate(end);
        }

    });

    /*用户-彻底删除*/
    function member_unset(obj,id){
        layer.confirm('彻底删除无法恢复，确认要删除数据吗？',function(index){
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已彻底删除',{icon:1,time:1000});

        });
    }
    /*-添加*/
    function tvsignal_add(title,url,w,h){
        x_admin_show(title,url,w,h);
    }
    /*-修改*/
    function tvsignal_edit(title,url,w,h){
        x_admin_show(title,url,w,h);
    }
</script>
