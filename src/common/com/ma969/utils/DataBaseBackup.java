/**
 * 数据库备份
 */
package com.ma969.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年12月6日下午4:20:58
 */
public class DataBaseBackup {
	public static void main(String[] args) {

		// if (exportDataBaseTools("127.0.0.1", "root", "123456", "d:/backupDatabase",
		// "magic-ma969.sql", "ma969data")) {
		// System.err.println("数据库备份成功");
		// } else {
		// System.err.println("数据库备份失败");
		// }
	}

	/**
	 * 数据库备份工具
	 * @param hostIP
	 * @param userName
	 * @param password
	 * @param savePath
	 * @param fileName
	 * @param databaseName
	 * @return
	 */
	public static boolean exportDataBaseTools(String hostIP, String userName, String password, String savePath,
			String fileName, String databaseName) {
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		if (!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
		}
		PrintWriter printWriter = null;
		BufferedReader bReader = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName)));
			Process process = Runtime.getRuntime().exec("mysqldump -h" + hostIP + " -u" + userName + " -p" + password
					+ " --set-charset=UTF8 " + databaseName);
			InputStreamReader isr = new InputStreamReader(process.getInputStream(), "utf8");
			bReader = new BufferedReader(isr);
			String line;
			while ((line = bReader.readLine()) != null) {
				printWriter.println(line);
			}
			printWriter.flush();
			if (process.waitFor() == 0) {
				return true;
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bReader != null) {
					bReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return false;
	}
}
