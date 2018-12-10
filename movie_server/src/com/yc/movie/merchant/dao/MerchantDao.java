package com.yc.movie.merchant.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Classifys;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Protagonists;
import com.yc.movie.bean.Ticket;
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
		String sql = "insert into merchant values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {me.getMerId(),me.getMerName(),me.getMerTel(),
				me.getMerEmail(),me.getMerAddr(),me.getMerPwd(),
				me.getMerIDCard(),me.getMerStatus(),me.getMerStoreName()};
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

	/**
	 * 通过id查询Merchant对象
	 * @param merId
	 * @return
	 * @throws SQLException 
	 */
	public Merchant findMerchantById(Long merId) throws SQLException {
		String sql = "select * from merchant where merId=?";
		List<Merchant> list = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),merId);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	public Merchant findMerchantByIdCard(String merIDCard) throws SQLException {
		String sql = "select * from merchant where merIDCard=?";
		List<Merchant> list = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),merIDCard);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	public Merchant findMerchantByTel(String merTel) throws SQLException {
		String sql = "select * from merchant where merTel=?";
		List<Merchant> list = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),merTel);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 查询所有的类型
	 * @return
	 * @throws SQLException 
	 */
	public List<ClassifyName> findAllClassify() throws SQLException {
		String sql = "select * from classifyname";
		List<ClassifyName> list = qr.query(sql, new BeanListHandler<ClassifyName>(ClassifyName.class));
		if(list.size() > 0)
			return list;
		return null;
	}

	/**
	 * 插入电影到数据库
	 * @param form
	 * @throws SQLException 
	 */
	public void addMovie(Movies form) throws SQLException {
		String sql = "insert into movies values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {form.getMovieId(),form.getMovieMerId(),form.getMovieIntegralNum(),
				form.getMovieName(),form.getMovieGradeNum(),form.getMovieVisitCount(),form.getMovieDescribe(),
				form.getMoviePath(),form.getMoviePrice(),form.getMovieStatus(),form.getMovieCreateTime(),
				form.getMovieTimeLong(),form.getMoviePrevue(),form.getMovieGenre()};
		qr.update(sql, params);
	}

	/**
	 * 通过创建时间查询movie对象
	 * @param movieCreateTime
	 * @throws SQLException 
	 */
	public Movies findMovieByTime(Timestamp movieCreateTime) throws SQLException {
		String sql = "select * from movies where movieCreateTime=?";
		List<Movies> list = qr.query(sql, new BeanListHandler<Movies>(Movies.class),movieCreateTime);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 通过类型名获取类型对象
	 * @param s
	 * @return
	 * @throws SQLException 
	 */
	public ClassifyName findClassifyNameByName(String s) throws SQLException {
		String sql = "select * from classifyname where classifyNameString=?";
		List<ClassifyName> list = qr.query(sql, new BeanListHandler<ClassifyName>(ClassifyName.class),s);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 添加类型
	 * @param c
	 * @throws SQLException 
	 */
	public void addClassify(Classifys c) throws SQLException {
		String sql = "insert into classifys values(?,?,?,?,?,?)";
		Object[] params = {c.getClassifyId(),c.getClassifyMovieId(),c.getClassifyTeleplayId(),c.getClassifyNameObj().getClassifyNameId(),
				c.getClassifyDescribe(),c.getParentClassify()};
		qr.update(sql,params);
	}

	/**
	 * 添加主演
	 * @param p
	 * @throws SQLException 
	 */
	public void addPro(Protagonists p) throws SQLException {
		String sql = "insert into protagonists values(?,?,?,?,?)";
		Object[] params = {p.getProId(),p.getProMovieId(),p.getProTeleplayId(),p.getProName(),p.getProLink()};
		qr.update(sql, params);
	}

	/**
	 * 添加电影票
	 * @param t
	 * @throws SQLException 
	 */
	public void addTicket(Ticket t) throws SQLException {
		String sql = "insert into ticket values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {t.getTicketId(),t.getTicketMovieId(),t.getTicketMerId(),t.getTicketStatus(),
				t.getTicketBuyBy(),t.getTicketStartTime(),t.getIndent(),
				t.getTicketLocation(),t.getTicketMovieTheater(),t.getTicketMovieStartTime(),
				t.getTicketMovieEndTime(),t.getTicketLocationNum()};
		qr.update(sql, params);
	}

	/**
	 * 添加图片
	 * @param img
	 * @throws SQLException 
	 */
	public void addImage(Images img) throws SQLException {
		String sql = "insert into images values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {img.getImgId(),img.getImgMovieId(),img.getImgAdminId(),img.getImgUserId(),
				img.getImgMerchantId(),img.getImgTeleplayId(),img.getImgTicketId(),
				img.getImgNewId(),img.getImgStatus(),img.getImgPath()};
		qr.update(sql, params);
	}

	/**
	 * //通过电影名查找数据库中相同的商户id
	 * @param movieName
	 * @return
	 * @throws SQLException 
	 */
	public String getMovieMerIdByMovieName(String movieName) throws SQLException {
		String sql = "select * from movies where movieName=?";
		List<Movies> list = qr.query(sql, new BeanListHandler<Movies>(Movies.class),movieName);
		if(list.size() > 0)
			return list.get(0).getMovieMerId();
		return null;
	}

	/**
	 * 修改数据库中所有的电影名的商户id
	 * @param oldMovieMerId
	 * @param str
	 * @throws SQLException 
	 */
	public void updateMerchantId(String oldMovieMerId, String newMovieMerId) throws SQLException {
		String sql = "update movies set movieMerId=? where movieMerId=?";
		Object[] params = {newMovieMerId,oldMovieMerId};
		qr.update(sql, params);
	}

	/**
	 * 判断数据库中是否已经有这个电影
	 * @param movieName
	 * @return
	 * @throws SQLException 
	 */
	public Movies haveMovie(String movieName) throws SQLException {
		String sql = "select * from movies where movieName=?";
		List<Movies> list = qr.query(sql, new BeanListHandler<Movies>(Movies.class),movieName);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
}
