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
<div id="payBox">
<form action="<c:url value='/movie.s' />" method="post">
<input type="hidden" name="method" value="pay">
	<table width="60%">

            <tr>

                <td bgcolor="#F7FEFF" colspan="4">

                <!-- 实际开发中：这里的订单号和支付金额来自servlet传递过来 -->
 
                订单号：<font style="margin-right:30px;" name="orderId">${indentObj.indentNum }</font> 

                支付金额：<font name="money">${indentObj.indentPrice }</font> 元

            </tr>

            <tr>

                <td>

                    <br/>

                </td>
            </tr>

            <tr>

                <td>请您选择在线支付银行</td>

            </tr>

            <tr>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="ICBC-NET">工商银行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="ABC-NET">农业银行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CCB-NET">建设银行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CMBCHINA-NET">招商银行</td>

            </tr>

            <tr>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CMBC-NET">中国民生银行总行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CEB-NET">光大银行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="BOCO-NET">交通银行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="SDB-NET">深圳发展银行</td>

            </tr>

            <tr>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="BCCB-NET">北京银行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="CIB-NET">兴业银行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="SPDB-NET">上海浦东发展银行</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="ECITIC-NET">中信银行</td>

            </tr>

            <tr>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="POST-NET-B2C">中国邮政</td>

                <td><INPUT TYPE="radio" NAME="pd_FrpId" value="BOC-NET-B2C">中国银行</td>

            </tr>

            <tr>

                <td>

                    <br/>

                </td>

            </tr>

            <tr>

                <td><input type="submit" value="进入银行支付" /></td>

            </tr>

        </table>

    </form>
</div>			
<c:if test="${! empty msg }">
		<script type="text/javascript">
			alert('${msg}');
		</script>
	</c:if>
<%@ include file="footer.jsp"%>

</body>
</html>