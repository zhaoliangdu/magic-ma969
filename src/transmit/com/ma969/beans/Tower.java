package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:52:38
 */
public class Tower implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 791217382858204231L;
	private Integer towerId;
	private String towerType; 
	private String towerFramework; 
	private String towerReport; 
	private int tstate;
	public int getTstate() {
		return tstate;
	}

	public void setTstate(int tstate) {
		this.tstate = tstate;
	}

	public Tower(String towerType, String towerFramework, String towerReport) {
		super();
		this.towerType = towerType;
		this.towerFramework = towerFramework;
		this.towerReport = towerReport;
	}

	public Tower(Integer towerId, String towerType, String towerFramework, String towerReport) {
		super();
		this.towerId = towerId;
		this.towerType = towerType;
		this.towerFramework = towerFramework;
		this.towerReport = towerReport;
	}

	public Tower() {
		super(); 
	}

	public Integer getTowerId() {
		return towerId;
	}

	public void setTowerId(Integer towerId) {
		this.towerId = towerId;
	}

	public String getTowerType() {
		return towerType;
	}

	public void setTowerType(String towerType) {
		this.towerType = towerType;
	}

	public String getTowerFramework() {
		return towerFramework;
	}

	public void setTowerFramework(String towerFramework) {
		this.towerFramework = towerFramework;
	}

	public String getTowerReport() {
		return towerReport;
	}

	public void setTowerReport(String towerReport) {
		this.towerReport = towerReport;
	}

}
