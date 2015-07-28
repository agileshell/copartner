<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<div class="sidebar-dropdown">
		<a href="#">导航</a>
	</div>
	<ul id="nav">
		<li>
			<a <c:if test="${viewname == 'home'}"> class="open" </c:if> href="/home"><i class="icon-bar-chart"></i>首页</a>
		</li>
		<li>
			<a <c:if test="${viewname == 'user_list'}"> class="open" </c:if> href="/user/list"><i class="icon-bar-chart"></i>用户列表</a>
		</li>
		<li>
			<a <c:if test="${viewname == 'content_list'}"> class="open" </c:if> href="/content/list"><i class="icon-bar-chart"></i>文章管理</a>
		</li>
        <li>
			<a <c:if test="${viewname == 'news_list'}"> class="open" </c:if> href="/news/list"><i class="icon-bar-chart"></i>新闻管理</a>
		</li>
        <li>
			<a <c:if test="${viewname == 'project_list'}"> class="open" </c:if> href="/project/list"><i class="icon-bar-chart"></i>项目管理</a>
		</li>
        <li>
			<a <c:if test="${viewname == 'manager'}"> class="open" </c:if> href="/commons/manager"><i class="icon-bar-chart"></i>资源管理</a>
		</li>
        <li>
			<a <c:if test="${viewname == 'feedback_list'}"> class="open" </c:if> href="/feedback/list"><i class="icon-list-alt"></i>意见反馈</a>
        </li>
	</ul>
</div>