package com.yc.movie.web.servlet;

import com.yc.exception.MovieException;
import com.yc.movie.bean.Comment;
import com.yc.movie.bean.Indent;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.PageBean;
import com.yc.movie.bean.Reply;
import com.yc.movie.bean.Teleplay;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.Users;
import com.yc.movie.service.MovieService;
import com.yc.utils.BaseServlet;
import com.yc.utils.CommonsUtils;
import com.yc.utils.PaymentUtil;
import com.yc.utils.PropertiesUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
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
	 * 设置订单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String setIndentStatus(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String type = request.getParameter("type");  //获取到要设置为的值
		Long indentId = Long.parseLong(request.getParameter("id"));  //获取到要设置的订单ID
		try {
			ms.setIndentStatus(indentId,type);
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "r:/userMovieIndent.jsp";
	}
	/**
	 * 跳转分类页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String goGenre(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			
			String op = request.getParameter("op");  //得到选择的分类
			
			List<Movies> movieList = ms.findAllMovie();  //找到所有的电影
			
			if(movieList == null || movieList.size() < 1){
				request.setAttribute("msg", "当前分类下没有电影");
				return "f:/genre.jsp";
			}
			String genreName = null;
			Properties p = new Properties();
			p.load(this.getClass().getClassLoader().getResourceAsStream("res_zh_CN.properties"));
			genreName = p.getProperty(op);
			
//			System.out.println(genreName);
			
			Integer pc = CommonsUtils.getPageListPc(request);  //得到pc
			PageBean<Movies> pb = ms.findMovieByClassify(pc,8,genreName);
			
			ResourceBundle rb = (ResourceBundle) session.getAttribute("lg");
			String genreName2 = rb.getString(op);
//			System.out.println("genreName2:"+genreName2);
			
			request.setAttribute("pageBean", pb);
			request.setAttribute("genreName", genreName2);
			request.setAttribute("op", op);
			
			return "f:/genre.jsp";
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/genre.jsp";
		}
	}
	/**
	 * 生成订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void createIndent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String choseStr = request.getParameter("choseStr");  //得到选择的座位
		Users loginedUser = (Users)session.getAttribute("loginedUser"); //得到当前登录用户
		String[] choseArr = null;
		if(choseStr == null || choseStr.trim().isEmpty()){
			response.getWriter().append("你还没有选择座位！");
			return;
		}else{
			choseArr = choseStr.split(";");  //得到选定座位的序号数组
		}
		
		try {
			List<Ticket> showChoseList = (List<Ticket>)session.getAttribute("showChoseList");
			List<Ticket> ticketList = new ArrayList<Ticket>();  //得到选择的电影票集合
			for(String s : choseArr){
				for(Ticket t : showChoseList){
					if(s.equals(t.getTicketLocationNum()+"")){
						ms.setTicketStatus(t,"0");  //设置电影票的状态
						ms.setTicketBuyBy(t,loginedUser);  //设置电影票的ticketBuyBy
						ticketList.add(t);
					}
				}
			}
			
			Movies nowMovie = (Movies)session.getAttribute("movieBallotTicket");  //得到当前正在浏览的电影
			
			Indent indent = ms.createIndent(ticketList,loginedUser,nowMovie);//创建订单对象
			
			session.setAttribute("indentObj", indent);
			response.getWriter().append("yes");
		} catch (MovieException e) {
			response.getWriter().append(e.getMessage());
			e.printStackTrace();
		}  
		
	}
	
	/**
	 * 生成选择座位页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showTicketChose(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String date = request.getParameter("date");  //得到日期  11-11
		String theater = request.getParameter("theater"); //得到厅室
		Merchant nowMerchant = (Merchant)session.getAttribute("nowMerchant");  //得到当前选择的商户
		try {
			List<Ticket> showChoseList = ms.getShowChoseList(nowMerchant.getMerId(),date,theater);//得到204张电影票
			System.out.println("电影票张数："+showChoseList.size());
			
			StringBuilder sb = new StringBuilder();  //得到已卖出的位置
			for(Ticket t : showChoseList){
				if("0".equals(t.getTicketStatus()))
					sb.append(t.getTicketLocationNum()+";");
			}
			System.out.println("已卖出的位置："+sb.toString());
			
			session.setAttribute("statusArr", sb.toString());
			session.setAttribute("showChoseList", showChoseList);
			return "r:/index/choose_seat.jsp";
		} catch (MovieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		
		return null;
	}
	/**
	 * 显示该电影 该商户下的可选日期和时间厅室
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showChoosableByMovieMerchant(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			Movies movie = (Movies)session.getAttribute("movieBallotTicket");  //得到当前正在浏览的电影
			Long merId = Long.parseLong(request.getParameter("merId"));  //当前选择的商户的ID
			
			List<String> dateArr = ms.createTicketByStartTime(movie.getTicketList()); //过滤掉已经上映了的电影票
//			System.out.println("dateArr:"+dateArr);
			
			
			
			 //这个集合中存储的是所有的日期对应的Movie
			Map<String,Movies> allMovietMap = new LinkedHashMap<String,Movies>(); 
//			System.out.println("日期有："+dateArr);
			for(String date : dateArr){
				Movies movieByDate = ms.findMovieById(movie.getMovieId());  //得到一个新的movie
				List<Ticket> ticketList2 = ms.createTicketByDate(movieByDate.getTicketList(),date); //得到当前日期对应的所有电影票的集合
				ticketList2 = ms.createTicketByMerId(ticketList2,merId);  //通过商户ID过滤
				movieByDate.setTicketList(ticketList2);
				allMovietMap.put(date, movieByDate);  //将当前得到的电影集合存入Map中  键是当前的日期
			}
//			System.out.println("allMovietMap的长度:"+allMovietMap.size());
//			System.out.println("12-18号有："+allMovietMap.get("12-18").getTicketList().size());
			
//			String regx = movieByDate.getTicketList().get(0).getTicketStartTime().toString().substring(5, 10);  //12-18
//			List<Ticket> ticketList2 = ms.createTicketByDate(movieByDate.getTicketList(),regx);  //再次过滤电影票  只显示第一天的电影票
//			movieByDate.setTicketList(ticketList2);
			
			/*for(Ticket t : movie.getTicketList()){
				System.out.println(t);
			}*/
			session.setAttribute("dateArr", dateArr);
			session.setAttribute("allMovietMap", allMovietMap);
			  
		
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
				ms.addVisitCount(id);//访问量加一
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
			List<Movies> movieList = ms.findAllMovie();
			if(movieList == null || movieList.size() < 1){
				request.setAttribute("msg", "当前网站中没有电影");
				return "f:/index.jsp";
			}
			int[] index1 = sortListByTime(movieList); 
			int[] index2 = sortListByGrade(movieList); 
			int[] index3 = sortListByVisit(movieList); 
			
			request.setAttribute("movieList",  movieList);
			request.setAttribute("indexTime", index1);
			request.setAttribute("indexGrade", index2);
			request.setAttribute("indexVisit", index3);
		} catch (MovieException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/index.jsp";
	}

	/**
	 * 按访问量排序
	 * @param movieList
	 * @return
	 */
	private int[] sortListByVisit(List<Movies> movieList) {
		Long[] time = new Long[movieList.size()];
		int num = 0;
		for(Movies m:movieList){
			time[num] = m.getMovieVisitCount();
			num++;
		}
		int[] index = new int[movieList.size()];
		for(int i=0;i<index.length;i++){
			index[i] = i;
		}
		for(int i=0; i<time.length; i++){
		    for(int j=i+1;j<time.length;j++){
		        if(time[j] > time[i]){ 
		        	Long temp = time[j];
		        	time[j] = time[i];
		        	time[i] = temp;
		        	int temp2 = index[j];
		        	index[j] = index[i];
		        	index[i] = temp2;
		        }       
		    }       
		}
		return index;
	}

	/**
	 * 按评分排序
	 * @param movieList
	 * @return
	 */
	private int[] sortListByGrade(List<Movies> movieList) {
		Double[] time = new Double[movieList.size()];
		int num = 0;
		for(Movies m:movieList){
			time[num] = m.getMovieGradeNum();
			num++;
		}
		int[] index = new int[movieList.size()];
		for(int i=0;i<index.length;i++){
			index[i] = i;
		}
		for(int i=0; i<time.length; i++){
		    for(int j=i+1;j<time.length;j++){
		        if(time[j] > time[i]){ 
		        	Double temp = time[j];
		        	time[j] = time[i];
		        	time[i] = temp;
		        	int temp2 = index[j];
		        	index[j] = index[i];
		        	index[i] = temp2;
		        }       
		    }       
		}
		return index;
	}

	/**
	 * 按时间排序
	 * @param movieListByTime
	 * @return
	 */
	private int[] sortListByTime(List<Movies> movieListByTime) {
		Long[] time = new Long[movieListByTime.size()];
		int num = 0;
		for(Movies m:movieListByTime){
			time[num] = m.getMovieCreateTime().getTime();
			num++;
		}
		int[] index = new int[movieListByTime.size()];
		for(int i=0;i<index.length;i++){
			index[i] = i;
		}
		for(int i=0; i<time.length; i++){
		    for(int j=i+1;j<time.length;j++){
		        if(time[j] > time[i]){ 
		        	Long temp = time[j];
		        	time[j] = time[i];
		        	time[i] = temp;
		        	int temp2 = index[j];
		        	index[j] = index[i];
		        	index[i] = temp2;
		        }       
		    }       
		}
		return index;
	}
	
	/**
	 * 验证支付
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void payRegx(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        String p1_MerId =  request.getParameter("p1_MerId");//商户编号
        String r0_Cmd =  request.getParameter("r0_Cmd");// 业务类型 返回固定值 "Buy"
        String r1_Code =  request.getParameter("r1_Code");// 支付结果 "1"代表成功
        String r2_TrxId =  request.getParameter("r2_TrxId");// 易宝支付交易流水号
        String r3_Amt =  request.getParameter("r3_Amt");// 支付金额
        String r4_Cur =  request.getParameter("r4_Cur");// 交易币种 返回时是"RMB"
        String r5_Pid =  request.getParameter("r5_Pid");// 商品名称
        String r6_Order =  request.getParameter("r6_Order");// 商户订单号
        String r7_Uid =  request.getParameter("r7_Uid");// 易宝支付会员ID
        String r8_MP =  request.getParameter("r8_MP");// 商户扩展信息
        String r9_BType =  request.getParameter("r9_BType");// 交易结果返回类型 为“1”: 浏览器重定向; 为“2”: 服务器点对点通讯.
        String hmac =  request.getParameter("hmac");//签名数据
        String rp_PayDate =  request.getParameter("r9_BType");//  支付成功时间  该返回参数不参与到hmac校验，范例中没有收录，可根据您的需要自行添加.

        /**

         * 数据校验是否正确

        易宝第三方：把交易信息明文加密--->密文    然后把明文和密文都交给发送给商户

              商户拿到数据后,把传过来的明文和传过来密文比较,

               如果数据相同，则说明没有被篡改 (商家与易宝加密时都用的商户注册时给的相同key)    

         */
        boolean flag = PaymentUtil.verifyCallback(hmac, p1_MerId, 
        		r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, 
        		r6_Order, r7_Uid, r8_MP, r9_BType, 
        		PropertiesUtils.getPropertiesByKey("keyValue"));

        if(!flag){//数据不一致
            out.write("交易失败！可能存在风险！");
            return;//结束
        }
        if("1".equals(r1_Code)){//说明支付成功

	        //支付成功：找到订单，更改订单的支付状态。
	
	        /**
	
	         * 只有支付成功时易宝支付才会通知商户。支付成功回调有两种，都会以GET形式通知到在线支付请求参数中的p8_Url上：■ 浏览器重定向■ 服务器点对点通讯
	
	            关于两种通知和业务处理说明：如果用户在支付成功后，并没有通知商家而是直接关闭了重定向的窗口，那么重定向就不会通知到商户，不管用户是否重定向通知到商户，
	
	            服务器点对点通知都会通知到商户，所以在callback页中r9_btype=1和r9_btype=2的两种通知类型中都要进行业务处理。
	
	            并注意处理重复订单的问题，以防两次通知都处理了相同的业务造成损失。 
	
	            应答机制是指当贵公司系统收到易宝支付的支付成功数据通知（服务器点对点通讯形式）时，必须回写以“success”开头的stream，易宝支付收到该stream，便认为贵公司已收到；否则将继续发送通知，直至收到。
	
	         */
	
	        if("1".equals(r9_BType)){//服务器点对点通讯.
	
	            System.out.println("#################交易成功");
	
	            out.write("<h2>订单号：<span style='color:red;'>"+r6_Order+"</span>，合计：<span style='color:red;'>"+r3_Amt+"</span>元，交易成功！<h2>");
	
	            /**
	
	             * 以下是处理交易成功后的业务逻辑，注意处理重复订单的问题，以防两次通知都处理了相同的业务造成损失。
	
	             */
	
	        }
	
	        if("2".equals(r9_BType)){//服务器点对点通讯.
	
	            out.write("success");//回写以“success”开头的stream，易宝支付收到该stream，便认为贵公司已收到；否则将继续发送通知，直至收到。
	
	            /**
	
	             * 以下是处理交易成功后的业务逻辑，注意处理重复订单的问题，以防两次通知都处理了相同的业务造成损失。
	
	             */
	        }
        }
	}
	
	/**
	 * 发起支付
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void pay(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String p0_Cmd = "Buy"; //业务类型   固定值“Buy” .
        String p1_MerId = PropertiesUtils.getPropertiesByKey("p1_MerId");//商户编号
        String p2_Order = request.getParameter("orderId");// 商户订单号
        String p3_Amt = request.getParameter("money");// 支付金额
        String p4_Cur = "CNY"; // 固定值 ”CNY”.
        String p5_Pid = "goodName"; // 商品名称
        String p6_Pcat = ""; // 商品种类
        String p7_Pdesc = ""; // 商品描述
        String p8_Url = PropertiesUtils.getPropertiesByKey("p8_Url"); // 商户接收支付成功数据的地址
        String p9_SAF = "1"; // 送货地址   为“1”: 需要用户将送货地址留在易宝支付系统;为“0”: 不需要，默认为 ”0”.
        String pa_MP = ""; // 商户扩展信息
        String pd_FrpId = request.getParameter("pd_FrpId"); // 支付通道编码
        String pr_NeedResponse = "0"; //固定值为“1”: 需要应答机制; 收到易宝支付服务器点对点支付成功通知，必须回写以”success”（无关大小写）开头的字符串，即使您收到成功通知时发现该订单已经处理过，也要正确回写”success”，否则易宝支付将认为您的系统没有收到通知，启动重发机制，直到收到”success”为止。 
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, PropertiesUtils.getPropertiesByKey("keyValue")); // 签名数据
        /**
         * 把组织好的数据重定向到正式请求地址：https://www.yeepay.com/app-merchant-proxy/node 
            商户：把交易信息明文加密--->密文    然后把明文和密文都交给发送给第三方易宝
                  第三方易宝拿到数据后,把传过来的明文和传过来密文比较,
                   如果数据相同，则说明没有被篡改 (易宝与商家加密时都用的商户注册时给的相同key)    
         */
        response.sendRedirect("https://www.yeepay.com/app-merchant-proxy/node?" +
                "p0_Cmd="+p0_Cmd+"&p1_MerId="+p1_MerId+"&p2_Order="+p2_Order+"&p3_Amt="+p3_Amt+"&p4_Cur="+p4_Cur+
                "&p5_Pid="+p5_Pid+"&p6_Pcat="+p6_Pcat+"&p7_Pdesc="+p7_Pdesc+"&p8_Url="+p8_Url+"&p9_SAF="+p9_SAF+
                "&pa_MP="+pa_MP+"&pd_FrpId="+pd_FrpId+"&pr_NeedResponse="+pr_NeedResponse+"&hmac="+hmac);
        System.out.println("https://www.yeepay.com/app-merchant-proxy/node?" +
                "p0_Cmd="+p0_Cmd+"&p1_MerId="+p1_MerId+"&p2_Order="+p2_Order+"&p3_Amt="+p3_Amt+"&p4_Cur="+p4_Cur+
                "&p5_Pid="+p5_Pid+"&p6_Pcat="+p6_Pcat+"&p7_Pdesc="+p7_Pdesc+"&p8_Url="+p8_Url+"&p9_SAF="+p9_SAF+
                "&pa_MP="+pa_MP+"&pd_FrpId="+pd_FrpId+"&pr_NeedResponse="+pr_NeedResponse+"&hmac="+hmac);
        
	}
}
