<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>课程详情</title>
	<link href="${cdn}css/video-js.css" rel="stylesheet" type="text/css">
	<script src="${cdn}js/video.js"></script>
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
								<div class="pull-left">课程详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">课程不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
										<div class="form-group">
											<label class="col-lg-2 control-label" >课程名称:</label>
											<div class="col-lg-10">${course.name}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >主讲人:</label>
											<div class="col-lg-10">${course.speaker}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >摘要:</label>
											<div class="col-lg-10">${course.synopsis}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >时长(分钟):</label>
											<div class="col-lg-10">${course.time}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >浏览次数:</label>
											<div class="col-lg-10">${course.clicks}次</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >是否免费:</label>
											<div class="col-lg-10">
												<c:if test="${course.isFree}"> 免费 </c:if>
												<c:if test="${!course.isFree}"> 非免费 </c:if>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">封皮:</label>
											<div class="col-lg-10">
											<img alt="${course.name}" src="${cdnDomain}${course.coverImg}" width="500"></img>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">视频:</label>
											<div class="col-lg-10">
											  <video id="cop.video.media" class="video-js vjs-default-skin" controls preload="none" width="500" height="300"
											      poster="${cdnDomain}${course.url}?vframe/jpg/offset/0/w/600/h/600"
											      data-setup="{}">
											    <source src="${cdnDomain}${course.url}" type='video/mp4' />
											    <source src="${cdnDomain}${course.url}" type='video/webm' />
											    <source src="${cdnDomain}${course.url}" type='video/ogg' />
											  </video>
											</div>
										</div>
										
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
												<a class="btn btn-default btn-sm" href="/course/list">列表</a>
												<a class="btn btn-default btn-sm" href="/course/edit/${course.id}">编辑</a>
											</div>
										</div>
									</c:if>
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

</body>
