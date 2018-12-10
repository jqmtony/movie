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
	 * ͨ���������
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
	 * ͨ��������������
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
	 * �����������̻�����
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
	 * ��Ӽ�¼
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
	 * �޸�����
	 * @param me
	 * @throws SQLException 
	 */
	public void updateByPwd(Merchant me) throws SQLException {
		String sql = "update merchant set merPwd=? where merEmail=?";
		Object[] params = {me.getMerPwd(),me.getMerEmail()};
		qr.update(sql,params);
	}

	/**
	 * ͨ��id��ѯMerchant����
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
	 * ��ѯ���е�����
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
	 * �����Ӱ�����ݿ�
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
	 * ͨ������ʱ���ѯmovie����
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
	 * ͨ����������ȡ���Ͷ���
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
	 * �������
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
	 * �������
	 * @param p
	 * @throws SQLException 
	 */
	public void addPro(Protagonists p) throws SQLException {
		String sql = "insert into protagonists values(?,?,?,?,?)";
		Object[] params = {p.getProId(),p.getProMovieId(),p.getProTeleplayId(),p.getProName(),p.getProLink()};
		qr.update(sql, params);
	}

	/**
	 * ��ӵ�ӰƱ
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
	 * ���ͼƬ
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
	 * //ͨ����Ӱ���������ݿ�����ͬ���̻�id
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
	 * �޸����ݿ������еĵ�Ӱ�����̻�id
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
	 * �ж����ݿ����Ƿ��Ѿ��������Ӱ
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
