<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<meta charset="UTF-8" />
	<meta name="keywords" content="dap" />
	<meta name="description" content="dap" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="author" content="fei.liu" />
	<title>创客汇管理平台--新建课程</title>

	<link rel="stylesheet" href="${cdn}css/bootstrap.css"></link>
	<link rel="stylesheet" href="${cdn}css/font-awesome.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery-ui.css"></link>
	<link rel="stylesheet" href="${cdn}css/fullcalendar.css"></link>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
	<link rel="stylesheet" href="${cdn}css/rateit.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-datetimepicker.min.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery.cleditor.css"></link>
	<link rel="stylesheet" href="${cdn}css/uniform.default.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-switch.css"></link>
	<link rel="stylesheet" href="${cdn}css/style.css"></link>
	<link rel="stylesheet" href="${cdn}css/widgets.css"></link>
	
	<!--[if lt IE 9]>
	<script src="${cdn}js/html5shim.js"></script>
	<![endif]-->

	<link rel="Shortcut Icon" href="${cdn}image/icon.png" />

</head>
<body>
	<jsp:include page="control/header.jsp" />
	<div class="content">
		<jsp:include page="control/sidebar.jsp"></jsp:include>
		<div class="mainbar">
			<div class="page-head">
				<h2 class="pull-left">课程管理</h2>
				<div class="bread-crumb pull-right">
					<a class="btn btn-default btn-sm" href="/course/add">新建课程</a>
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
									<div class="pull-left">新建课程</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<div class="padd">
										<form id="add_course_form" class="form-horizontal" role="form" action="/course/save" method="post" enctype="multipart/form-data">
											<div class="form-group">
												<label class="col-lg-5 control-label" for="title">课程名称<span class="cofrequired">*</span>:</label>
												<div class="col-lg-7">
													<input name="name" id="name" type="text" class="form-control" placeholder="课程名称"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="speaker">主讲人<span class="cofrequired">*</span>:</label>
												<div class="col-lg-7">
													<input name="speaker" id="speaker" type="text" class="form-control" placeholder="主讲人"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="synopsis">摘要<span class="cofrequired">*</span>:</label>
												<div class="col-lg-7">
													<input name="synopsis" id="synopsis" type="text" class="form-control" placeholder="摘要"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="time">时长(分钟)<span class="cofrequired">*</span>:</label>
												<div class="col-lg-7">
													<input name="time" id="time" type="text" class="form-control" placeholder="时长(分钟)"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="status">状态<span class="cofrequired">*</span>:</label>
												<div class="col-lg-7">
													<jsp:include page="control/commons-status.jsp">
														<jsp:param value="active" name="status"/>
														<jsp:param value="false" name="has_all"/>
														<jsp:param value="false" name="update"/>
													</jsp:include>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="free">是否免费<span class="cofrequired">*</span>:</label>
												<div class="col-lg-7">
													<select id="free" class="form-control" name="free">
														<option value="1" >免费</option>
														<option value="0" >非免费</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="coverImg">封皮<span class="cofrequired">*</span>:</label>
												<div class="col-lg-7">
													<input name="coverImg" id="coverImg" type="file" class="form-control" placeholder="封皮"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="media">视频<span class="cofrequired">*</span>:</label>
												<div class="col-lg-7">
													<input name="media" id="media" type="file" class="form-control" placeholder="视频"></input>
												</div>
											</div>
											<hr/>
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
		<div class="clearfix"></div>
	</div>
	<!--
	<jsp:include page="control/copy-rights.jsp" />
	-->
	<script src="${cdn}js/jquery.js"></script>
	<script src="${cdn}js/bootstrap.js"></script>
	<script src="${cdn}js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="${cdn}js/fullcalendar.min.js"></script>
	<script src="${cdn}js/jquery.rateit.min.js"></script>
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>
	<script src="${cdn}js/excanvas.min.js"></script>
	<script src="${cdn}js/jquery.flot.js"></script>
	<script src="${cdn}js/jquery.flot.resize.js"></script>
	<script src="${cdn}js/jquery.flot.pie.js"></script>
	<script src="${cdn}js/jquery.flot.stack.js"></script>
	<script src="${cdn}js/jquery.noty.js"></script>
	<script src="${cdn}js/themes/default.js"></script>
	<script src="${cdn}js/layouts/bottom.js"></script>
	<script src="${cdn}js/layouts/topRight.js"></script>
	<script src="${cdn}js/layouts/top.js"></script>
	<script src="${cdn}js/sparklines.js"></script>
	<script src="${cdn}js/jquery.cleditor.min.js"></script>
	<script src="${cdn}js/bootstrap-datetimepicker.min.js"></script>
	<script src="${cdn}js/jquery.uniform.min.js"></script>
	<script src="${cdn}js/bootstrap-switch.min.js"></script>
	<script src="${cdn}js/filter.js"></script>
	<script src="${cdn}js/custom.js"></script>
	<script src="${cdn}js/charts.js"></script>
	
	<script src="${cdn}js/jquery.validate.min.js"></script>
	
	<script>
		$(document).ready(function() {
			$('#name').focus();
	        $('#add_course_form').validate({
	            rules: {
	            	name: {
	                    required: true,
	                    minlength: 2,
	                    maxlength: 64
	                },
	                speaker: {
	                	required: true,
	                    minlength: 2,
	                    maxlength: 20
	                },
	                synopsis: {
	                	required: true,
	                    minlength: 2,
	                    maxlength: 256
	                },
	                time: {
	                	required: true,
	                	digits: true
	                },
	                coverImg: {
	                	required: true
	                },
	                media: {
	                	required: true
	                }
	            },
	            messages: {
	            	name: {
	                    required: '名称不能为空',
	                    minlength: "名称长度不能小于2个字符",
	                    maxlength: "名称长度不能大于64个字符"
	                },
	                speaker: {
	                    required: '主讲人不能为空',
	                    minlength: "主讲人长度不能小于2个字符",
	                    maxlength: "主讲人长度不能大于20个字符"
	                },
	                synopsis: {
	                    required: '摘要不能为空',
	                    minlength: "摘要长度不能小于2个字符",
	                    maxlength: "摘要长度不能大于256个字符"
	                },
	                time: {
	                    required: '时长不能为空',
	                    digits: "只能输入整数"
	                },
	                coverImg: {
	                	required: "必须上传封面图片"
	                },
	                media: {
	                	required: "必须上传视频"
	                }
	            },
	            submitHandler: function(form) {
	                form.submit();
	            }
	        });
		});
	</script>
	
</body>
</html>