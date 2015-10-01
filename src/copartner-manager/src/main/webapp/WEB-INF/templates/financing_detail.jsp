<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>融资详情</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">融资详情</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>融资详情
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
								<div class="pull-left">融资详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">融资不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
<div class="form-group">
	<label class="col-lg-2 control-label" >融资名称:</label>
	<div class="col-lg-10">${finance.projectName}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label" >融资发布者:</label>
	<div class="col-lg-10"><a href="/user/detail/${finance.userId}">${finance.userName}</a></div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">联系人:</label>
	<div class="col-lg-10">${finance.contactPerson} - ${finance.contact}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">融资要求:</label>
	<div class="col-lg-10">${finance.content}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">优势:</label>
	<div class="col-lg-10">${finance.advantage}</div>
</div>

<div class="form-group">
	<label class="col-lg-2 control-label">团队规模:</label>
	<div class="col-lg-10">${finance.teamSizeName}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">行业:</label>
	<div class="col-lg-10">${finance.industryDomainName}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">融资阶段:</label>
	<div class="col-lg-10">${finance.financingPhaseName}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">地区:</label>
	<div class="col-lg-10">${finance.fullLocation}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">营业执照号:</label>
	<div class="col-lg-10">${finance.businessLicense}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">商业计划书:</label>
	<div class="col-lg-10">
		<a href="${cdnDomain}${finance.businessPlan}" target="_blank">查看</a>
	</div>
</div>
<hr />
<div class="form-group">
	<div class="col-lg-offset-1 col-lg-12">
		<a class="btn btn-default btn-sm" href="/finance/list">列表</a>
		<a class="btn btn-default btn-sm" href="/finance/edit/${finance.id}">编辑</a>
	</div>
</div>
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
