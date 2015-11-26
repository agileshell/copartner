<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>新建参赛项目</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">参赛项目管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/contestentry/add">新建参赛项目</a>
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
								<div class="pull-left">新建参赛项目</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<form id="add_contestentry_form" class="form-horizontal" role="form" action="/contestentry/save" method="post" enctype="multipart/form-data">
										
										<div class="tabbable" style="margin-bottom: 18px;">
					                      <ul class="nav nav-tabs">
					                        <li class="active"><a href="#bash_info" data-toggle="tab">基本信息</a></li>
					                        <li><a href="#extend_info" data-toggle="tab">图文信息</a></li>
					                        <li><a href="#registration_info" data-toggle="tab">报名信息</a></li>
					                      </ul>
					                      <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
					                        <div class="tab-pane active" id="bash_info">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="name">参赛项目名称<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="name" id="name" type="text" class="form-control" placeholder="参赛项目名称"></input>
													</div>
												</div>
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="contestId">大赛ID<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="contestId" id="contestId" type="text" class="form-control" placeholder="大赛ID"></input>
													</div>
												</div>
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="userName">参赛者姓名<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="userName" id="userName" type="text" class="form-control" placeholder="参赛者姓名"></input>
													</div>
												</div>
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="contact">参赛者联系方式:</label>
													<div class="col-lg-10">
														<input name="contact" id="contact" type="text" class="form-control" placeholder="参赛者联系方式"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="status">状态<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="active" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="false" name="update"/>
														</jsp:include>
													</div>
												</div>
					                        </div>
					                        <div class="tab-pane" id="extend_info">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="coverImg">封皮<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="coverImg" id="coverImg" type="file" class="form-control" placeholder="封皮"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="introduction">项目简介:</label>
													<div class="col-lg-10">
														<textarea name="introduction" id="introduction" class="form-control" rows="3" placeholder="项目简介"></textarea>
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
	
	<script>
		$(document).ready(function() {
		    KindEditor.ready(function(K) {
		        window.editor = K.create('#introduction', {
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
		            afterBlur: function(){this.sync();}
		        });
		    });
		    
			$('#name').focus();
	        $('#add_contestentry_form').validate({
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
	                contestId: {
	                	required: true
	                },
	                userName: {
	                    required: true,
	                    minlength: 2,
	                    maxlength: 64
	                },
	                coverImg: {
	                	required: true
	                }
	            },
	            messages: {
	            	title: {
	                    required: '参赛项目名称不能为空',
	                    minlength: "参赛项目名称长度不能小于2个字符",
	                    maxlength: "参赛项目名称长度不能大于64个字符"
	                },
	                contestId: {
	                    required: '大赛ID不能为空'
	                },
	                userName: {
	                	required: '参赛者姓名不能为空',
	                    minlength: "参赛者姓名长度不能小于2个字符",
	                    maxlength: "参赛者姓名长度不能大于64个字符"
	                }
	                coverImg: {
	                	required: "必须上传封面图片"
	                }
	            },
	            submitHandler: function(form) {
	                form.submit();
	            }
	        });
	        
		});
	</script>
</body>
