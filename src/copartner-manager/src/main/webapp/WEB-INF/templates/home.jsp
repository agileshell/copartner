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
	<title>创客管理平台--首页</title>
	
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
				<h2 class="pull-left">首页</h2>
				<div class="bread-crumb pull-right">
					<i class="icon-home"></i>首页
				</div>
				<div class="clearfix"></div>
			</div>
			
			<div class="matter">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-head">
									<div class="pull-left">数据概况</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<div class="padd statement">
										<div class="row">
											<div class="col-md-4">
												<div class="well">
													<h2>${user_count}</h2>
													<p><a href="/user/list">用户总数</a></p>
												</div>
											</div>
											<div class="col-md-4">
												<div class="well">
													<h2>${project_count}</h2>
													<p><a href="/project/list">项目总数</a></p>
												</div>
											</div>
											<div class="col-md-4">
												<div class="well">
													<h2>${content_count}</h2>
													<p><a href="/content/list">文章总数</a></p>
												</div>
											</div>
										</div>
										
										<div class="row">
											<div class="col-md-12">
												<hr />
												<table class="table table-striped table-bordered table-hover">
													<thead>
														<tr>
															<th>ID</th>
															<th>姓名</th>
															<th>邮箱</th>
															<th>手机号</th>
															<th>地址</th>
															<th>性别</th>
															<th>年龄</th>
															<th>状态</th>
															<th>注册时间</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${!success}">
															<tr><td colspan="10" style="text-align: center;">空空如也!!!</td></tr>
														</c:if>
														<c:if test="${success}">
															<c:forEach var="c" items="${userList}" varStatus="status">
																<tr>
																	<td>${c.id}</td>
																	<td>${c.name}</td>
																	<td>${c.email}</td>
																	<td>${c.mobile}</td>
																	<td>${c.fullLocation}</td>
																	<td>
																		<c:if test="${c.gender == 'F'}"> <i class="icon-female"></i> </c:if>
																		<c:if test="${c.gender == 'M'}"> <i class="icon-male"></i> </c:if>
																	</td>
																	<td>${c.age}</td>
																	<td>
																		<jsp:include page="control/commons-status.jsp">
																			<jsp:param value="${c.status}" name="status"/>
																			<jsp:param value="false" name="has_all"/>
																			<jsp:param value="true" name="update"/>
																			<jsp:param value="${c.id}" name="id"/>
																			<jsp:param value="/user/update_status/${c.id}" name="url"/>
																		</jsp:include>
																	</td>
																	<td>${c.gmtcreated}</td>
																	<td>
																		<div class="btn-group">
																			<a class="btn btn-xs btn-default" href="/user/detail/${c.id}">
																				详情
																			</a>
																			<a class="btn btn-xs btn-default" href="/user/chat/${c.id}" target="_self">
																				即时聊天
																			</a>
																		</div>
																	</td>
																</tr>
															</c:forEach>
														</c:if>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<jsp:include page="control/pagination.jsp"/>
								</div>
							</div>
						</div>
					</div>
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