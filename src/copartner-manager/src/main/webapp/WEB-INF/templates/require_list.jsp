<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>需求列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">需求管理</h2>
			<div class="bread-crumb pull-right">
				<!-- <a class="btn btn-default btn-sm" href="/require/add">新建需求</a> -->
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
									<form class="form-horizontal" role="form" action="/require/list" method="get">
										<div class="form-group">
											<label class="col-lg-2 control-label" for="userId">用户ID:</label>
											<div class="col-lg-4">
												<input name="userId" id="userId" value="${req.userId}" type="text" class="form-control" placeholder="用户ID">
											</div>
											<label class="col-lg-2 control-label" for="type">类型:</label>
											<div class="col-lg-4">
												<select id="type" class="form-control" name="type">
													<option value="0">--全部--</option>
													<option value="1" <c:if test="${req.type == 1}"> selected="selected" </c:if>>加入团队</option>
													<option value="2" <c:if test="${req.type == 2}"> selected="selected" </c:if>>寻求搭档</option>
													<option value="3" <c:if test="${req.type == 3}"> selected="selected" </c:if>>寻求融资</option>
													<option value="4" <c:if test="${req.type == 4}"> selected="selected" </c:if>>寻求融智</option>
													<option value="5" <c:if test="${req.type == 5}"> selected="selected" </c:if>>投资项目</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-1 control-label" for="status">状态:</label>
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
								<div class="pull-left">需求列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>类型</th>
											<th>关联项目</th>
											<th>所属者</th>
											<th>状态</th>
											<th>创建时间</th>
											<th>收藏次数</th>
											<th>评论次数</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="9" style="text-align: center;">空空如也!!!</td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${requireList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td>
														<c:if test="${c.type == 1}"> 加入团队 </c:if>
														<c:if test="${c.type == 2}"> 寻求搭档 </c:if>
														<c:if test="${c.type == 3}"> 寻求融资 </c:if>
														<c:if test="${c.type == 4}"> 寻求融智 </c:if>
														<c:if test="${c.type == 5}"> 投资项目 </c:if>
													</td>
													<td><a href="/project/detail/${c.projectId}"><t:short content="${c.projectName}" length="10"/></a></td>
													<td><a href="/user/detail/${c.userId}"><t:short content="${c.user.name}" length="10"/></a></td>
													<td>
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="${c.status}" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="true" name="update"/>
															<jsp:param value="${c.id}" name="id"/>
															<jsp:param value="/require/update_status/${c.id}" name="url"/>
														</jsp:include>
													</td>
													<td>${c.gmtcreated}</td>
													<td>${c.likeCount}次</td>
													<td>${c.commentCount}次</td>
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
