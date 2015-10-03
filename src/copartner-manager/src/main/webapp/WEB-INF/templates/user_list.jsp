<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>用户列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">用户列表</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>用户列表
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
									<form class="form-horizontal" role="form" action="/user/list" method="get">
										<div class="form-group">
											<label class="col-lg-2 control-label" for="id">ID:</label>
											<div class="col-lg-4">
												<input name="id" id="id" value="${req.id}" type="text" class="form-control" placeholder="用户ID">
											</div>
											<label class="col-lg-2 control-label" for="title">姓名:</label>
											<div class="col-lg-4">
												<input name="name" id="name" value="${req.name}" type="text" class="form-control" placeholder="用户姓名">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-1 control-label" for="type">角色:</label>
											<div class="col-lg-4">
												<select id="type" class="form-control" name="roleId">
													<option value="0" <c:if test="${req.roleId == 0}"> selected="selected" </c:if>>全部</option>
													<option value="1" <c:if test="${req.roleId == 1}"> selected="selected" </c:if>>创业者</option>
													<option value="2" <c:if test="${req.roleId == 2}"> selected="selected" </c:if>>投资人</option>
													<option value="3" <c:if test="${req.roleId == 3}"> selected="selected" </c:if>>导师</option>
												</select>
											</div>
											<label class="col-lg-1 control-label" for="type">手机号:</label>
											<div class="col-lg-4">
												<input name="mobile" id="mobile" value="${req.mobile}" type="text" class="form-control" placeholder="用户手机号">
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
								<div class="pull-left">用户列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>ID</th>
											<th>姓名</th>
											<th>认证</th>
											<th>角色</th>
											<th>邮箱</th>
											<th>手机号</th>
											<th>地址</th>
											<th>性别</th>
											<th>年龄</th>
											<th>状态</th>
											<th>注册时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="12" style="text-align: center;">空空如也!!!</td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${userList}" varStatus="status">
												<tr>
													<td>${c.id}</td>
													<td>${c.name}</td>
													<td>
														<c:if test="${c.authenticated}">
															<span style="color: #39bd94;">已认证</span>
														</c:if>
														<c:if test="${!c.authenticated}">
															<a href="/user/authentication/${c.id}"><span style="color: #ff6600;">未认证</span></a>
														</c:if>
													</td>
													<td>
														<%-- ${startupRoles.get(c.startupRoleId)} --%>
														<c:if test="${c.roleId == 1}"> 创业者 </c:if>
														<c:if test="${c.roleId == 2}"> 投资人 </c:if>
														<c:if test="${c.roleId == 3}"> 导师 </c:if>
													</td>
													<td>${c.email}</td>
													<td>${c.mobile}</td>
													<td>${c.fullLocation}</td>
													<td>
														<c:if test="${c.gender == 'F'}"> <i class="icon-female"></i> </c:if>
														<c:if test="${c.gender == 'M'}"> <i class="icon-male"></i> </c:if>
													</td>
													<td>${c.age}</td>
													<td>
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="${c.status}" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="true" name="update"/>
															<jsp:param value="${c.id}" name="id"/>
															<jsp:param value="/user/update_status/${c.id}" name="url"/>
														</jsp:include>
													</td>
													<td>${c.gmtcreated}</td>
													<td>
														<div class="btn-group">
															<a class="btn btn-xs btn-default" href="/user/detail/${c.id}">
																详情
															</a>
															<c:if test="${!c.authenticated}">
																<a class="btn btn-xs btn-default" href="/user/authentication/${c.id}" target="_self">
																	审核
																</a>
															</c:if>
															<!--
															<a class="btn btn-xs btn-default" href="/user/chat/${c.id}" target="_self">
																即时聊天
															</a>
															-->
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
