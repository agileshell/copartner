<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>项目编辑</title>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">项目编辑</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>项目编辑
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
								<div class="pull-left">项目编辑</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd form-horizontal">
									<c:if test="${!success}">
										<div class="form-group">
											<div class="col-lg-12" style="text-align: center;">项目不存在!!!</div>
										</div>
									</c:if>
									<c:if test="${success}">
<form id="edit_project_form" class="form-horizontal" role="form" action="/project/update/${project.id}" method="post" enctype="multipart/form-data">
									
<div class="form-group">
	<label class="col-lg-2 control-label" for="name">项目名称<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<input name="name" id="name" value="${project.name}" type="text" class="form-control" placeholder="项目名称"></input>
	</div>
</div>								
<div class="form-group">
	<label class="col-lg-2 control-label" for="contactPerson">联系人<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<input name="contactPerson" id="contactPerson" value="${project.contactPerson}" type="text" class="form-control" placeholder="联系人"></input>
	</div>
</div>								
<div class="form-group">
	<label class="col-lg-2 control-label" for="contact">联系方式<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<input name="contact" id="contact" value="${project.contact}" type="text" class="form-control" placeholder="联系方式"></input>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label">项目LOGO:</label>
	<div class="col-lg-10">
	<div class="gallery">
     	<a href="${cdnDomain}${project.logo}" class="prettyPhoto[pp_gal]">
     		<img src="${cdnDomain}${project.logo}" alt="${project.name}" width="80">
     	</a>
     </div>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label" for="logo">项目LOGO<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<input name="logo" id="logo" type="file" class="form-control" placeholder="项目LOGO"></input>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label" for="status">状态<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<select id="status" class="form-control" name="status">
			<option value="active" <c:if test="${project.status == 'active'}"> selected="selected" </c:if>>可用</option>
			<option value="inactive" <c:if test="${project.status == 'inactive'}"> selected="selected" </c:if>>不可用</option>
			<option value="deleted" <c:if test="${project.status == 'deleted'}"> selected="selected" </c:if>>删除</option>
		</select>
	</div>
</div>

<div class="form-group">
<label class="col-lg-2 control-label" for="advantage">优势:</label>
<div class="col-lg-10">
	<textarea name="advantage" id="advantage" class="form-control" rows="3" placeholder="优势">${project.advantage}</textarea>
	</div>
</div>
<div class="form-group">
<label class="col-lg-2 control-label" for="content">实施条件:</label>
<div class="col-lg-10">
	<textarea name="content" id="content" class="form-control" rows="3" placeholder="实施条件">${project.content}</textarea>
	</div>
</div>
<hr />
<div class="form-group">
	<div class="col-lg-offset-1 col-lg-12">
	<button type="submit" class="btn btn-default">提交</button>
	</div>
</div>
</form>
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
	
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>

	<script>
		$(document).ready(function() {
			$('#name').focus();
	        $('#edit_project_form').validate({
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
	                contactPerson: {
	                	required: true,
	                    minlength: 2,
	                    maxlength: 64
	                },
	                contact: {
	                	required: true,
	                    minlength: 2,
	                    maxlength: 20
	                }
	            },
	            messages: {
	            	name: {
	                    required: '项目名称不能为空',
	                    minlength: "项目名称长度不能小于2个字符",
	                    maxlength: "项目名称长度不能大于64个字符"
	                },
	                contactPerson: {
	                    required: '联系人不能为空',
	                    minlength: "联系人长度不能小于2个字符",
	                    maxlength: "联系人长度不能大于64个字符"
	                },
	                contact: {
	                    required: '联系人不能为空',
	                    minlength: "联系人长度不能小于2个字符",
	                    maxlength: "联系人长度不能大于20个字符"
	                }
	            },
	            submitHandler: function(form) {
	                form.submit();
	            }
	        });
	        
		});
	    
	</script>
	
</body>
