<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>新闻动态编辑</title>
	<link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">新闻动态管理</h2>
			<div class="bread-crumb pull-right">
				<a class="btn btn-default btn-sm" href="/news/add">新建新闻动态</a>
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
								<div class="pull-left">编辑新闻动态</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<form id="edit_news_form" class="form-horizontal" role="form" action="/news/update/${news.id}" method="post" enctype="multipart/form-data">
										
										<div class="tabbable" style="margin-bottom: 18px;">
					                      <ul class="nav nav-tabs">
					                        <li class="active"><a href="#tab1" data-toggle="tab">基本信息</a></li>
					                        <li><a href="#tab2" data-toggle="tab">图文信息</a></li>
					                      </ul>
					                      <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
					                        <div class="tab-pane active" id="tab1">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label" for="title">标题<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<input name="title" id="title" value="${news.title}" type="text" class="form-control" placeholder="标题"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="type">类型<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<jsp:include page="control/news-type.jsp">
															<jsp:param value="${news.type}" name="type"/>
															<jsp:param value="false" name="has_all"/>
														</jsp:include>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="status">状态<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<jsp:include page="control/commons-status.jsp">
															<jsp:param value="${news.status}" name="status"/>
															<jsp:param value="false" name="has_all"/>
															<jsp:param value="false" name="update"/>
														</jsp:include>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="synopsis">摘要<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<!-- <input name="synopsis" id="synopsis" value="${news.synopsis}" type="text" class="form-control" placeholder="摘要"></input> -->
														<textarea name="synopsis" id="synopsis" class="form-control" rows="3" placeholder="摘要">${news.synopsis}</textarea>
													</div>
												</div>
					                        </div>
					                        <div class="tab-pane" id="tab2">
					                        	<div class="form-group">
													<label class="col-lg-2 control-label">封皮:</label>
													<div class="col-lg-10">
														<div class="gallery">
								                      	<a href="${cdnDomain}${news.coverImg}" class="prettyPhoto[pp_gal]">
								                      		<img src="${cdnDomain}${news.coverImg}" alt="${news.title}" width="80">
								                      	</a>
								                      </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="coverImg">封皮:</label>
													<div class="col-lg-10">
														<input name="coverImg" id="coverImg" type="file" class="form-control" placeholder="封皮"></input>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-2 control-label" for="article">内容<span class="cofrequired">*</span>:</label>
													<div class="col-lg-10">
														<textarea name="article" id="article" class="form-control" rows="3" placeholder="内容">${news.article}</textarea>
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
			
			$('#title').focus();
	        $('#edit_news_form').validate({
	        	onsubmit:true,
        	  	onfocusout:false,
        	  	onkeyup:false,
        	  	onkeyup:false,
        	  	onclick:false,
	        	rules: {
	            	title: {
	                    required: true,
	                    minlength: 2,
	                    maxlength: 64
	                },
	                synopsis: {
	                	required: true,
	                    minlength: 2,
	                    maxlength: 256
	                }
	            },
	            messages: {
	            	title: {
	                    required: '标题不能为空',
	                    minlength: "标题长度不能小于2个字符",
	                    maxlength: "标题长度不能大于64个字符"
	                },
	                synopsis: {
	                    required: '摘要不能为空',
	                    minlength: "摘要长度不能小于2个字符",
	                    maxlength: "摘要长度不能大于256个字符"
	                }
	            },
	            submitHandler: function(form) {
	                form.submit();
	            }
	        });
	        
		});
	    
	</script>

</body>
