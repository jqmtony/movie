package com.yc.movie.merchant.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.bean.Images;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.utils.TxQueryRunner;

public class MerchantDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 通过邮箱查找
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	public Merchant findMerchantByEmail(String email) throws SQLException {
		String sql = "select * from merchant where merEmail=?";
		List<Merchant> list = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),email);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 通过邮箱和密码查找
	 * @param email
	 * @param pwd
	 * @return
	 * @throws SQLException 
	 */
	public Merchant findMerchantByEmailAndPwd(String email, String pwd) throws SQLException {
		String sql = "select * from merchant where merEmail=? and merPwd=?";
		Object[] params = {email,pwd};
		List<Merchant> list = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),params);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 创建完整的商户对象
	 * @param me
	 * @return
	 * @throws SQLException 
	 */
	public Merchant createMerchant(Merchant me) throws SQLException {
		String sql = "select * from images where imgMerchantId=?";
		List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),me.getMerId());
		me.setImgList(imgList);
		
		sql = "select * from movies where movieMerId=?";
		List<Movies> movieList = qr.query(sql, new BeanListHandler<Movies>(Movies.class),me.getMerId());
		me.setMovieList(movieList);
		return me;
	}

	/**
	 * 添加记录
	 * @param me
	 * @throws SQLException 
	 */
	public void insertMerchant(Merchant me) throws SQLException {
		String sql = "insert into merchant values(?,?,?,?,?,?,?,?)";
		Object[] params = {me.getMerId(),me.getMerName(),me.getMerTel(),
				me.getMerEmail(),me.getMerAddr(),me.getMerPwd(),
				me.getMerIDCard(),me.getMerStatus()};
		qr.update(sql, params);
	}

	/**
	 * 修改密码
	 * @param me
	 * @throws SQLException 
	 */
	public void updateByPwd(Merchant me) throws SQLException {
		String sql = "update merchant set merPwd=? where merEmail=?";
		Object[] params = {me.getMerPwd(),me.getMerEmail()};
		qr.update(sql,params);
	}
}
