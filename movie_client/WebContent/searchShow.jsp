<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>Genre</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- pop-up -->
<link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />
<!-- //pop-up -->
<link href="css/easy-responsive-tabs.css" rel='stylesheet' type='text/css'/>
<link rel="stylesheet" type="text/css" href="css/zoomslider.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="css/font-awesome.css" rel="stylesheet"> 
<script type="text/javascript" src="js/modernizr-2.6.2.min.js"></script>
<!--/web-fonts-->
<link href='http://fonts.googleapis.com/css?family=Tangerine:400,700' rel='stylesheet' type='text/css'>
<link href="http://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<!--//web-fonts-->
</head>

<body> 
<%-- <c:if test="${empty pageBean }">
	<jsp:forward page="movie.s?method=goGenre" />
</c:if> --%>
<!--/main-header-->
  <!--/banner-section-->
  <%@ include file="head.jsp"%>
				<div class="w3_breadcrumb">
				</div>
			<!-- //breadcrumb -->

			<!--/content-inner-section-->
				<div class="w3_content_agilleinfo_inner">
					<div class="agile_featured_movies">
						<!--/comedy-movies-->
					<h3 class="agile_w3_title hor-t">${lg['keyWord'] } <span>${search_text}</span> </h3>
					</div>
					</div>
					<c:set var="pb" value="${pageBean_movie }" />
			<%--循环遍历电影 --%>
						 <div class="wthree_agile-requested-movies tv-movies">
						 <c:forEach items="${pb.beanList }" var="movie">
								<div class="col-md-2 w3l-movie-gride-agile requested-movies">
									<a href="<c:url value='/movie.s?method=singleShow&type=movie&id=${movie.movieId }' />" class="hvr-sweep-to-bottom"><img src="<c:url value='${movie.imgList[0].imgPath }' />" title="Movies Pro" class="img-responsive" alt=" ">
										<div class="w3l-action-icon"><i class="fa fa-play-circle-o" aria-hidden="true"></i></div>
									</a>
										<div class="mid-1 agileits_w3layouts_mid_1_home">
											<div class="w3l-movie-text">
												<h6><a href="<c:url value='/movie.s?method=singleShow&type=movie&id=${movie.movieId }' />" style="font-size:15px;">${movie.movieName }</a></h6>							
											</div>
											<div class="mid-2 agile_mid_2_home">
												<p>${fn:substring(movie.movieCreateTime,0,4)}</p>
												<div class="block-stars">
													<ul class="w3l-ratings">
														<c:set var="starNum" value="${movie.movieGradeNum/2}"/>
															<c:forEach var="i" begin="1" end="${starNum }">
																<a href="#"><i class="fa fa-star" aria-hidden="true"></i></a>
															</c:forEach>
															<c:if test="${starNum%1 != 0}">
																<a href="#"><i class="fa fa-star-half-o" aria-hidden="true"></i></a>
																<c:set var="starNum" value="${starNum+1}"/>
															</c:if>
															<c:forEach var="i" begin="${starNum+1}" end="5">
																<a href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a>
															</c:forEach>
													</ul>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
									<div class="ribben">
										<p>${lg["New"]}</p>
									</div>
							</div>
							</c:forEach>
							<div class="clearfix"></div>
						</div>
				
				
				<%--分页按钮 --%>
				<div class="blog-pagenat-wthree" style="margin:0 0 20px 0;">
					
					<ul>
						<li><a class="frist" href="<c:url value='/movie.s?method=search&Search=${search_text}&pc=${pb.pc-1}' />">Prev</a></li>
						
						<c:choose>
			              	<c:when test="${pb.tp < 3}">
			              		<c:set var="begin" value="1" />
			              		<c:set var="end" value="${pb.tp}"/>
			              	</c:when>
			              	<c:otherwise>
			              		<c:set var="begin" value="${pb.pc-1}"/>
			              		<c:set var="end" value="${pb.pc+1}"/>
			              		
			              		<c:if test="${begin < 1 }">
				              		<c:set var="begin" value="1"/>
				              		<c:set var="end" value="3"/>
				              	</c:if>
				              	
				              	<c:if test="${end > pb.tp }">
				              		<c:set var="begin" value="${pb.tp-2}"/>
				              		<c:set var="end" value="${pb.tp}"/>
				              	</c:if>
			              	</c:otherwise>
			              </c:choose>
			              <c:forEach var="i" begin="${begin}" end="${end}">
			              	<c:choose>
			              		<c:when test="${i eq pb.pc}">
			              			<li><a  style="background:#bbb;" href="<c:url value='/movie.s?method=search&Search=${search_text}&pc=${i}' />">${i }</a></li>
			              		</c:when>
			              		<c:otherwise>
			              			<li><a href="<c:url value='/movie.s?method=search&Search=${search_text}&pc=${i}' />">${i }</a></li>
			              		</c:otherwise>
			              	</c:choose>
			              </c:forEach>
						
						<li><a class="last" href="<c:url value='/movie.s?method=search&Search=${search_text}&pc=${pb.pc+1}' />">Next</a></li>
					</ul>
				</div>
			<!--//comedy-movies-->
		<c:if test="${! empty msg}">		
			<script type="text/javascript">
				alert("${msg}");
			</script>
		</c:if>	
<!--/footer-bottom-->
	<%@ include file="footer.jsp"%>

</body>
</html>