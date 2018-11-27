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
 * 提供一个BaseServlet以便于其他处理请求的Servlet继承这个BaseServlet以达到扩展功能的目的
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
		//处理编码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		
		 //1.获取 methodName 参数，它是用户想调用的方法名称
        String methodName = request.getParameter("method");
        this.session = request.getSession();
        
        Method method = null;
        //2.通过方法名称获取 Method 对象
        try {
            //[3]使其子类能够调用自身的私有方法
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("您调用的  " + methodName + "（） 方法不存在", e);
        }
        //3.通过 Method 对象来调用它
            try {  
                method.setAccessible(true); //使用单一安全性检查（为了提高效率）
                String result = (String) method.invoke(this, request, response);
                //[2]简化转发和重定向功能
                /*考虑到用 f、r 分别代替 forward、redirect 对他人阅读代码可能会造成阅读障碍
                故，在此并未进行替代*/
                if (result != null && !result.trim().isEmpty()) {
                    int index = result.indexOf(":");  //获取第一个冒号的位置
                    if(index == -1){	//如果没有冒号，使用转发
                    	request.getRequestDispatcher(result).forward(request, response);
                    }else{	//如果冒号存在
                    	String start = result.substring(0, index);  //分割出前缀
                    	String path = result.substring(index+1);	//分割出路径
                    	if("f".equals(start)){	//前缀为f  表示转发
                    		request.getRequestDispatcher(path).forward(request, response);
                    	}else if("r".equals(start)){	//前缀为r 表示重定向
                    		response.sendRedirect(request.getContextPath() + path);
                    	}
                    }
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

}
