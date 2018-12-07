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


</head>
<body>
<!--/main-header-->
  <!--/banner-section-->
	<%@ include file="head.jsp"%>
	<!-- breadcrumb -->
		<div class="w3_breadcrumb">
			<div class="breadcrumb-inner">	
				<ul>
					<li><a href="index.jsp">Home</a><i>//</i></li>
					<li>Single</li>
				</ul>
			</div>
		</div>
	<!-- //breadcrumb -->
	
	
	<div class="catbox">

		<table id="cartTable">
			<thead>
				<tr>
					<th><label><input class="check-all check" type="checkbox"/>&nbsp;全选</label></th>
					<th>电影名</th>
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
					<th>上架时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="checkbox"><input class="check-one check" type="checkbox"/></td>
					<td class="goods"><img src="indentImages/封面小6.jpg" alt=""/><span>复仇者联盟3:无限战争</span></td>
					<td class="price">100</td>
					<td class="count"><span class="reduce"></span><input class="count-input" type="text" value="1"/><span class="add">+</span></td>
					<td class="subtotal">100</td>
					<td class="update">2018-12-4</td>
					<td class="operation"><span class="delete">删除</span></td>
				</tr>
				<tr>
					<td class="checkbox"><input class="check-one check" type="checkbox"/></td>
					<td class="goods"><img src="indentImages/封面小4.jpg" alt=""/><span>蜘蛛侠:英雄归来</span></td>
					<td class="price">100</td>
					<td class="count"><span class="reduce"></span><input class="count-input" type="text" value="1"/><span class="add">+</span></td>
					<td class="subtotal">100</td>
					<td class="update">2018-12-4</td>
					<td class="operation"><span class="delete">删除</span></td>
				</tr>
				<tr>
					<td class="checkbox"><input class="check-one check" type="checkbox"/></td>
					<td class="goods"><img src="indentImages/封面小5.jpg" alt=""/><span>正义联盟</span></td>
					<td class="price">100</td>
					<td class="count"><span class="reduce"></span><input class="count-input" type="text" value="1"/><span class="add">+</span></td>
					<td class="subtotal">100</td>
					<td class="update">2018-12-4</td>
					<td class="operation"><span class="delete">删除</span></td>
				</tr>
				<tr>
					<td class="checkbox"><input class="check-one check" type="checkbox"/></td>
					<td class="goods"><img src="indentImages/封面小10.jpg" alt=""/><span>侏罗纪世界2</span></td>
					<td class="price">100</td>
					<td class="count"><span class="reduce"></span><input class="count-input" type="text" value="1"/><span class="add">+</span></td>
					<td class="subtotal">100</td>
					<td class="update">2018-12-4</td>
					<td class="operation"><span class="delete">删除</span></td>
				</tr>
			</tbody>
		</table>
		
		<div class="foot" id="foot">
			<label class="fl select-all"><input type="checkbox" class="check-all check"/>&nbsp;全选</label>
			<a class="fl delete" id="deleteAll" href="javascript:;">删除</a>
			<div class="fr closing">结 算</div>
			<div class="fr total">合计：￥<span id="priceTotal">0.00</span></div>
			<div class="fr selected" id="selected">已选商品<span id="selectedTotal">0</span>件<span class="arrow up">︽</span><span class="arrow down">︾</span></div>
			<div class="selected-view">
				<div id="selectedViewList" class="clearfix">
					<div><img src="indentImages/1.jpg"><span>取消选择</span></div>
				</div>
				<span class="arrow">◆<span>◆</span></span>
			</div>
		</div>
	
	</div>
	
	
	<!--/footer-bottom-->
	<%@ include file="footer.jsp"%>

 

</body>
</html>