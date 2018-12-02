package com.yc.movie.merchant.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Part;

import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Classifys;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Protagonists;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.Verification;
import com.yc.movie.exception.MerchantException;
import com.yc.movie.merchant.dao.MerchantDao;
import com.yc.movie.utils.CommonsUtils;
import com.yc.movie.utils.JdbcUtils;

import UDP.Rece;
import UDP.Send;
import sun.font.CreatedFontTracker;

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

	/**
	 * 发送验证码
	 * @param tel
	 * @throws MerchantException 
	 */
	public String sendTelCode(String tel) throws MerchantException {
		//判断手机号是否为Null
		if(tel == null || tel.isEmpty())
			throw new MerchantException("请输入手机号!");
		
		//判断手机号格式是否正确
		if(!tel.matches(CommonsUtils.TEL_NUM_REGX))
			throw new MerchantException("手机号格式不正确!");
		
		//发送
		String code = CommonsUtils.createVerifyCode(6, CommonsUtils.VERIFY_CODE_TYPE_TEL);
		CommonsUtils.sendTelCode(tel, "您正在进行实名验证，验证码【"+code+"】，请勿将验证码泄露给他人(15分钟内有效)");
		
		return code;
	}

	/**
	 * 实名认证
	 * @param form	merchant对象
	 * @param loginedMerchant	当前登录的merchant对象
	 * @param telCode	生成的手机验证码
	 * @param inVerify	输入的手机验证码
	 * @throws MerchantException
	 */
	public String realName(Merchant form,Merchant loginedMerchant,String telCode,String inVerify) throws MerchantException {
		String name = form .getMerName();
		String tel = form.getMerTel();
		String addr = form.getMerAddr();
		String card = form.getMerIDCard();
		boolean flag = true;
//		System.out.println(inVerify+"+"+telCode);
//		System.out.println(addr);
		
	
		
		//判断姓名是否为null
		if(name == null || name.trim().isEmpty())
			throw new MerchantException("请输入姓名！");
		
		//判断手机号码是否为Null
		if(tel == null || tel.trim().isEmpty())
			throw new MerchantException("请输入手机号！");
		
		//判断地址是否为null
		if(addr == null || addr.trim().isEmpty())
			throw new MerchantException("请输入地址！");
		if(addr.startsWith("中国 省"))
			throw new MerchantException("请输入地址！");
		
		//判断身份证是否为null
		if(card == null || card.trim().isEmpty())
			throw new MerchantException("请输入身份证号！");
		
		//判断输入的验证码是否为null
		if(inVerify == null || inVerify.trim().isEmpty())
			throw new MerchantException("请输入手机验证码！");
		
		//判断生成的验证码是否为null
		if(telCode == null || telCode.trim().isEmpty())
			throw new MerchantException("验证码输入错误！");
		
		//判断姓名格式
		if(!name.trim().matches(CommonsUtils.NAME_REGX))
			throw new MerchantException("姓名格式不正确！");
		
		//判断手机号码格式
		if(!tel.trim().matches(CommonsUtils.TEL_NUM_REGX))
			throw new MerchantException("手机号格式不正确！");
		
		//判断身份证格式
		if(!card.trim().matches(CommonsUtils.ID_NUM_REGX))
			throw new MerchantException("身份证号码格式不正确！");
		
		//判断验证码格式
		if(!inVerify.trim().matches("\\d{6}"))
			throw new MerchantException("验证码格式不正确！");
		
		//判断验证码是否正确
		if(!inVerify.trim().equals(telCode.trim()))
			throw new MerchantException("验证码错误！");
		try{
			//判断身份证是否已经被实名
			Merchant m = md.findMerchantByIdCard(form.getMerIDCard());
			if(m != null)
				throw new MerchantException("输入的身份证号码已经实名过了！");
			
			//判断手机号是否已经被注册
			m = md.findMerchantByTel(form.getMerTel());
			if(m != null)
				throw new MerchantException("输入的手机号码已经被绑定！");
		}catch(SQLException e){
			throw new MerchantException("系统异常，请稍后再试！");
		}
		
		//TCP发送给管理员
		String infoID = CommonsUtils.getUUID();
//			new Thread(new Send(new DatagramSocket())).start();
		byte[] buf = infoID.getBytes();
		try {
			DatagramSocket rece = new DatagramSocket(10005);
			new Thread(new Rece(rece)).start();
			
			DatagramSocket ds = new DatagramSocket();
			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 10005);
			ds.send(dp);
			ds.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return infoID;
	}

	/**
	 * 查询所有的类型
	 * @return
	 */
	public List<ClassifyName> findAllClassify() {
		try {
			return md.findAllClassify();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 非图片信息校验
	 * @param form
	 * @param moviePro
	 * @param movieClassify
	 * @throws MerchantException 
	 */
	public void movieRegx(Movies form, String moviePro, String movieClassify) throws MerchantException {
		String name = form.getMovieName();
		BigDecimal price = form.getMoviePrice();
		String descript = form.getMovieDescribe();
		Long inte = form.getMovieIntegralNum();
		
		//判断电影名是否为null
		if(name == null || name.trim().isEmpty())
			throw new MerchantException("请输入电影名！");
		
		//判断价格是否为null
		if(price == null)
			throw new MerchantException("请输入电影单价！");
		
		//判断描述是否为null
		if(descript == null || descript.trim().isEmpty())
			throw new MerchantException("请输入电影描述！");
		
		//判断积分数是否为null
		if(inte == null)
			throw new MerchantException("请输入积分数！");
		
		//判断主演是否为null
		if(moviePro == null || moviePro.trim().isEmpty())
			throw new MerchantException("请输入主演！");
		
		
	}

	/**
	 * 图片信息校验
	 * @param partList
	 * @throws MerchantException 
	 */
	public void movieRegx(List<Part> partList) throws MerchantException {
		if(partList == null || partList.size() < 3)
			throw new MerchantException("文件上传异常！");
		
		if(partList.get(0).getSubmittedFileName() == null || partList.get(0).getSubmittedFileName().trim().isEmpty())
			throw new MerchantException("请选择封面图片！");
		
		if(partList.get(1).getSubmittedFileName() == null || partList.get(1).getSubmittedFileName().trim().isEmpty())
			throw new MerchantException("请选择介绍图片！");
		
		if(partList.get(2).getSubmittedFileName() == null || partList.get(2).getSubmittedFileName().trim().isEmpty())
			throw new MerchantException("请选择单品显示图片！");
	}

	/**
	 * 电影上架
	 * @param form	电影
	 * @param sqlPaths	图片路径
	 * @param moviePro	主演集
	 * @param movieClassify	类型
	 * @param movieCount  电影票总数
	 * @throws MerchantException
	 */
	public void addMovie(Movies form , List<String> sqlPaths ,String moviePro , List<String> movieClassify ,Integer movieCount , String movieTime) throws MerchantException {
		try {
			JdbcUtils.beginTransaction();
			md.addMovie(form);  //添加电影
			
			Movies insertedMovie = md.findMovieByTime(form.getMovieCreateTime());  //获取到刚刚添加的电影
			
			//添加类型
			for(String s:movieClassify){
				Classifys c = new Classifys();
				c.setClassifyMovieId(insertedMovie.getMovieId());
				ClassifyName cn = md.findClassifyNameByName(s);  //获取类型对象
				c.setClassifyNameObj(cn); //保存类型对象
				md.addClassify(c);  //添加类型表
			}
			
			//添加主演
			String[] arr = moviePro.split(";");
			for(String s:arr){
				Protagonists p = new Protagonists();
				p.setProMovieId(insertedMovie.getMovieId());
				p.setProName(s);
				md.addPro(p);  //添加主演
			}
			
			//生成电影票
			for(int i=0;i<movieCount;i++){
				Ticket t = new Ticket();
				t.setTicketStartTime(Timestamp.valueOf(movieTime));  //上映时间
				t.setMovie(insertedMovie);
				t.setTicketStatus("1");
				md.addTicket(t);
			}
			
			//添加图片
			for(String s:sqlPaths){
				Images img = new Images();
				img.setImgPath(s);
				img.setImgMovieId(insertedMovie.getMovieId());
				md.addImage(img);
			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MerchantException("系统异常，请稍后再试！");
			}
		}
		
		
		
	}
}
