<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>行业领域列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">行业管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/industry/add">新建行业</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
			<div class="container">
				<!-- row start -->
				<div class="row">
					<div class="col-md-12">
						<div class="widget">
							<div class="widget-head">
								<div class="pull-left">行业列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>行业名称</th>
											<th>显示</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="5" style="text-align: center;">空空如也!!!</td></tr>
											<tr><td colspan="5" style="text-align: center;"><a class="btn btn-default btn-sm" href="/industry/add">新建行业</a></td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${industryList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td>${c.name}</td>
													<td>
														<c:if test="${c.isListed}"> 显示 </c:if>
														<c:if test="${!c.isListed}"> 隐藏 </c:if>
													</td>
													<td>${c.gmtcreated}</td>
													<td>
														<div class="btn-group">
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
								<!-- <jsp:include page="control/pagination.jsp"/> -->
							</div>
						</div>
					</div>
				</div>
				<!-- row end -->
			</div>
		</div>
	</div>
</body>
