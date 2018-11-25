package com.yc.movie.admin.service;

import java.sql.SQLException;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;
import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.admin.bean.Verification;
import com.yc.movie.admin.dao.AdminDao;
import com.yc.movie.admin.exception.AdminException;
import com.yc.movie.utils.DateUtils;
import com.yc.movie.utils.JdbcUtils;
import com.yc.movie.utils.MailUtils;

public class AdminService {
	private AdminDao ad = new AdminDao();
	public static final String EMAIL_REGX = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
	
	/**
	 * 登录业务
	 * @param form	
	 * @return 登陆成功的Admins对象(信息完整的)
	 */
	public Admins login(Admins form,Verification v) throws AdminException{
		//如果验证码不为null，判断验证码是否正确
		// TODO Auto-generated method stub
		
		//用户名格式(邮箱)验证  正则表达式
		// TODO Auto-generated method stub
		
		//调用ad的findAdminByEmail()方法判断用户名(邮箱)是否存在
		Admins admin = ad.findAdminByEmail(form.getAdminEmail());
		// TODO Auto-generated method stub
		
		//调用ad的findAdminByPwd()方法判断密码是否正确
		admin = ad.findAdminByPwd(form.getAdminEmail(),form.getAdminPwd());
		// TODO Auto-generated method stub
		
		//登录成功，发送邮箱提醒   
		//尊敬的管理员：{0},您于{0}登录了电影网站管理系统！<a href="http://localhost:8080/movie_server/AdminServlet?method=alterPwd&alterId={0}">不是自己登录?</a>
		Object[] code = {admin.getAdminName(),DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),admin.getAdminId()};
		MailUtils.send(this.getClass(), admin.getAdminEmail(), code,MailUtils.LOGINED_EMAIL_FILENAME);
		
		
		return admin;
	}

	/**
	 * 将登录记录对象添加到数据库
	 * @param alr
	 * @throws AdminException 
	 */
	public void addALR(AdminLoginRecord alr) throws AdminException {
		try {
			JdbcUtils.beginTransaction();  //开启事务
			
			ad.addALR(alr);	//调用AdminDao的addALR()方法添加
			
			JdbcUtils.commitTransaction();  //提交事务
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();  //回滚
			} catch (SQLException e1) {
				throw new AdminException("系统异常，请稍后再试！");
			} 
		}  
	}
}
