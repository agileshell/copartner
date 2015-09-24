<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>问题列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">问题管理</h2>
			<div class="bread-crumb pull-right">
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
									<form class="form-horizontal" role="form" action="/question/list" method="get">
										<div class="form-group">
											<label class="col-lg-2 control-label" for="domainId">关键字:</label>
											<div class="col-lg-4">
												<input name="keyword" id="keyword" value="${req.keyword}" type="text" class="form-control" placeholder="问题关键字">
											</div>
											<label class="col-lg-2 control-label" for="status">状态:</label>
											<div class="col-lg-4">
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
								<div class="pull-left">问题列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>标题</th>
											<th>内容</th>
											<th>权限</th>
											<th>状态</th>
											<th>问题时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="6" style="text-align: center;">空空如也!!!</td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${questionList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td>
													<t:short content="${c.title}" length="10"></t:short>
													</td>
													<td>
													<t:short content="${c.content}" length="10"></t:short>
													</td>
													<td>
														<c:if test="${c.permission == '1'}"> 全体可见 </c:if>
														<c:if test="${c.permission == '2'}"> 导师可见 </c:if>
													</td>
													<td>
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="${c.status}" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="true" name="update"/>
															<jsp:param value="${c.id}" name="id"/>
															<jsp:param value="/question/update_status/${c.id}" name="url"/>
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
