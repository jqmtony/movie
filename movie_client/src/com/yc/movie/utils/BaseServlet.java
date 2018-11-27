package com.yc.movie.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �ṩһ��BaseServlet�Ա����������������Servlet�̳����BaseServlet�Դﵽ��չ���ܵ�Ŀ��
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public HttpSession session = null;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		
		 //1.��ȡ methodName �����������û�����õķ�������
        String methodName = request.getParameter("method");
        this.session = request.getSession();
        
        Method method = null;
        //2.ͨ���������ƻ�ȡ Method ����
        try {
            //[3]ʹ�������ܹ����������˽�з���
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("�����õ�  " + methodName + "���� ����������", e);
        }
        //3.ͨ�� Method ������������
            try {  
                method.setAccessible(true); //ʹ�õ�һ��ȫ�Լ�飨Ϊ�����Ч�ʣ�
                String result = (String) method.invoke(this, request, response);
                //[2]��ת�����ض�����
                /*���ǵ��� f��r �ֱ���� forward��redirect �������Ķ�������ܻ�����Ķ��ϰ�
                �ʣ��ڴ˲�δ�������*/
                if (result != null && !result.trim().isEmpty()) {
                    int index = result.indexOf(":");  //��ȡ��һ��ð�ŵ�λ��
                    if(index == -1){	//���û��ð�ţ�ʹ��ת��
                    	request.getRequestDispatcher(result).forward(request, response);
                    }else{	//���ð�Ŵ���
                    	String start = result.substring(0, index);  //�ָ��ǰ׺
                    	String path = result.substring(index+1);	//�ָ��·��
                    	if("f".equals(start)){	//ǰ׺Ϊf  ��ʾת��
                    		request.getRequestDispatcher(path).forward(request, response);
                    	}else if("r".equals(start)){	//ǰ׺Ϊr ��ʾ�ض���
                    		response.sendRedirect(request.getContextPath() + path);
                    	}
                    }
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

}
