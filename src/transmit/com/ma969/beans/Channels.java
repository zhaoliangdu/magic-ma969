package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:52:18
 */
public class Channels implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String channel;
	
	@Override
	public String toString() {
		return "Channels [id=" + id + ", channel=" + channel + "]";
	}
	public Channels() {
		super(); 
	}
	public Channels(int id, String channel) {
		super();
		this.id = id;
		this.channel = channel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}

}
