package com.ma969.controller;
 
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.ma969.service.DigitalDataService;
import com.ma969.service.RadioDataService;
import com.ma969.service.SystemSetService;
import com.ma969.service.TestDataService;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:50:27
 */
@Controller
public class MapController {
	@Autowired
	DigitalDataService digitalDataService;
	@Autowired
	RadioDataService radioDataService;
	@Autowired
	SystemSetService systemService;
	@Autowired
	TestDataService tservice;

	/**
	 * 百度地图视图
	 * 
	 * @return
	 */
	@RequestMapping("baidumap")
	public ModelAndView baiduMap() {
		return new ModelAndView("jsp/map/baidumap");
	}

	/**
	 * 生成场强图
	 * 
	 * @param uid
	 * @param testModeId
	 * @param typeId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("fieldOverlay")
	public void fieldOverlay(@RequestParam("uid") int uid, @RequestParam("testModeId") int testModeId,
			@RequestParam("typeId") int typeId, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		Gson gson = new Gson();
		List<List<TreeMap<String, Float>>> points = tservice.getFieldOverlay(uid, testModeId);
		String json = gson.toJson(points);
		 
		writer.println(json);
		writer.flush();
		writer.close();
	}

	/**
	 * 生成场强所在范围矩形图
	 * 
	 * @param response
	 * @param testmodeId
	 * @throws Exception
	 */
	@RequestMapping("fieldRectangle")
	@ResponseBody
	public void fieldRectangle(HttpServletResponse response, @RequestParam("testModeId") int testmodeId)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		Map<String, Float> fieldRectangle = tservice.getFieldRectangle(testmodeId);
		Gson gson = new Gson();
		String json = gson.toJson(fieldRectangle);
	 
		writer.println(json);
		writer.flush();
		writer.close();
	}

	/**
	 * 必应地图视图
	 * 
	 * @return
	 */
	@RequestMapping("bingmap")
	public ModelAndView bingMap() {

		return new ModelAndView("jsp/map/bingmap");
	}

	/**
	 * 高德地图视图
	 * 
	 * @return
	 */
	@RequestMapping("gaodemap")
	public ModelAndView gaodeMap() {

		return new ModelAndView("jsp/map/gaodemap");
	}

	/**
	 * 高德地图视图
	 * 
	 * @return
	 */
	@RequestMapping("supermap")
	public ModelAndView supermap() {

		return new ModelAndView("jsp/map/supermap");
	}

	/**
	 * 高德地图
	 * 
	 * @return
	 */
	@RequestMapping("gaodetest")
	public ModelAndView gaodetestMap() {

		return new ModelAndView("jsp/map/gaodetest");
	}
}
