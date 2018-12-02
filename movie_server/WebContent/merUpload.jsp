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

        <title>信息照片上传</title>

        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/home.css">
		
    </head>
    <c:if test="${empty classifyList}">
    	<jsp:forward page="/mer.s?method=findClassify"></jsp:forward>
    </c:if>
    <style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}
/* demo */
.demo{width:500px;margin:0 auto;}
.demo span{display:inline-block;width:240px;}
.demo span input{padding:3px;width:120px;font-size:12px;font-family:Arial, Helvetica, sans-serif;}
</style>
    <style>
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
     line-height: 40px;
     text-align: center;
     background: #d8b49c;
     display: block;
     font-size: 16px;
     border-radius: 5px;
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
#cvs1,#cvs2,#cvs3{
	border: 1px solid #000;
	margin:20px 0 20px 50px;
}
    </style>
    <body>
    	 <form action="<c:url value='/mer.s?method=addMovie' />" name="form1" method="post" enctype="multipart/form-data">
        <section class="aui-content">
            <div style="height:20px;"></div>
            <div class="aui-content-up">
               
                    <div class="aui-content-up-form">
                        <h2><a href="merProduct_Manage.jsp">商品管理</a> >> 电影上架 </h2>
                    </div>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            片名 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="movieName" onBlur="checkna()" value="${param.movieName }" required id="1" placeholder="请输入片名">
                            <span class="tips" id="mname"></span>
                        </div>
                    </div>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            主演 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="moviePro" value="${param.moviePro }" id="2" placeholder="请输入主演" onBlur="checkpsd1()" required/>
                            <span class="tips" id="pro"></span>
                        </div>
                    </div>
                  <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            分类 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <select style="width:100px;height:30px;" name="movieClassify1" value="${param.movieClassify }">
                            	<c:forEach items="${classifyList }" var="bean">
                            		<option style="width:200px;height:30px;">${bean.classifyNameString }</option>
                            	</c:forEach>
                            </select>
                            <select style="width:100px;height:30px;" name="movieClassify2" value="${param.movieClassify }">
                            	<c:forEach items="${classifyList }" var="bean" begin="1">
                            		<option style="width:200px;height:30px;">${bean.classifyNameString }</option>
                            	</c:forEach>
                            </select>
                            <select style="width:100px;height:30px;" name="movieClassify3" value="${param.movieClassify }">
                            	<c:forEach items="${classifyList }" var="bean" begin="2">
                            		<option style="width:200px;height:30px;">${bean.classifyNameString }</option>
                            	</c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            单价 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="moviePrice" value="${param.moviePrice }" id="3" placeholder="请输入单价" onBlur="checkpsd2()" required/>
                            <span class="tips" id="price"></span>
                        </div>
                    </div>
                    
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            电影票总数 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <select style="width:200px;height:30px;" name="movieCount" >
                            	<option>50</option>
                            	<option>100</option>
                            	<option>300</option>
                            	<option>500</option>
                            	<option>1000</option>
                            	<option>2000</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            上映时间 <em>*</em>
                        </label>
                        <div class="aui-form-input">
								<input type="text" name="movieTime" style="width:200px;height:30px;" value="${param.movieTime }" id="" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"/>
								
								<!-- <span>获取日期：<input type="text" value="" id="" onclick="SelectDate(this,'yyyy-MM-dd')"/></span> -->
						   
                        </div>
                    </div>
                    
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            积分 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="movieIntegralNum" value="${param.movieIntegralNum }" id="4" placeholder="请输入积分" onBlur="checkpsd3()" required/>
                            <span class="tips" id="inte"></span>
                        </div>
                    </div>
                    
                    
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            描述 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <textarea class="aui-form-control" name="movieDescribe" id="4" minlength="5" onBlur="checkpsd4()">${param.movieDescript }</textarea>
                        	<span class="tips" id="des"></span>
                        </div>
                        
                    </div>
          
          
                     <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            封面<em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <div class="con4">
								<canvas id="cvs1" width="200" height="200" onclick="upload1.click()" style="cursor:pointer;"></canvas>
								<!-- <span class="btn upload">选择封面图 --><input type="file" class="upload_pic" id="upload1" name="image1" style="display:none;"/><!-- </span> -->
							</div>
                        </div>
                    </div>
                    
                     <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            介绍 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                           <div class="con4">
								<canvas id="cvs2" width="200" height="200" onclick="upload2.click()" style="cursor:pointer;"></canvas>
								<input type="file" class="upload_pic" id="upload2" name="image2" style="display:none;"/>
							</div>
                        </div>
                    </div>
                    
                     <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            单品展示<em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <div class="con4">
								<canvas id="cvs3" width="200" height="200" onclick="upload3.click()" style="cursor:pointer;"></canvas>
								<input type="file" class="upload_pic" id="upload3" name="image3" style="display:none;"/>
							</div>
                        </div>
                    </div>
                    
                    <div class="aui-form-group-text">
                        <h3>上架说明</h3>
                        <p>上架说明正文</p>
                    </div>
               
            </div>
            <div class="aui-btn-default">
            <button style="margin-right:100px;" class="aui-btn aui-btn-account" onclick="window.location.href='merProduct_Manage.jsp'">返回商品管理</button>
                <input type="submit" class="aui-btn aui-btn-account" value="确认上架">
            </div>
            
        </section> 
        </form>
      
        <!-- mask end -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/up.js"></script>
        <script type="text/javascript">
            

            //验证片
            function checkna(){
                na=form1.movieName.value;
                if( na.length <1 || na.length >20)
                {
                    mname.innerHTML='<font class="tips_false">长度是1~20个字符</font>';
                }else{
                    mname.innerHTML='<font class="tips_true">输入正确</font>';
                }
            }

            //验证主演
            function checkpsd1(){
                na=form1.moviePro.value;
                if( na.length <1 || na.length >20)
                {
                    pro.innerHTML='<font class="tips_false">长度是1~20个字符</font>';
                }else{
                    pro.innerHTML='<font class="tips_true">输入正确</font>';
                }
            }

            //验证单价
            function checkpsd2(){
                na=form1.moviePrice.value;
                var reg=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
                if(!reg.test(na))
                {
                    price.innerHTML='<font class="tips_false">单价格式错误</font>';
                }else{
                    price.innerHTML='<font class="tips_true">输入正确</font>';
                }
            }
            
          //验证积分
            function checkpsd3(){
                na=form1.movieIntegralNum.value;
                var reg=/\d{1,3}/;
                if(!reg.test(na))
                {
                    inte.innerHTML='<font class="tips_false">积分格式错误 0-999</font>';
                }else{
                    inte.innerHTML='<font class="tips_true">输入正确</font>';
                }
            }

            
            
          //验证描述
            function checkpsd4(){
                na=form1.movieDescribe.value;
                if(na.length < 5 || na.length > 500)
                {
                	des.innerHTML='<font class="tips_false">描述必须大于5个字符</font>';
                }else{
                    des.innerHTML='<font class="tips_true">输入正确</font>';
                }
            }

        
        </script>
        <script>
//获取上传按钮
var input1 = document.getElementById("upload1"); 
var input12 = document.getElementById("upload2");
var input13 = document.getElementById("upload3"); 
if(typeof FileReader==='undefined'){ 
     input1.setAttribute('disabled','disabled'); 
     input12.setAttribute('disabled','disabled'); 
     input13.setAttribute('disabled','disabled'); 
}else{ 
	 var cvs1 = document.getElementById("cvs1");
	 var cvs2 = document.getElementById("cvs2");
	 var cvs3 = document.getElementById("cvs3");
     input1.addEventListener('change',function(){readFile1(cvs1,input1)},false); 
     input12.addEventListener('change',function(){readFile1(cvs2,input12)},false); 
     input13.addEventListener('change',function(){readFile1(cvs3,input13)},false); 
}
function readFile1(obj,input1){ 
	var file = input1.files[0];//获取上传文件列表中第一个文件
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

		var image = new Image();
		// 设置src属性 
		image.src = e.target.result;
		var max=200;
		// 绑定load事件处理器，加载完成后执行，避免同步问题
		image.onload = function(){ 
			// 获取 canvas DOM 对象 
			var canvas = obj; 
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
};
</script>
<script type="text/javascript" src="js/adddate.js" ></script> 
<c:if test="${! empty msg}">
	<script type="text/javascript">
		alert("${msg}");
	</script>
</c:if>
    </body>
</html>
