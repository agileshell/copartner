<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>新建课程</title>
</head>
<body>
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
										
										<div class="tabbable" style="margin-bottom: 18px;">
					                      <ul class="nav nav-tabs">
					                        <li class="active"><a href="#tab1" data-toggle="tab">基本信息</a></li>
					                        <li><a href="#tab2" data-toggle="tab">音频信息</a></li>
					                      </ul>
					                      <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
					                        <div class="tab-pane active" id="tab1">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="title">课程名称<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="name" id="name" type="text" class="form-control" placeholder="课程名称"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="speaker">主讲人<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="speaker" id="speaker" type="text" class="form-control" placeholder="主讲人"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="synopsis">摘要<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<textarea name="synopsis" id="synopsis" class="form-control" rows="3" placeholder="摘要"></textarea>
														<!-- <input name="synopsis" id="synopsis" type="text" class="form-control" placeholder="摘要"></input> -->
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="time">时长(分钟)<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="time" id="time" type="text" class="form-control" placeholder="时长(分钟)"></input>
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
												<div class="form-group">
													<label class="col-lg-2 control-label" for="free">是否免费<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<select id="free" class="form-control" name="free">
															<option value="1" >免费</option>
															<option value="0" >非免费</option>
														</select>
													</div>
												</div>
					                        </div>
					                        <div class="tab-pane" id="tab2">
												<div class="form-group">
													<label class="col-lg-2 control-label" for="coverImg">封皮<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="coverImg" id="coverImg" type="file" class="form-control" placeholder="封皮"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="media">视频<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="media" id="media" type="text" class="form-control" placeholder="视频"></input>
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
	
	<script>
		$(document).ready(function() {
			$('#name').focus();
	        $('#add_course_form').validate({
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
	                media: {
	                	required: true
	                },
	                coverImg: {
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
	                media: {
	                	required: "必须上传视频"
	                },
	                coverImg: {
	                	required: "必须上传封皮"
	                }
	            },
	            submitHandler: function(form) {
	                form.submit();
	            }
	        });
		});
	</script>
	
</body>
