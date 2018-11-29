package com.yc.movie.web.servlet;

import com.yc.exception.MovieException;
import com.yc.movie.bean.Movies;
import com.yc.movie.service.MovieService;
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
	 * 主页最新电影显示
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllMovie(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// 将allMovieBeanList集合存在request域中
		//1.描述  2.时间  3.分类(类型表) 4.评分数 5.图片封面(图片表)
		try {
			List<Movies> movieListByTime = ms.findAllMovie();
			Collections.sort(movieListByTime,new Comparator<Movies>() {

				@Override
				public int compare(Movies o1, Movies o2) {
					if(o1.getMovieCreateTime().getTime() > o1.getMovieCreateTime().getTime()) return 1;
					if(o1.getMovieCreateTime().getTime() == o1.getMovieCreateTime().getTime()) return 0;
					return -1;
				}
			});
			request.setAttribute("movieListByTime", movieListByTime);
//			System.out.println("servlet1"+movieListByTime);
			
			List<Movies> movieListByCount = ms.findAllMovie();
			Collections.sort(movieListByCount,new Comparator<Movies>() {

				@Override
				public int compare(Movies o1, Movies o2) {
					if(o1.getMovieVisitCount() < o1.getMovieVisitCount()) return 1;
					if(o1.getMovieVisitCount() == o1.getMovieVisitCount()) return 0;
					return -1;
				}
			});
			request.setAttribute("movieListByCount",  movieListByCount);
//			System.out.println("servlet2"+movieListByCount);
			
			List<Movies> movieListByGrade = ms.findAllMovie();
			Collections.sort(movieListByGrade,new Comparator<Movies>() {

				@Override
				public int compare(Movies o1, Movies o2) {
					if(o1.getMovieGradeNum() > o1.getMovieGradeNum()) return 1;
					if(o1.getMovieGradeNum() == o1.getMovieGradeNum()) return 0;
					return -1;
				}
			});
			request.setAttribute("movieListByGrade",  movieListByGrade);
//			System.out.println("servlet3"+movieListByGrade);
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/index.jsp";
	}
}
