<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
	<div class="conjtainer">
		<div class="navbar-header">
			<button class="navbar-toggle btn-navbar" type="button"
				data-toggle="collapse" data-target=".bs-navbar-collapse">
				<span>创客汇</span>
			</button>
			<a href="#" class="navbar-brand hidden-lg">创客汇</a>
		</div>
		<nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
			<ul class="nav navbar-nav">
				<li class="dropdown dropdown-big">
					<i>
						<img width="30px" height="30px" style="margin-left: 1px;margin-top: 10px" alt="SSO" src="${cdn}image/shoseicon64px.png">
					</i>
				</li>
			</ul>
			
			<ul class="nav navbar-nav pull-right">
				<li class="dropdown pull-right">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="icon-user"></i> 刘飞 <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="icon-cogs"></i>设置</a></li>
						<li><a href="#"><i class="icon-off"></i>退出</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
<header>
    <div class="container" style="height: 1px;"></div>
</header>