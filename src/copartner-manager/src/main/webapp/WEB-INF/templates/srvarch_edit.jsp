<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>服务机构编辑</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">服务机构管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/srvarch/add">新建服务机构</a>
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
								<div class="pull-left">编辑服务机构</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<form id="edit_srvarch_form" class="form-horizontal" role="form" action="/srvarch/update/${srvarch.id}" method="post" enctype="multipart/form-data">
										<div class="form-group">
											<label class="col-lg-2 control-label" for="name">服务机构名称<span class="cofrequired">*</span>:</label>
											<div class="col-lg-10">
												<input name="name" id="name" value="${srvarch.name}" type="text" class="form-control" placeholder="服务机构名称"></input>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">图标:</label>
											<div class="col-lg-10">
											<div class="gallery">
						                      	<a href="<t:cdn domain="${cdnDomain}" path="${srvarch.icon}"></t:cdn>" class="prettyPhoto[pp_gal]">
						                      		<img src="<t:cdn domain="${cdnDomain}" path="${srvarch.icon}"></t:cdn>" alt="${srvarch.name}" width="100">
						                      	</a>
						                      </div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="icon">图标<span class="cofrequired">*</span>:</label>
											<div class="col-lg-10">
												<input name="icon" id="icon" type="file" class="form-control" placeholder="图标"></input>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="description">描述:</label>
											<div class="col-lg-10">
												<textarea name="description" id="description" class="form-control" rows="3" placeholder="描述">${srvarch.description}</textarea>
											</div>
										</div>
										<hr />
										<div class="form-group">
											<div class="col-lg-offset-1 col-lg-12">
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
	
	<script charset="utf-8" src="${cdn}js/kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="${cdn}js/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${cdn}js/kindeditor/plugins/autoheight/autoheight.js"></script>
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>
	
	<script>
		$(document).ready(function() {
			KindEditor.ready(function(K) {
		        window.editor = K.create('#description', {
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
		            width: "100%",
		            minWidth: 300,
		            autoHeightMode : true,
		            afterCreate : function() {
		                this.loadPlugin('autoheight');
		            },
		            afterBlur: function(){this.sync();}
		        });
		    });
			
			$('#name').focus();
	        $('#edit_srvarch_form').validate({
	        	onsubmit:true,
        	  	onfocusout:false,
        	  	onkeyup:false,
        	  	onkeyup:false,
        	  	onclick:false,
	            rules: {
	            	name: {
	                    required: true,
	                    minlength: 2,
	                    maxlength: 64
	                }
	            },
	            messages: {
	            	name: {
	                    required: '名称不能为空',
	                    minlength: "名称长度不能小于2个字符",
	                    maxlength: "名称长度不能大于64个字符"
	                }
	            },
	            submitHandler: function(form) {
	                form.submit();
	            }
	        });
		});
	</script>
</body>
