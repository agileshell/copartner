<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<meta charset="UTF-8" />
	<meta name="keywords" content="dap" />
	<meta name="description" content="dap" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="author" content="andpay.me" />
	<title>创客管理平台--登录</title>
	<link rel="stylesheet" href="${cdn}css/bootstrap.css"></link>
	<link rel="stylesheet" href="${cdn}css/font-awesome.css"></link>
	<link rel="stylesheet" href="${cdn}css/style.css"></link>
	<!--[if lt IE 9]>
	<script src="${cdn}js/html5shim.js"></script>
	<![endif]-->
	<link rel="Shortcut Icon" href="${cdn}image/icon.png" />
</head>
<body>
	<div class="admin-form">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget worange">
						<div class="widget-head">
							<i class="icon-lock"></i>&nbsp;&nbsp;创客管理平台--登录
						</div>
						<div class="widget-content">
							<div class="padd">
								<form class="form-horizontal" action="/login_action" method="post">
									<input name="enter" type="hidden" value="false"></input>
									<div class="form-group">
										<label class="control-label col-lg-3" for="name">用户名:</label>
										<div class="col-lg-9">
											<input name="name" type="text" class="form-control" id="name" placeholder="用户名"></input>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-lg-3" for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
										<div class="col-lg-9">
											<input name="password" type="password" class="form-control" id="password" placeholder="密码"></input>
										</div>
									</div>
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-9 col-lg-offset-3">
												<span style="color: #d43f3a;">${message}</span>
											</div>
										</div>
									</c:if>
									<div class="col-lg-9 col-lg-offset-2">
										<button type="submit" class="btn btn-danger">登录</button>
									</div>
									<br />
								</form>
							</div>
						</div>
						<div class="widget-foot"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${cdn}js/jquery.js"></script>
	<script src="${cdn}js/bootstrap.js"></script>
</body>
</html>