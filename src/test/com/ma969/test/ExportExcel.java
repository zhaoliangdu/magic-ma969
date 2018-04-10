
package com.ma969.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年12月4日下午1:21:03
 */
public class ExportExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String exportExcel = exportExcel();
		System.err.println(exportExcel);
	}

	public static String exportExcel() {

		// 创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建HSSFCell对象
		HSSFSheet sheet = wb.createSheet("测试数据表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row0 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row0.createCell(0);
		// 设置单元格内容
		cell.setCellValue("测试数据表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		// 在sheet里创建第二行
		HSSFRow row1 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		row1.createCell(0).setCellValue("姓名");
		row1.createCell(1).setCellValue("班级");
		row1.createCell(2).setCellValue("笔试成绩");
		row1.createCell(3).setCellValue("机试成绩");
		// 在sheet里创建第三行
		int line = 12, sl = 2;

		for (int i = sl; i < line; i++) {
			HSSFRow createRow = sheet.createRow(i);
			createRow.createCell(0).setCellValue("李明");
			createRow.createCell(1).setCellValue("As178");
			createRow.createCell(2).setCellValue(87);
			createRow.createCell(3).setCellValue(78);
		}
		// 输出Excel文件
		String path = "";
		try {
			path = "d:/exportdata/" + LocalDate.now() + "_student.xls";
			FileOutputStream output = new FileOutputStream(path);
			wb.write(output);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return path;
	}
}
