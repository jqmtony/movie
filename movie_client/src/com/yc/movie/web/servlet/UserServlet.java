package com.yc.movie.web.servlet;

import com.yc.exception.UserException;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.movie.bean.Verify;
import com.yc.movie.service.UserService;
import com.yc.utils.BaseServlet;
import com.yc.utils.CommonsUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    public void exit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	session.removeAttribute("loginedUser");
    }
    /**
     * 修改信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void alterInfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	//需求   1.姓名  2.手机号 3.邮箱  4.地址 5.出生日期  7.头像
    }

    /**
     * 发送验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void sendVerify(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	String code = request.getParameter("code");  //获取手机号/验证码
    	String userAccount = request.getParameter("userAccount"); //获取用户名
    	Integer num = Integer.parseInt(request.getParameter("num"));
    	try {
			String createVerify = us.sentVerify(code,userAccount,num);
			Cookie cookie = new Cookie("verifyCode", createVerify);  //将生成的验证码存在cookie中
			cookie.setMaxAge(60*10);  //设置有效期为10分钟
			response.addCookie(cookie); //将cookie添加
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());  //将错误信息响应给客户端
		}
    	
    }
    
    
    /**
     * 更新用户   忘记密码/用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	Users form = CommonsUtils.toBean(request, Users.class); //获取表单数据   userAccount  userPwd
    	String code = request.getParameter("code");  //手机号或者邮箱
    	String userPwd2 = request.getParameter("userPwd2");  //确认密码
    	String inputVerify = request.getParameter("verify");	//用户输入的验证码
    	Cookie cookie = CommonsUtils.getCookie(request, "verifyCode");  //获取验证码对应的cookie
    	Integer num = Integer.parseInt(request.getParameter("num"));  //是注册还是修改密码
    	if(cookie == null){
    		response.getWriter().append("你还没有发送验证码");
    		return;
    	}
    	String createVerify = cookie.getValue(); //当前发送的验证码
    	Verify verify = new Verify();	//创建验证码对象
    	verify.setInputVerify(inputVerify);
    	verify.setCreateVerify(createVerify);
    	
    	try {
    		if(num == 1){
    			us.updateUser(form,code,userPwd2,verify,UserService.UPDATE_TYPE_PWD);  //修改密码
    		}else if(num == 2){
    			us.updateUser(form,code,userPwd2,verify,UserService.UPDATE_TYPE_REGISTER);  //用户注册
    		}
			CommonsUtils.removeCookie(request, response, "verifyCode");  //删除cookie中的验证码
			response.getWriter().append("");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
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
