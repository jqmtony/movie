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

        <title>实名认证</title>

        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/home.css">
		
    </head>
   

    <c:if test="${empty provincesMap }">
    	<jsp:forward page="/mer.s?method=getMapByProvinces"></jsp:forward>
    </c:if>
    <body>
        <section class="aui-content" style="display:block" id="realBefore">
            <div style="height:20px;"></div>
            <div class="aui-content-up">
                <form action="" name="form1" method="post">
                    <div class="aui-content-up-form">
                        <h2>实名认证</h2>
                    </div>
                    	
                    <%--商户姓名 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            姓名 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="merName" onBlur="blurMerName()" required id="merName" placeholder="请输入您的姓名">
                            <span class="tips" id="merNameErrorMsg" style="color:red;"></span>
                        </div>
                    </div>
                    
                    <%--商户店名 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            商户店名 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="merStoreName" onBlur="blurMerStoreName()" required id="merStoreName" placeholder="请输入您的店名">
                            <span class="tips" id="merStoreNameErrorMsg" style="color:red;"></span>
                        </div>
                    </div>
                    
                    <%--商户身份证号码 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            身份证号 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="merIDCard" id="merIDCard" placeholder="请输入身份证号码" onBlur="blurMerIDCard()" required/>
                            <span class="tips" id="merIDCardErrorMsg" style="color:red;"></span>
                        </div>
                    </div>
                    
                    <%--商户所在地址 --%>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            所在地址 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <!-- <textarea class="aui-form-control" name="description" id="4" minlength="5">请输入您所在的地址...</textarea> -->
                            <select style="width:110px;height:30px;" id="country">
                            	<option selected="selected">中国</option>
                            </select>
                            
                            <select style="width:110px;height:30px;" id="province" onchange="createCityAndDistrict()">
                            	<option selected="selected">省</option>
                            	<c:forEach items="${provincesMap }" var="map">
                            		<option rel="${map.key }">${map.value }</option>
                            	</c:forEach>
                            </select>
                            <select style="width:110px;height:30px;" id="city" onchange="createDistrictByCity()">
                            	<option value="0">市</option>
                            </select>
                            <select style="width:110px;height:30px;" id="district">
                            	<option>县</option>
                            </select>
                            <span class="tips" id="address" style="color:red;"></span>
                        </div>
                    </div>
                    
                    <%--商户手机号码 --%>
                     <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            手机号码 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="merTel" id="merTel" placeholder="请输入11位的手机号码" onBlur="blurMerTel()" required/>
                            <span class="tips" id="merTelErrorMsg" style="color:red;"></span>
                        </div>
                    </div>
                    
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                           验证码 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="verifyCode" id="verifyCode" placeholder="请输入验证码" required/>
                            <span class="tips" id="verifyMsg" style="color:red;"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="aui-btn-default">
           		<button class="aui-btn aui-btn-account" onclick="sendTelCode()" style="margin-left:150px;" id="sendTelCode">发送手机验证码</button>
                <button class="aui-btn aui-btn-account" onclick="save()" style="margin-left:150px;">保存并提交审核</button>
            </div>
        </section>
        
        <section class="aui-content" style="display:none" id="realSucceed">
        	<div class="aui-content-up">
        		<div class="aui-content-up-form">
	                 <h2>实名认证</h2>
	             </div>
        		<div style="margin:100px 0 200px 0;color:green;font-size:40px;">
        			<font>恭喜！ 您认证成功了！</font>
        		</div>
        	</div>
        	
        </section>
        
        <!-- mask end -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/up.js"></script>
        <script type="text/javascript">
        	//确定
        	function save(){
        		var name = $('#merName');	//姓名
        		var tel = $('#merTel');	//电话号码
        		var merStoreName = $('#merStoreName');  //店名
        		var idcard = $('#merIDCard');	//身份证号码
        		var country = $('#country');	//国家
        		var province = $('#province');	//省
       			var city = $('#city');	//市
       			var district = $('#district');	//县
       			var verifyCode = $('#verifyCode'); //验证码
       			var data = {
       					merName : name.val(),
       					merTel : tel.val(),
       					merStoreName : merStoreName.val(),
       					merAddr : country.val()+" "+province.val()+" "+city.val()+" "+district.val(),
       					merIDCard : idcard.val(),
       					verify : verifyCode.val()
       			};
       			$.post("<c:url value='/mer.s?method=realName' />",data,function(data){
       				if(data === "404"){
       					window.location.href = "<c:url value='/server404.jsp' />";
       				}else if(data === "yes"){
       					$('#realSucceed').css("display","block");
       					$("#realBefore").css("display","none");
       				}else{
       					var merStoreName = $('#merStoreNameErrorMsg');
       					var merName = $('#merNameErrorMsg');
       					var zizhi = $('#merIDCardErrorMsg');
       					var address = $('#address');
       					var phone = $('#merTelErrorMsg');
       					var verifyMsg = $('#verifyMsg');
       					address.html("");
       					if(data.indexOf("姓名") != -1){
       						merName.html(data);
       					}else if(data.indexOf("手机号") != -1){
       						phone.html(data);
       					}else if(data.indexOf("身份证") != -1){
       						zizhi.html(data);
       					}else if(data.indexOf("地址") != -1){
       						address.html(data);
       					}else if(data.indexOf("验证码") != -1){
       						verifyMsg.html(data);
       					}else if(data.indexOf("店名") != -1){
       						merStoreName.html(data);
       					}else{
       						alert(data);
       					}
       					
       				}
       			});
        	}
            //验证商户姓名
            function blurMerName(){
                var na=form1.merName.value;
                if( na.length >1 && na.length <8){
                	merNameErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
                }else{
                	merNameErrorMsg.innerHTML='<font class="tips_false">长度是1~8个字符</font>';
                }
            }

            //验证商户店名
            function blurMerStoreName(){
                var na=form1.merStoreName.value;
                if( na.length >1 && na.length <20){
                	merStoreNameErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
                }else{
                	merStoreNameErrorMsg.innerHTML='<font class="tips_false">长度是1~20个字符</font>';
                }
            }
            
          //验证身份证号码
            function blurMerIDCard(){
                var na=form1.merIDCard.value;
                if(na.length != 18)
                {
                    merIDCardErrorMsg.innerHTML='<font class="tips_false">必须是18位身份证号码</font>';
                }else{
                	merIDCardErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
                }
            }
            
             //验证手机号码
            function blurMerTel(){
                var na= form1.merTel.value;
                if( na.length != 11){
                    merTelErrorMsg.innerHTML='<font class="tips_false">必须是11位的数字</font>';
                }else{
                	merTelErrorMsg.innerHTML='<font class="tips_true">输入正确</font>';
                }
            } 

            
            
            
        </script>
        
     <script type="text/javascript">
    	//创建市和县
   		function createCityAndDistrict(){
   			var province = $('#province option:selected');
   			var city = $('#city');
   			var district = $('#district');
   			
   			var data={pId : province.val()};
   			$.post("<c:url value='/mer.s?method=getCityAndDistrictByProvince&pId="+province.attr("rel")+"' />",data,function(data){
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
   			
   			var data={pId : province.val()};
   			$.post("<c:url value='/mer.s?method=getDistrictByCity&cId="+city.attr("rel")+"' />",data,function(data){
   				district.html("");
   				var districts = eval("("+data+")");
   				$.each(districts,function(n,value){
   					district.append('<option rel="'+n+'">'+value+'</option>');
   				});
   			});
    	}
    </script>
    
    <script type="text/javascript">
    	function sendTelCode(){
    		var tel = $('#merTel');
    		var data = {
    				telephone : tel.val()
    		};
    		$.post("<c:url value='/mer.s?method=sendTelCode' />",data,function(data){
    			if(data === "yes"){
    				//发送成功
    				$('#sendTelCode').attr("disabled",true);
    				out(60);
    			}else{
    				//发送失败
    				alert(data);
    			}
    		});
    	}
    	
    	//计时器
    	function out(t){
			var i = t ;
			var btn = $('#sendTelCode');
			if(i==0){
				btn.attr("disabled",false);
				btn.html("发送手机验证码");
				return;
			}
			btn.html("重新发送( "+i+" s)")
			i--;
			setTimeout("out("+i+")",1000);
		}
    </script>
    
     
    </body>
</html>
