<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>hello jsp</title>
		<script type="text/javascript" src="jquery.min.js"></script>
	</head>
	
	<body onload="openChat()">
		接收：<textarea id="recive"></textarea><br><br>
		发送：<textarea id="send"></textarea><br><br>
		<input type="button" value="发送" onclick="send()">
		<script type="text/javascript">
			//开启线程
			function openChat(){
				var data;
				$.post("<c:url value='/chat.s?method=server' />",data,function(data){
					
				})
				$.post("<c:url value='/chat.s?method=client' />",data,function(data){
					alert(data)
					$("#recive").val(data)
				})
			};
			
			//发送
			function send(){
				var data = {content : $("#send")}
				$.post("<c:url value='/chat.s?method=send' />",data,function(data){
					
				})
			}
		</script>
	</body>
</html>