<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>评论列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">评论管理</h2>
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
									<form class="form-horizontal" role="form" action="/comments/list" method="get">
										<div class="form-group">
											<label class="col-lg-2 control-label" for="domainId">ID:</label>
											<div class="col-lg-4">
												<input name="domainId" id="domainId" value="${req.domainId}" type="text" class="form-control" placeholder="评论ID">
											</div>
											<label class="col-lg-2 control-label" for="type">类型:</label>
											<div class="col-lg-4">
												<select id="type" class="form-control" name="type">
													<option value="1" <c:if test="${req.type == '1'}"> selected="selected" </c:if>>融资融智评论</option>
													<option value="2" <c:if test="${req.type == '2'}"> selected="selected" </c:if>>项目评论</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="status">状态:</label>
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
								<div class="pull-left">评论列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>内容</th>
											<th>状态</th>
											<th>评论时间</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="4" style="text-align: center;">空空如也!!!</td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${commentsList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td>${c.content}</td>
													<td>
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="${c.status}" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="true" name="update"/>
															<jsp:param value="${c.id}" name="id"/>
															<jsp:param value="/comments/update_status/${req.type}/${c.id}" name="url"/>
														</jsp:include>
													</td>
													<td>${c.gmtcreated}</td>
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
