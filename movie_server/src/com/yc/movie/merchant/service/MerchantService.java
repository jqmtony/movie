package com.yc.movie.merchant.service;

import java.io.IOException;
import java.sql.SQLException;

import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Verification;
import com.yc.movie.exception.MerchantException;
import com.yc.movie.merchant.dao.MerchantDao;
import com.yc.movie.utils.CommonsUtils;
import com.yc.movie.utils.JdbcUtils;

public class MerchantService {
	private MerchantDao md = new MerchantDao();

	/**
	 * 登录
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException
	 */
	public Merchant login(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//判断生成的验证码是否为null
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//判断输入的验证码是否为null
			if(in == null || in.isEmpty())
				throw new MerchantException("请输入验证码！");
			
			//判断验证码是否正确
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("验证码输入错误！");
		}
		
		//判断用户名是否为null
		if(email == null || email.isEmpty())
			throw new MerchantException("请输入邮箱地址！");
		
		//判断密码是否为null
		if(pwd == null || pwd.isEmpty())
			throw new MerchantException("请输入密码！");
		
		//判断用户名格式是否正确
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("邮箱格式有误！");
		
		//判断用户名是否存在
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me == null)
				throw new MerchantException("该邮箱未注册！");
			
			//判断用户名和密码是否匹配
			pwd = CommonsUtils.parseMD5(pwd);
			me = md.findMerchantByEmailAndPwd(email,pwd);
			if(me == null)
				throw new MerchantException("密码错误！");
			
			//登陆成功 创造一个完整的商户对象
			me = md.createMerchant(me);
			return me;
		} catch (SQLException e) {
			throw new MerchantException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 注册
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException 
	 */
	public Merchant register(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//判断生成的验证码是否为null
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//判断输入的验证码是否为null
			if(in == null || in.isEmpty())
				throw new MerchantException("请输入验证码！");
			
			//判断验证码是否正确
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("验证码输入错误！");
		}
		
		//判断用户名是否为null
		if(email == null || email.isEmpty())
			throw new MerchantException("请输入邮箱地址！");
		
		//判断密码是否为null
		if(pwd == null || pwd.isEmpty())
			throw new MerchantException("请输入密码！");
		
		//判断用户名格式是否正确
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("邮箱格式有误！");
		
		//判断用户名是否存在
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me != null)
				throw new MerchantException("该邮箱已被注册！");
			
			//验证成功
			form.setMerStatus("0");
			form.setMerPwd(CommonsUtils.parseMD5(form.getMerPwd()));
			
			//发邮件
			//您好！您正在注册【影视天堂】后台商户管理账号：<h3><a href="http://{0}mer.s?method=registerAfter">点击激活</a></h3>
			Object[] codes = {CommonsUtils.getAddressAndProName(this.getClass())};
			CommonsUtils.sendMail(this.getClass(), form.getMerEmail(), codes, "register_merchant_email.properties");
			return form;
		} catch(IOException e1){
			throw new MerchantException("系统异常，系统文件被损坏！");
		}catch (SQLException e) {
			throw new MerchantException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 账号激活
	 * @param me
	 * @throws MerchantException 
	 */
	public void registerAfter(Merchant me) throws MerchantException {
		try {
			me.setMerStatus("0");  //商户账号的初始化状态为禁用   必须实名认证后才可以使用
			JdbcUtils.beginTransaction();
			md.insertMerchant(me);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MerchantException("系统异常，请稍后再试！");
			}
		}
	}

	/**
	 * 发送修改密码链接到邮箱
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException 
	 */
	public Merchant fogetBefore(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//判断生成的验证码是否为null
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//判断输入的验证码是否为null
			if(in == null || in.isEmpty())
				throw new MerchantException("请输入验证码！");
			
			//判断验证码是否正确
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("验证码输入错误！");
		}
		
		//判断用户名是否为null
		if(email == null || email.isEmpty())
			throw new MerchantException("请输入邮箱地址！");
		
		//判断用户名格式是否正确
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("邮箱格式有误！");
		
		//判断用户名是否存在
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me == null)
				throw new MerchantException("该邮箱未注册！");
			
			//发邮件
			//尊敬的 ：{0}，您好！您正在修改【影视天堂】后台商户管理账号的密码：<h3><a href="http://{2}mer.s?method=registerAfter">点击修改</a></h3>
			Object[] codes = {email,CommonsUtils.getAddressAndProName(this.getClass())};
			CommonsUtils.sendMail(this.getClass(), form.getMerEmail(), codes, "register_forget_email.properties");
			return form;
		} catch(IOException e1){
			throw new MerchantException("系统异常，系统文件被损坏！");
		}catch (SQLException e) {
			throw new MerchantException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 修改密码
	 * @param me
	 * @param pwd2
	 * @throws MerchantException 
	 */
	public void foget(Merchant me, String pwd2) throws MerchantException {
		String pwd1 = me.getMerPwd();
		//判断密码是否为null
		if(pwd1 == null || pwd1.isEmpty())
			throw new MerchantException("请输入密码！");

		//判断确认密码是否为null
		if(pwd2 == null || pwd2.isEmpty())
			throw new MerchantException("请输入确认密码！");
		
		//判断密码格式是否正确
		if(!pwd1.matches(CommonsUtils.PWD_REGX))
			throw new MerchantException("密码格式不正确！");
		
		//判断密码和确认密码是否相同
		if(!pwd1.equals(pwd2))
			throw new MerchantException("两次输入的密码不相同！");
		
		//加密
		me.setMerPwd(CommonsUtils.parseMD5(pwd1));
		
		try {
			JdbcUtils.beginTransaction();
			//修改
			md.updateByPwd(me);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MerchantException("系统异常，请稍后再试！");
			}
		}
	}
}
