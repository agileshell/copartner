<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>项目汇总列表</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">项目汇总管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/contestproject/download">下载</a>
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
									<form class="form-horizontal" role="form" action="/contestproject/list" method="get">
										<div class="form-group">
											<label class="col-lg-1 control-label" for="userId">参赛者ID:</label>
											<div class="col-lg-3">
												<input name="userId" id="userId" value="${req.userId}" type="text" class="form-control" placeholder="参赛者ID">
											</div>
											<label class="col-lg-1 control-label" for="contestId">大赛ID:</label>
											<div class="col-lg-3">
												<input name="contestId" id="contestId" value="${req.contestId}" type="text" class="form-control" placeholder="大赛ID">
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
								<div class="pull-left">项目汇总列表</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>所在市县或园区</th>
											<th>创业实体名称</th>
											<th>所属行业</th>
											<th>企业法律形态</th>
											<th>吸纳就业人数</th>
											<th>注册时间</th>
											<th>法定代表人</th>
											<th>人员类别</th>
											<th>联系方式</th>
											<th>身份证</th>
											<th>开户行</th>
											<th>开户名</th>
											<th>账号</th>
											<th>申请扶持金额</th>
											<th>详情</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!success}">
											<tr><td colspan="14" style="text-align: center;">空空如也!!!</td></tr>
										</c:if>
										<c:if test="${success}">
											<c:forEach var="c" items="${contestEntryList}" varStatus="status">
												<tr>
													<td>${c.location}</td>
													<td>${c.instance}</td>
													<td>${c.industry}</td>
													<td><t:short content="${c.legalFormation}" length="10"/></td>
													<td>${c.employqty}</td>
													<td>${c.regtime}</td>
													<td>${c.legalPerson}</td>
													<td>${c.userCategory}</td>
													<td>${c.contact}</td>
													<td>${c.idNumber}</td>
													<td>${c.bankName}</td>
													<td>${c.bankUserName}</td>
													<td>${c.bankAccount}</td>
													<td>${c.supportMoney}</td>
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
