package com.yc.movie.admin.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.utils.TxQueryRunner;

public class AdminDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * ���������ַ��ѯAdmins����
	 * @param adminEmail	�����ַ
	 * @return Admins����
	 */
	public Admins findAdminByEmail(String adminEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ���������ַ�������ѯAdmins����
	 * @param adminEmail	�����ַ
	 * @param adminPwd	����
	 * @return	Admins����
	 */
	public Admins findAdminByPwd(String adminEmail, String adminPwd) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ��AdminLoginRecord��ӵ����ݿ�
	 * @param alr
	 */
	public void addALR(AdminLoginRecord alr) {
		// TODO Auto-generated method stub
	}
}
