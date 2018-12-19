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
	 * ���ĵ�Ӱ��״̬
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void setMovieStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");  //��ȡ��Ҫ���õ�״̬
		Long movieId = Long.parseLong(request.getParameter("movieId"));  //��ȡ��Ҫ���õĵ�ӰID
		try {
			ms.setMovieStatus(type,movieId);
			response.getWriter().append("yes");
		} catch (MerchantException e) {
			e.printStackTrace();
			response.getWriter().append(e.getMessage());
		}  
	}
	/**
	 * ���ҵ�ǰ��¼�̻��еĵ�Ӱ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findMovieByMer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Merchant loginedMer = (Merchant)session.getAttribute("loginedMerchant"); //�õ���ǰ��¼���̻�
		Integer pc = CommonsUtils.getPageListPc(request);  //�õ�pc  ��ǰҳ
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
	 * ˢ�½�����Ϣ
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
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");  //ȡ����ǰ��¼���û�
		String str = "userToMer"+loginedMerchant.getMerId();  // userToMer2
		String fileName = request.getServletContext().getRealPath("/onlineChat/sendUserToMer.properties").replace("movie_server", "movie_client");
		File file = new File(fileName);
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		
		String text = p.getProperty(str);
//		System.out.println("�̻��յ�����Ϣ��"+text);
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
			System.out.println("�̻��յ�����Ϣ��"+content);
			merFlag = flag;
			response.getWriter().append(content);
		}
		
	}
	
	/**
	 * ��������Ϣ���͸�������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendContentToChatServer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");  //��ȡ��Ϣ����
		String id = request.getParameter("id");  
		id = id.substring(8);  //��ȡ�û�ID
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");  //ȡ����ǰ��¼���̻�
		
		String fileName = request.getServletContext().getRealPath("/onlineChat/sendMerToUser.properties").replace("movie_client", "movie_server");
		File file = new File(fileName);
		Properties p = new Properties();
		FileOutputStream fos = new FileOutputStream(file);
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		
		p.put("merToUser"+id, userFlag+";"+loginedMerchant.getMerId()+"{[code]}"+content+"{[code]}"+loginedMerchant.getImgList().get(0).getImgPath());
//		System.out.println("�̻����û�������Ϣ��"+userFlag+";"+loginedMerchant.getMerId()+"{[code]}"+content);
		userFlag++;
		p.store(fos, "");
		
		fos.close();
	}
	
	
	/**
	 * ���������û�
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
		
		//����Ӱ���ӡ�����
		String name = form.getMovieName();
		if(!name.startsWith("��")){
			name = "��" + name;
		}
		if(!name.endsWith("��")){
			name = name + "��";
		}
		form.setMovieName(name);
		
		String movieMerId = form.getMovieMerId();  //��ȡ�����ε��̻�ID
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");  //�õ���ǰ��¼�̻�
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
		
		
		try {
			ms.regxMovieInfo(form,m);  //��֤��Ϣ
			
			List<String> sqlPaths = new ArrayList<String>();
			
			//�ϴ�
			String sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", partList.get(0),250,300);
			sqlPaths.add(sqlPath);
			sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", partList.get(1),650,410);
			sqlPaths.add(sqlPath);
			sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", partList.get(2),1680,850);
			sqlPaths.add(sqlPath);
			sqlPath = CommonsUtils.uploadImage(request,"/merHeadCreateImage", partList.get(2),720,338);
			sqlPaths.add(sqlPath);
			sqlPaths.add(CommonsUtils.uploadFile(request, "/merHeadCreateImage", partList.get(3)));
			
			form.setMoviePrevue(sqlPaths.get(4)); //��ӵ�ӰԤ��Ƭ��·��
			ms.addMovie(form, m,sqlPaths,oldMovieMerId,loginedMerchant.getMerId());  //��Ӱ�ϼ�
			
			//�ϼܳɹ�   �����ʼ���������������վ������
			ms.sendEmailToSub(form);
			
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
	 * �ϴ���Ƭ
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
		
		Images img = new Images();  //����ͷ��
		img.setImgMerchantId(loginedMerchant.getMerId());  //�����̻�ID
		img.setImgStatus("ͷ��");
		img.setImgPath(sqlPath);  //����ͼƬ·��
		
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
	 * ʵ����֤
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void realName(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Merchant form = CommonsUtils.toBean(request, Merchant.class);  // 
		
//		System.out.println("�̻���֤��Ϣ��"+form);
		String inVerify = request.getParameter("verify");  //��ȡ���û��������֤��
		String telCode = null;
		Cookie cookie = CommonsUtils.getCookie(request, "telCode");
		if(cookie != null)
			telCode = cookie.getValue();
		Merchant loginedMerchant = (Merchant)session.getAttribute("loginedMerchant");
		
		try {
			form.setMerId(loginedMerchant.getMerId());
			String infoID = ms.realName(form,loginedMerchant,telCode,inVerify);
//			form.setMerId(loginedMerchant.getMerId());
//			request.getServletContext().setAttribute(infoID, form);  //��Ҫ��˵���Ϣ��������
			loginedMerchant = ms.findMerchantById(loginedMerchant.getMerId());
			session.setAttribute("loginedMerchant", loginedMerchant);
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
			form.setMerIpAddr(request.getRemoteAddr());  //���õ�ǰ��¼IP
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
