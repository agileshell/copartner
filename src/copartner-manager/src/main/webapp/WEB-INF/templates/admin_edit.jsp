<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>更新管理员</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">管理员管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/admin/add">添加管理员</a>
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
								<div class="pull-left">更新管理员</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<form id="add_admin_form" class="form-horizontal" role="form" action="/admin/update/${admin.id}" method="POST">
										<div class="form profile">
											<div class="form-group">
												<label class="control-label col-lg-3" for="loginName">登录名<span class="cofrequired">*</span>:</label>
												<div class="col-lg-6">
													<input type="text" value="${admin.loginName}" class="form-control" id="loginName" name="loginName" placeholder="登录名">
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-3" for="name">显示名<span class="cofrequired">*</span>:</label>
												<div class="col-lg-6">
													<input type="text" value="${admin.name}" class="form-control" id="name" name="name" placeholder="显示名">
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-3" for="permission">权限<span class="cofrequired">*</span>:</label>
												<div class="col-lg-6">
													<select class="form-control" name="permission">
														<option value="2" <c:if test="${admin.permissionCode == 2}"> selected="selected" </c:if>>超级管理员</option>
														<option value="1" <c:if test="${admin.permissionCode == 1}"> selected="selected" </c:if>>管理员</option>
														<option value="0" <c:if test="${admin.permissionCode == 0}"> selected="selected" </c:if>>一般用户</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-3" for="status">状态<span class="cofrequired">*</span>:</label>
												<div class="col-lg-6">
													<select class="form-control" name="status">
														<option value="1" <c:if test="${admin.status == 2}"> selected="selected" </c:if>>有效</option>
														<option value="0" <c:if test="${admin.status == 0}"> selected="selected" </c:if>>锁定</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<div class="col-lg-6 col-lg-offset-1">
													<button type="submit" class="btn btn-success">提交</button>
												</div>
											</div>
										</div>
									</form>
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

	<script>
		$(document).ready(function() {
			$('#loginName').focus();
			$('#add_admin_form').validate({
				onsubmit : true,
				onfocusout : false,
				onkeyup : false,
				onkeyup : false,
				onclick : false,
				rules : {
					name : {
						required : true,
						minlength : 2,
						maxlength : 20
					},
					loginName : {
						required : true,
						minlength : 2,
						maxlength : 20
					}
				},
				messages : {
					name : {
						required : '显示名不能为空',
						minlength : "显示名长度不能小于2个字符",
						maxlength : "显示名长度不能大于20个字符"
					},
					loginName : {
						required : '登录名不能为空',
						minlength : "登录名长度不能小于2个字符",
						maxlength : "登录名长度不能大于20个字符"
					}
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
		});
	</script>

</body>
