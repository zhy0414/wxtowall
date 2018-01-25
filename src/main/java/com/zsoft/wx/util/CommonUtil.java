package com.zsoft.wx.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import redis.clients.jedis.Jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.zsoft.wx.domain.outbound.CustomText;
import com.zsoft.wx.model.TowallMessage;


/**
 * 通用工具类
 * 获取 access_token 值
 * @author zhangyong
 */
public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	//private static String ACCESS_TOKEN_KEY = "my_access_token";

	// 凭证获取（GET）
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	
	/**
	 * 获取接口访问凭证
	 * 先在 redis 里面查找，如果没有就去微信服务器调取，并放入redis
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static String getToken() {
		String accessTokenKey = ProfileUtil.getInstance().read("redis.properties", "ACCESS_TOKEN_KEY");
		
		Jedis jedis = RedisUtil.getJedis();
		String accessToken = jedis.get(accessTokenKey);
		//String accessToken = null;		//清空redis中access_token使用
		if(null == accessToken){
			String requestUrl = TOKEN_URL.replace("APPID", ProfileUtil.getInstance().read("wx.properties", "appID")).replace("APPSECRET", ProfileUtil.getInstance().read("wx.properties", "appsecret"));
			JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
			
			log.info("微信端获取 access_token，值【"+jsonObject.getString("access_token")+"】，有效期【"+jsonObject.getIntValue("expires_in")+"】");
			//System.out.println("微信端获取 access_token，有效期【"+jsonObject.getInt("expires_in")+"】");
			
			if(null != jsonObject){
				jedis.setex(accessTokenKey, jsonObject.getIntValue("expires_in"), jsonObject.getString("access_token"));
				accessToken = jsonObject.getString("access_token");
			}
		}
		
		RedisUtil.returnResource(jedis);
		
		return accessToken;
	}
	
	/**
	 * 发送https请求
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);

			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return jsonObject;
	}

	
	
	/**
	 * URL编码（utf-8）
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
}