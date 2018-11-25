package com.yc.movie.admin.dao;

import java.sql.SQLException;

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
	 * @throws SQLException 
	 */
	public void addALR(AdminLoginRecord alr) throws SQLException {
		String sql = "insert into adminLoginRecord values(?,?,?,?,?)";
		Object[] params = {alr.getAlrId(),alr.getAlrLoginTime(),alr.getAlrLoginIp(),alr.getAlrAdmin().getAdminId(),alr.getAlrStatus()};
		qr.update(sql, params);
	}
}
