<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:if test="${empty justAlterMer }">
	<jsp:forward page="server404.jsp"></jsp:forward>
</c:if>
	<head>
		<meta charset="UTF-8">
		<title>登录</title>
		
		<link rel="stylesheet" href="css/index.css" />
		
		<link rel="stylesheet" href="css/bootstrap.min.css" /><!---可无视-->
		<script src="js/jquery.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="content">
			<div class="bidTitle">影视天堂 · 商户后台修改密码</div>
			<div class="logCon">
				<span>重置密码:</span>
				<input class="bt_input" type="text" id="pwd1"/>
				<span>确认密码:</span>
				<input class="bt_input" type="text" id="pwd2"/>
				<input type="button" class="logingBut" onclick="alter()" value="修改" />
			</div>
		</div>
	</body>
	<script type="text/javascript">
		
		function alter(){
			var pwd1 = $('#pwd1');
			var pwd2 = $('#pwd2');
			var data = {
					pwd1 : pwd1.val(),
					pwd2 : pwd2.val()
			};
			$.post("<c:url value='/mer.s?method=forget' />",data,function(data){
				if(data === "yes"){
					alert("修改成功！返回登录！");
					window.location.href = "<c:url value='/merLogin.jsp' />";
					return;
				}else{
					alert(data);
				}
			});
		}
	</script>
</html>