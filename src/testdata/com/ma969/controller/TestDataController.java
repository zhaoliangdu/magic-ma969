/**
 * 数据-综合
 */
package com.ma969.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
import com.google.gson.Gson;
import com.ma969.service.SystemSetService;
import com.ma969.service.TestDataService;
import com.ma969.service.TransmitService;
import com.ma969.utils.DataBaseBackup;
import com.ma969.beans.TvSignal;
import com.ma969.service.AnalogDataService;
import com.ma969.service.CDRDataService;
import com.ma969.service.DigitalDataService;
import com.ma969.service.RadioDataService;

/**
 *
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:50:10
 */
@Controller
@RequestMapping("datapoint")
public class TestDataController {
	@Autowired
	SystemSetService systemService;
	@Autowired
	TransmitService transmitService;
	@Autowired
	RadioDataService radioDataService;
	@Autowired
	DigitalDataService digitalDataService;
	@Autowired
	CDRDataService cdrDataService;
	@Autowired
	AnalogDataService analogDataService;
	@Autowired
	TestDataService testdataService;

	/**
	 * 数据导入页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("datamanageview")
	public ModelAndView importData(HttpServletRequest request) {
		String auth = "auth";
		if ((int) request.getSession().getAttribute(auth) >= 1) {
			return new ModelAndView("jsp/data/datamanage").addObject("ismode", "false");
		} else {
			return new ModelAndView("jsp/data/datamanage").addObject("ismode", "true");
		}
	}

	/**
	 * 数据可视化页面
	 * 
	 * @return
	 */
	@RequestMapping("dataview")
	public ModelAndView dataView() {
		List<String> areas = digitalDataService.listAreas();
		areas.addAll(radioDataService.listAreas());
		areas.addAll(cdrDataService.listAreas());
		areas.addAll(analogDataService.listAreas());
		return new ModelAndView("jsp/data/dataview").addObject("areas", areas);
	}

	/**
	 * 电视分析仪视图
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("tvsignalview")
	public ModelAndView tvsignal(HttpServletRequest request) {
		return new ModelAndView("jsp/data/tvsignal").addObject("tvsignal", testdataService.listTVSignals());
	}

	@RequestMapping("add-tvsignal")
	public ModelAndView addTvsignal() {
		return new ModelAndView("jsp/data/add-tvsignal");
	}

	/**
	 * 添加电视分析仪数据
	 * 
	 * @param tvSignal
	 * @param request
	 * @return
	 */
	@RequestMapping("addtvsignal")
	public ModelAndView addTVSignal(TvSignal tvSignal, HttpServletRequest request) {
		if (tvSignal != null) {
			if (testdataService.saveTVSignal(tvSignal) == 1) {
				return new ModelAndView("jsp/data/add-tvsignal").addObject("dmsg", "添加成功");
			} else {
				return new ModelAndView("jsp/data/add-tvsignal").addObject("dmsg", "添加成功");
			}
		} else {
			return new ModelAndView("jsp/data/add-tvsignal").addObject("dmsg", "不能为空");
		}
	}

	/**
	 * 根据Id查看电视分析仪
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("edit-tvsignal")
	public ModelAndView findTVSignalById(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
		TvSignal tvsignal = testdataService.findTVSignalById(id);
		return new ModelAndView("jsp/data/edit-tvsignal").addObject("tvsignal", tvsignal);
	}

	/**
	 * 更新
	 * 
	 * @param tvSignal
	 * @param request
	 * @return
	 */
	@RequestMapping("updatetvsignal")
	public ModelAndView updateTVSignal(TvSignal tvSignal, HttpServletRequest request) {
		if (tvSignal != null) {
			if (testdataService.updateTVSignal(tvSignal) == 1) {
				return new ModelAndView("jsp/data/edit-tvsignal").addObject("dmsg", "修改成功");
			} else {
				return new ModelAndView("jsp/data/edit-tvsignal").addObject("dmsg", "修改失败");
			}
		} else {
			return new ModelAndView("jsp/data/edit-tvsignal").addObject("dmsg", "不能为空");
		}
	}

	/**
	 * 删除电视信号分析
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("deltvsignal")
	@ResponseBody
	public void delTVSignal(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println(testdataService.deleteTVSignal(id) + "");
		writer.flush();
		writer.close();
	}

	/**
	 * 获取发射台名称
	 * 
	 * @param lng
	 * @param lat
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("gettransmitid")
	@ResponseBody
	public void getTransmitName(@RequestParam("longitude") float lng, @RequestParam("latitude") float lat,
			HttpServletResponse response) throws IOException {
		response.setContentType("application/josn;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		Map<String, Float> map = new HashMap<>(16);
		map.put("longitude", lng);
		map.put("latitude", lat);
		writer.print(transmitService.getTransmitName(map));
		writer.flush();
		writer.close();
	}

	/**
	 * 读取文件导入数据库
	 *
	 * @param area
	 * @param testmode
	 * @param file
	 * @param eid
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("readfile")
	@ResponseBody
	public void importData(@RequestParam("auth") int auth, @RequestParam("area") String area,
			@RequestParam("file") CommonsMultipartFile files[], @RequestParam("eid") int eid,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		String realPath = "d://magic-ma969//temp//pointdata//";
		if (area != "" && files[0].getOriginalFilename() != "") {
			if (auth >= 1) {
				boolean readFileToDataBase = testdataService.readFileToDataBase(files, area, eid, realPath);
				if (readFileToDataBase) {
					writer.println(2);
				}
			} else {
				writer.println(1);
			}
		} else {
			writer.println(0);
		}
		writer.flush();
		writer.close();
	}

	/**
	 * 数据库备份
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("backupdatabase")
	@ResponseBody
	public ResponseEntity<byte[]> blackUpData(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String filename = LocalDate.now() + "_magic-ma969.sql";
		String path = "D:\\magic-ma969\\fileupload\\database\\";
		DataBaseBackup.exportDataBaseTools("127.0.0.1", "root", "123456", path, filename, "ma969data");
		File file = new File(path + filename);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(file.getName().getBytes("gb2312"), "iso8859-1"));
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	/**
	 * 获取数据模式
	 *
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getTestMode")
	@ResponseBody
	public void getTestMode(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(new Gson().toJson(radioDataService.listDataModes()));
		writer.flush();
		writer.close();
	}

	/**
	 * 获取采样点
	 *
	 * @param uid
	 * @param testModeId
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("getpoints")
	@ResponseBody
	public void getPoints(@RequestParam("uid") int uid, @RequestParam("testModeId") int testModeId,
			@RequestParam("typeId") int typeId, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		List points = new ArrayList<>();
		if (testModeId == 1) {
			points = digitalDataService.listPoints(uid, typeId);
		} else if (testModeId == 2) {
			points = radioDataService.listPoints(uid, typeId);
		} else if (testModeId == 3) {
			points = cdrDataService.listPoints(uid, typeId);
		} else {
			points = analogDataService.listPoints(uid, typeId);
		}
		String json = new Gson().toJson(points).trim();
		System.err.println(json);
		writer.println(json);
		writer.flush();
		writer.close();
	}
	
}
