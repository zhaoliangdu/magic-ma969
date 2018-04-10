package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author ZhaoLiangdu
 *
 */
public class FieldStatistics implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	private int fieldId;
	private int fieldCount;
	private String fieldRange;
	private String color;
	
	public FieldStatistics() {
		super(); 
	}
	
	public FieldStatistics(int fieldId, int fieldCount, String fieldRange, String color) {
		super();
		this.fieldId = fieldId;
		this.fieldCount = fieldCount;
		this.fieldRange = fieldRange;
		this.color = color;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
 
	public int getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}
	public String getFieldRange() {
		return fieldRange;
	}
	public void setFieldRange(String fieldRange) {
		this.fieldRange = fieldRange;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
