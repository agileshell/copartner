<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>课程列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">课程管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/course/add">新建课程</a>
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
									<form class="form-horizontal" role="form" action="/course/list" method="get">
										<div class="form-group">
											<label class="col-lg-1 control-label" for="keyword">关键字:</label>
											<div class="col-lg-4">
												<input name="keyword" id="keyword" value="${req.keyword}" type="text" class="form-control" placeholder="关键字">
											</div>
											<label class="col-lg-1 control-label" for="status">状态:</label>
											<div class="col-lg-3">
												<jsp:include page="control/commons-status.jsp">
													<jsp:param value="${req.status}" name="status"/>
													<jsp:param value="true" name="has_all"/>
													<jsp:param value="false" name="update"/>
												</jsp:include>
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
								<div class="pull-left">课程列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>课程名称</th>
											<th>主讲人</th>
											<th>摘要</th>
											<th>时长</th>
											<th>状态 </th>
											<th>是否免费</th>
											<th>创建时间</th>
											<th>浏览次数</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="10" style="text-align: center;">空空如也!!!</td></tr>
											<tr><td colspan=107" style="text-align: center;"><a class="btn btn-default btn-sm" href="/course/add">新建课程</a></td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${coursetList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td><t:short content="${c.name}" length="10"></t:short></td>
													<td>${c.speaker}</td>
													<td><t:short content="${c.synopsis}" length="10"></t:short></td>
													<td>${c.time}分钟</td>
													<td>
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="${c.status}" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="true" name="update"/>
															<jsp:param value="${c.id}" name="id"/>
															<jsp:param value="/course/update_status/${c.id}" name="url"/>
														</jsp:include>
													</td>
													<td>
														<c:if test="${c.isFree}"> 免费  </c:if>
														<c:if test="${!c.isFree}"> 非免费  </c:if>
													</td>
													<td>${c.gmtcreated}</td>
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
	
</body>
