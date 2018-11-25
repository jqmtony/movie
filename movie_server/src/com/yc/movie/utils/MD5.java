package com.yc.movie.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {
	
	/**
	 * MD5����
	 * @param inStr ��Ҫ���ܵ��ַ���
	 * @return һ��32λ������
	 */
	public static String parseMD5(String inStr){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("����ʧ��");
		}
		char[] charArr = inStr.toCharArray();
		byte[] byteArr = new byte[charArr.length];
		for(int i=0;i<charArr.length;i++){
			byteArr[i] = (byte) charArr[i];
		}
		
		byte[] md5Bytes = md5.digest(byteArr);
		
		StringBuilder hexValue = new StringBuilder();
		
		for(int i=0;i<md5Bytes.length;i++){
			int val = (int)md5Bytes[i] & 0xff;
			if(val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
	}
}
