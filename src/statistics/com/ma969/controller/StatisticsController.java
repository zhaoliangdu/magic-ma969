package com.ma969.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.ma969.services.StatisticsService;

/**
 * 
 * @author zhao
 *
 */
@Controller
public class StatisticsController {
	@Autowired
	StatisticsService statisticsService;

	/**
	 * 场强分布根据数据绘制直方图和饼状图
	 * 
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping("getfieldStrength")
	@ResponseBody
	public void getFieldStrength(@RequestParam("uid") int uid, @RequestParam("testModeId") int testModeId, String area,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		List<Float> fieldStrength = statisticsService.getFieldStrength(uid, testModeId, area);
		String json = new Gson().toJson(fieldStrength); 
		System.err.println("json:"+json);
		writer.println(json);
		writer.flush();
		writer.close();
	}
}
