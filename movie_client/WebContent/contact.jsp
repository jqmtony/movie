<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Contact</title>
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
<!--/main-header-->
  <!--/banner-section-->
	<%@ include file="head.jsp"%>
			<!--/content-inner-section-->
				<div class="w3_content_agilleinfo_inner">
					<div class="agile_featured_movies">
							<div class="inner-agile-w3l-part-head">
					            <h3 class="w3l-inner-h-title">Contact</h3>
								<p class="w3ls_head_para">Add short Description</p>
							</div>
						<div class="w3_mail_grids">
								<form action="#" method="post">
									<div class="col-md-6 w3_agile_mail_grid">
										<span class="input input--ichiro">
											<input class="input__field input__field--ichiro" type="text" id="input-25" placeholder=" " required="">
											<label class="input__label input__label--ichiro" for="input-25">
												<span class="input__label-content input__label-content--ichiro">Your Name</span>
											</label>
										</span>
										<span class="input input--ichiro">
											<input class="input__field input__field--ichiro" type="email" id="input-26" placeholder=" " required="">
											<label class="input__label input__label--ichiro" for="input-26">
												<span class="input__label-content input__label-content--ichiro">Your Email</span>
											</label>
										</span>
										<span class="input input--ichiro">
											<input class="input__field input__field--ichiro" type="text" id="input-27" placeholder=" " required="">
											<label class="input__label input__label--ichiro" for="input-27">
												<span class="input__label-content input__label-content--ichiro">Your Phone Number</span>
											</label>
										</span>
										
									</div>
									<div class="col-md-6 w3_agile_mail_grid two">
										<textarea name="Message" placeholder="Your Message" required=""></textarea>
										<input type="submit" value="Submit">
									</div>
									<div class="clearfix"> </div>
								</form>
								<div class="agileits-get-us">
									<ul>
										<li><i class="fa fa-map-marker" aria-hidden="true"></i>United States,Utah 10009, USA</li>
										<li><i class="fa fa-phone" aria-hidden="true"></i>  +033 111 222 4567</li>
										<li><i class="fa fa-envelope" aria-hidden="true"></i><a href="mailto:info@example.com"> mail@example.com</a></li>
									</ul>
								
							</div>
					
					</div>
					</div>
				</div>
			<!--//content-inner-section-->


<!--/footer-bottom-->
	<%@ include file="footer.jsp"%>
 

</body>
</html>