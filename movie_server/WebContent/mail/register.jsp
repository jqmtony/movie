<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>hello jsp</title>
	</head>
	
	<body onload="out(5)">
		<div align="center">
			<h1>商户账号激活成功 </h1>  <font id="times" style="color:red;size:20;"></font> 秒后跳转... <br/><br/>
			<a href="http://${addrIp}merLogin.jsp">点击手动跳转</a>
		</div>
		
		<script>
				function out(obj){
					var i = obj ;
					if(i<=0){
						document.location.href="http://${addrIp}merLogin.jsp";
						return;
					}
					document.getElementById('times').innerHTML = i;
					i--;
					setTimeout("out("+i+")",1000);
				}
		</script>
	</body>
</html>