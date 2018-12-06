<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>jQuery在线选座(影院版)</title>
<meta name="keywords" content="jQuery在线选座,jQuery选座系统" />
<meta name="description" content="jquery.seat-charts是一款适合电影票、高铁票的在线选座插件。" />

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
<style type="text/css">
.front{width: 300px;margin: 5px 32px 45px 32px;background-color: #f0f0f0;	color: #666;text-align: center;padding: 3px;border-radius: 5px;}
.booking_area {float: right;position: relative;width:200px;height: 450px; }
.booking_area h3 {margin: 5px 5px 0 0;font-size: 16px;}
.booking_area p{line-height:26px; font-size:16px; color:#999}
.booking_area p span{color:#666}
div.seatCharts-cell {color: #182C4E;height: 25px;width: 25px;line-height: 25px;margin: 3px;float: left;text-align: center;outline: none;font-size: 13px;}
div.seatCharts-seat {color: #fff;cursor: pointer;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;}
div.seatCharts-row {height: 35px;}
div.seatCharts-seat.available {background-color: #B9DEA0;}
div.seatCharts-seat.focused {background-color: #76B474;border: none;}
div.seatCharts-seat.selected {background-color: #E6CAC4;}
div.seatCharts-seat.unavailable {background-color: #472B34;cursor: not-allowed;}
div.seatCharts-container {border-right: 1px dotted #adadad;width: 400px;padding: 20px;float: left;}
div.seatCharts-legend {padding-left: 0px;position: absolute;bottom: 16px;}
ul.seatCharts-legendList {padding-left: 0px;}
.seatCharts-legendItem{float:left; width:90px;margin-top: 10px;line-height: 2;}
span.seatCharts-legendDescription {margin-left: 5px;line-height: 30px;}
.checkout-button {display: block;width:80px; height:24px; line-height:20px;margin: 10px auto;border:1px solid #999;font-size: 14px; cursor:pointer}
#seats_chose {max-height: 150px;overflow-y: auto;overflow-x: none;width: 200px;}
#seats_chose li{float:left; width:72px; height:26px; line-height:26px; border:1px solid #d3d3d3; background:#f7f7f7; margin:6px; font-size:14px; font-weight:bold; text-align:center}
</style>
		<link rel="stylesheet" type="text/css" href="ballotTicket/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="ballotTicket/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="ballotTicket/css/component.css" />
		<script src="ballotTicket/js/modernizr-custom.js"></script>
</head>
<body>
<%--头部 --%>
<%@ include file="head.jsp"%>
<%--头部 --%>


<%-- <jsp:include page="index/details.jsp" /> --%>
<%-- <%@include file="index/details.jsp" %> --%>
<!-- breadcrumb -->
		<div class="w3_breadcrumb">
			<div class="breadcrumb-inner">	
				<ul>
					<li><a href="index.jsp">Home</a><i>//</i></li>
					<li><a href="${refererPath }">Single</a></li>
				</ul>
			</div>
		</div>
	<!-- //breadcrumb -->
<iframe style="height:1000px;width:100%;" src="index/details.jsp">
</iframe>


<%--尾部 --%>
<%@ include file="footer.jsp"%>
<%--尾部 --%>
</body>

</html>


