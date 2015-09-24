<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>新闻动态详情</title>
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
											<label class="col-lg-5 control-label" >标题:</label>
											<div class="col-lg-7">${news.title}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-5 control-label" >摘要:</label>
											<div class="col-lg-7">${news.synopsis}</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-5 control-label">封皮:</label>
											<div class="col-lg-7">
											<img alt="${news.title}" src="${cdnDomain}${news.coverImg}" width="500"></img>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-5 control-label">内容:</label>
											<div class="col-lg-7">${news.article}</div>
										</div>
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-9">
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

</body>
