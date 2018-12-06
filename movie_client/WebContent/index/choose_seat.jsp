<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>详情</title>
<link href="css/seat.css" type="text/css" rel="stylesheet" />
<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="js/layer-v3.0.3/layer/mobile/need/layer.css" rel="stylesheet" type="text/css" />
<link href="js/layer-v3.0.3/layer/mobile/need/layer.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/phone.js" ></script>
<script type="text/javascript" src="js/layer-v3.0.3/layer/mobile/layer.js"></script>
</head>

<body>
<div class="whole">

	<header class="header">
        <a href="javascript:history.back(-1)" class="fa fa-angle-left"></a>
        <span class="names">变形金刚5：最后的骑士</span>
    </header>
    
    <div class="seat_head">
    	<h3>北京沃美影城常营店</h3>
        <span>
        	<a>今天07-06</a>
            <a>19:00</a>
            <a>(国语 3D)</a>
        </span>
    </div>
    
    <div class="seat_show">
    	<ul>
        	<li>
            	<i></i>
                <span>可选</span>
            </li>
            <li>
            	<i></i>
                <span>已售</span>
            </li>
            <li>
            	<i></i>
                <span>已选</span>
            </li>
            <li>
            	<i></i>
                <span>最佳区域</span>
            </li>
        </ul>
    </div>
    
    <div class="seat_choose">
    	<div class="number" id="num"></div>
    	<div class="seats" id="seats"></div>
        
    </div>
    <div class="buttons" onclick="show()">确认选择</div>
	<li class=""><input type="checkbox" name="seat-1" id="seat-1"><label for="seat-1"></label></li>
</div>

<script>
function show(){
	var number = $('#num');
	var seats = $('#seats');
	var touchs = $('#touchs');
	$("input[type='checkbox']").each(function(){
//		if($(this).attr("checked") != undefined )  这里筛选出来的就是已经被选择的座位   seat1  -  seat204
//	    	alert($(this).attr("name"));
	});
}
$(function(){
	var html='';
		html+='<ul class="touchs" id="touchs"><div class="screen">大厅屏幕</div>';
		 for(var i=1; i<=204; i++){
			var selected = (i == 204) ? 'selected' : '';//(i>91&&i<98 ? 'selected' : '');  //这里是设置什么位置被选中的
			html+='<li class="'+selected+'">';
			html+='<input type="checkbox" name="seat-'+i+'" id="seat-'+i+'" />';
			html+='<label for="seat-'+i+'"></label>';
			html+='</li>';
		} 
		html+='<div class="the_best"></div><div class="crossnum" id="crossnum"></div></ul>';
		$('#seats').html(html);
		
	
	$('.selected').children('input').attr({'disabled':'disabled','checked':'checked'});
	
		
	$('.seats li input').on('click',function(){
		var checklen = $('.seats li').not('.selected').children('input:checked').length;
		console.log(checklen);
		if(checklen>2){
			popu('最多只能选择两个座位');
			return false;
		}
	});
	
	
	//公用弹出层
	function popu(content){
		layer.open({
			content: content
			,skin: 'msg'
			,time: 3
		});	
	}
	
	
	var num='';
		num+='<ul>';
		for(var i=1; i<=12; i++){
			num+='<li>'+i+'</li>';
		}
		html+='</ul>';
		$('#num').html(num);
		
	var crossnum='';
		crossnum+='<ul>';
		for(var j=1; j<=17; j++){
			crossnum+='<li>'+j+'</li>';
		}
		html+='</ul>';
		$('#crossnum').html(crossnum);
	
	
	
})
</script>

<!---拖拽js--->
<script>
$(function(){
    var flag = false;
    var cur = {
        x:0,
        y:0
    }
    var nx,ny,dx,dy,x,y ;
    function down(){
        flag = true;
        var touch ;
        if(event.touches){
            touch = event.touches[0];
        }else {
            touch = event;
        }
        cur.x = touch.clientX;
        cur.y = touch.clientY;
        dx = div2.offsetLeft;
        dy = div2.offsetTop;
    }
    function move(){
        if(flag){
            var touch ;
            if(event.touches){
                touch = event.touches[0];
            }else {
                touch = event;
            }
            nx = touch.clientX - cur.x;
            ny = touch.clientY - cur.y;
            x = dx+nx;
            y = dy+ny;
            div2.style.left = x+"px";
            //div2.style.top = y +"px";

			
            //阻止页面的滑动默认事件
            document.addEventListener("touchmove",function(){
                //event.preventDefault();
            },false);
        }
    }
    //鼠标释放时候的函数
    function end(){
        flag = false;
    }
    var div2 = document.getElementById("touchs");
    div2.addEventListener("mousedown",function(){
        down();
    },false);
    div2.addEventListener("touchstart",function(){
        down();
    },false)
    div2.addEventListener("mousemove",function(){
        move();
    },false);
    div2.addEventListener("touchmove",function(){
        move();
    },false)
    document.body.addEventListener("mouseup",function(){
        end();
    },false);
    div2.addEventListener("touchend",function(){
        end();
    },false);
	
});
</script>

</body>
</html>
