package com.ma969.test;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest; 
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:47:11
 */
public class Ma969Test {
	/**
	 * 安全哈希算法 main
	 * 
	 * @param args
	 */

	public static void main(String args[]) {
		 System.err.println("hello world");
	}
	
	public static final String KEY_SHA = "SHA";

	public static String getResult(String inputStr) {
		BigInteger sha = null;
		System.out.println("=======加密前的数据:" + inputStr);
		byte[] inputData = inputStr.getBytes();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
			messageDigest.update(inputData);
			sha = new BigInteger(messageDigest.digest());
			System.out.println("SHA加密后:" + sha.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sha.toString();
	}

	public double getFileSize(String filepath) {
		File file = new File(filepath);
		if (file.exists() && file.isFile()) {
			return file.length();
		} else {
			System.err.println("不是文件");
			return 0;
		}
	}
}
