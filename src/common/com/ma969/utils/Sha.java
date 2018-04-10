/**
 * 密码MD5加密
 */
package com.ma969.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:48:51
 */
public class Sha {
	public static void main(String[] args) {
		try {
			String inputStr = "adminadmin";
			String result = getResult(inputStr);
			System.err.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final String KEY_SHA = "SHA";

	/**
	 * 密码加密
	 * @param inputStr
	 * @return
	 */
	public static String getResult(String inputStr) {
		BigInteger sha = null;
		byte[] inputData = inputStr.getBytes();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
			messageDigest.update(inputData);
			sha = new BigInteger(messageDigest.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sha.toString();
	}
}
