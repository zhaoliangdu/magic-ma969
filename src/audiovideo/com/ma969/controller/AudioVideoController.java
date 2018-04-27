package com.ma969.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ma969.beans.Progress;
import com.ma969.beans.Audio;
import com.ma969.beans.Video;
import com.ma969.service.AudioVideoService;
import com.ma969.service.TransmitStationService;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:51:32
 */
@Controller
public class AudioVideoController {
	@Autowired
	AudioVideoService services;
	@Autowired
	TransmitStationService tranService;

	private static final String REALPATHA = "D:\\magic-ma969\\fileupload\\audio\\";
	private static final String REALPATHV = "D:\\magic-ma969\\fileupload\\video\\";
	private static final Log LOG = LogFactory.getLog(AudioVideoController.class);

	/**
	 * 音频视图
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("audioma")
	public ModelAndView audioManage(HttpServletResponse response, HttpServletRequest request) {
		LOG.info("audioma method");
		List<Audio> audioData = services.getAudioData();
		audioData.forEach(audiodata -> audiodata
				.setImportDate(audiodata.getImportDate().substring(0, audiodata.getImportDate().length() - 2)));
		return new ModelAndView("jsp/audiovideo/audio-ma").addObject("audiolist", audioData);
	}

	/**
	 * 视频视图
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("videoma")
	public ModelAndView videoManage(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf-8");
		List<Video> videoData = services.listVideoData();
		videoData.forEach(videodata -> videodata
				.setImportDate(videodata.getImportDate().substring(0, videodata.getImportDate().length() - 2)));
		return new ModelAndView("jsp/audiovideo/video-ma").addObject("videolist", videoData);
	}

	/**
	 * 添加音频
	 * 
	 * @param files
	 * @param eid
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("addaudio")
	public ModelAndView addAudio(@RequestParam("filename") MultipartFile files, @RequestParam("eid") int eid,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String filename = LocalDate.now().toString() + System.currentTimeMillis() + "_" + files.getOriginalFilename();
		String tempPath = "fileupload\\audio\\";
		services.saveFileToData(files, filename, REALPATHA + filename, tempPath, eid, "audio");
		session.setAttribute("realPath", REALPATHA + filename);
		session.setAttribute("filename", filename);
		session.setAttribute("type", "audio");
		return audioManage(response, request);
	}

	/**
	 * 添加视频
	 * 
	 * @param files
	 * @param eid
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addvideo")
	public ModelAndView addvideo(@RequestParam("filename") MultipartFile files, @RequestParam("eid") int eid,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String filename = LocalDate.now().toString() + System.currentTimeMillis() + "_" + files.getOriginalFilename();
		String tempPath = "fileupload\\video\\";
		services.saveFileToData(files, filename, REALPATHV + filename, tempPath, eid, "video");
		session.setAttribute("realPath", REALPATHV + filename);
		session.setAttribute("filename", filename);
		session.setAttribute("type", "video");
		return videoManage(response, request);
	}

	/**
	 * 获取文件上传信息返回上传进度
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getprogress")
	@ResponseBody
	public void getProgress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Progress progress = (Progress) session.getAttribute("status");
		PrintWriter writer = response.getWriter();

		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		if (progress != null) {
			String result = numberFormat
					.format((float) progress.getBytesRead() / (float) progress.getContentLength() * 100);

			String realPath = (String) session.getAttribute("realPath");
			String filename = (String) session.getAttribute("filename");
			String type = (String) session.getAttribute("type");
			boolean checkFileAndData = services.checkFileAndData(filename, realPath, type);
			String processVal = "100";
			if (processVal.equals(result) && checkFileAndData) {
				writer.println(1);
			} else {
				writer.println(result);
			}
		} else {
			writer.println(0);
		}
		writer.flush();
		writer.close();
	}

	/**
	 * 删除音频数据
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("delaudiodata")
	@ResponseBody
	public void deleteAudioData(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String audioName = services.getAudioName(id);
		System.err.println("audioName:" + audioName);
		PrintWriter writer = response.getWriter();
		writer.println(services.deleteAudioData(id, REALPATHA + audioName));
		writer.flush();
		writer.close();
	}

	/**
	 * 删除视频数据
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("delvideodata")
	@ResponseBody
	public void delVideoData(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter writer = response.getWriter();
		String videoName = services.getVideoName(id);

		int delVideoData = services.deleteVideoData(id, REALPATHV + videoName);
		writer.println(delVideoData);
		writer.flush();
		writer.close();
	}
}
