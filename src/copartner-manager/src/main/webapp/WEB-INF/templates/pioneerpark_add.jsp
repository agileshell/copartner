<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>新建创业园</title>
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
								<div class="pull-left">新建创业园</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<form id="add_pioneerpark_form" class="form-horizontal" role="form" action="/pioneerpark/save" method="post" enctype="multipart/form-data">
										
										<input type="hidden" name="longitude" id="longitude"></input>
										<input type="hidden" name="latitude" id="latitude"></input>
										
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
														<input name="name" id="name" type="text" class="form-control" placeholder="名称"></input>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-lg-2 control-label" for="content">简介:</label>
													<div class="col-lg-10">
														<textarea name="content" id="content" class="form-control" rows="3" placeholder="简介"></textarea>
													</div>
												</div>
												
					                        </div>
					                        <div class="tab-pane" id="address">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="pca">省市区<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<div id="pca_distpicker" class="form-control"> 
														    省份:<select class="prov" name="province" id="province"></select>  
														    城市:<select class="city" name="city" disabled="disabled" id="city"></select> 
														    区县:<select class="dist" name="area" disabled="disabled" id="area"></select> 
														</div>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-lg-2 control-label" for="addressDetail">详细地址<span class="cofrequired">*</span>:</label>
													<div class="col-lg-8">
														<input name="addressDetail" id="addressDetail" type="text" class="form-control" placeholder="详细地址"></input>
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
			
			$("#pca_distpicker").citySelect({  
			    url:"/assets/js/cityselect/city.min.js",
			    prov : "河南省", //省份
			    city : "郑州市", //城市
			    dist : "金水区"//, //区县
			    //nodata : "none" //当子集无数据时，隐藏select
			});
			
			var map = new BMap.Map("map_canvas");
			map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
			map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
			map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
			map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
			map.centerAndZoom(new BMap.Point(34.8059290000, 113.6671710000), 12);
			var myGeo = new BMap.Geocoder();
			map.setDefaultCursor("url('bird.cur')");   //设置地图默认的鼠标指针样式
			
			myGeo.getPoint("河南省郑州市金水区", function(point) {
				if (point) {// {"lng":116.30799,"lat":40.058692}
					map.centerAndZoom(point, 16);
				}else{
					alert("您选择地址没有解析到结果!");
				}
			}, "河南省");
			
			var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(function(r){
				if(this.getStatus() == BMAP_STATUS_SUCCESS){
					map.centerAndZoom(r.point, 16);
				}
			},{enableHighAccuracy: true})
			
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
				map.centerAndZoom(e.point, 16);
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
			
			$("#search_map").click(function() {
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
						var marker = new BMap.Marker(point);  // 创建标注
						map.addOverlay(marker);              // 将标注添加到地图中
						map.centerAndZoom(point, 16);
						marker.enableDragging(); //marker可拖拽
						
						var label = new BMap.Label(address,{offset:new BMap.Size(20,-10)});
						marker.setLabel(label);
						
						$("#longitude").val(point.lng);
						$("#latitude").val(point.lat);
						
					}else{
						alert("未找到您选择的地址!!!");
					}
				}, province + city);
				
			});
			
			$('#name').focus();
	        $('#add_pioneerpark_form').validate({
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
	                specials: {
	                	required: true,
	                    minlength: 2,
	                    maxlength: 256
	                },
	                hardware: {
	                	required: true,
	                    minlength: 2,
	                    maxlength: 256
	                }
	            },
	            messages: {
	            	title: {
	                    required: '名称不能为空',
	                    minlength: "名称长度不能小于2个字符",
	                    maxlength: "名称长度不能大于64个字符"
	                },
	                synopsis: {
	                    required: '特色信息不能为空',
	                    minlength: "特色信息长度不能小于2个字符",
	                    maxlength: "特色信息长度不能大于256个字符"
	                },
	                hardware: {
	                    required: '硬件信息不能为空',
	                    minlength: "硬件信息长度不能小于2个字符",
	                    maxlength: "硬件信息长度不能大于256个字符"
	                }
	            },
	            submitHandler: function(form) {
	                form.submit();
	            }
	        });
	        
		});
	</script>
</body>
