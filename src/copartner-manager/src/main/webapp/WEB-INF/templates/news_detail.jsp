<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>新闻动态详情</title>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">新闻动态管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/news/add">新建新闻动态</a>
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
								<div class="pull-left">新闻动态详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">新闻动态不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
										<div class="form-group">
											<label class="col-lg-2 control-label" >标题:</label>
											<div class="col-lg-10">${news.title}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >摘要:</label>
											<div class="col-lg-10">${news.synopsis}</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">封皮:</label>
											<div class="col-lg-10">
												<div class="gallery">
							                      	<a href="<t:cdn domain="${cdnDomain}" path="${news.coverImg}"></t:cdn>" class="prettyPhoto[pp_gal]">
							                      		<img src="<t:cdn domain="${cdnDomain}" path="${news.coverImg}"></t:cdn>" alt="${news.title}" width="80">
							                      	</a>
							                      </div>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">内容:</label>
											<div class="col-lg-10">${news.article}</div>
										</div>
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
												<a class="btn btn-default btn-sm" href="/news/list">列表</a>
												<a class="btn btn-default btn-sm" href="/news/edit/${news.id}">编辑</a>
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
