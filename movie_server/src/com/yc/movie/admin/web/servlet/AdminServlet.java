package com.yc.movie.admin.web.servlet;

import com.yc.movie.bean.*;
import com.yc.movie.exception.AdminException;
import com.yc.movie.admin.service.AdminService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.CommonsUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	 * 开启聊天服务
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void startChatServer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
	}
	
	/**
	 * 获取订单集合
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getIndentList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			List<Indent> indentList = as.findAllIndent();//查找所有的订单
			request.setAttribute("indentList", indentList);
		} catch (AdminException e) {
			request.setAttribute("msg", e.getMessage());
		}  
		return "f:/index.jsp";
	}
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String exit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		session.removeAttribute("loginedAdmin");  //将session中的loginedAdmin删除
		return "r:/adminLogin.jsp";  //重定向到登录界面
	}
	
	/**
	 * 管理员登录前Email校验  失焦ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loginAdminEmailRegx(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String adminEmail = request.getParameter("adminEmail").trim();  //得到输入的Email
		try {
			as.loginAdminEmailRegx(adminEmail);
			response.getWriter().append("");
		} catch (AdminException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	
	/**
	 * 注册  ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);  //将表单数据封装成javabean 对象
		String pwd2 = request.getParameter("adminPwd2");	//得到确认密码
		try {
			//注册
			as.register(form,pwd2);
			response.getWriter().append("注册成功,账号："+form.getAdminEmail());  
		} catch (AdminException e) {
			//如果注册失败
			response.getWriter().append(e.getMessage());
		}
	}
	/**
	 * 注册页面的失焦事件   ajax
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void registerBlur(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);  //将表单数据封装成javabean 对象
		String pwd2 = request.getParameter("adminPwd2");	//得到确认密码
		String status = request.getParameter("status");	//获取到是哪个注册属性
		String chose = "";	//这个属性是注册表单中的数据Id
		switch(status){
		case "1":chose="adminReEmail";break;
		case "2":chose="adminRePwd";break;
		case "3":chose="adminRePwd2";break;
		case "4":chose="adminRegisterCode";break;
		}
		String jsonMsg = null;
		try {
			as.registerBlur(form,status,pwd2);  //调用AdminService的校验格式方法
			//如果没有异常 
			jsonMsg = "";  
			
		} catch (AdminException e) {
			jsonMsg = "{\"msg\":\""+e.getMessage()+"\",\"chose\":\""+chose+"\"}";
		}
		response.getWriter().append(jsonMsg); // {"msg":"","chose":"chose"}
	}
	
	/**
	 * 修改密码 ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void resetPwd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);  //将表单数据封装成javabean 对象
		String pwd2 = request.getParameter("adminPwd2");	//得到确认密码
		try {
			//修改密码
			as.resetPwd(form,pwd2);
			response.getWriter().append("修改成功");  
		} catch (AdminException e) {
			//如果修改失败
			response.getWriter().append(e.getMessage());
		}
	}
	
	/**
	 * 修改密码校验  ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void resetPwdBlur(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);  //将表单数据封装成javabean 对象
		String pwd2 = request.getParameter("adminPwd2");	//得到确认密码
		String status = request.getParameter("status");	//获取到是哪个注册属性
		String chose = "";	//这个属性是注册表单中的数据Id
		switch(status){
		case "1":chose="adminFoEmail";break;
		case "2":chose="adminFoPwd";break;
		case "3":chose="adminFoPwd2";break;
		case "4":chose="adminFoRegisterCode";break;
		}
		String jsonMsg = null;
		try {
			as.resetPwdBlur(form,status,pwd2);  //调用AdminService的校验格式方法
			//如果没有异常 
			jsonMsg = "";  
			
		} catch (AdminException e) {
			jsonMsg = "{\"msg\":\""+e.getMessage()+"\",\"chose\":\""+chose+"\"}";
		}
		response.getWriter().append(jsonMsg); // {"msg":"","chose":"chose"}
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = CommonsUtils.toBean(request, Admins.class);	//将表单数据封装成javabean对象
		String ch = request.getParameter("rememberAdminEmail");  //获取是否记住账号  on / null
		if(ch == null){
			request.setAttribute("isRemember", "false");
		}
		
		form.setAdminPwd(CommonsUtils.parseMD5(form.getAdminPwd()));	//加密
	
		Admins loginedAdmin = form;
		try{
			loginedAdmin = as.login(form);  //调用AdminService的login()方法
			
			if(ch == null){
				//移除cookie
				CommonsUtils.removeCookie(request, response, "rememberAdmin");
			}
			//没有异常  登录成功
			if(ch != null && ch.equals("on")){
				Cookie cookie = new Cookie("rememberAdmin", loginedAdmin.getAdminEmail());
				cookie.setMaxAge(60*60*24*7);	//七天
				response.addCookie(cookie);
			}
			AdminLoginRecord alr = getALR(request,loginedAdmin,"成功");  //获取到AdminLoginRecord登录记录对象
			as.addALR(alr);	//将登录记录对象添加到数据库
			session.setAttribute("loginedAdmin", loginedAdmin);  //将登陆的管理员对象存入session域中
			session.removeAttribute("loginErrorNum");  //将登录错误次数属性从session域中移除
			return "r:/index.jsp";  //重定向到index.jsp
		}catch(AdminException e){
			//有异常  登录失败
			AdminLoginRecord alr = getALR(request,form,"失败");  //获取到AdminLoginRecord登录记录对象
			try {as.addALR(alr);//将登录记录对象添加到数据库
			} catch (AdminException e1) {throw new RuntimeException();}	
			
			request.setAttribute("loginMsg", e.getMessage());  //将登录错误信息保存在request域中
			return "f:/adminLogin.jsp";  //转发到登录页面
		}
		
		
	}
	/**
	 * 获取AdminLoginRecord对象
	 */
	 private AdminLoginRecord getALR(HttpServletRequest request, Admins form,String status) {
		 //创建AdminLoginRecord对象
		 AdminLoginRecord alr = new AdminLoginRecord();
		 
		 //设置属性值
		 alr.setAlrLoginIp(request.getRemoteAddr());  //设置登录IP
		 alr.setAlrLoginTime(new Timestamp(new Date().getTime()));  //设置登录时间
		 alr.setAlrStatus(status);   //设置登录状态
		 alr.setAlrAdmin(form);    //设置登录管理员对象
		return alr;
	}

}
