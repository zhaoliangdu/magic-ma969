package com.ma969.dao;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:51:43
 */

import java.util.List;

import com.ma969.beans.Audio;

public interface AudioMapper {

	/**
	 * 获取audio List 数据
	 * @return
	 */
	List<Audio> listAudioData();
	
	/**
	 * 添加audio 数据
	 * @param audio
	 * @return
	 */
	int saveAudioData(Audio audio);

	/**
	 * 删除audio
	 * @param id
	 * @return
	 */
	int deleteAudioData(int id);

	/**
	 * 查找audio
	 * @param id
	 * @return
	 */
	String findById(int id);
	
	/**
	 * 根据文件名查找
	 * @param fileName
	 * @return
	 */
	int findByFileName(String fileName);
}
