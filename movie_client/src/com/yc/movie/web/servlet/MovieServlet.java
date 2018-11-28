package com.yc.movie.web.servlet;

import com.yc.exception.MovieException;
import com.yc.movie.bean.Movies;
import com.yc.utils.BaseServlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/movie.s")
public class MovieServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieService();
	/**
	 * ��ҳ���µ�Ӱ��ʾ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllMovie(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// ��allMovieBeanList���ϴ���request����
		//1.����  2.ʱ��  3.����(���ͱ�) 4.������ 5.ͼƬ����(ͼƬ��)
		try {
			session.setAttribute("movieListByTime", ms.findAllMovie("movieCreateTime"));
		
			session.setAttribute("movieListByCount",  ms.findAllMovie("movieVisitCount"));
			
			session.setAttribute("movieListByGrade",  ms.findAllMovie("movieGradeNum"));
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/index.jsp";
	}
}
