package com.yc.movie.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.bean.Images;
import com.yc.movie.bean.Indent;
import com.yc.movie.bean.Integral;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.utils.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 将用户插入到数据库中
	 * @param user
	 * @throws SQLException 
	 */
	public void insertUser(Users user) throws SQLException {
		String sql = "insert into users values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {user.getUserId(),user.getUserName(),user.getUserAccount(),
				user.getUserEmail(),user.getUserPwd(),
				user.getUserCreateTime(),user.getUserTel(),
				user.getUserAddr(),user.getUserStatus()};
		qr.update(sql,params);
	}

	/**
	 * 根据字段集和参数集查询用户是否存在    不存在返回null
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
//		System.out.println("sql:"+sql);
		List<Users> result = qr.query(sql, new BeanListHandler<Users>(Users.class),params);
		if(result.size() > 0)
			return result.get(0);
		return null;
	}

	/**
	 * 添加登录日志对象
	 * @param ulr
	 * @throws SQLException 
	 */
	public void insertULR(UserLoginRecord ulr) throws SQLException {
		String sql = "insert into userloginrecord values(?,?,?,?)";
		Object[] params = {ulr.getUlrId(),ulr.getUlrLoginTime(),ulr.getUlrLoginIp(),ulr.getUser().getUserId()};
		qr.update(sql, params);
	}

	/**
	 * 修改密码
	 * @param user
	 * @throws SQLException 
	 */
	public void alterPwd(Users user) throws SQLException {
		String sql = "update users set userPwd=? where userAccount=?";
		Object[] params = {user.getUserPwd(),user.getUserAccount()};
		qr.update(sql, params);
	}

	/**
	 * 添加积分卡
	 * @param in 积分卡对象
	 * @throws SQLException 
	 */
	public void insertIntegral(Integral in) throws SQLException {
		String sql = "insert into integral values(?,?,?)";
		Object[] params = {in.getIntegralId(),in.getUser().getUserId(),in.getIntegralCount()};
		qr.update(sql, params);
	}

	/**
	 * 根据ID创建User对象
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public Users createUser(Users user) throws SQLException {
		String sql = "select * from ticket where ticketBuyBy=?";
		List<Ticket> ticketList = qr.query(sql, new BeanListHandler<Ticket>(Ticket.class),user.getUserId());
		user.setTicketList(ticketList);
		
		sql = "select * from images where imgUserId=?";
		List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),user.getUserId());
		user.setImgList(imgList);
		
		sql = "select * from indent where indentUserId=?";
		List<Indent> indentList = qr.query(sql, new BeanListHandler<Indent>(Indent.class),user.getUserId());
		user.setIndentList(indentList);
		
		sql = "select * from integral where integralUserId=?";
		List<Integral> integralList = qr.query(sql, new BeanListHandler<Integral>(Integral.class),user.getUserId());
		if(integralList.size() > 0) 
			user.setIntegral(integralList.get(0));
		
		sql = "select * from userloginrecord where ulrUserId=?";
		List<UserLoginRecord> ulrList = qr.query(sql, new BeanListHandler<UserLoginRecord>(UserLoginRecord.class),user.getUserId());
		user.setUlrList(ulrList);
		return user;
	}
}
