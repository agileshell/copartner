<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>政策解读详情</title>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">政策解读管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/content/add">新建政策解读</a>
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
								<div class="pull-left">政策解读详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">政策解读不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
										<div class="form-group">
											<label class="col-lg-2 control-label" >标题:</label>
											<div class="col-lg-10">${content.title}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >摘要:</label>
											<div class="col-lg-10">${content.synopsis}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >类型:</label>
											<div class="col-lg-10">
												<c:if test="${c.type == 2}"> 地方政策 </c:if>
												<c:if test="${c.type == 1}"> 国家政策 </c:if>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">封皮:</label>
											<div class="col-lg-10">
												<div class="gallery">
							                      	<a href="${cdnDomain}${content.coverImg}" class="prettyPhoto[pp_gal]">
							                      		<img src="${cdnDomain}${content.coverImg}" alt="${content.title}" width="500">
							                      	</a>
							                      </div>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">内容:</label>
											<div class="col-lg-10">${content.article}</div>
										</div>
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
												<a class="btn btn-default btn-sm" href="/content/list">列表</a>
												<a class="btn btn-default btn-sm" href="/content/edit/${content.id}">编辑</a>
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
