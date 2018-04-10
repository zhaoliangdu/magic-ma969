/**
 * 清理缓存、上传文件
 */
package com.ma969.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年12月15日上午10:52:46
 */
public class OperationFileUtils {
	public static void main(String[] args) {
		File file = new File("d:/magic-ma969/temp/");
		deleteAllFiles(file);
	}

	/**
	 * 获取文件大小
	 * 
	 * @param filepath
	 * @return
	 */
	public static double getFileSize(String filepath) {
		File file = new File(filepath);
		double filesize = 0;
		if (file.exists() && file.isFile()) {
			filesize = file.length();
			filesize = filesize / 1024000;
		} else {
			System.err.println("不是文件");
		}
		return filesize;
	}

	/**
	 * 获取临时文件大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getTempFiles(String filePath) {
		File file = new File(filePath);
		DecimalFormat df3 = new DecimalFormat("######0.000");
		double filesize = 0;
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				filesize += getFileSize(filePath + fileList[i].getName());
			}
		}
		return df3.format(filesize);
	}

	/**
	 * 删除临时文件方法
	 * 
	 * @param file
	 * @return
	 */
	public static boolean deleteAllFiles(File file) {
		boolean delete = false;
		if (file == null || !file.exists()) {
			return delete;
		} 
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					deleteAllFiles(f);
				}
			}
		} else {
			delete = file.delete();
		}
		return delete;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean delFile(String filePath) {
		File file = new File(filePath);
		boolean delete = false;
		if (file.isFile()) {
			System.err.println("delFilename:" + file.getName() + "-path:" + file.getPath());
			delete = file.delete();
		}
		return delete;
	}

	/**
	 * 文件上传
	 * 
	 * @param files
	 * @param filePath
	 */
	public static void uploadFIle(MultipartFile file, String realPath) {
		System.err.println("filePath:" + realPath);
		// 保存到硬盘
		try {
			file.transferTo(new File(realPath));
			// 复制文件到项目访问路径
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多文件上传
	 * 
	 * @param files
	 * @param realPath
	 * @param serverPath
	 * @param timeStr
	 */
	public static void uploadFIles(CommonsMultipartFile[] files, String realPath, String timeStr) {

		for (int i = 0; i < files.length; i++) {
			String file = timeStr + files[i].getOriginalFilename();
			System.err.println("file:" + file);
			try {
				files[i].transferTo(new File(realPath + timeStr + files[i].getOriginalFilename()));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 创建所需文件夹
	 */
	public static void mkFiles() {
		String dir0 = "d://magic-ma969//";
		String dir1 = dir0 + "log";
		String dir2 = dir0 + "fileupload//";
		String dir21 = dir2 + "audio";
		String dir22 = dir2 + "pointdata";
		String dir23 = dir2 + "video";
		String dir24 = dir2 + "transmit";
		File file0 = new File(dir0);
		if (!file0.isDirectory()) {
			file0.mkdir();
			File file1 = new File(dir1);
			if (!file1.isDirectory()) {
				file1.mkdirs();
				File file2 = new File(dir2);
				if (!file2.isDirectory()) {
					file2.mkdirs();
					File file21 = new File(dir21);
					File file22 = new File(dir22);
					File file23 = new File(dir23);
					File file24 = new File(dir24);
					if (!(file21.isDirectory() && file22.isDirectory() && file23.isDirectory()
							&& file24.isDirectory())) {
						file21.mkdirs();
						file22.mkdirs();
						file23.mkdirs();
						file24.mkdirs();
					}
				}
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param filePath
	 * @param targetPath
	 */
	public static Long filesCopy(String filepath, String targetPath) {
		long copy = 0;
		try {
			File file = new File(filepath);
			if (file.isFile()) {
				copy = Files.copy(Paths.get(filepath), new FileOutputStream(targetPath));
			}
			System.err.println(copy);
		} catch (IOException e) {
			System.err.println("文件未找到！");
			e.printStackTrace();
		}
		// 返回文件大小字节byte
		return copy;
	}

}
