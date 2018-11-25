package com.yc.movie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * ����ָ���������ַ��������룬��������ֵ
	 * @param dateString	2018-01-01
	 * @param mask	yyyy-MM-dd
	 * @return	�������ڶ���
	 * @throws ParseException 
	 */
	public static Date getDate(String dateString,String mask){
		try {
			return new SimpleDateFormat(mask).parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ����ָ�������ڶ�������룬���ظ�ʽ����������ַ���
	 * @param date	���ڶ���
	 * @param mask	yyyy-MM-dd
	 * @return	���������ַ���
	 */
	public static String format(Date date,String mask) {
		return new SimpleDateFormat(mask).format(date);
	}
}
