package com.yc.movie.service;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Classifys;
import com.yc.movie.bean.Comment;
import com.yc.movie.bean.Indent;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.PageBean;
import com.yc.movie.bean.Reply;
import com.yc.movie.bean.Teleplay;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.Users;
import com.yc.movie.dao.MovieDao;
import com.yc.utils.CommonsUtils;
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

	/**
	 * 添加评分
	 * @param movieId
	 * @throws MovieException 
	 */
	public void addMovieGradeNum(Long movieId) throws MovieException {
		try {
			JdbcUtils.beginTransaction();
			Movies movie = md.findMovieById(movieId); //得到movie
			movie.setMovieGradeNum(movie.getMovieGradeNum()+0.001);  //评论
			md.updateMovieGradeNum(movie);  //更新评分数
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
	 * 根据ID获取商户对象
	 * @param merId
	 * @return
	 * @throws MovieException 
	 */
	public Merchant findMerchantById(Long merId) throws MovieException {
		try {
			return md.findMerchantById(merId);
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 通过时间来过滤电影票
	 * @param ticketList
	 * @return
	 */
	public List<String> createTicketByStartTime(List<Ticket> ticketList) {
		List<String> list = new ArrayList<String>();
		for(Ticket t:ticketList){
			if(t.getTicketStartTime().getTime() > new Date().getTime()){  //如果电影票上的时间小于当前时间就移除
				String date = t.getTicketStartTime().toString().substring(5, 10);
				if(list.indexOf(date) == -1)
					list.add(date);
			}
		}
		return list;
	}

	/**
	 * 过滤电影票  只要传入日期的电影  12-12
	 * @param ticketList
	 * @return
	 */
	public List<Ticket> createTicketByDate(List<Ticket> ticketList,String regx) {
		List<Ticket> list = new ArrayList<Ticket>();
		for(Ticket t:ticketList){
			if(regx.equals(t.getTicketStartTime().toString().substring(5, 10))){
				list.add(t);  
			}
		}
		return list;
	}

	/**
	 * 通过商户ID过滤电影票
	 * @param ticketList2
	 * @param merId
	 * @return
	 */
	public List<Ticket> createTicketByMerId(List<Ticket> ticketList2, Long merId) {
		List<Ticket> list = new ArrayList<Ticket>();
		for(Ticket t : ticketList2){
			if(merId.equals(t.getTicketMerId()))
				list.add(t);
		}
		return list;
	}

	/**
	 * 得到要选的204张票
	 * @param merId
	 * @param date
	 * @param theater
	 * @return
	 * @throws MovieException 
	 */
	public List<Ticket> getShowChoseList(Long merId, String date, String theater) throws MovieException {
		try {
			List<Ticket> ticketList1 = md.getTicketListByMerIdAndTheater(merId,theater); //通过商户和厅室查找
			List<Ticket> ticketList = new ArrayList<Ticket>();
			for(Ticket t : ticketList1){
				if(t.getTicketStartTime().toString().contains(date))
					ticketList.add(t);
			}
			return ticketList;
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试");
		}
	}

	/**
	 * 创建订单对象
	 * @param ticketList
	 * @param loginedUser
	 * @param nowMovie
	 * @return
	 * @throws MovieException 
	 */
	public Indent createIndent(List<Ticket> ticketList, Users loginedUser, Movies nowMovie) throws MovieException {
		Indent in = new Indent();
		in.setUser(loginedUser);  //设置用户
		in.setIndentUserID(loginedUser.getUserId());  //设置用户ID
		in.setIndentStatus("0");  //设置订单状态  0是未收货
		in.setIndentNum(CommonsUtils.createIndentNum());  //设置订单号
		in.setIndentPrice(new BigDecimal((Double.parseDouble(nowMovie.getMoviePrice().toString()) * Double.parseDouble(ticketList.size()+"")) + ""));
		in.setIndentCreateTime(new Timestamp(new Date().getTime()));  //设置订单创建时间
		in.setTicketList(ticketList);  //设置电影票集合
		
		try {
			JdbcUtils.beginTransaction();
			md.insertIndent(in);
			
			//更改电影票的ticketIndentId
			Indent indent = md.findIndentByIndentNum(in.getIndentNum());
			for(Ticket t:ticketList){
				md.setTicketIndentId(t.getTicketId(),indent.getIndentId());
			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("系统异常，请稍后再试");
			}
		}
		return in;
	}

	/**
	 * 设置电影票的状态
	 * @param t
	 * @param string
	 * @throws MovieException 
	 */
	public void setTicketStatus(Ticket t, String status) throws MovieException {
		try {
			JdbcUtils.beginTransaction();
			md.setTicketStatus(t.getTicketId(),status);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("系统异常，请稍后再试");
			}
		}
	}

	/**
	 * 访问量+1
	 * @param id
	 * @throws MovieException 
	 */
	public void addVisitCount(Long id) throws MovieException {
		try {
			JdbcUtils.beginTransaction();
			md.addVisitCount(id);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("系统异常，请稍后再试");
			}
		}
	}
	/**
	 * 通过分类查找电影集合   (分页查询)
	 * @param pc
	 * @param i
	 * @param genreName
	 * @return
	 * @throws MovieException 
	 */
	public PageBean<Movies> findMovieByClassify(Integer pc, int ps, String genreName) throws MovieException {
		try {
			ClassifyName cn = md.findClassifyNameByName(genreName);
			
			List<Classifys> cList = md.findClassifyByNameId(cn.getClassifyNameId());
			
			PageBean<Movies> pb = new PageBean<Movies>();
			pb.setPc(pc);  //当前页
			
			pb.setPs(ps);	//每页显示
			
			List<Movies> movieList = md.findMovieByClassify(cList);
			pb.setTr(movieList.size());  //总记录数
			if(pb.getPc() > pb.getTp()){
				pb.setPc(pb.getTp());
			}
			if(pb.getPc() < 1){
				pb.setPc(1);
			}
			List<Movies> movieList2 = md.findMovieByClassifyPage(pb.getPc(),pb.getPs(),cList);
			for(Movies m : movieList2){
				m = md.createMovie(m);
			}
			pb.setBeanList(movieList2);
			return pb;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MovieException("系统异常，请稍后再试");
		}
	}

	/**
	 * 设置电影票的ticketBuyBy
	 * @param t
	 * @param loginedUser
	 * @throws MovieException 
	 */
	public void setTicketBuyBy(Ticket t, Users loginedUser) throws MovieException {
		try {
			JdbcUtils.beginTransaction();
			md.setTicketBuyBy(t.getTicketId(),loginedUser.getUserId());
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("系统异常，请稍后再试");
			}
		}
	}

	/**
	 * 设置订单状态
	 * @param indentId
	 * @param type
	 * @throws MovieException 
	 */
	public void setIndentStatus(Long indentId, String type) throws MovieException {
		try {
			JdbcUtils.beginTransaction();
			md.setIndentStatus(indentId,type);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("系统异常，请稍后再试");
			}
		}
	}

}
