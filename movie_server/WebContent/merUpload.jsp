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
    <body>
        <section class="aui-content">
            <div style="height:20px;"></div>
            <div class="aui-content-up">
                <form action="" name="form1" method="post">
                    <div class="aui-content-up-form">
                        <h2><a href="merProduct_Manage.jsp">商品管理</a> >> 电影上架 </h2>
                    </div>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            片名 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="yourname" onBlur="checkna()" required id="1" placeholder="请输入片名">
                            <span class="tips" id="divname">长度1~20个字符</span>
                        </div>
                    </div>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            主演 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="youphone" id="2" placeholder="请输入主演" onBlur="checkpsd1()" required/>
                            <span class="tips" id="phone">多位用 英文 ; 号隔开</span>
                        </div>
                    </div>
                  <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            分类 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <select style="width:200px;height:30px;">
                            	<option style="width:200px;height:30px;">123</option>
                            </select>
                        </div>
                    </div>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            单价 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <input type="text" class="aui-form-control-two" name="youziz" id="3" placeholder="请输入单价" onBlur="checkpsd2()" required/>
                            <span class="tips" id="zizhi">单位/元    币种：人民币</span>
                        </div>
                    </div>
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            描述 <em>*</em>
                        </label>
                        <div class="aui-form-input">
                            <textarea class="aui-form-control" name="description" id="4" minlength="5"></textarea>
                        </div>
                    </div>
          
                    <div class="aui-form-group clear">
                        <label class="aui-label-control">
                            相关图片  <em>*</em>
                            <span>小于5M</span>
                        </label>
                        <div class="aui-form-input">
                            <div class="aui-content-img-box aui-content-full">
                                <div class="aui-photo aui-up-img clear">
                                    <section class="aui-file-up fl">
                                        <img src="merUploadImg/up.png" class="add-img">
                                        <input type="file" name="file" id="file" class="file" value="" accept="image/jpg,image/jpeg,image/png,image/bmp" multiple/>
                                    </section>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="aui-form-group-text">
                        <h3>上架说明</h3>
                        <p>上架说明正文</p>
                    </div>
                </form>
            </div>
            <div class="aui-btn-default">
            <button style="margin-right:100px;" class="aui-btn aui-btn-account" onclick="window.location.href='merProduct_Manage.jsp'">返回商品管理</button>
                <button class="aui-btn aui-btn-account">确认上架</button>
                
            </div>
        </section>
        <!-- mask begin -->
        <div class="aui-mask aui-works-mask">
            <div class="aui-mask-content">
                <p class="aui-delete-text">确定要删除你上传的图片？</p>
                <p class="aui-check-text">
                    <span class="aui-delete aui-accept-ok">确定</span>
                    <span class="aui-accept-no">取消</span>
                </p>
            </div>
        </div>
        <!-- mask end -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/up.js"></script>
        <script type="text/javascript">
            

            //验证片
            function checkna(){

                na=form1.yourname.value;

                if( na.length <1 || na.length >20)

                {

                    divname.innerHTML='<font class="tips_false">长度是1~20个字符</font>';



                }else{

                    divname.innerHTML='<font class="tips_true">输入正确</font>';



                }



            }

            //验证主演
            function checkpsd1(){

                na=form1.youphone.value;

                if( na.length <1 || na.length >200)

                {

                    phone.innerHTML='<font class="tips_false">长度是1~20个字符</font>';



                }else{

                    phone.innerHTML='<font class="tips_true">输入正确</font>';



                }



            }

            //验证单价
            function checkpsd2(){

                na=form1.youziz.value;
                var reg=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
                if(!reg.test(na))

                {

                    zizhi.innerHTML='<font class="tips_false">单价格式错误</font>';



                }else{

                    zizhi.innerHTML='<font class="tips_true">输入正确</font>';



                }



            }



        
        </script>
    </body>
</html>
