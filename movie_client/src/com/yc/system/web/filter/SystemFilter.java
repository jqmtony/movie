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

import com.yc.utils.CommonsUtils;

/**
 * Servlet Filter implementation class filter
 */
@WebFilter(urlPatterns={"*.jsp"})
public class SystemFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;	//获取到HttpServletRequest对象
		HttpSession session = httpRequest.getSession();		//得到session对象
		Object obj = session.getAttribute("lg");	//得到language属性
		if(obj == null){	//如果session中没有lg
			Locale locale = Locale.getDefault();	//得到主机对象
			ResourceBundle rb = ResourceBundle.getBundle("res",locale);	//加载配置文件
			session.setAttribute("lg", rb);	//将配置文件对象存在session域中
			session.setAttribute("lgType", rb.getLocale().getLanguage());
		}
		
		Properties p = new Properties();
		p.load(this.getClass().getClassLoader().getResourceAsStream(CommonsUtils.IP_ADDR_AND_PRO_NAME));
		String addr = p.getProperty("address");
		String proName = p.getProperty("projectName");
		session.setAttribute("addrIp", addr+proName);
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);	//放行
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
