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
	<meta name="author" content="fei.liu" />
	<title>创客汇管理平台--新建文章</title>

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
	
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
	
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
				<h2 class="pull-left">文章管理</h2>
				<div class="bread-crumb pull-right">
					<a class="btn btn-default btn-sm" href="add">新建文章</a>
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
									<div class="pull-left">新建文章</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<div class="padd">
										<form class="form-horizontal" role="form" action="/content/save" method="post" enctype="multipart/form-data">
											<div class="form-group">
												<label class="col-lg-5 control-label" for="title">标题:</label>
												<div class="col-lg-7">
													<input name="title" id="title" type="text" class="form-control" placeholder="标题"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="type">类型:</label>
												<div class="col-lg-7">
													<jsp:include page="control/content-type.jsp">
														<jsp:param value="1" name="type"/>
														<jsp:param value="false" name="has_all"/>
													</jsp:include>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="status">状态:</label>
												<div class="col-lg-7">
													<jsp:include page="control/commons-status.jsp">
														<jsp:param value="active" name="status"/>
														<jsp:param value="false" name="has_all"/>
														<jsp:param value="false" name="update"/>
													</jsp:include>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="synopsis">摘要:</label>
												<div class="col-lg-7">
													<input name="synopsis" id="synopsis" type="text" class="form-control" placeholder="摘要"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="coverImg">封皮:</label>
												<div class="col-lg-7">
													<input name="coverImg" id="coverImg" type="file" class="form-control" placeholder="封皮"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="article">内容:</label>
												<div class="col-lg-7">
													<textarea name="article" id="article" class="form-control" rows="3" placeholder="内容"></textarea>
												</div>
											</div>
											
											<hr />
											
											<div class="form-group">
												<div class="col-lg-offset-1 col-lg-9">
													<button type="submit" class="btn btn-default">提交</button>
												</div>
											</div>
										</form>
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
	
	<script charset="utf-8" src="${cdn}js/kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="${cdn}js/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${cdn}js/kindeditor/plugins/autoheight/autoheight.js"></script>
	
	<script>
	    KindEditor.ready(function(K) {
	        window.editor = K.create('#article', {
	            langType : 'zh_CN',
	            uploadJson : '/editor/file_upload',
	            items : [
	                     'source', '|', 'undo', 'redo', '|', 'preview', 'template', 'code', 'cut', 'copy', 'paste',
	                     'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
	                     'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	                     'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
	                     'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
	                     'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
	                     'flash', 'media', 'insertfile', 'table', 'hr', 'baidumap', 'pagebreak',
	                     'anchor', 'link', 'unlink'
	            ],
	            minHeight : 300,
	            autoHeightMode : true,
	            afterCreate : function() {
	                this.loadPlugin('autoheight');
	            }
	        });
	    });
	</script>
	
	<!--
	<script src="${cdn}js/ckeditor/ckeditor.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $(function() {
	        CKEDITOR.replace("article");
	    });
	</script>
	-->
	
</body>
</html>