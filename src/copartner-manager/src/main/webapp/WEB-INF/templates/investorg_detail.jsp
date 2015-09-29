<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>投资机构详情</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">投资机构管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/investorg/add">新建投资机构</a>
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
								<div class="pull-left">投资机构详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">投资机构不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
										<div class="form-group">
											<label class="col-lg-2 control-label" >名称:</label>
											<div class="col-lg-10">${investOrg.name}</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">LOGO:</label>
											<div class="col-lg-10">
												<div class="gallery">
							                      	<a href="${cdnDomain}${investOrg.logo}" class="prettyPhoto[pp_gal]">
							                      		<img src="${cdnDomain}${investOrg.logo}" alt="${investOrg.name}" width="80">
							                      	</a>
							                      </div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >特色:</label>
											<div class="col-lg-10">${investOrg.specials}</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">硬件:</label>
											<div class="col-lg-10">${investOrg.hardware}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">简介:</label>
											<div class="col-lg-10">${investOrg.content}</div>
										</div>
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
												<a class="btn btn-default btn-sm" href="/investorg/list">列表</a>
												<a class="btn btn-default btn-sm" href="/investorg/edit/${investOrg.id}">编辑</a>
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
