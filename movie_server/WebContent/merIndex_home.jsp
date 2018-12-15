<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
		<meta name="format-detection" content="telephone=no, email=no, date=no, address=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<link href="merCss/bksystem.css" rel="stylesheet" type="text/css" />
		<link href="merFont/iconfont.css" rel="stylesheet" type="text/css" />
		<link href="merCss/module.css" rel="stylesheet" type="text/css" />
		<link href="merCss/pages.css" rel="stylesheet" type="text/css" />
		<title>商户首页</title>
		<script src="merJs/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="merJs/jquery.cookie.js" type="text/javascript"></script>
		<script src="merJs/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="merJs/HUpages.js" type="text/javascript"></script>
			<!--[if lt IE 9]>
          <script src="js/html5shiv.js" type="text/javascript"></script>
          <script src="js/css3-mediaqueries.js"  type="text/javascript"></script>
        <![endif]-->
	</head>
		<body id="situation" class="backgrounddd">
			<div class="spacing_style"></div>
			<div class="pages-style">
				<div class="clearfix">
					<div class="col-md-6">
						<div class="box-module">
							<div class="box-title">状态栏</div>
							<div class="box-content">
								<div class="Shops_info clearfix">
									<div class="left_shop">
										<div class="left_shop_logo">
											<div class="shop_logo"><img src="<c:url value='${loginedMerchant.imgList[0].imgPath }' />" onerror="this.src='merImages/noimage.gif',this.onerror=null"></div>
											
										</div>
										<div class="Shops_content">
											
											<ul class="clearfix">
												<li><label class="name">店铺名称：</label>${loginedMerchant.merStoreName }</li>
												<li><label class="name">商家ID：</label>${loginedMerchant.merId }</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</html>
<script src="js/dist/echarts.js" type="text/javascript"></script>
<script>
	//内页框架
	$(function() {
		$("#situation").Hupage({
			scrollbar:function(e){
				e.niceScroll({
					      cursorcolor: "#dddddd",
					      cursoropacitymax: 1,
					      touchbehavior: false,
					      cursorwidth: "3px",
					      cursorborder: "0",
					      cursorborderradius: "3px",
				 });						
			},
			expand: function(thisBox, settings) {
				settings.scrollbar(thisBox);//设置当前页滚动样式
			}
		})
	})
</script>
<script>
	/*********************/
   require.config({
            paths: {
                echarts: './js/dist'
            }
        });
        require(
            [
                'echarts',
				'echarts/theme/macarons',
                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                'echarts/chart/bar'
            ],
            function (ec,theme) {
                var myChart = ec.init(document.getElementById('main'),theme);
               option = {
    title : {
        text: '当周交易记录',
        subtext: '每周7天的交易记录'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['所有订单','已完成','未完成']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['周一','周二','周三','周四','周五','周六','周日']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}单'
            }
        }
    ],
    series : [
        {
            name:'所有订单',
            type:'line',
            data:[110, 210, 150, 130, 125, 133, 106],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'已完成',
            type:'line',
            data:[110, 105, 140, 130, 110, 121, 100],
            markPoint : {
                data : [
                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        },
		   {
            name:'未完成',
            type:'line',
            data:[0, 25, 10, 10, 15, 12, 6],
            markPoint : {
                data : [
                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};
                  
			myChart.setOption(option);
			}
			);
</script>