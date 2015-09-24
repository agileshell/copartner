<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>意见反馈</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">意见反馈</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>意见反馈
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
									<form class="form-horizontal" role="form" action="/feedback/list" method="get">
										<div class="form-group">
											<label class="col-lg-1 control-label" for="userId">用户ID:</label>
											<div class="col-lg-4">
												<input name="userId" id="userId" value="${req.userId}" type="text" class="form-control" placeholder="用户ID">
											</div>
											<label class="col-lg-1 control-label" for="text">反馈内容:</label>
											<div class="col-lg-4">
												<input name="text" id="text" value="${req.text}" type="text" class="form-control" placeholder="反馈内容">
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
								<div class="pull-left">意见反馈</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>反馈人</th>
											<th>反馈内容</th>
											<th>反馈时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="5" style="text-align: center;">空空如也!!!</td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${feedbackList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td>
														<a class="btn btn-xs btn-default" href="/user/detail/${c.userId}">
															${c.userId}
														</a>
													</td>
													<td>${c.text}</td>
													<td>${c.gmtcreated}</td>
													<td>
														<div class="btn-group">
															回复意见反馈
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
	
</body>
