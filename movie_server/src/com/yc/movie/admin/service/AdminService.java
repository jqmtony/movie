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
		try{
			//如果验证码不为null，判断验证码是否正确
			if(v != null){
				if(v.getInCode() == null || v.getInCode().trim().isEmpty())
					throw new AdminException("请输入验证码!");
				if(!v.getInCode().equalsIgnoreCase(v.getText())){
					throw new AdminException("验证码错误!");
				}
			}
			
			//用户名格式(邮箱)验证  正则表达式
			if(!form.getAdminEmail().matches(EMAIL_REGX)){
				throw new AdminException("邮箱格式不正确!");
			}
			
			//调用ad的findAdminByEmail()方法判断用户名(邮箱)是否存在
			Admins admin = ad.findAdminByEmail(form.getAdminEmail());
			if(admin == null){
				throw new AdminException("该用户未注册!");
			}
			
			//调用ad的findAdminByPwd()方法判断密码是否正确
			admin = ad.findAdminByPwd(form.getAdminEmail(),form.getAdminPwd());
			if(admin == null){
				throw new AdminException("密码错误!");
			}
			
			//登录成功，发送邮箱提醒   
			//尊敬的管理员：{0},您于{0}登录了电影网站管理系统！<a href="http://localhost:8080/movie_server/AdminServlet?method=alterPwd&alterId={0}">不是自己登录?修改密码！</a>
			Object[] code = {admin.getAdminName(),DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),admin.getAdminId()};
			MailUtils.send(this.getClass(), admin.getAdminEmail(), code,MailUtils.LOGINED_EMAIL_FILENAME);
			
			
			return admin;
		}catch(SQLException e){
			throw new AdminException("系统异常，请稍后再试！");
		}
		
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

	/**
	 * 发送邮件给管理员  让他通过邮箱链接修改密码
	 * @param adminEmail
	 * @throws AdminException 
	 */
	public Admins fotPwdSendEmail(String adminEmail) throws AdminException {
		Admins adm=null;
		try {
			adm = ad.findAdminByEmail(adminEmail);
		} catch (SQLException e) {
			throw new AdminException("系统异常，请稍后再试！");
		}  //得到要修改密码的管理员
		if(adm == null){   //这个邮箱还没有注册
			throw new AdminException("该邮箱还没有注册，请先注册！");
		}
		//尊敬的管理员：{0}，<a href="http://localhost:8080/movie_server/AdminServlet?method=alterPwd&alterId={0}">点击修改密码</a>
		Object[] code = {adm.getAdminName(),adm.getAdminId()};  //填充补位
		MailUtils.send(this.getClass(), adminEmail, code,MailUtils.ALTER_PWD_EMAIL_FILENAME);	//发送邮件
		return adm;
	}

	/**
	 * 根据Id获得Admins对象
	 * @param alterId
	 * @return
	 * @throws AdminException 
	 */
	public Admins findById(Long alterId) throws AdminException {
		try {
			return ad.findAdminById(alterId);
		} catch (SQLException e) {
			throw new AdminException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 修改密码
	 * @param form
	 * @param adminPwd2
	 * @return
	 * @throws AdminException 
	 */
	public Admins alterPwd(Admins form, String adminPwd2) throws AdminException {
		//判断数据是否为null   form.getAdminPwd()   adminPwd2
		if(form.getAdminPwd() == null || form.getAdminPwd().trim().isEmpty()){
			throw new AdminException("新密码不能为空");
		}
		if(adminPwd2 == null || adminPwd2.trim().isEmpty()){
			throw new AdminException("确认密码不能为空");
		}
		//判断新密码格式是否正确
		// TODO Auto-generated method stub
		
		//判断新密码和确认密码是否相同
		// TODO Auto-generated method stub
		
		//将新密码加密
		// TODO Auto-generated method stub
		
		//修改数据库
		try {
			JdbcUtils.beginTransaction();
			
			ad.alterPwd(form);
			
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new AdminException("系统异常，请稍后再试！");
			}
		}
		//修改成功，得到修改完成的Admins对象并返回
		Admins adm=null;
		try {
			adm = ad.findAdminByEmail(form.getAdminEmail());
		} catch (SQLException e) {
			throw new AdminException("系统异常，请稍后再试！");
		}
		return adm;
	}
}
