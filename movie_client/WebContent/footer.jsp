<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="contact-w3ls" id="contact">
			<div class="footer-w3lagile-inner">
				<h2>${lg["foot_1"] } <span>${lg["foot_2"] }</span></h2>
				<p class="para">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vitae eros eget tellus 
					tristique bibendum. Donec rutrum sed sem quis venenatis.</p>
				<div class="footer-contact">
					<form action="#" method="post">
						<input type="email" name="Email" placeholder="${lg['foot_24'] }" required=" ">
						<input type="submit" value="${lg['foot_25']}">
					</form>
				</div>
					<div class="footer-grids w3-agileits">
						<div class="col-md-2 footer-grid">
						<h4>${lg["foot_3"] }</h4>
							<ul> 
								<li><a href="#" title="Release 2018">2018</a></li> 
								<li><a href="#" title="Release 2017">2017</a></li>
								<li><a href="#" title="Release 2016">2016</a></li> 
								<li><a href="#" title="Release 2015">2015</a></li> 
								<li><a href="#" title="Release 2014">2014</a></li>
								<li><a href="#" title="Release 2013">2013</a></li> 
							</ul>
						</div>
							<div class="col-md-2 footer-grid">
						<h4>${lg["Movies"] }</h4>
							<ul>
								
								<li><a href="genre.html">${lg["foot_4"] }</a></li>
								<li><a href="comedy.html">${lg["foot_5"] }</a></li>
								<li><a href="series.html">${lg["foot_6"] }</a></li>
								<li><a href="series.html">${lg["foot_7"] }  </a></li>
								<li><a href="genre.html">${lg["foot_8"] }  </a></li>
								<li><a href="horror.html">${lg["foot_9"] }  </a></li>
								
							</ul>
						</div>
				

							<div class="col-md-2 footer-grid">
								<h4>${lg["foot_10"] }</h4>
									<ul class="w3-tag2">
									<li><a href="comedy.html">${lg["foot_11"] }</a></li>
									<li><a href="horror.html">${lg["foot_12"] }</a></li>
									<li><a href="series.html">${lg["foot_13"] }</a></li>
									<li><a href="series.html">${lg["foot_14"] }</a></li>
									<li><a href="series.html">${lg["foot_15"] }</a></li>
									<li><a href="genre.html">${lg["foot_16"] }</a></li>
									<li><a href="single.html">${lg["foot_17"] }</a></li>
									<li><a href="comedy.html">${lg["foot_11"] }</a></li>
									<li><a href="horror.html">${lg["foot_12"] }</a></li>
									<li><a href="series.html">${lg["foot_13"] }</a></li>
									<li><a href="series.html">${lg["foot_14"] }</a></li>
									<li><a href="genre.html">${lg["foot_15"] }</a></li>
									<li><a href="comedy.html">${lg["foot_16"] }</a></li>
									<li><a href="horror.html">${lg["foot_17"] }</a></li>
									<li><a href="genre.html">${lg["foot_17"] }</a></li>
									
								</ul>


						</div>
								<div class="col-md-2 footer-grid">
						<h4>${lg["foot_18"] }</h4>
							<c:forEach items="${movieListByTime }" var="movie">
								<div class="footer-grid1">
									<div class="footer-grid1-left">
										<a href="single.html"><img src="${movie.imgList[0].imgPath }" alt=" " class="img-responsive"></a>
									</div>
									<div class="footer-grid1-right">
										<a href="single.html">${movie.movieName}</a>
										
									</div>
									<div class="clearfix"> </div>
								</div>
							</c:forEach>

						</div>
						<div class="col-md-2 footer-grid">
						   <h4 class="b-log"><a href="index.html"><span>${lg["indexTitle_1"] }</span>${lg["indexTitle_2"] } <span>${lg["indexTitle_3"] }</span>${lg["indexTitle_4"] }</a></h4>
							
							<c:forEach items="${movieListByCount }" var="movie">
								<div class="footer-grid-instagram">
									<a href="single.html"><img src="${movie.imgList[0].imgPath }" alt=" " class="img-responsive"></a>
								</div>
							</c:forEach>
							<div class="clearfix"> </div>
						</div>
						<div class="clearfix"> </div>
						<ul class="bottom-links-agile">
								<li><a href="icons.html" title="Font Icons">${lg["foot_19"] }</a></li> 
								<li><a href="short-codes.html" title="Short Codes">${lg["foot_20"] }</a></li> 
								<li><a href="contact.html" title="contact">${lg["foot_21"] }</a></li> 
								
							</ul>
					</div>
					<h3 class="text-center follow">${lg["foot_22"] } <span>${lg["foot_23"] }</span></h3>
						<ul class="social-icons1 agileinfo">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
							<li><a href="#"><i class="fa fa-youtube"></i></a></li>
							<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
						</ul>	
					
			 </div>
						
			</div>
			
		<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Dropdown-Menu-JavaScript -->
			<script>
				$(document).ready(function(){
					$(".dropdown").hover(            
						function() {
							$('.dropdown-menu', this).stop( true, true ).slideDown("fast");
							$(this).toggleClass('open');        
						},
						function() {
							$('.dropdown-menu', this).stop( true, true ).slideUp("fast");
							$(this).toggleClass('open');       
						}
					);
				});
			</script>
		<!-- //Dropdown-Menu-JavaScript -->


<script type="text/javascript" src="js/jquery.zoomslider.min.js"></script>
		<!-- search-jQuery -->
				<script src="js/main.js"></script>
			<script src="js/simplePlayer.js"></script>
			<script>
				$("document").ready(function() {
					$("#video").simplePlayer();
				});
			</script>
			<script>
				$("document").ready(function() {
					$("#video1").simplePlayer();
				});
			</script>
			<script>
				$("document").ready(function() {
					$("#video2").simplePlayer();
				});
			</script>
				<script>
				$("document").ready(function() {
					$("#video3").simplePlayer();
				});
			</script>

			<!-- pop-up-box -->  
		<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
	<!--//pop-up-box -->

			<div id="small-dialog1" class="mfp-hide">
			<iframe src=""></iframe>
	</div>
	<div id="small-dialog2" class="mfp-hide">
		<iframe src=""></iframe>
	</div>
	<script>
		$(document).ready(function() {
		$('.w3_play_icon,.w3_play_icon1,.w3_play_icon2').magnificPopup({
			type: 'inline',
			fixedContentPos: false,
			fixedBgPos: true,
			overflowY: 'auto',
			closeBtnInside: true,
			preloader: false,
			midClick: true,
			removalDelay: 300,
			mainClass: 'my-mfp-zoom-in'
		});
																		
		});
	</script>
<script src="js/easy-responsive-tabs.js"></script>
<script>
$(document).ready(function () {
	$('#horizontalTab').easyResponsiveTabs({
		type: 'default', //Types: default, vertical, accordion           
		width: 'auto', //auto or any width like 600px
		fit: true,   // 100% fit in a container
		closed: 'accordion', // Start closed if in accordion view
		activate: function(event) { // Callback function if tab is switched
		var $tab = $(this);
		var $info = $('#tabInfo');
		var $name = $('span', $info);
		$name.text($tab.text());
		$info.show();
	}
});
$('#verticalTab').easyResponsiveTabs({
type: 'vertical',
width: 'auto',
fit: true
});
});
</script>
<link href="css/owl.carousel.css" rel="stylesheet" type="text/css" media="all">
<script src="js/owl.carousel.js"></script>
<script>
	$(document).ready(function() { 
		$("#owl-demo").owlCarousel({
	 
		 autoPlay: 3000, //Set AutoPlay to 3 seconds
		  autoPlay : true,
		   navigation :true,

		  items : 5,
		  itemsDesktop : [640,4],
		  itemsDesktopSmall : [414,3]
	 
		});
	 
	}); 
</script> 

<!--/script-->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>

<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},900);
				});
			});
</script>
 <script type="text/javascript">
						$(document).ready(function() {
							/*
							var defaults = {
					  			containerID: 'toTop', // fading element id
								containerHoverID: 'toTopHover', // fading element hover id
								scrollSpeed: 1200,
								easingType: 'linear' 
					 		};
							*/
							
							$().UItoTop({ easingType: 'easeOutQuart' });
							
						});
					</script>
<!--end-smooth-scrolling-->
	<script src="js/bootstrap.js"></script>
	
	
	<%-- 选择语言事件 --%>
<script type="text/javascript">
	$(document).ready(function() {
		var selector = $('#languageChose');
		selector.on("change",function(){
			var name = $("option:selected",this).val();
			var data = {name:name};
			$.post("<c:url value='/sys.s?method=alterLanguage'/>",data,function(data){
				history.go(0);
			});
		});
		
	});
</script>