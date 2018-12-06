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
        <span class="collect">
        	<i class="fa fa-star-o"></i>
        </span>
    </header>
    
    <div class="film-length">
    	<span>片长：${movieBallotTicket.movieTimeLong }分钟</span>
        <span class="imax">${movieBallotTicket.movieGenre }</span>
    </div>
    
    <div class="tips">温馨提示：电影开场前30分钟关闭在线售票</div>
    
    <div class="tab date">
        <!---tab日期标签滑动--->
        <div id="J_MenuX">
            <div class="xs-container">
                <ul class="xs-content nav nav-pills nav-justified" id="menus_xx" _xx="0">
                	<c:if test="${fn:length(movieByNextTime.ticketList) > 0 }">
                		<li>${fn:substring(movieByNextTime.ticketList[0].ticketStartTime,5,10) }(今天)</li>
                	</c:if>
                	<c:forEach var="i" begin="1" end="${fn:length(movieByNextTime.ticketList)-1 }">
                		<c:if test="${fn:substring(movieByNextTime.ticketList[i].ticketStartTime,5,10) != fn:substring(movieByNextTime.ticketList[i-1].ticketStartTime,5,10) }">
                			<li>${fn:substring(movieByNextTime.ticketList[i].ticketStartTime,5,10) }</li>
                		</c:if>
                	</c:forEach>
                </ul>
            </div>
        </div>
        <!---tab标签滑动END--->
    </div>
    
    <div class="tickets-list">
    	<ul>
    		<c:if test="${fn:length(movieByNextTime.ticketList) > 0 }">
    			<li>
	            	<div class="ticket-info">
	                	<span class="start">${fn:substring(movieByNextTime.ticketList[0].ticketStartTime,11,16)}</span>
	                    <span class="styles">${movieByNextTime.movieGenre}</span>
	                    <span> (结束)</span>
	                    <span>4号厅</span>
	                </div>
	                <div class="buy-btn">
	                	<span>40<b>元</b></span>
	                    <a href="choose_seat.jsp">选座购票</a>
	                </div>
	            </li>
    		</c:if>
    		
    		<c:forEach var="i" begin="1" end="${fn:length(movieByNextTime.ticketList)-1 }">
    			
    		</c:forEach>
        	<li>
            	<div class="ticket-info">
                	<span class="start">14:20</span>
                    <span class="styles">原版3D</span>
                    <span>16:50(结束)</span>
                    <span>4号厅</span>
                </div>
                <div class="buy-btn">
                	<span>40<b>元</b></span>
                    <a href="choose_seat.jsp">选座购票</a>
                </div>
            </li>
            
           <!--  <li>
            	<div class="ticket-info">
                	<span class="start">15:20</span>
                    <span class="styles">原版3D</span>
                    <span>17:50(结束)</span>
                    <span>5号厅</span>
                </div>
                <div class="buy-btn">
                	<span>40<b>元</b></span>
                    <a href="choose_seat.jsp">选座购票</a>
                </div>
            </li>
            
            <li>
            	<div class="ticket-info">
                	<span class="start">16:20</span>
                    <span class="styles">原版3D</span>
                    <span>18:50(结束)</span>
                    <span>6号厅</span>
                </div>
                <div class="buy-btn">
                	<span>40<b>元</b></span>
                    <a href="choose_seat.jsp">选座购票</a>
                </div>
            </li>
            
            <li>
            	<div class="ticket-info">
                	<span class="start">17:20</span>
                    <span class="styles">原版3D</span>
                    <span>19:50(结束)</span>
                    <span>7号厅</span>
                </div>
                <div class="buy-btn">
                	<span>40<b>元</b></span>
                    <a href="choose_seat.jsp">选座购票</a>
                </div>
            </li>
            
            <li>
            	<div class="ticket-info">
                	<span class="start">18:20</span>
                    <span class="styles">原版3D</span>
                    <span>20:50(结束)</span>
                    <span>8号厅</span>
                </div>
                <div class="buy-btn">
                	<span>40<b>元</b></span>
                    <a href="choose_seat.jsp">选座购票</a>
                </div>
            </li>
            
            <li>
            	<div class="ticket-info">
                	<span class="start">19:20</span>
                    <span class="styles">原版3D</span>
                    <span>21:50(结束)</span>
                    <span>9号厅</span>
                </div>
                <div class="buy-btn">
                	<span>40<b>元</b></span>
                    <a href="choose_seat.jsp">选座购票</a>
                </div>
            </li>
            
            <li>
            	<div class="ticket-info">
                	<span class="start">20:20</span>
                    <span class="styles">原版3D</span>
                    <span>22:50(结束)</span>
                    <span>1号厅</span>
                </div>
                <div class="buy-btn">
                	<span>40<b>元</b></span>
                    <a href="choose_seat.jsp">选座购票</a>
                </div>
            </li>
            
            <li>
            	<div class="ticket-info">
                	<span class="start">21:20</span>
                    <span class="styles">原版3D</span>
                    <span>23:50(结束)</span>
                    <span>2号厅</span>
                </div>
                <div class="buy-btn">
                	<span>40<b>元</b></span>
                    <a href="choose_seat.jsp">选座购票</a>
                </div>
            </li> -->
            
        </ul>
    </div>
    
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
		
})
</script>

</body>
</html>
