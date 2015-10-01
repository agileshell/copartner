<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>融智编辑</title>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">融智编辑</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>融智编辑
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
								<div class="pull-left">融智编辑</div>
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
<form id="edit_finance_form" class="form-horizontal" role="form" action="/demand/update/${demand.id}" method="post" enctype="multipart/form-data">

<div class="form-group">
	<label class="col-lg-2 control-label" for="projectName">融智名称<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<input name="projectName" id="projectName" value="${demand.projectName}" type="text" class="form-control" placeholder="融智名称"></input>
	</div>
</div>

<div class="form-group">
	<label class="col-lg-2 control-label" for="contactPerson">联系人<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<input name="contactPerson" id="contactPerson" value="${demand.contactPerson}" type="text" class="form-control" placeholder="联系人"></input>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label" for="contact">联系方式<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<input name="contact" id="contact" value="${demand.contact}" type="text" class="form-control" placeholder="联系方式"></input>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label" for="status">状态<span class="cofrequired">*</span>:</label>
	<div class="col-lg-10">
		<select id="status" class="form-control" name="status">
			<option value="active" <c:if test="${demand.status == 'active'}"> selected="selected" </c:if>>可用</option>
			<option value="inactive" <c:if test="${demand.status == 'inactive'}"> selected="selected" </c:if>>不可用</option>
			<option value="deleted" <c:if test="${demand.status == 'deleted'}"> selected="selected" </c:if>>删除</option>
		</select>
	</div>
</div>
<div class="form-group">
<label class="col-lg-2 control-label" for="content">融智要求:</label>
<div class="col-lg-10">
	<textarea name="content" id="content" class="form-control" rows="3" placeholder="融智要求">${demand.content}</textarea>
	</div>
</div>
<div class="form-group">
<label class="col-lg-2 control-label" for="advantage">优势:</label>
<div class="col-lg-10">
	<textarea name="advantage" id="advantage" class="form-control" rows="3" placeholder="优势">${demand.advantage}</textarea>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label" for="businessLicense">营业执照号:</label>
	<div class="col-lg-10">
		<input name="businessLicense" id="businessLicense" value="${demand.businessLicense}" type="text" class="form-control" placeholder="营业执照号"></input>
	</div>
</div>
<div class="form-group">
	<label class="col-lg-2 control-label" for="businessPlan">商业计划书:</label>
	<div class="col-lg-6">
		<input name="businessPlan" id="businessPlan" type="file" class="form-control" placeholder="商业计划书"></input>
	</div>
	<div class="col-lg-4">
		<a href="${cdnDomain}${demand.businessPlan}" target="_blank">查看商业计划书</a>
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

	<script>
		$(document).ready(function() {
			$('#projectName').focus();
	        $('#edit_finance_form').validate({
	        	onsubmit:true,
        	  	onfocusout:false,
        	  	onkeyup:false,
        	  	onkeyup:false,
        	  	onclick:false,
	            rules: {
	            	projectName: {
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
	                    required: '融智名称不能为空',
	                    minlength: "融智名称长度不能小于2个字符",
	                    maxlength: "融智名称长度不能大于64个字符"
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
