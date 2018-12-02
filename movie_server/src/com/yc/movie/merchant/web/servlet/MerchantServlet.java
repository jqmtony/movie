package com.yc.movie.merchant.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Verification;
import com.yc.movie.exception.MerchantException;
import com.yc.movie.merchant.service.MerchantService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.CommonsUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		Movies form = CommonsUtils.toBean(request, Movies.class);
		Integer movieCount = Integer.parseInt(request.getParameter("movieCount")); //电影票总数
		String movieTime = request.getParameter("movieTime");  //上映时间
		System.out.println("movieTime:"+movieTime);
		//		String movieName = request.getParameter("movieName");  //电影名
		String moviePro = request.getParameter("moviePro");  //电影主演集
		String movieClassify = request.getParameter("movieClassify1");  //电影类型
		String movieClassify2 = request.getParameter("movieClassify2");
		String movieClassify3 = request.getParameter("movieClassify3");
		List<String> movieClassifyList = new ArrayList<String>();
		movieClassifyList.add(movieClassify);
		movieClassifyList.add(movieClassify2);
		movieClassifyList.add(movieClassify3);
		
		String moviePrice = request.getParameter("moviePrice");  //电影单价
		Double l = Double.valueOf(moviePrice);
		form.setMoviePrice(BigDecimal.valueOf(l));
//		String movieDescript = request.getParameter("movieDescript"); //电影描述
//		String movieIntegralNum = request.getParameter("movieIntegralNum");  //电影积分数
		
		System.out.println(form);
		try {
			ms.movieRegx(form,moviePro,movieClassify);//非图片的信息校验
			
			Part part1 = request.getPart("image1");
			Part part2 = request.getPart("image2");
			Part part3 = request.getPart("image3");
			List<Part> partList = new ArrayList<Part>();
			partList.add(part1);
			partList.add(part2);
			partList.add(part3);
			
			ms.movieRegx(partList);  //图片信息校验
			
			List<String> sqlPaths = new ArrayList<String>();
			//上传
			for(Part p : partList){
				String sqlPath = CommonsUtils.uploadFile(request,"/merHeadCreateImage", p);
				sqlPaths.add(sqlPath);
			}
			
			Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");
			form.setMerchant(loginedMerchant);  //对应商户
			form.setMovieGradeNum(0d);  //评分数
			form.setMovieStatus("1");  //上架
			form.setMovieCreateTime(new Timestamp(new Date().getTime()));  //上架时间
			
			ms.addMovie(form,sqlPaths,moviePro,movieClassifyList,movieCount,movieTime);  //上架电影
			
			request.setAttribute("msg", "上架成功");
			return "f:/merUpload.jsp";
		} catch (MerchantException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/merUpload.jsp";
		}  
		
//		System.out.println(name3);
//		Collection<Part> partList = request.getParts();
//		for(Part part : partList){
//			String name = part.getSubmittedFileName();
//			part.write("merHeadCreateImage");
////			CommonsUtils.uploadFile("merHeadCreateImage", file)
//			String name2 = part.getName();
//			System.out.println(name2+":"+name);
//		}
//		return "f:/merUpload.jsp"; 
//		Movies movie = new Movies();
//		
//		//1.得到解析工厂
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		
//		//2.得到解析器
//		ServletFileUpload suf = new ServletFileUpload(factory);
//		
//		//3.解析
//		try {
//			List<FileItem> fileItem = suf.parseRequest(request);
//			for(FileItem fi : fileItem){
//				if(fi.isFormField()){ //普通
//					System.out.println(fi.getFieldName());
//					String infoUTF8 = CommonsUtils.getString_ISO8859_To_UTF8(fi.getString());
//					System.out.println(infoUTF8);
////					switch(fi.getFieldName()){
////					
////					}
//				}else{  //文件
//					System.out.println("文件："+fi.getFieldName());
////					String infoUTF8 = CommonsUtils.getString_ISO8859_To_UTF8(fi.getString());
//					System.out.println("文件："+fi.getString());
//				}
//			}
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return "f:/merUpload.jsp";
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
