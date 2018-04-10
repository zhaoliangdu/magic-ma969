package com.ma969.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.ma969.beans.SystemSet;
import com.ma969.service.MagicRobot;
import com.ma969.service.SystemSetService;
import com.ma969.utils.OperationFileUtils;
import com.ma969.utils.RandomColor;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:51:03
 */

@Controller
public class SystemSetController {
	@Autowired
	SystemSetService service;
	@Autowired
	MagicRobot magicRobot;

	private static final Log LOG = LogFactory.getLog(SystemSetController.class);
	private int type0 = 0, type1 = 1, type2 = 2;

	/**
	 * 系统设置页面 加载系统设置数值
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping("getsystemset")
	public ModelAndView setSystem(@RequestParam("typeId") int typeId, HttpServletRequest request,
			HttpServletResponse response) {
		LOG.info("systemset...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		int uid = (int) request.getSession().getAttribute("uid");
		SystemSet systemSet = service.getSystemSet(uid);
		int fontSize = systemSet.getFontSize();
		int score = 0, min = 0, max = 0;
		String[] colorarr = null, rangearr = null;
		List<String> rangelist = new ArrayList<>();
		List<String> colorlist = new ArrayList<>();
		if (typeId <= type2) {
			if (typeId == type0) {
				score = systemSet.getFieldScore();
				colorarr = systemSet.getFieldColor().split(",");
				rangearr = systemSet.getFieldRange().split(",");
				min = systemSet.getMinField();
				max = systemSet.getMaxField();
			} else if (typeId == type1) {
				score = systemSet.getSnrScore();
				colorarr = systemSet.getSnrColor().split(",");
				rangearr = systemSet.getSnrRange().split(",");
				min = systemSet.getMinSnr();
				max = systemSet.getMaxSnr();
			} else if (typeId == type2) {
				score = systemSet.getLdpcScore();
				colorarr = systemSet.getLdpcColor().split(",");
				rangearr = systemSet.getLdpcRange().split(",");
				min = systemSet.getMinLdpc();
				max = systemSet.getMaxLdpc();
			} else {
				score = systemSet.getFieldScore();
				colorarr = systemSet.getFieldColor().split(",");
				rangearr = systemSet.getFieldRange().split(",");
				min = systemSet.getMinField();
				max = systemSet.getMaxField();
			}
			for (String color : colorarr) {
				colorlist.add(color);
			}
			for (String range : rangearr) {
				rangelist.add(range.trim());
			}
			int clist = colorlist.size();
			LOG.info("colorlist:" + colorlist.size() + "-score:" + score);
			if (clist < score) {
				for (int i = 0; i < score - clist; i++) {
					colorlist.add(RandomColor.randomColor());
				}
			} else if (clist > score) {
				for (int i = clist - 1; i >= score; i--) {
					colorlist.remove(i);
				}
			}
			return new ModelAndView("jsp/systemset/systemset").addObject("min", min).addObject("max", max)
					.addObject("score", score).addObject("rangelist", rangelist).addObject("colorlist", colorlist)
					.addObject("typeId", typeId).addObject("fontSize", fontSize);
		} else {
			return new ModelAndView("jsp/systemset/systemset").addObject("typeId", 0);
		}
	}

	/**
	 * udateSystemSet 修改系统设置
	 * 
	 * @param min
	 * @param max
	 * @param score
	 * @param uid
	 * @param colors
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("updatesystemset")
	public ModelAndView updateSystemSet(@RequestParam("typeId") int typeid, @RequestParam("min") int min,
			@RequestParam("max") int max, @RequestParam("score") int score, @RequestParam("uid") int uid,
			@RequestParam("colors") String colors, HttpServletRequest request) throws IOException {
		LOG.info("updatesystemset..." + colors);
		// 计算场强分段 返回string
		String rangelist = service.getRange(min, max, score).trim();
		String[] colorarr = colors.split(",");
		List<String> colorlist = new ArrayList<>();
		for (String color : colorarr) {
			colorlist.add("#" + color);
		}
		int clist = colorlist.size();
		if (clist < score) {
			for (int i = 0; i < score - clist; i++) {
				colorlist.add(RandomColor.randomColor());
			}
		} else if (clist > score) {
			for (int i = clist - 1; i >= score; i--) {
				colorlist.remove(i);
			}
		}
		StringBuffer sBuffer = new StringBuffer();
		for (String color : colorlist) {
			sBuffer.append(color.trim());
			sBuffer.append(",");
		}
		sBuffer.delete(sBuffer.length() - 1, sBuffer.length());
		String colorstr = sBuffer.toString().trim();
		SystemSet systemSet = new SystemSet();
		int upde = 0;
		systemSet.setUid(uid);
		if (typeid == type0) {
			systemSet.setMinField(min);
			systemSet.setMaxField(max);
			systemSet.setFieldColor(colorstr);
			systemSet.setFieldRange(rangelist);
			systemSet.setFieldScore(score);
			upde = service.updateFieldSet(systemSet);
		} else if (typeid == type1) {
			systemSet.setMinSnr(min);
			systemSet.setMaxSnr(max);
			systemSet.setSnrColor(colorstr);
			systemSet.setSnrRange(rangelist);
			systemSet.setSnrScore(score);
			upde = service.updateSnrSet(systemSet);
		} else if (typeid == type2) {
			systemSet.setMinLdpc(min);
			systemSet.setMaxLdpc(max);
			systemSet.setLdpcColor(colorstr);
			systemSet.setLdpcRange(rangelist);
			systemSet.setLdpcScore(score);
			upde = service.updateLdpcSet(systemSet);
		} else {
			systemSet.setMinField(min);
			systemSet.setMaxField(max);
			systemSet.setFieldColor(colorstr);
			systemSet.setFieldRange(rangelist);
			systemSet.setFieldScore(score);
			upde = service.updateFieldSet(systemSet);
		}
		if (upde == 1) {
			return new ModelAndView("jsp/systemset/systemset").addObject("min", min).addObject("max", max)
					.addObject("score", score).addObject("rangelist", rangelist).addObject("colorlist", colorlist)
					.addObject("uid", uid).addObject("sysmsg", "修改成功！").addObject("typeId", typeid);
		} else {
			return new ModelAndView("jsp/systemset/systemset").addObject("min", min).addObject("max", max)
					.addObject("score", score).addObject("rangelist", rangelist).addObject("colorlist", colorlist)
					.addObject("uid", uid).addObject("sysmsg", "修改失败！").addObject("typeId", typeid);
		}
	}

	/**
	 * 获取场强/信噪比/误包率 分段颜色
	 * 
	 * @param uid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getsamplestyle")
	@ResponseBody
	public void getSamplestyle(@RequestParam("uid") int uid, @RequestParam("typeId") Integer typeid,
			HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String colors = "";
		if (typeid == type0) {
			colors = service.getFieldColors(uid);
		} else if (typeid == type1) {
			colors = service.getSnrColors(uid);
		} else if (typeid == type2) {
			colors = service.getLdpcColors(uid);
		} else {
			colors = service.getFieldColors(uid);
		}
		String json = new Gson().toJson(colors.split(","));

		writer.println(json);
		writer.flush();
		writer.close();
	}

	/**
	 * 加载背景颜色和字体
	 * 
	 * @param uid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getbgcolorfont")
	@ResponseBody
	public void getBgColor(@RequestParam("uid") int uid, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(new Gson().toJson(service.getSystemSet(uid)));
		writer.flush();
		writer.close();
	}

	/**
	 * 修改背景颜色字体
	 * 
	 * @param bgcolor
	 * @param fontStyle
	 * @param fontSize
	 * @param uid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("updatefont")
	@ResponseBody
	public void updateBgColor(@RequestParam("fontsize") int fontSize, @RequestParam("uid") int uid,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(service.updateBgColorFont(new SystemSet(fontSize, uid)) + "");
		writer.flush();
		writer.close();
	}

	/**
	 * 恢复默认设置
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("resetsystemset")
	public ModelAndView resetSysSet(@RequestParam("uid") int uid, HttpServletRequest request,
			HttpServletResponse response) {
		if (service.resetSysSet(uid) == 1) {
			return setSystem(0, request, response).addObject("sysmsg", "恢复成功");
		} else {
			return setSystem(0, request, response).addObject("sysmsg", "恢复失败");
		}
	}

	/**
	 * 清理缓存文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("cleartempfiles")
	public ModelAndView clearTempFiles(HttpServletRequest request, HttpServletResponse response) {
		String tempPath = "D:\\magic-ma969\\temp\\";
		OperationFileUtils.deleteAllFiles(new File(tempPath));
		return setSystem(0, request, response);
	}

	/**
	 * 得到缓存文件大小
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("gettempsize")
	@ResponseBody
	public void getTempSize(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String pointdatatemp = "d:\\magic-ma969\\temp\\pointdata\\";
		String transmittemp = "d:\\magic-ma969\\temp\\transmit\\";
		String size = OperationFileUtils.getTempFiles(pointdatatemp);
		String size1 = OperationFileUtils.getTempFiles(transmittemp);
		writer.println((Double.parseDouble(size) + Double.parseDouble(size1)) + "Mb");
		writer.flush();
		writer.close();
	}

	/**
	 * 帮助中心
	 * 
	 * @return
	 */
	@RequestMapping("help")
	public ModelAndView help() {
		return new ModelAndView("jsp/systemset/help");
	}

	/**
	 * magic robot
	 * 
	 * @param context
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("inquiry")
	@ResponseBody
	public void inquiry(@RequestParam("context") String context, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		String tuLingAI = magicRobot.tuLingAI(context);
		System.err.println("tulingsay:" + tuLingAI);
		writer.println(tuLingAI);
		writer.flush();
		writer.close();
	}
}
