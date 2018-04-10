/**
 * 数据模式
 */
package com.ma969.beans;

import java.io.Serializable;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月10日下午2:09:49
 */
public class DataMode implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	private int id;
	private String testMode;

	public DataMode() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public DataMode(int id, String testMode) {
		super();
		this.id = id;
		this.testMode = testMode;
	}

	@Override
	public String toString() {
		return "DataMode [id=" + id + ", testMode=" + testMode + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestMode() {
		return testMode;
	}

	public void setTestMode(String testMode) {
		this.testMode = testMode;
	}

}
