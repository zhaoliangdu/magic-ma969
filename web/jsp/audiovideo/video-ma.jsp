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
				<a name="default">视频数据</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->
		<xblock>
		<div class="layui-form-item layui-form">
			<div class="layui-input-inline">
				<button class="layui-btn layui-btn-danger">
					<i class="layui-icon">&#xe640;</i>批量删除
				</button>
			</div>
			<form action="addvideo" enctype="multipart/form-data" method="post"
				id="videofrom" target="hidden_frame" onsubmit="return checkform()">
				<label class="layui-form-label">台站名称：</label>
				<div class="layui-input-inline">
					<select name="eid" class="layui-input showtran" required="required"></select>
				</div>
				<div class="layui-input-inline">
					<input class="layui-input" type="file" name="filename"
						accept=".MP4" required id="videofile" id="videofile" />
				</div>
				<div class="layui-input-inline">
					<input type="submit" class="layui-btn layui-btn-normal" value="上传"
						id="videoUploadBtn" />
				</div>
			</form>
		</div>

		</xblock>
		<table class="layui-table">
			<thead>
				<tr>

					<th>编号</th>
					<th>上传时间</th>
					<th>视频文件名称</th>
					<th>播放视频文件</th>
					<th>台站名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="video" items="${videolist }">
					<tr>
					 
						<td>${video.id }</td>
						<td>${video.importDate }</td>
						<td>${video.videoName }</td>
						<td><video controls width="300" height='200'>
								<source id="videosource" src="/video/${video.videoPath }">
								您的浏览器不支持 video 元素。
								</source>
							</video></td>
						<td>${video.transmitName }</td>
						<c:choose>
							<c:when test="${auth==2 }">
								<td><a href="#" class="layui-btn layui-btn-danger"
									onclick="delvideo(${video.id})">删除</a></td>
							</c:when>
							<c:otherwise>
								<td>无操作权限</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<input type="hidden"
						value="${pageContext.servletContext.contextPath }${video.videoPath }"
						pathid="${video.id}" />
				</c:forEach>
			</tbody>
		</table>
		<input id="videoview" hidden="hidden"
			value="${pageContext.servletContext.contextPath }/videoma" />
		<center>
			<div id="progressdiv">
				<div class="layui-progress layui-progress-big">
					<div class="layui-progress-bar layui-bg-blue" id="vprogressBar">
						<span id="progressnum"></span>
					</div>
				</div>
			</div>
		</center>
		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
<script>

function checkform(){
	var file = $("#videofile").val(); 
	if(file==""){
		alert("文件不能为空");
		return false;
	}
}
 
	function delvideo(id){
		if(window.confirm("确定删除吗？")){
			$.ajax({
				url:"delvideodata",
				type:"post",
			 	data:{"id":id},
			 	success:function(val){
			 		if(val==1){
			 			alert("删除成功！");
			 			window.location.href=$("#videoview").val();
			 		}else{
			 			alert("删除失败！")
			 		}
			 	}
			});
		}else{
			return false;
		}
	}
	 $(document).ready(function() {  
         var init;
         $("#progressdiv").hide(); 
             $('#videoUploadBtn').bind('click', function() { 
             	$("#progressdiv").show();
                 $('#vidoefrom').submit();  
                 var eventFun = function() {  
                 	$.ajax({
             			url : "getprogress",
             			type : "get",
             			success : function(progressdata) {  
             				if(progressdata == 0){
             					 clearInterval(init);
             	            	 alert("上传失败，文件不能为空"); 
             	            	 window.location.reload();
             					 $("#progressdiv").hide();   
             					 window.location.reload();
             	            }else if (progressdata == 1 ||progressdata == "100%" ) { 
             					 clearInterval(init);
             					 $("#progressdiv").hide();   
             					 alert("上传成功");
             					 window.location.reload();
             	            } else{
             	            	$("#progressnum").text(progressdata);
             	            	$("#vprogressBar").css("width", progressdata);   
             	            }
             			}
             		});
                 };  
                 init = setInterval(eventFun, 500);  
             });   
         });  
</script>
<jsp:include page="../bottom.jsp"></jsp:include>
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
			"sInfoPostFix": "",
			"sSearch": "搜索:",
			"sUrl": "",
			"sEmptyTable": "表中数据为空",
			"sLoadingRecords": "载入中...",
			"sInfoThousands": ",",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上页",
				"sNext": "下页",
				"sLast": "末页"
			},
			"oAria": {
				"sSortAscending": ": 以升序排列此列",
				"sSortDescending": ": 以降序排列此列"
			}
		}
	});
});
</script>
