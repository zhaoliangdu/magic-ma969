package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:50:58
 */
public class SystemSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int systemId;
	private int minField;
	private int maxField;
	private int fieldScore;
	private String fieldColor;
	private String fieldRange;
	private int minSnr;
	private int maxSnr;
	private int snrScore;
	private String snrColor;
	private String snrRange;
	private int minLdpc;
	private int maxLdpc;
	private int ldpcScore;
	private String ldpcColor;
	private String ldpcRange;
	private int fontSize;
	private long uid;
	private int sysstate;

	public SystemSet() {
		super();
	}

	public SystemSet(int systemId, int minField, int maxField, int fieldScore, String fieldColor, String fieldRange,
			long uid, int sysstate) {
		super();
		this.systemId = systemId;
		this.minField = minField;
		this.maxField = maxField;
		this.fieldScore = fieldScore;
		this.fieldColor = fieldColor;
		this.fieldRange = fieldRange;
		this.uid = uid;
		this.sysstate = sysstate;
	}

	public SystemSet(int minField, int maxField, int fieldScore, String fieldColor, String fieldRange, long uid) {
		super();
		this.minField = minField;
		this.maxField = maxField;
		this.fieldScore = fieldScore;
		this.fieldColor = fieldColor;
		this.fieldRange = fieldRange;
		this.uid = uid;
	}

	public SystemSet(int minField, int maxField, int fieldScore, String fieldColor, String fieldRange, long uid,
			int sysstate) {
		super();
		this.minField = minField;
		this.maxField = maxField;
		this.fieldScore = fieldScore;
		this.fieldColor = fieldColor;
		this.fieldRange = fieldRange;
		this.uid = uid;
		this.sysstate = sysstate;
	}

	public SystemSet(int minField, int maxField, int fieldScore, String fieldColor, String fieldRange, int minSnr,
			int maxSnr, int snrScore, String snrColor, String snrRange, int minLdpc, int maxLdpc, int ldpcScore,
			String ldpcColor, String ldpcRange, String bgcolor, long uid, int sysstate) {
		super();
		this.minField = minField;
		this.maxField = maxField;
		this.fieldScore = fieldScore;
		this.fieldColor = fieldColor;
		this.fieldRange = fieldRange;
		this.minSnr = minSnr;
		this.maxSnr = maxSnr;
		this.snrScore = snrScore;
		this.snrColor = snrColor;
		this.snrRange = snrRange;
		this.minLdpc = minLdpc;
		this.maxLdpc = maxLdpc;
		this.ldpcScore = ldpcScore;
		this.ldpcColor = ldpcColor;
		this.ldpcRange = ldpcRange;
		this.uid = uid;
		this.sysstate = sysstate;
	}

	public SystemSet(int fontSize, long uid) {
		super();
		this.fontSize = fontSize;
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "SystemSet [systemId=" + systemId + ", minField=" + minField + ", maxField=" + maxField + ", fieldScore="
				+ fieldScore + ", fieldColor=" + fieldColor + ", fieldRange=" + fieldRange + ", minSnr=" + minSnr
				+ ", maxSnr=" + maxSnr + ", snrScore=" + snrScore + ", snrColor=" + snrColor + ", snrRange=" + snrRange
				+ ", minLdpc=" + minLdpc + ", maxLdpc=" + maxLdpc + ", ldpcScore=" + ldpcScore + ", ldpcColor="
				+ ldpcColor + ", ldpcRange=" + ldpcRange + ", uid=" + uid + ", sysstate=" + sysstate + "]";
	}

	public int getSysstate() {
		return sysstate;
	}

	public void setSysstate(int sysstate) {
		this.sysstate = sysstate;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public int getMinField() {
		return minField;
	}

	public void setMinField(int minField) {
		this.minField = minField;
	}

	public int getMaxField() {
		return maxField;
	}

	public void setMaxField(int maxField) {
		this.maxField = maxField;
	}

	public int getFieldScore() {
		return fieldScore;
	}

	public void setFieldScore(int fieldScore) {
		this.fieldScore = fieldScore;
	}

	public String getFieldColor() {
		return fieldColor;
	}

	public void setFieldColor(String fieldColor) {
		this.fieldColor = fieldColor;
	}

	public String getFieldRange() {
		return fieldRange;
	}

	public void setFieldRange(String fieldRange) {
		this.fieldRange = fieldRange;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getMinSnr() {
		return minSnr;
	}

	public void setMinSnr(int minSnr) {
		this.minSnr = minSnr;
	}

	public int getMaxSnr() {
		return maxSnr;
	}

	public void setMaxSnr(int maxSnr) {
		this.maxSnr = maxSnr;
	}

	public int getSnrScore() {
		return snrScore;
	}

	public void setSnrScore(int snrScore) {
		this.snrScore = snrScore;
	}

	public String getSnrColor() {
		return snrColor;
	}

	public void setSnrColor(String snrColor) {
		this.snrColor = snrColor;
	}

	public String getSnrRange() {
		return snrRange;
	}

	public void setSnrRange(String snrRange) {
		this.snrRange = snrRange;
	}

	public int getMinLdpc() {
		return minLdpc;
	}

	public void setMinLdpc(int minLdpc) {
		this.minLdpc = minLdpc;
	}

	public int getMaxLdpc() {
		return maxLdpc;
	}

	public void setMaxLdpc(int maxLdpc) {
		this.maxLdpc = maxLdpc;
	}

	public int getLdpcScore() {
		return ldpcScore;
	}

	public void setLdpcScore(int ldpcScore) {
		this.ldpcScore = ldpcScore;
	}

	public String getLdpcColor() {
		return ldpcColor;
	}

	public void setLdpcColor(String ldpcColor) {
		this.ldpcColor = ldpcColor;
	}

	public String getLdpcRange() {
		return ldpcRange;
	}

	public void setLdpcRange(String ldpcRange) {
		this.ldpcRange = ldpcRange;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

}
