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
				<!-- <a class="btn btn-default btn-sm" href="/contestentry/add">新建参赛项目</a> -->
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
											<label class="col-lg-1 control-label" for="userId">参赛者ID:</label>
											<div class="col-lg-3">
												<input name="userId" id="userId" value="${req.userId}" type="text" class="form-control" placeholder="参赛者ID">
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
											<th>大赛名称</th>
											<th>营业执照号</th>
											<th>投票次数</th>
											<th>状态</th>
											<th>上传时间</th>
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
													<td><a href="/project/detail/${c.projectId}" target="_blank"><t:short content="${c.projectName}" length="10"/></a></td>
													<td><a href="/user/detail/${c.userId}" target="_blank"><t:short content="${c.userName}" length="10"/></a></td>
													<td><a href="/contest/detail/${c.contestId}" target="_blank"><t:short content="${c.contestName}" length="10"/></a></td>
													<td>${c.businessLicense}</td>
													<td>${c.votes}次</td>
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
