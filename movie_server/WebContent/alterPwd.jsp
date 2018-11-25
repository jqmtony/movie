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
		<form action="<c:url value='/admin.s' />" method="post">
			<input type="hidden" name="method" value="alterPwd">
			<input type="hidden" name="adminId" value="${alterAdmin.adminId }">
			<h1>修改密码</h1>
			邮箱：<input type="text" name="adminEmail" value="${alterAdmin.adminEmail}"><br/>
			密码：<input type="password" name="adminPwd" /><br />
			确认密码：<input type="password" name="addminPwd2" /><br/>
			<input type="submit" value="确认修改" />
			<br />
			${go}
		</form>
		<c:if test="${! empty msg}">
			<script type="text/javascript">
				alter('${msg}');
			</script>
		</c:if>
	</body>
</html>