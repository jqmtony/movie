package com.yc.movie.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.utils.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * ���û����뵽���ݿ���
	 * @param user
	 * @throws SQLException 
	 */
	public void insertUser(Users user) throws SQLException {
		String sql = "insert into users values(?,?,?,?,?,?,?,?)";
		Object[] params = {user.getUserId(),user.getUserName(),
				user.getUserEmail(),user.getUserPwd(),
				user.getUserCreateTime(),user.getUserTel(),
				user.getUserPayNum(),user.getUserPayPwd()};
		qr.update(sql,params);
	}

	/**
	 * �����ֶμ��Ͳ�������ѯ�û��Ƿ����    �����ڷ���null
	 * @param username
	 * @param selectConf
	 * @return
	 * @throws SQLException 
	 */
	public Users findUserBySelectConf(String[] selectConfs,Object...params) throws SQLException {
		StringBuilder sb = new StringBuilder();
		for(String s:selectConfs)
			sb.append(" and "+s+"=?");
		String sql = "select * from users where 1=1"+sb.toString();
		System.out.println("sql:"+sql);
		List<Users> result = qr.query(sql, new BeanListHandler<Users>(Users.class),params);
		if(result.size() > 0)
			return result.get(0);
		return null;
	}

	/**
	 * ��ӵ�¼��־����
	 * @param ulr
	 * @throws SQLException 
	 */
	public void insertULR(UserLoginRecord ulr) throws SQLException {
		String sql = "insert into userloginrecord values(?,?,?,?)";
		Object[] params = {ulr.getUlrId(),ulr.getUlrLoginTime(),ulr.getUlrLoginIp(),ulr.getUser().getUserId()};
		qr.update(sql, params);
	}

	/**
	 * �޸�����
	 * @param user
	 * @throws SQLException 
	 */
	public void alterPwd(Users user) throws SQLException {
		String sql = "update users set userPwd=? where userId=?";
		Object[] params = {user.getUserPwd(),user.getUserId()};
		qr.update(sql, params);
	}
}
