package com.yc.movie.web.servlet;

import com.yc.exception.MovieException;
import com.yc.movie.bean.Comment;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Reply;
import com.yc.movie.bean.Teleplay;
import com.yc.movie.bean.Users;
import com.yc.movie.service.MovieService;
import com.yc.utils.BaseServlet;
import com.yc.utils.CommonsUtils;

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
	 * 显示该电影 该商户下的可选日期和时间厅室
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showChoosableByMovieMerchant(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Movies movie = (Movies)session.getAttribute("movieBallotTicket");  //得到当前正在浏览的电影
		movie.setTicketList(ms.createTicketByStartTime(movie.getTicketList()));  //过滤掉已经上映了的电影
		session.setAttribute("movieByNextTime", movie);
		Long merId = Long.parseLong(request.getParameter("merId"));  
		try {
			Merchant merchant = ms.findMerchantById(merId);  //得到当前选择的商户
			session.setAttribute("nowMerchant", merchant);
			return "r:/index/tickets.jsp";
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
			return "r:/index/tickets.jsp";
		} 
	}
	/**
	 * 跳转到购票网页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String goBallotTicket(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String refererPath = request.getHeader("referer"); //获取referer请求头
		session.setAttribute("refererPath", refererPath);
		Users loginedUser = (Users)session.getAttribute("loginedUser");
		if(loginedUser == null){
			request.setAttribute("msg", "你该没有登录，请先登录再进行下一步操作！");
			return "f:/userLogin.jsp";
		}
		Long movieId = Long.parseLong(request.getParameter("movieId"));  //得到要购票的电影ID
		try {
			Movies movie = ms.findMovieById(movieId);
			session.setAttribute("movieBallotTicket", movie);
			return "r:/ballotTicket.jsp";
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
			return "r:/ballotTicket.jsp";
		}
		
	}
	
	/**
	 * 添加评分
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addMovieGradeNum(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Long movieId = Long.parseLong(request.getParameter("movieId"));  //获取到要添加评分的movieId
		try {
			ms.addMovieGradeNum(movieId);  //添加评分
			response.getWriter().append("yes");
		} catch (MovieException e) {
			response.getWriter().append(e.getMessage());
		}  
		
	}
	
	/**
	 * 给回复发送回复
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendReplyToReply(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Reply form = CommonsUtils.toBean(request, Reply.class); // replyContent   replyId
		Users user = (Users)session.getAttribute("loginedUser");
		try {
			ms.addReplyToReply(form,user);
			response.getWriter().append("yes");
		} catch (MovieException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	
	/**
	 * 给评论发送回复
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendReplyToComment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Reply form = CommonsUtils.toBean(request, Reply.class);  // replyContent  replyCommentId
		Users user = (Users)session.getAttribute("loginedUser");
		try {
			ms.addReplyToComment(form,user);
			response.getWriter().append("yes");
		} catch (MovieException e) {
			response.getWriter().append(e.getMessage());
		}
	}
	
	
	/**
	 * 发送评论
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendComment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String refererPath = request.getHeader("referer"); //获取referer请求头
		session.setAttribute("refererPath", refererPath);
		
		Users loginedUser = (Users)session.getAttribute("loginedUser");  //获取当前登录的用户对象  
		if(loginedUser == null){  //如果没有登录   就不能发送评论  转发到登录页面
			response.getWriter().append("notLogin");
			return;
		}
		
		//如果不为null  说明登录了可以发送新的评论
		Comment form = CommonsUtils.toBean(request, Comment.class);  //将表单数据封装成javabean对象
//		//form中有  commentMovieId  commentContent
		
		try {
			ms.sendComment(form,loginedUser); //发送评论
			response.getWriter().append("yes");
		} catch (MovieException e) {
			response.getWriter().append(e.getMessage());
		}  
	}
	/**
	 * 单个电影/电视剧展示  （用户点击某个电影/电视剧）
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String singleShow(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String type = request.getParameter("type");  //获取类型  电影/电视剧
		Long id = Long.parseLong(request.getParameter("id"));	//获取选择的id
		try{
			if("movie".equals(type)){  //电影
				Movies singleShow = ms.findMovieById(id);
				request.setAttribute("singleShow", singleShow);
				return "f:/singleMovie.jsp";
			}else if("teleplay".equals(type)){  //电视剧
				Teleplay singleShow = ms.findTeleplayById(id);
				request.setAttribute("singleShow", singleShow);
				return "f:/singleTeleplay.jsp";
			}
			return "f:/index.jsp";
		}catch(MovieException e){
			request.setAttribute("msg", e.getMessage());
			return "f:/index.jsp";
		}
	}
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
			request.setAttribute("movieListByTime", movieListByTime);
//			System.out.println("servlet1"+movieListByTime);
			
			List<Movies> movieListByCount = ms.findAllMovie();
			request.setAttribute("movieListByCount",  movieListByCount);
			
			List<Movies> movieListByGrade = ms.findAllMovie();
			request.setAttribute("movieListByGrade",  movieListByGrade);
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/index.jsp";
	}
}
