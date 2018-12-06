package com.yc.movie.merchant.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Verification;
import com.yc.movie.exception.MerchantException;
import com.yc.movie.merchant.service.MerchantService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.CommonsUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		String movieMerId = form.getMovieMerId();  //获取到本次的商户ID
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
		
//		System.out.println(form);
//		System.out.println("类型："+classifyStr);
//		System.out.println("预告片路径："+moviePrevuePath);
//		System.out.println("1号厅电影开始时间："+movieStartTime1);
//		System.out.println("2号厅电影开始时间："+movieStartTime2);
//		System.out.println("3号厅电影开始时间："+movieStartTime3);
//		System.out.println("4号厅电影开始时间："+movieStartTime4);
//		System.out.println("5号厅电影开始时间："+movieStartTime5);
//		System.out.println("6号厅电影开始时间："+movieStartTime6);
//		System.out.println("图片1路径"+movieImage1);
//		System.out.println("图片2路径"+movieImage2);
//		System.out.println("图片3路径"+movieImage3);
		
		try {
			ms.regxMovieInfo(form,m);  //验证信息
			
			List<String> sqlPaths = new ArrayList<String>();
			//上传
			for(Part p : partList){
				String sqlPath = CommonsUtils.uploadFile(request,"/merHeadCreateImage", p);
				sqlPaths.add(sqlPath);
			}
			
			form.setMoviePrevue(sqlPaths.get(3)); //添加电影预告片的路径
			ms.addMovie(form, m,sqlPaths,oldMovieMerId);  //电影上架
			
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
	 * 实名认证
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void realName(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant form = CommonsUtils.toBean(request, Merchant.class);  // 
		String inVerify = request.getParameter("verify");  //获取到用户输入的验证码
		String telCode = null;
		Cookie cookie = CommonsUtils.getCookie(request, "telCode");
		if(cookie != null)
			telCode = cookie.getValue();
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");
//		if(loginedMerchant == null){
//			response.getWriter().append("404");
//			return;
//		}
		try {
			String infoID = ms.realName(form,loginedMerchant,telCode,inVerify);
			form.setMerId(loginedMerchant.getMerId());
			request.getServletContext().setAttribute(infoID, form);  //将要审核的信息存在域中
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
