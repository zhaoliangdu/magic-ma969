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
				<a name="default">音频数据</a>
			</legend>
		</fieldset>
		<!-- 右侧内容框架，更改从这里开始 -->
		<xblock>
		<div class="layui-form-item layui-form">

			<form action="${pageContext.servletContext.contextPath }/media/addaudio" enctype="multipart/form-data" method="post"
				id="audiofrom" onsubmit="return checkform()" target="hidden_frame">
				<label class="layui-form-label">台站名称：</label>
				<div class="layui-input-inline">
					<select name="eid" class="layui-input showtran" required="required"></select>
				</div>
				<div class="layui-input-inline">
					<input type="file" accept=".mp3" name="filename"
						class="layui-input" required="required" id="batchFile" />
				</div>
				<div class="layui-input-inline">
					<input type="submit" class="layui-btn layui-btn-normal" value="上传"
						id="audioUploadBtn" />
				</div>
			</form>
		</div>
		</xblock>
		<progress max="100" style="width: 100%;" id="vprogressBar"></progress>
		<table class="layui-table">
			<thead>
				<tr>

					<th>编号</th>
					<th>上传时间</th>
					<th>音频文件名称</th>
					<th>播放音频文件</th>
					<th>台站名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${audiolist }" var="audio">
				<tr>

					<td>${audio.id }</td>
					<td>${audio.importDate }</td>
					<td>${audio.audioName}</td>
					<td><audio controls>
							<source src="/audio/${audio.audioPath }" type="audio/mpeg">
						</audio></td>
					<td>${audio.transmitName }</td>
					<c:choose>
						<c:when test="${auth==2 }">
							<td><a href="#" class="layui-btn layui-btn-danger"
								onclick="delaudio(${audio.id})">删除</a></td>
						</c:when>
						<c:otherwise>
							<td>无操作权限</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>

		<!-- 右侧内容框架，更改从这里结束 -->
	</div>
</div>
<input hidden="hidden" id="audioview"
	value="${pageContext.servletContext.contextPath }/meida/audioma" />
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->

<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
<jsp:include page="../bottom.jsp"></jsp:include>
<script>

function checkform(){
	var file = $("#batchFile").val(); 
	if(file==""){
		return false;
	}
}

	function delaudio(id){
		if(window.confirm("确定删除吗？")){
			$.ajax({
				url:"${pageContext.servletContext.contextPath }/media/delaudiodata",
				type:"post",
				data:{"id":id},
				success:function(val){
					if(val==1){
						alert("删除成功！");
						window.location.href=$("#audioview").val();
					}else{
						alert("删除失败！");
					}
				}
			});
		}else{
			return false;
		} 
	}
	
	 $(document).ready(function() {   
	        var init; 
	            $('#audioUploadBtn').bind('click', function() { 
	             
	                $('#audiofrom').submit();  
	                var eventFun = function() {  
	                	$.ajax({
	            			url : "${pageContext.servletContext.contextPath }/media/getprogress",
	            			type : "post",
	            			success : function(progressdata) {  
	            				if(progressdata == 0){
	            					 clearInterval(init);
	            	            	 alert("上传失败，文件不能为空"); 
	            	            	 window.location.reload();
	            					 
	            					 window.location.reload();
	            	            }else if ((progressdata == "100%")||(progressdata ==1)) { 
	            					 clearInterval(init); 
	            					 alert("上传成功");
	            					 window.location.reload();
	            	            } else{
	            	            	$("#vprogressBar").val(progressdata);  
	            	            }
	            			}
	            		});
	                };  
	                init = setInterval(eventFun, 500);  
	            });   
	        });  
	 
</script>
