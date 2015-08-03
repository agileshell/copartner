<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<meta charset="UTF-8" />
	<meta name="keywords" content="dap" />
	<meta name="description" content="dap" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>创客汇管理平台--融资详情</title>

	<link rel="stylesheet" href="${cdn}css/bootstrap.css"></link>
	<link rel="stylesheet" href="${cdn}css/font-awesome.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery-ui.css"></link>
	<link rel="stylesheet" href="${cdn}css/fullcalendar.css"></link>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
	<link rel="stylesheet" href="${cdn}css/rateit.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-datetimepicker.min.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery.cleditor.css"></link>
	<link rel="stylesheet" href="${cdn}css/uniform.default.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-switch.css"></link>
	<link rel="stylesheet" href="${cdn}css/style.css"></link>
	<link rel="stylesheet" href="${cdn}css/widgets.css"></link>
	
	<!--[if lt IE 9]>
	<script src="${cdn}js/html5shim.js"></script>
	<![endif]-->

	<link rel="Shortcut Icon" href="${cdn}image/icon.png" />

</head>
<body>
	<jsp:include page="control/header.jsp" />
	<div class="content">
		<jsp:include page="control/sidebar.jsp"></jsp:include>
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
	<label class="col-lg-5 control-label" >融资名称:</label>
	<div class="col-lg-7">${finance.name}</div>
</div>
<div class="form-group">
	<label class="col-lg-5 control-label" >融资发布者:</label>
	<div class="col-lg-7"><a href="/user/detail/${finance.userId}">${finance.userName}</a></div>
</div>
<div class="form-group">
	<label class="col-lg-5 control-label">联系人:</label>
	<div class="col-lg-7">${finance.contactPerson} - ${finance.contact}</div>
</div>
<div class="form-group">
	<label class="col-lg-5 control-label">融资要求:</label>
	<div class="col-lg-7">${finance.content}</div>
</div>
<div class="form-group">
	<label class="col-lg-5 control-label">优势:</label>
	<div class="col-lg-7">${finance.advantage}</div>
</div>

<div class="form-group">
	<label class="col-lg-5 control-label">团队规模:</label>
	<div class="col-lg-7">${finance.teamSizeName}</div>
</div>
<div class="form-group">
	<label class="col-lg-5 control-label">行业:</label>
	<div class="col-lg-7">${finance.industryDomainName}</div>
</div>
<div class="form-group">
	<label class="col-lg-5 control-label">地区:</label>
	<div class="col-lg-7">${finance.fullLocation}</div>
</div>
<hr />
<div class="form-group">
	<div class="col-lg-offset-1 col-lg-9">
		<a class="btn btn-default btn-sm" href="/finance/list">列表</a>
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
		<div class="clearfix"></div>
	</div>

	<!--
	<jsp:include page="control/copy-rights.jsp" />
	-->

	<script src="${cdn}js/jquery.js"></script>
	<script src="${cdn}js/bootstrap.js"></script>
	<script src="${cdn}js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="${cdn}js/fullcalendar.min.js"></script>
	<script src="${cdn}js/jquery.rateit.min.js"></script>
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>
	<script src="${cdn}js/excanvas.min.js"></script>
	<script src="${cdn}js/jquery.flot.js"></script>
	<script src="${cdn}js/jquery.flot.resize.js"></script>
	<script src="${cdn}js/jquery.flot.pie.js"></script>
	<script src="${cdn}js/jquery.flot.stack.js"></script>
	<script src="${cdn}js/jquery.noty.js"></script>
	<script src="${cdn}js/themes/default.js"></script>
	<script src="${cdn}js/layouts/bottom.js"></script>
	<script src="${cdn}js/layouts/topRight.js"></script>
	<script src="${cdn}js/layouts/top.js"></script>
	<script src="${cdn}js/sparklines.js"></script>
	<script src="${cdn}js/jquery.cleditor.min.js"></script>
	<script src="${cdn}js/bootstrap-datetimepicker.min.js"></script>
	<script src="${cdn}js/jquery.uniform.min.js"></script>
	<script src="${cdn}js/bootstrap-switch.min.js"></script>
	<script src="${cdn}js/filter.js"></script>
	<script src="${cdn}js/custom.js"></script>
	<script src="${cdn}js/charts.js"></script>

</body>
</html>