<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="main.jsp"></jsp:include>
<script type="text/javascript"
        src="http://webapi.amap.com/maps?v=1.4.0&key=100ac2f2d36ccc13f62f1f86617ca9e3"></script>
<script type='text/javascript'
        src='https://www.bing.com/api/maps/mapcontrol?branch=experimental&callback=loadMapScenario'
        async defer></script>
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="content">
        <!-- 右侧内容框架，更改从这里开始 -->
        <blockquote class="layui-elem-quote">
            欢迎使用宝盈科技车载移动数据云端在线管理系统和数据分析管理软件【MA969YDYZM-S】<span class="f-14">V1.0</span>&nbsp;&nbsp;
            <span style="color: red;">本平台尽量使用谷歌浏览器打开，IE可能导致某些功能不能使用</span>
        </blockquote>
        <blockquote class="layui-elem-quote">注册时间：${createtime }&nbsp;&nbsp;
            上次登录时间：${lastLoginTime }&nbsp;&nbsp;IP：<span style="color:#00b7ee;">${ipAddr }</span></blockquote>
        <fieldset class="layui-elem-field layui-field-title site-title">
            <legend>
                <a name="default">采样点信息统计</a>
            </legend>
        </fieldset>
        <table class="layui-table">
            <thead>
            <tr>
                <th>数据模式</th>
                <th>采样点数量</th>
                <th>地区</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="points" items="${pointstatistics }">
                <tr>
                    <td><a href="${points.url }">${points.testmode }</a></td>
                    <td>${points.count }</td>
                    <td>${points.areas }</td>
                    <td><a href="#" onclick="addpoints(${points.testmodeId})">显示</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="min-width: 1000px;">
            <div
                    style="float: left; width: 20%; min-width: 200px; height: 800px; overflow-y: scroll;">
                <blockquote>
                    <select class="showtran layui-input" onchange="getTransmitById()"
                            id="showtran"></select>&nbsp;<br>
                    <input type="button" onclick="startscreen()" class="layui-btn"
                           value="动态显示" id="srun"/><input type="button" class="layui-btn "
                                                          value="全屏"
                                                          onclick="fullscreen('台站信息','${pageContext.servletContext.contextPath}/emitter/fullscreen','1900','950')"/>&nbsp;&nbsp;<input
                        type="checkbox" onclick="showTransmitByMap()" id="traninmap"
                        lay-skin="primary"/>&nbsp;显示台站
                    <hr>
                    <div id="transmitInfodiv"
                         style="font-size: 15px; line-height: 20px;">
                        <input type="text" hidden="hidden" id="antennaId">
                        <p>
                            <strong> 发射台名称： </strong><span id="tranName"></span>&nbsp;&nbsp;<strong>
                            台站地区： </strong><span id="tranArea"></span>
                        </p>
                        <p>
                            <strong> 经度： </strong><span id="trclongitude"></span>&nbsp;&nbsp;
                            <strong> 纬度： </strong><span id="trclatitude"></span> &nbsp;&nbsp;
                        </p>
                        <p>
                            <strong> 高度： </strong><span id="trcheight"></span>
                        </p>
                        <p>
                            <strong> 天线型号： </strong> <span id="trantennaType"> </span>
                            &nbsp;&nbsp; <strong> 节目源接引方案： </strong> <span
                                id="trprogramSource"> </span>
                        </p>
                        <p>
                            <strong> 天线场形： </strong> <span id="trfieldPattern"> </span>
                            &nbsp;&nbsp; <strong> 天线安装设计： </strong> <span id="trinstallPlan">
							</span>
                        </p>
                        <p>
                            <strong> 天线频道： </strong> <b id="trchannel"> </b>
                        </p>
                        <p>
                            <strong> 天线频率： </strong> <b id="trfrequency"> </b>
                        </p>
                        <p>
                            <strong> 塔桅配置 </strong>
                        </p>
                        <p>
                            <strong> 塔桅型号： </strong> <span id="trtowerType"> </span>
                            &nbsp;&nbsp; <strong> 塔桅结构： </strong> <span id="trtowerFramework">
							</span>
                        </p>
                        <p>
                            <strong> 勘察报告： </strong> <span id="trtowerReport"> </span>
                        </p>
                        <p>
                            <strong> 机房配置 </strong>
                        </p>
                        <p>
                            <strong> 机房位置： </strong> <span id="trroomPosition"> </span>
                            &nbsp;&nbsp; <strong> 设备状态： </strong> <span
                                id="trroomDeviceStatus"> </span>
                        </p>
                        <p>
                            <strong> 供配电： </strong> <span id="trpowerDistribution"> </span>
                            &nbsp;&nbsp; <strong> 防雷接地： </strong> <span id="trgrounding">
							</span>
                        </p>
                        <p>
                            <strong> 空调通风及采暖： </strong> <span id="trairConditioner"> </span>
                        </p>
                        <p>
                            <strong> 人员配置 </strong>
                        </p>
                        <p>
                            <strong> 负责人： </strong> <span id="trsupervisor"> </span>
                            &nbsp;&nbsp; <strong> 测试人员： </strong> <span id="trtestPeople">
							</span>
                        </p>
                        <p>
                            <strong> 负责人制度： </strong> <span id="trresponsiblePerson">
							</span>
                        </p>
                    </div>
                </blockquote>
            </div>
            <div id="baidumap"
                 style="width: 80%; min-height: 800px; min-width: 800px; float: left;">
            </div>
        </div>
        <br>
        <!-- 右侧内容框架，更改从这里结束 -->
    </div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<jsp:include page="bottom.jsp"></jsp:include>

<script>
    function fullscreen(title, url, w, h) {
        //页面层
        x_admin_show(title, url, w, h);
    }

    //滚动播放发射台站
    var i = 0;
    var t;

    function autoSelect() {
        var optlist = document.getElementById("showtran");
        if (i < optlist.length) {
            optlist.selectedIndex = i;
        } else {
            i = -1;
        }
        i++;
        t = setTimeout("autoSelect()", 3000);
    }

    //打开关闭是否滚动播放
    flag = true;
    var b;

    function startscreen() {
        txt = $("#srun").val();
        switch (txt) {
            case "动态显示":
                $("#srun").val("暂停");
                autoSelect();
                b = setInterval("getTransmitById()", 900);
                break;
            case "暂停":
                $("#srun").val("动态显示");
                clearInterval(b);
                clearTimeout(t);
                break;
        }
    }

    function getTransmitById() {
        var tran = document.getElementById("showtran");
        var trId = tran.options[tran.selectedIndex].value;
        $("#trchannel").empty();
        $("#trfrequency").empty();
        //加载动态发射台面板
        $.ajax({
            url: "${pageContext.servletContext.contextPath}/emitter/findByTransmitId",
            type: "get",
            data: {
                "tid": trId
            },
            success: function (tran) {
                $("#antennaId").val(tran.aId);
                $("#tranName").text(tran.tName);
                $("#trtowerType").text(tran.towerType);
                $("#trtowerFramework").text(tran.towerFramework);
                $("#trtowerReport").text(tran.towerReport);
                $("#tranArea").text(tran.area);
                $("#trtransmitName").text(tran.tName);
                $("#trclatitude").text(tran.latitude);
                $("#trclongitude").text(tran.longitude);
                $("#trcheight").text(tran.height);
                $("#trantennaType").text(tran.antennaType);
                $("#trprogramSource").text(tran.programSource);
                $("#trfieldPattern").text(tran.fieldPattern);
                $("#trinstallPlan").text(tran.installPlan);
                $("#trsupervisor").text(tran.supervisor);
                $("#trtestPeople").text(tran.testPeople);
                $("#trresponsiblePerson").text(tran.responsiblePerson);
                $("#trroomPosition").text(tran.roomPosition);
                $("#trroomDeviceStatus").text(tran.roomDeviceStatus);
                $("#trpowerDistribution").text(tran.powerDistribution);
                $("#trgrounding").text(tran.grounding);
                $("#trairConditioner").text(tran.airConditioner);

                $("#fantennaId").val(tran.aId);
                $("#ftranName").text(tran.tName);
                $("#ftrtowerType").text(tran.towerType);
                $("#ftrtowerFramework").text(tran.towerFramework);
                $("#ftrtowerReport").text(tran.towerReport);
                $("#ftranArea").text(tran.area);
                $("#ftrtransmitName").text(tran.tName);
                $("#ftrclatitude").text(tran.latitude);
                $("#ftrclongitude").text(tran.longitude);
                $("#ftrcheight").text(tran.height);
                $("#ftrantennaType").text(tran.antennaType);
                $("#ftrprogramSource").text(tran.programSource);
                $("#ftrfieldPattern").text(tran.fieldPattern);
                $("#ftrinstallPlan").text(tran.installPlan);
                $("#ftrsupervisor").text(tran.supervisor);
                $("#ftrtestPeople").text(tran.testPeople);
                $("#ftrresponsiblePerson").text(tran.responsiblePerson);
                $("#ftrroomPosition").text(tran.roomPosition);
                $("#ftrroomDeviceStatus").text(tran.roomDeviceStatus);
                $("#ftrpowerDistribution").text(tran.powerDistribution);
                $("#ftrgrounding").text(tran.grounding);
                $("#ftrairConditioner").text(tran.airConditioner);

                $.ajax({
                    url: "${pageContext.servletContext.contextPath}/emitter/getchannel",
                    type: "get",
                    data: {
                        "aid": tran.aId
                    },
                    success: function (channel) {
                        $("#ftrchannel").empty();
                        if (channel !== undefined) {
                            for (var i = 0; i < channel.length; i++) {
                                if (i % 2 == 0) {
                                    $("#trchannel").prepend(
                                        "<span>" + channel[i].channel + " "
                                        + "</span><br>");
                                    $("#ftrchannel").prepend(
                                        "<span>" + channel[i].channel + " "
                                        + "</span><br>");
                                } else {
                                    $("#trchannel").prepend(
                                        "<span>" + channel[i].channel + " "
                                        + "</span>");
                                    $("#ftrchannel").prepend(
                                        "<span>" + channel[i].channel + " "
                                        + "</span>");
                                }
                            }
                        }

                    }
                });

                $.ajax({
                    url: "${pageContext.servletContext.contextPath}/emitter/getfrequency",
                    type: "get",
                    data: {
                        "aid": tran.aId
                    },
                    success: function (freq) {
                        $("#tfrfrequency").empty();
                        if (freq !== null || freq !== undefined) {
                            for (var i = 0; i < freq.length; i++) {
                                if (i % 2 == 0) {
                                    $("#trfrequency").prepend(
                                        "<span>" + freq[i].frequency + " "
                                        + "</span><br>");
                                    $("#tfrfrequency").prepend(
                                        "<span>" + freq[i].frequency + " "
                                        + "</span><br>");
                                } else {
                                    $("#trfrequency").prepend(
                                        "<span>" + freq[i].frequency + " "
                                        + "</span>");
                                    $("#tfrfrequency").prepend(
                                        "<span>" + freq[i].frequency + " "
                                        + "</span>");
                                }
                            }
                        }
                    }
                });
            }
        });
    }
</script>
<script>
    // 初始化百度地图
    var bdmap = new BMap.Map("baidumap", {
        mapType: BMAP_HYBRID_MAP
    });
    bdmap.centerAndZoom(new BMap.Point(105.200, 37.700), 5);
    bdmap.enableScrollWheelZoom(true);
    bdmap.enableInertialDragging();
    bdmap.addControl(new BMap.MapTypeControl()); //添加地图类型控件
    bdmap.enableContinuousZoom();
    //城市列表
    var size = new BMap.Size(10, 20);
    bdmap.addControl(new BMap.CityListControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: size,
    }));
    //鼠标移动显示经纬度
    bdmap.addEventListener("mousemove", function (e) {
        $("#lnglat").text(
            "经度：" + e.point.lng.toFixed(4) + ",纬度："
            + e.point.lat.toFixed(4));
    });
    $("<span id='lnglat'></span>").appendTo("#baidumap");
    var bottom_right_control = new BMap.ScaleControl({
        anchor: BMAP_ANCHOR_BOTTOM_RIGHT
    });// 右下角，添加比例尺

    bdmap.addControl(bottom_right_control);

    var markers = new Array();
    var labels = new Array();
    var content = new Array();

    function showTransmitByMap() {
        var opts = {
            width: 250, // 信息窗口宽度
            height: 150, // 信息窗口高度
            title: "台站信息", // 信息窗口标题
            enableMessage: true
            //设置允许信息窗发送短息
        };
        var tranck = document.getElementById("traninmap").checked;
        if (tranck) {
            $.ajax({
                url: "${pageContext.servletContext.contextPath}/emitter/gettranlocation",
                type: "post",
                success: function (tran) {
                    for (var i = 0; i < tran.length; i++) {
                        markers[i] = new BMap.Marker(new BMap.Point(
                            tran[i].longitude, tran[i].latitude));
                        bdmap.addOverlay(markers[i]);
                        labels[i] = new BMap.Label(tran[i].tName, {
                            offset: new BMap.Size(20, -10)
                        });
                        labels[i].setStyle({
                            maxWidth: "none"
                        });
                        markers[i].setLabel(labels[i]);
                        content[i] = "地区：" + tran[i].area + "<br>" + "经度："
                            + tran[i].longitude + ",纬度：" + tran[i].latitude
                            + "<br>" + "高度：" + tran[i].height + "<br>"
                            + "负责人：" + tran[i].supervisor + "<br>"
                            + "测试人员：" + tran[i].testPeople;
                        addClickHandler(content[i], markers[i]);
                    }

                    function addClickHandler(content, marker) {
                        marker.addEventListener("click", function (e) {
                            openInfo(content, e)
                        });
                    }

                    function openInfo(content, e) {
                        var p = e.target;
                        var point = new BMap.Point(p.getPosition().lng, p
                            .getPosition().lat);
                        var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象
                        bdmap.openInfoWindow(infoWindow, point); //开启信息窗口
                    }
                }
            });
        } else {
            var allOverlay = bdmap.getOverlays();
            for (var i = 0; i < allOverlay.length; i++) {
                bdmap.removeOverlay(allOverlay[i]);
            }
        }
    }

    function addpoints(testmodeId){
        //定义海量点集合
        var pointCollections = new Array();
	  
        $("#loadingTip").remove();
        $("<div id='loadingTip' >加载数据，请稍候...</div>").appendTo($("#baidumap"));
        var allOverlay = bdmap.getOverlays();
        for (var i = 0; i < allOverlay.length; i++) {
            bdmap.removeOverlay(allOverlay[i]);
        }

        var opts = {
            width : 250, // 信息窗口宽度
            height : 280, // 信息窗口高度
            title : "采样点详细信息", // 信息窗口标题
        };
        var options = {
            size : BMAP_POINT_SIZE_BIGGER,
            shape : BMAP_POINT_SHAPE_CIRCLE,
        }

        $
            .ajax({
                url : "${pageContext.servletContext.contextPath}/datapoint/getpoints",
                type : "post",
                data : {
                    "uid" : $("#uid").val(),
                    "testModeId" : testmodeId,
                    "typeId" : 0
                },
                success : function(val) {
					 
                    $("#loadingTip").remove();
                    $(
                        "<div id='loadingTip'  style='background-color:#6699ff'>加载完成！</div>")
                        .appendTo($("#baidumap"));
                    if (document.createElement('canvas').getContext) {
                        var pointss = new Array(val[0].length);
                        for (var i = 0; i < pointss.length; i++) {
                            pointss[i] = [];
                        }
                        var pointvals = new Array();
                        for (var i = 0; i < val[1].length; i++) {
                            pointvals[i] = new BMap.Point(val[1][i].lon,
                                val[1][i].lat);
                            for (var j = 0; j < val[0].length; j++) {

                                    if (val[1][i].field >= parseInt(val[0][j]
                                            .split("-")[0])
                                        && val[1][i].field < parseInt(val[0][j]
                                            .split("-")[1])) {
                                        pointss[j].push(pointvals[i]);
                                    }

                            }
                        }

                        var pointCollection;
                        for (var i = 0; i < pointss.length; i++) {
                            var array = [];
                            array = pointss[i];
                            var options = {
                                size : BMAP_POINT_SIZE_BIG,
                                shape : BMAP_POINT_SHAPE_CIRCLE,
                                color : val[2][i]
                            }
                            pointCollectioni = new BMap.PointCollection(
                                pointss[i], options);
                            //点击采样点显示详细信息
                            pointCollectioni.addEventListener('click',
                                function(e) {
                                    var infoSample = getSampleInfo(
                                        e.point.lng, e.point.lat);
                                    openInfo(infoSample, e.point.lng,
                                        e.point.lat);
                                });
                            pointCollections[i] = pointCollectioni;
                            bdmap.addOverlay(pointCollectioni); // 添加Overlay

                            function openInfo(content, lng, lat) {
                                var point = new BMap.Point(lng, lat);
                                var infoWindow = new BMap.InfoWindow(
                                    content, opts); // 创建信息窗口对象
                                bdmap.openInfoWindow(infoWindow, point); //开启信息窗口
                            }
                        }
                        //根据经纬度加载采样点信息
                        function getSampleInfo(lng, lat) {
                            for (var i = 0; i < val[1].length; i++) {
                                var everyLng = val[1][i].lon;
                                var everyLat = val[1][i].lat;
                                var point = new BMap.Point(lng, lat);
                                var pointEvery = new BMap.Point(everyLng,
                                    everyLat);
                                if (pointEvery.equals(point)) {
                                    var sampleInfo;
                                    if (testmodeId == 1) {
                                        sampleInfo = "<br>时间："
                                            + val[1][i].time
                                                .substring(
                                                    0,
                                                    (val[1][i].time.length) - 2)
                                            + "<br>频率："
                                            + val[1][i].frequency
                                            + "(MHz)<br>经度："
                                            + val[1][i].lon + "<br>纬度："
                                            + val[1][i].lat
                                            + "<br>场强值："
                                            + val[1][i].field
                                            + "<br>误包率："
                                            + val[1][i].ldpc
                                            + "<br>信噪比："
                                            + val[1][i].snr + "<br>高度："
                                            + val[1][i].height
                                            + "<br>距离："
                                            + val[1][i].distance
                                            + "<br>方位角："
                                            + val[1][i].angle;
                                    } else {
                                        sampleInfo = "<br>时间："
                                            + val[1][i].time
                                                .substring(
                                                    0,
                                                    (val[1][i].time.length) - 2)
                                            + "<br>频率："
                                            + val[1][i].frequency
                                            + "(MHz)<br>经度："
                                            + val[1][i].lon + "<br>纬度："
                                            + val[1][i].lat
                                            + "<br>场强值："
                                            + val[1][i].field
                                            + "<br>信噪比："
                                            + val[1][i].snr + "<br>高度："
                                            + val[1][i].height
                                            + "<br>距离："
                                            + val[1][i].distance
                                            + "<br>方位角："
                                            + val[1][i].angle;
                                    }
                                    return sampleInfo;
                                }
                            }
                        }
                        function delpoint(time) {
                            alert(time)
                        }
                    } else {
                        alert('请在chrome、safari、IE8+以上浏览器查看本示例');
                    }
                }
            });
    }
</script>
