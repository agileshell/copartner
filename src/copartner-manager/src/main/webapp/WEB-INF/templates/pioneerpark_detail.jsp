<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>创业园详情</title>

<style type="text/css">
#map_canvas img, .google-maps img {
	max-width: none;
}
</style>

<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=HuEFhWtwXsXsKjXIgmIZFu4y"></script>

<script type="text/javascript"
	src="${cdn}js/baidumap/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="${cdn}js/baidumap/SearchInfoWindow_min.css" />

</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">创业园管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/pioneerpark/add">新建创业园</a>
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
								<div class="pull-left">创业园详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">创业园不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
										
										<input type="hidden" name="longitude" id="longitude" value="${pioneerPark.longitude}"></input>
										<input type="hidden" name="latitude" id="latitude" value="${pioneerPark.latitude}"></input>
										
										<input type="hidden" id="province" value="${pioneerPark.province}"></input>
										<input type="hidden" id="city" value="${pioneerPark.city}"></input>
										<input type="hidden" id="area" value="${pioneerPark.area}"></input>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">名称:</label>
											<div class="col-lg-10">${pioneerPark.name}</div>
										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label">地址:</label>
											<div class="col-lg-10">${pioneerPark.address}</div>
										</div>
										<div class="form-group">
											<div class="col-lg-12">
												<div class="widget-content">
													<div class="padd" id="map_canvas" style="height:300px;img{max-width:none;}"></div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">简介:</label>
											<div class="col-lg-10">${pioneerPark.content}</div>
										</div>
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
												<a class="btn btn-default btn-sm" href="/pioneerpark/list">列表</a>
												<a class="btn btn-default btn-sm"
													href="/pioneerpark/edit/${pioneerPark.id}">编辑</a>
											</div>
										</div>
									</c:if>
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
	<script type="text/javascript">
	$(document).ready(function() {
		
		var province = $("#province").val();
		var city = $("#city").val();
		var area = $("#area").val();

		var longitude = $("#longitude").val();
		var latitude = $("#latitude").val();
		
		area = area || "";
		var addressDetail = $("#addressDetail").val();
		
		var address = province + city + area + addressDetail;
		var map = new BMap.Map("map_canvas");
		map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		map.centerAndZoom(province, 11);
		var myGeo = new BMap.Geocoder();
		map.setDefaultCursor("url('bird.cur')");   //设置地图默认的鼠标指针样式
		map.enableDragging();   // 开启拖拽
		map.enableInertialDragging();   // 开启惯性拖拽
		
		myGeo.getPoint(address, function(point) {
			if (point) {
				var marker = new BMap.Marker(point);  // 创建标注
				map.addOverlay(marker);              // 将标注添加到地图中
				map.centerAndZoom(point, 11);
				var pointArray = new Array();
				pointArray[0] = point;
				map.setViewport(pointArray);
			}else{
				alert("您选择地址没有解析到结果!");
			}
		}, province);
	});
	</script>
</body>
