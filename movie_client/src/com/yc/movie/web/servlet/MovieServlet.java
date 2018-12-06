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
	 * ��ʾ�õ�Ӱ ���̻��µĿ�ѡ���ں�ʱ������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showChoosableByMovieMerchant(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Movies movie = (Movies)session.getAttribute("movieBallotTicket");  //�õ���ǰ��������ĵ�Ӱ
		movie.setTicketList(ms.createTicketByStartTime(movie.getTicketList()));  //���˵��Ѿ���ӳ�˵ĵ�Ӱ
		session.setAttribute("movieByNextTime", movie);
		Long merId = Long.parseLong(request.getParameter("merId"));  
		try {
			Merchant merchant = ms.findMerchantById(merId);  //�õ���ǰѡ����̻�
			session.setAttribute("nowMerchant", merchant);
			return "r:/index/tickets.jsp";
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
			return "r:/index/tickets.jsp";
		} 
	}
	/**
	 * ��ת����Ʊ��ҳ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String goBallotTicket(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String refererPath = request.getHeader("referer"); //��ȡreferer����ͷ
		session.setAttribute("refererPath", refererPath);
		Users loginedUser = (Users)session.getAttribute("loginedUser");
		if(loginedUser == null){
			request.setAttribute("msg", "���û�е�¼�����ȵ�¼�ٽ�����һ��������");
			return "f:/userLogin.jsp";
		}
		Long movieId = Long.parseLong(request.getParameter("movieId"));  //�õ�Ҫ��Ʊ�ĵ�ӰID
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
	 * �������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addMovieGradeNum(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Long movieId = Long.parseLong(request.getParameter("movieId"));  //��ȡ��Ҫ������ֵ�movieId
		try {
			ms.addMovieGradeNum(movieId);  //�������
			response.getWriter().append("yes");
		} catch (MovieException e) {
			response.getWriter().append(e.getMessage());
		}  
		
	}
	
	/**
	 * ���ظ����ͻظ�
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
	 * �����۷��ͻظ�
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
	 * ��������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendComment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String refererPath = request.getHeader("referer"); //��ȡreferer����ͷ
		session.setAttribute("refererPath", refererPath);
		
		Users loginedUser = (Users)session.getAttribute("loginedUser");  //��ȡ��ǰ��¼���û�����  
		if(loginedUser == null){  //���û�е�¼   �Ͳ��ܷ�������  ת������¼ҳ��
			response.getWriter().append("notLogin");
			return;
		}
		
		//�����Ϊnull  ˵����¼�˿��Է����µ�����
		Comment form = CommonsUtils.toBean(request, Comment.class);  //�������ݷ�װ��javabean����
//		//form����  commentMovieId  commentContent
		
		try {
			ms.sendComment(form,loginedUser); //��������
			response.getWriter().append("yes");
		} catch (MovieException e) {
			response.getWriter().append(e.getMessage());
		}  
	}
	/**
	 * ������Ӱ/���Ӿ�չʾ  ���û����ĳ����Ӱ/���Ӿ磩
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String singleShow(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String type = request.getParameter("type");  //��ȡ����  ��Ӱ/���Ӿ�
		Long id = Long.parseLong(request.getParameter("id"));	//��ȡѡ���id
		try{
			if("movie".equals(type)){  //��Ӱ
				Movies singleShow = ms.findMovieById(id);
				request.setAttribute("singleShow", singleShow);
				return "f:/singleMovie.jsp";
			}else if("teleplay".equals(type)){  //���Ӿ�
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
