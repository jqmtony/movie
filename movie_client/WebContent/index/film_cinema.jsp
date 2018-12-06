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
<link href="css/film_cinema.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/phone.js" ></script>
</head>

<body>
<div class="whole">

	<header class="header">
        <a href="javascript:history.back(-1)" class="fa fa-angle-left"></a>
        <span class="names">${movieBallotTicket.movieName}</span>
        <!--<span class="collect">
        	<i class="fa fa-search"></i>
        </span>-->
    </header>
    
    <div class="film_content">
    	<!-- <div class="tab">
        	<ul>
            	<li class="active">附近<i class="fa fa-caret-down"></i></li>
                <li>区域<i class="fa fa-caret-down"></i></li>
                <li>服务<i class="fa fa-caret-down"></i></li>
                <li>其他<i class="fa fa-caret-down"></i></li>
            </ul>
        </div> -->
        <div class="address">
        	<ul>
        		<c:forEach items="${movieBallotTicket.merchantList }" var="mer">
	            	<li>
	                	<a href="<c:url value='/movie.s?method=showChoosableByMovieMerchant&merId=${mer.merId}' />">
	                        <div class="film_addr">
	                            <span>${mer.merStoreName }</span>  <%--商家店名 --%>
	                            <p>${movieBallotTicket.moviePrice }<span>元起</span></p>
	                            <!-- <span class="resort">常去影院</span> -->
	                        </div>
	                        <div class="det_addr">
	                            <span>${mer.merAddr }</span>
	                            <!-- <p>13.77km</p> -->
	                        </div>
	                        <div class="session">近期场次：<%--需要后台处理一下 --%>
	                       ${fn:substring(movieBallotTicket.ticketList[0].ticketStartTime ,11,16) } <%--fn:substring 获取子串 ${fn:substring(zip, 6, -1)}  --%>
	                        <c:forEach var="i" begin="1" end="${fn:length(movieBallotTicket.ticketList)-1 }">
	                        	<c:if test="${movieBallotTicket.ticketList[i].ticketMovieTheater != movieBallotTicket.ticketList[i-1].ticketMovieTheater }">
                        			| ${fn:substring(movieBallotTicket.ticketList[i].ticketStartTime,11,16) }
                        		</c:if>
	                        </c:forEach> 
	                        </div>
	                    </a>
	                </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    
    
</div>
</body>
</html>
