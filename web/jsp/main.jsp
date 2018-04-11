<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="wrapper">
	<!-- 左侧菜单开始 -->
	<div class="left-nav">
		<div id="side-nav">
			<ul id="nav">
				<li class="list" current><a href="index"> <i
						class="iconfont">&#xe761;</i> 欢迎页面 <i class="iconfont nav_right">&#xe697;</i>
				</a></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe70b;</i> 数据管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu">
						<li><a href="datamanageview"> <i class="iconfont">&#xe6a7;</i>
								数据管理
						</a></li>
						<li><a href="dataview"> <i class="iconfont">&#xe6a7;</i>
								可视化数据
						</a></li>
						<li><a href="tvsignalview"> <i class="iconfont">&#xe6a7;</i>
								电视信号分析仪
						</a></li>
						<li><a href="digitaldata"> <i class="iconfont">&#xe6a7;</i>
								数字电视
						</a></li>
						<li><a href="analogdata"> <i class="iconfont">&#xe6a7;</i>
								模拟电视
						</a></li>
						<li><a href="cdrdata"> <i class="iconfont">&#xe6a7;</i>
								CDR数据
						</a></li>
						<li><a href="radiodata"> <i class="iconfont">&#xe6a7;</i>
								调幅调频
						</a></li>
					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 音视频管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu">
						<li><a href="audioma"> <i class="iconfont">&#xe6a7;</i>
								音频管理
						</a></li>
						<li><a href="videoma"> <i class="iconfont">&#xe6a7;</i>
								视频管理
						</a></li>
					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 地图管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu" style="display: none">
						<li><a href="baidumap"> <i class="iconfont">&#xe6a7;</i>
								百度地图
						</a></li>
					</ul>
					<ul class="sub-menu" style="display: none">
						<li><a href="bingmap"> <i class="iconfont">&#xe6a7;</i>
								必应地图
						</a></li>
					</ul>
				</li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 用户管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu" style="display: none">
						<li><a href="usermanage"> <i class="iconfont">&#xe6a7;</i>
								用户管理
						</a></li>
						<li><a href="jurisdiction"> <i class="iconfont">&#xe6a7;</i>
								权限管理
						</a></li>
					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 发射台站管理 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu" style="display: none">
						<li><a href="transmitmaview"> <i class="iconfont">&#xe6a7;</i>
								发射站台管理
						</a></li>
						<li><a href="addtransmitview"> <i class="iconfont">&#xe6a7;</i>
								添加发射台站
						</a></li>

					</ul></li>
				<li class="list"><a href="javascript:;"> <i
						class="iconfont">&#xe6a3;</i> 系统设置 <i class="iconfont nav_right">&#xe697;</i>
				</a>
					<ul class="sub-menu" style="display: none">
						<li><a href="getsystemset?typeId=0"> <i class="iconfont">&#xe6a7;</i>
								系统设置
						</a></li>
						<li><a href="help"> <i class="iconfont">&#xe6a7;</i> 帮助中心
						</a></li>
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