<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
											<li><a href="genre.jsp">${lg["indexMenuGenreAction"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreBiography"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreCrime"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreFamily"] }</a></li>
											<li><a href="horror.jsp">${lg["indexMenuGenreHorror"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreRomance"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreSports"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreWar"] }</a></li>
										</ul>
									</div>
									<div class="col-sm-4">
										<ul class="multi-column-dropdown">
											<li><a href="genre.jsp">${lg["indexMenuGenreAdventure"] }</a></li>
											<li><a href="comedy.jsp">${lg["indexMenuGenreComedy"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreDocumentary"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreFantasy"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreThriller"] }</a></li>
										</ul>
									</div>
									<div class="col-sm-4">
										<ul class="multi-column-dropdown">
											<li><a href="genre.jsp">${lg["indexMenuGenreAnimation"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreCostume"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreDrama"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreHistory"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenreMusical"] }</a></li>
											<li><a href="genre.jsp">${lg["indexMenuGenrePsychological"] }</a></li>
										</ul>
									</div>
									<div class="clearfix"></div>
									</li>
								</ul>
							</li>
							<li><a href="series.jsp">${lg["indexMenuTvSeries"]}</a></li>
							<li><a href="news.jsp">${lg["indexMenuNews"]}</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">${lg["indexMenuCountry"]}<b class="caret"></b></a>
								<ul class="dropdown-menu multi-column columns-3">
									<li>
										<div class="col-sm-4">
											<ul class="multi-column-dropdown">
												<li><a href="genre.jsp">${lg["indexMenuCountryAsia"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryFrance"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryTaiwan"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryUnitedStates"]}</a></li>
											</ul>
										</div>
										<div class="col-sm-4">
											<ul class="multi-column-dropdown">
												<li><a href="genre.jsp">${lg["indexMenuCountryChina"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryHongCong"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryJapan"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryThailand"]}</a></li>
											</ul>
										</div>
										<div class="col-sm-4">
											<ul class="multi-column-dropdown">
												<li><a href="genre.jsp">${lg["indexMenuCountryEuro"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryIndia"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryKorea"]}</a></li>
												<li><a href="genre.jsp">${lg["indexMenuCountryUnitedKingdom"]}</a></li>
											</ul>
										</div>
										<div class="clearfix"></div>
									</li>
								</ul>
							</li>
							<li><a href="list.jsp">${lg["indexMenuAZList"]}</a></li>
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
									<li><i class="fa fa-phone" aria-hidden="true"></i> (+000) 009 455 4088</li>
									<c:choose>
										<c:when test="${empty loginedUser }">
											<li><a href="userLogin.jsp" class="login">${lg["indexLogin"]}</a></li>
										</c:when>
										<c:otherwise>
											<li style="margin-left:30px;">
												<img id="headImg" src="" alt="图片">
												<font>${loginedUser.userAccount}</font>
											</li>
											<li style="margin-left:20px;">
												<select id="infoChange" style="color:#000000;" onchange="infoChange()">
													<option selected="selected">个人中心</option>
													<option>修改信息</option>
													<option>退出登录</option>
												</select>
											</li>
										</c:otherwise>
									</c:choose>
									<%-- <li><a href="#" class="login"  data-toggle="modal" data-target="#myModal4">${lg["indexLogin"]}</a></li> --%>
									<%-- <li><a href="#" class="login reg"  data-toggle="modal" data-target="#myModal5">${lg["indexRegister"]}</a></li> --%>

								</ul>
						</div>
						 <div class="col-md-6 wthree_share_agile">
									
									<div class="single-agile-shar-buttons">
									<ul>
								<li>
									<div class="fb-like" data-href="" data-layout="button_count" data-action="like" data-size="small" data-show-faces="false" data-share="false"></div>
									
								</li>
								<li>
									<div class="fb-share-button" data-href="" data-layout="button_count" data-size="small" data-mobile-iframe="true"><a class="fb-xfbml-parse-ignore" target="_blank" href="">${lg["indexShare"]}</a></div>
								</li>
								
							</ul>
								</div>
						</div>
				</div>
			</div>
			<!--//banner-bottom-->
		     <!-- Modal1 -->
					<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" >

							<div class="modal-dialog">
							<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4>${lg["indexLogin"]}</h4>
										<div class="login-form">
											<form action="#" method="post">
												<input type="email" name="email" placeholder='${lg["indexLoginEmail"]}' required="">
												<input type="password" name="password" placeholder='${lg["indexLoginPassword"]}' required="">
												<div class="tp">
													<input type="submit" value='${lg["indexLoginString"]}'>
												</div>
												<div class="forgot-grid">
												       <div class="log-check">
														<label class="checkbox"><input type="checkbox" name="checkbox">${lg["indexLoginRememberMe"]}</label>
														</div>
														<div class="forgot">
															<a href="#" data-toggle="modal" data-target="#myModal2">${lg["indexLoginForgotPassword"]}</a>
														</div>
														<div class="clearfix"></div>
													</div>
												
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
				<!-- //Modal1 -->
				  <!-- Modal1 -->
					<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" >

							<div class="modal-dialog">
							<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4>${lg["indexRegister"] }</h4>
										<div class="login-form">
											<form action="#" method="post">
											    <input type="text" name="name" placeholder='${lg["indexRegisterName"]}' required="">
												<input type="email" name="email" placeholder='${lg["indexLoginEmail"]}' required="">
												<input type="password" name="password" placeholder='${lg["indexLoginPassword"]}' required="">
												<input type="password" name="conform password" placeholder='${lg["indexRegisterConfirmPassword"] }' required="">
												<div class="signin-rit">
													<span class="agree-checkbox">
														<label class="checkbox"><input type="checkbox" name="checkbox">${lg["indexRegisterAgree1"] } <a class="w3layouts-t" href="#" target="_blank">${lg["indexRegisterAgree2"] }</a> ${lg["indexRegisterAgree3"] } <a class="w3layouts-t" href="#" target="_blank">${lg["indexRegisterAgree4"] }</a></label>
													</span>
												</div>
												<div class="tp">
													<input type="submit" value='${lg["indexRegisterString"] }'>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
				<!-- //Modal1 -->
			
			