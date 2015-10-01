<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>创业大赛详情</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">创业大赛管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/contest/add">新建创业大赛</a>
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
								<div class="pull-left">创业大赛详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									
									<div class="form-group">
										<label class="col-lg-2 control-label" for="title">大赛标题:</label>
										<div class="col-lg-10">${contest.title}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="introduction">大赛简介:</label>
										<div class="col-lg-10">${contest.introduction}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label">封皮:</label>
										<div class="col-lg-10">
										<div class="gallery">
					                      	<a href="${cdnDomain}${contest.coverImg}" class="prettyPhoto[pp_gal]">
					                      		<img src="${cdnDomain}${contest.coverImg}" alt="${contest.title}" width="500">
					                      	</a>
					                      </div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="rules">大赛规则:</label>
										<div class="col-lg-10">${contest.rules}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="registration">报名信息:</label>
										<div class="col-lg-10">${contest.registration}</div>
									</div>
									<hr/>
									<div class="form-group">
										<div class="col-lg-offset-1 col-lg-12">
											<a class="btn btn-default btn-sm" href="/contest/list">列表</a>
											<a class="btn btn-default btn-sm" href="/contest/edit/${contest.id}">编辑</a>
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
