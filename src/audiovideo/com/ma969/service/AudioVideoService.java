package com.ma969.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.ma969.beans.Audio;
import com.ma969.beans.Video;
import com.ma969.dao.AudioMapper;
import com.ma969.dao.VideoMapper;
import com.ma969.utils.OperationFileUtils;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:51:52
 */
@Service
public class AudioVideoService {
	@Autowired
	AudioMapper audioMapper;
	@Autowired
	VideoMapper videoMapper;

	private static final String ATYPE = "audio", VTYPE = "video";

	public List<Audio> getAudioData() {

		return audioMapper.listAudioData();
	}

	public int saveAudioData(Audio audio) {
		return audioMapper.saveAudioData(audio);
	}

	/**
	 * 添加音视频文件路径到数据库，上传到服务器
	 * 
	 * @param files
	 * @param filePath
	 * @param filepath
	 * @param eid
	 * @param filetype
	 * @return
	 */
	public int saveFileToData(MultipartFile file, String filename, String realPath, String tempPath, int eid,
			String filetype) {
		int addfile = 0;
		String time = LocalDateTime.now().toString();
		System.err.println("realpath:" + realPath);
		// 上传文件到服务器
		OperationFileUtils.uploadFIle(file, realPath);
		Audio audio = null;
		Video video = null;
		if (ATYPE.equals(filetype)) {
			audio = new Audio();
			audio.setImportDate(time);
			audio.setAudioName(filename);
			audio.setAudioPath(filename);
			audio.setEid(eid);
			addfile = saveAudioData(audio);
		}
		if (VTYPE.equals(filetype)) {
			System.err.println("filename:" + filename);
			video = new Video();
			video.setImportDate(time);
			video.setVideoName(filename);
			video.setVideoPath(filename);
			video.setEid(eid);
			addfile = saveVideoData(video);
		}
		return addfile;
	}

	public List<Video> listVideoData() {
		return videoMapper.listVideoData();
	}

	public int saveVideoData(Video video) {
		return videoMapper.saveVideoData(video);
	}

	/**
	 * 删除音频
	 * 
	 * @param id
	 * @param fileName
	 * @param tempPath
	 * @param realPath
	 * @return
	 */
	@Transactional
	public int deleteAudioData(int id, String filePath) {
		TransactionalCacheManager transactional = new TransactionalCacheManager();
		if (OperationFileUtils.delFile(filePath)) {
			if (audioMapper.deleteAudioData(id) == 1) {
				transactional.commit();
				return 1;
			}
		}
		transactional.rollback();
		return 0;
	}

	public String getAudioName(int id) {
		return audioMapper.findById(id);
	}

	/**
	 * 删除视频
	 * 
	 * @param id
	 * @param fileName
	 * @param tempPath
	 * @param realPath
	 * @return
	 */
	@Transactional
	public int deleteVideoData(int id, String filePath) {
		TransactionalCacheManager transactional = new TransactionalCacheManager();
		if (OperationFileUtils.delFile(filePath)) {
			if (videoMapper.deleteVideoData(id) == 1) {
				transactional.commit();
				return 1;
			}
		}
		transactional.rollback();
		return 0;
	}

	public String getVideoName(int id) {
		return videoMapper.findById(id);
	}

	/**
	 * 上传时检查数据库添加是否成功以及文件是否上传成功
	 * 
	 * @param filename
	 * @param filePath
	 * @param type
	 * @return
	 */
	public boolean checkFileAndData(String filename, String fileRealPath, String type) {
		boolean flag = false;
		System.err.println(fileRealPath);
		File file = new File(fileRealPath);
		if (ATYPE.equals(type)) {
			flag = audioMapper.findByFileName(filename) == 1 && file.exists();
		} else if (VTYPE.equals(type)) {
			flag = videoMapper.findByFileName(filename) == 1 && file.exists();
		}
		return flag;
	}

}
