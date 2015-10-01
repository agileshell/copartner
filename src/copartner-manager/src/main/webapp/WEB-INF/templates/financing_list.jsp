<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>融资列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">融资列表</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>融资列表
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
									<form class="form-horizontal" role="form" action="/finance/list" method="get">
										<div class="form-group">
											<label class="col-lg-2 control-label" for="id">ID:</label>
											<div class="col-lg-4">
												<input name="id" id="id" value="${req.id}" type="text" class="form-control" placeholder="用户ID">
											</div>
											<label class="col-lg-2 control-label" for="title">融资名称:</label>
											<div class="col-lg-4">
												<input name="name" id="name" value="${req.name}" type="text" class="form-control" placeholder="融资名称">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="status">融资状态:</label>
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
								<div class="pull-left">融资列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>创建者</th>
											<th>融资名称</th>
											<th>优势</th>
											<th>融资要求</th>
											<th>状态</th>
											<th>联系人</th>
											<th>联系方式</th>
											<th>发起时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="10" style="text-align: center;">空空如也!!!</td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${financingList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td><a href="/user/detail/${c.userId}">${c.userId}</a></td>
													<td><t:short content="${c.projectName}" length="10"></t:short></td>
													<td><t:short content="${c.advantage}" length="10"></t:short></td>
													<td><t:short content="${c.content}" length="10"></t:short></td>
													<td>
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="${c.status}" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="true" name="update"/>
															<jsp:param value="${c.id}" name="id"/>
															<jsp:param value="/finance/update_status/${c.id}" name="url"/>
														</jsp:include>
													</td>
													<td>${c.contactPerson}</td>
													<td>${c.contact}</td>
													<td>${c.gmtcreated}</td>
													<td>
														<div class="btn-group">
															<a class="btn btn-xs btn-default" href="/finance/detail/${c.id}">
																详情
															</a>
															<a class="btn btn-xs btn-default" href="/finance/edit/${c.id}">
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
