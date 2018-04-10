<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../resource/resource.jsp"></jsp:include>
<blockquote style="font-size: 2em;">
    <h2>台站信息</h2><br>
    <select class="showtran layui-input" onchange="getTransmitById()"
            id="showtran"></select><input type="button"
                                          onclick="startscreen()" class="layui-btn" value="动态显示"
                                          id="srun"/>
    <hr>

    <div id="transmitInfodiv"
         style=" line-height: 1.5em;">
        <input type="text" hidden="hidden" id="antennaId">
        <p>
            <strong> 发射台名称： </strong><span id="tranName"></span>&nbsp;&nbsp;<strong>
            台站地区： </strong><span id="tranArea"></span>
        </p>
        <p>
            <strong> 经度： </strong><span id="trclongitude"></span>&nbsp;&nbsp;
            <strong> 纬度： </strong><span id="trclatitude"></span>
            &nbsp;&nbsp; <strong> 高度： </strong><span id="trcheight"></span>
        </p>
        <p>
            <strong> 天线型号： </strong> <span id="trantennaType"> </span>
            &nbsp;&nbsp; <strong> 节目源接引方案： </strong> <span
                id="trprogramSource"> </span>
        </p>
        <p>
            <strong> 天线场形： </strong> <span id="trfieldPattern"> </span>
            &nbsp;&nbsp; <strong> 天线安装设计： </strong> <span
                id="trinstallPlan"> </span>
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
            &nbsp;&nbsp; <strong> 塔桅结构： </strong> <span
                id="trtowerFramework"> </span>
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
            <strong> 供配电： </strong> <span id="trpowerDistribution">
									</span> &nbsp;&nbsp; <strong> 防雷接地： </strong> <span id="trgrounding">
									</span>
        </p>
        <p>
            <strong> 空调通风及采暖： </strong> <span id="trairConditioner">
									</span>
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

<script>
    $(document).ready(
        function() {

            $.ajax({
                url : "loadTransmit",
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
            url: "findByTransmitId",
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
                    url: "getchannel",
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
                    url: "getfrequency",
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