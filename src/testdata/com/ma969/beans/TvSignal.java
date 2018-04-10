/**
 * 电视信号分析仪
 */
package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author zhao
 *
 */

public class TvSignal implements Serializable {

	private static final long serialVersionUID = 3831373757558996533L;

	private int id;
	private String area;
	private double longitude;
	private double latitude;
	private String machineModel;
	private String chargePerson;
	private String transmitName;

	public TvSignal() {
		super(); 
	}

	public TvSignal(int id, String area, double longitude, double latitude, String machineModel, String chargePerson,
			String transmitName) {
		super();
		this.id = id;
		this.area = area;
		this.longitude = longitude;
		this.latitude = latitude;
		this.machineModel = machineModel;
		this.chargePerson = chargePerson;
		this.transmitName = transmitName;
	}

	@Override
	public String toString() {
		return "TVSignal [id=" + id + ", area=" + area + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", machineModel=" + machineModel + ", chargePerson=" + chargePerson + ", transmitName=" + transmitName
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getMachineModel() {
		return machineModel;
	}

	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}

	public String getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getTransmitName() {
		return transmitName;
	}

	public void setTransmitName(String transmitName) {
		this.transmitName = transmitName;
	}
}
