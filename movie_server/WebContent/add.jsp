<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>西域游新增</title>
    <meta name="keywords" content="西域游新增">
    <meta name="description" content="西域游新增">
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
</body>
</html>
	<div style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
		<div class="ftitle">用户 信息</div>
			<form id="fm" method="post" novalidate>
				<div class="fitem">
					<label>编 号:</label>
					<input name="firstname" class="easyui-validatebox" required="true">
				</div>
				<div class="fitem">
					<label>名 字:</label>
					<input name="lastname" class="easyui-validatebox" required="true">
				</div>
				<div class="fitem">
					<label>电 话:</label>
					<input name="phone">
				</div>
				<div class="fitem">
					<label>地 址:</label>
					<input name="add" class="easyui-validatebox" validType="email">
				</div>
				<div class="fitem">
					<label>名 字:</label>
					<input name="lastname" class="easyui-validatebox" required="true">
				</div>
				<div class="fitem">
					<label>电 话:</label>
					<input name="phone">
				</div>
				<div class="fitem">
					<label>地 址:</label>
					<input name="add" class="easyui-validatebox" validType="email">
				</div>
				<div class="fitem">
					<label>名 字:</label>
					<input name="lastname" class="easyui-validatebox" required="true">
				</div>
			</form>
	</div>
	<div>
		<a class="btn btn-primary" href="#" role="button">保 存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
	</div>
</body>	
</html>
							
							
								
							