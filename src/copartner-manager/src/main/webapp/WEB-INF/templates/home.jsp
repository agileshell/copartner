<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>首页</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">首页</h2>
			<div class="bread-crumb pull-right">
				<i class="icon-home"></i>首页
			</div>
			<div class="clearfix"></div>
		</div>
		
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget">
							<div class="widget-head">
								<div class="pull-left">数据概况</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd statement">
									<div class="row">
										<div class="col-md-4">
											<div class="well">
												<h2>${user_count}</h2>
												<p><a href="/user/list">用户总数</a></p>
											</div>
										</div>
										<div class="col-md-4">
											<div class="well">
												<h2>${project_count}</h2>
												<p><a href="/project/list">项目总数</a></p>
											</div>
										</div>
										<div class="col-md-4">
											<div class="well">
												<h2>${content_count}</h2>
												<p><a href="/content/list">文章总数</a></p>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-12">
											<hr />
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>ID</th>
														<th>姓名</th>
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
														<tr><td colspan="11" style="text-align: center;">空空如也!!!</td></tr>
													</c:if>
													<c:if test="${success}">
														<c:forEach var="c" items="${userList}" varStatus="status">
															<tr>
																<td>${c.id}</td>
																<td>${c.name}</td>
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
										</div>
									</div>
								</div>
								<!-- <jsp:include page="control/pagination.jsp"/> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
</body>
