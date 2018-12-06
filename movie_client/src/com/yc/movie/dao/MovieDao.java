package com.yc.movie.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.bean.ClassifyName;
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
			if(classifysList.size() > 0){
				for(Classifys c : classifysList){
					sql = "select * from classifyname where classifyNameId=?";
					List<ClassifyName> list1 = qr.query(sql, new BeanListHandler<ClassifyName>(ClassifyName.class),c.getClassifyName());
					if(list1.size() > 0){
						c.setClassifyNameObj(list1.get(0));
					}
				}
				movie.setClassifysList(classifysList);  //�������ͼ���
			}
			
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
		//��ӵ�ӰƱ����
		String sql = "select * from ticket where ticketMovieId=?";
		List<Ticket> ticketList = qr.query(sql, new BeanListHandler<Ticket>(Ticket.class),movie.getMovieId());
		movie.setTicketList(ticketList);
		
		//��ӵ�Ӱ���ͼ���
		sql = "select * from classifys where classifyMovieId=?";
		List<Classifys> classifysList = qr.query(sql, new BeanListHandler<Classifys>(Classifys.class),movie.getMovieId());
		classifysList = createClassifysList(classifysList);
		movie.setClassifysList(classifysList);
		
		//��ӵ�ӰͼƬ����
		sql = "select * from images where imgMovieId=?";
		List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),movie.getMovieId());
		movie.setImgList(imgList);
		
		//��ӵ�Ӱ���ݼ���
		sql = "select * from protagonists where proMovieId=?";
		List<Protagonists> proList = qr.query(sql, new BeanListHandler<Protagonists>(Protagonists.class),movie.getMovieId());
		movie.setProList(proList);
		
		//��ӵ�Ӱ��Ӧ���̻�
		sql = "select * from merchant where merId=?";
		String[] merIds = movie.getMovieMerId().split(";");
		List<Merchant> list = new ArrayList<Merchant>();
		for(String str : merIds){
			List<Merchant> merList = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),Long.parseLong(str));
			if(merList.size() > 0)
				list.add(merList.get(0));
		}
		movie.setMerchantList(list);
		
		
		//��ӵ�Ӱ���ۼ���
		sql = "select * from comment where commentMovieId=?";
		List<Comment> commentList = qr.query(sql, new BeanListHandler<Comment>(Comment.class),movie.getMovieId());
		if(commentList.size() > 0){
			commentList = createCommentListByReply(commentList);
			movie.setCommentList(commentList);
		}
		return movie;
	}

	/**
	 * ����classifysList����   ���ǽ�������������ӽ���
	 * @param classifysList
	 * @return
	 * @throws SQLException 
	 */
	private List<Classifys> createClassifysList(List<Classifys> classifysList) throws SQLException {
		for(Classifys c : classifysList){
			String sql = "select * from classifyname where classifyNameId=?";
			List<ClassifyName> classifyNameList = qr.query(sql, new BeanListHandler<ClassifyName>(ClassifyName.class),c.getClassifyName());
			if(classifyNameList.size() > 0)
				c.setClassifyNameObj(classifyNameList.get(0));
		}
		return classifysList;
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
			
			c.setReplyNum(Long.parseLong(getCommentReplyNum(c)+""));
		}
		return commentList;
	}

	/**
	 * ��ȡ���۵Ļظ�����
	 * @param c
	 * @return
	 */
	private int getCommentReplyNum(Comment c) {
		int i = 0;
		if(c == null || c.getReplyList() == null || c.getReplyList().size() <= 0)
			return i;
		for(Reply r : c.getReplyList()){
			if(r == null || r.getReplySonList() == null || r.getReplySonList().size() <= 0)
				continue;
			for(Reply rSon : r.getReplySonList()){
				i = getReplyNum(rSon,i);
			}
		}
		return i;
	}

	/**
	 * ��ȡ�ظ��Ļظ�����
	 * @param rSon
	 */
	private int getReplyNum(Reply r,int i) {
		if(r == null || r.getReplySonList() == null || r.getReplySonList().size() <= 0)
			return i;
		for(Reply rSon : r.getReplySonList()){
			i = getReplyNum(rSon,i);
			i++;
		}
		return i;
	}

	/**
	 * ����ظ�����
	 * @param replyList
	 * @return
	 * @throws SQLException 
	 */
	private List<Reply> createReplyList(List<Reply> replyList) throws SQLException {
		//ÿһ���ظ�
		for(Reply r : replyList){
			String sql = "select * from users where userId=?";
			List<Users> userList = qr.query(sql,new BeanListHandler<Users>(Users.class),r.getReplyUserId());
			if(userList.size() > 0)
				r.setUser(userList.get(0));
			
			//��Ӷ��Ӽ���
			sql = "select * from reply where replyParId=?";
			List<Reply> replySonList = qr.query(sql, new BeanListHandler<Reply>(Reply.class),r.getReplyId());
			if(replySonList.size() > 0){
				replySonList = createReplyList(replySonList);
				r.setReplySonList(replySonList);
			}else{
				r.setReplySonList(null);
			}
			int i = 0;
			getReplyNum(r,i);
			r.setReplyNum(Long.parseLong(""+i));
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

	/**
	 * ͨ��ID�������۶���
	 * @param replyCommentId
	 * @throws SQLException 
	 */
	public Comment findCommentById(Long replyCommentId) throws SQLException {
		String sql = "select * from comment where commentId=?";
		List<Comment> commentList = qr.query(sql, new BeanListHandler<Comment>(Comment.class),replyCommentId);
		if(commentList.size() > 0)
			return commentList.get(0);
		return null;
	}

	/**
	 * ����ظ��������ݿ�
	 * @param form
	 * @throws SQLException 
	 */
	public void insertReply(Reply r) throws SQLException {
		String sql = "insert into reply values(?,?,?,?,?,?)";
		Object[] params = {r.getReplyId(),r.getReplyUserId(),r.getReplyCommentId(),
				r.getReplyCreateTime(),r.getReplyContent(),r.getReplyParId()};
		qr.update(sql, params);
	}

	/**
	 * ͨ��ID��ѯ�ظ�����
	 * @param replyId
	 * @return
	 * @throws SQLException 
	 */
	public Reply findReplyById(Long replyId) throws SQLException {
		String sql = "select * from reply where replyId=?";
		List<Reply> replyList = qr.query(sql, new BeanListHandler<Reply>(Reply.class),replyId);
		if(replyList.size() > 0)
			return replyList.get(0);
		return null;
	}

	/**
	 * �޸�����
	 * @param movie
	 * @throws SQLException 
	 */
	public void updateMovieGradeNum(Movies movie) throws SQLException {
//		System.out.println(movie.getMovieGradeNum());
		String sql = "update movies set movieGradeNum=? where movieId=?";
		Object[] params = {movie.getMovieGradeNum(),movie.getMovieId()};
		qr.update(sql, params);
	}

	/**
	 * �����̻�id��ȡ�̻�����
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

}
