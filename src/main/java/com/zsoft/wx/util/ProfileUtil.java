package com.zsoft.wx.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfileUtil {
	private Logger log = LoggerFactory.getLogger("ProfileUtil.class");
	
	private static class ProfileUtilHandler{
		private static ProfileUtil instance = new ProfileUtil();
	}
	
	private ProfileUtil(){}
	
	public static ProfileUtil getInstance(){
		return ProfileUtilHandler.instance;
	}
	
	public String read(String filename, String key) {
		
		String str = File.separator;
		//非静态方法时使用：
		InputStream path = this.getClass().getResourceAsStream(".." + str + ".." + str+".."+str+".."+str+filename);
		//InputStream path = ProfileUtil.class.getClass().getResourceAsStream(".." + str + ".." + str+".."+str+".."+str+"wx.properties");

		Properties pros = new Properties();
		try {
			pros.load(path);
		} catch (IOException ex) {
			// System.out.println("file is not exist");
			log.error("配置文件 "+filename+" 不存在！");
			System.out.println("资源文件不存在");
		}
		
		String value = pros.getProperty(key);
		
		return value;
	}
}
