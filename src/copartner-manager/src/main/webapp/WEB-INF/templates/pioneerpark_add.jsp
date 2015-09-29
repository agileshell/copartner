<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>新建创业园</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
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
										
										<div class="tabbable" style="margin-bottom: 18px;">
					                      <ul class="nav nav-tabs">
					                        <li class="active"><a href="#base_info" data-toggle="tab">基本信息</a></li>
					                        <li><a href="#address" data-toggle="tab">地理位置</a></li>
					                      </ul>
					                      <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
					                        <div class="tab-pane active" id="base_info">
					                        	
												<div class="form-group">
													<label class="col-lg-5 control-label" for="name">名称<span class="cofrequired">*</span>:</label>
													<div class="col-lg-7">
														<input name="name" id="name" type="text" class="form-control" placeholder="名称"></input>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-lg-5 control-label" for="content">简介:</label>
													<div class="col-lg-7">
														<textarea name="content" id="content" class="form-control" rows="3" placeholder="简介"></textarea>
													</div>
												</div>
												
					                        </div>
					                        <div class="tab-pane" id="address">
					                        	<div class="form-group">
													<label class="col-lg-5 control-label" for="pca">省市区<span class="cofrequired">*</span>:</label>
													<div class="col-lg-7">
														
														<div id="pca_distpicker" data-toggle="distpicker" class="form-control">
														  <select data-province="---- 选择省 ----"></select>
														  <select data-city="---- 选择市 ----"></select>
														  <select data-district="---- 选择区 ----"></select>
														</div>
														
													</div>
													
												</div>
												
												<div class="form-group">
													<label class="col-lg-5 control-label" for="addressDetail">详细地址<span class="cofrequired">*</span>:</label>
													<div class="col-lg-7">
														<input name="addressDetail" id="addressDetail" type="text" class="form-control" placeholder="详细地址"></input>
													</div>
												</div>
					                        </div>
					                      </div>
					                    </div>
										
										<hr />
										
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-9">
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
	
	<script charset="utf-8" src="${cdn}js/distpicker/distpicker.data.min.js"></script>
	<script charset="utf-8" src="${cdn}js/distpicker/distpicker.min.js"></script>
	
	<script>
	
		$.fn.distpicker.noConflict();
		
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
	            autoHeightMode : true,
	            afterCreate : function() {
	                this.loadPlugin('autoheight');
	            },
	            afterBlur: function() {this.sync();}
	        });
	    });
		
		$(document).ready(function() {
			  
			$("#pca_distpicker").distpicker({
			  placeholder: false
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
