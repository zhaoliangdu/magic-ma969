package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:52:22
 */
public class Frequency implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3835761718956129527L;
	private int fid;
	private int aid;
	private Float frequency;

	@Override
	public String toString() {
		return "Frequency [fid=" + fid + ", aid=" + aid + ", frequency=" + frequency + "]";
	}

	public Frequency(int aid, Float frequency) {
		super();
		this.aid = aid;
		this.frequency = frequency;
	}

	public Frequency() {
		super();
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public Float getFrequency() {
		return frequency;
	}

	public void setFrequency(Float frequency) {
		this.frequency = frequency;
	}

}
