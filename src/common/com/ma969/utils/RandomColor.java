/**
 * 随机生成颜色
 */
package com.ma969.utils;

import java.util.Random;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:49:07
 */
public class RandomColor {
	/**
	 * 生成随机颜色
	 * @return
	 */
	public static String randomColor() {
		// 红色
		String red;
		// 绿色
		String green;
		// 蓝色
		String blue;
		// 生成随机对象
		Random random = new Random();
		// 生成红色颜色代码
		red = Integer.toHexString(random.nextInt(256)).toUpperCase();
		// 生成绿色颜色代码
		green = Integer.toHexString(random.nextInt(256)).toUpperCase();
		// 生成蓝色颜色代码
		blue = Integer.toHexString(random.nextInt(256)).toUpperCase(); 
		// 判断红色代码的位
		red = red.length() == 1 ? "0" + red : red;
		// 判断绿色代码的位
		green = green.length() == 1 ? "0" + green : green;
		// 判断蓝色代码的位
		blue = blue.length() == 1 ? "0" + blue : blue;
		// 生成十六进制颜色
		String color = "#" + red + green + blue; 
		return color;
	}
}
