<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<footer>
	<center>
		<div class="footer">
			<div class="copyright">
				Copyright &copy; 2001 - <span id='year'></span> Magic-China. All
				Rights Reserved
			</div>
		</div>
	</center>
</footer>
<!-- 背景切换开始 -->
<div class="bg-changer">
	<div class="swiper-container changer-list">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/a.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/b.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/c.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/d.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/e.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/f.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/g.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/h.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/i.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/j.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<img class="item" src="${pageContext.servletContext.contextPath }/resource/images/k.jpg" alt="">
			</div>
			<div class="swiper-slide">
				<span class="reset">初始化</span>
			</div>
		</div>
	</div>
	<div class="bg-out"></div>
	<div id="changer-set">
		<i class="iconfont">&#xe696;</i>
	</div>
</div>
<!-- 背景切换结束 -->
</body>
</html>
<script>
	$(document).ready(function() {
		var date = new Date();
		var year = date.getFullYear();
		document.getElementById("year").innerHTML = year;
		if ($(".imde").val() == "true") {
			$(".testmode").css("display", "block");
		}
	});
</script>
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				 
				$.ajax({
					url : "${pageContext.servletContext.contextPath}/emitter/loadTransmit",
					type : "get",
					success : function(transmit) {
						for (var i = 0; i < transmit.length; i++) {
							$(".showtran").prepend(
									"<option value="+transmit[i].transmitId+">"
											+ transmit[i].tName + "</option>");
						}
					}
				});
			});
</script>

