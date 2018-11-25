package com.yc.movie.admin.web.servlet;

import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.admin.bean.Verification;
import com.yc.movie.admin.exception.AdminException;
import com.yc.movie.admin.service.AdminService;
import com.yc.movie.utils.BaseServlet;
import com.yc.movie.utils.FormUtils;
import com.yc.movie.utils.MD5;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterPwd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Admins form = FormUtils.toBean(request, Admins.class);//将表单数据封装成javabean对象
		String adminPwd2 = request.getParameter("adminPwd2");  //得到确认密码
		try {
			as.alterPwd(form,adminPwd2);  //修改密码
			request.setAttribute("go", "<br />5 秒后自动跳转 。。。<a href='login.jsp'>点击跳转登录页面-></a>");
			request.setAttribute("msg", "修改成功！请使用新密码登录！");  //将成功信息添加到request域中
		} catch (AdminException e) {
			//修改失败
			request.setAttribute("msg", "修改失败！"+e.getMessage());  //将失败信息添加到request域中
		}
		return "f:/alterPwd.jsp";  //转发
	}
	/**
	 * 修改密码之前的页面跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterPwdBefore(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String alterIdStr = request.getParameter("alterId");  //获取要修改的管理员Id
		if(alterIdStr == null)
			return "r:/server404.jsp";
		Long alterId = Long.parseLong(alterIdStr);	//将Id转换成Long类型
		try {
			Admins alterAdmin = as.findById(alterId);	//得到要修改的管理员对象
			request.setAttribute("alterAdmin", alterAdmin);
			return "f:/alterPwd.jsp";  //转发到alterPwd.jsp页面
		} catch (AdminException e) {
			throw new RuntimeException();
		}	
		
	}
	
	/**
	 * 发送修改密码链接的邮件  ajax
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fotPwdSendEmail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String adminEmail = request.getParameter("adminEmail").trim();  //得到输入的邮箱
		try {
			Admins admin = as.fotPwdSendEmail(adminEmail);//发送邮件
			response.getWriter().append("修改密码链接已经发送到您的邮箱："+admin.getAdminEmail()+" 中，请打开邮件根据链接修改密码！");
		} catch (AdminException e) {
			response.getWriter().append(e.getMessage());
		}  
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
		Admins form = FormUtils.toBean(request, Admins.class);	//将表单数据封装成javabean对象
		form.setAdminPwd(MD5.parseMD5(form.getAdminPwd()));	//加密
		
		Verification v = null;	//定义一个验证码变量
		Object obj = session.getAttribute("verificationObject");	//取出session中的验证码对象
		if(obj != null){	//如果session存在验证码对象
			v = (Verification)obj;	//将obj向下转型成验证码类型
			v.setInCode(request.getParameter("inCode"));	//得到客户端输入的验证码
		}
		Admins loginedAdmin = form;
		try{
			loginedAdmin = as.login(form,v);  //调用AdminService的login()方法
			
			//没有异常  登录成功
			AdminLoginRecord alr = getALR(request,loginedAdmin,"成功");  //获取到AdminLoginRecord登录记录对象
			as.addALR(alr);	//将登录记录对象添加到数据库
			session.setAttribute("loginedAdmin", loginedAdmin);  //将登陆的管理员对象存入session域中
			session.removeAttribute("loginErrorNum");  //将登录错误次数属性从session域中移除
			return "r:/index.jsp";  //重定向到index.jsp
		}catch(AdminException e){
			//有异常  登录失败
			AdminLoginRecord alr = getALR(request,loginedAdmin,"成功");  //获取到AdminLoginRecord登录记录对象
			try {as.addALR(alr);//将登录记录对象添加到数据库
			} catch (AdminException e1) {throw new RuntimeException();}	
			
			request.setAttribute("msg", e.getMessage());  //将登录错误信息保存在request域中
			int loginErrorNum = 0;
			try{
				loginErrorNum = (int)session.getAttribute("loginErrorNum");  //强制类型转换  可能会抛异常
				//没有异常  说明session域中有loginErrorNum
				session.setAttribute("loginErrorNum", loginErrorNum+1);  //将登录错误次数+1
			}catch(Exception e2){
				//有异常  说明session域中没有loginErrorNum
				session.setAttribute("loginErrorNum", 1);  //将登录错误次数初始化为
			}
			
			if(loginErrorNum >= 2){  //如果登录次数大于等于3次
				//将验证码存在request域中
				request.setAttribute("verificationCode",
						"<img id='codeImg' style=\"margin:0 20px 20px 20px;\" alt=\"你的浏览器版本过低\" src=\"" + request.getContextPath()
								+ "/user.s?method=createVerification\" onclick='againCode()'/>"
								+ "<input style='margin:0 0 0 20px;' type='text' placeholder='请输入验证码' name='codeText' class=''/>");
			}
			
			return "f:/login.jsp";  //转发到登录页面
		}
		
		
	}
	/**
	 * 获取AdminLoginRecord对象
	 */
	 private AdminLoginRecord getALR(HttpServletRequest request, Admins loginedAdmin,String status) {
		 //创建AdminLoginRecord对象
		 AdminLoginRecord alr = new AdminLoginRecord();
		 
		 //设置属性值
		 alr.setAlrLoginIp(request.getRemoteAddr());  //设置登录IP
		 alr.setAlrLoginTime(new Timestamp(new Date().getTime()));  //设置登录时间
		 alr.setAlrStatus(status);   //设置登录状态
		 alr.setAlrAdmin(loginedAdmin);   //设置登录管理员对象
		return alr;
	}

 	/**
	 * 生成验证码
	 */
	public void createVerification(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Verification verification = new Verification(); // 创建验证码对象
		BufferedImage image = verification.getImage(); // 生成验证码图片
		session.setAttribute("verificationObject", verification);// 将验证码对象保存到session域中
		Verification.output(image, response.getOutputStream());// 把图片响应给客户端
	}
}
