package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:52:27
 */
public class Room implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roomId;
	private String roomPosition; 
	private String roomDeviceStatus; 
	private String powerDistribution; 
	private String grounding; 
	private String airConditioner; 
	private int rstate;
	public int getRstate() {
		return rstate;
	}

	public void setRstate(int rstate) {
		this.rstate = rstate;
	}

	public Room() {
		super(); 
	}

	public Room(String roomPosition, String roomDeviceStatus, String powerDistribution, String grounding,
			String airConditioner) {
		super();
		this.roomPosition = roomPosition;
		this.roomDeviceStatus = roomDeviceStatus;
		this.powerDistribution = powerDistribution;
		this.grounding = grounding;
		this.airConditioner = airConditioner;
	}

	public Room(Integer roomId, String roomPosition, String roomDeviceStatus, String powerDistribution,
			String grounding, String airConditioner) {
		super();
		this.roomId = roomId;
		this.roomPosition = roomPosition;
		this.roomDeviceStatus = roomDeviceStatus;
		this.powerDistribution = powerDistribution;
		this.grounding = grounding;
		this.airConditioner = airConditioner;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomPosition() {
		return roomPosition;
	}

	public void setRoomPosition(String roomPosition) {
		this.roomPosition = roomPosition;
	}

	public String getRoomDeviceStatus() {
		return roomDeviceStatus;
	}

	public void setRoomDeviceStatus(String roomDeviceStatus) {
		this.roomDeviceStatus = roomDeviceStatus;
	}

	public String getPowerDistribution() {
		return powerDistribution;
	}

	public void setPowerDistribution(String powerDistribution) {
		this.powerDistribution = powerDistribution;
	}

	public String getGrounding() {
		return grounding;
	}

	public void setGrounding(String grounding) {
		this.grounding = grounding;
	}

	public String getAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(String airConditioner) {
		this.airConditioner = airConditioner;
	}
}
