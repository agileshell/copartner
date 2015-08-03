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
	<title>创客汇管理平台--项目列表</title>
	
	<link rel="stylesheet" href="${cdn}css/bootstrap.css"></link>
	<link rel="stylesheet" href="${cdn}css/font-awesome.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery-ui.css"></link>
	<link rel="stylesheet" href="${cdn}css/fullcalendar.css"></link>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
	<link rel="stylesheet" href="${cdn}css/rateit.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-datetimepicker.min.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery.cleditor.css"></link>
	<link rel="stylesheet" href="${cdn}css/uniform.default.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-switch.css"></link>
	<link rel="stylesheet" href="${cdn}css/style.css"></link>
	<link rel="stylesheet" href="${cdn}css/widgets.css"></link>
	
	<script src="${cdn}js/jquery.js"></script>
	
	<!--[if lt IE 9]>
	<script src="${cdn}js/html5shim.js"></script>
	<![endif]-->
	
	<link rel="Shortcut Icon" href="${cdn}image/icon.png" />

</head>
<body>
	<jsp:include page="control/header.jsp"/>
	<div class="content">
		<jsp:include page="control/sidebar.jsp"></jsp:include>
		<div class="mainbar">
			<div class="page-head">
				<h2 class="pull-left">项目列表</h2>
				<div class="bread-crumb pull-right">
					<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>项目列表
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="matter">
				<div class="container">
					<!-- row start -->
					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-content">
									<div class="padd">
										<form class="form-horizontal" role="form" action="/project/list" method="get">
											<div class="form-group">
												<label class="col-lg-2 control-label" for="id">ID:</label>
												<div class="col-lg-4">
													<input name="id" id="id" value="${req.id}" type="text" class="form-control" placeholder="用户ID">
												</div>
												<label class="col-lg-2 control-label" for="title">项目名称:</label>
												<div class="col-lg-4">
													<input name="name" id="name" value="${req.name}" type="text" class="form-control" placeholder="项目名称">
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label" for="type">项目状态:</label>
												<div class="col-lg-4">
													<jsp:include page="control/commons-status.jsp">
														<jsp:param value="${req.status}" name="status"/>
														<jsp:param value="true" name="has_all"/>
														<jsp:param value="false" name="update"/>
													</jsp:include>
												</div>
												<div class="col-lg-2">
													<button type="submit" class="btn btn-default">查询</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
							<div class="widget">
								<div class="widget-head">
									<div class="pull-left">项目列表</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>ID</th>
												<th>创建者</th>
												<th>项目名称</th>
												<th>优势</th>
												<th>实施条件</th>
												<th>收藏</th>
												<th>评论</th>
												<th>状态</th>
												<th>联系人</th>
												<th>联系方式</th>
												<th>发起时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${!success}">
												<tr><td colspan="12" style="text-align: center;">空空如也!!!</td></tr>
											</c:if>
											<c:if test="${success}">
												<c:forEach var="c" items="${projectList}" varStatus="status">
													<tr>
														<td>${c.id}</td>
														<td><a href="/user/detail/${c.userId}">${c.userId}</a></td>
														<td>${utils.getShort(${c.name}, 10)}</td>
														<td>${utils.getShort(${c.advantage}, 10)}</td>
														<td>${utils.getShort(${c.content}, 10)}</td>
														<td>${c.likeCount}</td>
														<td>${c.commentCount}</td>
														<td>
															<jsp:include page="control/commons-status.jsp">
																<jsp:param value="${c.status}" name="status"/>
																<jsp:param value="false" name="has_all"/>
																<jsp:param value="true" name="update"/>
																<jsp:param value="${c.id}" name="id"/>
																<jsp:param value="/project/update_status/${c.id}" name="url"/>
															</jsp:include>
														</td>
														<td>${c.contactPerson}</td>
														<td>${c.contact}</td>
														<td>${c.gmtcreated}</td>
														<td>
															<div class="btn-group">
																<a class="btn btn-xs btn-default" href="/project/detail/${c.id}">
																	详情
																</a>
															</div>
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
									<jsp:include page="control/pagination.jsp"/>
								</div>
							</div>
							
						</div>
					</div>
					<!-- row end -->
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	
	<!--
	<jsp:include page="control/copy-rights.jsp"/>
	-->
	
	<script src="${cdn}js/bootstrap.js"></script>
	<script src="${cdn}js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="${cdn}js/fullcalendar.min.js"></script>
	<script src="${cdn}js/jquery.rateit.min.js"></script>
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>
	<script src="${cdn}js/excanvas.min.js"></script>
	<script src="${cdn}js/jquery.flot.js"></script>
	<script src="${cdn}js/jquery.flot.resize.js"></script>
	<script src="${cdn}js/jquery.flot.pie.js"></script>
	<script src="${cdn}js/jquery.flot.stack.js"></script>
	<script src="${cdn}js/jquery.noty.js"></script>
	<script src="${cdn}js/themes/default.js"></script>
	<script src="${cdn}js/layouts/bottom.js"></script>
	<script src="${cdn}js/layouts/topRight.js"></script>
	<script src="${cdn}js/layouts/top.js"></script>
	<script src="${cdn}js/sparklines.js"></script>
	<script src="${cdn}js/jquery.cleditor.min.js"></script>
	<script src="${cdn}js/bootstrap-datetimepicker.min.js"></script>
	<script src="${cdn}js/jquery.uniform.min.js"></script>
	<script src="${cdn}js/bootstrap-switch.min.js"></script>
	<script src="${cdn}js/filter.js"></script>
	<script src="${cdn}js/custom.js"></script>
	<script src="${cdn}js/charts.js"></script>
	
</body>
</html>