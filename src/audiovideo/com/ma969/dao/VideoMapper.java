package com.ma969.dao;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:51:46
 */

import java.util.List;

import com.ma969.beans.Video;

public interface VideoMapper {

	/**
	 * 获取video List数据
	 * @return
	 */
	List<Video> listVideoData();

	/**
	 * 添加video 数据
	 * @param video
	 * @return
	 */
	int saveVideoData(Video video);

	/**
	 * 删除video
	 * @param id
	 * @return
	 */
	int deleteVideoData(int id);

	/**
	 * 查找video数据
	 * @param id
	 * @return
	 */
	String findById(int id);

	/**
	 * 根据文件名查找video
	 * @param fileName
	 * @return
	 */
	int findByFileName(String fileName);
}
