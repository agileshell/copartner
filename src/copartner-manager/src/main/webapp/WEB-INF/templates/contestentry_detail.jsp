<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>参赛项目详情</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
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
								<div class="pull-left">参赛项目详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<div class="form-group">
										<label class="col-lg-2 control-label" for="name">参赛项目名称:</label>
										<div class="col-lg-10">${contestEntry.name}</div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="contestId">大赛ID:</label>
										<div class="col-lg-10"><a href="/contest/detail/${contestEntry.contestId}">${contestEntry.contestId}</a></div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="userName">参赛者姓名:</label>
										<div class="col-lg-10">${contestEntry.userName}</div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">参赛者联系方式:</label>
										<div class="col-lg-10">${contestEntry.contact}</div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">赞的次数:</label>
										<div class="col-lg-10">${contestEntry.praise}次</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label">封皮:</label>
										<div class="col-lg-10">
										<div class="gallery">
					                      	<a href="${cdnDomain}${contestEntry.coverImg}" class="prettyPhoto[pp_gal]">
					                      		<img src="${cdnDomain}${contestEntry.coverImg}" alt="${contestEntry.title}" width="500">
					                      	</a>
					                      </div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="introduction">项目简介:</label>
										<div class="col-lg-10">${contestEntry.introduction}</div>
									</div>
									<hr/>
									<div class="form-group">
										<div class="col-lg-offset-1 col-lg-12">
											<a class="btn btn-default btn-sm" href="/contestentry/list">列表</a>
											<a class="btn btn-default btn-sm" href="/contestentry/edit/${contestEntry.id}">编辑</a>
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
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>
</body>
