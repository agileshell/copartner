<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
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
