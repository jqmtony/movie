<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
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
		<!-- <link href="merSkin/black/skin.css" rel="stylesheet" type="text/css" id="skin" /> -->
		<link href="merCss/module.css" rel="stylesheet" type="text/css" />
		<link href="merFont/iconfont.css" rel="stylesheet" type="text/css" />
		<title>登录</title>
		<script src="merJs/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="merJs/jquery.cookie.js" type="text/javascript"></script>
		<script src="merJs/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="merJs/BKframe.js" type="text/javascript"></script>
		<!--[if lt IE 9]>
          <script src="js/html5shiv.js" type="text/javascript"></script>
          <script src="js/css3-mediaqueries.js"  type="text/javascript"></script>
        <![endif]-->
	</head>
	<body class="login-layout Reg_log_style" id="loginstyle">
		<!-- <div class="logintop">
			<span>后台管理界面平台</span>
			<ul>
				<li>
					<a href="">返回首页</a>
				</li>
				<li>
					<a href="#">帮助</a>
				</li>
				<li>
					<a href="#">关于</a>
				</li>
			</ul>
		</div> -->
		<div class="loginbody">
			<div class="login-container">
				<div class="center"> <img src="merImages/logo.png" /></div>
				<div class="space-6"></div>
				<div class="position-relative">
					<div id="login-box" class="login-box widget-box no-border visible">
						<div class="login-main">
							<!--皮肤选择-->
						<div class="skin-section">
							<a href="javascript:void(0)" class="skin-btn clickBombbox iconfont icon-pifushezhi" id="skin-btn"></a>
							<div class="Bombbox">
								<ul class="skin-list">
									<li>
										<a class="colorpick-btn" href="javascript:void(0)" data-val="black" id="default" style="background-color:#222A2D"></a>
									</li>
									<li>
										<a class="colorpick-btn" href="javascript:void(0)" data-val="blue" style="background-color:#438EB9;"></a>
									</li>
									<li>
										<a class="colorpick-btn" href="javascript:void(0)" data-val="green" style="background-color:#72B63F;"></a>
									</li>
									<li>
										<a class="colorpick-btn" href="javascript:void(0)" data-val="gray" style="background-color:#067350;"></a>
									</li>
									<li>
										<a class="colorpick-btn" href="javascript:void(0)" data-val="red" style="background-color:#FF6868;"></a>
									</li>
									<li>
										<a class="colorpick-btn" href="javascript:void(0)" data-val="purple" style="background-color:#6F036B;"></a>
									</li>
								</ul>
							</div>
						</div>
							<div class="clearfix">
								<div class="login_icon"><img src="merImages/login_img.png" /></div>
								<form class="" style=" width:300px; float:right; margin-right:50px;">
									<h4 class="title_name"><img src="merImages/login_title.png" /></h4>
									<font style="color:red;" id="errorMsg"></font>
									<fieldset>
										<ul>
										<c:choose>
											<c:when test="${! empty cookie.remMer}">
												<li class="frame_style form_error"><label class="user_icon iconfont">&#xe620;</label><input name="" type="text" value="${cookie.remMer.value}" data-name="" id="username" /><i></i></li>
											</c:when>
											<c:otherwise>
												<li class="frame_style form_error"><label class="user_icon iconfont">&#xe620;</label><input name="" type="text" value="" data-name="邮箱地址" id="username" /><i>邮箱地址</i></li>
											</c:otherwise>
										</c:choose>
											<li class="frame_style form_error"><label class="password_icon iconfont">&#xe625;</label><input name="" type="password" value="" data-name="密码" id="userpwd" /><i>密码</i></li>
											<li class="frame_style form_error">
												<label class="Codes_icon iconfont">&#xe624;</label>
												<input name="" type="text" data-name="验证码" id="Codes_text" /><i>验证码</i>
												<div class="Codes_region">
												<img id="verify" src="<c:url value='/mer.s?method=createVerification' />" width="100%" height="38px" onclick="againCreateVerify()" style="cursor: pointer;">
											</div></li>
										</ul>
										<div class="space"></div>
										<div class="clearfix">
											<label class="inline">
										<c:choose>
											<c:when test="${! empty cookie.remMer}">
												<input id="remUser" type="checkbox" checked="checked" class="ace">
											</c:when>
											<c:otherwise>
												<input id="remUser" type="checkbox" class="ace">
											</c:otherwise>
										</c:choose>
                                      
                                      <span class="lbl">保存用户</span><a href="javascript:forget()" style="margin-left:150px;">忘记密码</a><br /><br />
                                  </label>
                                 			<button type="button" class="login_btn" id="login_btn"> 登&nbsp;陆 </button>
                                  			<button type="button" class="login_btn" id="register_btn"> 注&nbsp;册</button>
										</div>

										<div class="space-4"></div>
									</fieldset>
								</form>
							</div>
							<!-- <div class="social-or-login center">
								<span class="bigger-110">通知</span>
							</div> -->
							<!-- <div class="social-login ">
								为了更好的体验性（兼容移动端），本网站系统不再对IE8（含IE8）以下浏览器支持，请见谅。
							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- <div class="loginbm">版权所有 2016</div> -->
	</body>
	
	<c:if test="${! empty msg }">
		<script type="text/javascript">
			alert("${msg}");
		</script>
	</c:if>
	<script type="text/javascript">
	//验证登录
	var i = 0;
	$(document).ready(function() {	
		$("input[type='text'],input[type='password']").blur(function() {
			var $el = $(this);
			var inputname=0;
			var $parent = $el.parent();
			$parent.attr('class', 'frame_style').removeClass(' form_error');
			if($el.val() == '') {
				var name=$el.attr("data-name");
				$parent.attr('class', 'frame_style').addClass(' form_error form_prompt');
				$parent.find('i').eq(inputname).html(name+"不能为空").addClass("prompt");
			}
		});
		$("input[type='text'],input[type='password']").focus(function() {
			var $el = $(this);
			var $parent = $el.parent();
			if($el.val() == '') {
				$parent.attr('class', 'frame_style').addClass(' form_errors');
			} else {
				$parent.attr('class', 'frame_style').removeClass(' form_errors');
			}
		});
	  $('#login_btn').on('click', function() {
		var num = 0;
		var str = "";
		$("input[type$='text'],input[type$='password']").each(function(n) {
			var $el = $(this);		
			var inputname=0;
			var $parent = $el.parent();
			if($el.val() == "") {
				var name=$el.attr("data-name");
				$parent.attr('class', 'frame_style').addClass(' form_error form_prompt');
                $parent.find('i').eq(inputname).html(name+"不能为空").addClass("prompt");
				num++;
				againCreateVerify(); //刷新验证码
				return false;
			}
		});
		if(num > 0) {
			return false;
		} else {
			//ajax 判断用户名是否存在
			//location.href = "index.html";
			var objUsername = $('#username');
			var objPwd = $('#userpwd');
			var objRemUser = $('#remUser');
			var isRemUser = objRemUser.prop('checked');
			var objVerify = $('#Codes_text');
			var data = {
					merEmail : objUsername.val(),
					merPwd : objPwd.val(),
					isRemUser : isRemUser,
					verify : objVerify.val()
			};
			
			$.post("<c:url value='mer.s?method=login' />",data,function(data){
				if(data === "yes"){
					location.href = "merIndex.jsp";
				}else{
					againCreateVerify(); //刷新验证码
					$('#errorMsg').html(data);
				}
			});
		}
	});
	  
	  //注册
	  $('#register_btn').on('click', function() {
			var num = 0;
			var str = "";
			$("input[type$='text'],input[type$='password']").each(function(n) {
				var $el = $(this);		
				var inputname=0;
				var $parent = $el.parent();
				if($el.val() == "") {
					var name=$el.attr("data-name");
					$parent.attr('class', 'frame_style').addClass(' form_error form_prompt');
	                $parent.find('i').eq(inputname).html(name+"不能为空").addClass("prompt");
					num++;
					againCreateVerify(); //刷新验证码
					return false;
				}
			});
			if(num > 0) {
				return false;
			} else {
				//ajax 判断用户名是否存在
				//location.href = "index.html";
				var objUsername = $('#username');
				var objPwd = $('#userpwd');
				var objRemUser = $('#remUser');
				var isRemUser = objRemUser.prop('checked');
				var objVerify = $('#Codes_text');
				var data = {
						merEmail : objUsername.val(),
						merPwd : objPwd.val(),
						isRemUser : isRemUser,
						verify : objVerify.val()
				};
				
				$.post("<c:url value='mer.s?method=register' />",data,function(data){
					if(data === "yes"){
						alert("注册链接已发送至您的邮箱，请根据邮件提示激活账号！")
						history.go(0);
					}else{
						againCreateVerify(); //刷新验证码
						$('#errorMsg').html(data);
					}
				}); 
			}
		});
	})
	//框架设置
	$(function() {
		$("#loginstyle").BKframe({
               //必须保留否则无法进行皮肤更换，以及兼容移动端
               
		})
	});
	
	//刷新验证码
	function againCreateVerify(){
		var data;
		var objVerify = $('#verify'); 
		$.post("<c:url value='/mer.s?method=createVerification' />",data,function(data){
			objVerify.attr("src","<c:url value='/mer.s?method=createVerification' />");
		}); 
	}
	
	//忘记密码
	function forget(){
		var num = 0;
		var str = "";
		$("input[type$='text']").each(function(n) {
			var $el = $(this);		
			var inputname=0;
			var $parent = $el.parent();
			if($el.val() == "") {
				var name=$el.attr("data-name");
				$parent.attr('class', 'frame_style').addClass(' form_error form_prompt');
                $parent.find('i').eq(inputname).html(name+"不能为空").addClass("prompt");
                againCreateVerify(); //刷新验证码
				num++;
				return false;
			}
		});
		if(num > 0) {
			return false;
		} else {
			//ajax 判断用户名是否存在
			//location.href = "index.html";
			var objUsername = $('#username');
			var objPwd = $('#userpwd');
			var objRemUser = $('#remUser');
			var isRemUser = objRemUser.prop('checked');
			var objVerify = $('#Codes_text');
			var data = {
					merEmail : objUsername.val(),
					verify : objVerify.val()
			};
			
			$.post("<c:url value='mer.s?method=fogetBefore' />",data,function(data){
				if(data === "yes"){
					alert("修改密码链接已发送至您的邮箱，请根据链接修改密码！")
					history.go(0);
				}else{
					againCreateVerify(); //刷新验证码
					$('#errorMsg').html(data);
				}
			}); 
		}
	}
</script>
</html>


