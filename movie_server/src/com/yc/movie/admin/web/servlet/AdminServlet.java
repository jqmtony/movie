package com.yc.movie.admin.web.servlet;

import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.admin.bean.Verification;
import com.yc.movie.admin.exception.AdminException;
import com.yc.movie.admin.service.AdminService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.FormUtils;
import com.yc.movie.utils.MD5;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
	 * �޸�����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterPwd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = FormUtils.toBean(request, Admins.class);//�������ݷ�װ��javabean����
		String adminPwd2 = request.getParameter("adminPwd2");  //�õ�ȷ������
		try {
			as.alterPwd(form,adminPwd2);  //�޸�����
			request.setAttribute("go", "<br />5 ����Զ���ת ������<a href='login.jsp'>�����ת��¼ҳ��-></a>");
			request.setAttribute("msg", "�޸ĳɹ�����ʹ���������¼��");  //���ɹ���Ϣ��ӵ�request����
		} catch (AdminException e) {
			//�޸�ʧ��
			request.setAttribute("msg", "�޸�ʧ�ܣ�"+e.getMessage());  //��ʧ����Ϣ��ӵ�request����
		}
		return "f:/alterPwd.jsp";  //ת��
	}
	/**
	 * �޸�����֮ǰ��ҳ����ת
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterPwdBefore(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String alterIdStr = request.getParameter("alterId");  //��ȡҪ�޸ĵĹ���ԱId
		if(alterIdStr == null)
			return "r:/server404.jsp";
		Long alterId = Long.parseLong(alterIdStr);	//��Idת����Long����
		try {
			Admins alterAdmin = as.findById(alterId);	//�õ�Ҫ�޸ĵĹ���Ա����
			request.setAttribute("alterAdmin", alterAdmin);
			return "f:/alterPwd.jsp";  //ת����alterPwd.jspҳ��
		} catch (AdminException e) {
			throw new RuntimeException();
		}	
		
	}
	
	/**
	 * �����޸��������ӵ��ʼ�  ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fotPwdSendEmail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String adminEmail = request.getParameter("adminEmail").trim();  //�õ����������
		try {
			Admins admin = as.fotPwdSendEmail(adminEmail);//�����ʼ�
			response.getWriter().append("�޸����������Ѿ����͵��������䣺"+admin.getAdminEmail()+" �У�����ʼ����������޸����룡");
		} catch (AdminException e) {
			response.getWriter().append(e.getMessage());
		}  
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
		Admins form = FormUtils.toBean(request, Admins.class);	//�������ݷ�װ��javabean����
		form.setAdminPwd(MD5.parseMD5(form.getAdminPwd()));	//����
		
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
			AdminLoginRecord alr = getALR(request,loginedAdmin,"�ɹ�");  //��ȡ��AdminLoginRecord��¼��¼����
			as.addALR(alr);	//����¼��¼������ӵ����ݿ�
			session.setAttribute("loginedAdmin", loginedAdmin);  //����½�Ĺ���Ա�������session����
			session.removeAttribute("loginErrorNum");  //����¼����������Դ�session�����Ƴ�
			return "r:/index.jsp";  //�ض���index.jsp
		}catch(AdminException e){
			//���쳣  ��¼ʧ��
			AdminLoginRecord alr = getALR(request,loginedAdmin,"�ɹ�");  //��ȡ��AdminLoginRecord��¼��¼����
			try {as.addALR(alr);//����¼��¼������ӵ����ݿ�
			} catch (AdminException e1) {throw new RuntimeException();}	
			
			request.setAttribute("msg", e.getMessage());  //����¼������Ϣ������request����
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
			
			return "f:/login.jsp";  //ת������¼ҳ��
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
		 alr.setAlrAdmin(loginedAdmin);   //���õ�¼����Ա����
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
