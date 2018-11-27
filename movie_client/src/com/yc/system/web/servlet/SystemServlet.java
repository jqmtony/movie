package com.yc.system.web.servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.utils.BaseServlet;

/**
 * Servlet implementation class SystemServlet
 */
@WebServlet("/sys.s")
public class SystemServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * �޸�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void alterLanguage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String name = request.getParameter("name");
		if(name!=null){
			Locale locale = null;
			if(name.equals("English")){
				locale = Locale.US;	//�õ���������
			}else if(name.equals("����")){
				locale = Locale.CHINA;
			}
			ResourceBundle rb = ResourceBundle.getBundle("res",locale);	//���������ļ�
			session.setAttribute("lg", rb);	//�������ļ��������session����
			session.setAttribute("lgType", rb.getLocale().getLanguage());
		}
	}

}
