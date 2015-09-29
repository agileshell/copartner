<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>投资机构列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">投资机构管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/investorg/add">新建投资机构</a>
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
									<form class="form-horizontal" role="form" action="/investorg/list" method="get">
										<div class="form-group">
											<label class="col-lg-2 control-label" for="name">名称:</label>
											<div class="col-lg-4">
												<input name="name" id="name" value="${req.name}" type="text" class="form-control" placeholder="投资机构名称">
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
								<div class="pull-left">投资机构列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>名称</th>
											<th>特色</th>
											<th>硬件</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="6" style="text-align: center;">空空如也!!!</td></tr>
											<tr><td colspan="6" style="text-align: center;"><a class="btn btn-default btn-sm" href="/investorg/add">新建投资机构</a></td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${investOrgList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td><t:short content="${c.name}" length="10"></t:short></td>
													<td><t:short content="${c.specials}" length="20"></t:short></td>
													<td><t:short content="${c.hardware}" length="20"></t:short></td>
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
