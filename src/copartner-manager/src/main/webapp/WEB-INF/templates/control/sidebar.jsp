<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<div class="sidebar-dropdown">
		<a href="#">导航</a>
	</div>
	<ul id="nav">
		<li>
			<a <c:if test="${viewname == 'article-list'}"> class="open" </c:if> href="/article/list"><i class="icon-bar-chart"></i>文章列表</a>
		</li>
        <li>
			<a <c:if test="${viewname == 'article-create'}"> class="open" </c:if> href="/article/create"><i class="icon-list-alt"></i>创建文章</a>
        </li>
        <li>
			<a <c:if test="${viewname == 'style-list'}"> class="open" </c:if> href="/style/list"><i class="icon-magnet"></i>主题列表</a>
        </li>
        <li>
			<a <c:if test="${ViewName == 'style-create'}"> class="open" </c:if> href="/style/create"><i class="icon-group"></i>创建主题</a>
        </li>
	</ul>
</div>