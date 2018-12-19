package com.yc.system.web.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yc.movie.utils.CommonsUtils;

/**
 * Servlet Filter implementation class filter
 */
@WebFilter(urlPatterns={"*.jsp","*.s"})
public class SystemFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;	//��ȡ��HttpServletRequest����
		HttpSession session = httpRequest.getSession();		//�õ�session����
		
		Properties p = new Properties();
		p.load(this.getClass().getClassLoader().getResourceAsStream(CommonsUtils.IP_ADDR_AND_PRO_NAME));
		String addr = p.getProperty("address");
		String proName = p.getProperty("projectName");
		request.getServletContext().setAttribute("addrIp", addr+proName);
		
		String path = httpRequest.getServletPath();	//��ȡ������·��   /login.jsp
		if(!path.endsWith("merLogin.jsp") && !path.endsWith("mer.s") && !path.endsWith("mail/register.jsp")){
			Object loginedMerchant = session.getAttribute("loginedMerchant");
			if(loginedMerchant == null){
				httpRequest.setAttribute("msg", "�㻹û�е�¼�����ȵ�¼��");
				httpRequest.getRequestDispatcher("/merLogin.jsp").forward(httpRequest, response);
			}
		}
		
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
