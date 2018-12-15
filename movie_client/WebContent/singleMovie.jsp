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
					<li><a href="index.jsp">${lg['indexTitle'] }</a><i>//</i></li>
					<li>${lg['single'] }</li>
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
														<div data-video="f2Z65fobH2I" id="video"> <img src="${pageContext.request.contextPath }${singleShow.imgList[2].imgPath }" alt="这里的图片不存在" class="img-responsive" /> </div>
													</div>
													 <h4>
													 	${lg['protagonist'] } ： <%--这里需要判断主演是否只有一个 --%>
													 	<c:choose>
													 		<c:when test="${fn:length(singleShow.proList) eq 1}">
													 			<a href="${singleShow.proList[fn:length(singleShow.proList)-1].proLink }">${singleShow.proList[fn:length(singleShow.proList)-1].proName }</a>
													 		</c:when>
													 		<c:when test="${fn:length(singleShow.proList) eq 2}">
													 			<a href="${singleShow.proList[fn:length(singleShow.proList)-2].proLink }">${singleShow.proList[fn:length(singleShow.proList)-2].proName }</a> |
													 			<a href="${singleShow.proList[fn:length(singleShow.proList)-1].proLink }">${singleShow.proList[fn:length(singleShow.proList)-1].proName }</a>
													 		</c:when>
													 		<c:otherwise>
													 			<c:forEach items="${singleShow.proList }" var="pro" begin="0" end="${fn:length(singleShow.proList)-2}">
															 		<a href="${pro.proLink }">${pro.proName }</a> |
															 	</c:forEach>
															 	<a href="${singleShow.proList[fn:length(singleShow.proList)-1].proLink }">${singleShow.proList[fn:length(singleShow.proList)-1].proName }</a>
													 		</c:otherwise>
													 	</c:choose>
														<i class="fa fa-eye" aria-hidden="true"><font style="font-size:15px;">&nbsp;${singleShow.movieVisitCount }</font></i>
														<a href="<c:url value='movie.s?method=goBallotTicket&movieId=${singleShow.movieId }' />"><i id="goShopping" class="fa fa-shopping-cart" aria-hidden="true" style="cursor:pointer;color:#f83;margin-left:20px;"><font style="font-size:15px;"> ${lg['buyTicket'] }</font></i></a>
														<i id="giveALike" class="fa fa-heart-o" aria-hidden="true" style="cursor:pointer;margin-left:20px;" onclick="giveALike()"><font style="font-size:17px;"> ${lg['like'] }</font></i><font id="giveALikeAdd"></font>
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
										<div class="response">
							<h4>${lg["Comment"] }</h4>
							<%--所有评论 --%>
							<c:if test="${fn:length(singleShow.commentList) eq 0}">
								<h2 style="color:#a3a">${lg['noCommentYetGrabTheCouch'] }</h2>
							</c:if>
							<c:set var="commenti" value="1"/>
							<c:forEach items="${singleShow.commentList }" var="comment">
							<div class="media response-info" style="border-left:7px solid#aabb55;border-top:7px solid#aabb55">
								<div class="media-left response-text-left">
									<a href="#">
										<img class="media-object" src="<c:url value='${comment.user.imgList[0].imgPath }' />" alt="" style="border:1px solid #000;width:100px;height:100;">
									</a>
									<h5><a href="#">${comment.user.userAccount }</a></h5>
								</div>
								<div class="media-body response-text-right">
									<p>${comment.commentContent }</p>
									<ul>
										<li>(${commenti} ${lg["tower"] }) </li>
										<c:set var="commenti" value="${commenti+1 }"/>
										<li>${comment.commentCreateTime }</li>
										<li><a href="javascript:sendReplyToCommentToggle(${comment.commentId})"><i class="fa fa-reply" aria-hidden="true"></i> ${lg["Reply"] }(${comment.replyNum })</a></li>
									</ul>
									
									
									<%--该评论的所有回复 --%>
									<div id="showReply${comment.commentId }" style="display:none;">
									<c:forEach items="${comment.replyList }" var="reply">
									<div class="media response-info" style="border-left:6px solid#aabb55;border-top:6px solid#aabb55">
										<div class="media-left response-text-left">
											<a href="#">
												<img class="media-object" src="<c:url value='${comment.user.imgList[0].imgPath }' />" alt="" style="border:1px solid #000;width:100px;height:100;">
											</a>
											<h5><a href="#">${reply.user.userAccount } ${lg['Reply'] } ${comment.user.userAccount }</a></h5>
										</div>
										<div class="media-body response-text-right">
											<p>${reply.replyContent }</p>
											<ul>
												<li>${reply.replyCreateTime }</li> <%--这是评论下的第一级 --%>
												<li><a href="javascript:sendReplyToggle(${reply.replyId })"><i class="fa fa-reply" aria-hidden="true"></i> ${lg["Reply"] }(${reply.replyNum })</a></li>
											</ul>	
										</div>
										<%--该评论所有的回复 --%>
										<div class="all-comments-info" style="width:600px; display:none;" id="reply${reply.replyId }"> 
											 <h5 >${lg["addReply"] } ${reply.user.userAccount }  <font id="errorMsgBySonReply${reply.replyId }" style="font-size:12px;color:red;"></font></h5>
											<div class="agile-info-wthree-box">
												   <div class="col-md-6 form-info">
												   <textarea id="sonreplyContent${reply.replyId }" placeholder="${lg['Message'] }" required="" style="width:500px;height:70px;"></textarea>
												   <input type="button" value="${lg['send'] }" onclick="sendReplyToReplay(${reply.replyId})" style="margin-left:330px;width:150px;height:40px;background-color:#06f;color:#fff;">
												  </div>
												 <div class="clearfix"> </div>
											</div>
										</div>
												<%---------------------------下面这一段是回复的所有回复----------------------------- --%>
												<c:forEach items="${reply.replySonList }" var="sonReply">
													<div class="media response-info" style="margin-left:50px;border-left:5px solid#aabb55;border-top:5px solid#aabb55">
														<div class="media-left response-text-left">
															<a href="#">
																<img class="media-object" src="<c:url value='${comment.user.imgList[0].imgPath }' />" alt="" style="border:1px solid #000;width:100px;height:100;">
															</a>
															<h5><a href="#">${sonReply.user.userAccount } ${lg['Reply'] } ${reply.user.userAccount }</a></h5>
														</div>
														<div class="media-body response-text-right">
															<p>${sonReply.replyContent }</p>
															<ul>
																<li>${sonReply.replyCreateTime }</li>
																<li><a href="javascript:sendReplyToReplyToggle(${sonReply.replyId })"><i class="fa fa-reply" aria-hidden="true"></i> ${lg["Reply"] }(${sonReply.replyNum })</a></li>
															</ul>	
														</div>
														<%--该回复所有的回复 --%>
														<div class="all-comments-info" style="width:600px; display:none;" id="sonReply${sonReply.replyId }"> 
															 <h5 >${lg["addReply"] } ${sonReply.user.userAccount }  <font id="errorMsgBySonReply${sonReply.replyId }" style="font-size:12px;color:red;"></font></h5>
															<div class="agile-info-wthree-box">
																   <div class="col-md-6 form-info">
																   <textarea id="sonreplyContent${sonReply.replyId }" placeholder="${lg['Message'] }" required="" style="width:500px;height:70px;"></textarea>
																   <input type="button" value="${lg['send'] }" onclick="sendReplyToReplay(${sonReply.replyId})" style="margin-left:330px;width:150px;height:40px;background-color:#06f;color:#fff;">
																  </div>
																 <div class="clearfix"> </div>
															</div>
														</div>
														<%--====================回复下的回复1=================================== --%>
														<c:forEach items="${sonReply.replySonList }" var="sonReply1">
													<div class="media response-info" style="margin-left:50px;border-left:4px solid#aabb55;border-top:4px solid#aabb55">
														<div class="media-left response-text-left">
															<a href="#">
																<img class="media-object" src="<c:url value='${comment.user.imgList[0].imgPath }' />" alt="" style="border:1px solid #000;width:100px;height:100;">
															</a>
															<h5><a href="#">${sonReply1.user.userAccount } ${lg['Reply'] } ${sonReply.user.userAccount }</a></h5>
														</div>
														<div class="media-body response-text-right">
															<p>${sonReply1.replyContent }</p>
															<ul>
																<li>${sonReply1.replyCreateTime }</li>
																<li><a href="javascript:sendReplyToReplyToggle(${sonReply1.replyId })"><i class="fa fa-reply" aria-hidden="true"></i> ${lg["Reply"] }(${sonReply1.replyNum })</a></li>
															</ul>	
														</div>
														<%--该回复所有的回复 --%>
														<div class="all-comments-info" style="width:600px; display:none;" id="sonReply${sonReply1.replyId }"> 
															 <h5 >${lg["addReply"] } ${sonReply.user.userAccount }  <font id="errorMsgBySonReply${sonReply1.replyId }" style="font-size:12px;color:red;"></font></h5>
															<div class="agile-info-wthree-box">
																   <div class="col-md-6 form-info">
																   <textarea id="sonreplyContent${sonReply1.replyId }" placeholder="${lg['Message'] }" required="" style="width:500px;height:70px;"></textarea>
																   <input type="button" value="${lg['send'] }" onclick="sendReplyToReplay(${sonReply1.replyId})" style="margin-left:330px;width:150px;height:40px;background-color:#06f;color:#fff;">
																  </div>
																 <div class="clearfix"> </div>
															</div>
														</div>
														<%--====================回复下的回复2=================================== --%>
														<c:forEach items="${sonReply1.replySonList }" var="sonReply2">
													<div class="media response-info" style="margin-left:50px;border-left:3px solid#aabb55;border-top:3px solid#aabb55">
														<div class="media-left response-text-left">
															<a href="#">
																<img class="media-object" src="<c:url value='${comment.user.imgList[0].imgPath }' />" alt="" style="border:1px solid #000;width:100px;height:100;">
															</a>
															<h5><a href="#">${sonReply2.user.userAccount } ${lg['Reply'] } ${sonReply1.user.userAccount }</a></h5>
														</div>
														<div class="media-body response-text-right">
															<p>${sonReply2.replyContent }</p>
															<ul>
																<li>${sonReply2.replyCreateTime }</li>
																<li><a href="javascript:sendReplyToReplyToggle(${sonReply2.replyId })"><i class="fa fa-reply" aria-hidden="true"></i> ${lg["Reply"] }(${sonReply2.replyNum })</a></li>
															</ul>	
														</div>
														<%--该回复所有的回复 --%>
														<div class="all-comments-info" style="width:600px; display:none;" id="sonReply${sonReply1.replyId }"> 
															 <h5 >${lg["addReply"] } ${sonReply1.user.userAccount }  <font id="errorMsgBySonReply${sonReply1.replyId }" style="font-size:12px;color:red;"></font></h5>
															<div class="agile-info-wthree-box">
																   <div class="col-md-6 form-info">
																   <textarea id="sonreplyContent${sonReply2.replyId }" placeholder="${lg['Message'] }" required="" style="width:500px;height:70px;"></textarea>
																   <input type="button" value="${lg['send'] }" onclick="sendReplyToReplay(${sonReply2.replyId})" style="margin-left:330px;width:150px;height:40px;background-color:#06f;color:#fff;">
																  </div>
																 <div class="clearfix"> </div>
															</div>
														</div>
														<div class="clearfix"> </div>
													</div>
												</c:forEach>
													<%--============================================================================================ --%>
														<div class="clearfix"> </div>
													</div>
												</c:forEach>
													<%--============================================================================================ --%>
														<div class="clearfix"> </div>
													</div>
												</c:forEach>
											<%---------------------------上面这一段是回复的所有回复----------------------------- --%>
										<div class="clearfix"> </div>
									</div>
									</c:forEach>
									
									</div>
									
									
								</div>
								<input id="onclickShowReply${comment.commentId }" type="button" value="点击查看所有回复" onclick="clickShowReply(${comment.commentId })" style="margin-left:500px;">
								
								<%--回复评论 --%>
								<div class="all-comments-info" style="width:600px; display:none;" id="replyComment${comment.commentId }"> 
									 <h5 >${lg["addReply"] } ${comment.user.userAccount }  <font id="errorMsgByReplyComment${comment.commentId }" style="font-size:12px;color:red;"></font></h5>
									<div class="agile-info-wthree-box">
										   <div class="col-md-6 form-info">
										   <textarea id="replyCommentContent${comment.commentId }" placeholder="${lg['Message'] }" required="" style="width:500px;height:70px;"></textarea>
										   <input type="button" value="${lg['send'] }" onclick="sendReplyToComment(${comment.commentId })" style="margin-left:330px;width:150px;height:40px;background-color:#06f;color:#fff;">
										  </div>
										 <div class="clearfix"> </div>
									</div>
								</div> 
									
									
								<div class="clearfix"> </div>
							</div>
							</c:forEach>
						</div>
						
								 <%--发表本电影的评论  （已实现）--%>		
								 <div class="all-comments-info">
									 <h5 >${lg["addComment"] }  <font id="errorMsg" style="font-size:12px;color:red;"></font></h5>
									<div class="agile-info-wthree-box">
										   <input type="hidden" id="commentMovieId" value="${singleShow.movieId }">
										   <div class="col-md-6 form-info">
										   <textarea id="commentContent" placeholder="${lg['Message'] }" required="" style="width:700px;"></textarea>
										   <input type="button" value="${lg['send'] }" onclick="sendComment()" style="margin-left:530px;width:150px;height:40px;background-color:#06f;color:#fff;">
										  </div>
										 <div class="clearfix"> </div>
									</div>
								</div> 
								   </div>
								   <div class="col-md-4 latest-news-agile-right-content">
										<h4 class="side-t-w3l-agile">${lg['Requested'] } <span>${lg['Movies'] }</span></h4>
										<div class="video_agile_player sidebar-player">
										            <div class="video-grid-single-page-agileits">
														<a href="<c:url value='/movie.s?method=singleShow&type=movie&id=${movieList2[indexVisit2[0]].movieId }' />">
															<img src="<c:url value='${movieList2[indexVisit2[0]].imgList[2].imgPath}' />" alt="" class="img-responsive">
														</a>
													</div>
 

										        <div class="player-text side-bar-info">
												<p class="fexi_header">${movieList2[indexVisit2[0]].movieName } </p>
												<p class="fexi_header_para"><span class="conjuring_w3">${lg['indexNewOneStoryLine'] }<label>:</label></span>${movieList2[indexVisit2[0]].movieDescribe }</p>
												<p class="fexi_header_para"><span>${lg['indexNewOneReleaseOn'] }<label>:</label></span>${fn:substring(movieList2[indexVisit2[0]].movieCreateTime,0,10) }</p>
												<p class="fexi_header_para">
													<span>${lg['indexNewOneGenres'] }<label>:</label> </span>
													${movieList2[indexVisit2[0]].classifysList[0].classifyNameObj.classifyNameString }  <%--先把第一个打印出来 --%>
													<c:forEach items="${movieList2[indexVisit2[0]].classifysList}" var="bean" begin="1">
														 | ${bean.classifyNameObj.classifyNameString }
													</c:forEach>								
												</p>
												<p class="fexi_header_para fexi_header_para1"><span>${lg["indexNewOneStarRating"] }<label>:</label></span>
													<c:set var="starNum" value="${movieList2[indexVisit2[0]].movieGradeNum/2}"/>
													<c:forEach var="i" begin="1" end="${starNum }">
														<a><i class="fa fa-star" aria-hidden="true"></i></a>
													</c:forEach>
													<c:if test="${starNum%1 != 0}">
														<a><i class="fa fa-star-half-o" aria-hidden="true"></i></a>
														<c:set var="starNum" value="${starNum+1}"/>
													</c:if>
													<c:forEach var="i" begin="${starNum+1}" end="5">
														<a><i class="fa fa-star-o" aria-hidden="true"></i></a>
													</c:forEach>
												</p>
											</div>

										    </div>
											<div class="clearfix"> </div>
											<div class="agile-info-recent">
											<div class="w3ls-recent-grids">
											<c:forEach items="${indexVisit2}" var="index" begin="1" end="3">
												<div class="w3l-recent-grid">
													<div class="wom">
														<a href="<c:url value='/movie.s?method=singleShow&type=movie&id=${movieList2[index].movieId }' />"><img src="<c:url value='${movieList2[index].imgList[0].imgPath}' />" alt=" " class="img-responsive"></a>
													</div>
													<div class="wom-right">
														<h5><a href="<c:url value='/movie.s?method=singleShow&type=movie&id=${movieList2[index].movieId }' />">${movieList2[index].movieName }</a></h5>
														<p>${movieList2[index].movieDescribe }</p>
															<ul class="w3l-sider-list">
																<li><i class="fa fa-clock-o" aria-hidden="true"></i>${fn:substring(movieList2[index].movieCreateTime,0,10) }</li>
																<li><i class="fa fa-eye" aria-hidden="true"></i> ${movieList2[index].movieVisitCount }</li>
															</ul>
													</div>
													<div class="clearfix"> </div>
												</div>
											</c:forEach>
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
<script type="text/javascript">
	//发表评论
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
 				alert("你还没有登录，不能发表评论，请先登录！");
 				window.location.href = "userLogin.jsp";
 			}else{
 				$('#errorMsg').html("   * "+data+"!");
 			}
 		});
 	}
	
	//发表回复的回复框的显示  
	function sendReplyToggle(str){
		//ajax校验是否已经登录
		var data;
		$.post("<c:url value='/user.s?method=isLogin' />",data,function(data){
			if(data === "yes"){
				var replyObj = $('#reply'+str);
				replyObj.toggle();
			}else if(data === "notLogin"){
				alert("你还没有登录，不能发表回复，请先登录！");
				window.location.href = "userLogin.jsp";
			}
		});
	} 
	
	//发送回复给评论的框的显示控制  str是当前的评论ID  用来生成每一个
	function sendReplyToCommentToggle(str){
		var data;
		$.post("<c:url value='/user.s?method=isLogin' />",data,function(data){
			if(data === "yes"){
				var replyCommentObj = $('#replyComment'+str);
				replyCommentObj.toggle();
			}else if(data === "notLogin"){
				alert("你还没有登录，不能发表回复，请先登录！");
				window.location.href = "userLogin.jsp";
			}
		});
	}
	
	//给回复发送回复的框显示控制
	function sendReplyToReplyToggle(str){
		var data;
		$.post("<c:url value='/user.s?method=isLogin' />",data,function(data){
			if(data === "yes"){
				var replyCommentObj = $('#sonReply'+str);
				replyCommentObj.toggle();
			}else if(data === "notLogin"){
				alert("你还没有登录，不能发表回复，请先登录！");
				window.location.href = "userLogin.jsp";
			}
		});
	}
	
	///给评论发送回复   （已完成）
	function sendReplyToComment(commentID){
		var errorBox = $('#errorMsgByReplyComment'+commentID);
		var content = $('#replyCommentContent'+commentID).val();
		var data = {
 				replyContent : content,
 				replyCommentId : commentID
 		};
		$.post("<c:url value='/movie.s?method=sendReplyToComment' />",data,function(data){
 			if(data === "yes"){
 				history.go(0);
 			}else{
 				errorBox.html("   * "+data+"!");
 			}
 		});
	}
	
	//给回复发送回复
	function sendReplyToReplay(replyID){
		var errorBox = $('#errorMsgBySonReply'+replyID);
		var content = $('#sonreplyContent'+replyID).val();
		var data = {
 				replyContent : content,
 				replyId : replyID
 		};
		$.post("<c:url value='/movie.s?method=sendReplyToReply' />",data,function(data){
 			if(data === "yes"){
 				history.go(0);
 			}else{
 				errorBox.html("   * "+data+"!");
 			}
 		});
	}
	
	//点击显示所有评论
	function clickShowReply(commentID){
		var box = $('#showReply'+commentID);
		var btn = $('#onclickShowReply'+commentID);
		if(btn.val() === "点击查看所有回复"){
			btn.val("点击收起所有回复");
		}else{
			btn.val("点击查看所有回复");
		}
		box.toggle();
	}
	var i = 1;
	var flag = true;
	//点赞
	function giveALike(){
		if(!flag){
			alert("您点击的太快了，请稍后再试！")
			setTimeout(function(){
				flag = true;
			},3000);
			return;
		}
		flag = false;
		var data;
		$.post("<c:url value='/user.s?method=isLogin' />",data,function(data){
			if(data === "yes"){
				var btn = $('#giveALike');
				var msg = $('#giveALikeAdd');
				//alert(btn.attr("class"))
				if(btn.attr("class") == "fa fa-heart-o"){
					btn.attr("class","fa fa-heart");
					msg.html(' + '+i);
					i++;
				}else{
					if(i > 10){
						alert('每个用户每次只能点赞10次')
						return;
					}
					msg.html(' + '+i);
					i++;
				}
				
				//将电影评分加到数据库中
				var da = {movieId : "${singleShow.movieId}"};
				$.post("<c:url value='/movie.s?method=addMovieGradeNum' />",da,function(data){
					if(data != "yes"){
						alert(data);
					}
				});
			}else if(data === "notLogin"){
				alert("你还没有登录，必须登录后才能点赞！");
				location.href = "userLogin.jsp";
			}
		});
	}
 </script>
	<!--/footer-bottom-->
	<%@ include file="footer.jsp"%>

 

</body>
</html>