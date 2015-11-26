<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>投资机构编辑</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">投资机构管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/investorg/add">新建投资机构</a>
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
								<div class="pull-left">编辑投资机构</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<form class="form-horizontal" id="edit_investorg_form" role="form" action="/investorg/update/${investOrg.id}" method="post" enctype="multipart/form-data">
										
										<div class="tabbable" style="margin-bottom: 18px;">
					                      <ul class="nav nav-tabs">
					                        <li class="active"><a href="#tab1" data-toggle="tab">基本信息</a></li>
					                        <li><a href="#tab2" data-toggle="tab">简介</a></li>
					                      </ul>
					                      <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
					                        <div class="tab-pane active" id="tab1">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="name">标题<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="name" id="title" value="${investOrg.name}" type="text" class="form-control" placeholder="名称"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="logo">LOGO:</label>
													<div class="col-lg-10">
														<div class="gallery">
									                      	<a href="<t:cdn domain="${cdnDomain}" path="${investOrg.logo}"></t:cdn>" class="prettyPhoto[pp_gal]">
									                      		<img src="<t:cdn domain="${cdnDomain}" path="${investOrg.logo}"></t:cdn>" alt="${investOrg.name}" width="80">
									                      	</a>
									                      </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="logo">LOGO:</label>
													<div class="col-lg-10">
														<input name="logo" id="logo" type="file" class="form-control" placeholder="LOGO"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="specials">特色:</label>
													<div class="col-lg-10">
														<textarea name="specials" id="specials" class="form-control" rows="3" placeholder="特色">${investOrg.specials}</textarea>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="hardware">硬件:</label>
													<div class="col-lg-10">
														<textarea name="hardware" id="hardware" class="form-control" rows="3" placeholder="硬件">${investOrg.hardware}</textarea>
													</div>
												</div>
					                        </div>
					                        <div class="tab-pane" id="tab2">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="article">内容:</label>
													<div class="col-lg-10">
														<textarea name="article" id="article" class="form-control" rows="3" placeholder="内容">${investOrg.content}</textarea>
													</div>
												</div>
					                        </div>
					                      </div>
					                    </div>
										
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
            width: "100%",
            minWidth: 300,
            autoHeightMode : true,
            afterCreate : function() {
                this.loadPlugin('autoheight');
            },
            afterBlur: function(){this.sync();}
        });
    });
	$(document).ready(function() {
		$('#name').focus();
        $('#edit_investorg_form').validate({
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
                },
                specials: {
                	required: true,
                    minlength: 2,
                    maxlength: 256
                },
                hardware: {
                	required: true,
                    minlength: 2,
                    maxlength: 256
                }
            },
            messages: {
            	name: {
                    required: '名称不能为空',
                    minlength: "名称长度不能小于2个字符",
                    maxlength: "名称长度不能大于64个字符"
                },
                specials: {
                    required: '特色信息不能为空',
                    minlength: "特色信息长度不能小于2个字符",
                    maxlength: "特色信息长度不能大于256个字符"
                },
                hardware: {
                    required: '硬件信息不能为空',
                    minlength: "硬件信息长度不能小于2个字符",
                    maxlength: "硬件信息长度不能大于256个字符"
                }
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
        
	});
	</script>

</body>
