package com.ma969.dao;

import java.util.List;
 
import com.ma969.beans.Channel;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:54:01
 */
public interface ChannelMapper {
	/**
	 * 获取频道List<Channel>
	 * @param aid
	 * @return
	 */
	List<Channel> getChannels(int aid);

	/**
	 * 添加频道
	 * @param channel
	 * @return
	 */
	int addChannel(Channel channel);

	/**
	 * 删除频道
	 * @param channel
	 * @return
	 */
	int deleteChannel(String channel);

	/**
	 * 根据id删除频道
	 * @param aid
	 * @return
	 */
	int deleteChannelById(int aid);

	/**
	 * 查找频道
	 * @param channel
	 * @return
	 */
	Channel findByChannel(Channel channel);

	/**
	 * 修改状态
	 * @param eid
	 * @return
	 */
	int updateCstate(int eid);
}
