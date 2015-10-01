<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>参赛项目列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">参赛项目管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/contestentry/add">新建参赛项目</a>
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
									<form class="form-horizontal" role="form" action="/contestentry/list" method="get">
										<div class="form-group">
											<label class="col-lg-1 control-label" for="name">参赛项目:</label>
											<div class="col-lg-3">
												<input name="name" id="name" value="${req.name}" type="text" class="form-control" placeholder="参赛项目">
											</div>
											<label class="col-lg-1 control-label" for="contestId">大赛ID:</label>
											<div class="col-lg-3">
												<input name="contestId" id="contestId" value="${req.contestId}" type="text" class="form-control" placeholder="大赛ID">
											</div>
											<div class="col-lg-1">
												<button type="submit" class="btn btn-default">查询</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						
						<div class="widget">
							<div class="widget-head">
								<div class="pull-left">参赛项目列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>参赛项目名称</th>
											<th>参赛者姓名</th>
											<th>联系方式</th>
											<th>大赛ID</th>
											<th>赞的次数</th>
											<th>状态</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="9" style="text-align: center;">空空如也!!!</td></tr>
											<tr><td colspan="9" style="text-align: center;"><a class="btn btn-default btn-sm" href="/contestentry/add">新建参赛项目</a></td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${contestEntryList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td><t:short content="${c.name}" length="10"></t:short></td>
													<td><t:short content="${c.userName}" length="10"></t:short></td>
													<td>${c.contact}</td>
													<td><a href="/contest/detail/${c.contestId}" target="_blank">${c.contestId}</a></td>
													<td>${c.praise}次</td>
													<td>
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="${c.status}" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="true" name="update"/>
															<jsp:param value="${c.id}" name="id"/>
															<jsp:param value="/contestentry/update_status/${c.id}" name="url"/>
														</jsp:include>
													</td>
													<td>${c.gmtcreated}</td>
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
</body>
