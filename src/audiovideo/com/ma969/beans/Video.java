package com.ma969.beans;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:51:25
 */
public class Video {
	private int id;
	private String importDate;
	private String videoName;
	private String videoPath;
	private int eid;
	private String transmitName;

	public Video() {
		super(); 
	}

	public Video(int id, String importDate, String videoName, String videoPath, int eid) {
		super();
		this.id = id;
		this.importDate = importDate;
		this.videoName = videoName;
		this.videoPath = videoPath;
		this.eid = eid;
	}

	public Video(String importDate, String videoName, String videoPath, int eid) {
		super();
		this.importDate = importDate;
		this.videoName = videoName;
		this.videoPath = videoPath;
		this.eid = eid;
	}

	public Video(String importDate, String videoName, String videoPath, int eid, String transmitName) {
		super();
		this.importDate = importDate;
		this.videoName = videoName;
		this.videoPath = videoPath;
		this.eid = eid;
		this.transmitName = transmitName;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", importDate=" + importDate + ", videoName=" + videoName + ", videoPath="
				+ videoPath + ", eid=" + eid + ", transmitName=" + transmitName + "]";
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

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
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
