<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>问题详情</title>
	<style type="text/css">
		img.user_icon {
			width: 40px;
			border: 0;
		}
	</style>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">问题管理</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>问题详情
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
			<div class="container">
				<!--row start-->
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">问题详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">问题不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
										<div class="form-group">
											<label class="col-lg-2 control-label" >标题:</label>
											<div class="col-lg-10">${question.title}</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" >内容:</label>
											<div class="col-lg-10">${question.content}</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">问题类别:</label>
											<div class="col-lg-10">${question.categoryName}</div>
										</div>
										
										<div class="form-group">
											<label class="col-lg-2 control-label">提问者:</label>
											<div class="col-lg-10">
												<a href="/user/detail/${question.questioner.userId}">${question.questioner.name}</a>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">导师:</label>
											<div class="col-lg-10">
												<a href="/user/detail/${question.tutor.userId}">${question.tutor.name}</a>
											</div>
										</div>
										<hr />
										
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>回复人</th>
													<th>内容</th>
													<th>状态</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="c" items="${question.answers0}" varStatus="status">
													<tr>
														<td>
														<a href="/user/detail/${c.answeror.userId}">
														<img class="user_icon" alt="${c.answeror.name}" src="${c.answeror.avatar}"></img>
														${c.answeror.name}
														</a>
														</td>
														<td>${c.content}</td>
														<td>
															<jsp:include page="control/commons-status.jsp">
																<jsp:param value="${c.status}" name="status"/>
																<jsp:param value="false" name="has_all"/>
																<jsp:param value="true" name="update"/>
																<jsp:param value="${c.id}" name="id"/>
																<jsp:param value="/question/update_answer_status/${c.id}" name="url"/>
															</jsp:include>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										
									</c:if>
								</div>
							</div>
							<div class="widget-foot"></div>
						</div>
					</div>
				</div>
				<!--row end-->
			</div>
		</div>
	</div>

</body>
