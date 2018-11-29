package com.yc.movie.web.servlet;

import com.yc.exception.UserException;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.movie.service.UserService;
import com.yc.utils.BaseServlet;
import com.yc.utils.CommonsUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.s")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	System.out.println(12312);
    }

    /**
     * 忘记密码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void forgetPwd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	Users form = CommonsUtils.toBean(request, Users.class); //获取表单数据   userAccount  userPwd
    	String code = request.getParameter("code");  //手机号或者邮箱
    	String userPwd2 = request.getParameter("userPwd2");  //确认密码
    	String verify = request.getParameter("verify");	//验证码
    	
    	try {
			us.alterPwd(form,code,userPwd2,verify);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	Users form = CommonsUtils.toBean(request, Users.class);  //获取表单数据
    	try {
    		UserLoginRecord ulr = getULR(request); //得到登录日志对象
			Users loginedUser = us.login(form,ulr);  //登录
			session.setAttribute("loginedUser", loginedUser);  //把登录成功的对象存在session域中
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());  //登录失败把信息响应给页面
		}
    }

    /**
     * 得到登录日志对象
     * @param request
     * @param loginedUser
     * @return
     */
	private UserLoginRecord getULR(HttpServletRequest request) {
		UserLoginRecord ulr = new UserLoginRecord();
		ulr.setUlrLoginIp(request.getRemoteAddr()); //设置登录IP
		ulr.setUlrLoginTime(new Timestamp(new Date().getTime()));  //设置登陆时间
		return ulr;
	}
}
