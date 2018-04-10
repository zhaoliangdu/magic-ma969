/**
 * 导出数据到Excel
 */
package com.ma969.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.ma969.beans.AnalogData;
import com.ma969.beans.CDRData;
import com.ma969.beans.DigitalData;
import com.ma969.beans.RadioData;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年12月4日下午1:21:03
 */
public class ExportExcel {
	/**
	 * 导出digital数据到excel
	 * 
	 * @param data
	 * @param path
	 * @return
	 */
	public static String exportDigToExcel(List<DigitalData> data, String path) {
		String tabtxt = "ID-时间-地区-经度-纬度-高度-速度-测试模式-频率-带宽-音频场强-信噪比-MER-误包率-距离-方位角";
		String[] split = tabtxt.split("-");
		List<String> tablist = new ArrayList<>();
		for (String string : split) {
			tablist.add(string);
		}
		// 创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建HSSFCell对象
		HSSFSheet sheet = wb.createSheet("测试数据表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue("测试数据表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		for (int y = 0; y < tablist.size(); y++) {
			row2.createCell(y).setCellValue(tablist.get(y));
		}
		Iterator<DigitalData> iterator = data.iterator();
		int x = 2;
		DigitalData digdata = null;
		while (iterator.hasNext()) {
			digdata = iterator.next();
			HSSFRow createRow = sheet.createRow(x);
			createRow.createCell(0).setCellValue(digdata.getId());
			createRow.createCell(1).setCellValue(digdata.getTime());
			createRow.createCell(2).setCellValue(digdata.getArea());
			createRow.createCell(3).setCellValue(digdata.getLongitude());
			createRow.createCell(4).setCellValue(digdata.getLatitude());
			createRow.createCell(5).setCellValue(digdata.getHeight());
			createRow.createCell(6).setCellValue(digdata.getSpeed());
			createRow.createCell(7).setCellValue("数字电视");
			createRow.createCell(8).setCellValue(digdata.getFrequency());
			createRow.createCell(9).setCellValue(digdata.getWideBand());
			createRow.createCell(10).setCellValue(digdata.getFieldStrength());
			createRow.createCell(11).setCellValue(digdata.getSnr());
			createRow.createCell(12).setCellValue(digdata.getMer());
			createRow.createCell(13).setCellValue(digdata.getLdpc());
			createRow.createCell(14).setCellValue(digdata.getDistance());
			createRow.createCell(15).setCellValue(digdata.getAngle());
			x++;
		}
		// 输出Excel文件
		FileOutputStream output = null;
		try {
			path += LocalDate.now() + "_digdata.xls";
			output = new FileOutputStream(path);
			wb.write(output);
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * 导出radio 数据到 excel
	 * 
	 * @param data
	 * @param path
	 * @return
	 */
	public static String exportRadioToExcel(List<RadioData> data, String path) {
		String tabtxt = "ID-时间-地区-经度-纬度-高度-速度-测试模式-频率-带宽-场强-频偏-信噪比-调制度-距离-方位角";
		String[] split = tabtxt.split("-");
		List<String> tablist = new ArrayList<>();
		for (String string : split) {
			tablist.add(string);
		}
		// 创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建HSSFCell对象
		HSSFSheet sheet = wb.createSheet("测试数据表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue("测试数据表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		for (int y = 0; y < tablist.size(); y++) {
			row2.createCell(y).setCellValue(tablist.get(y));
		}
		Iterator<RadioData> iterator = data.iterator();
		int x = 2;
		RadioData radio = null;
		while (iterator.hasNext()) {
			radio = iterator.next();
			HSSFRow createRow = sheet.createRow(x);
			createRow.createCell(0).setCellValue(radio.getId());
			createRow.createCell(1).setCellValue(radio.getTime());
			createRow.createCell(2).setCellValue(radio.getArea());
			createRow.createCell(3).setCellValue(radio.getLongitude());
			createRow.createCell(4).setCellValue(radio.getLatitude());
			createRow.createCell(5).setCellValue(radio.getHeight());
			createRow.createCell(6).setCellValue(radio.getSpeed());
			if (radio.getTestModeId() == 2) {
				createRow.createCell(7).setCellValue("调频");
			} else {
				createRow.createCell(7).setCellValue("调幅");
			}
			createRow.createCell(8).setCellValue(radio.getFrequency());
			createRow.createCell(9).setCellValue(radio.getWideBand());
			createRow.createCell(10).setCellValue(radio.getFieldStrength());
			createRow.createCell(11).setCellValue(radio.getFrequencyOffset());
			createRow.createCell(12).setCellValue(radio.getSignalNoiseRatio());
			createRow.createCell(13).setCellValue(radio.getRegulationSystem());
			createRow.createCell(14).setCellValue(radio.getDistance());
			createRow.createCell(15).setCellValue(radio.getAngle());
			x++;
		}
		// 输出Excel文件
		FileOutputStream output = null;
		try {
			path += LocalDate.now() + "_radiodata.xls";
			output = new FileOutputStream(path);
			wb.write(output);
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * 导出CDR 数据到 excel
	 * 
	 * @param data
	 * @param path
	 * @return
	 */
	public static String exportCDRToExcel(List<CDRData> data, String path) {
		String tabtxt = "ID-时间-地区-纬度-经度-高度-速度-模式-频率-场强-信噪比-MER-BER-误包率-距离-方位角";
		String[] split = tabtxt.split("-");
		List<String> tablist = new ArrayList<>();
		for (String string : split) {
			tablist.add(string);
		}
		// 创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建HSSFCell对象
		HSSFSheet sheet = wb.createSheet("测试数据表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue("测试数据表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		for (int y = 0; y < tablist.size(); y++) {
			row2.createCell(y).setCellValue(tablist.get(y));
		}
		Iterator<CDRData> iterator = data.iterator();
		int x = 2;
		CDRData cdr = null;
		while (iterator.hasNext()) {
			cdr = iterator.next();
			HSSFRow createRow = sheet.createRow(x);
			createRow.createCell(0).setCellValue(cdr.getId());
			createRow.createCell(1).setCellValue(cdr.getTime());
			createRow.createCell(2).setCellValue(cdr.getArea());
			createRow.createCell(3).setCellValue(cdr.getLongitude());
			createRow.createCell(4).setCellValue(cdr.getLatitude());
			createRow.createCell(5).setCellValue(cdr.getHeight());
			createRow.createCell(6).setCellValue(cdr.getSpeed());
			createRow.createCell(7).setCellValue("CDR");
			createRow.createCell(8).setCellValue(cdr.getFrequency()); 
			createRow.createCell(9).setCellValue(cdr.getFieldStrength());
			createRow.createCell(10).setCellValue(cdr.getSnr());
			createRow.createCell(11).setCellValue(cdr.getMer());
			createRow.createCell(12).setCellValue(cdr.getBer());
			createRow.createCell(13).setCellValue(cdr.getLdpc());
			createRow.createCell(14).setCellValue(cdr.getDistance());
			createRow.createCell(15).setCellValue(cdr.getAngle());
			x++;
		}
		// 输出Excel文件
		FileOutputStream output = null;
		try {
			path += LocalDate.now() + "_cdrdata.xls";
			output = new FileOutputStream(path);
			wb.write(output);
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * 导出Analog 数据到 excel
	 * 
	 * @param data
	 * @param path
	 * @return
	 */
	public static String exportAnalogToExcel(List<AnalogData> data, String path) {
		String tabtxt = "ID-时间-地区-纬度-经度-高度-速度-模式-频率-场强-信噪比-距离-方位角";
		String[] split = tabtxt.split("-");
		List<String> tablist = new ArrayList<>();
		for (String string : split) {
			tablist.add(string);
		}
		// 创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建HSSFCell对象
		HSSFSheet sheet = wb.createSheet("测试数据表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue("测试数据表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		for (int y = 0; y < tablist.size(); y++) {
			row2.createCell(y).setCellValue(tablist.get(y));
		}
		Iterator<AnalogData> iterator = data.iterator();
		int x = 2;
		AnalogData analogData = null;
		while (iterator.hasNext()) {
			analogData = iterator.next();
			HSSFRow createRow = sheet.createRow(x);
			createRow.createCell(0).setCellValue(analogData.getId());
			createRow.createCell(1).setCellValue(analogData.getTime());
			createRow.createCell(2).setCellValue(analogData.getArea());
			createRow.createCell(3).setCellValue(analogData.getLongitude());
			createRow.createCell(4).setCellValue(analogData.getLatitude());
			createRow.createCell(5).setCellValue(analogData.getHeight());
			createRow.createCell(6).setCellValue(analogData.getSpeed());
			createRow.createCell(7).setCellValue("模拟电视");
			createRow.createCell(8).setCellValue(analogData.getFrequency());
			createRow.createCell(9).setCellValue(analogData.getFieldStrength());
			createRow.createCell(10).setCellValue(analogData.getSnr());
			createRow.createCell(11).setCellValue(analogData.getDistance());
			createRow.createCell(12).setCellValue(analogData.getAngle());
			x++;
		}
		// 输出Excel文件
		FileOutputStream output = null;
		try {
			path += LocalDate.now() + "_analogdata.xls";
			output = new FileOutputStream(path);
			wb.write(output);
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return path;
	}
}
