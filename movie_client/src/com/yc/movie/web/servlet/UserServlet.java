package com.yc.movie.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yc.exception.UserException;
import com.yc.movie.bean.Indent;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.movie.bean.Verify;
import com.yc.movie.service.UserService;
import com.yc.utils.BaseServlet;
import com.yc.utils.CommonsUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.s")
@MultipartConfig
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private static Long userFlag = 1l;
	private static Long merFlag = 0l;
	
	/**
	 * ˢ�½�����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void revicedContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users loginedUser = (Users)session.getAttribute("loginedUser");  //ȡ����ǰ��¼���û�
		String str = "merToUser"+loginedUser.getUserId();  // merToUser1
		String fileName = request.getServletContext().getRealPath("/onlineChat/sendMerToUser.properties").replace("movie_client", "movie_server");
		File file = new File(fileName);
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		
		String text = p.getProperty(str);
//		System.out.println("�û��յ�����Ϣ��"+text);
		if(text == null || text.isEmpty()){
			response.getWriter().append("no");
			return;
		}
		String[] arr = text.split(";");
//		for(String s:arr)
//			System.out.println(s);
		Long flag = Long.parseLong(arr[0]);
		if(flag.equals(userFlag)){
			response.getWriter().append("no");
		}else{
			String content = arr[1];
			System.out.println("�û��յ�����Ϣ��"+content);
			userFlag = flag;
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
		id = id.substring(8);  //��ȡ�̻�ID
		Users loginedUser = (Users)session.getAttribute("loginedUser");  //ȡ����ǰ��¼���û�

		String fileName = request.getServletContext().getRealPath("/onlineChat/sendUserToMer.properties").replace("movie_server", "movie_client");
		File file = new File(fileName);
		Properties p = new Properties();
		FileOutputStream fos = new FileOutputStream(file);
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		
		p.put("userToMer"+id, merFlag+";"+loginedUser.getUserId()+"{[code]}"+content+"{[code]}"+loginedUser.getImgList().get(0).getImgPath());
//		System.out.println("�û����̻�������Ϣ��"+merFlag+";"+loginedUser.getUserId()+"{[code]}"+content+"{[code]}"+loginedUser.getImgList().get(0).getImgPath());
		merFlag++;
		p.store(fos, "");
		
		fos.close();
	}
	///////////////// wt ����////////////////////////////
	/**
	 * ����
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ��������
		String email = request.getParameter("email");

		// У������ĸ�ʽ
		try {
			us.regxSubEmail(email);

			// ���ִ�е�������˵��email�����ʽ��ȷ Ȼ���������ӵ����ı�����
			us.addSubEmail(email);

			// ���ִ�е��������Ǿ���ӳɹ�
			response.getWriter().append("yes");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	/////////////////////////////////////////////

	/**
	 * ���ݵ�ǰ��¼�û��������ж���
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findIndentListByLoginedUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users loginedUser = (Users) session.getAttribute("loginedUser");
		try {
			List<Indent> indentList = us.findIndentListByUser(loginedUser); // �������ж���
			request.setAttribute("indentList", indentList);

		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/userMovieIndent.jsp";

	}

	/**
	 * �޸���Ϣ
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���� 1.���� 2.�ֻ��� 3.���� 4.��ַ 5.�������� 7.ͷ��
		Users loginedUser = (Users) session.getAttribute("loginedUser");
		String userName = request.getParameter("userName"); // ����
		String userTel = request.getParameter("userTel"); // �ֻ���
		String userEmail = request.getParameter("userEmail"); // ����

		// ��ַ
		String country = request.getParameter("country"); // ����
		String province = request.getParameter("province"); // ʡ
		String city = request.getParameter("city"); // ��
		String district = request.getParameter("district"); // ��
		String fullAddress = request.getParameter("fullAddress"); // ��ϸ��ַ
		String userAddr = country + " " + province + " " + city + " " + district + " " + fullAddress; // ��ַ

		// ��������
		String y = request.getParameter("y"); // ��
		String m = request.getParameter("m"); // ��
		String d = request.getParameter("d"); // ��
		Date da = CommonsUtils.dateFormat(y + "-" + m + "-" + d, "yyyy-MM-dd");
		java.sql.Date userBirthday = new java.sql.Date(da.getTime()); // ��������

		// ͷ��
		Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		// System.out.println("filename:"+filename);
		// System.out.println("������"+userName);
		// System.out.println("�ֻ��ţ�"+userTel);
		// System.out.println("���䣺"+userEmail);
		// System.out.println("��ַ��"+userAddr);
		// System.out.println("�������ڣ�"+userBirthday);
		// return null;
		Users form = new Users();
		form.setUserName(userName);
		form.setUserTel(userTel);
		form.setUserEmail(userEmail);
		form.setUserAddr(userAddr);
		form.setUserBirthday(userBirthday);
		form.setUserId(loginedUser.getUserId());
		form.setUserAge((new Date().getTime() - userBirthday.getTime()) / 1000 / 60 / 60 / 24 / 365); // ��������

		// �жϵ�ַ�Ƿ�Ϊnull
		if (userAddr.startsWith("�й� ʡ"))
			form.setUserAddr(loginedUser.getUserAddr());

		if (userEmail == null)
			form.setUserEmail(loginedUser.getUserEmail());

		if (userTel == null)
			form.setUserTel(loginedUser.getUserTel());

		String sqlPath = null;
		// �ϴ�ͷ��
		if (filename != null && !filename.trim().isEmpty()) {
			sqlPath = CommonsUtils.uploadImage(request, "/merHeadCreateImage", part, 100, 100);
		}

		try {
			us.alterInfo(form, sqlPath);

			loginedUser = us.findUserByUserId(loginedUser.getUserId());
			session.setAttribute("loginedUser", loginedUser);
			return "r:/userAlterInfo.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/userAlterInfo.jsp";
		}

	}

	/**
	 * ��֤����
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regxEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("inputEmail"); // ��ȡ�����������
		Users loginedUser = (Users) session.getAttribute("loginedUser"); // �õ���ǰ��¼���û�
		try {
			us.regxEmail(email, loginedUser);
			response.getWriter().append("yes");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
		}
	}

	/**
	 * ��֤�ֻ���
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regxTel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tel = request.getParameter("inputTel"); // ��ȡ��������ֻ���
		Users loginedUser = (Users) session.getAttribute("loginedUser"); // �õ���ǰ��¼���û�
		try {
			us.regxTel(tel, loginedUser);
			response.getWriter().append("yes");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
		}
	}

	/**
	 * ͨ������»�ȡ��
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateDay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer y = Integer.parseInt(request.getParameter("y"));
		Integer m = Integer.parseInt(request.getParameter("m"));
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, y);
		c.set(Calendar.MONTH, m - 1);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		String[] dayArr = getModel(1, days);
		response.getWriter().append(JSON.toJSONString(dayArr));
	}

	/**
	 * ���ݴ������������
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private String[] getModel(int start, int end) {
		String[] m = new String[end - start + 1];
		for (int i = 0; i < m.length; i++) {
			m[i] = String.valueOf(i + start);
		}
		return m;
	}

	/**
	 * �����л�ȡ��
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getDistrictByCity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cId = request.getParameter("cId");
		Map<String, String> dis = CommonsUtils.xmlDistricts(cId, this.getClass());
		String jsonStr = JSON.toJSONString(dis);
		response.getWriter().append(jsonStr);
	}

	/**
	 * ����ʡID��ȡ�к���
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCityAndDistrictByProvince(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("pId"); // ��ȡʡid
		Map<String, String> cityMap = CommonsUtils.xmlCities(pId, this.getClass());
		String s = null;
		for (Iterator<Entry<String, String>> it = cityMap.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> me = it.next();
			s = me.getKey();
			break;
		}
		Map<String, String> dis = CommonsUtils.xmlDistricts(s, this.getClass());
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(cityMap);
		list.add(dis);
		String jsonStr = JSON.toJSONString(list);
		response.getWriter().append(jsonStr);
	}

	/**
	 * ��������ʡ
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getMapByProvinces(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = CommonsUtils.xmlProvince(this.getClass());
		session.setAttribute("provincesMap", map);
		return "r:/userAlterInfo.jsp";
	}

	/**
	 * ��ת��¼ҳ��
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String goLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String refererPath = request.getHeader("referer");
		session.setAttribute("refererPath", refererPath);
		System.out.println("refererPath:" + refererPath);
		Users user = (Users) session.getAttribute("loginedUser");
		if (user == null) {
			return "r:/userLogin.jsp";
		} else {
			request.getRequestDispatcher(refererPath).forward(request, response);
			return null;
		}
	}

	/**
	 * ��ǰ�Ƿ��¼ ajax
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String refererPath = request.getHeader("referer");
		session.setAttribute("refererPath", refererPath);
		Users user = (Users) session.getAttribute("loginedUser");
		if (user == null) {
			response.getWriter().append("notLogin");
			return;
		} else {
			response.getWriter().append("yes");
		}
	}

	/**
	 * ��¼֮ǰ�������ĸ�ҳ�淢�͵ĵ�¼���� ������session��
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginSetReferer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String refererPath = request.getHeader("referer");
		session.setAttribute("refererPath", refererPath);
		return "r:/userLogin.jsp";
	}

	/**
	 * �˳���¼
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.removeAttribute("refererPath");
		session.removeAttribute("loginedUser");
	}

	/**
	 * ������֤��
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendVerify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code"); // ��ȡ�ֻ���/��֤��
		String userAccount = request.getParameter("userAccount"); // ��ȡ�û���
		Integer num = Integer.parseInt(request.getParameter("num"));
		try {
			String createVerify = us.sentVerify(code, userAccount, num);
			Cookie cookie = new Cookie("verifyCode", createVerify); // �����ɵ���֤�����cookie��
			cookie.setMaxAge(60 * 10); // ������Ч��Ϊ10����
			response.addCookie(cookie); // ��cookie���
		} catch (UserException e) {
			response.getWriter().append(e.getMessage()); // ��������Ϣ��Ӧ���ͻ���
		}

	}

	/**
	 * �����û� ��������/�û�ע��
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users form = CommonsUtils.toBean(request, Users.class); // ��ȡ������
																// userAccount
																// userPwd
		String code = request.getParameter("code"); // �ֻ��Ż�������
		String userPwd2 = request.getParameter("userPwd2"); // ȷ������
		String inputVerify = request.getParameter("verify"); // �û��������֤��
		Cookie cookie = CommonsUtils.getCookie(request, "verifyCode"); // ��ȡ��֤���Ӧ��cookie
		Integer num = Integer.parseInt(request.getParameter("num")); // ��ע�ỹ���޸�����
		if (cookie == null) {
			response.getWriter().append("�㻹û�з�����֤��");
			return;
		}
		String createVerify = cookie.getValue(); // ��ǰ���͵���֤��
		Verify verify = new Verify(); // ������֤�����
		verify.setInputVerify(inputVerify);
		verify.setCreateVerify(createVerify);

		try {
			if (num == 1) {
				us.updateUser(form, code, userPwd2, verify, UserService.UPDATE_TYPE_PWD); // �޸�����
			} else if (num == 2) {
				us.updateUser(form, code, userPwd2, verify, UserService.UPDATE_TYPE_REGISTER); // �û�ע��
			}
			CommonsUtils.removeCookie(request, response, "verifyCode"); // ɾ��cookie�е���֤��
			response.getWriter().append("");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
		}
	}

	/**
	 * ��¼
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users form = CommonsUtils.toBean(request, Users.class); // ��ȡ������
		form.setUserIpAddr(request.getRemoteAddr());  //���õ�ǰ��¼IP
		try {
			UserLoginRecord ulr = getULR(request); // �õ���¼��־����
			Users loginedUser = us.login(form, ulr); // ��¼
			session.setAttribute("loginedUser", loginedUser); // �ѵ�¼�ɹ��Ķ������session����

		} catch (UserException e) {
			response.getWriter().append(e.getMessage()); // ��¼ʧ�ܰ���Ϣ��Ӧ��ҳ��
		}
	}

	/**
	 * �õ���¼��־����
	 * 
	 * @param request
	 * @param loginedUser
	 * @return
	 */
	private UserLoginRecord getULR(HttpServletRequest request) {
		UserLoginRecord ulr = new UserLoginRecord();
		ulr.setUlrLoginIp(request.getRemoteAddr()); // ���õ�¼IP
		ulr.setUlrLoginTime(new Timestamp(new Date().getTime())); // ���õ�½ʱ��
		return ulr;
	}
}
