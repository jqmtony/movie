package com.yc.movie.admin.web.servlet;

import com.yc.movie.bean.*;
import com.yc.movie.exception.AdminException;
import com.yc.movie.admin.service.AdminService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.CommonsUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin.s")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AdminService as = new AdminService();
	
	/**
	 * �����������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void startChatServer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
	}
	
	/**
	 * ��ȡ��������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getIndentList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			List<Indent> indentList = as.findAllIndent();//�������еĶ���
			request.setAttribute("indentList", indentList);
		} catch (AdminException e) {
			request.setAttribute("msg", e.getMessage());
		}  
		return "f:/index.jsp";
	}
	/**
	 * �˳���¼
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String exit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		session.removeAttribute("loginedAdmin");  //��session�е�loginedAdminɾ��
		return "r:/adminLogin.jsp";  //�ض��򵽵�¼����
	}
	
	/**
	 * ����Ա��¼ǰEmailУ��  ʧ��ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loginAdminEmailRegx(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String adminEmail = request.getParameter("adminEmail").trim();  //�õ������Email
		try {
			as.loginAdminEmailRegx(adminEmail);
			response.getWriter().append("");
		} catch (AdminException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	
	/**
	 * ע��  ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);  //�������ݷ�װ��javabean ����
		String pwd2 = request.getParameter("adminPwd2");	//�õ�ȷ������
		try {
			//ע��
			as.register(form,pwd2);
			response.getWriter().append("ע��ɹ�,�˺ţ�"+form.getAdminEmail());  
		} catch (AdminException e) {
			//���ע��ʧ��
			response.getWriter().append(e.getMessage());
		}
	}
	/**
	 * ע��ҳ���ʧ���¼�   ajax
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void registerBlur(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);  //�������ݷ�װ��javabean ����
		String pwd2 = request.getParameter("adminPwd2");	//�õ�ȷ������
		String status = request.getParameter("status");	//��ȡ�����ĸ�ע������
		String chose = "";	//���������ע����е�����Id
		switch(status){
		case "1":chose="adminReEmail";break;
		case "2":chose="adminRePwd";break;
		case "3":chose="adminRePwd2";break;
		case "4":chose="adminRegisterCode";break;
		}
		String jsonMsg = null;
		try {
			as.registerBlur(form,status,pwd2);  //����AdminService��У���ʽ����
			//���û���쳣 
			jsonMsg = "";  
			
		} catch (AdminException e) {
			jsonMsg = "{\"msg\":\""+e.getMessage()+"\",\"chose\":\""+chose+"\"}";
		}
		response.getWriter().append(jsonMsg); // {"msg":"","chose":"chose"}
	}
	
	/**
	 * �޸����� ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void resetPwd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);  //�������ݷ�װ��javabean ����
		String pwd2 = request.getParameter("adminPwd2");	//�õ�ȷ������
		try {
			//�޸�����
			as.resetPwd(form,pwd2);
			response.getWriter().append("�޸ĳɹ�");  
		} catch (AdminException e) {
			//����޸�ʧ��
			response.getWriter().append(e.getMessage());
		}
	}
	
	/**
	 * �޸�����У��  ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void resetPwdBlur(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);  //�������ݷ�װ��javabean ����
		String pwd2 = request.getParameter("adminPwd2");	//�õ�ȷ������
		String status = request.getParameter("status");	//��ȡ�����ĸ�ע������
		String chose = "";	//���������ע����е�����Id
		switch(status){
		case "1":chose="adminFoEmail";break;
		case "2":chose="adminFoPwd";break;
		case "3":chose="adminFoPwd2";break;
		case "4":chose="adminFoRegisterCode";break;
		}
		String jsonMsg = null;
		try {
			as.resetPwdBlur(form,status,pwd2);  //����AdminService��У���ʽ����
			//���û���쳣 
			jsonMsg = "";  
			
		} catch (AdminException e) {
			jsonMsg = "{\"msg\":\""+e.getMessage()+"\",\"chose\":\""+chose+"\"}";
		}
		response.getWriter().append(jsonMsg); // {"msg":"","chose":"chose"}
	}
	
	/**
	 * ��¼
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);	//�������ݷ�װ��javabean����
		String ch = request.getParameter("rememberAdminEmail");  //��ȡ�Ƿ��ס�˺�  on / null
		if(ch == null){
			request.setAttribute("isRemember", "false");
		}
		
		form.setAdminPwd(CommonsUtils.parseMD5(form.getAdminPwd()));	//����
	
		Admins loginedAdmin = form;
		try{
			loginedAdmin = as.login(form);  //����AdminService��login()����
			
			if(ch == null){
				//�Ƴ�cookie
				CommonsUtils.removeCookie(request, response, "rememberAdmin");
			}
			//û���쳣  ��¼�ɹ�
			if(ch != null && ch.equals("on")){
				Cookie cookie = new Cookie("rememberAdmin", loginedAdmin.getAdminEmail());
				cookie.setMaxAge(60*60*24*7);	//����
				response.addCookie(cookie);
			}
			AdminLoginRecord alr = getALR(request,loginedAdmin,"�ɹ�");  //��ȡ��AdminLoginRecord��¼��¼����
			as.addALR(alr);	//����¼��¼������ӵ����ݿ�
			session.setAttribute("loginedAdmin", loginedAdmin);  //����½�Ĺ���Ա�������session����
			session.removeAttribute("loginErrorNum");  //����¼����������Դ�session�����Ƴ�
			return "r:/index.jsp";  //�ض���index.jsp
		}catch(AdminException e){
			//���쳣  ��¼ʧ��
			AdminLoginRecord alr = getALR(request,form,"ʧ��");  //��ȡ��AdminLoginRecord��¼��¼����
			try {as.addALR(alr);//����¼��¼������ӵ����ݿ�
			} catch (AdminException e1) {throw new RuntimeException();}	
			
			request.setAttribute("loginMsg", e.getMessage());  //����¼������Ϣ������request����
			return "f:/adminLogin.jsp";  //ת������¼ҳ��
		}
		
		
	}
	/**
	 * ��ȡAdminLoginRecord����
	 */
	 private AdminLoginRecord getALR(HttpServletRequest request, Admins form,String status) {
		 //����AdminLoginRecord����
		 AdminLoginRecord alr = new AdminLoginRecord();
		 
		 //��������ֵ
		 alr.setAlrLoginIp(request.getRemoteAddr());  //���õ�¼IP
		 alr.setAlrLoginTime(new Timestamp(new Date().getTime()));  //���õ�¼ʱ��
		 alr.setAlrStatus(status);   //���õ�¼״̬
		 alr.setAlrAdmin(form);    //���õ�¼����Ա����
		return alr;
	}

}
