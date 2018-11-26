package com.yc.movie.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.admin.bean.Verification;
import com.yc.movie.admin.dao.AdminDao;
import com.yc.movie.admin.exception.AdminException;
import com.yc.movie.utils.JdbcUtils;
import com.yc.movie.utils.CommonsUtils;

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
			if(form.getAdminEmail()==null || form.getAdminEmail().trim().isEmpty())
				throw new AdminException("请输入邮箱地址!");
			if(!form.getAdminEmail().matches(CommonsUtils.EMAIL_REGX)){
				throw new AdminException("邮箱格式必须是:xxx@xxx.xxx!");
			}
			
			//调用ad的findAdminByEmail()方法判断用户名(邮箱)是否存在
			Admins admin = ad.findAdminByEmail(form.getAdminEmail());
			if(admin == null){
				throw new AdminException("此邮箱未注册，请先去注册!");
			}
			
			//调用ad的findAdminByPwd()方法判断密码是否正确
			admin = ad.findAdminByPwd(form.getAdminEmail(),form.getAdminPwd());
			if(admin == null){
				throw new AdminException("密码错误!");
			}
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
	 * 注册管理员时失焦进行校验  ajax
	 * @param form
	 * @param status
	 * @throws AdminException 
	 */
	public void registerBlur(Admins form, String status,String pwd2) throws AdminException {
		switch(status){
		case "4":
			//注册码
			//1.正则表达式判断  不正确抛异常
			if(form.getAdminRegisterCode() == null || !form.getAdminRegisterCode().matches(CommonsUtils.REGISTER_CODE_REGX)){
				throw new AdminException("注册码格式不正确！");
			}
			
			//2.配置文件查询   判断是否存在此注册码   不存在就抛异常
			boolean flag = false;
			Properties p = new Properties();	//创建Properties流  这个流是集合中可以和IO流相关连的流   和Map相似
			try {
				p.load(this.getClass().getClassLoader().getResourceAsStream("registerCode.properties"));  //加载配置文件
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
		case "1":
			//邮箱
			//1.正则表达式判断  不正确抛异常
			if(form.getAdminEmail() == null || !form.getAdminEmail().matches(CommonsUtils.EMAIL_REGX)){
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
		case "2":
			//密码
			//1.正则表达式判断  不正确抛异常
			if(form.getAdminPwd() == null || !form.getAdminPwd().matches(CommonsUtils.PWD_REGX)){
				throw new AdminException("密码格式不正确:必须是字母数字或下划线！");
			}
			
			break;
		case "3":
			//确认密码
			//1.判断确认密码是否与新密码相同   不相同就抛异常
			if(pwd2 == null || !form.getAdminPwd().trim().equals(pwd2)){
				throw new AdminException("两次输入的密码不相同！");
			}
			
			break;
		default:throw new AdminException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 管理员登录之前的Email校验  ajax失焦
	 * @param adminEmail
	 * @throws AdminException 
	 */
	public void loginAdminEmailRegx(String adminEmail) throws AdminException {
		//判断格式是否正确
		if(adminEmail==null || adminEmail.isEmpty())
			throw new AdminException("请输入邮箱地址!");
		if(!adminEmail.matches(CommonsUtils.EMAIL_REGX))
			throw new AdminException("邮箱格式必须是:xxx@xxx.xxx!");
		try {
			Admins adm = ad.findAdminByEmail(adminEmail);
			if(adm == null)
				throw new AdminException("此邮箱未注册，请先去注册!");
		} catch (SQLException e) {
			throw new AdminException("系统异常，请稍后再试！");
		}
		
	}

	/**
	 * 注册
	 * @param form
	 * @throws AdminException 
	 */
	public void register(Admins form,String pwd2) throws AdminException {
		//1.数据校验
		registerBlur(form,"1",pwd2);
		registerBlur(form,"2",pwd2);
		registerBlur(form,"3",pwd2);
		registerBlur(form,"4",pwd2);
		
		//2.密码加密
		form.setAdminPwd(CommonsUtils.parseMD5(form.getAdminPwd()));
		
		//3.设置权值
		//  根据注册码来设置权值   根据首字母来确定是什么管理员
		Integer str = Integer.parseInt(form.getAdminRegisterCode().substring(0, 1));  //得到首字母
		Long weight = 0l;
		switch(str){
		case 0:weight=10000l;break;
		case 1:weight=1000l;break;
		case 2:weight=100l;break;
		}
		form.setAdminWeight(weight);
		
		//4.设置注册时间
		form.setAdminCreateTime(new Timestamp(new Date().getTime()));
		
		//5.注册事务
		try {
			JdbcUtils.beginTransaction();	//开启事务
			
			ad.addAdmin(form);  //将admin添加到数据库
			
			JdbcUtils.commitTransaction();  //提交事务
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();  //回滚
			} catch (SQLException e1) {
				throw new AdminException("系统异常，请稍后再试！");
			}
		}
		
		//注册成功  发送注册成功邮件
		String to = form.getAdminEmail();  //设置收件人
		Object[] codes = {form.getAdminEmail(),CommonsUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")};  //替换补位   管理员  时间
		String fileName = "register_admin_email.properties";
		CommonsUtils.sendMail(this.getClass(), to, codes, fileName);
	}

	/**
	 * 修改密码之前的校验    ajax
	 * @param form
	 * @param status
	 * @param pwd2
	 * @throws AdminException 
	 */
	public void resetPwdBlur(Admins form, String status, String pwd2) throws AdminException {
		switch(status){
		case "4":
			//注册码
			//1.正则表达式判断  不正确抛异常
			if(form.getAdminRegisterCode() == null || !form.getAdminRegisterCode().matches(CommonsUtils.REGISTER_CODE_REGX)){
				throw new AdminException("注册码格式不正确！");
			}
			
			try {
				//2.通过注册码查询数据库中是否存在admin对象   不存在就抛异常
				Admins admin = ad.findAdminByRegisterCode(form.getAdminRegisterCode());
				if(admin == null){
					throw new AdminException("注册码不存在或未被使用！");
				}
				
				//3.通过邮箱和注册码同时查询
				if(form.getAdminEmail() == null)
					throw new AdminException("请先填写邮箱！");
				admin = ad.findAdminByRegisterCodeAndEmail(form.getAdminRegisterCode(),form.getAdminEmail());
				if(admin == null)
					throw new AdminException("注册码与邮箱不匹配！");
			} catch (SQLException e) {
				throw new AdminException("系统异常，请稍后再试！");
			}
	
			break;
		case "1":
			//邮箱
			//1.正则表达式判断  不正确抛异常
			if(form.getAdminEmail() == null || !form.getAdminEmail().matches(CommonsUtils.EMAIL_REGX)){
				throw new AdminException("邮箱格式不正确！");
			}
			
			//2.查询数据库  判断是否已存在此邮箱  如果已存在就抛异常
			try {
				Admins admin = ad.findAdminByEmail(form.getAdminEmail());
				if(admin == null){
					throw new AdminException("该邮箱未被注册！");
				}
			} catch (SQLException e) {
				throw new AdminException("系统异常，请稍后再试！");
			}
			
			break;
		case "2":
			//密码
			//1.正则表达式判断  不正确抛异常
			if(form.getAdminPwd() == null || !form.getAdminPwd().matches(CommonsUtils.PWD_REGX)){
				throw new AdminException("密码格式不正确:必须是字母数字或下划线！");
			}
			
			break;
		case "3":
			//确认密码
			//1.判断确认密码是否与新密码相同   不相同就抛异常
			if(pwd2 == null || !form.getAdminPwd().trim().equals(pwd2)){
				throw new AdminException("两次输入的密码不相同！");
			}
			
			break;
		default:throw new AdminException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 修改密码
	 * @param form
	 * @param pwd2
	 * @throws AdminException 
	 */
	public void resetPwd(Admins form, String pwd2) throws AdminException {
		//1.数据校验
		resetPwdBlur(form,"1",pwd2);
		resetPwdBlur(form,"2",pwd2);
		resetPwdBlur(form,"3",pwd2);
		resetPwdBlur(form,"4",pwd2);
		
		//2.密码加密
		form.setAdminPwd(CommonsUtils.parseMD5(form.getAdminPwd()));
		
		
		//3.修改事务
		try {
			JdbcUtils.beginTransaction();	//开启事务
			
			ad.resetPwd(form.getAdminEmail(),form.getAdminPwd());  //修改密码
			
			JdbcUtils.commitTransaction();  //提交事务
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();  //回滚
			} catch (SQLException e1) {
				throw new AdminException("系统异常，请稍后再试！");
			}
		}
		
		//修改成功  发送邮件
		//localhost:8080/movie_server
		//
		String to = form.getAdminEmail();  //设置收件人
		Object[] codes = {form.getAdminEmail(),CommonsUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")};  //替换补位   管理员  时间
		String fileName = "reset_password_email.properties";
		CommonsUtils.sendMail(this.getClass(), to, codes, fileName);
	}
}
