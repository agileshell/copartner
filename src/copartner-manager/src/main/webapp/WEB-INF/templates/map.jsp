<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>百度地图接入</title>
<style type="text/css">
	#map_canvas img {
	   max-width: none;
	}
	#map_canvas img,
	.google-maps img {
	  max-width: none;
	}
</style>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">百度地图接入</h2>
			<div class="bread-crumb pull-right">
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
			<div class="container">
				<!--row start-->
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">百度地图接入</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									
									<div class="row">
										<div class="col-md-12">
											<div class="widget-content" id="map_canvas" style="img {max-width:none;}"></div>
										</div>
									</div>
									
								</div>
							</div>
							<div class="widget-foot"></div>
						</div>
					</div>
				</div>
				<!--row end-->
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=HuEFhWtwXsXsKjXIgmIZFu4y"></script>

	<script type="text/javascript">
	
		$(function() {
			
			/**
			$("#searchMap").click(function() {
				
			});
			**/
			
			setTimeout(function() {//添加延时加载。解决问题
				alert("渲染地图");
				
				// 百度地图API功能
				var map = new BMap.Map("map_canvas");
				var point = new BMap.Point(116.331398, 39.897445);
				map.centerAndZoom(point, 12);
				// 创建地址解析器实例
				var myGeo = new BMap.Geocoder();
				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint("北京市海淀区上地10街", function(point){
					if (point) {// {"lng":116.30799,"lat":40.058692}
						alert("地理位置点 : " + JSON.stringify(point));
						map.centerAndZoom(point, 16);
						map.addOverlay(new BMap.Marker(point));
					}else{
						alert("您选择地址没有解析到结果!");
					}
				}, "北京市");
				
				
				/**
				// 百度地图API功能
				var map = new BMap.Map("map_canvas");

				map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
				map.setCurrentCity("北京"); // 设置地图显示的城市 此项是必须设置的
				map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放

				var point = new BMap.Point(116.331398, 39.897445);
				map.centerAndZoom(point, 12);
				// 创建地址解析器实例
				var myGeo = new BMap.Geocoder();

				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint("北京市海淀区上地10街", function(point) {
					if (point) {
						console.log("地理位置点 : " + JSON.stringify(point));
						map.centerAndZoom(point, 16);
						map.addOverlay(new BMap.Marker(point));
					} else {
						alert("您选择地址没有解析到结果!");
					}
				}, "北京市");
				**/
				
			}, 300);
			
		});
		
	</script>
	
</body>
