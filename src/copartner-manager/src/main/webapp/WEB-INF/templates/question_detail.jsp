<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
	<title>创客汇管理平台--问题详情</title>
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
	<style type="text/css">
		img.user_icon {
			width: 40px;
			border: 0;
		}
	</style>
</head>
<body>
	<jsp:include page="control/header.jsp" />
	<div class="content">
		<jsp:include page="control/sidebar.jsp"></jsp:include>
		<div class="mainbar">
			<div class="page-head">
				<h2 class="pull-left">问题管理</h2>
				<div class="bread-crumb pull-right">
					<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>问题详情
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
									<div class="pull-left">问题详情</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<div class="padd form-horizontal">
										<c:if test="${!success}">
											<div class="form-group">
												<div class="col-lg-12" style="text-align: center;">问题不存在!!!</div>
											</div>
										</c:if>
										<c:if test="${success}">
											<div class="form-group">
												<label class="col-lg-5 control-label" >标题:</label>
												<div class="col-lg-7">${question.title}</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" >内容:</label>
												<div class="col-lg-7">${question.content}</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label">问题类别:</label>
												<div class="col-lg-7">${question.categoryName}</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label">提问者:</label>
												<div class="col-lg-7">
													<a href="/user/detail/${question.questioner.userId}">${question.questioner.name}</a>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label">导师:</label>
												<div class="col-lg-7">
													<a href="/user/detail/${tutor.questioner.userId}">${tutor.questioner.name}</a>
												</div>
											</div>
											<hr />
											
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>回复人</th>
														<th>内容</th>
														<th>状态</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="c" items="${tutor.answers0}" varStatus="status">
														<tr>
															<td>
															<a href="/user/detail/${c.answeror.userId}">
															<img class="user_icon" alt="${c.answeror.name}" src="${cdnDomain}${c.answeror.avatar}"></img>
															${c.answeror.name}
															</a>
															</td>
															<td>${c.content}</td>
															<td>
																<jsp:include page="control/commons-status.jsp">
																	<jsp:param value="${c.status}" name="status"/>
																	<jsp:param value="false" name="has_all"/>
																	<jsp:param value="true" name="update"/>
																	<jsp:param value="${c.id}" name="id"/>
																	<jsp:param value="/question/update_answer_status/${c.id}" name="url"/>
																</jsp:include>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											
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
		<div class="clearfix"></div>
	</div>

	<!--
	<jsp:include page="control/copy-rights.jsp" />
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