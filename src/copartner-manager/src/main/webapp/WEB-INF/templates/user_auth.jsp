<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>用户认证审核</title>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">用户认证审核</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>用户认证审核
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
								<div class="pull-left">用户认证审核</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">用户不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
										<form id="add_status_form" class="form-horizontal" role="form" action="/user/auth/${user.id}" method="post">
											<div class="form-group">
												<label class="col-lg-2 control-label" >姓名:</label>
												<div class="col-lg-10">${user.name}</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label" >身份证号:</label>
												<div class="col-lg-10">${user.idNumber}</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label" >专业:</label>
												<div class="col-lg-10">
													<c:if test="${user.professionId == 1}"> 学术型 </c:if>
													<c:if test="${user.professionId == 2}"> 实业型 </c:if>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-2 control-label">身份证图片:</label>
												<div class="col-lg-10">
													<div class="gallery">
								                      	<a href="<t:cdn domain="${cdnDomain}" path="${user.idPicture}"></t:cdn>" class="prettyPhoto[pp_gal]">
								                      		<img src="<t:cdn domain="${cdnDomain}" path="${user.idPicture}"></t:cdn>" alt="${user.name}" width="100">
								                      	</a>
								                      </div>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-2 control-label" >审核状态:</label>
												<div class="col-lg-10">
													<select id="type" class="form-control" name="authenticated">
														<option value="false" selected="selected">认证不通过</option>
														<option value="true" >认证通过</option>
													</select>
												</div>
											</div>
											
											<hr />
											<div class="form-group">
												<div class="col-lg-offset-1 col-lg-12">
													<button type="submit" class="btn btn-success">提交审核</button>
												</div>
											</div>
										</form>
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
