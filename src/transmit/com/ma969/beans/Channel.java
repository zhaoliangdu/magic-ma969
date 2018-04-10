package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:55:08
 */
public class Channel implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private Integer channelId;
	private Integer aId;
	 
	private String channel;

	
	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", aId=" + aId + ", channel=" + channel + "]";
	}

	public Channel(Integer channelId, Integer aId, String channel) {
		super();
		this.channelId = channelId;
		this.aId = aId;
		this.channel = channel;
	}

	public Channel(Integer aId,  String channel) {
		super();

		this.aId = aId;
		this.channel = channel;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getaId() {
		return aId;
	}

	public void setaId(Integer aId) {
		this.aId = aId;
	}


	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}


	public Channel() {
		super(); 
	}

}