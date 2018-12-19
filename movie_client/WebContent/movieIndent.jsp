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
<body>
<!--/main-header-->
  <!--/banner-section-->
	<%@ include file="head.jsp"%>
	
	
	<!--
		<div class="w3_breadcrumb">
			<div class="breadcrumb-inner">	
				<ul>
					<li><a href="index.jsp">Home</a><i>//</i></li>
					<li>Single</li>
				</ul>
			</div>
		</div> -->
	
	
	<div class="catbox" style="margin-top:30px; margin-bottom: 30px;">
		<h3>订单号：${indentObj.indentNum }</h3>
		<table id="cartTable" style="margin-top:20px;">
			<thead>
				<tr  style="text-align:center;">
					<th>电影名</th>
					<th>封面</th>
					<th>单价</th>
					<th>数量</th>
					<th>上映时间</th>
					<th>结束时间</th>
					<th>座位</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${indentObj.ticketList}" var="ticket">
				<tr>
					<td class="goods"><h3>${movieBallotTicket.movieName }</h3></td>
					<td><img style="width:100px;height:150px;" src="<c:url value='${movieBallotTicket.imgList[0].imgPath }' />" alt="不能显示这张图片"/></td>
					<td class="price">${movieBallotTicket.moviePrice } 元</td>
					<td class="count">1</td>
					<td class="update">${fn:substring(ticket.ticketMovieStartTime,0,16) }</td>
					<td class="update">${fn:substring(ticket.ticketMovieEndTime,0,16) }</td>
					<td>${ticket.ticketLocation }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div class="foot" id="foot">
			<div class="fr closing" onclick="location.href='<c:url value='pay.jsp' />'"><a href="<c:url value='pay.jsp' />">结 算</a></div>
			<div class="fr total">合计：￥${indentObj.indentPrice}</span></div>
		</div>
	
	</div>
	
	<c:if test="${! empty msg }">
		<script type="text/javascript">
			alert('${msg}');
		</script>
	</c:if>
	<!--/footer-bottom-->
	<%@ include file="footer.jsp"%>

 

</body>
</html>