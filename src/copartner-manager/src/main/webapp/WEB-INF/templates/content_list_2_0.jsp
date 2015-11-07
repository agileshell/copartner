<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>政策解读列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">政策解读管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/content/add">新建政策解读</a>
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
									<form class="form-horizontal" role="form" action="/content/list" method="get">
										<div class="form-group">
											<label class="col-lg-2 control-label" for="id">ID:</label>
											<div class="col-lg-4">
												<input name="id" id="id" value="${req.id}" type="text" class="form-control" placeholder="政策解读ID">
											</div>
											<label class="col-lg-2 control-label" for="title">标题:</label>
											<div class="col-lg-4">
												<input name="title" id="title" value="${req.title}" type="text" class="form-control" placeholder="标题">
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
								<div class="pull-left">政策解读列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="row">
									<div class="col-md-12">
										<c:forEach var="c" items="${contentList}" varStatus="status">
											<div class="col-md-3">
												<div class="widget">
													<div class="widget-head">
														<div class="pull-left">
															<a href="/content/detail/${c.id}"><t:short content="${c.title}" length="5"></t:short></a>
														</div>
														<div class="widget-icons pull-right">
															<a class="btn btn-xs btn-default" href="/content/edit/${c.id}">
																<i class="icon-edit"></i>
															</a>
														</div>
														<div class="clearfix"></div>
													</div>
													<div class="widget-content">
														<div class="padd">
															<ul class="task">
											                	<li>
											                      <div class="gallery">
											                      	<a href="<t:cdn domain="${cdnDomain}" path="${c.coverImg}"></t:cdn>" class="prettyPhoto[pp_gal]">
											                      		<img src="<t:cdn domain="${cdnDomain}" path="${c.coverImg}"></t:cdn>" alt="${c.title}">
											                      	</a>
											                      </div>
											                	</li>
											                	<li>
											                		<t:short content="${c.synopsis}" length="30"></t:short>
											                	</li>
											                </ul>
														</div>
														<div class="widget-foot">
															<jsp:include page="control/commons-status.jsp">
																<jsp:param value="${c.status}" name="status"/>
																<jsp:param value="false" name="has_all"/>
																<jsp:param value="true" name="update"/>
																<jsp:param value="${c.id}" name="id"/>
																<jsp:param value="/content/update_status/${c.id}" name="url"/>
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
