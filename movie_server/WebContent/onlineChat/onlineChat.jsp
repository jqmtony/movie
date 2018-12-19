<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="css/qq.css"/>

</head>
<c:if test="${userMerList eq null}">
	<jsp:forward page="/mer.s?method=findAllUser" />
</c:if>
<body onload="refresh()">

<div class="qqBox" style="margin-top:10px;">
	<div class="BoxHead">
		<div class="headImg">
			<img src="<c:url value='${loginedMerchant.imgList[0].imgPath }' />" style="width:43px;height:42px;"/>
		</div>
		<div class="internetName">${loginedMerchant.merName }</div>
	</div>
	<div class="context">
		<div class="conLeft">
			<ul>
				<c:forEach items="${userMerList }" var="user">
					<li name="newsList${user.userId }">
						<div class="liLeft"><img src="<c:url value='${user.imgList[0].imgPath }' />" style="width:43px;height:42px;"/></div>
						<div class="liRight">
							<span class="intername">${user.userName }</span>
							<!-- <span class="infor">厉害了</span> -->
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="conRight">
			<div class="Righthead">
				<div class="headName">${userMerList[0].userName }</div>
			</div>
			<div class="RightCont">
				<ul class="newsList" name="newsList${userMerList[0].userId }" style="display:block;">
					
				</ul>
				<c:forEach items="${userMerList }" var="user" begin="1">
					<ul class="newsList" name="newsList${user.userId }" style="display:none;">
						
					</ul>
				</c:forEach>
			</div>
			<div class="RightFoot">
				<div class="inputBox">
					<textarea id="dope" style="width: 99%;height: 75px; border: none;outline: none;" name="" rows="" cols=""></textarea>
					<button class="sendBtn">发送(s)</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
var nowObj = $('.conLeft li:first');  //当前点击的左边的对象
//点击左边的人显示聊天页面
$('.conLeft li').on('click',function(){
	nowObj = $(this);
	var leftName = $(this).attr("name");
	$(this).addClass('bg').siblings().removeClass('bg');
	var intername=$(this).children('.liRight').children('.intername').text();
	$('.headName').text(intername);
	//$('.newsList').html('');
	$('.newsList').each(function(index,data){  //聊天内容面板的切换
		//console.log($(data).attr("name")+"..."+leftName);
		if($(data).attr("name") === leftName){
			$(data).css("display","block");
		}else{
			$(data).css("display","none");
		}
	});
});

//点击发送信息按钮
$('.sendBtn').on('click',function(){
	var news=$('#dope').val();
	if(news==''){
		alert('不能为空');
	}else{
		$('#dope').val('');
		var str='';
		str+='<li>'+
				'<div class="answerHead"><img src="<c:url value='${loginedMerchant.imgList[0].imgPath}' />"/></div>'+
				'<div class="answers"><img class="jiao" src="img/20170926103645_03_02.jpg">'+news+'</div>'+
			'</li>';
			
		//给对应的面板添加消息
		$('.newsList').each(function(index,data){
			if($(data).attr('name') === nowObj.attr("name")){
				$(data).append(str);
			}
		});
		
		//setTimeout(answers,1000);   //自动回复
		
		var data1 = {content : news,id : nowObj.attr("name")};
		//发送给服务器
		$.post("<c:url value='/mer.s?method=sendContentToChatServer' />",data1,function(data){
			
		});
		
		//将刚才发送的聊天内容显示到页面
		$('.conLeft').find('li.bg').children('.liRight').children('.infor').text(news);
		$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
		}
	});

//刷新查找是否有给当前用户的信息
function refresh(){
	var data;
	$.post("<c:url value='/mer.s?method=revicedContent' />",data,function(data){
		if(data != 'no'){
			var dataArr = data.split("{[code]}");  //得到数组
			var userId = dataArr[0];
		
			
			userId = "newsList" + userId;
			var content = dataArr[1];
			var path = dataArr[2];
			
			var str = '<li name='+userId+'>'+
			'<div class="nesHead"><img src="${pageContext.request.contextPath}'+path+'" /></div>'+
			'<div class="news"><img class="jiao" src="img/jiao.jpg">'+content+'</div>'+
			'</li>';
			
			//给对应的面板添加回复
			$('.newsList').each(function(index,data){
				if($(data).attr('name') === userId){
					$(data).append(str);
				}
			});	
		}
	});
	setTimeout('refresh()',2000);
}


//自动回复 
function answers(){
	var arr=["你好","今天天气很棒啊","你吃饭了吗？","我最美我最美","我是可爱的僵小鱼","你们忍心这样子对我吗？","spring天下无敌，实习工资850","我不管，我最帅，我是你们的小可爱","段友出征，寸草不生","一入段子深似海，从此节操是路人","馒头：嗷","突然想开个车","段子界混的最惨的两个狗：拉斯，普拉达。。。"];
	var aa=Math.floor((Math.random()*arr.length));
	var answer='';
	answer+='<li>'+
				'<div class="nesHead"><img src="img/tou.jpg"/></div>'+
				'<div class="news"><img class="jiao" src="img/jiao.jpg">'+arr[aa]+'</div>'+
			'</li>';
	//给对应的面板添加回复
	$('.newsList').each(function(index,data){
		if($(data).attr('name') === nowObj.attr("name")){
			$(data).append(answer);
		}
	});	
	$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
}

</script>

</body>
</html>