package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:52:31
 */
public class StationPersonnel implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer stationPersonId;
private String supervisor; 
private String testPeople; 
private String responsiblePerson; 
private int pstate;
public int getPstate() {
	return pstate;
}
public void setPstate(int pstate) {
	this.pstate = pstate;
}
public StationPersonnel(String supervisor, String testPeople, String responsiblePerson) {
	super();
	this.supervisor = supervisor;
	this.testPeople = testPeople;
	this.responsiblePerson = responsiblePerson;
}
public StationPersonnel() {
	super(); 
}
public StationPersonnel(Integer stationPersonId, String supervisor, String testPeople, String responsiblePerson) {
	super();
	this.stationPersonId = stationPersonId;
	this.supervisor = supervisor;
	this.testPeople = testPeople;
	this.responsiblePerson = responsiblePerson;
}
public Integer getStationPersonId() {
	return stationPersonId;
}
public void setStationPersonId(Integer stationPersonId) {
	this.stationPersonId = stationPersonId;
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
}
