<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="apple-touch-fullscreen" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <meta name="format-detection" content="telephone=no">

        <title>个人信息</title>

        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/home.css">
		
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
		border: 1px solid #000;
		margin:20px 0 20px 50px;
	}
</style>
<script type="text/javascript">
function writeImg(url){
	var image = new Image();
	image.src = url;
	var max=200;
	image.onload = function(){ 
		var canvas = document.getElementById("cvs"); 
		var ctx = canvas.getContext("2d"); 
		ctx.clearRect(0, 0, canvas.width, canvas.height); 
		ctx.drawImage(image, 0, 0, 200, 200);
	};
}
</script>
    <body>
        <section class="aui-content" style="display:block" id="realBefore">
            <div style="height:20px;"></div>
            <div class="aui-content-up">
     			<form id="uploadForm" action="" enctype="multipart/form-data">
					 <input type="file" name="file" id="upload" style="display:none;"/>
						<!-- <span class="btn upload">上传头像</span> -->
				</form>
                <form action="" name="form1" method="post">
                <input type="hidden" id="uploadSqlPath" value="">
                    <div class="aui-content-up-form">
                        <h2>个人信息</h2>
                    </div>
                    
                    <%--上传头像 --%>
					 <div class="con4">
						 <canvas id="cvs" width="200" height="200" style="cursor:pointer;" onclick="upload.click()"></canvas>
						 <script type="text/javascript">
							writeImg("<c:url value='${loginedMerchant.imgList[0].imgPath}' />");
						</script>
					</div>
										
                    <%--商户姓名 --%>
                    <div class="aui-form-group clear" style="margin-left:100px;">
                        <label class="aui-label-control">
                            姓名 <em> : </em>
                        </label>
                        <div class="aui-form-input">
                            <font style="height:30px;font-size:18px;line-height:30px;">${loginedMerchant.merName }</font>
                        </div>
                    </div>
                    
                    <%--商户店名 --%>
                    <div class="aui-form-group clear" style="margin-left:100px;">
                        <label class="aui-label-control">
                            商户店名 <em> : </em>
                        </label>
                        <div class="aui-form-input">
                            <font style="height:30px;font-size:18px;line-height:30px;">${loginedMerchant.merStoreName }</font>
                        </div>
                    </div>
                    
                    <%--商户身份证号码 --%>
                    <div class="aui-form-group clear" style="margin-left:100px;">
                        <label class="aui-label-control">
                            身份证号 <em> : </em>
                        </label>
                        <div class="aui-form-input">
                            <font style="height:30px;font-size:18px;line-height:30px;">${loginedMerchant.merIDCard }</font>
                        </div>
                    </div>
                    
                    <%--商户所在地址 --%>
                    <div class="aui-form-group clear" style="margin-left:100px;">
                        <label class="aui-label-control">
                            所在地址 <em> : </em>
                        </label>
                        <div class="aui-form-input">
                            <font style="height:30px;font-size:18px;line-height:30px;">${loginedMerchant.merAddr }</font>
                        </div>
                    </div>
                    
                    <%--商户手机号码 --%>
                     <div class="aui-form-group clear" style="margin-left:100px;margin-bottom:100px;">
                        <label class="aui-label-control">
                            手机号码 <em> : </em>
                        </label>
                        <div class="aui-form-input">
                            <font style="height:30px;font-size:18px;line-height:30px;">${loginedMerchant.merTel }</font>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- mask end -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/up.js"></script>
     
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
				writeImg(e.target.result);
				var formData = new FormData($('#uploadForm')[0]);
				$.ajax({
					url : '<c:url value="/mer.s?method=uploadHeadImg" />',
					type : 'POST',
					data : formData,
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					succeed : function(data){
					}
				});
			}
		};
	</script>
    </body>
</html>
