package com.ma969.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.google.gson.Gson;

@Service
public class MagicRobot {
	public static final String APP_ID = "10809891";
	public static final String BD_API_KEY = "C8Uu1pLmiggsXdLfBUqtKMj1";
	public static final String SECRET_KEY = "NYCpK1HVo35QK5WIIpDewzkFbvfqwDkD";
	private static final String TL_API_KEY = "532d10190e6b41a4bebfee64e48e2ee2";

	@SuppressWarnings({ "unused", "unchecked" })
	public String tuLingAI(String context) {
		// String INFO = URLEncoder.encode("北京今日天气", "utf-8");
		HttpURLConnection connection;
		StringBuffer sb = null;
		try {
			String INFO = URLEncoder.encode(context, "utf-8");
			String getURL = "http://www.tuling123.com/openapi/api?key=" + TL_API_KEY + "&info=" + INFO;
			URL getUrl = new URL(getURL);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();

			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			sb = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			// 断开连接
			connection.disconnect();
			System.out.println(sb);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string = sb.toString();

		Gson gson = new Gson();
		HashMap<String, String> map = new HashMap<>();
		Map<String, String> maps = gson.fromJson(string, Map.class);
		String text = (String) maps.get("text");
		String url = (String) maps.get("url");
		String location = (String) maps.get("location");
		if(url!=null) {
			text = url != null ? text + "-<a href='" + url + "' target='_blank'>打开链接</a>" : text;
		}else if (location!=null) {
			text = location != null ? text + "-" + location : text;
		} 
		return text;
	}

	/**
	 * 语音合成
	 * 
	 * @param context
	 */
	public static int speechSynthesis(String context) {
		// 初始化一个AipSpeech
		AipSpeech client = new AipSpeech(APP_ID, BD_API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000); 

		// 调用接口
		TtsResponse res = client.synthesis(context, "zh", 1, null);
		byte[] data = res.getData();
		JSONObject res1 = res.getResult();
		if (data != null) {
			try {
				Util.writeBytesToFileSystem(data, "D://magic-ma969//tuling//tulingsay.mp3");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (res1 != null) {
			System.out.println(res1.toString(2));
		}
		return 1;
	}

}
