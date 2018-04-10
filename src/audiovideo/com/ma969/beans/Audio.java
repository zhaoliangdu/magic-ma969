package com.ma969.beans;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:47:00
 */
public class Audio {
	private int id;
	private String importDate;
	private String audioName;
	private String audioPath;
	private int eid;
	private String transmitName;

	public Audio() {
		super(); 
	}

	public Audio(int id, String importDate, String audioName, String audioPath, int eid) {
		super();
		this.id = id;
		this.importDate = importDate;
		this.audioName = audioName;
		this.audioPath = audioPath;
		this.eid = eid;
	}

	public Audio(String importDate, String audioName, String audioPath, int eid) {
		super();
		this.importDate = importDate;
		this.audioName = audioName;
		this.audioPath = audioPath;
		this.eid = eid;
	}

	public Audio(String importDate, String audioName, String audioPath, int eid, String transmitName) {
		super();
		this.importDate = importDate;
		this.audioName = audioName;
		this.audioPath = audioPath;
		this.eid = eid;
		this.transmitName = transmitName;
	}

	@Override
	public String toString() {
		return "Audio [id=" + id + ", importDate=" + importDate + ", audioName=" + audioName + ", audioPath="
				+ audioPath + ", eid=" + eid + ", transmitName=" + transmitName + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public String getAudioName() {
		return audioName;
	}

	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getTransmitName() {
		return transmitName;
	}

	public void setTransmitName(String transmitName) {
		this.transmitName = transmitName;
	}
	
}
