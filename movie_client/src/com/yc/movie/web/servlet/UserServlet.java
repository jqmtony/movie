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
     * ע��
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	System.out.println(12312);
    }

    /**
     * ��������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void forgetPwd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	Users form = CommonsUtils.toBean(request, Users.class); //��ȡ������   userAccount  userPwd
    	String code = request.getParameter("code");  //�ֻ��Ż�������
    	String userPwd2 = request.getParameter("userPwd2");  //ȷ������
    	String verify = request.getParameter("verify");	//��֤��
    	
    	try {
			us.alterPwd(form,code,userPwd2,verify);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * ��¼
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	Users form = CommonsUtils.toBean(request, Users.class);  //��ȡ������
    	try {
    		UserLoginRecord ulr = getULR(request); //�õ���¼��־����
			Users loginedUser = us.login(form,ulr);  //��¼
			session.setAttribute("loginedUser", loginedUser);  //�ѵ�¼�ɹ��Ķ������session����
		} catch (UserException e) {
			response.getWriter().append(e.getMessage());  //��¼ʧ�ܰ���Ϣ��Ӧ��ҳ��
		}
    }

    /**
     * �õ���¼��־����
     * @param request
     * @param loginedUser
     * @return
     */
	private UserLoginRecord getULR(HttpServletRequest request) {
		UserLoginRecord ulr = new UserLoginRecord();
		ulr.setUlrLoginIp(request.getRemoteAddr()); //���õ�¼IP
		ulr.setUlrLoginTime(new Timestamp(new Date().getTime()));  //���õ�½ʱ��
		return ulr;
	}
}
