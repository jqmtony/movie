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
		
		Properties p = new Properties();
		p.load(this.getClass().getClassLoader().getResourceAsStream(CommonsUtils.IP_ADDR_AND_PRO_NAME));
		String addr = p.getProperty("address");
		String proName = p.getProperty("projectName");
		request.getServletContext().setAttribute("addrIp", addr+proName);
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);	//放行
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
