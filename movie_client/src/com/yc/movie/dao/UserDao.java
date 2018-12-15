package com.yc.movie.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.prism.Image;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Indent;
import com.yc.movie.bean.Integral;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Sub;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.utils.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	private MovieDao md = new MovieDao();

	/**
	 * ���û����뵽���ݿ���
	 * @param user
	 * @throws SQLException 
	 */
	public void insertUser(Users user) throws SQLException {
		String sql = "insert into users values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {user.getUserId(),user.getUserName(),user.getUserAccount(),
				user.getUserEmail(),user.getUserPwd(),
				user.getUserCreateTime(),user.getUserTel(),
				user.getUserAddr(),user.getUserStatus(),
				user.getUserBirthday(),user.getUserAge(),user.getUserIpAddr()};
		qr.update(sql,params);
	}

	/**
	 * �����ֶμ��Ͳ�������ѯ�û��Ƿ����    �����ڷ���null
	 * @param username
	 * @param selectConf
	 * @return
	 * @throws SQLException 
	 */
	public Users findUserBySelectConf(String[] selectConfs,Object...params) throws SQLException {
		StringBuilder sb = new StringBuilder();
		for(String s:selectConfs)
			sb.append(" and "+s+"=?");
		String sql = "select * from users where 1=1"+sb.toString();
//		System.out.println("sql:"+sql);
		List<Users> result = qr.query(sql, new BeanListHandler<Users>(Users.class),params);
		if(result.size() > 0)
			return result.get(0);
		return null;
	}

	/**
	 * ��ӵ�¼��־����
	 * @param ulr
	 * @throws SQLException 
	 */
	public void insertULR(UserLoginRecord ulr) throws SQLException {
		String sql = "insert into userloginrecord values(?,?,?,?)";
		Object[] params = {ulr.getUlrId(),ulr.getUlrLoginTime(),ulr.getUlrLoginIp(),ulr.getUser().getUserId()};
		qr.update(sql, params);
	}

	/**
	 * �޸�����
	 * @param user
	 * @throws SQLException 
	 */
	public void alterPwd(Users user) throws SQLException {
		String sql = "update users set userPwd=? where userAccount=?";
		Object[] params = {user.getUserPwd(),user.getUserAccount()};
		qr.update(sql, params);
	}

	/**
	 * ��ӻ��ֿ�
	 * @param in ���ֿ�����
	 * @throws SQLException 
	 */
	public void insertIntegral(Integral in) throws SQLException {
		String sql = "insert into integral values(?,?,?)";
		Object[] params = {in.getIntegralId(),in.getUser().getUserId(),in.getIntegralCount()};
		qr.update(sql, params);
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
	 * ͨ���û�����ʱ���ѯ�û�
	 * @param userCreateTime
	 * @return
	 * @throws SQLException 
	 */
	public Users findUserByCraeteTime(Timestamp userCreateTime) throws SQLException {
		String sql = "select * from users where userCreateTime=?";
		List<Users> list = qr.query(sql, new BeanListHandler<Users>(Users.class),userCreateTime);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * ͨ����������û�����  û�ҵ��ͷ���Null
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	public Users findUserByEmail(String email) throws SQLException {
		String sql = "select * from users where userEmail=?";
		List<Users> list = qr.query(sql, new BeanListHandler<Users>(Users.class),email);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * ͨ���ֻ��Ų����û�����   û�ҵ��ͷ���null
	 * @param tel
	 * @return
	 * @throws SQLException 
	 */
	public Users findUserByTel(String tel) throws SQLException {
		String sql = "select * from users where userTel=?";
		List<Users> list = qr.query(sql, new BeanListHandler<Users>(Users.class),tel);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * �޸���Ϣ
	 * @param form
	 * @throws SQLException 
	 */
	public void alterInfo(Users form) throws SQLException {
		String sql = "update users set userName=?,userTel=?,userEmail=?,userAddr=?,userBirthday=?,userAge=? where userId=?";
		Object[] params = {form.getUserName(),form.getUserTel(),form.getUserEmail(),
				form.getUserAddr(),form.getUserBirthday(),form.getUserAge(),form.getUserId()};
		qr.update(sql, params);
	}

	/**
	 * ����һ��ͼƬ
	 * @param sqlPath
	 * @throws SQLException 
	 */
	public void addImageByUser(Images im) throws SQLException {
		String sql = "insert into images values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {im.getImgId(),im.getImgMovieId(),im.getImgAdminId(),
				im.getImgUserId(),im.getImgMerchantId(),im.getImgTeleplayId(),im.getImgTicketId(),
				im.getImgNewId(),im.getImgStatus(),im.getImgPath()};
		qr.update(sql, params);
	}

	/**
	 * ͨ��ID��ѯ�û�����
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public Users findUserById(Long userId) throws SQLException {
		String sql = "select * from users where userId=?";
		List<Users> list = qr.query(sql, new BeanListHandler<Users>(Users.class),userId);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * ͨ���û���ID����ͼƬ
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public Images findImageByUserId(Long userId) throws SQLException {
		String sql = "select * from images where imgUserId=?";
		List<Images> list = qr.query(sql, new BeanListHandler<Images>(Images.class),userId);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * �޸�ͷ��
	 * @param im
	 * @throws SQLException 
	 */
	public void alterImageByUser(Images im) throws SQLException {
		String sql = "update images set imgPath=? where imgUserId=?";
		Object[] params = {im.getImgPath(),im.getImgUserId()};
		qr.update(sql,params);
	}

	/**
	 * �����û��������ж���
	 * @param loginedUser
	 * @return
	 * @throws SQLException 
	 */
	public List<Indent> findIndentListByUser(Users user) throws SQLException {
		String sql = "select * from indent where indentUserId=? and indentStatus!='-1'";
		List<Indent> indentList = qr.query(sql, new BeanListHandler<Indent>(Indent.class),user.getUserId());
		indentList = createIndentList(indentList,user);
		return indentList;
	}

	/**
	 * ��䶩��List
	 * @param indentList
	 * @return
	 * @throws SQLException 
	 */
	private List<Indent> createIndentList(List<Indent> indentList,Users user) throws SQLException {
		for(Indent in : indentList){
			//��䵱ǰ�û�
			in.setUser(user);
			
			//����ӰƱ����
			String sql = "select * from ticket where ticketIndentId=?";
			List<Ticket> ticketList = qr.query(sql, new BeanListHandler<Ticket>(Ticket.class),in.getIndentId());
			ticketList = createTicketList(ticketList);
			in.setTicketList(ticketList);
		}
		return indentList;
	}

	/**
	 * ����ӰƱ����
	 * @param ticketList
	 * @return
	 * @throws SQLException 
	 */
	private List<Ticket> createTicketList(List<Ticket> ticketList) throws SQLException {
		for(Ticket t:ticketList){
			//����Ӱ
			String sql = "select * from movies where movieId=?";
			List<Movies> movieList = qr.query(sql, new BeanListHandler<Movies>(Movies.class),t.getTicketMovieId());
			if(movieList.size() > 0){
				Movies movie = md.createMovie(movieList.get(0));
				t.setMovie(movie);
			}
			
			//����̻�
			sql = "select * from merchant where merId=?";
			List<Merchant> merList = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),t.getTicketMerId());
			if(merList.size() > 0)
				t.setMerchant(merList.get(0));
		}
		return ticketList;
	}
	
	/////wt ////////////////////////////////////////////////
	/**
	 * �������ַ���뵽����������
	 * @param email
	 * @throws SQLException
	 */
	public void insertEmailToSub(String email) throws SQLException {
		String sql="insert into sub values(?,?)";
		Object[] params={null,email};
		qr.update(sql,params);
	}
	/////////////////////////////////////////////////////

	/**
	 * ����������Ҷ��Ķ���
	 * @param email
	 * @return
	 * @throws SQLException 
	 */
	public Sub findSubByEmail(String email) throws SQLException {
		String sql = "select * from sub where subEmail=?";
		List<Sub> subList = qr.query(sql, new BeanListHandler<Sub>(Sub.class),email);
		if(subList.size() > 0)
			return subList.get(0);
		return null;
	}

	/**
	 * �����̻�ID�����̻�����
	 * @param merId
	 * @return
	 * @throws SQLException 
	 */
	public Merchant findMerByMerId(Long merId) throws SQLException {
		String sql = "select * from merchant where merId=?";
		List<Merchant> merList = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),merId);
		if(merList.size() > 0){
			Merchant mer = merList.get(0);
			mer = createMerchant(mer);
			return mer;
		}
		return null;
	}

	/**
	 * �����̻�����
	 * @param mer
	 * @return
	 * @throws SQLException 
	 */
	private Merchant createMerchant(Merchant mer) throws SQLException {
		String sql = "select * from images where imgMerchantId=?";
		List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),mer.getMerId());
		mer.setImgList(imgList);
		return mer;
	}

	/**
	 * ����image�������ݿ�
	 * @param img
	 * @throws SQLException 
	 */
	public void insertImage(Images img) throws SQLException {
		String sql = "insert into values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {img.getImgId(),img.getImgMovieId(),img.getImgAdminId(),
				img.getImgUserId(),img.getImgMerchantId(),img.getImgTeleplayId(),
				img.getImgTicketId(),img.getImgNewId(),img.getImgStatus(),
				img.getImgPath()};
		qr.update(sql, params);
	}
}
