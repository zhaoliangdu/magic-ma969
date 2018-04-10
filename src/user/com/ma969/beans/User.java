package com.ma969.beans;

import java.io.Serializable;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:49:34
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int auth;
	private String authDesc;
	private String userName;
	private String password;
	private String name;
	private String userBirthday;
	private String userAddress;
	private String userPhone;
	private String userCompany;
	private int ustate; 
	private String createTime;
	private String lastLoginTime;

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public User() {
		super();
	}

	public int getUstate() {
		return ustate;
	}

	public void setUstate(int ustate) {
		this.ustate = ustate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public User(int auth, String authDesc, String userName, String password, String name, String userBirthday,
			String userAddress, String userPhone, String userCompany, int ustate, int state, String createTime,
			String lastLoginTime) {
		super();
		this.auth = auth;
		this.authDesc = authDesc;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.userBirthday = userBirthday;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userCompany = userCompany;
		this.ustate = ustate;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
	}

	public User(int userId, String userName, String name, String userBirthday, String userAddress, String userPhone,
			String userCompany) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.userBirthday = userBirthday;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userCompany = userCompany;
	}

	public User(int auth, String userName, String password, String name, String userBirthday, String userAddress,
			String userPhone, String userCompany) {
		super();
		this.auth = auth;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.userBirthday = userBirthday;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userCompany = userCompany;
	}

	public User(String userName, String name, String userBirthday, String userAddress, String userPhone,
			String userCompany) {
		super();
		this.userName = userName;
		this.name = name;
		this.userBirthday = userBirthday;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userCompany = userCompany;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", auth=" + auth + ", authDesc=" + authDesc + ", userName=" + userName
				+ ", password=" + password + ", name=" + name + ", userBirthday=" + userBirthday + ", userAddress="
				+ userAddress + ", userPhone=" + userPhone + ", userCompany=" + userCompany + ", ustate=" + ustate
				+ ", createTime=" + createTime + ", lastLoginTime=" + lastLoginTime + "]";
	}

	public User(int auth, String userName, String password) {
		super();
		this.auth = auth;
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, int userId, String password, String name, String userBirthday, String userAddress,
			String userPhone, String userCompany) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.userBirthday = userBirthday;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userCompany = userCompany;
	}

	public User(int userId, int auth, String authDesc, String userName, String password, String name,
			String userBirthday, String userAddress, String userPhone, String userCompany) {
		super();
		this.userId = userId;
		this.auth = auth;
		this.authDesc = authDesc;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.userBirthday = userBirthday;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userCompany = userCompany;
	}

	public String getAuthDesc(int auth) {
		int auth0 = 0, auth1 = 1, auth2 = 2;
		if (auth == auth0) {
			authDesc = "普通员工";
		}
		if (auth == auth1) {
			authDesc = "管理员";
		}
		if (auth == auth2) {
			authDesc = "超级管理员";
		}
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public int getAuth() {
		return auth;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

}
