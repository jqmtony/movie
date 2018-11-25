package com.yc.movie.utils;

import java.util.Random;

public class RegxUtils {
	public static final String PWD_REGX = "[A-Za-z\\d.!@#$%^&*]{6,18}"; // ����
	public static final String USERNAME_REGX = "[A-Za-z\\d_]{6,18}"; // �û���
	public static final String REGISTER_CODE_REGX = "[A-Z\\d]{4}[-][A-Z\\d]{4}[-][A-Z\\d]{4}[-][A-Z\\d]{4}"; // ע����
	public static final String NAME_REGX = "^(([\\u4e00-\\u9fa5]{2,8}))$"; // ����
	public static final String ID_NUM_REGX = "[1-9]\\d{16}[0-9X]"; // ���֤��
	public static final String QQ_NUM_REGX = "[1-9]\\d{4,10}"; // QQ��
	public static final String EMAIL_REGX = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$"; // ����
	public static final String AGE_REGX = "\\d{1,3}"; // ����
	public static final String SEX_REGX = "[��Ů]"; // �Ա�
	public static final String TEL_NUM_REGX = "[1-9]\\d{10}"; // �ֻ�����
	private static Random ra = new Random(); // ���������
	
}
