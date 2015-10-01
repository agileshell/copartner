<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>编辑创业园</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
	
	<style type="text/css">
		#map_canvas img,
		.google-maps img {
		  max-width: none;
		}
	</style>
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=HuEFhWtwXsXsKjXIgmIZFu4y"></script>
	
	<script type="text/javascript" src="${cdn}js/baidumap/SearchInfoWindow_min.js"></script>
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
								<div class="pull-left">编辑创业园</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<form id="edit_pioneerpark_form" class="form-horizontal" role="form" action="/pioneerpark/update/${pioneerPark.id}" method="post" enctype="multipart/form-data">
										
										<input type="hidden" name="longitude" id="longitude" value="${pioneerPark.longitude}"></input>
										<input type="hidden" name="latitude" id="latitude" value="${pioneerPark.latitude}"></input>
										
										<input type="hidden" id="provinceVal" value="${pioneerPark.province}"></input>
										<input type="hidden" id="cityVal" value="${pioneerPark.city}"></input>
										<input type="hidden" id="areaVal" value="${pioneerPark.area}"></input>
										
										<div class="tabbable" style="margin-bottom: 18px;">
					                      <ul class="nav nav-tabs">
					                        <li class="active"><a href="#base_info" data-toggle="tab">基本信息</a></li>
					                        <li><a href="#address" data-toggle="tab">地理位置</a></li>
					                      </ul>
					                      <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
					                        <div class="tab-pane active" id="base_info">
					                        	
												<div class="form-group">
													<label class="col-lg-2 control-label" for="name">名称<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input value="${pioneerPark.name}" name="name" id="name" type="text" class="form-control" placeholder="名称"></input>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-lg-2 control-label" for="content">简介:</label>
													<div class="col-lg-10">
														<textarea name="content" id="content" class="form-control" rows="3" placeholder="简介">${pioneerPark.content}</textarea>
													</div>
												</div>
												
					                        </div>
					                        <div class="tab-pane" id="address">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="pca">省市区<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<div id="pca_distpicker" class="form-control"> 
														    省份&nbsp;:&nbsp;<select class="prov" name="province" id="province"></select>  
														    城市&nbsp;:&nbsp;<select class="city" name="city" disabled="disabled" id="city"></select> 
														    区县&nbsp;:&nbsp;<select class="dist" name="area" disabled="disabled" id="area"></select> 
														</div>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-lg-2 control-label" for="addressDetail">详细地址<span class="cofrequired">*</span>:</label>
													<div class="col-lg-8">
														<input value="${pioneerPark.addressDetail}" name="addressDetail" id="addressDetail" type="text" class="form-control" placeholder="详细地址"></input>
													</div>
													<div class="col-lg-2">
														<button id="search_map" type="button" class="btn btn-default">查询</button>
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-lg-12">
														<div class="widget-content">
															<div class="padd" id="map_canvas" style="height:300px;img{max-width:none;}"></div>
														</div>
													</div>
												</div>
												
					                        </div>
					                      </div>
					                    </div>
										
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
												<button type="submit" class="btn btn-default">提交</button>
											</div>
										</div>
									</form>
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
	
	<script charset="utf-8" src="${cdn}js/kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="${cdn}js/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${cdn}js/kindeditor/plugins/autoheight/autoheight.js"></script>
	
	<script charset="utf-8" src="${cdn}js/cityselect/jquery.cityselect.js"></script>
	
	<script>
	
	    KindEditor.ready(function(K) {
	        window.editor = K.create('#content', {
	            langType : 'zh_CN',
	            uploadJson : '/editor/file_upload',
	            items : [
	                     'source', '|', 'undo', 'redo', '|', 'preview', 'template', 'code', 'cut', 'copy', 'paste',
	                     'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
	                     'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	                     'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
	                     'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
	                     'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
	                     'flash', 'media', 'insertfile', 'table', 'hr', 'baidumap', 'pagebreak',
	                     'anchor', 'link', 'unlink'
	            ],
	            minHeight : 300,
	            width: "100%",
	            minWidth: 300,
	            autoHeightMode : true,
	            afterCreate : function() {
	                this.loadPlugin('autoheight');
	            },
	            afterBlur: function() {this.sync();}
	        });
	    });
		
		$(document).ready(function() {
			
			var province = $("#provinceVal").val();
			var city = $("#cityVal").val();
			var area = $("#areaVal").val();
			
			$("#pca_distpicker").citySelect({  
			    url : "/assets/js/cityselect/city.min.js",
			    prov : province, //省份
			    city : city, //城市
			    dist : area // //区县
			    //, nodata : "none" //当子集无数据时，隐藏select
			    , required : false
			});

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
			// 覆盖区域图层测试
			//map.addTileLayer(new BMap.PanoramaCoverageLayer());
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
					marker.enableDragging(); // marker可拖拽
					marker.addEventListener("click", function attribute() {
						var p = marker.getPosition(); // 获取marker的位置
						$("#longitude").val(p.lng);
						$("#latitude").val(p.lat);
					});
					var label = new BMap.Label(address, {offset: new BMap.Size(20,-10)});
					marker.setLabel(label);
				}else{
					alert("您选择地址没有解析到结果!");
				}
			}, province);
			
			$("#province").change(function() {
				var province = $(this).val();
				map.centerAndZoom(province, 11);// 用城市名设置地图中心点
			});
			
			$("#city").change(function() {
				var province = $("#province").val();
				var city = $(this).val();
				map.centerAndZoom(province + city, 11);// 用城市名设置地图中心点
			});
			
			$("#area").change(function() {
				var province = $("#province").val();
				var city = $("#city").val();
				var area = $(this).val();
				map.centerAndZoom(province + city + area, 11);// 用城市名设置地图中心点
			});
			
			map.addEventListener("click", function(e) {
				map.centerAndZoom(e.point, 11);
			});
			
			// 添加带有定位的导航控件
			var navigationControl = new BMap.NavigationControl({
			  // 靠左上角位置
			  anchor: BMAP_ANCHOR_TOP_LEFT,
			  // LARGE类型
			  type: BMAP_NAVIGATION_CONTROL_LARGE,
			  // 启用显示定位
			  enableGeolocation: true
			});
			map.addControl(navigationControl);
			// 添加定位控件
			var geolocationControl = new BMap.GeolocationControl();
			geolocationControl.addEventListener("locationSuccess", function(e) {
			  // 定位成功事件
			  var address = '';
			  address += e.addressComponent.province;
			  address += e.addressComponent.city;
			  address += e.addressComponent.district;
			  address += e.addressComponent.street;
			  address += e.addressComponent.streetNumber;
			  map.centerAndZoom(address, 11);// 用城市名设置地图中心点
			});
			geolocationControl.addEventListener("locationError",function(e) {
				
			});
			map.addControl(geolocationControl);
			
			function search_map() {
				var province = $("#province").val();
				var city = $("#city").val();
				var area = $("#area").val();
				var addressDetail = $("#addressDetail").val();
				if (province == null || city == null || province == "" || city == "") {
					alert("请选择省市区!!!");
					return;
				}
				if (addressDetail == null || addressDetail == "") {
					alert("请输入详细地址!!!");
					return;
				}
				area = area || "";
				var address = province + city + area + addressDetail;
				myGeo.getPoint(address, function(point) {
					if (point) {// {"lng":116.30799,"lat":40.058692}
						$("#longitude").val(point.lng);
						$("#latitude").val(point.lat);
						map.clearOverlays();
						var marker = new BMap.Marker(point);  // 创建标注
						map.addOverlay(marker);              // 将标注添加到地图中
						map.centerAndZoom(point, 16);
						marker.enableDragging(); //marker可拖拽
						marker.addEventListener("click", function attribute() {
							var p = marker.getPosition(); // 获取marker的位置
							$("#longitude").val(p.lng);
							$("#latitude").val(p.lat);
						});
						var geoc = new BMap.Geocoder();
						geoc.getLocation(point, function(rs){
							var addComp = rs.addressComponents;
							address = addComp.province + " " + addComp.city + " " + addComp.district + " " + addComp.street + " " + addComp.streetNumber;
							marker.setLabel(new BMap.Label(address, {offset: new BMap.Size(20, -10)}));
							
							/**
							if (addComp.province.contains("上海")
									|| addComp.province.contains("北京")
									|| addComp.province.contains("天津")
									|| addComp.province.contains("重庆")) {
								$("#provinceV").val(province);
								$("#cityV").val(addComp.district);
							} else {
								$("#provinceV").val(province);
								$("#cityV").val(city);
								$("#areaV").val(addComp.district);
							}
							$("#addressDetail").val(addComp.street + addComp.streetNumber);
							var box_obj=$("#pca_distpicker");
							var prov_obj=box_obj.find(".prov");
							var city_obj=box_obj.find(".city");
							var dist_obj=box_obj.find(".dist");
							if (addComp.province.contains("上海")
									|| addComp.province.contains("北京")
									|| addComp.province.contains("天津")
									|| addComp.province.contains("重庆")) {
								setTimeout(function(){
									city_obj.val(addComp.district);
								}, 1);
							} else {
								setTimeout(function(){
									dist_obj.val(addComp.district);
								}, 1);
							}
							**/
							
						});
					}else{
						alert("未找到您选择的地址!!!");
					}
				}, province + city);
				
			}
			
			$("#search_map").click(search_map);
			
			$('#name').focus();
	        $('#edit_pioneerpark_form').validate({
	        	onsubmit:true,
        	  	onfocusout:false,
        	  	onkeyup:false,
        	  	onkeyup:false,
        	  	onclick:false,
        	  	rules: {
	            	name: {
	            		required: true,
	                    minlength: 2,
	                    maxlength: 64
	                },
	                content: {
	                	required: true,
	                    minlength: 2
	                },
	                addressDetail: {
	                	required: true,
	                    minlength: 2,
	                    maxlength: 254
	                }
	            },
	            messages: {
	            	name: {
	                    required: '创业园名称不能为空',
	                    minlength: "创业园名称长度不能小于2个字符",
	                    maxlength: "创业园名称长度不能大于64个字符"
	                },
	                content: {
	                    required: '简介信息不能为空',
	                    minlength: "简介信息长度不能小于2个字符"
	                },
	                addressDetail: {
	                    required: '详细地址不能为空',
	                    minlength: "详细地址长度不能小于2个字符",
	                    maxlength: "详细地址长度不能大于254个字符"
	                }
	            },
	            submitHandler: function(form) {
	            	var name = $("#name").val();
	            	var content = $("#content").val();
	            	if (name == null || name == "" || content == null || content == "") {
	            		alert("请输入创业园名称和简介!!!");
						return;
	            	}
	    			var longitude = $("#longitude").val();
					var latitude = $("#latitude").val();
					if (longitude == null || longitude == "" || latitude == null || latitude == "") {
						search_map();
						alert("请输入省市区和详细地址并查询确认具体位置后提交!!!");
						return;
					}
	                form.submit();
	            }
	        });
	        
		});
	</script>
</body>
