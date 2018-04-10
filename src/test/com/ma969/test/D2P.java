package com.ma969.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

public class D2P {
	public static void main(String[] args) throws IOException {
		String filename = "d://小菜编程成长记.pdf";
		pdf2doc(filename);
	}

	public static void pdf2doc(String filepath) throws IOException {
		PDDocument doc = PDDocument.load(new File(filepath));
		int pagenumber = doc.getNumberOfPages();

		filepath = filepath.substring(0, filepath.lastIndexOf("."));
		// String dirName = "D:\\pdf\\";// 创建目录D:\\pdf\\a.doc
		String dirName = filepath;// 创建目录D:\\pdf\\a.doc
		// createDir(dirName);// 调用方法创建目录
		String fileName = dirName + ".doc";// 创建文件

		FileOutputStream fos = new FileOutputStream(fileName);
		Writer writer = new OutputStreamWriter(fos, "UTF-8");
		PDFTextStripper stripper = new PDFTextStripper();

		stripper.setSortByPosition(true);// 排序
		// stripper.setWordSeparator("");//pdfbox对中文默认是用空格分隔每一个字，通过这个语句消除空格（视频是这么说的）
		stripper.setStartPage(1);// 设置转换的开始页
		stripper.setEndPage(pagenumber);// 设置转换的结束页
		stripper.writeText(doc, writer);
		writer.close();
		doc.close();
		System.out.println("pdf转换word成功！");
	}

}
