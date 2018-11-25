package com.yc.movie.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.sun.jmx.snmp.Timestamp;
import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.admin.bean.Verification;
import com.yc.movie.admin.dao.AdminDao;
import com.yc.movie.admin.exception.AdminException;
import com.yc.movie.utils.DateUtils;
import com.yc.movie.utils.JdbcUtils;
import com.yc.movie.utils.MD5;
import com.yc.movie.utils.MailUtils;
import com.yc.movie.utils.RegxUtils;

public class AdminService {
	private AdminDao ad = new AdminDao();
	
	
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
			if(!form.getAdminEmail().matches(RegxUtils.EMAIL_REGX)){
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
			throw new AdminException("该邮箱还没有注册，请先注册！");
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
		if(!form.getAdminPwd().trim().matches(RegxUtils.PWD_REGX)){
			throw new AdminException("新密码格式不正确");
		}
		
		//判断新密码和确认密码是否相同
		if(!form.getAdminPwd().equals(adminPwd2)){
			throw new AdminException("两次输入密码不相同");
		}
		
		//将新密码加密
		form.setAdminPwd(MD5.parseMD5(form.getAdminPwd()));
		
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

	/**
	 * 注册管理员时失焦进行校验
	 * @param form
	 * @param status
	 * @throws AdminException 
	 */
	public void registerBlur(Admins form, String status,String pwd2) throws AdminException {
		switch(status){
		case "1":
			//注册码
			//1.正则表达式判断  不正确抛异常
			if(!form.getAdminRegisterCode().matches(RegxUtils.REGISTER_CODE_REGX)){
				throw new AdminException("注册码格式不正确！");
			}
			
			//2.配置文件查询   判断是否存在此注册码   不存在就抛异常
			boolean flag = false;
			Properties p = new Properties();	//创建Properties流  这个流是集合中可以和IO流相关连的流   和Map相似
			try {
				p.load(this.getClass().getResourceAsStream("registerCode.properties"));  //加载配置文件
				Set<Object> keySet = p.keySet();	//p<Object,Object>   得到p的key集合
				for(Object key:keySet){	//遍历所有的注册码
					String str = String.valueOf(p.get(key)); //通过键取值    得到一个注册码
					if(str.equals(form.getAdminRegisterCode())){	//判断是否相同
						flag = true;
					}
				}
				if(flag == false){
					throw new AdminException("注册码不存在！");
				}
			} catch (IOException e) {
				throw new AdminException("系统异常，请稍后再试！");
			}
			
			//3.数据库查询  判断此注册码是否已被使用  如果已经被使用就抛异常
			try {
				Admins admin = ad.findAdminByRegisterCode(form.getAdminRegisterCode());
				if(admin != null){
					throw new AdminException("该注册码已被使用！");
				}
			} catch (SQLException e) {
				throw new AdminException("系统异常，请稍后再试！");
			}
	
			break;
		case "2":
			//姓名
			//1.正则表达式判断  不正确抛异常
			if(!form.getAdminName().matches(RegxUtils.NAME_REGX)){
				throw new AdminException("姓名格式不正确！");
			}
			
			break;
		case "3":
			//电话
			//1.正则表达式判断  不正确抛异常
			if(!form.getAdminTel().matches(RegxUtils.TEL_NUM_REGX)){
				throw new AdminException("电话格式不正确！");
			}
			
			break;
		case "4":
			//邮箱
			//1.正则表达式判断  不正确抛异常
			if(!form.getAdminEmail().matches(RegxUtils.EMAIL_REGX)){
				throw new AdminException("邮箱格式不正确！");
			}
			
			//2.查询数据库  判断是否已存在此邮箱  如果已存在就抛异常
			try {
				Admins admin = ad.findAdminByEmail(form.getAdminEmail());
				if(admin != null){
					throw new AdminException("该邮箱已被使用！");
				}
			} catch (SQLException e) {
				throw new AdminException("系统异常，请稍后再试！");
			}
			
			break;
		case "5":
			//密码
			//1.正则表达式判断  不正确抛异常
			if(!form.getAdminPwd().matches(RegxUtils.PWD_REGX)){
				throw new AdminException("密码格式不正确！");
			}
			
			break;
		case "6":
			//确认密码
			//1.判断确认密码是否与新密码相同   不相同就抛异常
			if(!form.getAdminPwd().equals(pwd2)){
				throw new AdminException("两次输入的密码不相同！");
			}
			
			break;
		default:throw new AdminException("系统异常，请稍后再试！");
		}
	}
}
