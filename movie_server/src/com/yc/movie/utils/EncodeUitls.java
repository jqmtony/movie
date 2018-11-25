package com.yc.movie.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncodeUitls {
	
	/**
	 * �Դ����ַ�������BASE64���벢����
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String encodeByBASE64(String str) throws IOException{
		return new BASE64Encoder().encode(str.getBytes("UTF-8"));
	}
	
	/**
	 * �Դ����ַ�������BASE64���벢����
	 * @param str
	 * @return
	 * @throws IOException 
	 */
	public static String decodeByBASE64(String str) throws IOException{
		return new String(new BASE64Decoder().decodeBuffer(str),"UTF-8");
	}
	
}
