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
	<title>SSO管理平台--错误提示</title>
	
	<link rel="stylesheet" href="${cdn}css/bootstrap.css"></link>
	<link rel="stylesheet" href="${cdn}css/font-awesome.css"></link>
	<link rel="stylesheet" href="${cdn}css/style.css"></link>

	<!--[if lt IE 9]>
	<script src="${cdn}js/html5shim.js"></script>
	<![endif]-->

	<link rel="Shortcut Icon" href="${cdn}image/shoseicon64px.png" />

</head>
<body>
	<div class="error-page">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<i class="icon-question-sign"></i>出错啦
						</div>
						<div class="widget-content">
							<div class="padd error">
								<h1>出错啦!!! 错误码 ${status}</h1>
								<p>${message}</p>
								<br />
								<form class="form-inline" action="/article/list" method="get">
									<div class="form-group">
										<input name="title" type="text" class="form-control" placeholder="文章标题">
									</div>
									<button type="submit" class="btn btn-default">搜索</button>
								</form>
								<br />
								<div class="horizontal-links">
									<a href="/article/list">文章列表</a> | <a href="/style/list">主题列表</a>
								</div>
							</div>
							<div class="widget-foot">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${cdn}js/jquery.js"></script>
	<script src="${cdn}js/bootstrap.js"></script>
</body>
</html>