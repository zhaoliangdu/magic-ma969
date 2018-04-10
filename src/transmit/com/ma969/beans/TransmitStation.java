package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:53:08
 */
public class TransmitStation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3517753457817842386L;
	private int transmitId;
	private String tName;
	private String area;
	private float latitude;
	private float longitude;
	private float height;
	private int estate;

	private int aId;
	private String antennaType;
	private String programSource;
	private String fieldPattern;
	private String installPlan;
	private int astate;
	private int pId;
	private String supervisor;
	private String testPeople;
	private String responsiblePerson;
	private int pstate;
	private int rId;
	private String roomPosition;
	private String roomDeviceStatus;
	private String powerDistribution;
	private String grounding;
	private String airConditioner;
	private int rstate;
	private int tId;
	private String towerType;
	private String towerFramework;
	private String towerReport;
	private int tstate;
	private String lastUpt;
	private int cstate;
	private int fstate;
	private String lastUpName;

	public TransmitStation() {
		super();
	}

	@Override
	public String toString() {
		return "TransmitStation [transmitId=" + transmitId + ", tName=" + tName + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", height=" + height + ", aId=" + aId + ", antennaType=" + antennaType
				+ ", programSource=" + programSource + ", fieldPattern=" + fieldPattern + ", installPlan=" + installPlan
				+ ", supervisor=" + supervisor + ", testPeople=" + testPeople + ", responsiblePerson="
				+ responsiblePerson + ", roomPosition=" + roomPosition + ", roomDeviceStatus=" + roomDeviceStatus
				+ ", powerDistribution=" + powerDistribution + ", grounding=" + grounding + ", airConditioner="
				+ airConditioner + ", towerType=" + towerType + ", towerFramework=" + towerFramework + ", towerReport="
				+ towerReport + "]";
	}

	public TransmitStation(String tName, float latitude, float longitude, float height, String area, String supervisor,
			String testPeople, String responsiblePerson, String roomPosition, String roomDeviceStatus,
			String powerDistribution, String grounding, String airConditioner, String towerType, String towerFramework,
			String towerReport, String lastUpt, String lastUpName) {
		super();
		this.tName = tName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		this.area = area;
		this.supervisor = supervisor;
		this.testPeople = testPeople;
		this.responsiblePerson = responsiblePerson;
		this.roomPosition = roomPosition;
		this.roomDeviceStatus = roomDeviceStatus;
		this.powerDistribution = powerDistribution;
		this.grounding = grounding;
		this.airConditioner = airConditioner;
		this.towerType = towerType;
		this.towerFramework = towerFramework;
		this.towerReport = towerReport;
		this.lastUpt = lastUpt;
		this.setLastUpName(lastUpName);
	}

	public TransmitStation(int transmitId, String tName, float latitude, float longitude, float height, String area,
			int aId, String antennaType, String programSource, String fieldPattern, String installPlan,
			String supervisor, String testPeople, String responsiblePerson, String roomPosition,
			String roomDeviceStatus, String powerDistribution, String grounding, String airConditioner,
			String towerType, String towerFramework, String towerReport, String lastUpt, String lastUpName) {
		super();
		this.transmitId = transmitId;
		this.tName = tName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		this.area = area;
		this.aId = aId;
		this.antennaType = antennaType;
		this.programSource = programSource;
		this.fieldPattern = fieldPattern;
		this.installPlan = installPlan;
		this.supervisor = supervisor;
		this.testPeople = testPeople;
		this.responsiblePerson = responsiblePerson;
		this.roomPosition = roomPosition;
		this.roomDeviceStatus = roomDeviceStatus;
		this.powerDistribution = powerDistribution;
		this.grounding = grounding;
		this.airConditioner = airConditioner;
		this.towerType = towerType;
		this.towerFramework = towerFramework;
		this.towerReport = towerReport;
		this.lastUpt = lastUpt;
		this.setLastUpName(lastUpName);
	}

	public String getLastUpt() {
		return lastUpt;
	}

	public void setLastUpt(String lastUpt) {
		this.lastUpt = lastUpt;
	}

	public int getCstate() {
		return cstate;
	}

	public void setCstate(int cstate) {
		this.cstate = cstate;
	}

	public int getFstate() {
		return fstate;
	}

	public void setFstate(int fstate) {
		this.fstate = fstate;
	}

	public int getEstate() {
		return estate;
	}

	public void setEstate(int estate) {
		this.estate = estate;
	}

	public int getAstate() {
		return astate;
	}

	public void setAstate(int astate) {
		this.astate = astate;
	}

	public int getPstate() {
		return pstate;
	}

	public void setPstate(int pstate) {
		this.pstate = pstate;
	}

	public int getRstate() {
		return rstate;
	}

	public void setRstate(int rstate) {
		this.rstate = rstate;
	}

	public int getTstate() {
		return tstate;
	}

	public void setTstate(int tstate) {
		this.tstate = tstate;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
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

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getTestPeople() {
		return testPeople;
	}

	public void setTestPeople(String testPeople) {
		this.testPeople = testPeople;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
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

	/**
	 * @return lastUpName
	 */
	public String getLastUpName() {
		return lastUpName;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	/**
	 * @param lastUpName
	 *            要设置的 lastUpName
	 */
	public void setLastUpName(String lastUpName) {
		this.lastUpName = lastUpName;
	}

}
