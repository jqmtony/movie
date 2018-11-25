package com.yc.movie.utils;

public class UUID {
	/**
	 * 获取一个32位的不重复的字符串
	 * 	字符串由数字与大写字母组成
	 * @return
	 */
	public static String getUuid() {
		return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
