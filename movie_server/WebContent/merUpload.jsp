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

        <title>添加电影</title>

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
    	 <form action="<c:url value='/mer.s?method=addMovie&movieMerId=${loginedMerchant.merId }' />" name="form1" method="post" enctype="multipart/form-data">
        <section class="aui-content">
            <div style="height:20px;"></div>
            <div class="aui-content-up">
               
                    <div class="aui-content-up-form">
                        <h2><a href="merProduct_Manage.jsp">商品管理</a> >> 添加电影</h2>
                    </div>
                    
                    <%--片名输入框 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            片名 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="movieName" onBlur="blurMovieName()" value="${param.movieName }" required id="1" placeholder="请输入片名">
                            <span class="tips" id="movieNameErrorMsg"></span>
                        </div>
                    </div>
                    
                    <%--主演输入框 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            主演 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="moviePro" value="${param.moviePro }" id="2" placeholder="请输入主演,多位主演用英文分号隔开" onBlur="blurMoviePro()" required/>
                            <span class="tips" id="movieProErrorMsg"></span>
                        </div>
                    </div>
                    
                    <%--选择分类 --%>
                  	<div class="aui-form-group clear">
                        <label class="aui-label-control">
                            分类 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <select style="width:100px;height:30px;" id="selectMovieClassify" onchange="setMovieClassify()">
                            	<c:forEach items="${classifyList }" var="bean">
                            		<option style="width:200px;height:30px;">${bean.classifyNameString }</option>
                            	</c:forEach>
                            </select>
                            <input type="text" readonly="readonly" name="movieClassify" id="movieClassify" value="${param.movieClassify}" style="width:300px;height:30px;" >
                       		<input type="button" value="重置" style="width:50px;height:30px;cursor:pointer;" onclick="emptyMovieClassify()">
                        </div>
                    </div>
                    
                    <%--选择片种 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            片种 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <select style="width:200px;height:30px;" name="movieGenre"> 
                            	<option style="width:200px;height:30px;">国产2D</option>
                            	<option style="width:200px;height:30px;">国产3D</option>
                            </select>
                        </div>
                    </div>
                    
                    <%--单价输入框 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            单价 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="moviePrice" value="${param.moviePrice }" id="3" placeholder="请输入单价" onBlur="blurMoviePrice()" required/>
                            <span class="tips" id="moviePriceErrorMsg"></span>
                        </div>
                    </div>
                    
                    <%--积分数 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            积分 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="movieIntegralNum" value="${param.movieIntegralNum }" id="4" placeholder="请输入积分" onBlur="blurMovieIntegralNum()" required/>
                            <span class="tips" id="movieIntegralNumErrorMsg"></span>
                        </div>
                    </div>
                    
                    <%--电影时长 --%>
          			<div class="aui-form-group clear">
                        <label class="aui-label-control">
                            时长 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="movieTimeLong" value="${param.movieTimeLong }" id="5" placeholder="请输入电影时长,单位:分钟" onBlur="blurMovieTimeLong()" required/>
                            <span class="tips" id="movieTimeLongErrorMsg"></span>
                        </div>
                    </div>
                    
                    <%--预告片 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            预告片 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="file" class="aui-form-control-two" name="moviePrevue" id="6" onchange="changeMoviePrevue()" required/>
                            <span class="tips" id="moviePrevueErrorMsg"></span>
                        </div>
                    </div>
                    
                    <%--电影描述 --%>
                     <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            描述 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <textarea class="aui-form-control" name="movieDescribe" placeholder="请输入电影剧情简介" id="7" minlength="5" onBlur="blurMovieDescribe()">${param.movieDescript }</textarea>
                        	<span class="tips" id="movieDescribeErrorMsg"></span>
                        </div>
                        
                    </div>
                    
          			<%--这里是选择生成电影票 --%>
          			 <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            上映厅室 <em>*</em>
                        </label>
                        <div class="aui-form-input" style="margin-top:20px;width:auto;">
                            <input type="checkbox" name="movieTheater1" name="movieTheater1" value="1号厅" style="margin-top:20px;" onclick="clickMovieTheater(1)">1号厅 <br />
                            <input type="checkbox" name="movieTheater2" name="movieTheater2" value="2号厅" style="margin-top:20px;" onclick="clickMovieTheater(2)">2号厅 <br />
                            <input type="checkbox" name="movieTheater3" name="movieTheater3" value="3号厅" style="margin-top:20px;" onclick="clickMovieTheater(3)">3号厅 <br />
                            <input type="checkbox" name="movieTheater4" name="movieTheater4" value="4号厅" style="margin-top:20px;" onclick="clickMovieTheater(4)">4号厅 <br />
                            <input type="checkbox" name="movieTheater5" name="movieTheater5" value="5号厅" style="margin-top:20px;" onclick="clickMovieTheater(5)">5号厅 <br />
                            <input type="checkbox" name="movieTheater6" name="movieTheater6" value="6号厅" style="margin-top:20px;" onclick="clickMovieTheater(6)">6号厅 <br />
                        </div>
                         <label class="aui-label-control" style="margin:0 0 0 40px">
                            上映时间 <em>*</em>
                        </label> 
                        <div class="aui-form-input" style="margin:20px 0 0 0;width:310px;">
                            <input type="text" name="movieStartTime1" style="margin-top:15px;" disabled="disabled" value="" id="movieStartTime1" placeholder="1号厅对应的上映时间" readonly="readonly" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" onblur="blurMovieStartTime()"/><br />
                            <input type="text" name="movieStartTime2" style="margin-top:15px;" disabled="disabled" value="" id="movieStartTime2" placeholder="2号厅对应的上映时间" readonly="readonly" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" onblur="blurMovieStartTime()"/><br />
                            <input type="text" name="movieStartTime3" style="margin-top:15px;" disabled="disabled" value="" id="movieStartTime3" placeholder="3号厅对应的上映时间" readonly="readonly" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" onblur="blurMovieStartTime()"/><br />
                            <input type="text" name="movieStartTime4" style="margin-top:15px;" disabled="disabled" value="" id="movieStartTime4" placeholder="4号厅对应的上映时间" readonly="readonly" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" onblur="blurMovieStartTime()"/><br />
                            <input type="text" name="movieStartTime5" style="margin-top:15px;" disabled="disabled" value="" id="movieStartTime5" placeholder="5号厅对应的上映时间" readonly="readonly" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" onblur="blurMovieStartTime()"/><br />
                            <input type="text" name="movieStartTime6" style="margin-top:15px;" disabled="disabled" value="" id="movieStartTime6" placeholder="6号厅对应的上映时间" readonly="readonly" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" onblur="blurMovieStartTime()"/><br />
                        </div>
                    </div>
                    
                    
                     <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            封面<em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <div class="con4">
								<canvas id="cvs1" width="200" height="200" onclick="upload1.click()" style="cursor:pointer;background-image:url('images/uploadLogo.jpg');"></canvas>
								<!-- <span class="btn upload">选择封面图 --><input type="file" class="upload_pic" id="upload1" name="movieImage1" style="display:none;"/><!-- </span> -->
							</div>
                        </div>
                    </div>
                    
                     <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            介绍 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                           <div class="con4">
								<canvas id="cvs2" width="200" height="200" onclick="upload2.click()" style="cursor:pointer;background-image:url('images/uploadLogo.jpg');"></canvas>
								<input type="file" class="upload_pic" id="upload2" name="movieImage2" style="display:none;"/>
							</div>
                        </div>
                    </div>
                    
                     <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            单品展示<em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <div class="con4">
								<canvas id="cvs3" width="200" height="200" onclick="upload3.click()" style="cursor:pointer;background-image:url('images/uploadLogo.jpg');"></canvas>
								<input type="file" class="upload_pic" id="upload3" name="movieImage3" style="display:none;"/>
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
                <input id="submitMovie" type="submit" style="background-color:#f9b6aa;" disabled="disabled" class="aui-btn aui-btn-account" value="确认上架">
            </div>
            
        </section> 
        </form>
      
        <!-- mask end -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/up.js"></script>
        <script type="text/javascript">
        	var flag = false;
            //验证片名
            function blurMovieName(){
            	na = form1.movieName.value;
            	console.log("片名:"+na);
            	if((na.length > 1) && (na.length < 20)){
            		movieNameErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
            	}else{
            		movieNameErrorMsg.innerHTML='<font class="tips_false">长度是1~20个字符</font>';
            	}
            	isCanUpload(); //判断是否可以提交
            }

            //验证主演
            function blurMoviePro(){
                na=form1.moviePro.value;
                console.log("主演:"+na);
                if( na.length >1 && na.length <500){
                	movieProErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
                }else{
                	 movieProErrorMsg.innerHTML='<font class="tips_false">长度是1~500个字符</font>';
                }
                isCanUpload(); //判断是否可以提交
            }

            //验证单价
            function blurMoviePrice(){
                na=form1.moviePrice.value;
                console.log("单价:"+na);
                var reg = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
                if(reg.test(na)){
                	moviePriceErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
                }else{
                	moviePriceErrorMsg.innerHTML='<font class="tips_false">单价格式错误</font>';
                }
                isCanUpload(); //判断是否可以提交
            }
            
          //验证积分
            function blurMovieIntegralNum(){
                na = form1.movieIntegralNum.value;
                console.log("积分:"+na);
                var reg = /^[1-9]\d{0,2}$/;
                if(reg.test(na)){
                	movieIntegralNumErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
                }else{
                	movieIntegralNumErrorMsg.innerHTML='<font class="tips_false">积分格式错误 1-999</font>';
                }
                isCanUpload(); //判断是否可以提交
            }

          //验证时长
          function blurMovieTimeLong(){
        	  na = form1.movieTimeLong.value;
              console.log("时长:"+na);
              var reg = /^[1-9]\d{0,2}$/;
              if(reg.test(na)){
              	movieTimeLongErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
              }else{
              	movieTimeLongErrorMsg.innerHTML='<font class="tips_false">时长格式错误 1-999</font>';
              }
              isCanUpload(); //判断是否可以提交
          }
            
          //验证是否选择文件
          function changeMoviePrevue(){
        	  na = form1.moviePrevue.value;
              console.log("电影预告文件名:"+na);
              if(na.endsWith(".mp4") || na.endsWith(".qsv")){
              	moviePrevueErrorMsg.innerHTML='<font class="tips_true">选择正确</font>';
              }else{
            	moviePrevueErrorMsg.innerHTML='<font class="tips_false">文件格式错误</font>';
              }
              isCanUpload(); //判断是否可以提交
          }
          
          
          
          //验证描述
            function blurMovieDescribe(){
                na=form1.movieDescribe.value;
                console.log("描述:"+na);
                if(na.length > 5 && na.length < 500){
                	movieDescribeErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
                }else{
                	movieDescribeErrorMsg.innerHTML='<font class="tips_false">描述必须大于5个字符</font>';
                }
                isCanUpload(); //判断是否可以提交
            }
          
         
          
			
          //选择电影厅  如果为选中 对 应的时间框变成可改变
          function clickMovieTheater(num){
        	  var movieTheaterObj = $('#movieTheater'+num);
        	  var movieStartTimeObj = $('#movieStartTime'+num);
        	  if(movieStartTimeObj.attr("disabled")){
        		  movieStartTimeObj.attr("disabled",false);
        	  }else{
        		  movieStartTimeObj.attr("disabled",true);
        		  movieStartTimeObj.val("");
        	  }
        	  
          }
          
         
          /* 	$('input[type="checkbox"]').each(function(){
          		
          		//if($(this).attr("checked") != undefined )
    	    		//alert($(this).attr("name"));
          	}); */
          	
          	//选择分类
          	function setMovieClassify(){
          		var selectMovieClassify = $('#selectMovieClassify').val();
          		var movieClassifyObj = $('#movieClassify');
          		if(movieClassifyObj.val().indexOf(selectMovieClassify) == -1){
          			movieClassifyObj.val(movieClassifyObj.val()+selectMovieClassify+";");
          		}
          	}
          	
          	//清空分类
          	function emptyMovieClassify(){
          		var movieClassifyObj = $('#movieClassify');
          		movieClassifyObj.val("");
          	}
        	
          	
          	//判断是否格式正确  正确就把上传按钮显示出来
          	function isCanUpload(){
          		$('span[class="tips"]').each(function(){
              		var status = $(this).html().indexOf("正确")!=-1;
              		console.log(status)
              		if(status === false){
              			$('#submitMovie').css("background-color","#f9b6aa");
              			$('#submitMovie').attr("disabled",true);
              			flag = false;
              		}
              	});
          		if(flag){
          			$('#submitMovie').css("background-color","#f45858");
          			$('#submitMovie').attr("disabled",false);
          		}
          		flag = true;
          	}
        </script>
        <script>
//获取上传文件按钮
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
