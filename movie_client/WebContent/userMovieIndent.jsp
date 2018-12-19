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

<link rel="stylesheet" href="indentCss/style.css"/>

<script type="text/javascript" src="indentJs/demo.js"></script>
<style type="text/css">
</style>

</head>
<c:choose>
	<c:when test="${indentList eq null}">
		<jsp:forward page="/user.s?method=findIndentListByLoginedUser" />
	</c:when>
</c:choose>
<body>

<!--/main-header-->
  <!--/banner-section-->
	<%@ include file="head.jsp"%>
	<!-- breadcrumb -->
		<div class="w3_breadcrumb">
			<div class="breadcrumb-inner">	
				<ul>
					<li><a href="index.jsp">${lg['indexTitle'] }</a><i>//</i></li>
					<li>${lg['myIndent'] }</li>
				</ul>
			</div>
		</div>
	<!-- //breadcrumb -->
	
	<!--
		<div class="w3_breadcrumb">
			<div class="breadcrumb-inner">	
				<ul>
					<li><a href="index.jsp">Home</a><i>//</i></li>
					<li>Single</li>
				</ul>
			</div>
		</div> -->
	
	
	<div class="catbox" style="width:1300px;margin-top:30px; margin-bottom: 30px;">
		<h3 align="center">${lg['myIndent'] }</h3>
		<table id="cartTable" style="margin-top:20px;">
			<thead>
				<tr style="text-align:center;">
					<th>${lg['indentNum'] }</th>
					<th>${lg['movieName'] }</th>
					<th>${lg['merchant'] }</th>
					<th>${lg['cover'] }</th>
					<th>${lg['totalMoney'] }</th>
					<th>${lg['count'] }</th>
					<th>${lg['movieStartTime'] }</th>
					<th>${lg['movieEndTime'] }</th>
					<th>${lg['indentStatus'] }</th>
					<th>${lg['operate'] }</th>
					<th>${lg['confirmReceipt'] }</th>
				</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${fn:length(indentList) eq 0 }">
					<h1>${lg['youHaveNotIndent'] }</h1>
				</c:when>
				<c:otherwise>
					<c:forEach items="${indentList }" var="indent">
						<tr>
							<td>${indent.indentNum }</td>
							<td><h3>${indent.ticketList[0].movie.movieName }</h3></td>
							<td><a href="">${indent.ticketList[0].merchant.merStoreName }</a></td>
							<td><img style="width:100px;height:150px;" src="<c:url value='${indent.ticketList[0].movie.imgList[0].imgPath }' />" alt="不能显示这张图片"/></td>
							<td class="price">${indent.indentPrice } 元</td>
							<td class="count">${fn:length(indent.ticketList) }</td>
							<td class="update">${fn:substring(indent.ticketList[0].ticketMovieStartTime,0,16) }</td>
							<td class="update">${fn:substring(indent.ticketList[0].ticketMovieEndTime,0,16) }</td>
							<c:choose>
								<c:when test="${indent.indentStatus == '0' }">
									<td>${lg['notReceiveTheGoods'] }</td>
								</c:when>
								<c:when test="${indent.indentStatus == '1' }">
									<td>${lg['offTheStocks'] }</td>
								</c:when>
							</c:choose>
							<td><a href="<c:url value='/movie.s?method=setIndentStatus&id=${indent.indentId }&type=1' />">${lg['confirmReceipt'] }</a></td>
							<td><a href="<c:url value='/movie.s?method=setIndentStatus&id=${indent.indentId }&type=-1' />">${lg['delete'] }</a></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		
		<%-- <div class="foot" id="foot">
			<div class="fr closing" onclick="location.href='<c:url value='pay.jsp' />'"><a href="<c:url value='pay.jsp' />">结 算</a></div>
			<div class="fr total">合计：￥${indentObj.indentPrice}</span></div>
		</div> --%>
	
	</div>
	
	
	<!--/footer-bottom-->
	<%@ include file="footer.jsp"%>

 

</body>
</html>