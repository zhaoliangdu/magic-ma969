package com.ma969.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ma969.service.TransmitStationService; 
import com.ma969.utils.OperationFileUtils;
import com.ma969.beans.Antenna;
import com.ma969.beans.Channel;
import com.ma969.beans.Channels;
import com.ma969.beans.Frequency;
import com.ma969.beans.Room;
import com.ma969.beans.StationPersonnel;
import com.ma969.beans.Tower;
import com.ma969.beans.TransmitStation;
import me.jor.util.Help;
import com.ma969.service.TransmitService;
import com.google.gson.Gson;
import com.ma969.beans.Transmit;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:18:12
 */
@Controller
public class TransmitStationController {
	@Autowired
	TransmitService transmitService;
	@Autowired
	TransmitStationService transmitStationService;
	private static final String TRANSMIT_PATH = "d://magic-ma969//temp//transmit//";

	/**
	 * 添加台站视图
	 * 
	 * @return
	 */
	@RequestMapping("addtransmitview")
	public ModelAndView addTransmitview() {
		List<Channels> getchannel = transmitStationService.listChannels();

		return new ModelAndView("jsp/transmitma/addtransmit").addObject("list", getchannel);
	}

	/**
	 * transmitName 台站管理视图
	 * 
	 * @return
	 */
	@RequestMapping("transmitmaview")
	@ResponseBody
	public ModelAndView transmitManage() {
		Map<Integer, String> map = new HashMap<>(16);
		transmitService.getTransmits().forEach(transmit -> {
			map.put(transmit.getTransmitId(), transmit.gettName());
		});
		return new ModelAndView("jsp/transmitma/transmitma").addObject("map", map).addObject("list",
				transmitStationService.listChannels());
	}

	/**
	 * 加载台站信息
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("loadTransmit")
	public void loadTransmit(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		OperationFileUtils.mkFiles();
		writer.println(new Gson().toJson(transmitService.getTransmits()));
		writer.flush();
		writer.close();
	}

	@RequestMapping("fullscreen")
	public ModelAndView fullScreen(){
		System.err.println("fullscreen");
		return new ModelAndView("jsp/fullscreen");
	}
	/**
	 * 根据id查找发射台
	 * 
	 * @throws IOException
	 */
	@RequestMapping("findByTransmitId")
	@ResponseBody
	public void findByTransmitId(@RequestParam("tid") int tid, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(new Gson().toJson(transmitStationService.findByTransmitId(tid)));
		writer.flush();
		writer.close();
	}

	/**
	 * 获取频道
	 * 
	 * @param aid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getchannel")
	@ResponseBody
	public void getChannel(@RequestParam("aid") int aid, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(new Gson().toJson(transmitStationService.getChannels(aid)));
		writer.flush();
		writer.close();
	}

	/**
	 * 获取频率
	 * 
	 * @param aid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getfrequency")
	@ResponseBody
	public void getFrequency(@RequestParam("aid") int aid, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(new Gson().toJson(transmitStationService.getFrequencys(aid)));
		writer.flush();
		writer.close();
	}

	/**
	 * 添加天线
	 * 
	 * @param response
	 * @param request
	 * @param map
	 * @throws IOException
	 */
	@RequestMapping("addantenna")
	@ResponseBody
	public void addAntenna(HttpServletResponse response, HttpServletRequest request,
			@RequestParam Map<String, Object> map) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		Antenna antenna = new Antenna((String) map.get("aantennaType"), (String) map.get("aprogramSource"),
				(String) map.get("afieldPattern"), (String) map.get("ainstallPlan"));
		PrintWriter writer = response.getWriter();
		List<Integer> list = new ArrayList<>();
		list.add(transmitStationService.addAntenna(antenna));
		list.add(antenna.getAntennaId());
		writer.println(new Gson().toJson(list));
		writer.flush();
		writer.close();
	}

	/**
	 * addChannel 添加频道
	 * 
	 * @param channel
	 * @param aId
	 * @throws IOException
	 */
	@RequestMapping("addchannel")
	@ResponseBody
	public void addChannel(@RequestParam("channel") String channel, @RequestParam("aid") int aId,
			HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		int addChannel = Help.isEmpty(transmitStationService.findByChannel(new Channel(aId, channel)))
				? transmitStationService.addChannel(new Channel(aId, channel)) : 0;
		writer.println(addChannel);
		writer.flush();
		writer.close();
	}

	/**
	 * 删除频道
	 * 
	 * @param channel
	 */
	@RequestMapping("delchannel")
	@ResponseBody
	public void delChannel(@RequestParam("channel") String channel, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println(transmitStationService.deleteChannel(channel));
		writer.flush();
		writer.close();
	}

	/**
	 * 添加频率
	 * 
	 * @param frequency
	 * @param aId
	 * @throws IOException
	 */
	@RequestMapping("addfrequency")
	@ResponseBody
	public void addFrequency(@RequestParam("frequency") Float frequency, @RequestParam("aid") int aId,
			HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		int addFrequency = !Help.isEmpty(frequency) ? transmitStationService.addFrequency(new Frequency(aId, frequency)) : 0;
		writer.println(addFrequency);
		writer.flush();
		writer.close();
	}

	/**
	 * 删除频率
	 * 
	 * @param frequency
	 * @throws IOException
	 */
	@RequestMapping("delfrequency")
	@ResponseBody
	public void delFrequency(@RequestParam("frequency") float frequency, HttpServletResponse response)
			throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println(transmitStationService.deleteFrequency(frequency));
		writer.flush();
		writer.close();
	}

	/**
	 * 添加发射台
	 * 
	 * @param addParam
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("addtransmit")
	public ModelAndView addTransmit(@RequestParam Map<String, String> addParam, HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String tName = addParam.get("atransmitName");
		String latitudeStr = addParam.get("alatitude");
		String longitudeStr = addParam.get("alongitude");
		String heightStr = addParam.get("aheight");
		String area = addParam.get("atransmitArea");
		String aIdStr = addParam.get("antennaId");
		String towerType = addParam.get("atowerType");
		String towerFramework = addParam.get("atowerFramework");
		String towerReport = addParam.get("atowerReport");
		String roomPosition = addParam.get("aroomPosition");
		String roomDeviceStatus = addParam.get("aroomDeviceStatus");
		String powerDistribution = addParam.get("apowerDistribution");
		String grounding = addParam.get("agrounding");
		String airConditioner = addParam.get("aairConditioner");
		String supervisor = addParam.get("asupervisor");
		String testPeople = addParam.get("atestPeople");
		String responsiblePerson = addParam.get("aresponsiblePerson");
		String username = addParam.get("username");
		try {
			int aid = Integer.parseInt(aIdStr);
			float latitude = Float.parseFloat(latitudeStr);
			float longitude = Float.parseFloat(longitudeStr);
			float height = Float.parseFloat(heightStr);
			Room room = new Room(roomPosition, roomDeviceStatus, powerDistribution, grounding, airConditioner);
			StationPersonnel stationPersonnel = new StationPersonnel(supervisor, testPeople, responsiblePerson);
			Tower tower = new Tower(towerType, towerFramework, towerReport);
			Transmit transmit = new Transmit(area, tName, latitude, longitude, height, LocalDateTime.now().toString(),
					username);
			transmitStationService.addTransmitStation(aid, room, stationPersonnel, tower, transmit);
		} catch (NumberFormatException e) {
			System.out.println("类型转换错误！");
			e.printStackTrace();
		}
		return transmitManage();
	}

	/**
	 * 根据天线id添加频道
	 * 
	 * @param response
	 * @param aId
	 * @param channel
	 * @throws IOException
	 */
	@RequestMapping("addChannelByantId")
	@ResponseBody
	public void addChannelByantId(HttpServletResponse response, @RequestParam("aId") int aId,
			@RequestParam("channel") String channel) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println(transmitStationService.addChannel(new Channel(aId, channel)));
		writer.flush();
		writer.close();
	}

	/**
	 * 根据天线id添加频率
	 * 
	 * @param response
	 * @param aId
	 * @param frequency
	 * @throws IOException
	 */
	@RequestMapping("addFrequencByaId")
	@ResponseBody
	public void addFrequencyByaId(HttpServletResponse response, @RequestParam("aId") int aId,
			@RequestParam("frequency") Float frequency) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println(transmitStationService.addFrequency(new Frequency(aId, frequency)));
		writer.flush();
		writer.close();
	}

	/**
	 * 删除发射台
	 * 
	 * @param tid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("deletetransmit")
	@ResponseBody
	public void deleteTransmit(@RequestParam("tid") int tid, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println(transmitStationService.deleteTransmitStation(tid) + "");
		writer.flush();
		writer.close();
	}

	/**
	 * 更新发射台信息
	 * 
	 * @param updateParam
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("updatetransmit")
	@ResponseBody
	public void updateTransmit(@RequestParam Map<String, String> updateParam, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("utf-8");
		String tidStr = updateParam.get("tId");
		String tName = updateParam.get("utransmitName");
		String area = updateParam.get("utransmitArea");
		String latitudeStr = updateParam.get("ulatitude");
		String longitudeStr = updateParam.get("ulongitude");
		String heightStr = updateParam.get("uheight");
		String antennaType = updateParam.get("uantennaType");
		String programSource = updateParam.get("uprogramSource");
		String fieldPattern = updateParam.get("ufieldPattern");
		String installPlan = updateParam.get("uinstallPlan");
		String towerType = updateParam.get("utowerType");
		String towerFramework = updateParam.get("utowerFramework");
		String towerReport = updateParam.get("utowerReport");
		String roomPosition = updateParam.get("uroomPosition");
		String roomDeviceStatus = updateParam.get("uroomDeviceStatus");
		String powerDistribution = updateParam.get("upowerDistribution");
		String grounding = updateParam.get("ugrounding");
		String airConditioner = updateParam.get("uairConditioner");
		String supervisor = updateParam.get("usupervisor");
		String testPeople = updateParam.get("utestPeople");
		String responsiblePerson = updateParam.get("uresponsiblePerson");
		String username = updateParam.get("username");
		float latitude = Float.parseFloat(latitudeStr);
		float longitude = Float.parseFloat(longitudeStr);
		float height = Float.parseFloat(heightStr);
		int tId = Integer.parseInt(tidStr);

		Antenna antenna = new Antenna(antennaType, programSource, fieldPattern, installPlan);
		Room room = new Room(roomPosition, roomDeviceStatus, powerDistribution, grounding, airConditioner);
		StationPersonnel stationPersonnel = new StationPersonnel(supervisor, testPeople, responsiblePerson);
		Tower tower = new Tower(towerType, towerFramework, towerReport);
		Transmit transmit = new Transmit(area, tName, longitude, latitude, height, LocalDateTime.now().toString(),
				username);
		int updateTransmitStation = transmitStationService.updateTransmitStation(tId, antenna, room, stationPersonnel,
				tower, transmit);
		PrintWriter writer = response.getWriter();
		writer.println(updateTransmitStation);
		writer.flush();
		writer.close();
	}

	/**
	 * 查询发射台
	 * 
	 * @param smap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("selecttransmit")
	@ResponseBody
	public void selectTransmit(@RequestParam Map<String, String> smap, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json;charset=utf-8");
		String tName = smap.get("stName");
		String latStr = smap.get("slat");
		String lonStr = smap.get("slon");
		String atype = smap.get("satype");
		String ttype = smap.get("sttype");
		String stName = new StringBuffer("%").append(tName).append("%").toString();
		String satype = new StringBuffer("%").append(atype).append("%").toString();
		String sttype = new StringBuffer("%").append(ttype).append("%").toString();
		Map<String, String> maps = new HashMap<>(16);
		maps.put("tName", stName);
		maps.put("lat", latStr);
		maps.put("lon", lonStr);
		maps.put("atype", satype);
		maps.put("ttype", sttype);
		List<TransmitStation> transmit = transmitStationService.findByParam(maps);
		List<Map<String, Object>> list = new ArrayList<>();
		if (!Help.isEmpty(transmit)) {
			transmit.forEach(tran -> {
				Map<String, Object> map = new HashMap<>(16);
				map.put("tId", tran.getTransmitId());
				map.put("tName", tran.gettName());
				list.add(map);
			});
			writer.println(new Gson().toJson(list));
		}
		writer.flush();
		writer.close();
	}

	/**
	 * 获取发射塔地址
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("gettranlocation")
	@ResponseBody
	public void getTransmitLocation(HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(new Gson().toJson(transmitStationService.findByParam(new HashMap<>(16))));
		writer.flush();
		writer.close();
	}

	/**
	 * 导入台站信息TXT
	 * 
	 * @param files
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("uploadTransmitTxt")
	public ModelAndView uploadTransmit(@RequestParam("transmittxt") CommonsMultipartFile[] files,
			HttpServletRequest request) throws IOException {
		boolean readFileToTransmit = transmitStationService.readFileToTransmit(files, TRANSMIT_PATH);
		if (readFileToTransmit) {
			request.getSession().setAttribute("tranmsg", "导入成功");
		} else {
			request.getSession().setAttribute("tranmsg", "导入失败");
		}
		return transmitManage();
	}

	/**
	 * 台站信息导出
	 * 
	 * @param type
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("exporttransmit")
	public ResponseEntity<byte[]> exportTransmit(@RequestParam("type") String type, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		List<TransmitStation> transmits = transmitStationService.listTransmitStation();
		HttpHeaders headers = new HttpHeaders();
		String excel = "excel";
		if (transmits != null) {
			String outpath = excel.equals(type) ? transmitStationService.exportTransmit(transmits, TRANSMIT_PATH)
					: transmitStationService.exportDataToTxt(transmits, TRANSMIT_PATH);
			File file = new File(outpath);
			if (file.exists()) { 
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment",
						new String(file.getName().getBytes("gb2312"), "iso8859-1"));
			}
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} else {
			request.getSession().setAttribute("tranmsg", "数据为空！");
			return new ResponseEntity<byte[]>(HttpStatus.OK);
		}
	}
}
