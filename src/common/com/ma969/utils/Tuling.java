/**
 * 图灵聊天机器人
 */
package com.ma969.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:49:12
 */
@Service
public class Tuling {
	private static final String APIKEY = "532d10190e6b41a4bebfee64e48e2ee2";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		while (true) {
			System.out.print("请输入:");
			String nextLine = new Scanner(System.in).next();
			String chatRobot = chatRobot(nextLine);
			System.out.println(chatRobot);
		}
	}

	/**
	 * 聊天机器人
	 * @param context
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String chatRobot(String context) throws Exception {
		StringBuffer sb = new StringBuffer(); 
		String info = URLEncoder.encode(context, "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + info;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close(); 
		connection.disconnect();
		// {"code":100000,"text":""}
		Map map = new Gson().fromJson(sb.toString(), Map.class);
		String text = (String) map.get("text");
		return text;
	}
}
