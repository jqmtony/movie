<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>Contact</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="" />

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- pop-up -->
<link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />
<!-- //pop-up -->
<link href="css/easy-responsive-tabs.css" rel='stylesheet' type='text/css'/>
<link rel="stylesheet" type="text/css" href="css/zoomslider.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="css/font-awesome.css" rel="stylesheet"> 
<script type="text/javascript" src="js/modernizr-2.6.2.min.js"></script>



<!--/web-fonts-->
<link href='http://fonts.googleapis.com/css?family=Tangerine:400,700' rel='stylesheet' type='text/css'>
<link href="http://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<!--//web-fonts-->

</head>
<style type="text/css">
	.con4{
	     width: 300px;
	     height: auto;
	     overflow: hidden;
	     margin: 20px auto;
	     color: #FFFFFF;
	}
	.con4 .btn{
	     width: 100%;
	     height: 40px;
	     line-height: 30px;
	     text-align: center;
	     background: red;
	     display: block;
	     font-size: 16px;
	     border-radius: 5px;
	}
	.btn:hover{
		background: #d8b49c;
	}
	.upload{
	     float: left;
	     position: relative;
	}
	.upload_pic{
	     display: block;
	     width: 100%;
	     height: 40px;
	     position: absolute;
	     left: 0;
	     top: 0;
	     opacity: 0;
	     border-radius: 5px;
	}
	#cvs{
		/*border: 1px solid #000;*/
		margin:20px 0 20px 50px;
	}
</style>
<script type="text/javascript">
function writeImg(url){
	var image = new Image();
	// 设置src属性 
	image.src = url;
	var max=200;
	// 绑定load事件处理器，加载完成后执行，避免同步问题
	image.onload = function(){ 
		// 获取 canvas DOM 对象 
		var canvas = document.getElementById("cvs"); 
		// 如果高度超标 宽度等比例缩放 *= 
		/*if(image.height > max) {
			image.width *= max / image.height; 
			image.height = max;
		} */
		// 获取 canvas的 2d 环境对象, 
		var ctx = canvas.getContext("2d"); 
		// canvas清屏 
		ctx.clearRect(0, 0, canvas.width, canvas.height); 
		// 重置canvas宽高
		/*canvas.width = image.width;
		canvas.height = image.height;
		if (canvas.width>max) {canvas.width = max;}*/
		// 将图像绘制到canvas上
		// ctx.drawImage(image, 0, 0, image.width, image.height);
		ctx.drawImage(image, 0, 0, 200, 200);
		// 注意，此时image没有加入到dom之中
	};
}
</script>
<c:if test="${empty loginedUser }">
	<jsp:forward page="userLogin.jsp" />
</c:if>
<body>
<c:if test="${empty provincesMap }">
	<jsp:forward page="/user.s?method=getMapByProvinces"></jsp:forward>
</c:if>
<!--/main-header-->
  <!--/banner-section-->
	<%@ include file="head.jsp"%>
			<!--/content-inner-section-->
				<div class="w3_content_agilleinfo_inner">
					<div class="agile_featured_movies">
							<div class="inner-agile-w3l-part-head">
					            <h3 class="w3l-inner-h-title">${lg['personalInformation'] }</h3>
								<p class="w3ls_head_para">${lg['addUserInfo'] }</p>
							</div>
						<div class="w3_mail_grids">
								<form action="<c:url value='/user.s?method=alterInfo' />" method="post" enctype="multipart/form-data">
								<input type="hidden" name="method" value="alterInfo">
									<div class="col-md-6 w3_agile_mail_grid">
										<span class="input input--ichiro">
											<input class="input__field input__field--ichiro" type="text" disabled="disabled" id="input-25"  placeholder="">
											<label class="input__label input__label--ichiro" for="input-25">
												<span class="input__label-content input__label-content--ichiro">${lg['yourUserName'] }${loginedUser.userAccount } <a>${lg['canNotAlter'] }</a></span>
											</label>
										</span>
										<span class="input input--ichiro">
											<input class="input__field input__field--ichiro" type="text" id="input-26" name="userName" value="${loginedUser.userName }" placeholder=" " onBlur="blurUserName()">
											<label class="input__label input__label--ichiro" for="input-26">
												<span class="input__label-content input__label-content--ichiro" id="nameShow">${lg['yourName'] }${loginedUser.userName }</span>
											</label>
										</span>
										<span class="input input--ichiro">
											<c:choose>
												<c:when test="${empty loginedUser.userEmail }">
													<input class="input__field input__field--ichiro" type="email" id="input-27" name="userEmail" placeholder=" " onBlur="blurUserEmail()">
													<label class="input__label input__label--ichiro" for="input-27">
														<span class="input__label-content input__label-content--ichiro" id="emailShow">${lg['yourEmail'] }</span>
													</label>
												</c:when>
												<c:otherwise>
													<input class="input__field input__field--ichiro" type="email" disabled="disabled" id="input-27" name="userEmail" value="${loginedUser.userEmail }" placeholder=" " onBlur="blurUserEmail()">
													<label class="input__label input__label--ichiro" for="input-27">
														<span class="input__label-content input__label-content--ichiro" id="emailShow">${lg['yourEmail'] }${loginedUser.userEmail } <a href="javascript:emailUnbind()" id="emailUnbind">${lg['unbind'] }</a></span>
													</label>
												</c:otherwise>
											</c:choose>
											
										</span>
										<span class="input input--ichiro">
											<c:choose>
												<c:when test="${empty loginedUser.userTel }">
													<input class="input__field input__field--ichiro" type="text" id="input-28" name="userTel" placeholder=" " onBlur="blurUserTel()">
													<label class="input__label input__label--ichiro" for="input-28">
														<span class="input__label-content input__label-content--ichiro" id="telShow">${lg['yourPhoneNumer'] }</span>
													</label>
												</c:when>
												<c:otherwise>
													<input class="input__field input__field--ichiro" disabled="disabled" type="text" id="input-28" name="userTel" value="${loginedUser.userTel }" placeholder=" " onBlur="blurUserTel()">
													<label class="input__label input__label--ichiro" for="input-28">
														<span class="input__label-content input__label-content--ichiro" id="telShow">${lg['yourPhoneNumer'] }${loginedUser.userTel } <a href="javascript:telUnbind()" id="telUnbind">${lg['unbind'] }</a></span>
													</label>
												</c:otherwise>
											</c:choose>
										</span>
										<span class="input input--ichiro">
											<c:set var="addrArr" value="${fn:split(loginedUser.userAddr, ' ')}"/>
											<div class="form-group" style="font-size:10px;">
												<label for="selector1"class="col-sm-2 control-label" style="width:auto;line-height:40px;">${lg['yourAddress'] }</label>
												<div class="col-sm-8"  style="width:auto;margin-left:5px;"><select  id="country" name="country" class="form-control1" style="height:30px;">
													<option selected="selected">中国</option>
												</select></div>
												<div class="col-sm-8"  style="width:auto;"><select  id="province" name="province" class="form-control1"  style="height:30px;" onchange="createCityAndDistrict()">
													<option>${addrArr[1] }</option>
													<c:forEach items="${provincesMap }" var="map">
														<c:if test="${map.value != addrArr[1] }">
															<option rel="${map.key }">${map.value }</option>
														</c:if>
					                            	</c:forEach>
												</select></div>
												<div class="col-sm-8"  style="width:auto;"><select  id="city" name="city" class="form-control1"  style="height:30px;" onchange="createDistrictByCity()">
													<option>${addrArr[2] }</option>
												</select></div>
												<div class="col-sm-8"  style="width:auto;"><select  id="district" name="district" class="form-control1" style="height:30px;">
													<option>${addrArr[3] }</option>
												</select></div>
											</div>
											<div><input type="text" style="margin-left:20px;width:400px;" value="${addrArr[4] }" id="fullAddress" name="fullAddress" placeholder="${lg['detailedAddress'] }"></div>
										</span>
										<span class="input input--ichiro">
										<c:set var="date" value="${fn:split(loginedUser.userBirthday , '-') }"/>
											<div class="form-group">
												<label for="selector1"class="col-sm-2 control-label" style="width:auto;line-height:40px;">${lg['yourBirthday'] }</label>
												<div class="col-sm-8"  style="width:auto;"><select name="y" id="y" class="form-control1" onchange="updateDay()">
													<c:forEach var="i" begin="1940" end="2017">
														<c:choose>
															<c:when test="${date[0] == i }">
																<option selected="selected">${i}</option>
															</c:when>
															<c:otherwise>
																<option>${i}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select></div>
												<div class="col-sm-8"  style="width:auto;"><select name="m" id="m" class="form-control1" onchange="updateDay()">
													<c:forEach var="i" begin="1" end="12">
														<c:if test="${i < 10}">
															<c:set var="i" value="${'0'+ i }" />
														</c:if>
														<c:choose>
															<c:when test="${i == date[1] }">
																<option selected="selected">${i }</option>
															</c:when>
															<c:otherwise>
																<option>${i}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select></div>
												<div class="col-sm-8"  style="width:auto;"><select name="d" id="d" class="form-control1">
													<option selected="selected">${date[2] }</option>
												</select></div>
											</div>
										</span>
										<!-- <span class="input input--ichiro">
											<input class="input__field input__field--ichiro" type="text" id="input-29" placeholder=" " required="">
											<label class="input__label input__label--ichiro" for="input-29">
												<span class="input__label-content input__label-content--ichiro">Your Phone Number</span>
											</label>
										</span> -->
										
									</div>
									<div class="col-md-6 w3_agile_mail_grid two">
									<%--上传头像 --%>
										 <div class="con4">
											<canvas id="cvs" width="200" height="200"></canvas>
											
											<c:choose>
												<c:when test="${empty loginedUser.imgList }">
													<script type="text/javascript">
														writeImg("<c:url value='/images/uploadLogo.png' />");
													</script>
													<span class="btn upload">${lg['uploadPhoto'] }<input type="file" name="file" class="upload_pic" id="upload" /></span>
												</c:when>
												<c:otherwise>
													<script type="text/javascript">
														writeImg("<c:url value='${loginedUser.imgList[0].imgPath}' />");
													</script>
													<span class="btn upload">${lg['alterPhoto'] }<input type="file" name="file" class="upload_pic" id="upload" /></span>
												</c:otherwise>
											</c:choose>
											
										</div>
										<input type="submit" value="${lg['submit'] }">
									</div>
									<div class="clearfix"> </div>
								</form>
								<!-- <div class="agileits-get-us">
									<ul>
										<li><i class="fa fa-map-marker" aria-hidden="true"></i>United States,Utah 10009, USA</li>
										<li><i class="fa fa-phone" aria-hidden="true"></i>  +033 111 222 4567</li>
										<li><i class="fa fa-envelope" aria-hidden="true"></i><a href="mailto:info@example.com"> mail@example.com</a></li>
									</ul>
								</div> -->
					
					</div>
					</div>
							<!-- <div class=" map">
								<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12947831.742778081!2d-95.665!3d37.599999999999994!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x54eab584e432360b%3A0x1c3bb99243deb742!2sUnited+States!5e0!3m2!1sen!2sin!4v1422444552833"></iframe>
							</div> -->
				</div>
			<!--//content-inner-section-->

 <script type="text/javascript">
    	//创建市和县
   		function createCityAndDistrict(){
   			var province = $('#province option:selected');
   			var city = $('#city');
   			var district = $('#district');
   			var data={pId : province.attr("rel")};
   			$.post("<c:url value='/user.s?method=getCityAndDistrictByProvince&pId="+province.attr("rel")+"' />",data,function(data){
   				city.html("");
   				district.html("");
   				var list = eval("("+data+")");
   				var citys = list[0];
   				var districts = list[1];
   				$.each(citys,function(n,value){
   					city.append('<option rel="'+n+'">'+value+'</option>');
   				});
   				
   				$.each(districts,function(n,value){
   					district.append('<option rel="'+n+'">'+value+'</option>');
   				});
   			});
   		}
    	//创建县
    	function createDistrictByCity(){
    		var province = $('#province');
   			var city = $('#city option:selected');
   			var district = $('#district');
   			
   			var data={pId : province.attr("rel")};
   			$.post("<c:url value='/user.s?method=getDistrictByCity&cId="+city.attr("rel")+"' />",data,function(data){
   				district.html("");
   				var districts = eval("("+data+")");
   				$.each(districts,function(n,value){
   					district.append('<option rel="'+n+'">'+value+'</option>');
   				});
   			});
    	}
    	
    	
    	//根据年和月获取日
    	function updateDay(){
    		var y = $('#y');
    		var m = $('#m');
    		var d = $('#d');
    		d.html("");
    		var data = {
    				y : y.val(),
    				m : m.val()
    		};
    		$.post("<c:url value='/user.s?method=updateDay' />",data,function(data){
    			var dayArr = eval("("+data+")");
    			$.each(dayArr,function(n,value){
    				if(value.length == 1)
    					value = "0"+value;
   					d.append('<option>'+value+'</option>');
   				});
    		});
    	}
    	
    	//邮箱的点击解绑事件
    	function emailUnbind(){
    		var emailUnbind = $('#emailUnbind');
    		var inputText = $('#input-27');
    		var emailShow = $('#emailShow');
    		emailUnbind.html(""); //将解绑按钮删除
    		inputText.attr("disabled",false);  //将邮箱输入框解锁
    		emailShow.html("${lg['yourEmail']}");
    	}
    	
    	//手机号点击解绑事件
    	function telUnbind(){
    		var telUnbind = $('#telUnbind');
    		var inputText = $('#input-28');
    		var telShow = $('#telShow');
    		telUnbind.html(""); //将解绑按钮删除
    		inputText.attr("disabled",false);  //将邮箱输入框解锁
    		telShow.html("${lg['yourPhoneNumer']}");
    	}
    	
    	//姓名输入框失焦事件
    	function blurUserName(){
    		var textBox = $('#input-26'); //姓名输入框
    		var nameShow = $('#nameShow');  //姓名显示
    		if(textBox.val() === ""){
    			nameShow.html("${lg['yourName'] }"+textBox.val()+"<font class='error' style='color:red;'> * 姓名不能为null</font>");
    		}else{
    			nameShow.html("${lg['yourName'] }"+textBox.val());
    		}
    	}
    	
    	//邮箱输入框失焦事件
    	function blurUserEmail(){
    		var textBox = $('#input-27');  //邮箱输入框
    		var emailShow = $('#emailShow');  //邮箱显示框
    		var data = {
    				inputEmail : textBox.val()
    		};
    		$.post("<c:url value='/user.s?method=regxEmail' />",data,function(data){
    			if(data === "yes"){
    				emailShow.html("${lg['yourEmail'] }"+textBox.val());
    			}else{
    				emailShow.html("${lg['yourEmail'] }"+textBox.val()+"<font class='error' style='color:red;'> * "+data+"</font>");
    			}
    		});
    	}
    	
    	//手机号输入框失焦事件
    	function blurUserTel(){
    		var textBox = $("#input-28");  //手机号输入框
    		var telShow = $("#telShow");  //手机号显示框
    		var data = {
    				inputTel : textBox.val()
    		};
    		$.post("<c:url value='/user.s?method=regxTel' />",data,function(data){
    			if(data === "yes"){
    				telShow.html("${lg['yourPhoneNumer'] }"+textBox.val());
    			}else{
    				telShow.html("${lg['yourPhoneNumer'] }"+textBox.val()+"<font class='error' style='color:red;'> * "+data+"</font>");
    			}
    		});
    	}
    </script>
    
    <script>
		//获取上传按钮
		var input1 = document.getElementById("upload"); 
		 
		if(typeof FileReader==='undefined'){ 
		     //result.innerHTML = "抱歉，你的浏览器不支持 FileReader"; 
		     input1.setAttribute('disabled','disabled'); 
		}else{ 
		     input1.addEventListener('change',readFile,false); 
		     /*input1.addEventListener('change',function (e){
		     	console.log(this.files);//上传的文件列表
		     },false); */
		}
		function readFile(){ 
			var file = this.files[0];//获取上传文件列表中第一个文件
			if(!/image\/\w+/.test(file.type)){
			//图片文件的type值为image/png或image/jpg
				alert("文件必须为图片！");
				return false; 
			} 
			// console.log(file);
			var reader = new FileReader();//实例一个文件对象
			reader.readAsDataURL(file);//把上传的文件转换成url
			//当文件读取成功便可以调取上传的接口
			reader.onload = function(e){ 
				// console.log(this.result);//文件路径
				// console.log(e.target.result);//文件路径
				/*var data = e.target.result.split(',');
				var tp = (file.type == 'image/png')? 'png': 'jpg';
				var imgUrl = data[1];//图片的url，去掉(data:image/png;base64,)
				//需要上传到服务器的在这里可以进行ajax请求
				// console.log(imgUrl);
				// 创建一个 Image 对象 
				var image = new Image();
				// 创建一个 Image 对象 
				// image.src = imgUrl;
				image.src = e.target.result;
				image.onload = function(){
					document.body.appendChild(image);
				}*/
			
				writeImg(e.target.result);
			}
		};
	</script>
	<c:if test="${! empty msg }">
		<script type="text/javascript">
			alert('${msg}');
		</script>
	</c:if>
<!--/footer-bottom-->
	<%@ include file="footer.jsp"%>
 

</body>
</html>