<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>需求详情</title>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">需求管理</h2>
			<div class="bread-crumb pull-right">
				<!-- <a class="btn btn-default btn-sm" href="/require/add">新建需求</a> -->
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
								<div class="pull-left">需求详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">需求不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
										<div class="form-group">
											<label class="col-lg-2 control-label" >需求类型:</label>
											<div class="col-lg-10">
												<c:if test="${require.type == 1}"> 加入团队 </c:if>
												<c:if test="${require.type == 2}"> 寻求搭档 </c:if>
												<c:if test="${require.type == 3}"> 寻求融资 </c:if>
												<c:if test="${require.type == 4}"> 寻求融智 </c:if>
												<c:if test="${require.type == 5}"> 投资项目 </c:if>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >关联项目:</label>
											<div class="col-lg-10">
												<a href="/project/detail/${require.projectId}">${require.projectName}</a>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >所属者:</label>
											<div class="col-lg-10">
												<a href="/user/detail/${require.user.userId}">${require.user.name}</a>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">内容:</label>
											<div class="col-lg-10">${require.content}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">收藏次数:</label>
											<div class="col-lg-10">${require.likeCount}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">评论次数:</label>
											<div class="col-lg-10">${require.commentCount}</div>
										</div>
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
												<a class="btn btn-default btn-sm" href="/require/list">列表</a>
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
	
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>

</body>
