<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>详情</title>
<link href="css/details.css" type="text/css" rel="stylesheet" />
<link href="js/layer-v3.0.3/layer/mobile/need/layer.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/phone.js" ></script>
<script type="text/javascript" src="js/layer-v3.0.3/layer/layer.js"></script>
</head>

<body>
<div class="whole">
	<div class="movie">
    	<img src="<c:url value='${movieBallotTicket.imgList[3].imgPath }' />" />
        <span>
        	<img src="image/start.png" />
        </span>
    </div>
    <div class="detail">
    	<div class="bol"><img src="image/bg-bg.png" /></div>
        <div class="det-con">
        	<div class="common det-c">
            	<div class="jz-pic">
                	<img src="<c:url value='${movieBallotTicket.imgList[0].imgPath }' />" />
                </div>
                <div class="det-inf">
                	<p class="det-titles">
                    	<span>${movieBallotTicket.movieName }</span>
                    </p>
                    <ul class="stars">
                    	<li><img src="image/stars.png" /></li>
                        <li><img src="image/stars.png" /></li>
                        <li><img src="image/stars.png" /></li>
                        <li><img src="image/stars.png" /></li>
                        <li><img src="image/stars.png" /></li>
                    </ul>
                    <p class="det-tag" style="color:#000000;">
						<c:forEach items="${movieBallotTicket.classifysList }" var="classify">
							${classify.classifyNameObj.classifyNameString } 
						</c:forEach>
					</p>
                    
                </div>
            </div>
            
            <div class="common parti">
            	<span>${movieBallotTicket.movieDescribe }</span>
            </div>
            
            <div class="common stage-photo">
            	<h3>${lg['stagePhoto'] }</h3>
                <ul>
                	<li><img src="<c:url value='${movieBallotTicket.imgList[0].imgPath }' />" /></li>
					<li><img src="<c:url value='${movieBallotTicket.imgList[1].imgPath }' />" /></li>
					<li><img src="<c:url value='${movieBallotTicket.imgList[2].imgPath }' />" /></li>
                </ul>
            </div>
            
        </div>
    </div>
    <a class="footer" href="film_cinema.jsp">${lg['theTicketImmediately'] }</a>
</div>
<script type="text/javascript">
$(document).ready(function() {
	
	$('.movie').click(function(){
		movie();
	});
	
	function movie(){
		layer.open({
		  type: 2,
		  title: false,
		  area: ['630px', '360px'],
		  shade: 0.8,
		  closeBtn: 0,
		  shadeClose: true,
		  content: '<c:url value="${movieBallotTicket.moviePrevue}" />'
		});	
	}
	
	$('.back').on('click',function(){
		window.history.go(-1);
	});
	
});
</script>
<c:if test="${! empty msg }">
	<script type="text/javascript">
		alert('${msg}');
	</script>
</c:if>
</body>
</html>
