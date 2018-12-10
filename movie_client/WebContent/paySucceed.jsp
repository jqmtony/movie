<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>Pay</title>
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
</head>
<style>
#payBox{
	margin:20px 0 20px 400px;
}
</style>
<body> 
<%-- <c:if test="${empty pageBean }">
	<jsp:forward page="movie.s?method=goGenre" />
</c:if> --%>
<!--/main-header-->
  <!--/banner-section-->
 <%@ include file="head.jsp"%>
 
 <div style="margin:30px 0 30px 600px;">
	<h1>支付成功</h1>
	<font style="color:red;" id="goMsg"></font> 秒后跳转页面...
</div>
	<script type="text/javascript">
		function out(obj){
			var i = obj ;
			if(i<=0){
				document.location.href="http://${addrIp}userMovieIndent.jsp";
				return;
			}
			document.getElementById('goMsg').innerHTML = i;
			i--;
			setTimeout("out("+i+")",1000);
		}
		out(5);
	</script>
<%@ include file="footer.jsp"%>

</body>
</html>