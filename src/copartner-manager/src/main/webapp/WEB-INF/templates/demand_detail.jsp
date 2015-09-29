<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>融智详情</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">融智详情</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>融智详情
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
								<div class="pull-left">融智详情</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">融智不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
<div class="form-group">
	<label class="col-lg-2 control-label" >融智名称:</label>
	<div class="col-lg-10">${demand.name}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label" >融智发布者:</label>
	<div class="col-lg-10"><a href="/user/detail/${demand.userId}">${demand.userName}</a></div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">联系人:</label>
	<div class="col-lg-10">${demand.contactPerson} - ${demand.contact}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">融智要求:</label>
	<div class="col-lg-10">${demand.content}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">优势:</label>
	<div class="col-lg-10">${demand.advantage}</div>
</div>

<div class="form-group">
	<label class="col-lg-2 control-label">团队规模:</label>
	<div class="col-lg-10">${demand.teamSizeName}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">行业:</label>
	<div class="col-lg-10">${demand.industryDomainName}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">地区:</label>
	<div class="col-lg-10">${demand.fullLocation}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">收藏次数:</label>
	<div class="col-lg-10">${demand.likeCount}</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">评论次数:</label>
	<div class="col-lg-10">${demand.commentCount}</div>
</div>
<hr />
<div class="form-group">
	<div class="col-lg-offset-1 col-lg-12">
		<a class="btn btn-default btn-sm" href="/demand/list">列表</a>
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
