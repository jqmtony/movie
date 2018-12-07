package com.yc.movie.test;

import com.yc.utils.BaseServlet;
import com.yc.utils.CommonsUtils;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/t.s")
@MultipartConfig
public class TestServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

   /**
    * 上传测试
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    */
	public void upload(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		System.out.println("从页面获取到的路径是："+filename);
		CommonsUtils.zipWidthHeightImageFile(new File(filename), new File("imageUploadTest/1.jpg"), 100, 100);
	}

}
