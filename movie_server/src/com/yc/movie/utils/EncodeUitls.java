package com.yc.movie.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncodeUitls {
	
	/**
	 * 对传入字符串进行BASE64编码并返回
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String encodeByBASE64(String str) throws IOException{
		return new BASE64Encoder().encode(str.getBytes("UTF-8"));
	}
	
	/**
	 * 对传入字符串进行BASE64解码并返回
	 * @param str
	 * @return
	 * @throws IOException 
	 */
	public static String decodeByBASE64(String str) throws IOException{
		return new String(new BASE64Decoder().decodeBuffer(str),"UTF-8");
	}
	
}
