package com.yc.utils;

import java.util.ResourceBundle;

public class PropertiesUtils {
	private static ResourceBundle bundle;
	
	//加载一次配置文件
	static{	
		bundle = ResourceBundle.getBundle("merchantInfo");
	}
	
	public static String getPropertiesByKey(String key){
		 return bundle.getString(key);
	}
}
