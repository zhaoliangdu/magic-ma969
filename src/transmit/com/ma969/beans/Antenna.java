package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:52:04
 */
public class Antenna implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer antennaId;
	private String antennaType; 
	private String programSource; 
	private String fieldPattern; 
	private String installPlan; 
	private int astate;
	public int getAstate() {
		return astate;
	}

	public void setAstate(int astate) {
		this.astate = astate;
	}

	public Antenna(String antennaType, String programSource, String fieldPattern, String installPlan) {
		super();
		this.antennaType = antennaType;
		this.programSource = programSource;
		this.fieldPattern = fieldPattern;
		this.installPlan = installPlan;
	}

	public Antenna() {
		super(); 
	}

	public Antenna(Integer antennaId, String antennaType, String programSource, String fieldPattern,
			String installPlan) {
		super();
		this.antennaId = antennaId;
		this.antennaType = antennaType;
		this.programSource = programSource;
		this.fieldPattern = fieldPattern;
		this.installPlan = installPlan;
	}

	public Integer getAntennaId() {
		return antennaId;
	}

	public void setAntennaId(Integer antennaId) {
		this.antennaId = antennaId;
	}

	public String getAntennaType() {
		return antennaType;
	}

	public void setAntennaType(String antennaType) {
		this.antennaType = antennaType;
	}

	public String getProgramSource() {
		return programSource;
	}

	public void setProgramSource(String programSource) {
		this.programSource = programSource;
	}

	public String getFieldPattern() {
		return fieldPattern;
	}

	public void setFieldPattern(String fieldPattern) {
		this.fieldPattern = fieldPattern;
	}

	public String getInstallPlan() {
		return installPlan;
	}

	public void setInstallPlan(String installPlan) {
		this.installPlan = installPlan;
	}
}
