<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="contact-w3ls" id="contact">
			<div class="footer-w3lagile-inner">
				<h2>${lg["foot_1"] } <span>${lg["foot_2"] }</span></h2>
				<p class="para">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vitae eros eget tellus 
					tristique bibendum. Donec rutrum sed sem quis venenatis.</p>
				<div class="footer-contact">
					<%--订阅  wt=======================================================================2 163 136======== --%>
					<form action="" method="post">
						<input type="email" name="Email" id="subEmail" placeholder="${lg['foot_24'] }">
						<input type="button" value="${lg['foot_25']}" onclick="sub()" style="width:100px;height:45px;background-color:#02a388;color:#fff;"><br/>
						<font style="color:red;" id="subEmailErrorMsg"></font>
					</form>
					<script type="text/javascript">
						function sub(){
							var subEmailObj = $('#subEmail');   //这是subEmail对象
							var data1={/*定义一个js对象*/
									//name : 'zhangsan',   //name为属性 zhangsan为值
									email : subEmailObj.val()
							};
							$.post("<c:url value='/user.s?method=sub' />",data1,function(data2){   
								/*
									//localhost:8080/movie_client/user.s
									<c:url value='' />   :  他是一个jstl表达式   作用是自动补全url前面的项目名
								*/
								/*参数1：发送请求的地址  
								参数2：当前页面发送给servlet的数据 
								参数3：servlet返回给当前页面的数据*/
								
								if(data2 === "yes"){
									$("#subEmailErrorMsg").html("");
									alert("恭喜您订阅成功！我们以后有新的动态都会通过邮箱发送给您");
									subEmailObj.val("");
								}else{
									$("#subEmailErrorMsg").html(" * "+data2);
								}
									
							});
						}
					</script>
					<%--============================================================================= --%>
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
								<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreAdventure' />">${lg["indexMenuGenreAdventure"] }</a></li>
								<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreComedy' />">${lg["indexMenuGenreComedy"] }</a></li>
								<li><a href="<c:url value='/movie.s?method=goGenre&op=indexMenuGenreFantasy' />">${lg["indexMenuGenreFantasy"] }</a></li>
								<li><a href="<c:url value='/movie.s?method=goGenre&op=foot_7' />">${lg["foot_7"] }  </a></li>
								<li><a href="<c:url value='/movie.s?method=goGenre&op=foot_8' />">${lg["foot_8"] }  </a></li>
								<li><a href="<c:url value='/movie.s?method=goGenre&op=foot_9' />">${lg["foot_9"] }  </a></li>
							</ul>
						</div>
				

							<div class="col-md-2 footer-grid">
								<h4>${lg["foot_10"] }</h4>
									<ul class="w3-tag2">
									<li><a href="<c:url value='/movie.s?method=goGenre&op=foot_11' />">${lg["foot_11"] }</a></li>
									<li><a href="<c:url value='/movie.s?method=goGenre&op=foot_12' />">${lg["foot_12"] }</a></li>
									<li><a href="<c:url value='/movie.s?method=goGenre&op=foot_13' />">${lg["foot_13"] }</a></li>
									<li><a href="<c:url value='/movie.s?method=goGenre&op=foot_14' />">${lg["foot_14"] }</a></li>
									<li><a href="<c:url value='/movie.s?method=goGenre&op=foot_16' />">${lg["foot_16"] }</a></li>
								</ul>


						</div>
								<div class="col-md-2 footer-grid">
						<h4>${lg["foot_18"] }</h4>
							<c:forEach items="${indexTime2 }" var="index" end="3">
								<div class="footer-grid1">
									<div class="footer-grid1-left">
										<a href="<c:url value='/movie.s?method=singleShow&type=movie&id=${movieList2[index].movieId }' />"><img src="<c:url value='${movieList2[index].imgList[3].imgPath }' />" alt=" " class="img-responsive"></a>
									</div>
									<div class="footer-grid1-right">
										<a href="<c:url value='/movie.s?method=singleShow&type=movie&id=${movieList2[index].movieId }' />">${movieList2[index].movieName}</a>
										
									</div>
									<div class="clearfix"> </div>
								</div>
							</c:forEach>

						</div>
						<div class="col-md-2 footer-grid">
						   <h4 class="b-log"><a href="index.jsp"><span>${lg["indexTitle_1"] }</span>${lg["indexTitle_2"] } <span>${lg["indexTitle_3"] }</span>${lg["indexTitle_4"] }</a></h4>
							
							<c:forEach items="${indexVisit2 }" var="index" end="5">
								<div class="footer-grid-instagram">
									<a href="<c:url value='/movie.s?method=singleShow&type=movie&id=${movieList2[index].movieId }' />"><img src="<c:url value='${movieList2[index].imgList[0].imgPath }' />" alt=" " class="img-responsive"></a>
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

<script type="text/javascript">
	//选择个人管理事件
	function infoChange(){
		var selector = $('#infoChange');
		if(selector.val() === "修改信息" || selector.val() === 'Alter Information'){
			window.location.href = "userAlterInfo.jsp";
		}else if(selector.val() === "我的订单" || selector.val() === 'My Indent'){
			window.location.href = "userMovieIndent.jsp";
		}else if(selector.val() === "退出登录" || selector.val() === 'Login Out'){
			var data;
			$.post("<c:url value='/user.s?method=exit' />",data,function(data){})
			window.location.href = "index.jsp";
		}
	}
</script>