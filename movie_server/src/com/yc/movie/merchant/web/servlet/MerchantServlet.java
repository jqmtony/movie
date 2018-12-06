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
	 * ��ӵ�Ӱ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addMovie(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//movieMerId  movieName movieIntegralNum  movieDescribe movieTimeLong moviePrevue moviePrice
		Movies form = CommonsUtils.toBean(request, Movies.class);
		String movieMerId = form.getMovieMerId();  //��ȡ�����ε��̻�ID
		String oldMovieMerId = null;
		try {
			oldMovieMerId = ms.getMovieMerIdByMovieName(form.getMovieName()); //ͨ����Ӱ���������ݿ�����ͬ�ĵ�Ӱ��
			if(oldMovieMerId == null)
				oldMovieMerId = "";
		} catch (MerchantException e1) {
			request.setAttribute("msg", e1.getMessage());
			return "f:/merUpload.jsp";
		}  
		String moviePro = request.getParameter("moviePro"); //����
		String classifyStr = request.getParameter("movieClassify");  //��ȡ�����ַ���
		String movieStartTime1 = request.getParameter("movieStartTime1");  //1������ӳʱ��
		String movieStartTime2 = request.getParameter("movieStartTime2");  //2������ӳʱ��
		String movieStartTime3 = request.getParameter("movieStartTime3");  //3������ӳʱ��
		String movieStartTime4 = request.getParameter("movieStartTime4");  //4������ӳʱ��
		String movieStartTime5 = request.getParameter("movieStartTime5");  //5������ӳʱ��
		String movieStartTime6 = request.getParameter("movieStartTime6");  //6������ӳʱ��
		
		Part moviePrevuePathPart = request.getPart("moviePrevue"); //�õ�Ԥ��Ƭ·����Part  �ϴ���ʱ��Ҫ��
		Part movieImage1Part = request.getPart("movieImage1");  //����ͼƬ��Part
		Part movieImage2Part = request.getPart("movieImage2");  //ͼƬ2��Part
		Part movieImage3Part = request.getPart("movieImage3"); //ͼƬ3��Part
		List<Part> partList = new ArrayList<Part>();
		partList.add(movieImage1Part);
		partList.add(movieImage2Part);
		partList.add(movieImage3Part);
		partList.add(moviePrevuePathPart);
		
		String moviePrevuePath = moviePrevuePathPart.getSubmittedFileName();  //Ԥ��Ƭ·��
		String movieImage1 = movieImage1Part.getSubmittedFileName();  //����ͼƬ
		String movieImage2 = movieImage2Part.getSubmittedFileName();  //ͼƬ2
		String movieImage3 = movieImage3Part.getSubmittedFileName();	//ͼƬ3
		
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
//		System.out.println("���ͣ�"+classifyStr);
//		System.out.println("Ԥ��Ƭ·����"+moviePrevuePath);
//		System.out.println("1������Ӱ��ʼʱ�䣺"+movieStartTime1);
//		System.out.println("2������Ӱ��ʼʱ�䣺"+movieStartTime2);
//		System.out.println("3������Ӱ��ʼʱ�䣺"+movieStartTime3);
//		System.out.println("4������Ӱ��ʼʱ�䣺"+movieStartTime4);
//		System.out.println("5������Ӱ��ʼʱ�䣺"+movieStartTime5);
//		System.out.println("6������Ӱ��ʼʱ�䣺"+movieStartTime6);
//		System.out.println("ͼƬ1·��"+movieImage1);
//		System.out.println("ͼƬ2·��"+movieImage2);
//		System.out.println("ͼƬ3·��"+movieImage3);
		
		try {
			ms.regxMovieInfo(form,m);  //��֤��Ϣ
			
			List<String> sqlPaths = new ArrayList<String>();
			//�ϴ�
			for(Part p : partList){
				String sqlPath = CommonsUtils.uploadFile(request,"/merHeadCreateImage", p);
				sqlPaths.add(sqlPath);
			}
			
			form.setMoviePrevue(sqlPaths.get(3)); //��ӵ�ӰԤ��Ƭ��·��
			ms.addMovie(form, m,sqlPaths,oldMovieMerId);  //��Ӱ�ϼ�
			
			request.setAttribute("msg", "�ϼܵ�Ӱ�ɹ�");
			return "f:/merUpload.jsp";
		} catch (MerchantException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/merUpload.jsp";
		}
		
	}
	/**
	 * �������е�����
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
	 * ʵ����֤
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void realName(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant form = CommonsUtils.toBean(request, Merchant.class);  // 
		String inVerify = request.getParameter("verify");  //��ȡ���û��������֤��
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
			request.getServletContext().setAttribute(infoID, form);  //��Ҫ��˵���Ϣ��������
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(e.getMessage());
		}
		
	}
	/**
	 * �����ֻ���֤��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendTelCode(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String tel = request.getParameter("telephone");  //��ȡ������ֻ���
		try {
			String telCode = ms.sendTelCode(tel);
			Cookie cookie = new Cookie("telCode", telCode);
			cookie.setMaxAge(60*15);  //15����
			response.addCookie(cookie);
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	/**
	 * �����л�ȡ��
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
	 * ����ʡID��ȡ�к���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCityAndDistrictByProvince(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String pId = request.getParameter("pId");  //��ȡʡid
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
	 * ��������ʡ
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
	 * �޸�����
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
	 * �����޸��������ӵ�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fogetBefore(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant form = CommonsUtils.toBean(request, Merchant.class);
		Verification verify = (Verification)session.getAttribute("session_verify");  //�õ���֤�����
		if(verify != null)
			verify.setInCode(request.getParameter("verify")); //�������֤��
		try {
			Merchant me = ms.fogetBefore(form,verify); 
			request.getServletContext().setAttribute("justAlterMer", me);  //��Ҫ�޸ĵ��û���������
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(" * "+e.getMessage());
		}
	}
	
	
	/**
	 * �û�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String registerAfter(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant me = (Merchant)request.getServletContext().getAttribute("justRegisterMer");
		if(me == null){
			request.setAttribute("msg", "�㻹δ��дע����Ϣ��");
			return "f:/merLogin.jsp";
		}
		try {
			ms.registerAfter(me);  //����
			request.getServletContext().removeAttribute("justRegisterMer");
			return "r:/mail/register.jsp";
		} catch (MerchantException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/merLogin.jsp";
		} 
	}
	
	/**
	 * ע��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//��װ������  merEmail  merPwd
		Merchant form = CommonsUtils.toBean(request, Merchant.class);
		Verification verify = (Verification)session.getAttribute("session_verify");  //�õ���֤�����
		if(verify != null)
			verify.setInCode(request.getParameter("verify")); //�������֤��
		try {
			Merchant me = ms.register(form,verify);   //�õ���merchant��û�д������ݿ�  ӦΪ����Ҫ���ʼ����м���
			request.getServletContext().setAttribute("justRegisterMer", me);  //��Ҫע����û���������
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			response.getWriter().append(" * "+e.getMessage());
		}
	}
	
	
	/**
	 * ��¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//��װ������  merEmail  merPwd
		Merchant form = CommonsUtils.toBean(request, Merchant.class);
		String isRemUser = request.getParameter("isRemUser");  //true/false
		Verification verify = (Verification)session.getAttribute("session_verify");  //�õ���֤�����
		if(verify != null)
			verify.setInCode(request.getParameter("verify")); //�������֤��
		try {
			Merchant me = ms.login(form,verify); 
//			System.out.println(me.getImgList().size());
			session.setAttribute("loginedMerchant", me);
			
			//��ס�˺�
			if(isRemUser != null && isRemUser.equals("true")){
				Cookie cookie = new Cookie("remMer", form.getMerEmail());
				cookie.setMaxAge(60*60*24*7);  //����
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
	 * ������֤��
	 */
	public void createVerification(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Verification verification = new Verification(); // ������֤�����
		BufferedImage image = verification.getImage(); // ������֤��ͼƬ
		session.setAttribute("session_verify", verification);// ����֤����󱣴浽session����
		Verification.output(image, response.getOutputStream());// ��ͼƬ��Ӧ���ͻ���
	}
}
