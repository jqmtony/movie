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
	 * 查询所有的movie
	 * @return
	 * @throws MovieException 
	 */
	public List<Movies> findAllMovie() throws MovieException {
		try {
			return md.findAllMovie();
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 通过ID查找电影
	 * @param id
	 * @return
	 * @throws MovieException 
	 */
	public Movies findMovieById(Long id) throws MovieException {
		try {
			Movies movie =  md.findMovieById(id);	//通过id得到movie对象
			movie = md.createMovie(movie); //将对应集合添加到movie对象中
			return movie;
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}  
	}

	/**
	 * 通过ID查找电视剧
	 * @param id
	 * @return
	 * @throws MovieException 
	 */
	public Teleplay findTeleplayById(Long id) throws MovieException {
		try {
			Teleplay teleplay =  md.findTeleplayById(id);	//通过id得到电视剧对象
			teleplay = md.createTeleplay(teleplay); //将对应集合添加到电视剧对象中
			return teleplay;
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}  
	}

	/**
	 * 发送评论业务
	 * @param form	form中有  commentMovieId  commentContent
	 * @param loginedUser
	 * @throws MovieException 
	 */
	public void sendComment(Comment form, Users loginedUser) throws MovieException {
		//判断commentContent是否为null
		if(form.getCommentContent() == null || form.getCommentContent().trim().isEmpty())
			throw new MovieException("评论信息不能为空");
		
		//过滤敏感词汇
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
				throw new MovieException("你要发送的评论中包含敏感词汇，不能发表。敏感词汇："+sb.toString());
		} catch (IOException e) {
			throw new MovieException("系统异常，本地文件被损坏！");
		}
		
		//保存评论
		form.setCommentCreateTime(new Timestamp(new Date().getTime()));  //设置评论时间
		form.setCommentUserId(loginedUser.getUserId());  //设置评论人ID
		
		try {
			JdbcUtils.beginTransaction();
			md.insertComment(form);//将评论插入数据库
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("系统异常，请稍后再试！");
			}
		} 
	}

	/**
	 * 给评论添加回复
	 * @param form  // replyContent  replyCommentId
	 * @throws MovieException 
	 */
	public void addReplyToComment(Reply form,Users user) throws MovieException {
		//校验回复
		//判断回复内容是否为null
		if(form.getReplyContent() == null || form.getReplyContent().trim().isEmpty())
			throw new MovieException("评论信息不能为空");
		
		//过滤敏感词汇
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
				throw new MovieException("你要发送的回复中包含敏感词汇，不能发表。敏感词汇："+sb.toString());
		} catch (IOException e) {
			throw new MovieException("系统异常，本地文件被损坏！");
		}
		
		//给回复添加userId  通过replyCommentId添加
		try {
			Comment comment = md.findCommentById(form.getReplyCommentId());  //获取comment对象
			form.setReplyUserId(user.getUserId());  //设置userId
			form.setReplyCreateTime(new Timestamp(new Date().getTime()));  //设置回复时间
		} catch (SQLException e) {
			throw new MovieException("系统异常，本地文件被损坏！");
		}
		
		//插入回复对象到数据库
		try {
			JdbcUtils.beginTransaction();
			md.insertReply(form);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("系统异常，本地文件被损坏！");
			}
		}
	}

	/**
	 * 给回复添加回复
	 * @param form // replyContent   replyId这里的这个id是父id
	 * @throws MovieException 
	 */
	public void addReplyToReply(Reply form,Users user) throws MovieException {
		//校验回复
		//判断回复内容是否为null
		if(form.getReplyContent() == null || form.getReplyContent().trim().isEmpty())
			throw new MovieException("评论信息不能为空");
		
		//过滤敏感词汇
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
				throw new MovieException("你要发送的回复中包含敏感词汇，不能发表。敏感词汇："+sb.toString());
		} catch (IOException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}
		
		//给回复添加userId  通过replyCommentId添加
		form.setReplyUserId(user.getUserId());  //设置userId  
		form.setReplyCreateTime(new Timestamp(new Date().getTime()));  //设置回复时间
		form.setReplyParId(form.getReplyId());  //设置父id
		form.setReplyId(null);  //把id清空
		
		
		//插入回复对象到数据库
		try {
			JdbcUtils.beginTransaction();
			md.insertReply(form);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("系统异常，请稍后再试！");
			}
		}
	}
}
