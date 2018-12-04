package com.yc.movie.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import com.yc.exception.MovieException;
import com.yc.movie.bean.Comment;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Reply;
import com.yc.movie.bean.Teleplay;
import com.yc.movie.bean.Users;
import com.yc.movie.dao.MovieDao;
import com.yc.utils.JdbcUtils;

public class MovieService{
	private MovieDao md = new MovieDao();

	/**
	 * ��ѯ���е�movie
	 * @return
	 * @throws MovieException 
	 */
	public List<Movies> findAllMovie() throws MovieException {
		try {
			return md.findAllMovie();
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * ͨ��ID���ҵ�Ӱ
	 * @param id
	 * @return
	 * @throws MovieException 
	 */
	public Movies findMovieById(Long id) throws MovieException {
		try {
			Movies movie =  md.findMovieById(id);	//ͨ��id�õ�movie����
			movie = md.createMovie(movie); //����Ӧ������ӵ�movie������
			return movie;
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
		}  
	}

	/**
	 * ͨ��ID���ҵ��Ӿ�
	 * @param id
	 * @return
	 * @throws MovieException 
	 */
	public Teleplay findTeleplayById(Long id) throws MovieException {
		try {
			Teleplay teleplay =  md.findTeleplayById(id);	//ͨ��id�õ����Ӿ����
			teleplay = md.createTeleplay(teleplay); //����Ӧ������ӵ����Ӿ������
			return teleplay;
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
		}  
	}

	/**
	 * ��������ҵ��
	 * @param form	form����  commentMovieId  commentContent
	 * @param loginedUser
	 * @throws MovieException 
	 */
	public void sendComment(Comment form, Users loginedUser) throws MovieException {
		//�ж�commentContent�Ƿ�Ϊnull
		if(form.getCommentContent() == null || form.getCommentContent().trim().isEmpty())
			throw new MovieException("������Ϣ����Ϊ��");
		
		//�������дʻ�
		Properties p = new Properties();
		StringBuilder sb = new StringBuilder();
		try {
			p.load(this.getClass().getClassLoader().getResourceAsStream("regxComment.properties"));
			for(Iterator<Entry<Object,Object>> it = p.entrySet().iterator();it.hasNext();){
				Entry<Object,Object> me = it.next();
				String value = String.valueOf(me.getValue());
				if(form.getCommentContent().contains(value)){
					sb.append(value+" ");
				}
			}
			if(!"".equals(sb.toString()) || !sb.toString().isEmpty())
				throw new MovieException("��Ҫ���͵������а������дʻ㣬���ܷ������дʻ㣺"+sb.toString());
		} catch (IOException e) {
			throw new MovieException("ϵͳ�쳣�������ļ����𻵣�");
		}
		
		//��������
		form.setCommentCreateTime(new Timestamp(new Date().getTime()));  //��������ʱ��
		form.setCommentUserId(loginedUser.getUserId());  //����������ID
		
		try {
			JdbcUtils.beginTransaction();
			md.insertComment(form);//�����۲������ݿ�
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		} 
	}

	/**
	 * ��������ӻظ�
	 * @param form  // replyContent  replyCommentId
	 * @throws MovieException 
	 */
	public void addReplyToComment(Reply form,Users user) throws MovieException {
		//У��ظ�
		//�жϻظ������Ƿ�Ϊnull
		if(form.getReplyContent() == null || form.getReplyContent().trim().isEmpty())
			throw new MovieException("������Ϣ����Ϊ��");
		
		//�������дʻ�
		Properties p = new Properties();
		StringBuilder sb = new StringBuilder();
		try {
			p.load(this.getClass().getClassLoader().getResourceAsStream("regxComment.properties"));
			for(Iterator<Entry<Object,Object>> it = p.entrySet().iterator();it.hasNext();){
				Entry<Object,Object> me = it.next();
				String value = String.valueOf(me.getValue());
				if(form.getReplyContent().contains(value)){
					sb.append(value+" ");
				}
			}
			if(!"".equals(sb.toString()) || !sb.toString().isEmpty())
				throw new MovieException("��Ҫ���͵Ļظ��а������дʻ㣬���ܷ������дʻ㣺"+sb.toString());
		} catch (IOException e) {
			throw new MovieException("ϵͳ�쳣�������ļ����𻵣�");
		}
		
		//���ظ����userId  ͨ��replyCommentId���
		try {
			Comment comment = md.findCommentById(form.getReplyCommentId());  //��ȡcomment����
			form.setReplyUserId(user.getUserId());  //����userId
			form.setReplyCreateTime(new Timestamp(new Date().getTime()));  //���ûظ�ʱ��
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�������ļ����𻵣�");
		}
		
		//����ظ��������ݿ�
		try {
			JdbcUtils.beginTransaction();
			md.insertReply(form);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("ϵͳ�쳣�������ļ����𻵣�");
			}
		}
	}

	/**
	 * ���ظ���ӻظ�
	 * @param form // replyContent   replyId��������id�Ǹ�id
	 * @throws MovieException 
	 */
	public void addReplyToReply(Reply form,Users user) throws MovieException {
		//У��ظ�
		//�жϻظ������Ƿ�Ϊnull
		if(form.getReplyContent() == null || form.getReplyContent().trim().isEmpty())
			throw new MovieException("������Ϣ����Ϊ��");
		
		//�������дʻ�
		Properties p = new Properties();
		StringBuilder sb = new StringBuilder();
		try {
			p.load(this.getClass().getClassLoader().getResourceAsStream("regxComment.properties"));
			for(Iterator<Entry<Object,Object>> it = p.entrySet().iterator();it.hasNext();){
				Entry<Object,Object> me = it.next();
				String value = String.valueOf(me.getValue());
				if(form.getReplyContent().contains(value)){
					sb.append(value+" ");
				}
			}
			if(!"".equals(sb.toString()) || !sb.toString().isEmpty())
				throw new MovieException("��Ҫ���͵Ļظ��а������дʻ㣬���ܷ������дʻ㣺"+sb.toString());
		} catch (IOException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
		}
		
		//���ظ����userId  ͨ��replyCommentId���
		form.setReplyUserId(user.getUserId());  //����userId  
		form.setReplyCreateTime(new Timestamp(new Date().getTime()));  //���ûظ�ʱ��
		form.setReplyParId(form.getReplyId());  //���ø�id
		form.setReplyId(null);  //��id���
		
		
		//����ظ��������ݿ�
		try {
			JdbcUtils.beginTransaction();
			md.insertReply(form);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
	}
}
