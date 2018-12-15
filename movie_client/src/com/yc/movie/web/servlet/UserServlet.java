package com.yc.movie.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yc.exception.UserException;
import com.yc.movie.bean.Indent;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.movie.bean.Verify;
import com.yc.movie.service.UserService;
import com.yc.utils.BaseServlet;
import com.yc.utils.CommonsUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.s")
@MultipartConfig
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private static Long userFlag = 1l;
	private static Long merFlag = 0l;
	
	/**
	 * 刷新接收信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void revicedContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users loginedUser = (Users)session.getAttribute("loginedUser");  //取出当前登录的用户
		String str = "merToUser"+loginedUser.getUserId();  // merToUser1
		String fileName = request.getServletContext().getRealPath("/onlineChat/sendMerToUser.properties").replace("movie_client", "movie_server");
		File file = new File(fileName);
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		
		String text = p.getProperty(str);
//		System.out.println("用户收到的信息："+text);
		if(text == null || text.isEmpty()){
			response.getWriter().append("no");
			return;
		}
		String[] arr = text.split(";");
//		for(String s:arr)
//			System.out.println(s);
		Long flag = Long.parseLong(arr[0]);
		if(flag.equals(userFlag)){
			response.getWriter().append("no");
		}else{
			String content = arr[1];
			System.out.println("用户收到的信息："+content);
			userFlag = flag;
			response.getWriter().append(content);
		}
	}
	
	
	/**
	 * 将聊天信息发送给服务器
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendContentToChatServer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");  //获取消息内容
		String id = request.getParameter("id");  
		id = id.substring(8);  //获取商户ID
		Users loginedUser = (Users)session.getAttribute("loginedUser");  //取出当前登录的用户

		String fileName = request.getServletContext().getRealPath("/onlineChat/sendUserToMer.properties").replace("movie_server", "movie_client");
		File file = new File(fileName);
		Properties p = new Properties();
		FileOutputStream fos = new FileOutputStream(file);
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		
		p.put("userToMer"+id, merFlag+";"+loginedUser.getUserId()+"{[code]}"+content+"{[code]}"+loginedUser.getImgList().get(0).getImgPath());
//		System.out.println("用户向商户发送消息："+merFlag+";"+loginedUser.getUserId()+"{[code]}"+content+"{[code]}"+loginedUser.getImgList().get(0).getImgPath());
		merFlag++;
		p.store(fos, "");
		
		fos.close();
	}
	///////////////// wt 订阅////////////////////////////
	/**
	 * 订阅
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求数据
		String email = request.getParameter("email");

		// 校验邮箱的格式
		try {
			us.regxSubEmail(email);

			// 如果执行到了这里说明email邮箱格式正确 然后把邮箱添加到订阅表里面
			us.addSubEmail(email);

			// 如果执行到了这里那就添加成功
			response.getWriter().append("yes");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	/////////////////////////////////////////////

	/**
	 * 根据当前登录用户查找所有订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findIndentListByLoginedUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users loginedUser = (Users) session.getAttribute("loginedUser");
		try {
			List<Indent> indentList = us.findIndentListByUser(loginedUser); // 查找所有订单
			request.setAttribute("indentList", indentList);

		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/userMovieIndent.jsp";

	}

	/**
	 * 修改信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 需求 1.姓名 2.手机号 3.邮箱 4.地址 5.出生日期 7.头像
		Users loginedUser = (Users) session.getAttribute("loginedUser");
		String userName = request.getParameter("userName"); // 姓名
		String userTel = request.getParameter("userTel"); // 手机号
		String userEmail = request.getParameter("userEmail"); // 邮箱

		// 地址
		String country = request.getParameter("country"); // 国家
		String province = request.getParameter("province"); // 省
		String city = request.getParameter("city"); // 市
		String district = request.getParameter("district"); // 县
		String fullAddress = request.getParameter("fullAddress"); // 详细地址
		String userAddr = country + " " + province + " " + city + " " + district + " " + fullAddress; // 地址

		// 出生日期
		String y = request.getParameter("y"); // 年
		String m = request.getParameter("m"); // 月
		String d = request.getParameter("d"); // 日
		Date da = CommonsUtils.dateFormat(y + "-" + m + "-" + d, "yyyy-MM-dd");
		java.sql.Date userBirthday = new java.sql.Date(da.getTime()); // 出生日期

		// 头像
		Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		// System.out.println("filename:"+filename);
		// System.out.println("姓名："+userName);
		// System.out.println("手机号："+userTel);
		// System.out.println("邮箱："+userEmail);
		// System.out.println("地址："+userAddr);
		// System.out.println("出生日期："+userBirthday);
		// return null;
		Users form = new Users();
		form.setUserName(userName);
		form.setUserTel(userTel);
		form.setUserEmail(userEmail);
		form.setUserAddr(userAddr);
		form.setUserBirthday(userBirthday);
		form.setUserId(loginedUser.getUserId());
		form.setUserAge((new Date().getTime() - userBirthday.getTime()) / 1000 / 60 / 60 / 24 / 365); // 设置年龄

		// 判断地址是否为null
		if (userAddr.startsWith("中国 省"))
			form.setUserAddr(loginedUser.getUserAddr());

		if (userEmail == null)
			form.setUserEmail(loginedUser.getUserEmail());

		if (userTel == null)
			form.setUserTel(loginedUser.getUserTel());

		String sqlPath = null;
		// 上传头像
		if (filename != null && !filename.trim().isEmpty()) {
			sqlPath = CommonsUtils.uploadImage(request, "/merHeadCreateImage", part, 100, 100);
		}

		try {
			us.alterInfo(form, sqlPath);

			loginedUser = us.findUserByUserId(loginedUser.getUserId());
			session.setAttribute("loginedUser", loginedUser);
			return "r:/userAlterInfo.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/userAlterInfo.jsp";
		}

	}

	/**
	 * 验证邮箱
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regxEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("inputEmail"); // 获取到输入的邮箱
		Users loginedUser = (Users) session.getAttribute("loginedUser"); // 得到当前登录的用户
		try {
			us.regxEmail(email, loginedUser);
			response.getWriter().append("yes");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
		}
	}

	/**
	 * 验证手机号
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regxTel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tel = request.getParameter("inputTel"); // 获取到输入的手机号
		Users loginedUser = (Users) session.getAttribute("loginedUser"); // 得到当前登录的用户
		try {
			us.regxTel(tel, loginedUser);
			response.getWriter().append("yes");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
		}
	}

	/**
	 * 通过年和月获取日
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateDay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer y = Integer.parseInt(request.getParameter("y"));
		Integer m = Integer.parseInt(request.getParameter("m"));
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, y);
		c.set(Calendar.MONTH, m - 1);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		String[] dayArr = getModel(1, days);
		response.getWriter().append(JSON.toJSONString(dayArr));
	}

	/**
	 * 根据传入参数生成天
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private String[] getModel(int start, int end) {
		String[] m = new String[end - start + 1];
		for (int i = 0; i < m.length; i++) {
			m[i] = String.valueOf(i + start);
		}
		return m;
	}

	/**
	 * 根据市获取县
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getDistrictByCity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cId = request.getParameter("cId");
		Map<String, String> dis = CommonsUtils.xmlDistricts(cId, this.getClass());
		String jsonStr = JSON.toJSONString(dis);
		response.getWriter().append(jsonStr);
	}

	/**
	 * 根据省ID获取市和县
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCityAndDistrictByProvince(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("pId"); // 获取省id
		Map<String, String> cityMap = CommonsUtils.xmlCities(pId, this.getClass());
		String s = null;
		for (Iterator<Entry<String, String>> it = cityMap.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> me = it.next();
			s = me.getKey();
			break;
		}
		Map<String, String> dis = CommonsUtils.xmlDistricts(s, this.getClass());
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(cityMap);
		list.add(dis);
		String jsonStr = JSON.toJSONString(list);
		response.getWriter().append(jsonStr);
	}

	/**
	 * 生成所有省
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getMapByProvinces(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = CommonsUtils.xmlProvince(this.getClass());
		session.setAttribute("provincesMap", map);
		return "r:/userAlterInfo.jsp";
	}

	/**
	 * 跳转登录页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String goLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String refererPath = request.getHeader("referer");
		session.setAttribute("refererPath", refererPath);
		System.out.println("refererPath:" + refererPath);
		Users user = (Users) session.getAttribute("loginedUser");
		if (user == null) {
			return "r:/userLogin.jsp";
		} else {
			request.getRequestDispatcher(refererPath).forward(request, response);
			return null;
		}
	}

	/**
	 * 当前是否登录 ajax
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String refererPath = request.getHeader("referer");
		session.setAttribute("refererPath", refererPath);
		Users user = (Users) session.getAttribute("loginedUser");
		if (user == null) {
			response.getWriter().append("notLogin");
			return;
		} else {
			response.getWriter().append("yes");
		}
	}

	/**
	 * 登录之前设置是哪个页面发送的登录请求 保存在session中
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginSetReferer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String refererPath = request.getHeader("referer");
		session.setAttribute("refererPath", refererPath);
		return "r:/userLogin.jsp";
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.removeAttribute("refererPath");
		session.removeAttribute("loginedUser");
	}

	/**
	 * 发送验证码
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendVerify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code"); // 获取手机号/验证码
		String userAccount = request.getParameter("userAccount"); // 获取用户名
		Integer num = Integer.parseInt(request.getParameter("num"));
		try {
			String createVerify = us.sentVerify(code, userAccount, num);
			Cookie cookie = new Cookie("verifyCode", createVerify); // 将生成的验证码存在cookie中
			cookie.setMaxAge(60 * 10); // 设置有效期为10分钟
			response.addCookie(cookie); // 将cookie添加
		} catch (UserException e) {
			response.getWriter().append(e.getMessage()); // 将错误信息响应给客户端
		}

	}

	/**
	 * 更新用户 忘记密码/用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users form = CommonsUtils.toBean(request, Users.class); // 获取表单数据
																// userAccount
																// userPwd
		String code = request.getParameter("code"); // 手机号或者邮箱
		String userPwd2 = request.getParameter("userPwd2"); // 确认密码
		String inputVerify = request.getParameter("verify"); // 用户输入的验证码
		Cookie cookie = CommonsUtils.getCookie(request, "verifyCode"); // 获取验证码对应的cookie
		Integer num = Integer.parseInt(request.getParameter("num")); // 是注册还是修改密码
		if (cookie == null) {
			response.getWriter().append("你还没有发送验证码");
			return;
		}
		String createVerify = cookie.getValue(); // 当前发送的验证码
		Verify verify = new Verify(); // 创建验证码对象
		verify.setInputVerify(inputVerify);
		verify.setCreateVerify(createVerify);

		try {
			if (num == 1) {
				us.updateUser(form, code, userPwd2, verify, UserService.UPDATE_TYPE_PWD); // 修改密码
			} else if (num == 2) {
				us.updateUser(form, code, userPwd2, verify, UserService.UPDATE_TYPE_REGISTER); // 用户注册
			}
			CommonsUtils.removeCookie(request, response, "verifyCode"); // 删除cookie中的验证码
			response.getWriter().append("");
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());
		}
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users form = CommonsUtils.toBean(request, Users.class); // 获取表单数据
		form.setUserIpAddr(request.getRemoteAddr());  //设置当前登录IP
		try {
			UserLoginRecord ulr = getULR(request); // 得到登录日志对象
			Users loginedUser = us.login(form, ulr); // 登录
			session.setAttribute("loginedUser", loginedUser); // 把登录成功的对象存在session域中

		} catch (UserException e) {
			response.getWriter().append(e.getMessage()); // 登录失败把信息响应给页面
		}
	}

	/**
	 * 得到登录日志对象
	 * 
	 * @param request
	 * @param loginedUser
	 * @return
	 */
	private UserLoginRecord getULR(HttpServletRequest request) {
		UserLoginRecord ulr = new UserLoginRecord();
		ulr.setUlrLoginIp(request.getRemoteAddr()); // 设置登录IP
		ulr.setUlrLoginTime(new Timestamp(new Date().getTime())); // 设置登陆时间
		return ulr;
	}
}
