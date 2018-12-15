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
	 * 将用户插入到数据库中
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
	 * 根据字段集和参数集查询用户是否存在    不存在返回null
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
	 * 添加登录日志对象
	 * @param ulr
	 * @throws SQLException 
	 */
	public void insertULR(UserLoginRecord ulr) throws SQLException {
		String sql = "insert into userloginrecord values(?,?,?,?)";
		Object[] params = {ulr.getUlrId(),ulr.getUlrLoginTime(),ulr.getUlrLoginIp(),ulr.getUser().getUserId()};
		qr.update(sql, params);
	}

	/**
	 * 修改密码
	 * @param user
	 * @throws SQLException 
	 */
	public void alterPwd(Users user) throws SQLException {
		String sql = "update users set userPwd=? where userAccount=?";
		Object[] params = {user.getUserPwd(),user.getUserAccount()};
		qr.update(sql, params);
	}

	/**
	 * 添加积分卡
	 * @param in 积分卡对象
	 * @throws SQLException 
	 */
	public void insertIntegral(Integral in) throws SQLException {
		String sql = "insert into integral values(?,?,?)";
		Object[] params = {in.getIntegralId(),in.getUser().getUserId(),in.getIntegralCount()};
		qr.update(sql, params);
	}

	/**
	 * 根据ID创建User对象
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
	 * 通过用户创建时间查询用户
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
	 * 通过邮箱查找用户对象  没找到就返回Null
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
	 * 通过手机号查找用户对象   没找到就返回null
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
	 * 修改信息
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
	 * 插入一张图片
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
	 * 通过ID查询用户对象
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
	 * 通过用户的ID查找图片
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
	 * 修改头像
	 * @param im
	 * @throws SQLException 
	 */
	public void alterImageByUser(Images im) throws SQLException {
		String sql = "update images set imgPath=? where imgUserId=?";
		Object[] params = {im.getImgPath(),im.getImgUserId()};
		qr.update(sql,params);
	}

	/**
	 * 根据用户查找所有订单
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
	 * 填充订单List
	 * @param indentList
	 * @return
	 * @throws SQLException 
	 */
	private List<Indent> createIndentList(List<Indent> indentList,Users user) throws SQLException {
		for(Indent in : indentList){
			//填充当前用户
			in.setUser(user);
			
			//填充电影票集合
			String sql = "select * from ticket where ticketIndentId=?";
			List<Ticket> ticketList = qr.query(sql, new BeanListHandler<Ticket>(Ticket.class),in.getIndentId());
			ticketList = createTicketList(ticketList);
			in.setTicketList(ticketList);
		}
		return indentList;
	}

	/**
	 * 填充电影票集合
	 * @param ticketList
	 * @return
	 * @throws SQLException 
	 */
	private List<Ticket> createTicketList(List<Ticket> ticketList) throws SQLException {
		for(Ticket t:ticketList){
			//填充电影
			String sql = "select * from movies where movieId=?";
			List<Movies> movieList = qr.query(sql, new BeanListHandler<Movies>(Movies.class),t.getTicketMovieId());
			if(movieList.size() > 0){
				Movies movie = md.createMovie(movieList.get(0));
				t.setMovie(movie);
			}
			
			//填充商户
			sql = "select * from merchant where merId=?";
			List<Merchant> merList = qr.query(sql, new BeanListHandler<Merchant>(Merchant.class),t.getTicketMerId());
			if(merList.size() > 0)
				t.setMerchant(merList.get(0));
		}
		return ticketList;
	}
	
	/////wt ////////////////////////////////////////////////
	/**
	 * 将邮箱地址插入到订阅邮箱中
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
	 * 根据邮箱查找订阅对象
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
	 * 根据商户ID查找商户对象
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
	 * 创建商户对象
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
	 * 插入image对象到数据库
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
