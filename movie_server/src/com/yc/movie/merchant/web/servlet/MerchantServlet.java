package com.yc.movie.merchant.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.PageBean;
import com.yc.movie.bean.Users;
import com.yc.movie.bean.Verification;
import com.yc.movie.exception.MerchantException;
import com.yc.movie.merchant.service.MerchantService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.CommonsUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class MerchantServlet
 */
@WebServlet("/mer.s")
@MultipartConfig
public class MerchantServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private MerchantService ms = new MerchantService();
	private static Long userFlag = 0l;
	private static Long merFlag = 1l;
	
	/**
	 * 更改电影的状态
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void setMovieStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");  //获取到要设置的状态
		Long movieId = Long.parseLong(request.getParameter("movieId"));  //获取到要设置的电影ID
		try {
			ms.setMovieStatus(type,movieId);
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			e.printStackTrace();
			response.getWriter().append(e.getMessage());
		}  
	}
	/**
	 * 查找当前登录商户中的电影
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findMovieByMer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Merchant loginedMer = (Merchant)session.getAttribute("loginedMerchant"); //得到当前登录的商户
		Integer pc = CommonsUtils.getPageListPc(request);  //得到pc  当前页
		try {
			PageBean<Movies> pb = ms.findMovieByMer(loginedMer,pc,3);
			request.setAttribute("pageBean_movie", pb);
			return "f:/merProduct_Manage.jsp";
		} catch (MerchantException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			return "f:/merProduct_Manage.jsp";
		}
	}
	
	
	/**
	 * 刷新接收信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void revicedContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Users> userList = null;
		try {
			userList = ms.findAllUser();
		} catch (MerchantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("userMerList", userList);
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");  //取出当前登录的用户
		String str = "userToMer"+loginedMerchant.getMerId();  // userToMer2
		String fileName = request.getServletContext().getRealPath("/onlineChat/sendUserToMer.properties").replace("movie_server", "movie_client");
		File file = new File(fileName);
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		
		String text = p.getProperty(str);
//		System.out.println("商户收到的信息："+text);
		if(text == null || text.isEmpty()){
			response.getWriter().append("no");
			return;
		}
		String[] arr = text.split(";");
//		for(String s:arr)
//			System.out.println(s);
		Long flag = Long.parseLong(arr[0]);
		if(flag.equals(merFlag)){
			response.getWriter().append("no");
		}else{
			String content = arr[1];
			System.out.println("商户收到的信息："+content);
			merFlag = flag;
			response.getWriter().append(content);
		}
		
	}
	
	/**
	 * 将聊天信息发送给服务器
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendContentToChatServer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");  //获取消息内容
		String id = request.getParameter("id");  
		id = id.substring(8);  //获取用户ID
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");  //取出当前登录的商户
		
		String fileName = request.getServletContext().getRealPath("/onlineChat/sendMerToUser.properties").replace("movie_client", "movie_server");
		File file = new File(fileName);
		Properties p = new Properties();
		FileOutputStream fos = new FileOutputStream(file);
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		
		p.put("merToUser"+id, userFlag+";"+loginedMerchant.getMerId()+"{[code]}"+content+"{[code]}"+loginedMerchant.getImgList().get(0).getImgPath());
//		System.out.println("商户向用户发送消息："+userFlag+";"+loginedMerchant.getMerId()+"{[code]}"+content);
		userFlag++;
		p.store(fos, "");
		
		fos.close();
	}
	
	
	/**
	 * 查找所有用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			List<Users> userList = ms.findAllUser();
			session.setAttribute("userMerList", userList);
			return "r:/onlineChat/onlineChat.jsp";
		} catch (MerchantException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/merIndex.jsp";
		}
	}
	/**
	 * 添加电影
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addMovie(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//movieMerId  movieName movieIntegralNum  movieDescribe movieTimeLong moviePrevue moviePrice
		Movies form = CommonsUtils.toBean(request, Movies.class);
		
		//给电影名加《》号
		String name = form.getMovieName();
		if(!name.startsWith("《")){
			name = "《" + name;
		}
		if(!name.endsWith("》")){
			name = name + "》";
		}
		form.setMovieName(name);
		
		String movieMerId = form.getMovieMerId();  //获取到本次的商户ID
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");  //得到当前登录商户
		String oldMovieMerId = null;
		try {
			oldMovieMerId = ms.getMovieMerIdByMovieName(form.getMovieName()); //通过电影名查找数据库中相同的电影名
			if(oldMovieMerId == null)
				oldMovieMerId = "";
		} catch (MerchantException e1) {
			request.setAttribute("msg", e1.getMessage());
			return "f:/merUpload.jsp";
		}  
		String moviePro = request.getParameter("moviePro"); //主演
		String classifyStr = request.getParameter("movieClassify");  //获取类型字符串
		String movieStartTime1 = request.getParameter("movieStartTime1");  //1号厅上映时间
		String movieStartTime2 = request.getParameter("movieStartTime2");  //2号厅上映时间
		String movieStartTime3 = request.getParameter("movieStartTime3");  //3号厅上映时间
		String movieStartTime4 = request.getParameter("movieStartTime4");  //4号厅上映时间
		String movieStartTime5 = request.getParameter("movieStartTime5");  //5号厅上映时间
		String movieStartTime6 = request.getParameter("movieStartTime6");  //6号厅上映时间
		
		Part moviePrevuePathPart = request.getPart("moviePrevue"); //得到预告片路径的Part  上传的时候要用
		Part movieImage1Part = request.getPart("movieImage1");  //封面图片的Part
		Part movieImage2Part = request.getPart("movieImage2");  //图片2的Part
		Part movieImage3Part = request.getPart("movieImage3"); //图片3的Part
		List<Part> partList = new ArrayList<Part>();
		partList.add(movieImage1Part);
		partList.add(movieImage2Part);
		partList.add(movieImage3Part);
		partList.add(moviePrevuePathPart);
		
		String moviePrevuePath = moviePrevuePathPart.getSubmittedFileName();  //预告片路径
		String movieImage1 = movieImage1Part.getSubmittedFileName();  //封面图片
		String movieImage2 = movieImage2Part.getSubmittedFileName();  //图片2
		String movieImage3 = movieImage3Part.getSubmittedFileName();	//图片3
		
		Map<String,String> m = new LinkedHashMap<String,String>();
		m.put("movieStartTime1", movieStartTime1);
		m.put("movieStartTime2", movieStartTime2);
		m.put("movieStartTime3", movieStartTime3);
		m.put("movieStartTime4", movieStartTime4);
		m.put("movieStartTime5", movieStartTime5);
		m.put("movieStartTime6", movieStartTime6);
		m.put("movieImage1", movieImage1);
		m.put("movieImage2", movieImage2);
		m.put("movieImage3", movieImage3);
		m.put("classifyStr", classifyStr);
		m.put("moviePrevuePath", moviePrevuePath);
		m.put("moviePro", moviePro);
		
		
		try {
			ms.regxMovieInfo(form,m);  //验证信息
			
			List<String> sqlPaths = new ArrayList<String>();
			
			//上传
			String sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", partList.get(0),250,300);
			sqlPaths.add(sqlPath);
			sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", partList.get(1),650,410);
			sqlPaths.add(sqlPath);
			sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", partList.get(2),1680,850);
			sqlPaths.add(sqlPath);
			sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", partList.get(2),720,338);
			sqlPaths.add(sqlPath);
			sqlPaths.add(CommonsUtils.uploadFile(request, "/merHeadCreateImage", partList.get(3)));
			
			form.setMoviePrevue(sqlPaths.get(4)); //添加电影预告片的路径
			ms.addMovie(form, m,sqlPaths,oldMovieMerId,loginedMerchant.getMerId());  //电影上架
			
			//上架成功   发送邮件给订阅了我们网站的邮箱
			ms.sendEmailToSub(form);
			
			request.setAttribute("msg", "上架电影成功");
			return "f:/merUpload.jsp";
		} catch (MerchantException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/merUpload.jsp";
		}
		
	}
	/**
	 * 查找所有的类型
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findClassify(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List<ClassifyName> list = ms.findAllClassify();
		session.setAttribute("classifyList", list);
		return "r:/merUpload.jsp";
	}
	
	
	/**
	 * 上传照片
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void uploadHeadImg(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Part p = request.getPart("file");
		String sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", p,200,200);
		System.out.println("sqlPath:"+sqlPath);
		
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");
		
		Images img = new Images();  //创建头像
		img.setImgMerchantId(loginedMerchant.getMerId());  //设置商户ID
		img.setImgStatus("头像");
		img.setImgPath(sqlPath);  //设置图片路径
		
		List<Images> imgList = new ArrayList<Images>();
		imgList.add(img);
		loginedMerchant.setImgList(imgList);
		try {
			ms.setImgPath(img);
		} catch (MerchantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 实名认证
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void realName(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant form = CommonsUtils.toBean(request, Merchant.class);  // 
		
//		System.out.println("商户认证信息："+form);
		String inVerify = request.getParameter("verify");  //获取到用户输入的验证码
		String telCode = null;
		Cookie cookie = CommonsUtils.getCookie(request, "telCode");
		if(cookie != null)
			telCode = cookie.getValue();
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");
		
		try {
			form.setMerId(loginedMerchant.getMerId());
			String infoID = ms.realName(form,loginedMerchant,telCode,inVerify);
//			form.setMerId(loginedMerchant.getMerId());
//			request.getServletContext().setAttribute(infoID, form);  //将要审核的信息存在域中
			loginedMerchant = ms.findMerchantById(loginedMerchant.getMerId());
			session.setAttribute("loginedMerchant", loginedMerchant);
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(e.getMessage());
		}
		
	}
	/**
	 * 发送手机验证码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendTelCode(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String tel = request.getParameter("telephone");  //获取输入的手机号
		try {
			String telCode = ms.sendTelCode(tel);
			Cookie cookie = new Cookie("telCode", telCode);
			cookie.setMaxAge(60*15);  //15分钟
			response.addCookie(cookie);
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	/**
	 * 根据市获取县
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getDistrictByCity(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String cId = request.getParameter("cId");
		Map<String,String> dis = CommonsUtils.xmlDistricts(cId,this.getClass());
		String jsonStr = JSON.toJSONString(dis);
		response.getWriter().append(jsonStr);
	}
	/**
	 * 根据省ID获取市和县
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCityAndDistrictByProvince(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String pId = request.getParameter("pId");  //获取省id
		Map<String,String> cityMap = CommonsUtils.xmlCities(pId, this.getClass());
		String s = null;
		for(Iterator<Entry<String, String>> it = cityMap.entrySet().iterator();it.hasNext();){
			Entry<String, String> me = it.next();
			s = me.getKey();
			break;
		}
		Map<String ,String> dis = CommonsUtils.xmlDistricts(s,this.getClass());
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list.add(cityMap);
		list.add(dis);
		String jsonStr = JSON.toJSONString(list);
		response.getWriter().append(jsonStr);
	}
	/**
	 * 生成所有省
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getMapByProvinces(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Map<String,String> map = CommonsUtils.xmlProvince(this.getClass());
		session.setAttribute("provincesMap", map);
		return "r:/merAlterInfo/merAlterInfo.jsp";
	}
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forget(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant me = (Merchant)request.getServletContext().getAttribute("justAlterMer");
		String pwd1 = request.getParameter("pwd1");
		String pwd2 = request.getParameter("pwd2");
		me.setMerPwd(pwd1);
		try {
			ms.foget(me,pwd2);
			request.getServletContext().removeAttribute("justAlterMer");
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	
	/**
	 * 发送修改密码链接到邮箱
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fogetBefore(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant form = CommonsUtils.toBean(request, Merchant.class);
		Verification verify = (Verification)session.getAttribute("session_verify");  //得到验证码对象
		if(verify != null)
			verify.setInCode(request.getParameter("verify")); //如果有验证码
		try {
			Merchant me = ms.fogetBefore(form,verify); 
			request.getServletContext().setAttribute("justAlterMer", me);  //将要修改的用户保存起来
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(" * "+e.getMessage());
		}
	}
	
	
	/**
	 * 用户激活
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String registerAfter(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant me = (Merchant)request.getServletContext().getAttribute("justRegisterMer");
		if(me == null){
			request.setAttribute("msg", "你还未填写注册信息！");
			return "f:/merLogin.jsp";
		}
		try {
			ms.registerAfter(me);  //激活
			request.getServletContext().removeAttribute("justRegisterMer");
			return "r:/mail/register.jsp";
		} catch (MerchantException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/merLogin.jsp";
		} 
	}
	
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//封装表单数据  merEmail  merPwd
		Merchant form = CommonsUtils.toBean(request, Merchant.class);
		Verification verify = (Verification)session.getAttribute("session_verify");  //得到验证码对象
		if(verify != null)
			verify.setInCode(request.getParameter("verify")); //如果有验证码
		try {
			Merchant me = ms.register(form,verify);   //得到的merchant还没有存入数据库  应为还需要发邮件进行激活
			request.getServletContext().setAttribute("justRegisterMer", me);  //将要注册的用户保存起来
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(" * "+e.getMessage());
		}
	}
	
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//封装表单数据  merEmail  merPwd
		Merchant form = CommonsUtils.toBean(request, Merchant.class);
		
		String isRemUser = request.getParameter("isRemUser");  //true/false
		Verification verify = (Verification)session.getAttribute("session_verify");  //得到验证码对象
		if(verify != null)
			verify.setInCode(request.getParameter("verify")); //如果有验证码
		try {
			form.setMerIpAddr(request.getRemoteAddr());  //设置当前登录IP
			Merchant me = ms.login(form,verify); 
//			System.out.println(me.getImgList().size());
			session.setAttribute("loginedMerchant", me);
			
			//记住账号
			if(isRemUser != null && isRemUser.equals("true")){
				Cookie cookie = new Cookie("remMer", form.getMerEmail());
				cookie.setMaxAge(60*60*24*7);  //七天
				response.addCookie(cookie);
			}else{
				CommonsUtils.removeCookie(request, response, "remMer");
			}
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(" * "+e.getMessage());
		}
	}
	
 	/**
	 * 生成验证码
	 */
	public void createVerification(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Verification verification = new Verification(); // 创建验证码对象
		BufferedImage image = verification.getImage(); // 生成验证码图片
		session.setAttribute("session_verify", verification);// 将验证码对象保存到session域中
		Verification.output(image, response.getOutputStream());// 把图片响应给客户端
	}
}
