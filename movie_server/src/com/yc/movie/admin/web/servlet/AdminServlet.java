package com.yc.movie.admin.web.servlet;

import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.admin.bean.Verification;
import com.yc.movie.admin.exception.AdminException;
import com.yc.movie.admin.service.AdminService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.CommonsUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
		
		Verification v = null;	//����һ����֤�����
		Object obj = session.getAttribute("verificationObject");	//ȡ��session�е���֤�����
		if(obj != null){	//���session������֤�����
			v = (Verification)obj;	//��obj����ת�ͳ���֤������
			v.setInCode(request.getParameter("inCode"));	//�õ��ͻ����������֤��
		}
		Admins loginedAdmin = form;
		try{
			loginedAdmin = as.login(form,v);  //����AdminService��login()����
			
			//û���쳣  ��¼�ɹ�
			if(ch.equals("on")){
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
			AdminLoginRecord alr = getALR(request,loginedAdmin,"ʧ��");  //��ȡ��AdminLoginRecord��¼��¼����
			try {as.addALR(alr);//����¼��¼������ӵ����ݿ�
			} catch (AdminException e1) {throw new RuntimeException();}	
			
			request.setAttribute("loginMsg", e.getMessage());  //����¼������Ϣ������request����
			int loginErrorNum = 0;
			try{
				loginErrorNum = (int)session.getAttribute("loginErrorNum");  //ǿ������ת��  ���ܻ����쳣
				//û���쳣  ˵��session������loginErrorNum
				session.setAttribute("loginErrorNum", loginErrorNum+1);  //����¼�������+1
			}catch(Exception e2){
				//���쳣  ˵��session����û��loginErrorNum
				session.setAttribute("loginErrorNum", 1);  //����¼���������ʼ��Ϊ
			}
			
			if(loginErrorNum >= 2){  //�����¼�������ڵ���3��
				//����֤�����request����
				request.setAttribute("verificationCode",
						"<img id='codeImg' style=\"margin:0 20px 20px 20px;\" alt=\"���������汾����\" src=\"" + request.getContextPath()
								+ "/user.s?method=createVerification\" onclick='againCode()'/>"
								+ "<input style='margin:0 0 0 20px;' type='text' placeholder='��������֤��' name='codeText' class=''/>");
			}
			
			return "f:/adminLogin.jsp";  //ת������¼ҳ��
		}
		
		
	}
	/**
	 * ��ȡAdminLoginRecord����
	 */
	 private AdminLoginRecord getALR(HttpServletRequest request, Admins loginedAdmin,String status) {
		 //����AdminLoginRecord����
		 AdminLoginRecord alr = new AdminLoginRecord();
		 
		 //��������ֵ
		 alr.setAlrLoginIp(request.getRemoteAddr());  //���õ�¼IP
		 alr.setAlrLoginTime(new Timestamp(new Date().getTime()));  //���õ�¼ʱ��
		 alr.setAlrStatus(status);   //���õ�¼״̬
		 alr.setAlrAdmin(loginedAdmin);    //���õ�¼����Ա����
		return alr;
	}

 	/**
	 * ������֤��
	 */
	public void createVerification(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Verification verification = new Verification(); // ������֤�����
		BufferedImage image = verification.getImage(); // ������֤��ͼƬ
		session.setAttribute("verificationObject", verification);// ����֤����󱣴浽session����
		Verification.output(image, response.getOutputStream());// ��ͼƬ��Ӧ���ͻ���
	}
}
