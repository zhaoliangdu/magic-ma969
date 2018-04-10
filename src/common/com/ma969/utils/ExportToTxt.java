/**
 * 导出到文本
 */
package com.ma969.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.ma969.beans.AnalogData;
import com.ma969.beans.CDRData;
import com.ma969.beans.DigitalData;
import com.ma969.beans.RadioData;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年12月6日下午2:05:56
 */
public class ExportToTxt {

	/**
	 * 导出digital数据到txt
	 * 
	 * @param listdata
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String exportDigDataToTxt(List<DigitalData> listdata, String path) throws IOException {
		String tabtxt = "ID-时间-地区-经度-纬度-高度-速度-测试模式-频率-带宽-音频场强-信噪比-MER-误包率-距离-方位角";
		path = path + LocalDate.now() + "_" + System.currentTimeMillis() + "_digitaldata.txt";
		File file = new File(path);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String[] split = tabtxt.split("-");
		for (String string : split) {
			bw.write(string + "\t");
		}
		bw.newLine();
		for (int i = 0; i < listdata.size(); i++) {
			System.err.println("i:" + i);
			try {
				bw.write(listdata.get(i).getId() + "\t");
				bw.write(listdata.get(i).getTime() + "\t");
				bw.write(listdata.get(i).getArea() + "\t");
				bw.write(listdata.get(i).getLongitude() + "\t");
				bw.write(listdata.get(i).getLatitude() + "\t");
				bw.write(listdata.get(i).getHeight() + "\t");
				bw.write(listdata.get(i).getSpeed() + "\t");
				bw.write("数字电视" + "\t");
				bw.write(listdata.get(i).getFrequency() + "\t");
				bw.write(listdata.get(i).getWideBand() + "\t");
				bw.write(listdata.get(i).getFieldStrength() + "\t");
				bw.write(listdata.get(i).getSnr() + "\t");
				bw.write(listdata.get(i).getMer() + "\t");
				bw.write(listdata.get(i).getLdpc() + "\t");
				bw.write(listdata.get(i).getDistance() + "\t");
				bw.write(listdata.get(i).getAngle() + "\t");
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 导出analog数据到txt
	 * 
	 * @param listdata
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String exportAnalogDataToTxt(List<AnalogData> listdata, String path) throws IOException {
		String tabtxt = "ID-时间-地区-经度-纬度-高度-速度-测试模式-频率-带宽-场强-snr-距离-方位角";
		path = path + LocalDate.now() + "_" + System.currentTimeMillis() + "_digitaldata.txt";
		File file = new File(path);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String[] split = tabtxt.split("-");
		for (String string : split) {
			bw.write(string + "\t");
		}
		bw.newLine();
		for (int i = 0; i < listdata.size(); i++) {
			System.err.println("i:" + i);
			try {
				bw.write(listdata.get(i).getId() + "\t");
				bw.write(listdata.get(i).getTime() + "\t");
				bw.write(listdata.get(i).getArea() + "\t");
				bw.write(listdata.get(i).getLongitude() + "\t");
				bw.write(listdata.get(i).getLatitude() + "\t");
				bw.write(listdata.get(i).getHeight() + "\t");
				bw.write(listdata.get(i).getSpeed() + "\t");
				bw.write("模拟电视" + "\t");
				bw.write(listdata.get(i).getFrequency() + "\t");
				bw.write(listdata.get(i).getFieldStrength() + "\t");
				bw.write(listdata.get(i).getSnr() + "\t");
				bw.write(listdata.get(i).getDistance() + "\t");
				bw.write(listdata.get(i).getAngle() + "\t");
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 导出analog数据到txt
	 * 
	 * @param listdata
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String exportCDRDataToTxt(List<CDRData> listdata, String path) throws IOException {
		String tabtxt = "ID-时间-地区-纬度-经度-高度-速度-模式-频率-场强-信噪比-MER-BER-误包率-距离-方位角";
		path = path + LocalDate.now() + "_" + System.currentTimeMillis() + "_digitaldata.txt";
		File file = new File(path);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String[] split = tabtxt.split("-");
		for (String string : split) {
			bw.write(string + "\t");
		}
		bw.newLine();
		for (int i = 0; i < listdata.size(); i++) {
			System.err.println("i:" + i);
			try {
				bw.write(listdata.get(i).getId() + "\t");
				bw.write(listdata.get(i).getTime() + "\t");
				bw.write(listdata.get(i).getArea() + "\t");
				bw.write(listdata.get(i).getLongitude() + "\t");
				bw.write(listdata.get(i).getLatitude() + "\t");
				bw.write(listdata.get(i).getHeight() + "\t");
				bw.write(listdata.get(i).getSpeed() + "\t");
				bw.write("CDR" + "\t");
				bw.write(listdata.get(i).getFrequency() + "\t");
				bw.write(listdata.get(i).getFieldStrength() + "\t");
				bw.write(listdata.get(i).getSnr() + "\t");
				bw.write(listdata.get(i).getMer() + "\t");
				bw.write(listdata.get(i).getBer() + "\t");
				bw.write(listdata.get(i).getLdpc() + "\t");
				bw.write(listdata.get(i).getDistance() + "\t");
				bw.write(listdata.get(i).getAngle() + "\t");
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 导出radio数据到txt
	 * 
	 * @param listdata
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String exportRadioDataToTxt(List<RadioData> listdata, String path) throws IOException {
		String tabtxt = "ID-时间-地区-经度-纬度-高度-速度-测试模式-频率-带宽-场强-频偏-信噪比-调制度-距离-方位角";
		path = path + LocalDate.now() + "_" + System.currentTimeMillis() + "_radiodata.txt";
		File file = new File(path);

		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String[] split = tabtxt.split("-");
		for (String string : split) {
			bw.write(string + "\t");
		}
		bw.newLine();
		int testmode = 2;
		listdata.forEach(radio -> {
			try {
				bw.write(radio.getId() + "\t");
				bw.write(radio.getTime() + "\t");
				bw.write(radio.getArea() + "\t");
				bw.write(radio.getLongitude() + "\t");
				bw.write(radio.getLatitude() + "\t");
				bw.write(radio.getHeight() + "\t");
				bw.write(radio.getSpeed() + "\t");
				if (radio.getTestModeId() == testmode) {
					bw.write("调频" + "\t");
				} else {
					bw.write("调幅" + "\t");
				}
				bw.write(radio.getFrequency() + "\t");
				bw.write(radio.getWideBand() + "\t");
				bw.write(radio.getFieldStrength() + "\t");
				bw.write(radio.getFrequencyOffset() + "\t");
				bw.write(radio.getSignalNoiseRatio() + "\t");
				bw.write(radio.getRegulationSystem() + "\t");
				bw.write(radio.getDistance() + "\t");
				bw.write(radio.getAngle() + "\t");
				bw.newLine();
			} catch (Exception e) {
				e.getMessage();
			}
		});
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
