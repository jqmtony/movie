<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0;"/>
<title>电影选购</title>
<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="css/tickets.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/phone.js" ></script>
<script type="text/javascript" src="js/menu_x.js"></script>
</head>

<body>
<div class="whole">

	<header class="header">
        <a href="javascript:history.back(-1)" class="fa fa-angle-left"></a>
        <span class="names">${movieBallotTicket.movieName }</span>
    </header>
    
    <div class="film-length">
    	<span>片长：${movieBallotTicket.movieTimeLong }分钟</span>
        <span class="imax">${movieBallotTicket.movieGenre }</span>
    </div>
    
    <div class="tips">温馨提示：电影开场前10分钟关闭在线售票</div>
    
    <div class="tab date">
        <!---tab日期标签滑动--->
        <div id="J_MenuX">
            <div class="xs-container">
            	<c:set var="num" value="1" />
                <ul class="xs-content nav nav-pills nav-justified" id="menus_xx" _xx="0">
                	<c:if test="${fn:length(dateArr) > 0 }">
                		<li name="0" class="0">${dateArr[0] }</li>
                	</c:if>
                	<c:forEach var="i" begin="1" end="${fn:length(dateArr)-1 }">
                		<c:if test="${dateArr[i] != dateArr[i-1] }">
                			<li name="${num }" class="${i}">${dateArr[i]}</li>
                			<c:set var="num" value="${num+1}" />
                		</c:if>
                	</c:forEach>
                </ul>
            </div>
        </div>
        <!---tab标签滑动END--->
    </div>
    
    <%--遍历所有的日期   通过日期取出对应的电影集合 --%>
    
    <%--把第一个日期对应的所有电影厅显示出来 --%>
    <div class="tickets-list" id="showTheater0" style="display:block;">
	    	<ul>
	    		<%--生成第一个电影厅 --%>
	    		<c:if test="${fn:length(allMovietMap[dateArr[0]].ticketList) > 0 }">
	    			<li>
		            	<div class="ticket-info">
		                	<span class="start">${fn:substring(allMovietMap[dateArr[0]].ticketList[0].ticketStartTime,11,16)}</span>
		                    <span class="styles">${allMovietMap[dateArr[0]].movieGenre}</span>
		                    <span>${fn:substring(allMovietMap[dateArr[0]].ticketList[0].ticketMovieEndTime,11,16)}(结束)</span>
		                    <span>${allMovietMap[dateArr[0]].ticketList[0].ticketMovieTheater }</span>
		                </div>
		                <div class="buy-btn">
		                	<span>${allMovietMap[dateArr[0]].moviePrice }<b>元</b></span>
		                    <a href="<c:url value='/movie.s?method=showTicketChose&date=${dateArr[0]}&theater=${allMovietMap[dateArr[0]].ticketList[0].ticketMovieTheater}' />">选座购票</a>
		                </div>
		            </li>
		            
		            <%--生成后面的电影厅 --%>
		            <c:forEach var="i" begin="1" end="${fn:length(allMovietMap[dateArr[0]].ticketList)-1 }">
		            	<c:if test="${allMovietMap[dateArr[0]].ticketList[i].ticketMovieTheater != allMovietMap[dateArr[0]].ticketList[i-1].ticketMovieTheater }">
		    				<li>
				            	<div class="ticket-info">
				                	<span class="start">${fn:substring(allMovietMap[dateArr[0]].ticketList[i].ticketStartTime,11,16)}</span>
				                    <span class="styles">${allMovietMap[dateArr[0]].movieGenre}</span>
				                    <span>${fn:substring(allMovietMap[dateArr[0]].ticketList[i].ticketMovieEndTime,11,16)}(结束)</span>
				                    <span>${allMovietMap[dateArr[0]].ticketList[i].ticketMovieTheater }</span>
				                </div>
				                <div class="buy-btn">
				                	<span>${allMovietMap[dateArr[0]].moviePrice }<b>元</b></span>
				                    <a href="<c:url value='/movie.s?method=showTicketChose&date=${dateArr[0]}&theater=${allMovietMap[dateArr[0]].ticketList[i].ticketMovieTheater}' />">选座购票</a>
				                </div>
				            </li>
			            </c:if>
	    			</c:forEach>
	    			
	    		</c:if>
	        </ul>
	    </div>
	    
	    <%--生成后面的日期 --%>
	<c:forEach var="i" begin="1" end="${fn:length(dateArr)-1 }">
	    <div class="tickets-list" id="showTheater${i}" style="display:none;">  <%--这里的ID刚刚改过 --%>
	    	<ul>
	    		<%--生成第一个电影厅 --%>
	    		<c:if test="${fn:length(allMovietMap[dateArr[i]].ticketList) > 0 }">
	    			<li>
		            	<div class="ticket-info">
		                	<span class="start">${fn:substring(allMovietMap[dateArr[i]].ticketList[0].ticketStartTime,11,16)}</span>
		                    <span class="styles">${allMovietMap[dateArr[i]].movieGenre}</span>
		                    <span>${fn:substring(allMovietMap[dateArr[i]].ticketList[0].ticketMovieEndTime,11,16)}(结束)</span>
		                    <span>${allMovietMap[dateArr[i]].ticketList[0].ticketMovieTheater }</span>
		                </div>
		                <div class="buy-btn">
		                	<span>${allMovietMap[dateArr[i]].moviePrice }<b>元</b></span>
		                    <a href="<c:url value='/movie.s?method=showTicketChose&date=${dateArr[i]}&theater=${allMovietMap[dateArr[i]].ticketList[0].ticketMovieTheater}' />">选座购票</a>
		                </div>
		            </li>
		            <%--生成后面的电影厅 --%>
		            <c:forEach var="j" begin="1" end="${fn:length(allMovietMap[dateArr[i]].ticketList)-1 }">
		            	<c:if test="${allMovietMap[dateArr[i]].ticketList[j].ticketMovieTheater != allMovietMap[dateArr[i]].ticketList[j-1].ticketMovieTheater }">
		    				<li>
				            	<div class="ticket-info">
				                	<span class="start">${fn:substring(allMovietMap[dateArr[i]].ticketList[j].ticketStartTime,11,16)}</span>
				                    <span class="styles">${allMovietMap[dateArr[i]].movieGenre}</span>
				                    <span>${fn:substring(allMovietMap[dateArr[i]].ticketList[j].ticketMovieEndTime,11,16)}(结束)</span>
				                    <span>${allMovietMap[dateArr[i]].ticketList[j].ticketMovieTheater }</span>
				                </div>
				                <div class="buy-btn">
				                	<span>${allMovietMap[dateArr[i]].moviePrice }<b>元</b></span>
				                    <a href="<c:url value='/movie.s?method=showTicketChose&date=${dateArr[i]}&theater=${allMovietMap[dateArr[i]].ticketList[j].ticketMovieTheater}' />">选座购票</a>
				                </div>
				            </li>
			            </c:if>
	    			</c:forEach>
	    			
	    		</c:if>
	        </ul>
	    </div>
    </c:forEach>
</div>

<script type="text/javascript">
	var menux = new menuX("#J_MenuX",0);
</script>

<script type="text/javascript">
$(function(){
	
	$('.collect .fa').click(function(){
		if($(this).hasClass('fa-star-o')){
			$(this).removeClass('fa-star-o').addClass('fa-star');
		}else{
			$(this).removeClass('fa-star').addClass('fa-star-o');
		}
	});
	
	$('#menus_xx li').click(function(){
		//把所有的先重置为不能显示
		$('.tickets-list').each(function(){
			$(this).css("display","none");
		});
		
		//把当前的对应的电影厅设置为显示
		var s = $(this).attr("name");  //得到当前的name 1 2 3
		//console.log("当前是第："+s);
		$('#showTheater'+s).css("display","block");
	});
	
	
})
</script>
</body>
</html>
