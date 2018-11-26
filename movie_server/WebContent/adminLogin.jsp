<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>HTML5 登录动画特效</title>
<link rel="stylesheet" href="adminLib/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="adminCss/login.css" />
<link rel="stylesheet" href="adminCss/tooltips.css" />


<script type="text/javascript" src="adminJs/jquery-1.9.1.js"></script>
<script type="text/javascript" src="adminJs/jquery.pure.tooltips.js"></script>
<script type="text/javascript" src="adminLib/layui/layui.js"></script>
<style>
body {
	margin:0;
	padding:0;
	overflow:hidden;
	background:#2d9b95;
	background:-moz-radial-gradient(center,ellipse cover,#2d9b95 0%,#0e1329 100%);
	background:-webkit-radial-gradient(center,ellipse cover,#2d9b95 0%,#0e1329 100%);
	background:-o-radial-gradient(center,ellipse cover,#2d9b95 0%,#0e1329 100%);
	background:-ms-radial-gradient(center,ellipse cover,#2d9b95 0%,#0e1329 100%);
	background:radial-gradient(ellipse at center,#2d9b95 0%,#0e1329 100%);
	filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#2d9b95',endColorstr='#0e1329',GradientType=1 );
	background:-webkit-gradient(radial,center center,0px,center center,100%,color-stop(0%,#2d9b95),color-stop(100%,#0e1329));
}
.box {background:#000000; position:absolute;margin-left:-14%; z-index:3;opacity: 0.6;left:50%;}
.box-login{width:450px; height:295px;margin-top:-10%;top:50%;}
.box-register{width:450px; height:385px;margin-top:-8%;top:46%;}
.box-reset{width:450px; height:385px;margin-top:-8%;top:46%;}
#register {display:none;}
#reset{display:none;}
</style>

</head>
<body>

<canvas></canvas>

<div class="beg-login-box box box-login layui-anim-up" id="login">
	<header>
		<h1 style="color:#FFFFFF">欢迎登录</h1>
	</header>
	<div class="beg-login-main">
		<form action="<c:url value='/admin.s' />" class="layui-form" method="post"><input name="__RequestVerificationToken" type="hidden" value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />		
			<input type="hidden" name="method" value="login">
			<div class="layui-form-item">
				<label class="beg-login-icon">
				<i class="layui-icon">&#xe612;</i>
			</label>
				<input id="adminEmail" type="text" name="adminEmail" onblur="adminEmailBlur()" value="${param.adminEmail}" lay-verify="userName" autocomplete="off" placeholder="请输入邮箱地址" class="layui-input">
			</div>
			<div class="layui-form-item">
				<label class="beg-login-icon">
				<i class="layui-icon">&#xe642;</i>
			</label>
				<input id="adminPwd" type="password" name="adminPwd" lay-verify="password" autocomplete="off" placeholder="请输入密码" class="layui-input">
			</div>
			<div class="layui-form-item">
			
				<div class="beg-pull-left beg-login-remember" style="color:#FFFFFF;margin-top: 1%;">
					<label>记住帐号？</label>
					<input type="checkbox" id="rememberAdminEmail" name="rememberAdminEmail" lay-skin="switch" lay-text="ON|OFF" checked>
				</div>
				
				<div class="beg-pull-right" style="margin-top: 4%;">
					<a class="btn pull-left btn-link text-muted" onClick="goto_forget()" style="color:#FFFFFF;cursor:pointer;">忘记密码?</a>
				</div>
				<div class="beg-clear"></div>
			</div>
			
			<div class="layui-form-item">
				<div class="beg-pull-left beg-login-remember" style="color:#FFFFFF;margin-top: -2%;">
					<button class="layui-btn layui-btn-radius layui-btn-primary" onclick="goto_register();return false;">
					<i class="layui-icon">&#xe650;</i> 注册
					</button>
				</div>
				
				<div class="beg-pull-right">
					<button type="submit" class="layui-btn layui-btn-radius" style="margin-top: 4%;">
					<i class="layui-icon">&#xe650;</i> 登录
					</button>
				</div>
			</div>
		</form>
	</div>
</div>

<div class="beg-login-box box box-register layui-anim-rotate" id="register">
	<header>
		<h1 style="color:#FFFFFF">欢迎注册</h1>
	</header>
	<div class="beg-login-main">
		<form action="" class="layui-form" method="post"><input name="__RequestVerificationToken" type="hidden" value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />		
			<div class="layui-form-item">
				<label class="beg-login-icon">
				<i class="layui-icon">&#xe612;</i>
			</label>
				<input id="adminReEmail" type="text" name="adminReEmail" lay-verify="userName" value="${param.adminEmail }" autocomplete="off" placeholder="请输入邮箱" class="layui-input" onblur="registerBlur(1)">
			</div>
			<div class="layui-form-item">
				<label class="beg-login-icon">
					<i class="layui-icon">&#xe642;</i>
				</label>
				<input id="adminRePwd" type="password" name="adminRePwd" lay-verify="password" autocomplete="off" placeholder="请输入密码" class="layui-input" onblur="registerBlur(2)">
			</div>
			<div class="layui-form-item">
				<label class="beg-login-icon">
					<i class="layui-icon">&#xe642;</i>
				</label>
				<input id="adminRePwd2" type="password" name="adminRePwd2" lay-verify="password" autocomplete="off" placeholder="确认密码" class="layui-input" onblur="registerBlur(3)">
			</div>
			<div class="layui-form-item">
				<label class="beg-login-icon">
					<i class="layui-icon">&#xe6b2;</i>
				</label>
				<input id="adminRegisterCode" type="text" name="adminRegisterCode" lay-verify="regcode" value="${param.adminRegisterCode }" autocomplete="off" placeholder="请输入注册码" class="layui-input" onblur="registerBlur(4)">
			</div>
			<div class="layui-form-item">
				<div class="beg-pull-left beg-login-remember" style="color:#FFFFFF;margin-top: 6%;">
					<button class="layui-btn" onclick="register();return false;">
					<i class="layui-icon">&#xe650;</i> 注册
					</button>
				</div>
				
				<div class="beg-pull-right">
					<button class="layui-btn layui-btn layui-btn-primary" style="margin-top: 18%;" onClick="goto_login();return false;">
					<i class="layui-icon">&#xe650;</i> 返回登录
				</button>
				</div>
			</div>
		</form>
	</div>
</div>

<div class="beg-login-box box box-reset layui-anim-rotate" id="reset">
	<header>
		<h1 style="color:#FFFFFF">重置密码</h1>
	</header>
	<div class="beg-login-main">
		<form action="" class="layui-form" method="post"><input name="__RequestVerificationToken" type="hidden" value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />		
			<div class="layui-form-item">
				<label class="beg-login-icon">
				<i class="layui-icon">&#xe612;</i>
			</label>
				<input id="adminFoEmail" type="text" name="adminFoEmail" lay-verify="userName" autocomplete="off" placeholder="请输入邮箱" class="layui-input" onblur="resetPwdBlur(1)">
			</div>
			<div class="layui-form-item">
				<label class="beg-login-icon">
					<i class="layui-icon">&#xe6b2;</i>
				</label>
				<input id="adminFoRegisterCode" type="text" name="adminFoRegisterCode" lay-verify="regcode" autocomplete="off" placeholder="请输入注册码" class="layui-input" onblur="resetPwdBlur(4)">
			</div>
			<div class="layui-form-item">
				<label class="beg-login-icon">
					<i class="layui-icon">&#xe642;</i>
				</label>
				<input id="adminFoPwd" type="password" name="adminFoPwd" lay-verify="password" autocomplete="off" placeholder="请输入重置密码" class="layui-input" onblur="resetPwdBlur(2)">
			</div>
			<div class="layui-form-item">
				<label class="beg-login-icon">
					<i class="layui-icon">&#xe642;</i>
				</label>
				<input id="adminFoPwd2" type="password" name="adminFoPwd2" lay-verify="password" autocomplete="off" placeholder="确认密码" class="layui-input" onblur="resetPwdBlur(3)">
			</div>
			<div class="layui-form-item">
				<div class="beg-pull-left beg-login-remember" style="color:#FFFFFF;margin-top: 6%;">
					<button class="layui-btn" onclick="reset_pwd();return false;">
					<i class="layui-icon">&#xe650;</i> 重置
					</button>
				</div>
				
				<div class="beg-pull-right">
					<button class="layui-btn layui-btn layui-btn-primary" style="margin-top: 18%;" onClick="goto_login();return false;">
					<i class="layui-icon">&#xe650;</i> 返回登录
				</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
window.requestAnimFrame = (function(){
  return  window.requestAnimationFrame || 
    window.webkitRequestAnimationFrame || 
    window.mozRequestAnimationFrame    || 
    window.oRequestAnimationFrame      || 
    window.msRequestAnimationFrame     ||  
    function( callback ){
    window.setTimeout(callback, 1000 / 60);
  };
})();

var canvas = document.getElementsByTagName("canvas")[0];
var ctx = canvas.getContext("2d");
var w = $(document).width();
var h = $(document).height();
canvas.width = w;
canvas.height = h;

var mols = [];

function init(){
  for(var i=0;i<18;i++){
    var mol = new generate_mol("C8H10N4O2");
    mols.push(mol);
    var mol = new generate_mol("C6H6O");
    mols.push(mol);
    var mol = new generate_mol("C6H6");
    mols.push(mol);
  }
}

function draw(){
  canvas.width = canvas.width;
  for(var i=0;i<mols.length;i++){
    var m = mols[i];
    m.x += m.vx;
    if(m.x >= w-100 || m.x <= 0){
      m.vx = -m.vx;
    }
    m.y += m.vy;
    if(m.y >= h-100 || m.y <= 0){
      m.vy = -m.vy;
    }
    
    m.r += 0.005;
    m.draw();
  }
}

function generate_mol(mol){
  this.x = Math.random()*w;
  this.y = Math.random()*h;
  this.vx = Math.random()*-2;
  this.vy = Math.random()*2;
  this.vr = 0.1;
  this.r = Math.random()*Math.PI;
  this.draw = function(){
    if(mol == "C6H6O"){
      //Phenol
      ctx.save();
      ctx.translate(this.x+20,this.y+80);
      ctx.rotate(this.r);
      ctx.translate(-this.x+20,-this.y-80);
      ctx.beginPath();
      ctx.moveTo(this.x,this.y + 5);
      ctx.lineTo(this.x,this.y + 30);
      ctx.lineTo(this.x - 26,this.y + 45);
      ctx.lineTo(this.x - 26,this.y + 75);
      ctx.lineTo(this.x,this.y + 90);
      ctx.lineTo(this.x + 26,this.y + 75);
      ctx.lineTo(this.x + 26,this.y + 45);
      ctx.lineTo(this.x,this.y + 30);
      ctx.moveTo(this.x - 20,this.y + 47);
      ctx.lineTo(this.x - 20,this.y + 73);
      ctx.moveTo(this.x,this.y + 83);
      ctx.lineTo(this.x + 22,this.y + 70);
      ctx.moveTo(this.x,this.y + 36);
      ctx.lineTo(this.x + 22,this.y + 49);
      ctx.strokeStyle = "rgba(255,255,255,0.2)";
      ctx.lineWidth = 3;
      ctx.stroke();
      ctx.fillStyle = "rgba(255,255,255,0.2)";
      ctx.font = "15px Arial";
      ctx.fillText("OH", this.x - 5, this.y);
      ctx.closePath();
      ctx.restore();
    }
    else if(mol == "C8H10N4O2"){
      //Caffeine
      ctx.save();
      ctx.translate(this.x+20,this.y+80);
      ctx.rotate(this.r);
      ctx.translate(-this.x+20,-this.y-80);
      ctx.beginPath();
      ctx.moveTo(this.x,this.y + 5);
      ctx.lineTo(this.x,this.y + 22);
      ctx.moveTo(this.x-9,this.y + 35);
      ctx.lineTo(this.x - 26,this.y + 45);
      ctx.lineTo(this.x - 26,this.y + 75);
      ctx.lineTo(this.x,this.y + 90);
      ctx.lineTo(this.x + 18,this.y + 80);
      ctx.moveTo(this.x + 26,this.y + 68);
      ctx.lineTo(this.x + 26,this.y + 45);
      ctx.lineTo(this.x + 9,this.y + 35);
      ctx.moveTo(this.x - 20,this.y + 47);
      ctx.lineTo(this.x - 20,this.y + 73);
      ctx.moveTo(this.x + 23,this.y + 42);
      ctx.lineTo(this.x + 36,this.y + 32);
      ctx.moveTo(this.x + 26,this.y + 46);
      ctx.lineTo(this.x + 39,this.y + 36);
      ctx.moveTo(this.x + 34,this.y + 81);
      ctx.lineTo(this.x + 48,this.y + 90);
      ctx.moveTo(this.x - 2,this.y + 88);
      ctx.lineTo(this.x - 2,this.y + 110);
      ctx.moveTo(this.x + 3,this.y + 88);
      ctx.lineTo(this.x + 3,this.y + 110);
      ctx.moveTo(this.x - 26,this.y + 45);
      ctx.lineTo(this.x - 46,this.y + 38);
      ctx.moveTo(this.x - 60,this.y + 44);
      ctx.lineTo(this.x - 74,this.y + 58);
      ctx.lineTo(this.x - 61,this.y + 77);
      ctx.moveTo(this.x - 58,this.y + 49);
      ctx.lineTo(this.x - 68,this.y + 59);
      ctx.moveTo(this.x - 46,this.y + 82);
      ctx.lineTo(this.x - 26,this.y + 73);
      ctx.moveTo(this.x - 60,this.y + 86);
      ctx.lineTo(this.x - 70,this.y + 100);
      ctx.strokeStyle = "rgba(255,255,255,0.2)";
      ctx.lineWidth = 3;
      ctx.stroke();
      ctx.fillStyle = "rgba(255,255,255,0.2)";
      ctx.font = "15px Arial";
      ctx.fillText("CH", this.x - 5, this.y);
      ctx.fillText("3", this.x + 18, this.y+6);
      ctx.fillText("N", this.x - 5, this.y+37);
      ctx.fillText("O", this.x + 38, this.y+35);
      ctx.fillText("N", this.x + 21, this.y+81);
      ctx.fillText("CH", this.x + 50, this.y+99);
      ctx.fillText("3", this.x + 72, this.y+105);
      ctx.fillText("O", this.x - 5, this.y+124);
      ctx.fillText("N", this.x - 59, this.y+42);
      ctx.fillText("N", this.x - 59, this.y+84);
      ctx.fillText("H  C", this.x - 98, this.y+114);
      ctx.fillText("3", this.x - 87, this.y+119);
      ctx.closePath();
      ctx.restore();
    }
    else if(mol == "C6H6"){
      //Benzene
      ctx.save();
      ctx.translate(this.x+20,this.y+80);
      ctx.rotate(this.r);
      ctx.translate(-this.x+20,-this.y-80);
      ctx.beginPath();
      ctx.moveTo(this.x,this.y + 30);
      ctx.lineTo(this.x - 26,this.y + 45);
      ctx.lineTo(this.x - 26,this.y + 75);
      ctx.lineTo(this.x,this.y + 90);
      ctx.lineTo(this.x + 26,this.y + 75);
      ctx.lineTo(this.x + 26,this.y + 45);
      ctx.lineTo(this.x,this.y + 30);
      ctx.moveTo(this.x - 20,this.y + 47);
      ctx.lineTo(this.x - 20,this.y + 73);
      ctx.moveTo(this.x,this.y + 83);
      ctx.lineTo(this.x + 22,this.y + 70);
      ctx.moveTo(this.x,this.y + 36);
      ctx.lineTo(this.x + 22,this.y + 49);
      ctx.strokeStyle = "rgba(255,255,255,0.2)";
      ctx.lineWidth = 3;
      ctx.stroke();
      ctx.closePath();
      ctx.restore();
    }
  }
}

init();

function animloop() {
  draw();
  requestAnimFrame(animloop);
}

$(function(){
	layui.use(['layer', 'form'], function() {
		var layer = layui.layer,
			$ = layui.jquery,
			form = layui.form();
	});
})

//登录失焦
function adminEmailBlur(){
	var data = {
			adminEmail:$('#adminEmail').val()
	};
	
	$.post("<c:url value='/admin.s?method=loginAdminEmailRegx' />",data,function(data){
		if(data != ""){
			$.pt({
				target: $("#adminEmail"),
				position: 'r',
				align: 't',
				width: 'auto',
				height: 'auto',
				content:data
			});
		}
	});
}


function goto_register(){
	$("#register").show();
	$("#login").hide();
	$("#reset").hide();
}

//注册前校验  ajax
function registerBlur(num){
	var data = {
			status:num,
			adminEmail:$('#adminReEmail').val(),
			adminPwd:$('#adminRePwd').val(),
			adminPwd2:$('#adminRePwd2').val(),
			adminRegisterCode:$('#adminRegisterCode').val()
	};
	$.post("<c:url value='/admin.s?method=registerBlur' />",data,function(data){
		if(data != ""){
			var dataJson = eval("("+data+")");
			var tar = dataJson.chose;
			if(dataJson.msg != ""){
				$.pt({
					target: $("#"+tar),
					position: 'r',
					align: 't',
					width: 'auto',
					height: 'auto',
					content:dataJson.msg
				});
			}
		}
	});
}

 //注册
function register(){
	var data = {
			adminEmail:$('#adminReEmail').val(),
			adminPwd:$('#adminRePwd').val(),
			adminPwd2:$('#adminRePwd2').val(),
			adminRegisterCode:$('#adminRegisterCode').val()
	};
	$.post("<c:url value='/admin.s?method=register' />",data,function(data){
		var str = data;
		var tar;
		if(str.indexOf("两次输入的密码不相同")!=-1){  //返回值是关于密码的
			tar = $('#adminRePwd2');
		}else if(str.indexOf("邮箱")!=-1){
			tar = $('#adminReEmail');
		}else  if(str.indexOf("注册码")!=-1){
			tar = $('#adminRegisterCode');
		}else if(str.indexOf("密码格式不正确")!=-1){
			tar = $('#adminRePwd');
		}
		if(tar != null){
			$.pt({
				target:tar,
				position: 'r',
				align: 't',
				width: 'auto',
				height: 'auto',
				content:str
			});
		}else{
			alert(str);
			history.go(0);
		}
	});
} 

function goto_login(){
	$("#register").hide();
	$("#login").show();
	$("#reset").hide();
}
function goto_forget(){
	$("#register").hide();
	$("#login").hide();
	$("#reset").show();
}

//修改密码
function resetPwdBlur(num){
	var data = {
			status:num,
			adminEmail:$('#adminFoEmail').val(),
			adminPwd:$('#adminFoPwd').val(),
			adminPwd2:$('#adminFoPwd2').val(),
			adminRegisterCode:$('#adminFoRegisterCode').val()
	};
	$.post("<c:url value='/admin.s?method=resetPwdBlur' />",data,function(data){
		if(data != ""){
			var dataJson = eval("("+data+")");
			var tar = dataJson.chose;
			if(dataJson.msg != ""){
				$.pt({
					target: $("#"+tar),
					position: 'r',
					align: 't',
					width: 'auto',
					height: 'auto',
					content:dataJson.msg
				});
			}
		}
	});
}
/* function reset_pwd(){
	var regcode_reset = $("#regcode_reset").val();
	var username_reset = $("#username_reset").val();
	var password_reset = $("#password_reset").val();
	var determine_password_reset = $("#determine_password_reset").val();
	if(username_reset.trim().length < 6){
		$.pt({
			target: $("#username_reset"),
			position: 'r',
			align: 't',
			width: 'auto',
			height: 'auto',
			content:"用户名不能少于6位"
		});
		return false;
	}
	//注册码不能为空
	if(regcode_reset == ""){
		$.pt({
			target: $("#regcode_reset"),
			position: 'r',
			align: 't',
			width: 'auto',
			height: 'auto',
			content:"注册码不能为空"
		});
		return false;
	}
	//密码只能是5-15位
	var regExp = new RegExp("^.{5,15}$");
	if(!regExp.test(password_reset)){
		$.pt({
			target: $("#password_reset"),
			position: 'r',
			align: 't',
			width: 'auto',
			height: 'auto',
			content:"密码只能是5-15位"
		});
		return false;
	}
	//两次输入的密码要一致
	if(password_reset != determine_password_reset){
		$.pt({
			target: $("#determine_password_reset"),
			position: 'r',
			align: 't',
			width: 'auto',
			height: 'auto',
			content:"两次输入的密码不一致"
		});
		return false;
	}
	alert("密码重置成功");
} */
animloop();
</script>
<c:if test="${! empty loginMsg }">   
	<script type="text/javascript">
		var str = "${loginMsg}";
		var tar = $("#login");
		if(str.indexOf("密码")!=-1){  //返回值是关于密码的
			tar = $('#adminPwd');
		}else if(str.indexOf("邮箱")!=-1){
			tar = $('#adminEmail');
		}
		if(tar === $("#login")){
			$.pt({
				target:tar,
				position: 't',
				align: 't',
				width: 'auto',
				height: 'auto',
				content:str
			});
		}else if(tar != null){
			$.pt({
				target:tar,
				position: 'r',
				align: 't',
				width: 'auto',
				height: 'auto',
				content:str
			});
		}
	</script>
</c:if>

<%-- <c:if test="${! empty registerMsg }">
	<script type="text/javascript">
		var str = "${registerMsg}";
		var tar = $("#register");
		if(str.indexOf("两次输入的密码不相同")!=-1){  //返回值是关于密码的
			tar = $('#adminRePwd2');
		}else if(str.indexOf("邮箱")!=-1){
			tar = $('#adminReEmail');
		}else  if(str.indexOf("注册码")!=-1){
			tar = $('#adminRegisterCode');
		}else if(str.indexOf("密码格式不正确")!=-1){
			tar = $('#adminRePwd');
		}
		if(tar === $("#register")){
			$.pt({
				target:tar,
				position: 't',
				align: 't',
				width: 'auto',
				height: 'auto',
				content:str
			});
		}else if(tar != null){
			$.pt({
				target:tar,
				position: 'r',
				align: 't',
				width: 'auto',
				height: 'auto',
				content:str
			});
		}
	</script>
</c:if> --%>


<c:if test="${! empty isRemember}">
	<script type="text/javascript">
		$('#rememberAdminEmail').attr("checked",false);
	</script>
</c:if>
<c:if test="${! empty cookie.rememberAdmin}">
	<script type="text/javascript">
		var ad = $('#adminEmail').val();
		if(ad === null || ad === ""){
			$('#adminEmail').val('${cookie.rememberAdmin.value}');
		}
	</script>
</c:if>
</body>
</html>

