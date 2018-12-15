<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
		<meta name="format-detection" content="telephone=no, email=no, date=no, address=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<link href="merCss/bksystem.css" rel="stylesheet" type="text/css" /> 
		<link href="merFont/iconfont.css" rel="stylesheet" type="text/css" />
		<link href="merCss/module.css" rel="stylesheet" type="text/css" />
		<link href="merCss/pages.css" rel="stylesheet" type="text/css" />
		<title>商品管理</title>
		<script src="merJs/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="merJs/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="merJs/HUpages.js" type="text/javascript"></script>
		<script src="merJs/common.js" type="text/javascript"></script>
	</head>
	
<style>
	#page{
		margin:0 0 0 30%;
		text-align: center;
	}
	#page ul li{
		/*border:1px solid #000;*/
		float:left;
		
	}
	.page_num{
		width:25px;
		margin:30px 5px 0 5px;
		background-color:#cdcdcd;
	}
	.page_num:hover{
		background-color:#438eb9;
	}
	.page_prev,.page_next{
		width:70px;
		margin:30px 40px 0 40px;
		background-color:#cdcdcd;
	}
	.page_prev:hover,.page_next:hover{
		background-color:#438eb9;
		color:#fff;
	}
	.page_pc{/*当前页*/
		background-color:#438eb9;
		color:#fff;
		font-size:15px;
	}
	.page_tr{/*总页数*/
		margin:30px 0 0 0;
	}
	
	.page_go{/*跳转页面输入框*/
		margin:30px 10px 0 0;
	}
</style>
<body id="situation">
<c:if test="${empty pageBean_movie}">
	<jsp:forward page="/mer.s?method=findMovieByMer"></jsp:forward>
</c:if>
<div class="pages-style" >
	
	<!--右侧内容-->
	<div class="bk-con-message message-section" id="iframe_box">
           <!--编辑内容-->
           <div class="operation  mb15">
           	<!-- <button class="btn button_btn btn-danger" type="button" onclick=""><i class="iconfont"></i>&nbsp;批量删除</button> -->
           	<button class="btn button_btn bg-deep-blue" type="button" onclick="window.location.href='merUpload.jsp'"><i class="iconfont"></i>&nbsp;添加电影</button>
           </div>
           <!--列表内容-->
           <div class="page_content clearfix mb15 table-module ">
           	<table class="gallery table table_list table_striped table-bordered " id="">
           		<thead>
			        <tr>
					<th >片名</th>
					<th width="130">封面</th>
					<th >首发时间</th>
					<th >单价</th>
					<th >时长</th>
					<th >剩余影票</th>
					<th >状态</th>                
					<th >操作</th>
			       </tr>
		       </thead>
		       <tbody>
		       		<c:forEach items="${pageBean_movie.beanList }" var="movie">
		           		<tr>
			           		<td>${movie.movieName }</td>
			           		<td><a href="merImages/ad/ad.jpg" class="zoomimg"><img src="<c:url value='${movie.imgList[2].imgPath }'/>"  width="100%" height="100%"/></a></td>
			           		<td>${fn:substring(movie.movieCreateTime,0,10) }</td>
			           		<td>${movie.moviePrice } 元</td>
			           		<td>${movie.movieTimeLong }分钟</td>
			           		<td>${movie.allMovieTicketCount} / ${fn:length(movie.ticketList) }</td>
			           		<c:choose>
			           			<c:when test="${movie.movieStatus eq 0 }">
			           				<td><font id="movieStatusText${movie.movieId }" style="color:red;">未上架</font></td>
			           				<td><a href="javascript:setMovieStatus(${movie.movieId })"><font id="setMovieStatusButton${movie.movieId }" style="color:green;">上架</font></a></td>
			           			</c:when>
			           			<c:otherwise>
			           				<td><font id="movieStatusText${movie.movieId }" style="color:green;">已上架</font></td>
			           				<td><a href="javascript:setMovieStatus(${movie.movieId })"><font id="setMovieStatusButton${movie.movieId }" style="color:red;">下架</font></a></td>
			           			</c:otherwise>
			           		</c:choose>
			           		<script>
			           			//更改电影状态
			           			function setMovieStatus(movieId){
			           				var type = $("#setMovieStatusButton"+movieId).html();
			           				if(type === "上架"){
			           					type = "1";
			           				}else if(type === "下架"){
			           					type = "0";
			           				}
			           				var data = {
			           						method : "setMovieStatus",
			           						type : type,
			           						movieId : movieId
			           				};
			           				$.post("<c:url value='/mer.s' />",data,function(data){
			           					if(data === "yes"){
			           						if(type === "1"){
			           							$("#movieStatusText"+movieId).html("已上架");
				           						$("#setMovieStatusButton"+movieId).html("下架");
				           						$("#movieStatusText"+movieId).css("color","green")
				           						$("#setMovieStatusButton"+movieId).css("color","red")
			           						}else if(type === "0"){
			           							$("#movieStatusText"+movieId).html("未上架");
				           						$("#setMovieStatusButton"+movieId).html("上架");
				           						$("#movieStatusText"+movieId).css("color","red")
				           						$("#setMovieStatusButton"+movieId).css("color","green")
			           						}
			           					}else{
			           						alert(data);
			           					}
			           				});
			           			}
			           			//跳转到第几页
		           				function goPage(){
		           					var num = $('#goPageNum').val();
		           					var regx = /^\d*$/;
		           					if(regx.test(num)){
		           						location.href = "<c:url value='/mer.s?method=findMovieByMer&pc="+num+"' />";
		           					}else{
		           						alert("页数只能为数字，请重新输入！");
		           						$('#goPageNum').val("");
		           					}
		           				}
		           			</script>
		           		</tr>
	           		</c:forEach>
		       </tbody>
           </table>
           <c:set var="pb" value="${pageBean_movie }"></c:set>
           <div id="page">
           		<ul>
           			<li class="page_tr">${pb.pc} / ${pb.tp}</li>
           			<li class="page_prev"><a href="<c:url value='/mer.s?method=findMovieByMer&pc=${pb.pc-1}' />">上一页</a></li>
           			
           			 <c:choose>
		              	<c:when test="${pb.tp < 5}">
		              		<c:set var="begin" value="1" />
		              		<c:set var="end" value="${pb.tp}"/>
		              	</c:when>
		              	<c:otherwise>
		              		<c:set var="begin" value="${pb.pc-2}"/>
		              		<c:set var="end" value="${pb.pc+2}"/>
		              		
		              		<c:if test="${begin < 1 }">
			              		<c:set var="begin" value="1"/>
			              		<c:set var="end" value="5"/>
			              	</c:if>
			              	
			              	<c:if test="${end > pb.tp }">
			              		<c:set var="begin" value="${pb.tp-4}"/>
			              		<c:set var="end" value="${pb.tp}"/>
			              	</c:if>
		              	</c:otherwise>
		              </c:choose>
		              <c:forEach var="i" begin="${begin}" end="${end}">
		              	<c:choose>
		              		<c:when test="${i eq pb.pc}">
		              			<li class="page_num page_pc"><a href="<c:url value='/mer.s?method=findMovieByMer&pc=${i}' />">${i}</a></li>
		              		</c:when>
		              		<c:otherwise>
		              			<li class="page_num"><a href="<c:url value='/mer.s?method=findMovieByMer&pc=${i}' />">${i}</a></li>
		              		</c:otherwise>
		              	</c:choose>
		              </c:forEach>
		              
           			<li class="page_next"><a href="<c:url value='/mer.s?method=findMovieByMer&pc=${pb.pc+1}' />">下一页</a></li>
           			<li class="page_go"><input id="goPageNum" type="text" style="width:40px;height:20px;"> 
           			<a href="javascript:goPage()">跳转至</a></li>
           			
           		</ul>
           	</div>	
           </div>
           	
    </div>
  </div>
 </body>
 <c:if test="${not empty msg }">
 	<script type="text/javascript">
 		alert("${msg}")
 	</script>
 </c:if>
</html>
<script type="text/javascript">
function addMovie(){
	
}
</script>
<script>
$(function(){
	//内页框架结构编辑
	$("#situation").Hupage({
		slide: '#breadcrumb',
		padding:15,
		menuModule:'#bk-con-menu', //菜单模块
		pagecontent:'.page_content',//自定义属性
		operation:'.operation',//自定义属性
		scrollbar:function(e){
			e.niceScroll({
				cursorcolor: "#dddddd",
				cursoropacitymax: 1,
				touchbehavior: false,
				cursorwidth: "3px",
				cursorborder: "0",
				cursorborderradius: "3px",
			});						
		},
		expand:function(thisBox,settings){	
			var pc="";
			$(settings.pagecontent).css("margin-bottom")!=null? pc=parseInt($(settings.pagecontent).css("margin-bottom").replace("px","")):'';
		    $(settings.pagecontent).css({height:$(window).height()-$(settings.operation).outerHeight()-pc-(settings.padding*2)})
		    settings.scrollbar($(settings.slide) && $(settings.pagecontent));		    
		}//自定义方法
	});
 });
</script>
