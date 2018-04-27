/**
 * 数字电视数据
 */
package com.ma969.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.servlet.ModelAndView;
import com.ma969.service.DigitalDataService;
import com.ma969.utils.ExportExcel;
import com.ma969.utils.ExportToTxt;
import com.ma969.beans.DigitalData;
import me.jor.util.Help;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月10日下午1:16:22
 */
@Controller
@RequestMapping("datapoint")
public class DigitalDataController {
	@Autowired
	DigitalDataService digitalDataService;
	private static final String POINTDATA_PATH = "D://magic-ma969//temp//pointdata//";

	/**
	 * 多条件分页查询显示
	 * 
	 * @param smap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("digitaldata")
	public ModelAndView digitalData(@RequestParam Map<String, Object> smap) throws Exception {
		 
		String parStartTime = (String) smap.get("startTime");
		String parEndTime = (String) smap.get("endTime");
		String area = (String) smap.get("area");
		String pageNumrStr = (String) smap.get("pageNumr");
		String frequency = (String) smap.get("frequency");
		
		Map<String, Object> paramMap = new HashMap<>(16);
		paramMap.put("startTime", parStartTime);
		paramMap.put("endTime", parEndTime);
		paramMap.put("area", area);
		paramMap.put("frequency", frequency);
		paramMap.put("testModeId", 1);
		paramMap.put("pageNumr", pageNumrStr);
		String pageNoStr = (String) smap.get("pageNo");
		int count = digitalDataService.getCount(paramMap);
		int pageNo = Help.isEmpty(pageNoStr) ? 1 : Integer.parseInt(pageNoStr);
		int pageNumr = Help.isEmpty(pageNumrStr) ? 10 : Integer.parseInt(pageNumrStr);
		pageNo = pageNo > 0 ? pageNo : 1;
		int pageNum = count / pageNumr;
		pageNum += count % pageNumr > 0 ? 1 : 0;
		paramMap.put("pageNo", (pageNo - 1) * pageNumr);
		paramMap.put("pageNumr", pageNumr);
		
		List<DigitalData> digitaldatas = digitalDataService.listDigitalDataByParam(paramMap);
		return new ModelAndView("jsp/data/digitaldata").addObject("digitaldatas", digitaldatas)
				.addObject("pageNo", pageNo).addObject("pageNum", pageNum).addObject("count", count)
				.addObject("stTime", smap.get("startTime")).addObject("enTime", smap.get("endTime"))
				.addObject("sareas", smap.get("area")).addObject("sfrequencys", smap.get("frequency"))
				.addObject("testModeId", 1).addObject("pageNumr", pageNumr);
	}

	/**
	 * 删除数字电视数据
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("deldigitaldata")
	@ResponseBody
	public void delDigitalData(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(digitalDataService.deleteDigitalData(id) + "");
		writer.flush();
		writer.close();
	}

	/**
	 * 导出数字电视数据
	 * 
	 * @param type
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("exportdigitaldata")
	@ResponseBody
	public ResponseEntity<byte[]> exportDeldigitalData(@RequestParam("type") String type, HttpServletResponse response,
			HttpServletRequest request) throws IOException { 
		List<DigitalData> digitalDatas = digitalDataService.listDigitalDatas();
		if (digitalDatas != null) {
			String outpath = "", excel = "excel", txt = "txt";
			if (excel.equals(type)) {
				outpath = ExportExcel.exportDigToExcel(digitalDatas, POINTDATA_PATH);
			} else if (txt.equals(type)) {
				outpath = ExportToTxt.exportDigDataToTxt(digitalDatas, POINTDATA_PATH);
			}
			File file = new File(outpath); 
			HttpHeaders headers = new HttpHeaders();
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
