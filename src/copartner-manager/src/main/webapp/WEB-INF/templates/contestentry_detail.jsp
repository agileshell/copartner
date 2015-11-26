<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>参赛项目详情</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">参赛项目管理</h2>
			<div class="bread-crumb pull-right">
				<!-- <a class="btn btn-default btn-sm" href="/contestentry/add">新建参赛项目</a> -->
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
								<div class="pull-left">参赛项目详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<div class="form-group">
										<label class="col-lg-2 control-label" for="name">参赛项目名称:</label>
										<div class="col-lg-10"><a href="/project/detail/${contestEntry.projectId}" target="_blank">${contestEntry.projectName}</a></div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="contestId">大赛名称:</label>
										<div class="col-lg-10"><a href="/contest/detail/${contestEntry.contestId}" target="_blank">${contestEntry.contestName}</a></div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="userName">参赛者姓名:</label>
										<div class="col-lg-10"><a href="/user/detail/${contestEntry.userId}" target="_blank">${contestEntry.userName}</a></div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">投票次数:</label>
										<div class="col-lg-10">${contestEntry.votes}次</div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">是否工商注册:</label>
										<div class="col-lg-10">
											<c:if test="${contestEntry.hasBusinessRegistered}"> 是 </c:if>
											<c:if test="${!contestEntry.hasBusinessRegistered}"> 否 </c:if>
										</div>
									</div>
		                        	<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">营业执照号:</label>
										<div class="col-lg-10">${contestEntry.businessLicense}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label">营业执照:</label>
										<div class="col-lg-10">
										<div class="gallery">
					                      	<a href="<t:cdn domain="${cdnDomain}" path="${contestEntry.businessLicenseImg}"></t:cdn>" class="prettyPhoto[pp_gal]">
					                      		<img src="<t:cdn domain="${cdnDomain}" path="${contestEntry.businessLicenseImg}"></t:cdn>" alt="${contestEntry.projectName}" width="500">
					                      	</a>
					                      </div>
										</div>
									</div>
									
									
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">所在市县或园区:</label>
										<div class="col-lg-10">${contestEntry.location}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">创业实体名称:</label>
										<div class="col-lg-10">${contestEntry.instance}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">所属行业:</label>
										<div class="col-lg-10">${contestEntry.industry}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">企业法律形态:</label>
										<div class="col-lg-10">${contestEntry.legalFormation}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">吸纳就业人数:</label>
										<div class="col-lg-10">${contestEntry.employqty}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">注册时间:</label>
										<div class="col-lg-10">${contestEntry.regtime}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">法定代表人:</label>
										<div class="col-lg-10">${contestEntry.legalPerson}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">人员类别:</label>
										<div class="col-lg-10">${contestEntry.userCategory}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">联系方式:</label>
										<div class="col-lg-10">${contestEntry.contact}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">身份证:</label>
										<div class="col-lg-10">${contestEntry.idNumber}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">开户行:</label>
										<div class="col-lg-10">${contestEntry.bankName}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">开户名:</label>
										<div class="col-lg-10">${contestEntry.bankUserName}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">账号:</label>
										<div class="col-lg-10">${contestEntry.bankAccount}</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label" for="contact">申请扶持金额:</label>
										<div class="col-lg-10">${contestEntry.supportMoney}</div>
									</div>
									
									<hr/>
									<div class="form-group">
										<div class="col-lg-offset-1 col-lg-12">
											<a class="btn btn-default btn-sm" href="/contestentry/list">列表</a>
										</div>
									</div>
									
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
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>
</body>
