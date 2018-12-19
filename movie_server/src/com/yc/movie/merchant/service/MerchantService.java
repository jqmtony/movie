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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Classifys;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.PageBean;
import com.yc.movie.bean.Protagonists;
import com.yc.movie.bean.Sub;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.Users;
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
			e.printStackTrace();
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
			e1.printStackTrace();
			throw new MerchantException("系统异常，系统文件被损坏！");
		}catch (SQLException e) {
			e.printStackTrace();
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
			md.insertMerchant(me);  //插入新注册的商户信息到数据库
			
			Merchant m = md.findMerchantByEmail(me.getMerEmail());
			
			//插入商户默认头像
			Images im = new Images();
			im.setImgMerchantId(m.getMerId());  //设置商户ID
			im.setImgStatus("头像");
			im.setImgPath("/images/uploadLogo.png");
			md.insertImage(im);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
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
			e1.printStackTrace();
			throw new MerchantException("系统异常，系统文件被损坏！");
		}catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
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
		String storeName = form.getMerStoreName();
		String name = form .getMerName();
		String tel = form.getMerTel();
		String addr = form.getMerAddr();
		String card = form.getMerIDCard();
		boolean flag = true;
		
		//判断姓名是否为null
		if(name == null || name.trim().isEmpty())
			throw new MerchantException("请输入姓名！");
		
		//判断店名是否为null
		if(storeName == null || storeName.trim().isEmpty())
			throw new MerchantException("请输入店名！");
		
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
			if(m != null && !m.getMerIDCard().equals(loginedMerchant.getMerIDCard()))
				throw new MerchantException("输入的身份证号码已经实名过了！");
			
			//判断手机号是否已经被注册
			m = md.findMerchantByTel(form.getMerTel());
			if(m != null && !m.getMerTel().equals(loginedMerchant.getMerTel()))
				throw new MerchantException("输入的手机号码已经被绑定！");
		}catch(SQLException e){
			e.printStackTrace();
			throw new MerchantException("系统异常，请稍后再试！");
		}
		
		try {
			JdbcUtils.beginTransaction();
			
			md.saveMerchant(form);  //保存商户认证信息
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new MerchantException("系统异常，请稍后再试！");
			}
		}
		
		//TCP发送给管理员
//		String infoID = CommonsUtils.getUUID();
////			new Thread(new Send(new DatagramSocket())).start();
//		byte[] buf = infoID.getBytes();
//		try {
//			DatagramSocket rece = new DatagramSocket(10005);
//			new Thread(new Rece(rece)).start();
//			
//			DatagramSocket ds = new DatagramSocket();
//			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 10005);
//			ds.send(dp);
//			ds.close();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		return null;
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
	 * 信息校验
	 * @param form //movieMerId  movieName movieIntegralNum  movieDescribe movieTimeLong moviePrevue moviePrice
	 * @param m
	 * @throws MerchantException 
	 */
	public void regxMovieInfo(Movies form, Map<String, String> m) throws MerchantException {
		String moviePro = m.get("moviePro");   //主演
		String classifyStr = m.get("classifyStr");  //获取类型字符串
		String moviePrevuePath = m.get("moviePrevuePath");  //预告片路径
		String movieStartTime1 = m.get("movieStartTime1");  //1号厅上映时间
		String movieStartTime2 = m.get("movieStartTime2");  //2号厅上映时间
		String movieStartTime3 = m.get("movieStartTime3");  //3号厅上映时间
		String movieStartTime4 = m.get("movieStartTime4");  //4号厅上映时间
		String movieStartTime5 = m.get("movieStartTime5");  //5号厅上映时间
		String movieStartTime6 = m.get("movieStartTime6");  //6号厅上映时间
		String movieImage1 = m.get("movieImage1");  //封面图片
		String movieImage2 = m.get("movieImage2");  //图片2
		String movieImage3 = m.get("movieImage3");  //图片3
		String movieName = form.getMovieName();  //电影名
		Long movieIntegralNum = form.getMovieIntegralNum();  //积分数
		String movieDescribe = form.getMovieDescribe();  //电影简介
		Long movieTimeLong = form.getMovieTimeLong();  //电影时长
		BigDecimal moviePrice = form.getMoviePrice();  //单价
		String movieGenre = form.getMovieGenre(); //片种
		
		//判断电影名是否为null
		if(isNull(movieName))
			throw new MerchantException("请输入电影名！");
		
		//判断主演是否为null
		if(isNull(moviePro))
			throw new MerchantException("请输入主演！");
		
		//判断分类是否为null
		if(isNull(classifyStr))
			throw new MerchantException("请输入类型！");
		
		//判断片种是否为null
		if(isNull(movieGenre))
			throw new MerchantException("请输入片种！");
		
		//判断单价是否为null
		if(moviePrice == null)
			throw new MerchantException("请输入单价！");
		
		//判断积分是否为Null
		if(movieIntegralNum == null)
			throw new MerchantException("请输入积分数！");
		
		//判断电影时长是否为Null
		if(movieTimeLong == null)
			throw new MerchantException("请输入电影播放时长！");
		
		//判断预告片是否为null
		if(isNull(moviePrevuePath))
			throw new MerchantException("请上传预告片！");
		
		//判断描述是否为null
		if(isNull(movieDescribe))
			throw new MerchantException("请输入电影简介！");
		
		//判断上映时间是否为null
		if(isNull(movieStartTime1) && isNull(movieStartTime2) && isNull(movieStartTime3) && 
				isNull(movieStartTime4) && isNull(movieStartTime5) && isNull(movieStartTime6))
			throw new MerchantException("请至少选择一个电影播放厅并填写上映时间！");
		
		//判断movieImage是否为null
		if(isNull(movieImage1) || isNull(movieImage2) || isNull(movieImage3))
			throw new MerchantException("你还没有上传电影的图片，电影的三张图片必须上传！");
	}
	
	/**
	 * 判断字符串是否为Null
	 * @param str
	 * @return
	 */
	private boolean isNull(String str){
		if(str == null || str.trim().isEmpty())
			return true;
		return false;
	}

	/**
	 * 电影上架
	 * @param form
	 * @param m
	 * @throws MerchantException 
	 */
	public void addMovie(Movies form, Map<String, String> m,List<String> sqlPaths,String oldMovieMerId,Long merId) throws MerchantException {
		String moviePro = m.get("moviePro");   //主演
		String classifyStr = m.get("classifyStr");  //获取类型字符串
		
		String movieStartTime1 = m.get("movieStartTime1");  //1号厅上映时间
		String movieStartTime2 = m.get("movieStartTime2");  //2号厅上映时间
		String movieStartTime3 = m.get("movieStartTime3");  //3号厅上映时间
		String movieStartTime4 = m.get("movieStartTime4");  //4号厅上映时间
		String movieStartTime5 = m.get("movieStartTime5");  //5号厅上映时间
		String movieStartTime6 = m.get("movieStartTime6");  //6号厅上映时间
//		String moviePrevuePath = m.get("moviePrevuePath");  //预告片路径   这四个文件的路径都在sqlPaths集合中
//		String movieImage1 = m.get("movieImage1");  //封面图片
//		String movieImage2 = m.get("movieImage2");  //图片2
//		String movieImage3 = m.get("movieImage3");  //图片3
		String movieName = form.getMovieName();  //电影名
		Long movieIntegralNum = form.getMovieIntegralNum();  //积分数
		String movieDescribe = form.getMovieDescribe();  //电影简介
		Long movieTimeLong = form.getMovieTimeLong();  //电影时长
		String moviePrevue = form.getMoviePrevue();  //电影片种
		BigDecimal moviePrice = form.getMoviePrice();  //单价
		
		form.setMovieVisitCount(0l);  //设置初始访问量为0
		form.setMovieGradeNum(0.0);  //设置初始评分为0
		form.setMovieStatus("1");  //设置电影的状态  1为正常
		form.setMovieCreateTime(new Timestamp(new Date().getTime()));  //设置电影上架时间
		
		//进入数据修改  进入事务
		try {
			JdbcUtils.beginTransaction();
			
			//如果此商户已经上映了此部电影   就只是增加电影票数
			Movies m1 = md.haveMovie(form.getMovieName());
			if(m1 != null){  //数据库中已经有movie了
				if(m1.getMovieMerId().contains(form.getMovieMerId()+"")){  //如果此商户已经有了这部电影
					System.out.println("只生成电影票。");
					//生成电影票
					if(movieStartTime1!=null){ 
						createTicket(movieStartTime1, m1.getMovieId(),1,movieTimeLong,merId);
					}
					if(movieStartTime2!=null){
						createTicket(movieStartTime2, m1.getMovieId(),2,movieTimeLong,merId);
					}
					if(movieStartTime3!=null){
						createTicket(movieStartTime3, m1.getMovieId(),3,movieTimeLong,merId);
					}
					if(movieStartTime4!=null){
						createTicket(movieStartTime4, m1.getMovieId(),4,movieTimeLong,merId);
					}
					if(movieStartTime5!=null){
						createTicket(movieStartTime5, m1.getMovieId(),5,movieTimeLong,merId);
					}
					if(movieStartTime6!=null){
						createTicket(movieStartTime6, m1.getMovieId(),6,movieTimeLong,merId);
					}
					JdbcUtils.commitTransaction();
					return;
				}else if(!m1.getMovieMerId().contains(form.getMovieMerId()+"")){
					//此商户还没有此电影
					//1.设置此电影的商户ID   将此商户添加到电影的商户ID中
					String str = oldMovieMerId+form.getMovieMerId()+";";
					md.updateMerchantId(form.getMovieName(),str);
					
					//2.添加电影票
					if(movieStartTime1!=null){ 
						createTicket(movieStartTime1, m1.getMovieId(),1,movieTimeLong,merId);
					}
					if(movieStartTime2!=null){
						createTicket(movieStartTime2, m1.getMovieId(),2,movieTimeLong,merId);
					}
					if(movieStartTime3!=null){
						createTicket(movieStartTime3, m1.getMovieId(),3,movieTimeLong,merId);
					}
					if(movieStartTime4!=null){
						createTicket(movieStartTime4, m1.getMovieId(),4,movieTimeLong,merId);
					}
					if(movieStartTime5!=null){
						createTicket(movieStartTime5, m1.getMovieId(),5,movieTimeLong,merId);
					}
					if(movieStartTime6!=null){
						createTicket(movieStartTime6, m1.getMovieId(),6,movieTimeLong,merId);
					}
					JdbcUtils.commitTransaction();
					return;
				}
			}
			
				
			String str = oldMovieMerId+form.getMovieMerId()+";";
			md.updateMerchantId(form.getMovieName(),str);
			
			form.setMovieMerId(str);  //修改电影的商户ID
			md.addMovie(form); //添加电影
			
			Movies insertedMovie = md.findMovieByTime(form.getMovieCreateTime());  //获取到刚刚添加的电影
			
			//添加类型
			String[] classifys = classifyStr.split(";");  //获取到类型数组
			for(String s:classifys){
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
			if(movieStartTime1!=null){ 
				createTicket(movieStartTime1, insertedMovie.getMovieId(),1,movieTimeLong,merId);
			}
			if(movieStartTime2!=null){
				createTicket(movieStartTime2, insertedMovie.getMovieId(),2,movieTimeLong,merId);
			}
			if(movieStartTime3!=null){
				createTicket(movieStartTime3, insertedMovie.getMovieId(),3,movieTimeLong,merId);
			}
			if(movieStartTime4!=null){
				createTicket(movieStartTime4, insertedMovie.getMovieId(),4,movieTimeLong,merId);
			}
			if(movieStartTime5!=null){
				createTicket(movieStartTime5, insertedMovie.getMovieId(),5,movieTimeLong,merId);
			}
			if(movieStartTime6!=null){
				createTicket(movieStartTime6, insertedMovie.getMovieId(),6,movieTimeLong,merId);
			}
			
			//添加文件路径到数据库
			int i = 1;
			for(String path:sqlPaths){  //预告片在最后一个
				if(i>4)
					break;
				Images img = new Images();
				img.setImgPath(path);
				if(i == 1)
					img.setImgStatus("封面");
				else if(i == 2)
					img.setImgStatus("介绍");
				else if(i == 3)
					img.setImgStatus("单品展示");
				else
					img.setImgStatus("售票展示");
				img.setImgMovieId(insertedMovie.getMovieId());
				md.addImage(img);
				i++;
			}
			
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new MerchantException("系统异常，请稍后再试");
			}
		}
	}

	/**
	 * 根据电影厅生成电影票   一个电影厅12 * 17 = 204个座位
	 * @param movieStartTime
	 * @param insertedMovie
	 * @param theaterNum
	 * @throws SQLException
	 */
	private void createTicket(String movieStartTime, Long insertedMovie,int theaterNum ,Long minute,Long merId) throws SQLException {
		Long num = 1l;
		for(int i=1;i<=12;i++){
			for(int j=1;j<=17;j++){
				Ticket t = new Ticket();
				t.setTicketStartTime(Timestamp.valueOf(movieStartTime));  //上映时间
				t.setTicketMovieStartTime(Timestamp.valueOf(movieStartTime)); //上映时间  两个其实是一样的
				t.setTicketLocation(i+"排"+j+"列");	//座位
				t.setTicketMovieTheater(theaterNum+"号厅");  //上映厅
				t.setTicketMovieId(insertedMovie);
				Long time = t.getTicketStartTime().getTime()+(minute*60*1000);  //得到结束时间戳
				t.setTicketMovieEndTime(new Timestamp(time));  //设置结束时间
				t.setTicketStatus("1");
				t.setTicketMerId(merId);
				t.setTicketLocationNum(num);
				num++;
				md.addTicket(t);
			}
		}
	}

	/**
	 * //通过电影名查找数据库中相同的商户id
	 * @param movieName
	 * @return
	 * @throws MerchantException 
	 */
	public String getMovieMerIdByMovieName(String movieName) throws MerchantException {
		try {
			return md.getMovieMerIdByMovieName(movieName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MerchantException("系统异常，请稍后再试");
		}
	}

	/**
	 * 发送邮件给订阅了我们网站的邮箱
	 * @param form
	 * @throws MerchantException 
	 */
	public void sendEmailToSub(Movies form) throws MerchantException {
		try {
			List<Sub> subList = md.findAllSub();  //找到所有的订阅邮箱
			//您好！您订阅的【影视天堂】更新了，更新内容：上架电影《{0}》。
			for(Sub s : subList){
				String to = s.getSubEmail();
				String fileName = "sub_email.properties";
				Object[] codes = {form.getMovieName()};
				CommonsUtils.sendMail(this.getClass(), to, codes, fileName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MerchantException("系统异常，请稍后再试");
		}
	}

	/**
	 * 查找所有用户
	 * @return
	 * @throws MerchantException 
	 */
	public List<Users> findAllUser() throws MerchantException {
		try {
			return md.findAllUser();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MerchantException("系统异常，请稍后再试");
		}
	}

	/**
	 * 根据用户ID查找用户
	 * @param userId
	 * @return
	 * @throws MerchantException 
	 */
	public Users findUserByUserId(Long userId) throws MerchantException {
		try {
			return md.findUserByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MerchantException("系统异常，请稍后再试");
		}
	}

	/**
	 * 修改头像
	 * @param img
	 * @throws MerchantException 
	 */
	public void setImgPath(Images img) throws MerchantException {
		try {
			md.setImagePath(img);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MerchantException("系统异常，请稍后再试");
		}
	}

	/**
	 * 查找商户对应的所有电影对象
	 * @param loginedMer
	 * @throws MerchantException 
	 */
	public PageBean<Movies> findMovieByMer(Merchant loginedMer,int pc,int ps) throws MerchantException {
		try {
			PageBean<Movies> pb = new PageBean<Movies>();
			pb.setPc(pc);
			pb.setPs(ps);
			
			pb = md.createPageBeanByMovie(pb,loginedMer.getMerId());
			
			return pb;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MerchantException("系统异常，请稍后再试");
		}
	}

	/**
	 * 设置电影状态
	 * @param type
	 * @param movieId
	 * @throws MerchantException 
	 */
	public void setMovieStatus(String type, Long movieId) throws MerchantException {
		try {
			JdbcUtils.beginTransaction();
			
			md.setMovieStatus(type,movieId);
			
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new MerchantException("系统异常，请稍后再试");
			}
		}
	}

	/**
	 * 根据商户ID查询商户
	 * @param merId
	 * @return
	 * @throws MerchantException 
	 */
	public Merchant findMerchantById(Long merId) throws MerchantException {
		try {
			return md.findMerchantById(merId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MerchantException("系统异常，请稍后再试");
		}
	}

	
}
