package com.yc.movie.admin.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.utils.TxQueryRunner;

public class AdminDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * ���������ַ��ѯAdmins����    û�в鵽����null
	 * @param adminEmail	�����ַ
	 * @return Admins����
	 * @throws SQLException 
	 */
	public Admins findAdminByEmail(String adminEmail) throws SQLException {
		String sql = "select * from admins where adminEmail=?";
		List<Admins> result = qr.query(sql, new BeanListHandler<Admins>(Admins.class),adminEmail);
		if(result.size() > 0) return result.get(0);
			return null;
	}

	/**
	 * ���������ַ�������ѯAdmins����   û�в鵽����null
	 * @param adminEmail	�����ַ
	 * @param adminPwd	����
	 * @return	Admins����
	 * @throws SQLException 
	 */
	public Admins findAdminByPwd(String adminEmail, String adminPwd) throws SQLException {
		String sql = "selecet * from admins where adminEmail=? and adminPwd=?";
		List<Admins> result = qr.query(sql,new BeanListHandler<Admins>(Admins.class),adminEmail,adminPwd);
		if(result.size()>0){
			return result.get(0);
		}
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

	/**
	 * ����Id��ѯAdmins����  û�в鵽����null
	 * @param alterId	ID
	 * @return	Admins����
	 * @throws SQLException 
	 */
	public Admins findAdminById(Long alterId) throws SQLException {
		String sql = "select * from admins where adminId=?";
		List<Admins> result = qr.query(sql, new BeanListHandler<Admins>(Admins.class),alterId);
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * �޸�����
	 * @param form	Admins����
	 * @throws SQLException 
	 */
	public void alterPwd(Admins form) throws SQLException {
		String sql ="update admins set adminPwd=? where adminEmail=?";
		Object[] params = {form.getAdminPwd(),form.getAdminEmail()};
		qr.update(sql,params);
	}

	/**
	 * ͨ��ע������ҹ���Ա����   ���û�ҵ��ͷ���null
	 * @param adminRegisterCode
	 * @return
	 * @throws SQLException 
	 */
	public Admins findAdminByRegisterCode(String adminRegisterCode) throws SQLException {
		String sql="select * from admins where adminRegisterCode=?";
		List<Admins> result = qr.query(sql, new BeanListHandler<Admins>(Admins.class),adminRegisterCode);
		if(result.size() > 0)
			return result.get(0);
		return null;
	}
	
}
