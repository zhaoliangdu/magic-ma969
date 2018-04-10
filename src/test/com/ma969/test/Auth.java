
package com.ma969.test;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年12月11日下午4:34:17
 */
public enum Auth {
	// 超级管理员  ，管理员 ， 普通
	SUPERMANAGER("超级管理员", 2), MANAGER("管理员", 1), USER("普通用户", 0);
	private Auth(String auther, int auth) {
		this.auther = auther;
		this.auth = auth;
	}

	public static String getAuther(int auth) {
		for (Auth a : Auth.values()) {
			if (a.getAuth() == auth) {
				return a.auther;
			}
		}
		return null;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	private String auther;
	private int auth;
}
