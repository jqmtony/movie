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
	<c:when test="${empty indentList}">
		<jsp:forward page="/user.s?method=findIndentListByLoginedUser" />
	</c:when>
</c:choose>
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
	
	
	<div class="catbox" style="width:1300px;margin-top:30px; margin-bottom: 30px;">
		<h3 align="center">我的订单</h3>
		<table id="cartTable" style="margin-top:20px;">
			<thead>
				<tr style="text-align:center;">
					<th>订单编号</th>
					<th>电影名</th>
					<th>商户</th>
					<th>封面</th>
					<th>总金额</th>
					<th>数量</th>
					<th>上映时间</th>
					<th>结束时间</th>
					<th>订单状态</th>
					<th>操作</th>
					<th>确认收货</th>
				</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${fn:length(indentList) eq 0 }">
					<h1>你还没有订单</h1>
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
									<td>未收货</td>
								</c:when>
								<c:when test="${indent.indentStatus == '1' }">
									<td>已完成</td>
								</c:when>
							</c:choose>
							<td><a href="<c:url value='/movie.s?method=setIndentStatus&id=${indent.indentId }&type=1' />">确认收货</a></td>
							<td><a href="<c:url value='/movie.s?method=setIndentStatus&id=${indent.indentId }&type=-1' />">删除</a></td>
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