<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
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
											<label class="col-lg-1 control-label" for="type">邮箱:</label>
											<div class="col-lg-4">
												<input name="email" id="email" value="${req.email}" type="text" class="form-control" placeholder="用户邮箱">
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
								<div class="row">
									<div class="col-md-12">
										<c:forEach var="c" items="${userList}" varStatus="status">
											<div class="col-md-3">
												<div class="widget">
													<div class="widget-head">
														<div class="pull-left">
															<c:if test="${c.roleId == 1}">创业者&nbsp;:&nbsp;</c:if>
															<c:if test="${c.roleId == 2}">投资人&nbsp;:&nbsp;</c:if>
															<c:if test="${c.roleId == 3}">导师&nbsp;:&nbsp;</c:if>
															<a href="/user/detail/${c.id}"><t:short content="${c.name}" length="6"></t:short></a>
														</div>
														<div class="widget-icons pull-right">
															<a href="/user/chat/${c.id}">
																<c:if test="${c.gender == 'F'}"> <i class="icon-female"></i> </c:if>
																<c:if test="${c.gender == 'M'}"> <i class="icon-male"></i> </c:if>
															</a>
														</div>
														<div class="clearfix"></div>
													</div>
													<div class="widget-content">
														<div class="padd">
															<ul class="task">
											                	<li><span class="uni">手机&nbsp;:&nbsp;</span>${c.mobile}
											                	</li>
											                	<li><span class="uni">地址&nbsp;:&nbsp;</span>${c.fullLocation}
											                	</li>
											                </ul>
														</div>
														<div class="widget-foot">
															<jsp:include page="control/commons-status.jsp">
																<jsp:param value="${c.status}" name="status"/>
																<jsp:param value="false" name="has_all"/>
																<jsp:param value="true" name="update"/>
																<jsp:param value="${c.id}" name="id"/>
																<jsp:param value="/user/update_status/${c.id}" name="url"/>
															</jsp:include>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
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
