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
	<title>创客管理平台--文章列表</title>
	
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
				<h2 class="pull-left">文章列表</h2>
				<div class="bread-crumb pull-right">
					<a href="/content/list"><i class="icon-home"></i>首页</a><span class="divider">/</span>文章列表
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
										<form class="form-horizontal" role="form" action="/content/list" method="get">
											<div class="form-group">
												<label class="col-lg-2 control-label" for="id">ID:</label>
												<div class="col-lg-4">
													<input name="id" id="id" value="${req.id}" type="text" class="form-control" placeholder="文章ID">
												</div>
												<label class="col-lg-2 control-label" for="title">标题:</label>
												<div class="col-lg-4">
													<input name="title" id="title" value="${req.title}" type="text" class="form-control" placeholder="标题">
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label" for="type">状态:</label>
												<div class="col-lg-4">
													<jsp:include page="control/commons-status.jsp">
														<jsp:param value="${req.status}" name="status"/>
														<jsp:param value="true" name="has_all"/>
													</jsp:include>
												</div>
												<label class="col-lg-2 control-label" for="type">类型:</label>
												<div class="col-lg-4">
													<jsp:include page="control/content-type.jsp">
														<jsp:param value="${req.type}" name="type"/>
														<jsp:param value="true" name="has_all"/>
													</jsp:include>
												</div>
												<div class="col-lg-2">
													<button type="submit" class="btn btn-default">提交</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
							
							<div class="widget">
								<div class="widget-head">
									<div class="pull-left">文章列表</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>ID</th>
												<th>类型</th>
												<th>标题</th>
												<th>摘要</th>
												<th>状态</th>
												<th>浏览次数</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${!success}">
												<tr><td colspan="7" style="text-align: center;">空空如也!!!</td></tr>
												<tr><td colspan="7" style="text-align: center;"><a class="btn btn-default btn-sm" href="add">新建文章</a></td></tr>
											</c:if>
											<c:if test="${success}">
												<c:forEach var="c" items="${contentList}" varStatus="status">
													<tr>
														<td>${c.id}</td>
														<td>
															<c:if test="${c.type == 1}"> 政策解读 </c:if>
															<c:if test="${c.type == 2}"> 公共资源 </c:if>
														</td>
														<td>${c.title}</td>
														<td>${c.synopsis}</td>
														<td>
															<c:if test="${c.status == 'active'}"> 激活 </c:if>
															<c:if test="${c.status == 'inactive'}"> 无效 </c:if>
															<c:if test="${c.status == 'deleted'}"> 已删除 </c:if>
														</td>
														<td>${c.clicks}次</td>
														<td>
															<div class="btn-group">
																<a class="btn btn-xs btn-default" href="detail/${c.id}">
																	详情
																</a>
																<a class="btn btn-xs btn-default" href="edit/${c.id}">
																	编辑
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
	
	<script src="${cdn}js/jquery.js"></script>
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