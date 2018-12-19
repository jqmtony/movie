package com.yc.movie.merchant.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Classifys;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Indent;
import com.yc.movie.bean.Integral;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.PageBean;
import com.yc.movie.bean.Protagonists;
import com.yc.movie.bean.Sub;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
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
		String sql = "insert into merchant values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {me.getMerId(),me.getMerName(),me.getMerTel(),
				me.getMerEmail(),me.getMerAddr(),me.getMerPwd(),
				me.getMerIDCard(),me.getMerStatus(),me.getMerStoreName(),
				me.getMerIpAddr()};
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
		if(list.size() > 0){
			Merchant mer = list.get(0);
			sql = "select * from images where imgMerchantId=?";
			List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),merId);
			mer.setImgList(imgList);
			return mer;
		}
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
	public void updateMerchantId(String movieName, String newMovieMerId) throws SQLException {
		String sql = "update movies set movieMerId=? where movieName=?";
		Object[] params = {newMovieMerId,movieName};
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
	
	/**
	 * ���û����ݲ������ݿ�
	 * @param name
	 * @param card
	 * @param addr
	 * @param tel
	 * @param id 
	 * @return
	 * @throws SQLException
	 */
	public void saveMerchant(Merchant form) throws SQLException {
		String sql = "update merchant set merStoreName=?,merName=?,merIDCard=?,merAddr=?,merTel=?,merStatus=? where merId=?";
		Object[] params={form.getMerStoreName(),form.getMerName(),form.getMerIDCard(),
				form.getMerAddr(),form.getMerTel(),"1",form.getMerId()} ;
		qr.update(sql,params);
	}

	/**
	 * �������еĶ�������
	 * @return
	 * @throws SQLException 
	 */
	public List<Sub> findAllSub() throws SQLException {
		String sql = "select * from sub";
		return qr.query(sql, new BeanListHandler<Sub>(Sub.class));
	}

	/**
	 * ���������û�
	 * @return
	 * @throws SQLException 
	 */
	public List<Users> findAllUser() throws SQLException {
		String sql = "select * from users";
		List<Users> userList = qr.query(sql, new BeanListHandler<Users>(Users.class));
		userList = createUserList(userList);
		return userList;
	}

	/**
	 * ���userList
	 * @throws SQLException 
	 */
	private List<Users> createUserList(List<Users> userList) throws SQLException {
		for(Users u:userList){
			//���ͼƬ����
			String sql = "select * from images where imgUserId=?";
			List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),u.getUserId());
			u.setImgList(imgList);
		}
		return userList;
	}

	/**
	 * �����û�ID��ȡ�û�
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public Users findUserByUserId(Long userId) throws SQLException {
		String sql = "select * from users where userId=?";
		List<Users> userList = qr.query(sql, new BeanListHandler<Users>(Users.class),userId);
		if(userList.size() > 0){
			Users user = userList.get(0);
			user = createUser(user);
			return user;
		}
		return null;
	}
	
	/**
	 * ����ID����User����
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
	
	/**
	 * ����image�������ݿ�
	 * @param img
	 * @throws SQLException 
	 */
	public void insertImage(Images img) throws SQLException {
		String sql = "insert into images values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {img.getImgId(),img.getImgMovieId(),img.getImgAdminId(),
				img.getImgUserId(),img.getImgMerchantId(),img.getImgTeleplayId(),
				img.getImgTicketId(),img.getImgNewId(),img.getImgStatus(),
				img.getImgPath()};
		qr.update(sql, params);
	}

	/**
	 * �޸�ͷ��
	 * @param form
	 * @throws SQLException 
	 */
	public void setImagePath(Images img) throws SQLException {
		String sql = "update images set imgPath=? where imgMerchantId=?";
		Object[] params = {img.getImgPath(),img.getImgMerchantId()};
		qr.update(sql, params);
	}

	/**
	 * �������еĵ�Ӱ
	 * @return
	 * @throws SQLException 
	 */
	public List<Movies> findAllMovieByMer(Long merId) throws SQLException {
		//select * from movies where movieMerId like '2;%' or movieMerId like '%;2;%'
		String sql = "select * from movies where movieMerId like ? or movieMerId like ?";
		Object[] params = {merId+";%","%;"+merId+";%"};
		return qr.query(sql, new BeanListHandler<Movies>(Movies.class),params);
	}

	/**
	 * ����Ӱ����
	 * @param movieList
	 * @return
	 * @throws SQLException 
	 */
	public List<Movies> createMovieList(List<Movies> movieList,Long merId) throws SQLException {
		for(Movies m : movieList){
			//���ͼƬ
			String sql = "select * from images where imgMovieId=?";
			List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),m.getMovieId());
			m.setImgList(imgList);
			
			//����ӰƱ
			sql = "select * from ticket where ticketMovieId=? and ticketMerId=?";
			Object[] params = {m.getMovieId(),merId};
			List<Ticket> ticketList = qr.query(sql, new BeanListHandler<Ticket>(Ticket.class),params);
			m.setTicketList(ticketList);
			
			//���ʣ���ӰƱ��
			int count = 0;
			for(Ticket t : ticketList){
				if("1".equals(t.getTicketStatus())){
					count++;
				}
			}
			m.setAllMovieTicketCount(count);
		}
		return movieList;
	}

	/**
	 * ��ҳ��ѯ�̻�����Ӧ�ĵ�Ӱ
	 * @param pb
	 * @param allMovieList
	 * @param merId
	 * @return
	 * @throws SQLException 
	 */
	public PageBean<Movies> createPageBeanByMovie(PageBean<Movies> pb, Long merId) throws SQLException {
		List<Movies> allMovie = findAllMovieByMer(merId);  //�鵽�̻���Ӧ�����еĵ�Ӱ
		
		int tr = allMovie.size();  //�õ��ܼ�¼��
		pb.setTr(tr);
		if(pb.getPc() < 1){   //�����ǰҳС��1������Ϊ1
			pb.setPc(1);
		}
		if(pb.getPc() > pb.getTp()){  //�����ǰҳ������ҳ��  ������Ϊ���һҳ
			pb.setPc(pb.getTp());
		}
		String sql = "select * from movies where movieMerId like ? or movieMerId like ? limit ?,?";
		Object[] params = {merId+";%","%;"+merId+";%",(pb.getPc()-1)*pb.getPs(),pb.getPs()};
		List<Movies> beanList = qr.query(sql, new BeanListHandler<Movies>(Movies.class),params);
		beanList = createMovieList(beanList, merId);  //���beanList
		pb.setBeanList(beanList);
		
		return pb;
	}

	/**
	 * ���õ�Ӱ״̬
	 * @param type
	 * @param movieId
	 * @throws SQLException 
	 */
	public void setMovieStatus(String type, Long movieId) throws SQLException {
		String sql = "update movies set movieStatus=? where movieId=?";
		Object[] params = {type,movieId};
		qr.update(sql,params);
	}

}
