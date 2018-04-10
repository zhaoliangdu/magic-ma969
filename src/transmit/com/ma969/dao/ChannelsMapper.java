package com.ma969.dao;

import java.util.List;

import com.ma969.beans.Channels;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:54:06
 */
public interface ChannelsMapper {
	/**
	 * 获取List<Channels>
	 * @return
	 */
	public List<Channels> listChannels();
}
