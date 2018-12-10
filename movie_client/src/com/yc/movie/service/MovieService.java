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

	/**
	 * �������
	 * @param movieId
	 * @throws MovieException 
	 */
	public void addMovieGradeNum(Long movieId) throws MovieException {
		try {
			JdbcUtils.beginTransaction();
			Movies movie = md.findMovieById(movieId); //�õ�movie
			movie.setMovieGradeNum(movie.getMovieGradeNum()+0.001);  //����
			md.updateMovieGradeNum(movie);  //����������
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
	 * ����ID��ȡ�̻�����
	 * @param merId
	 * @return
	 * @throws MovieException 
	 */
	public Merchant findMerchantById(Long merId) throws MovieException {
		try {
			return md.findMerchantById(merId);
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * ͨ��ʱ�������˵�ӰƱ
	 * @param ticketList
	 * @return
	 */
	public List<String> createTicketByStartTime(List<Ticket> ticketList) {
		List<String> list = new ArrayList<String>();
		for(Ticket t:ticketList){
			if(t.getTicketStartTime().getTime() > new Date().getTime()){  //�����ӰƱ�ϵ�ʱ��С�ڵ�ǰʱ����Ƴ�
				String date = t.getTicketStartTime().toString().substring(5, 10);
				if(list.indexOf(date) == -1)
					list.add(date);
			}
		}
		return list;
	}

	/**
	 * ���˵�ӰƱ  ֻҪ�������ڵĵ�Ӱ  12-12
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
	 * ͨ���̻�ID���˵�ӰƱ
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
	 * �õ�Ҫѡ��204��Ʊ
	 * @param merId
	 * @param date
	 * @param theater
	 * @return
	 * @throws MovieException 
	 */
	public List<Ticket> getShowChoseList(Long merId, String date, String theater) throws MovieException {
		try {
			List<Ticket> ticketList1 = md.getTicketListByMerIdAndTheater(merId,theater); //ͨ���̻������Ҳ���
			List<Ticket> ticketList = new ArrayList<Ticket>();
			for(Ticket t : ticketList1){
				if(t.getTicketStartTime().toString().contains(date))
					ticketList.add(t);
			}
			return ticketList;
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ�����");
		}
	}

	/**
	 * ������������
	 * @param ticketList
	 * @param loginedUser
	 * @param nowMovie
	 * @return
	 * @throws MovieException 
	 */
	public Indent createIndent(List<Ticket> ticketList, Users loginedUser, Movies nowMovie) throws MovieException {
		Indent in = new Indent();
		in.setUser(loginedUser);  //�����û�
		in.setIndentUserID(loginedUser.getUserId());  //�����û�ID
		in.setIndentStatus("0");  //���ö���״̬  0��δ�ջ�
		in.setIndentNum(CommonsUtils.createIndentNum());  //���ö�����
		in.setIndentPrice(new BigDecimal((Double.parseDouble(nowMovie.getMoviePrice().toString()) * Double.parseDouble(ticketList.size()+"")) + ""));
		in.setIndentCreateTime(new Timestamp(new Date().getTime()));  //���ö�������ʱ��
		in.setTicketList(ticketList);  //���õ�ӰƱ����
		
		try {
			JdbcUtils.beginTransaction();
			md.insertIndent(in);
			
			//���ĵ�ӰƱ��ticketIndentId
			Indent indent = md.findIndentByIndentNum(in.getIndentNum());
			for(Ticket t:ticketList){
				md.setTicketIndentId(t.getTicketId(),indent.getIndentId());
			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MovieException("ϵͳ�쳣�����Ժ�����");
			}
		}
		return in;
	}

	/**
	 * ���õ�ӰƱ��״̬
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
				throw new MovieException("ϵͳ�쳣�����Ժ�����");
			}
		}
	}

	/**
	 * ������+1
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
				throw new MovieException("ϵͳ�쳣�����Ժ�����");
			}
		}
	}
	/**
	 * ͨ��������ҵ�Ӱ����   (��ҳ��ѯ)
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
			pb.setPc(pc);  //��ǰҳ
			
			pb.setPs(ps);	//ÿҳ��ʾ
			
			List<Movies> movieList = md.findMovieByClassify(cList);
			pb.setTr(movieList.size());  //�ܼ�¼��
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
			throw new MovieException("ϵͳ�쳣�����Ժ�����");
		}
	}

	/**
	 * ���õ�ӰƱ��ticketBuyBy
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
				throw new MovieException("ϵͳ�쳣�����Ժ�����");
			}
		}
	}

	/**
	 * ���ö���״̬
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
				throw new MovieException("ϵͳ�쳣�����Ժ�����");
			}
		}
	}

}
