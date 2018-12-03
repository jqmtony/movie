<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<title>Single Page</title>
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
<c:if test="${empty singleShow}">
	<jsp:forward page="index.jsp"></jsp:forward>
</c:if>
<body>
<!--/main-header-->
  <!--/banner-section-->
	<%@ include file="head.jsp"%>
	<!-- breadcrumb -->
				<div class="w3_breadcrumb">
					<div class="breadcrumb-inner">	
						<ul>
							<li><a href="index.jsp">Home</a><i>//</i></li>
							<li>Single</li>
						</ul>
					</div>
				</div>
			<!-- //breadcrumb -->
	<div class="w3_breadcrumb"></div>
<!-- //breadcrumb -->
			<!--/content-inner-section-->
				<div class="w3_content_agilleinfo_inner">
						<div class="agile_featured_movies">
				            <div class="inner-agile-w3l-part-head">
					            <h3 class="w3l-inner-h-title">${singleShow.movieName }</h3>
								<p class="w3ls_head_para">Add short Description</p>
							</div>
							   <div class="latest-news-agile-info">
								   <div class="col-md-8 latest-news-agile-left-content">
											<div class="single video_agile_player">
											       
										            <div class="video-grid-single-page-agileits">
														<div data-video="f2Z65fobH2I" id="video"> <img src="${singleShow.imgList[0].imgPath }" alt="" class="img-responsive" /> </div>
													</div>
													 <h4>
													 	主演 ： 
													 	<c:forEach items="${singleShow.proList }" var="pro" begin="0" end="${fn:length(singleShow.proList)-2}">
													 		<a href="${pro.proLink }">${pro.proName }</a> |
													 	</c:forEach>
													 	<a href="${singleShow.proList[fn:length(singleShow.proList)-1].proLink }">${singleShow.proList[fn:length(singleShow.proList)-1].proName }</a>
													 </h4>
													 	
										    </div>
											<div class="single-agile-shar-buttons">
											    <h5 >${lg["ShareThis"] } :</h5>
													<ul>
														<li>
															<div class="fb-like" data-href="" data-layout="button_count" data-action="like" data-size="small" data-show-faces="false" data-share="false"></div>
															<script>(function(d, s, id) {
															  var js, fjs = d.getElementsByTagName(s)[0];
															  if (d.getElementById(id)) return;
															  js = d.createElement(s); js.id = id;
															  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.7";
															  fjs.parentNode.insertBefore(js, fjs);
															}(document, 'script', 'facebook-jssdk'));</script>
														</li>
														<li>
															<div class="fb-share-button" data-href="" data-layout="button_count" data-size="small" data-mobile-iframe="true"><a class="fb-xfbml-parse-ignore" target="_blank" href="">Share</a></div>
														</li>
														<li class="news-twitter">
															<a href="" class="twitter-follow-button" data-show-count="false">Follow @w3layouts</a><script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
														</li>
														<li>
															<a href="https://twitter.com/intent/tweet?screen_name=w3layouts" class="twitter-mention-button" data-show-count="false">Tweet to @w3layouts</a><script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
														</li>
														<li>
															<!-- Place this tag where you want the +1 button to render. -->
															<div class="g-plusone" data-size="medium"></div>

															<!-- Place this tag after the last +1 button tag. -->
															<script type="text/javascript">
															  (function() {
																var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
																po.src = 'https://apis.google.com/js/platform.js';
																var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
															  })();
															</script>
														</li>
													</ul>
											</div>
										<div class="admin-text">
												<h5>WRITTEN BY ADMIN</h5>
												<div class="admin-text-left">
													<a href="#"><img src="images/admin.jpg" alt=""></a>
												</div>
												<div class="admin-text-right">
													<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,There are many variations of passages of Lorem Ipsum available, 
													sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
													<span>View all posts by :<a href="#"> Admin </a></span>
												</div>
												<div class="clearfix"> </div>
										</div>
										<div class="response">
							<h4>${lg["Comment"] }</h4>
							<c:forEach items="${singleShow.commentList }" var="comment">
							<%--评论 --%>
							<div class="media response-info">
								<div class="media-left response-text-left">
									<a href="#">
										<img class="media-object" src="images/admin.jpg" alt="">
									</a>
									<h5><a href="#">${comment.user.userAccount }</a></h5>
								</div>
								<div class="media-body response-text-right">
									<p>${comment.commentContent }</p>
									<ul>
										<li>${comment.commentCreateTime }</li>
										<li><a href="single.jsp"><i class="fa fa-reply" aria-hidden="true"></i> ${lg["Reply"] }</a></li>
									</ul>
									<%--该评论的回复 --%>
									<c:forEach items="${comment.replyList }" var="reply">
									<div class="media response-info">
										<div class="media-left response-text-left">
											<a href="#">
												<img class="media-object" src="images/admin.jpg" alt="">
											</a>
											<h5><a href="#">${reply.user.userAccount }</a></h5>
										</div>
										<div class="media-body response-text-right">
											<p>${reply.replyContent }</p>
											<ul>
												<li>${reply.replyCreateTime }</li>
												<li><a href="single.jsp"><i class="fa fa-reply" aria-hidden="true"></i> ${lg["Reply"] }</a></li>
											</ul>		
										</div>
										<div class="clearfix"> </div>
									</div>
									</c:forEach>
								</div>
								<div class="clearfix"> </div>
							</div>
							</c:forEach>
							
							
							<!-- <div class="media response-info">
								<div class="media-left response-text-left">
									<a href="#">
										<img class="media-object" src="images/admin.jpg" alt="">
									</a>
									<h5><a href="#">Admin</a></h5>
								</div>
								<div class="media-body response-text-right">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,There are many variations of passages of Lorem Ipsum available, 
										sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.There are many variations of passages of Lorem Ipsum available.</p>
									<ul>
										<li>November 03, 2016</li>
										<li><a href="single.jsp"><i class="fa fa-reply" aria-hidden="true"></i> Reply</a></li>
									</ul>		
								</div>
								<div class="clearfix"> </div>
							</div> -->
						</div>
						
											<%--评论 --%>
											<!-- <div class="form-group mb-n">
												<label for="largeinput" class="col-sm-2 control-label label-input-lg">Large Input</label>
												<div class="col-sm-8">
													<textarea placeholder="Message" required="" style="width:500px;height:100px;"></textarea>
												</div>
											</div> -->
											 <div class="all-comments-info">
												 <h5 >${lg["addComment"] }  <font id="errorMsg" style="font-size:12px;color:red;"></font></h5>
												<div class="agile-info-wthree-box">
													   <input type="hidden" id="commentMovieId" value="${singleShow.movieId }">
													   <div class="col-md-6 form-info">
													   <textarea id="commentContent" placeholder="${lg['Message'] }" required="" style="width:700px;"></textarea>
													   <input type="button" value="${lg['send'] }" onclick="sendComment()" style="margin-left:530px;width:150px;height:40px;background-color:#06f;color:#fff;">
													  </div>
													 <div class="clearfix"> </div>
													 <script type="text/javascript">
													 	function sendComment(){
													 		var data = {
													 				commentContent : $('#commentContent').val(),
													 				commentMovieId : $('#commentMovieId').val()
													 		};
													 		$.post("<c:url value='/movie.s?method=sendComment' />",data,function(data){
													 			if(data === "yes"){
													 				alert("评论成功！感谢您为电影${singleShow.movieName}发表了评论！")
													 				history.go(0);
													 			}else if(data === "notLogin"){
													 				alert("你还没有登录，请先登录！");
													 				window.location.href = "userLogin.jsp";
													 			}else{
													 				$('#errorMsg').html("   * "+data+"!");
													 			}
													 		});
													 	}
													 </script>
												</div>
											</div> 
								   </div>
								   <div class="col-md-4 latest-news-agile-right-content">
								   <h4 class="side-t-w3l-agile">For Latest <span>Movies</span></h4>
										<div class="side-bar-form">
										 <form action="#" method="post">
											<input type="search" name="email" placeholder="Search here...." required="required">
											<input type="submit" value=" ">
										 </form>
									    </div>
								      <h4 class="side-t-w3l-agile">Hot <span>Topics</span></h3>
									    <ul class="side-bar-agile">
										   <li><a href="single.jsp">John Abraham, Sonakshi Sinha and Tahir ...</a><p>Sep 29, 2016</p></li>
										   <li><a href="single.jsp">Romantic drama about two people finding out that love</a><p>Feb 3, 2016</p></li>
										   <li><a href="single.jsp">Storks have moved on from delivering babies to packages ...</a><p>Aug 1, 2016</p></li>
										   <li><a href="single.jsp">John Abraham, Sonakshi Sinha and Tahir ...</a><p>Sep 29, 2016</p></li>
										   <li><a href="single.jsp">John Abraham, Sonakshi Sinha and Tahir ...</a><p>Sep 29, 2016</p></li>
										</ul>
										<h4 class="side-t-w3l-agile">Latest <span>Trailer</span></h3>
										<div class="video_agile_player sidebar-player">
										            <div class="video-grid-single-page-agileits">
														<div data-video="fNKUgX8PhMA" id="video1"> <img src="images/22.jpg" alt="" class="img-responsive"> <div id="play"></div></div>
													</div>


										        <div class="player-text side-bar-info">
												<p class="fexi_header">Me Before You </p>
												<p class="fexi_header_para"><span class="conjuring_w3">Story Line<label>:</label></span>Me Before You Official Trailer #2 (2016) - Emilia Clarke, Sam Claflin Movie HD

A girl in a small town forms an unlikely....</p>
												<p class="fexi_header_para"><span>Release On<label>:</label></span>Feb 3, 2016 </p>
												<p class="fexi_header_para">
													<span>Genres<label>:</label> </span>
													<a href="genre.jsp">Drama</a> | 
													<a href="genre.jsp">Adventure</a> | 
													<a href="genre.jsp">Family</a>								
												</p>
												<p class="fexi_header_para fexi_header_para1"><span>Star Rating<label>:</label></span>
													<a href="#"><i class="fa fa-star" aria-hidden="true"></i></a>
													<a href="#"><i class="fa fa-star" aria-hidden="true"></i></a>
													<a href="#"><i class="fa fa-star-half-o" aria-hidden="true"></i></a>
													<a href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a>
													<a href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a>
												</p>
											</div>

										    </div>
											<div class="clearfix"> </div>
											<div class="agile-info-recent">
											<h4 class="side-t-w3l-agile">Latest <span>Trailer</span></h4>
											<div class="w3ls-recent-grids">
												<div class="w3l-recent-grid">
													<div class="wom">
														<a href="single.jsp"><img src="images/m12.jpg" alt=" " class="img-responsive"></a>
													</div>
													<div class="wom-right">
														<h5><a href="single.jsp">Lorem Integer rutrum</a></h5>
														<p>Mauris fermentum dictum magna. Sed laoreet aliquam leo. 
															Ut tellus dolor, dapibus eget.</p>
															<ul class="w3l-sider-list">
																<li><i class="fa fa-clock-o" aria-hidden="true"></i>On Jan 12, 2016</li>
																<li><i class="fa fa-eye" aria-hidden="true"></i> 2602</li>
															</ul>
													</div>
													<div class="clearfix"> </div>
												</div>
												<div class="w3l-recent-grid">
													<div class="wom">
														<a href="single.jsp"><img src="images/m13.jpg" alt=" " class="img-responsive"></a>
													</div>
													<div class="wom-right">
															<h5><a href="single.jsp">Lorem Integer rutrum</a></h5>
														<p>Mauris fermentum dictum magna. Sed laoreet aliquam leo. 
															Ut tellus dolor, dapibus eget.</p>
															<ul class="w3l-sider-list">
																<li><i class="fa fa-clock-o" aria-hidden="true"></i>On Mar 3, 2016</li>
																<li><i class="fa fa-eye" aria-hidden="true"></i> 2742</li>
															</ul>
													</div>
													<div class="clearfix"> </div>
												</div>
												<div class="w3l-recent-grid">
													<div class="wom">
														<a href="single.jsp"><img src="images/m14.jpg" alt=" " class="img-responsive"></a>
													</div>
													<div class="wom-right">
															<h5><a href="single.jsp">Lorem Integer rutrum</a></h5>
														    <p>Mauris fermentum dictum magna. Sed laoreet aliquam leo. 
															Ut tellus dolor, dapibus eget.</p>
															<ul class="w3l-sider-list">
																<li><i class="fa fa-clock-o" aria-hidden="true"></i>On Oct 13, 2016</li>
																<li><i class="fa fa-eye" aria-hidden="true"></i> 2802</li>
															</ul>
													</div>
													<div class="clearfix"> </div>
												</div>
											</div>
										</div>
										
							       </div>
								   <div class="clearfix"></div>
							   </div>
					
						</div>
				</div>
			<!--//content-inner-section-->
			<c:if test="${! empty msg }">
				<script type="text/javascript">
					alert('${msg}');
				</script>
			</c:if>

	<!--/footer-bottom-->
	<%@ include file="footer.jsp"%>

 

</body>
</html>