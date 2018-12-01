package com.yc.movie.merchant.web.servlet;

import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Verification;
import com.yc.movie.exception.MerchantException;
import com.yc.movie.merchant.service.MerchantService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.CommonsUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MerchantServlet
 */
@WebServlet("/mer.s")
public class MerchantServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private MerchantService ms = new MerchantService();
	
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
