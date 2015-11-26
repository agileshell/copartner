<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
	<div class="conjtainer">
		<div class="navbar-header">
			<button class="navbar-toggle btn-navbar" type="button"
				data-toggle="collapse" data-target=".bs-navbar-collapse">
				<span>创始人后台</span>
			</button>
			<a href="#" class="navbar-brand hidden-lg">创始人后台</a>
		</div>
		<nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
			<ul class="nav navbar-nav">
				<li class="dropdown dropdown-big">
					<i>
						<img width="30px" height="30px" style="margin-left: 1px;margin-top: 10px" alt="创始人后台" src="${cdn}image/icon.png">
					</i>
				</li>
			</ul>
			
			<ul class="nav navbar-nav pull-right">
				<li class="dropdown pull-right">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="icon-user"></i> <shiro:principal property="name"/> <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<shiro:hasRole name="sadmin">
							<li><a href="/admin/list"><i class="icon-cogs"></i>管理设置</a></li>
						</shiro:hasRole>
                		<li><a href="#" data-toggle="modal" data-target="#changePassword"><i class="icon-dashboard"></i>修改密码</a></li>
                		
						<li><a href="/logout"><i class="icon-off"></i>退出</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
	
	<div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
       		<form id="password_change_form" class="form-horizontal" role="form" action="/async/password/change" method="POST">
       			<div class="modal-content">
	               <div class="modal-header">
	                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                   <h4 class="modal-title" id="myModalLabel">修改密码</h4>
	               </div>
	               <div class="modal-body">
	               		
	                   <div class="input-group input-group-lg">
	                       <span class="input-group-addon">原始密码:</span>
	                       <input type="password" name="old_password" id="old_password" class="form-control" placeholder="原始密码">
	                   </div>
	                   
	                   <div class="input-group input-group-lg">
	                       <span class="input-group-addon">新的密码:</span>
	                       <input type="password" name="new_password" id="new_password" class="form-control" placeholder="新的密码">
	                   </div>
	                   
	                   <div class="input-group input-group-lg">
	                       <span class="input-group-addon">确认密码:</span>
	                       <input type="password" name="confirm_password" id="confirm_password" class="form-control" placeholder="确认密码">
	                   </div>
	
	               </div>
	               <div class="modal-footer">
	                   <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                   <input type="submit" value="确认" class="btn btn-primary"/>
	               </div>
	           </div>
       		</form>
       </div>
   </div>
	
</div>
<header>
    <div class="container" style="height: 1px;"></div>
</header>
<script type="text/javascript">
	$(document).ready(function() {
		$('#old_password').focus();
		$('#password_change_form').validate({
			onsubmit : true,
			onfocusout : false,
			onkeyup : false,
			onkeyup : false,
			onclick : false,
			rules : {
				old_password : {
					required : true,
					minlength : 6
				},
				new_password : {
					required : true,
					minlength : 6
				},
				confirm_password : {
					required : true,
					minlength : 6,
					equalTo : "#new_password"
				}
			},
			messages : {
				old_password : {
					required : '请输入原密码',
					minlength : "密码不能小于6个字符"
				},
				new_password : {
					required : '请输入新密码',
					minlength : "密码不能小于6个字符"
				},
				confirm_password : {
					required : "请确认新密码",
					minlength : "密码不能小于6个字符",
					equalTo : "两次输入密码不一致!!!"
				}
			},
			submitHandler : function(form) {
				$.ajax({
					type : "POST",
					url : form.action,
					timeout : 5000,
					dataType : "JSON",
					data : {
						old_password : $("#old_password").val(),
						new_password : $("#new_password").val()
					},
					beforeSend : function(XMLHttpRequest) {
					},
					complete : function(XMLHttpRequest, status) {
						// status = success, error or timeout
						// if (status == 'timeout') {}
						form.reset();
					},
					success : function(data) {
						if (data.success) {
							alert(data.message);
							window.location.href = "/logout";
							return;
						}
						alert(data.message);
					},
					failure : function(data) {

					}
				});

			}
		});

	});
</script>

