<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>用户详情</title>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">用户详情</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>用户详情
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
								<div class="pull-left">用户详情</div>
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
										<div class="form-group">
											<label class="col-lg-2 control-label" >姓名:</label>
											<div class="col-lg-10">${user.name}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >身份证:</label>
											<div class="col-lg-10">${user.idNumber}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >身份认证:</label>
											<div class="col-lg-10">
												<c:if test="${user.authenticated}">
													<i style="color: #39bd94;">已认证</i>
												</c:if>
												<c:if test="${!user.authenticated}">
													<a href="/user/authentication/${user.id}"><i style="color: #ff6600;">未认证</i></a>
												</c:if>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >VIP等级:</label>
											<div class="col-lg-10">${user.level}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >积分:</label>
											<div class="col-lg-10">${user.points}</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">头像:</label>
											<div class="col-lg-10">
												<div class="gallery">
							                      	<a href="${cdnDomain}${user.avatar}" class="prettyPhoto[pp_gal]">
							                      		<img src="${cdnDomain}${user.avatar}" alt="${user.name}" width="100">
							                      	</a>
							                      </div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >邮箱:</label>
											<div class="col-lg-10">
												${user.email}&nbsp;&nbsp;|&nbsp;&nbsp;
												<c:if test="${user.isEmailVerified}">
													<i style="color: #39bd94;">已认证</i>
												</c:if>
												<c:if test="${!user.isEmailVerified}">
													<i style="color: #ff6600;">未认证</i>
												</c:if>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">手机号:</label>
											<div class="col-lg-10">
												${user.mobile}&nbsp;&nbsp;|&nbsp;&nbsp;
												<c:if test="${user.isMobileVerified}">
													<i style="color: #39bd94;">已认证</i>
												</c:if>
												<c:if test="${!user.isMobileVerified}">
													<i style="color: #ff6600;">未认证</i>
												</c:if>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">领域:</label>
											<div class="col-lg-10">${domains}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">创业角色:</label>
											<div class="col-lg-10">${startupRole}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">创业状态:</label>
											<div class="col-lg-10">${startupStatus}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">地址:</label>
											<div class="col-lg-10">${user.fullLocation}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">简介:</label>
											<div class="col-lg-10">${user.introduction}</div>
										</div>
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
												<a class="btn btn-default btn-sm" href="/user/list">列表</a>
												<!--<a class="btn btn-default btn-sm" href="/user/chat/${user.id}" target="_self">即时聊天</a>-->
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
