<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="demo-1" class="banner-inner">
	 <div class="banner-inner-dott">
		<!--/header-w3l-->
			   <div class="header-w3-agileits" id="home">
			     <div class="inner-header-agile part2">	
				<nav class="navbar navbar-default">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<h1><a  href="index.html"><span>${lg["indexTitle_1"] }</span>${lg["indexTitle_2"] } <span>${lg["indexTitle_3"] }</span>${lg["indexTitle_4"] }</a></h1>
					</div>
					<!-- navbar-header -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
							<li class="active"><a href="index.jsp">${lg["indexMenuHome"]}</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">${lg["indexMenuGenre"] } <b class="caret"></b></a>
								<ul class="dropdown-menu multi-column columns-3">
									<li>
									<div class="col-sm-4">
										<ul class="multi-column-dropdown">
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreAction' />">${lg["indexMenuGenreAction"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreBiography' />">${lg["indexMenuGenreBiography"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreCrime' />">${lg["indexMenuGenreCrime"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreFamily' />">${lg["indexMenuGenreFamily"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreHorror' />">${lg["indexMenuGenreHorror"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreRomance' />">${lg["indexMenuGenreRomance"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreSports' />">${lg["indexMenuGenreSports"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreWar' />">${lg["indexMenuGenreWar"] }</a></li>
										</ul>
									</div>
									<div class="col-sm-4">
										<ul class="multi-column-dropdown">
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreAdventure' />">${lg["indexMenuGenreAdventure"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreComedy' />">${lg["indexMenuGenreComedy"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreDocumentary' />">${lg["indexMenuGenreDocumentary"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreFantasy' />">${lg["indexMenuGenreFantasy"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreThriller' />">${lg["indexMenuGenreThriller"] }</a></li>
										</ul>
									</div>
									<div class="col-sm-4">
										<ul class="multi-column-dropdown">
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreAnimation' />">${lg["indexMenuGenreAnimation"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreCostume' />">${lg["indexMenuGenreCostume"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreDrama' />">${lg["indexMenuGenreDrama"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreHistory' />">${lg["indexMenuGenreHistory"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreMusical' />">${lg["indexMenuGenreMusical"] }</a></li>
											<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenrePsychological' />">${lg["indexMenuGenrePsychological"] }</a></li>
										</ul>
									</div>
									<div class="clearfix"></div>
									</li>
								</ul>
							</li>
							<li><a href="series.jsp">${lg["indexMenuTvSeries"]}</a></li>
							<li><a href="news.jsp">${lg["indexMenuNews"]}</a></li>
							<li><a href="contact.jsp">${lg["indexMenuContact"]}</a></li>
							<li>
								<select style="margin:13px 10px 0 10px; width:80px;cursor:pointer;" id="languageChose">
								<c:choose>
									<c:when test="${lgType eq 'zh'}">
										<option name="china" selected="selected">中文</option>
										<option name="english">English</option>
									</c:when>
									<c:otherwise>
										<option name="china">中文</option>
										<option name="english" selected="selected">English</option>
									</c:otherwise>
								</c:choose>
								</select><font style="color:#ffffff;">${lg["language"]}</font>
							</li>
						</ul>

					</div>
					<div class="clearfix"> </div>	
				</nav>
					<div class="w3ls_search">
									<div class="cd-main-header">
										<ul class="cd-header-buttons">
											<li><a class="cd-search-trigger" href="#cd-search"> <span></span></a></li>
										</ul> <!-- cd-header-buttons -->
									</div>
										<div id="cd-search" class="cd-search">
											<form action="#" method="post">
												<input name="Search" type="search" placeholder="Search...">
											</form>
										</div>
								</div>
	
			</div> 

			   </div>
		<!--//header-w3l-->
		</div>
    </div>
  <!--/banner-section-->
 <!--//main-header-->
	         <!--/banner-bottom-->
			   <div class="w3_agilits_banner_bootm">
			     <div class="w3_agilits_inner_bottom">
			            <div class="col-md-6 wthree_agile_login">
						     <ul>
									<li style="margin-right:50px;"><i class="fa fa-phone" aria-hidden="true"></i> (+000) 009 455 4088</li>
									<c:choose>
										<c:when test="${empty loginedUser }">
											<li><a href="<c:url value='/user.s?method=loginSetReferer' />" class="login">${lg["indexLogin"]}</a></li>
										</c:when>
										<c:when test="${fn:length(loginedUser.imgList) eq 0 }">
											<li style="margin-left:30px;">
												<img id="headImg" style="width:20px;height:20px;" src="<c:url value='/images/uploadLogo.png' />" alt="图片">
												<font>${loginedUser.userAccount}</font>
											</li>
											<li style="margin-left:20px;">
												<select id="infoChange" style="color:#000000;" onchange="infoChange()">
													<option selected="selected">${lg['personalCenter'] }</option>
													<option>${lg['alterInfo'] }</option>
													<option>${lg['myIndent'] }</option>
													<option>${lg['loginOut'] }</option>
												</select>
											</li>
										</c:when>
										<c:otherwise>
											<li style="margin-left:30px;">
												<img id="headImg" style="width:20px;height:20px;" src="<c:url value='${loginedUser.imgList[0].imgPath }' />" alt="图片">
												<font>${loginedUser.userAccount}</font>
											</li>
											<li style="margin-left:20px;">
												<select id="infoChange" style="color:#000000;" onchange="infoChange()">
													<option selected="selected">${lg['personalCenter'] }</option>
													<option>${lg['alterInfo'] }</option>
													<option>${lg['myIndent'] }</option>
													<option>${lg['loginOut'] }</option>
												</select>
											</li>
										</c:otherwise>
									</c:choose>
									<%-- <li><a href="#" class="login"  data-toggle="modal" data-target="#myModal4">${lg["indexLogin"]}</a></li> --%>
									<%-- <li><a href="#" class="login reg"  data-toggle="modal" data-target="#myModal5">${lg["indexRegister"]}</a></li> --%>

								</ul>
						</div>
						 
				</div>
			</div>
			<!--//banner-bottom-->
		     
			
			