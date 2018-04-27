<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="wrapper">
	<!-- 左侧菜单开始 -->
	<div class="left-nav">
		<div id="side-nav">
			<ul id="nav">
				<li class="list" current><a
					href="${pageContext.servletContext.contextPath }/index"> <i
						class="iconfont">&#xe761;</i> 欢迎页面
				</a></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe70b;</i> 数据管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu">
						<li><a href="${pageContext.servletContext.contextPath }/datapoint/datamanageview"> 数据管理 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/datapoint/dataview"> 可视化数据 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/datapoint/tvsignalview"> 电视信号分析仪 </a></li>
						<li><hr></li>
						<li><a href="${pageContext.servletContext.contextPath }/datapoint/digitaldata"> 数字电视 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/datapoint/analogdata"> 模拟电视 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/datapoint/cdrdata"> CDR数据 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/datapoint/radiodata"> 调幅调频 </a></li>
					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 音视频管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu">
						<li><a href="${pageContext.servletContext.contextPath }/media/audioma"> 音频管理 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/media/videoma"> 视频管理 </a></li>
					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 地图管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu" style="display: none">
						<li><a
							href="${pageContext.servletContext.contextPath }/map/baidumap">
								百度地图 </a></li>
					</ul>
					<ul class="sub-menu" style="display: none">
						<li><a
							href="${pageContext.servletContext.contextPath }/map/bingmap">
								必应地图 </a></li>
					</ul>
					<ul class="sub-menu" style="display: none">
						<li><a
							href="${pageContext.servletContext.contextPath }/map/gaodemap">
								高德地图 </a></li>
					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 用户管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu" style="display: none">
						<li><a href="${pageContext.servletContext.contextPath }/user/usermanage"> 用户管理 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/jurisdiction"> 权限管理 </a></li>
					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 发射台站管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu" style="display: none">
						<li><a href="${pageContext.servletContext.contextPath }/emitter/transmitmaview"> 发射站台管理 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/emitter/addtransmitview"> 添加发射台站 </a></li>

					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 系统设置 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu" style="display: none">
						<li><a href="${pageContext.servletContext.contextPath }/systemset/getsystemset?typeId=0"> 系统设置 </a></li>
						<li><a href="${pageContext.servletContext.contextPath }/systemset/help"> 帮助中心 </a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<!-- 左侧菜单结束 -->
	<script>
		function logout() {
			if (window.confirm('确定退出吗？')) {
				return true;
			} else {
				$("#logouta").attr("href", "index");
				return false;
			}
		}
	</script>