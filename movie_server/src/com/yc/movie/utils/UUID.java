package com.yc.movie.utils;

public class UUID {
	/**
	 * ��ȡһ��32λ�Ĳ��ظ����ַ���
	 * 	�ַ������������д��ĸ���
	 * @return
	 */
	public static String getUuid() {
		return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
