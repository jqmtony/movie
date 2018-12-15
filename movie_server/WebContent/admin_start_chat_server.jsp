<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>hello jsp</title>
		<script type="text/javascript" src="js/jquery.min.js"></script>
	</head>
	
	<body>
		<input type="button" value="开启聊天服务器" onclick="startChatServer()">
	</body>
	<script type="text/javascript">
		//开启聊天服务
		function startChatServer(){
			var data;
			$.post("<c:url value='/admin.s?method=startChatServer' />",data,function(data){
				
			});
		}
	</script>
</html>