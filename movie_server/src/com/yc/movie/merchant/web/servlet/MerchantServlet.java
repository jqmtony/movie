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
	 * ��ӵ�Ӱ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addMovie(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Movies form = CommonsUtils.toBean(request, Movies.class);
		Integer movieCount = Integer.parseInt(request.getParameter("movieCount")); //��ӰƱ����
		String movieTime = request.getParameter("movieTime");  //��ӳʱ��
		System.out.println("movieTime:"+movieTime);
		//		String movieName = request.getParameter("movieName");  //��Ӱ��
		String moviePro = request.getParameter("moviePro");  //��Ӱ���ݼ�
		String movieClassify = request.getParameter("movieClassify1");  //��Ӱ����
		String movieClassify2 = request.getParameter("movieClassify2");
		String movieClassify3 = request.getParameter("movieClassify3");
		List<String> movieClassifyList = new ArrayList<String>();
		movieClassifyList.add(movieClassify);
		movieClassifyList.add(movieClassify2);
		movieClassifyList.add(movieClassify3);
		
		String moviePrice = request.getParameter("moviePrice");  //��Ӱ����
		Double l = Double.valueOf(moviePrice);
		form.setMoviePrice(BigDecimal.valueOf(l));
//		String movieDescript = request.getParameter("movieDescript"); //��Ӱ����
//		String movieIntegralNum = request.getParameter("movieIntegralNum");  //��Ӱ������
		
		System.out.println(form);
		try {
			ms.movieRegx(form,moviePro,movieClassify);//��ͼƬ����ϢУ��
			
			Part part1 = request.getPart("image1");
			Part part2 = request.getPart("image2");
			Part part3 = request.getPart("image3");
			List<Part> partList = new ArrayList<Part>();
			partList.add(part1);
			partList.add(part2);
			partList.add(part3);
			
			ms.movieRegx(partList);  //ͼƬ��ϢУ��
			
			List<String> sqlPaths = new ArrayList<String>();
			//�ϴ�
			for(Part p : partList){
				String sqlPath = CommonsUtils.uploadFile(request,"/merHeadCreateImage", p);
				sqlPaths.add(sqlPath);
			}
			
			Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");
			form.setMerchant(loginedMerchant);  //��Ӧ�̻�
			form.setMovieGradeNum(0d);  //������
			form.setMovieStatus("1");  //�ϼ�
			form.setMovieCreateTime(new Timestamp(new Date().getTime()));  //�ϼ�ʱ��
			
			ms.addMovie(form,sqlPaths,moviePro,movieClassifyList,movieCount,movieTime);  //�ϼܵ�Ӱ
			
			request.setAttribute("msg", "�ϼܳɹ�");
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
//		//1.�õ���������
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		
//		//2.�õ�������
//		ServletFileUpload suf = new ServletFileUpload(factory);
//		
//		//3.����
//		try {
//			List<FileItem> fileItem = suf.parseRequest(request);
//			for(FileItem fi : fileItem){
//				if(fi.isFormField()){ //��ͨ
//					System.out.println(fi.getFieldName());
//					String infoUTF8 = CommonsUtils.getString_ISO8859_To_UTF8(fi.getString());
//					System.out.println(infoUTF8);
////					switch(fi.getFieldName()){
////					
////					}
//				}else{  //�ļ�
//					System.out.println("�ļ���"+fi.getFieldName());
////					String infoUTF8 = CommonsUtils.getString_ISO8859_To_UTF8(fi.getString());
//					System.out.println("�ļ���"+fi.getString());
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
