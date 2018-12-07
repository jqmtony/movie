<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>hello jsp</title>
	</head>
	
	<body>
		<form action="<c:url value='/t.s?method=upload' />" method="post" enctype="multipart/form-data">
			<input type="file" id="file" name="file">
			<input type="submit" value="上传">
		</form>
	</body>
</html>