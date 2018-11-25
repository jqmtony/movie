<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员新增</title>
    <meta name="keywords" content="管理员新增">
    <meta name="description" content="管理员新增">
    <link rel="shortcut icon" href="favicon.ico"> 
	<link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
</head>

<body class="gray-bg">

    
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>   

	<div style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
		<div class="ftitle">用户 信息</div>
			<form id="fm" method="post" novalidate>
				<div class="fitem">
					<label>注册码:</label>
					<input name="adminRegisterCode" id="adminRegisterCode" class="easyui-validatebox" required="true" onBlur="inputBlur(1)" onFocus="inputFocus(1)">
					<font id="font1"></font>
				</div>
				<div class="fitem">
					<label>姓 名:</label>
					<input name="adminName" id="adminName" class="easyui-validatebox" required="true" onBlur="inputBlur(2)" onFocus="inputFocus(2)">
					<font id="font2"></font>
				</div>
				<div class="fitem">
					<label>电 话:</label>
					<input name="adminTel" id="adminTel" onBlur="inputBlur(3)" onFocus="inputFocus(3)">
					<font id="font3"></font>
				</div>
				<div class="fitem">
					<label>地 址:</label>
					<select name="adminAddr" id="adminAddr">
						
					</select>
					<font id="font4"></font>
				</div>
				<div class="fitem">
					<label>邮 箱:</label>
					<input name="adminEmail" id="adminEmail" class="easyui-validatebox" required="true" validType="email" onBlur="inputBlur(4)" onFocus="inputFocus(4)">
					<font id="font5"></font>
				</div>
				<div class="fitem">
					<label>密 码:</label>
					<input name="adminPwd" id="adminPwd" onBlur="inputBlur(5)" onFocus="inputFocus(5)">
					<font id="font6"></font>
				</div>
				<div class="fitem">
					<label>确认密码:</label>
					<input name="adminPwd2" id="adminPwd2" class="easyui-validatebox" onBlur="inputBlur(6)" onFocus="inputFocus(6)" >
					<font id="font7"></font>
				</div>
			</form>
	</div>
	<div>
		<a class="btn btn-primary" href="#" role="button">保 存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
	</div>
	
</body>	
<script type="text/javascript">
		function inputBlur(num){
			var data = {
					status:num,
					adminRegisterCode:$('#adminRegisterCode').val(),
					adminName:$('#adminName').val(),
					adminTel:$('#adminTel').val(),
					adminEmail:$('#adminEmail').val(),
					adminPwd:$('#adminPwd').val(),
					adminPwd2:$('#adminPwd2').val()
					};
			$.post("<c:url value='/admin.s?method=registerBlur' />",data,function(data){
				$('#font'+data.status).html(data.html);
			});
		}
		
		function inputFocus(num){
			$('#font'+num).html("");
		}
	</script>
</html>
							
							
								
							