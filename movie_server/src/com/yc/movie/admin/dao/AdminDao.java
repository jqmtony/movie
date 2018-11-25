package com.yc.movie.admin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.utils.TxQueryRunner;

public class AdminDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据邮箱地址查询Admins对象    没有查到返回null
	 * @param adminEmail	邮箱地址
	 * @return Admins对象
	 */
	public Admins findAdminByEmail(String adminEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据邮箱地址和密码查询Admins对象   没有查到返回null
	 * @param adminEmail	邮箱地址
	 * @param adminPwd	密码
	 * @return	Admins对象
	 */
	public Admins findAdminByPwd(String adminEmail, String adminPwd) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 将AdminLoginRecord添加到数据库
	 * @param alr
	 * @throws SQLException 
	 */
	public void addALR(AdminLoginRecord alr) throws SQLException {
		String sql = "insert into adminLoginRecord values(?,?,?,?,?)";
		Object[] params = {alr.getAlrId(),alr.getAlrLoginTime(),alr.getAlrLoginIp(),alr.getAlrAdmin().getAdminId(),alr.getAlrStatus()};
		qr.update(sql, params);
	}

	/**
	 * 根据Id查询Admins对象  没有查到返回null
	 * @param alterId	ID
	 * @return	Admins对象
	 */
	public Admins findAdminById(Long alterId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 修改密码
	 * @param form	Admins对象
	 */
	public void alterPwd(Admins form) {
		// TODO Auto-generated method stub
	}
}
