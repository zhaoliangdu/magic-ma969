<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 发射台  -->
<script type="text/javascript">
	//解析json数据
	function toJson(str) {
		return JSON.parse(str);
	}

	//添加天线成功显示添加频道频率
	function showcf() {
		if ($("#aantennaType").val() == '' || $("#aprogramSource").val() == ''
				|| $("#afieldPattern").val() == ''
				|| $("#ainstallPlan").val() == '') {
			alert("请填写完整天线信息！");
			return false;
		} else {
			$.ajax({
				url : "addantenna",
				type : "post",
				data : {
					"aantennaType" : $("#aantennaType").val(),
					"aprogramSource" : $("#aprogramSource").val(),
					"afieldPattern" : $("#afieldPattern").val(),
					"ainstallPlan" : $("#ainstallPlan").val()
				},
				success : function(val) {
					var addanten = val[0];
					var antennaId = val[1];
					$("#antennaId").val(antennaId);
					if (addanten == 1) {
						$("#chfre").show();
						$("#cfcontext").show();
						return;
					} else {
						alert("添加失败！");
					}
				}
			});
		}
	}

	function addaChannel() {
		var aId = $("#antennaId").val();
		 
		var channel = document.getElementById("achannels");
		var channelval = channel.options[channel.selectedIndex].value;
		var chans = document.getElementById("aschannel");
		$.ajax({
			url : "addChannelByantId",
			type : "post",
			data : {
				"aId" : aId,
				"channel" : channelval
			},
			success : function(val) {
				if (val == 1) {
					$("#aschannel").append(
							"<option>" + channelval + "</option>");
				}
			}
		});
	}
	function adelChannel() {
		var channel = document.getElementById("aschannel");
		var channelval = channel.options[channel.selectedIndex].value;
		var index = channel.selectedIndex;
		$.ajax({
			url : "delchannel",
			type : "post",
			data : {
				"channel" : channelval
			},
			success : function(val) {
				if (val == 1) {
					channel.options.remove(index);
				}
			}
		});
	}
	function addaFrequency() {
		var aId = $("#antennaId").val();
		var frequency = $("#afrequency").val();
		$.ajax({
			url : "addFrequencByaId",
			type : "post",
			data : {
				"aId" : aId,
				"frequency" : frequency
			},
			success : function(val) {
				if (val == 1) {
					$("#asfrequency").append(
							"<option>" + frequency + "</option>");
				}
			}
		});
	}
	function adelFrequency() {
		var frequency = document.getElementById("asfrequency");
		var frequencyval = frequency.options[frequency.selectedIndex].value;
		var index = frequency.selectedIndex;
		$.ajax({
			url : "delfrequency",
			type : "post",
			data : {
				"frequency" : frequencyval
			},
			success : function(val) {
				if (val == 1) {
					frequency.options.remove(index);
				}
			}
		});
	}
	//根据条件查询发射台
	function selectTransmit() {
		$("#transmitS").empty();
		$.ajax({
			url : "selecttransmit",
			type : "get",
			data : {
				"stName" : $("#stName").val(),
				"slat" : $("#slat").val(),
				"slon" : $("#slon").val(),
				"satype" : $("#satype").val(),
				"sttype" : $("#sttype").val()
			},
			success : function(tran) {
				if (tran !== undefined) {
					for (var i = 0; i < tran.length; i++) {
						$("#transmitS").prepend(
								"<option value='"+tran[i].tId+"'>"
										+ tran[i].tName + "</option>");
					}
				} else {
					alert("您要查找的信息不存在！");
				}
			}
		});
	}

	//添加频道
	function addChannel() {

		var channel = document.getElementById("channels");
		var channelval = channel.options[channel.selectedIndex].value;
		var chans = document.getElementById("schannel");
		var aid = $("#aid").val(); 
		$.ajax({
			url : "addchannel",
			type : "post",
			data : {
				"aid" : aid,
				"channel" : channelval
			},
			success : function(val) { 
				if (val == 1) {
					$("#schannel")
							.append("<option>" + channelval + "</option>");
				}
			}
		});
	}
	//添加频率
	function addFrequency() {
		var aid = $("#aid").val();
		var frequency = $("#frequency").val();
		$.ajax({
			url : "addfrequency",
			type : "post",
			data : {
				"aid" : aid,
				"frequency" : frequency
			},
			success : function(val) {
				if (val == 1) {
					$("#sfrequency").append(
							"<option>" + frequency + "</option>");
				}
			}
		});
	}
	var auth = $("#userauth").val();
	//删除频道
	function delChannel() {
		var channel = document.getElementById("schannel");
		var channelval = channel.options[channel.selectedIndex].value;
		var index = channel.selectedIndex;
		if (auth > 0) {
			$.ajax({
				url : "delchannel",
				type : "post",
				data : {
					"channel" : channelval
				},
				success : function(val) {
					if (val == 1) {
						channel.options.remove(index);
					}
				}
			});
		} else {
			alert("您没有操作权限");
			return false;
		}

	}
	//删除频率
	function delFrequency() {

		var sfre = document.getElementById("sfrequency");
		var frequency = sfre.options[sfre.selectedIndex].value;
		var index = sfre.selectedIndex;
		if (auth > 0) {

			$.ajax({
				url : "delfrequency",
				type : "post",
				data : {
					"frequency" : frequency
				},
				success : function(val) {
					if (val == 1) {
						sfre.options.remove(index);
					}
				}
			});
		} else {
			alert("您没有操作权限！");
			return false;
		}
	}
	//更新发射台
	function updateTransmit() {
		var tran = document.getElementById("transmitS");
		var trId = tran.options[tran.selectedIndex].value;
		var auth = $("#userauth").val();
		if (auth >= 1) {
			$.ajax({
				url : "updatetransmit",
				type : "post",
				data : {
					"tId" : trId,
					"utransmitArea" : $("#transmitArea").val(),
					"utransmitName" : $("#transmitName").val(),
					"ulatitude" : $("#clatitude").val(),
					"ulongitude" : $("#clongitude").val(),
					"uheight" : $("#height").val(),
					"uantennaType" : $("#antennaType").val(),
					"uprogramSource" : $("#programSource").val(),
					"ufieldPattern" : $("#fieldPattern").val(),
					"uinstallPlan" : $("#installPlan").val(),
					"utowerType" : $("#towerType").val(),
					"utowerFramework" : $("#towerFramework").val(),
					"utowerReport" : $("#towerReport").val(),
					"uroomPosition" : $("#roomPosition").val(),
					"uroomDeviceStatus" : $("#roomDeviceStatus").val(),
					"upowerDistribution" : $("#powerDistribution").val(),
					"ugrounding" : $("#grounding").val(),
					"uairConditioner" : $("#airConditioner").val(),
					"usupervisor" : $("#supervisor").val(),
					"utestPeople" : $("#testPeople").val(),
					"uresponsiblePerson" : $("#responsiblePerson").val(),
					"username" : $("#username").val()
				},
				success : function(val) {
					if (val == 1) {
						alert("更新成功！");
						location.reload();
					} else {
						alert("更新失败");
					}
				}
			});
		} else {
			alert("您不是管理员不能进行操作");
			return false;
		}
	}
	//删除发射台
	function deleteTransmit() {
		var auth = $("#userauth").val();
		if (auth == 2) {
			if (window.confirm("确定删除吗！")) {
				var tran = document.getElementById("transmitS");
				var trId = tran.options[tran.selectedIndex].value;
				if (trId == null) {
					return false;
				} else {
					//删除发射台
					$.ajax({
						url : "deletetransmit",
						type : "post",
						data : {
							"tid" : trId
						},
						success : function(val) {
							if (val == 1) {
								location.href = "transmitmaview";
								alert("删除成功");
							} else {
								alert("删除失败！");
							}
						}
					});
				}
			} else {
				return false;
			}
		} else {
			alert("您不是管理员不能进行操作");
			return false;
		}
	}

	//根据发射台id加载发射台 和频道频率
	function getTransmitId() {

		var tran = document.getElementById("transmitS");
		var trId = tran.options[tran.selectedIndex].value;
		//根据发射台id加载发射台信息
		$.ajax({
			url : "findByTransmitId",
			type : "get",
			data : {
				"tid" : trId
			},
			success : function(tran) {
				$("#schannel").empty();
				$("#sfrequency").empty();
				$("#transmitArea").val(tran.area);
				$("#towerType").val(tran.towerType);
				$("#towerFramework").val(tran.towerFramework);
				$("#towerReport").val(tran.towerReport);
				$("#transmitName").val(tran.tName);
				$("#clatitude").val(tran.latitude);
				$("#clongitude").val(tran.longitude);
				$("#height").val(tran.height);
				$("#aid").val(tran.aId);
				$("#antennaType").val(tran.antennaType);
				$("#programSource").val(tran.programSource);
				$("#fieldPattern").val(tran.fieldPattern);
				$("#installPlan").val(tran.installPlan);
				$("#supervisor").val(tran.supervisor);
				$("#testPeople").val(tran.testPeople);
				$("#responsiblePerson").val(tran.responsiblePerson);
				$("#roomPosition").val(tran.roomPosition);
				$("#roomDeviceStatus").val(tran.roomDeviceStatus);
				$("#powerDistribution").val(tran.powerDistribution);
				$("#grounding").val(tran.grounding);
				$("#airConditioner").val(tran.airConditioner);
				$("#lastupt").text(
						"最后操作人："
								+ tran.lastUpName
								+ ",最后更新时间："
								+ tran.lastUpt.substring(0,
										(tran.lastUpt.length - 2)));

				//加载频道列表
				$
						.ajax({
							url : "getchannel",
							type : "get",
							data : {
								"aid" : tran.aId
							},
							success : function(channel) {
								for (var i = 0; i < channel.length; i++) {
									$("#schannel").prepend(
											"<option value='"+channel [i].channel+"'>"
													+ channel[i].channel
													+ "</option>");
								}
							}
						});
				//加载频率列表
				$.ajax({
					url : "getfrequency",
					type : "get",
					data : {
						"aid" : tran.aId
					},
					success : function(freq) {
						for (var i = 0; i < freq.length; i++) {
							$("#sfrequency").prepend(
									"<option value='"+freq[i].frequency+"'>"
											+ freq[i].frequency + "</option>");
						}
					}
				});
			}
		});
	}
</script>
<script type="text/javascript">
	function aadChannel() {
		var channels = document.getElementById("achannels");
		var channel = channels.options[channels.selectedIndex].value;

		$("#aschannel").append("_$tag___" + channel + "_$tag____");
	}
	function aadfrequency() {
		var frequency = $("#afrequency").val();
		$("#asfrequency").append("_$tag___" + fequency + "_$tag____");
	}
	//解析json
	function toJson(str) {
		return JSON.parse(str);
	}
	//加载发射台名称
	$(document).ready(
			function() {
				$.ajax({
					url : "loadTransmit",
					type : "get",
					success : function(transmit) {
						for (var i = 0; i < transmit.length; i++) {
							$("#transmitName").prepend(
									"_$tag____________________________________"
											+ transmit[i].tName + "_$tag____");
						}
					}
				});

			});

	//发射台面板动态显示信息
	function getTransmitById() {

		var tran = document.getElementById("transmitName");
		var trId = tran.options[tran.selectedIndex].value;
		$("#trchannel").empty();
		$("#trfrequency").empty();
		//加载动态发射台面板
		$.ajax({
			url : "findByTransmitId",
			type : "get",
			data : {
				"tid" : trId
			},
			success : function(tran) {
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
					url : "getchannel",
					type : "get",
					data : {
						"aid" : tran.aId
					},
					success : function(channel) {
						if (channel !== undefined) {
							for (var i = 0; i < channel.length; i++) {
								if (i % 2 == 0) {
									$("#trchannel").prepend(
											"_$tag_" + channel[i].channel + " "
													+ "_$tag___$ta");
									$("#ftrchannel").prepend(
											"_$tag_" + channel[i].channel + " "
													+ "_$tag___$ta");
								} else {
									$("#trchannel").prepend(
											"_$tag_" + channel[i].channel + " "
													+ "_$tag__");
									$("#ftrchannel").prepend(
											"_$tag_" + channel[i].channel + " "
													+ "_$tag__");
								}
							}
						}
					}
				});
				$.ajax({
					url : "getfrequency",
					type : "get",
					data : {
						"aid" : tran.aId
					},
					success : function(frequency) {
						if (frequency !== null || frequency !== undefined) {
							var freq = toJson(frequency);
							for (var i = 0; i < freq.length; i++) {
								if (i % 2 == 0) {
									$("#trfrequency").prepend(
											"_$tag_" + freq[i].frequency + " "
													+ "_$tag___$ta");
									$("#tfrfrequency").prepend(
											"_$tag_" + freq[i].frequency + " "
													+ "_$tag___$ta");
								} else {
									$("#trfrequency").prepend(
											"_$tag_" + freq[i].frequency + " "
													+ "_$tag__");
									$("#tfrfrequency").prepend(
											"_$tag_" + freq[i].frequency + " "
													+ "_$tag__");
								}
							}
						}
					}
				});
			}
		});
	}
</script>