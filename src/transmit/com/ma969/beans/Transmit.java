package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:52:48
 */
public class Transmit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int transmitId;
	private int antennaId;
	private int roomId;
	private int stationPersonId;
	private int towerId;
	private String area;
	private String tName;
	private float longitude;
	private float latitude;
	private float height;
	private int estate;
	private String lastUpt;
	private String lastUpName;

	@Override
	public String toString() {
		return "Transmit [transmitId=" + transmitId + ", antennaId=" + antennaId + ", roomId=" + roomId
				+ ", stationPersonId=" + stationPersonId + ", towerId=" + towerId + ", area=" + area + ", tName="
				+ tName + ", longitude=" + longitude + ", latitude=" + latitude + ", height=" + height + ", estate="
				+ estate + "]";
	}

	public Transmit(int transmitId, String area, String tName, float longitude, float latitude, float height,
			int estate, String lastUpt, String lastUpName) {
		super();
		this.transmitId = transmitId;
		this.area = area;
		this.tName = tName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
		this.estate = estate;
		this.lastUpt = lastUpt;
		this.lastUpName = lastUpName;
	}

	public Transmit(String area, String tName, float longitude, float latitude, float height, String lastUpt,
			String lastUpName) {
		super();
		this.area = area;
		this.tName = tName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
		this.lastUpt = lastUpt;
		this.lastUpName = lastUpName;
	}

	public Transmit(int antennaId, int roomId, int stationPersonId, int towerId, String area, String tName,
			float longitude, float latitude, float height, String lastUpt, String lastUpName) {
		super();
		this.antennaId = antennaId;
		this.roomId = roomId;
		this.stationPersonId = stationPersonId;
		this.towerId = towerId;
		this.area = area;
		this.tName = tName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
		this.lastUpt = lastUpt;
		this.lastUpName = lastUpName;
	}

	public Transmit(int transmitId, int antennaId, int roomId, int stationPersonId, int towerId, String area,
			String tName, float longitude, float latitude, float height, int estate, String lastUpt,
			String lastUpName) {
		super();
		this.transmitId = transmitId;
		this.antennaId = antennaId;
		this.roomId = roomId;
		this.stationPersonId = stationPersonId;
		this.towerId = towerId;
		this.area = area;
		this.tName = tName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
		this.estate = estate;
		this.lastUpt = lastUpt;
		this.lastUpName = lastUpName;
	}

	public String getLastUpName() {
		return lastUpName;
	}

	public void setLastUpName(String lastUpName) {
		this.lastUpName = lastUpName;
	}

	public String getLastUpt() {
		return lastUpt;
	}

	public void setLastUpt(String lastUpt) {
		this.lastUpt = lastUpt;
	}

	public int getEstate() {
		return estate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setEstate(int estate) {
		this.estate = estate;
	}

	public Transmit() {
		super();
	}

	public int getAntennaId() {
		return antennaId;
	}

	public void setAntennaId(int antennaId) {
		this.antennaId = antennaId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getStationPersonId() {
		return stationPersonId;
	}

	public void setStationPersonId(int stationPersonId) {
		this.stationPersonId = stationPersonId;
	}

	public int getTowerId() {
		return towerId;
	}

	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}

	public int getTransmitId() {
		return transmitId;
	}

	public void setTransmitId(int transmitId) {
		this.transmitId = transmitId;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
