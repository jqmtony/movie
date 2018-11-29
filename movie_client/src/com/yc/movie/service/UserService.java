package com.yc.movie.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.Session;

import com.yc.exception.UserException;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.movie.bean.Verify;
import com.yc.movie.dao.UserDao;
import com.yc.utils.CommonsUtils;
import com.yc.utils.JdbcUtils;

public class UserService {
	private UserDao ud = new UserDao();

	/**
	 * 注册
	 * @param user 
	 * @param userPwd2
	 * @param ura  是否选中了同意注册协议
	 * @throws UserException 
	 */
	public void register(Users user, String userPwd2,String ura) throws UserException {
		String userName = user.getUserName().trim();  //获取到输入的姓名
		String userEmail = user.getUserEmail().trim();  //获取到输入的Email
		String userPwd = user.getUserPwd().trim();  //获取到输入的密码
		// 判断姓名是否为null
		if(userName == null || userName.isEmpty())
			throw new UserException("请输入姓名！");
		
		//判断邮箱是否为null
		if(userEmail == null || userEmail.isEmpty())
			throw new UserException("请输入邮箱！");
		
		//判断密码是否为null
		if(userPwd == null || userPwd.isEmpty())
			throw new UserException("请输入密码！");
		
		//判断确认密码是否为null
		if(userPwd2 == null || userPwd2.trim().isEmpty())
			throw new UserException("请输入确认密码！");
		
		//姓名格式校验
		if(!userName.matches(CommonsUtils.NAME_REGX))
			throw new UserException("姓名格式不正确！");
		
		//邮箱格式校验
		if(!userEmail.matches(CommonsUtils.EMAIL_REGX))
			throw new UserException("邮箱格式不正确！");
		
		//邮箱是否已被注册
		try {
			Users u = ud.findUserBySelectConf(new String[]{"userEmail"} ,userEmail);
			if(u != null)
				throw new UserException("此邮箱已被注册！");
		} catch (SQLException e) {
			throw new UserException("系统错误，请稍后再试！");
		}
		
		//密码格式校验
		if(!userPwd.matches(CommonsUtils.PWD_REGX))
			throw new UserException("密码格式不正确！");
		
		//判断密码和确认密码是否相同
		if(!userPwd.equals(userPwd2))
			throw new UserException("两次输入的密码不一样！");
		
		//密码加密
		user.setUserPwd(CommonsUtils.parseMD5(userPwd));
		
		//注册
		try {
			JdbcUtils.beginTransaction();
			ud.insertUser(user); //插入记录
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("系统错误，请稍后再试！");
			}
		}
		
		
		//发送邮件
		//{0},你好，你正在注册【电影天堂】账号，点击链接确认注册：<a href="http://{1}mail/registerSucceed.jsp">点击这里进行激活</a>
		String fileName = "register_user_email.properties";
		String to = userEmail;
		try {
			String addIp = CommonsUtils.getAddressAndProName(this.getClass());
			Object[] codes = {userName,addIp};
			CommonsUtils.sendMail(this.getClass(), to, codes, fileName);
		} catch (IOException e) {
			throw new UserException("系统错误，系统文件已被损坏！");
		}
	}

	/**
	 * 登录
	 * @param form
	 * @return
	 * @throws UserException 
	 */
	public Users login(Users form,UserLoginRecord ulr) throws UserException {
		//得到三种可能账号类型的值 
		String userAccount = form.getUserAccount();
		String userEmail = form.getUserEmail();
		String userTel = form.getUserTel();
		
		String pwd = form.getUserPwd();  //得到密码  （未加密的）
		
		
		// 判断账号是否为null  为null就抛异常  否则就把值保存到username中
		String username = null;
		if(userAccount == null || userAccount.isEmpty())
			if(userEmail == null || userEmail.isEmpty())
				if(userTel == null || userTel.isEmpty())
					throw new UserException("账号不能为空");
				else username = userTel;
			else username = userEmail;
		else username = userAccount;
			
		//判断密码是否为空
		if(pwd == null || pwd.isEmpty())
			throw new UserException("密码不能为空");
		
		//判断账号格式是否正确  不正确就抛异常   否则就把账号类型保存到selectConf中
		String selectConf = ""; 
		if(!username.matches(CommonsUtils.USERNAME_REGX))
			if(!username.matches(CommonsUtils.EMAIL_REGX))
				if(!username.matches(CommonsUtils.TEL_NUM_REGX))
					throw new UserException("账号格式错误");
				else selectConf = "userTel";
			else selectConf = "userEmail";
		else selectConf = "userAccount";
		
		Users user = null;
		try {
			//判断账号是否存在
			user = ud.findUserBySelectConf(new String[]{selectConf},username);
			if(user == null)
				throw new UserException("该账号还未注册");
			
			//判断密码是否正确
			pwd = CommonsUtils.parseMD5(pwd);  //加密
			String[] selectConfs = {selectConf,"userPwd"};
			user = ud.findUserBySelectConf(selectConfs, username,pwd);
			if(user == null)
				throw new UserException("密码错误");
		} catch (SQLException e) {
			throw new UserException("系统异常，请稍后再试");
		}
		
		//添加登录日志
		ulr.setUser(user);  //设置登录对象
		try {
			JdbcUtils.beginTransaction(); //开启事务
			ud.insertULR(ulr);  //添加
			JdbcUtils.commitTransaction(); //关闭事务
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction(); //回滚
			} catch (SQLException e1) { 
				throw new UserException("系统异常，请稍后再试");
			}  
		} 
		
		
		//都通过了就登录成功  返回当前登录对象
		return user;
	}

	/**
	 * 修改密码
	 * @param form
	 * @param code
	 * @param userPwd2
	 * @param verify
	 * @throws UserException 
	 */
	public void alterPwd(Users form, String code, String userPwd2, Verify verify) throws UserException {
		String account = form.getUserAccount();    //得到用户名
		String userPwd = form.getUserPwd();        //得到密码
		
		// 判断用户名是否为空
		if(account == null || account.isEmpty())
			throw new UserException("请输入用户名");
		
		//判断手机号/邮箱是否为空
		if(code == null || code.isEmpty())
			throw new UserException("请输入手机号/邮箱");
		
		//判断密码是否为空
		if(userPwd == null || userPwd.isEmpty())
			throw new UserException("请输入密码");
		
		//判断确认密码是否为空
		if(userPwd2 == null || userPwd2.isEmpty())
			throw new UserException("请输入确认密码");
		
		//判断验证码是否为空
		if(verify == null || verify.getInputVerify().isEmpty())
			throw new UserException("请输入验证码");
		
		//判断用户名格式是否正确
		if(!account.matches(CommonsUtils.USERNAME_REGX))
			throw new UserException("用户名格式不正确");
		
		//判断手机号/邮箱格式是否正确
		String selectCode = null;
		if(!code.matches(CommonsUtils.TEL_NUM_REGX))
			if(!code.matches(CommonsUtils.EMAIL_REGX))
				throw new UserException("手机号/邮箱 格式不正确");
			else selectCode = "userEmail";
		else selectCode = "userTel";
		
		//判断密码格式是否正确
		if(!userPwd.matches(CommonsUtils.PWD_REGX))
			throw new UserException("密码格式不正确");
		
		//判断验证码格式是否正确
		if("userEmail".equals(selectCode) && !verify.getInputVerify().matches(CommonsUtils.VERIFY_EMAIL_REGX))
			if("userTel".equals(selectCode) && !verify.getInputVerify().matches(CommonsUtils.VERIFY_TEL_REGX))
				throw new UserException("验证码格式有误");
		Users user = null;
		try {
			//判断用户名是否存在
			user = ud.findUserBySelectConf(new String[]{"userAccount"}, account);
			if(user == null)
				throw new UserException("该用户不存在");
			
			//判断手机号/邮箱是否存在
			user = ud.findUserBySelectConf(new String[]{selectCode}, code);
			if(user == null)
				throw new UserException("手机号/邮箱 不存在或未绑定用户");
			
			//判断用户名和手机号/邮箱是否匹配
			String[] selectConfs = {"userAccount",selectCode};
			Object[] params = {account,code};
			user = ud.findUserBySelectConf(selectConfs, params);
			if(user == null)
				throw new UserException("手机号/邮箱 与用户名不匹配");
			
			//判断验证码是否正确
			if(!verify.getInputVerify().equalsIgnoreCase(verify.getCreateVerify()))
				throw new UserException("验证码不正确");
			
		} catch (SQLException e) {
			throw new UserException("系统异常，请稍后再试！");
		}
		
		//都正确  修改密码
		try {
			JdbcUtils.beginTransaction();
			ud.alterPwd(user);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("系统异常，请稍后再试！");
			}
		}
	}

	/**
	 * 发送验证码
	 * @param code
	 * @param userAccount
	 * @return
	 * @throws UserException 
	 */
	public String sentVerify(String code, String account) throws UserException {
		//判断用户名是否为空
		if(account == null || account.isEmpty())
			throw new UserException("请输入要修改密码的用户名");
		
		//判断手机号/邮箱是否为空
		if(code == null || code.isEmpty())
			throw new UserException("请输入接收验证码的手机号/邮箱");
		
		//判断用户名格式是否正确
		if(!account.matches(CommonsUtils.USERNAME_REGX))
			throw new UserException("用户名格式不正确");
		
		//判断手机号/邮箱格式是否正确
		String selectCode = null;
		if(!code.matches(CommonsUtils.TEL_NUM_REGX))
			if(!code.matches(CommonsUtils.EMAIL_REGX))
				throw new UserException("手机号/邮箱 格式不正确");
			else selectCode = "userEmail";
		else selectCode = "userTel";
		
		Users user = null;
		try{
			//判断用户名是否存在
			user = ud.findUserBySelectConf(new String[]{"userAccount"}, account);
			if(user == null)
				throw new UserException("该用户名不存在");
			
			//判断手机号/邮箱是否存在
			user = ud.findUserBySelectConf(new String[]{selectCode}, code);
			if(user == null)
				throw new UserException("手机号/邮箱 不存在或未绑定用户");
			
			//判断手机号/邮箱是否与输入的用户名匹配
			String[] selectConfs = {"userAccount",selectCode};
			Object[] params = {account,code};
			System.out.println(account+"+"+code);
			user = ud.findUserBySelectConf(selectConfs, params);
			if(user == null)
				throw new UserException("手机号/邮箱 与用户名不匹配");
			
			//发送手机验证码/邮箱验证码
			String text = null;
			if("userEmail".equals(selectCode)){
				//发送邮箱验证码   
				text = CommonsUtils.createVerifyCode(4, CommonsUtils.VERIFY_CODE_TYPE_EMAIL); //生成验证码
				String to = code; //设置收件人
				Object[] codes = {user.getUserName(),text};  //设置补位
				CommonsUtils.sendMail(this.getClass(), to, codes, "alter_user_email.properties");  //发送邮件
			}else if("userTel".equals(selectCode)){
				//发送手机验证码
				text = CommonsUtils.createVerifyCode(6, CommonsUtils.VERIFY_CODE_TYPE_TEL);
				
			}
			return text;  //返回验证码
		}catch(SQLException e){
			throw new UserException("系统异常，请稍后再试！");
		}
	}
}
