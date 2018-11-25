<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>电影售票系统后台 - 登录</title>
    <meta name="keywords" content="西域游后台">
    <meta name="description" content="西域游后台啊哈啊">

    <link rel="shortcut icon" href="favicon.ico"> 
	<link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                
                <h1 class="logo-name"><img src="img/logo-lin.png"></h1>

            </div>
            <h3>欢迎使用 电影网站</h3>

            <form class="m-t" role="form" action="<c:url value='/admin.s' />">
            	<input type="hidden" name="method" value="login">
                <div class="form-group">
                    <input type="email" name="adminEmail" class="form-control" placeholder="用户名(邮箱地址)" required="" value="${param.adminEmail }">
                </div>
                <div class="form-group">
                    <input type="password" name="adminPwd" class="form-control" placeholder="密码" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


                <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="#">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>   
</body>

</html>