package com.yc.movie.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(urlPatterns={"*.s","*.jsp"})
public class UserFilter implements Filter {

   
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		// place your code here
//		String path = httpRequest.getServletPath();	//获取到访问路径   /login.jsp
//		if(!path.endsWith("login.jsp") && !path.endsWith("user.s")){
//			if(session.getAttribute("loginedUser") == null){
//				//未登录
//				request.setAttribute("msg", "请先登录系统！");
//				request.getRequestDispatcher("/userLogin.jsp").forward(request, response);
//				return;
//			}
//		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
