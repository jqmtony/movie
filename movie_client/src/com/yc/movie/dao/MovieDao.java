package com.yc.movie.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.bean.Classifys;
import com.yc.movie.bean.Comment;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Protagonists;
import com.yc.movie.bean.Reply;
import com.yc.movie.bean.Teleplay;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.Users;
import com.yc.utils.TxQueryRunner;

public class MovieDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * ��ѯ���е�movie
	 * @return
	 * @throws SQLException 
	 */
	public List<Movies> findAllMovie() throws SQLException {
		String sql = "select * from movies";
		List<Movies> movieList = qr.query(sql, new BeanListHandler<Movies>(Movies.class));
		if(movieList.size() < 1)
			return null;
		for(Movies movie : movieList){
			//��ѯ��ǰmovie��Ӧ�����ͼ���
			sql = "select * from classifys where classifyMovieId=?";
			List<Classifys> classifysList = qr.query(sql, new BeanListHandler<Classifys>(Classifys.class),movie.getMovieId());
			if(classifysList.size() > 0)
				movie.setClassifysList(classifysList);  //�������ͼ���
			//��ѯ��ǰmovie��Ӧ��ͼƬ����
			sql = "select * from images where imgMovieId=?";
			List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),movie.getMovieId());
			if(imgList.size() > 0)
				movie.setImgList(imgList);  //����ͼƬ����
		}
		System.out.println(movieList);
		return movieList;
	}

	/**
	 * ͨ��ID����movie
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Movies findMovieById(Long id) throws SQLException {
		String sql = "select * from movies where movieId=?";
		List<Movies> result = qr.query(sql, new BeanListHandler<Movies>(Movies.class),id);
		if(result.size() > 0)
			return result.get(0);
		return null;
	}

	/**
	 * ����һ��movie����   (��movie������Ӷ�Ӧ����)
	 * @param movie
	 * @return
	 * @throws SQLException 
	 */
	public Movies createMovie(Movies movie) throws SQLException {
		String sql = "select * from ticket where ticketMovieId=?";
		List<Ticket> ticketList = qr.query(sql, new BeanListHandler<Ticket>(Ticket.class),movie.getMovieId());
		movie.setTicketList(ticketList);
		
		sql = "select * from classifys where classifyMovieId=?";
		List<Classifys> classifysList = qr.query(sql, new BeanListHandler<Classifys>(Classifys.class),movie.getMovieId());
		movie.setClassifysList(classifysList);
		
		sql = "select * from images where imgMovieId=?";
		List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),movie.getMovieId());
		movie.setImgList(imgList);
		
		sql = "select * from protagonists where proMovieId=?";
		List<Protagonists> proList = qr.query(sql, new BeanListHandler<Protagonists>(Protagonists.class),movie.getMovieId());
		movie.setProList(proList);
		
		sql = "select * from merchant where merId=?";
		List<Merchant> merList = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),movie.getMovieMerId());
		if(merList.size() > 0)
			movie.setMerchant(merList.get(0));
		
		sql = "select * from comment where commentMovieId=?";
		List<Comment> commentList = qr.query(sql, new BeanListHandler<Comment>(Comment.class),movie.getMovieId());
		if(commentList.size() > 0){
			commentList = createCommentListByReply(commentList);
			movie.setCommentList(commentList);
		}
		return movie;
	}

	/**
	 * ��������   ������������Ҫ�Ķ����װ��������
	 * @param commentList
	 * @return
	 * @throws SQLException 
	 */
	private List<Comment> createCommentListByReply(List<Comment> commentList) throws SQLException {
		for(Comment c:commentList){
			String sql = "select * from reply where replyCommentId=?";
			List<Reply> replyList = qr.query(sql, new BeanListHandler<Reply>(Reply.class),c.getCommentId());
			if(replyList.size() > 0){
				replyList = createReplyList(replyList);
				c.setReplyList(replyList);
			}
				
			
			sql = "select * from users where userId=?";
			List<Users> userList = qr.query(sql,new BeanListHandler<Users>(Users.class),c.getCommentUserId());
			if(userList.size() > 0)
				c.setUser(userList.get(0));
		}
		return commentList;
	}

	/**
	 * ����ظ�����
	 * @param replyList
	 * @return
	 * @throws SQLException 
	 */
	private List<Reply> createReplyList(List<Reply> replyList) throws SQLException {
		for(Reply r : replyList){
			String sql = "select * from users where userId=?";
			List<Users> userList = qr.query(sql,new BeanListHandler<Users>(Users.class),r.getReplyUserId());
			if(userList.size() > 0)
				r.setUser(userList.get(0));
		}
		return replyList;
	}

	/**
	 * ����id��ѯteleplay����
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Teleplay findTeleplayById(Long id) throws SQLException {
		String sql = "select * from teleplay where teleplayId=?";
		List<Teleplay> result = qr.query(sql, new BeanListHandler<Teleplay>(Teleplay.class),id);
		if(result.size() > 0)
			return result.get(0);
		return null;
	}

	/**
	 * �������Ӿ����
	 * @param teleplay
	 * @return
	 * @throws SQLException 
	 */
	public Teleplay createTeleplay(Teleplay teleplay) throws SQLException {
		String sql = "select * from merchant where merId=?";
		List<Merchant> merList = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),teleplay.getTeleplayMerId());
		if(merList.size() > 0)
			teleplay.setMerchant(merList.get(0));
		
		sql = "select * from classifys where classifyTeleplayId=?";
		List<Classifys> classifysList = qr.query(sql, new BeanListHandler<Classifys>(Classifys.class),teleplay.getTeleplayId());
		teleplay.setClassifysList(classifysList);
		
		sql = "select * from images where imgTeleplayId=?";
		List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),teleplay.getTeleplayId());
		teleplay.setImgList(imgList);
		
		sql = "select * from protagonists where proTeleplayId=?";
		List<Protagonists> proList = qr.query(sql, new BeanListHandler<Protagonists>(Protagonists.class),teleplay.getTeleplayId());
		teleplay.setProList(proList);
		return teleplay;
	}

	/**
	 * �����۲��뵽���ݿ�
	 * @param form
	 * @throws SQLException 
	 */
	public void insertComment(Comment c) throws SQLException {
		String sql = "insert into comment values(?,?,?,?,?,?,?)";
		Object[] params = {c.getCommentId(),c.getCommentUserId(),c.getCommentReplyId(),
				c.getCommentMovieId(),c.getCommentTeleplayId(),c.getCommentContent(),c.getCommentCreateTime()};
		qr.update(sql, params);
	}

}
