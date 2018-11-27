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
	 * 修改语言
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
				locale = Locale.US;	//得到主机对象
			}else if(name.equals("中文")){
				locale = Locale.CHINA;
			}
			ResourceBundle rb = ResourceBundle.getBundle("res",locale);	//加载配置文件
			session.setAttribute("lg", rb);	//将配置文件对象存在session域中
			session.setAttribute("lgType", rb.getLocale().getLanguage());
		}
	}

}
