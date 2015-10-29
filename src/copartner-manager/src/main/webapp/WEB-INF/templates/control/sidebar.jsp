<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<div class="sidebar-dropdown">
		<a href="#">导航</a>
	</div>
	<ul id="nav">
		<li>
			<a <c:if test="${viewname == 'home_2_0'}"> class="open" </c:if> href="/home"><i class="icon-home"></i>首页</a>
		</li>
		<li>
			<a <c:if test="${viewname == 'user_list'}"> class="open" </c:if> href="/user/list"><i class="icon-user"></i>用户列表</a>
		</li>
		<li>
			<a <c:if test="${viewname == 'content_list'}"> class="open" </c:if> href="/content/list"><i class="icon-book"></i>政策解读</a>
		</li>
        
        <li>
			<a <c:if test="${viewname == 'project_list'}"> class="open" </c:if> href="/project/list"><i class="icon-bookmark"></i>项目管理</a>
        </li>
        
        <li>
			<a <c:if test="${viewname == 'require_list'}"> class="open" </c:if> href="/require/list"><i class="icon-tint"></i>需求管理</a>
        </li>
        
        <li>
			<a <c:if test="${viewname == 'campaign_list'}"> class="open" </c:if> href="/campaign/list"><i class="icon-tint"></i>活动管理</a>
        </li>
		
		<li class="has_sub">
			<a href="#" <c:if test="${viewname == 'pioneerpark_list' || viewname == 'investorg_list'}"> class="subdrop" </c:if>>
				<i class="icon-star"></i>公共资源<span class="pull-right"><i class="icon-chevron-right"></i></span>
			</a>
			<ul <c:if test="${viewname == 'pioneerpark_list' || viewname == 'investorg_list'}"> style="display: block;" </c:if>>
			  	<li>
					<a <c:if test="${viewname == 'pioneerpark_list'}"> class="open" </c:if> href="/pioneerpark/list"><i class="icon-road"></i>创业园</a>
				</li>
		        <li>
					<a <c:if test="${viewname == 'investorg_list'}"> class="open" </c:if> href="/investorg/list"><i class="icon-map-marker"></i>投资机构</a>
				</li>
			</ul>
        </li>
		
		<li class="has_sub">
			<a href="#" <c:if test="${viewname == 'contest_list' || viewname == 'contestentry_list' || viewname == 'contestproject_list'}"> class="subdrop" </c:if>>
				<i class="icon-star"></i>创业大赛<span class="pull-right"><i class="icon-chevron-right"></i></span>
			</a>
			<ul
			     <c:if test="${viewname == 'contest_list' || viewname == 'contestentry_list' || viewname == 'contestproject_list'}"> style="display: block;" </c:if>>
			    <li>
					<a <c:if test="${viewname == 'contest_list'}"> class="open" </c:if> href="/contest/list"><i class="icon-heart"></i>大赛管理</a>
				</li>
			    <li>
					<a <c:if test="${viewname == 'contestentry_list'}"> class="open" </c:if> href="/contestentry/list"><i class="icon-star"></i>大赛作品</a>
				</li>
			    <li>
					<a <c:if test="${viewname == 'contestproject_list'}"> class="open" </c:if> href="/contestproject/list"><i class="icon-asterisk"></i>项目汇总</a>
				</li>
			</ul>
        </li>
        
        <li>
			<a <c:if test="${viewname == 'news_list'}"> class="open" </c:if> href="/news/list"><i class="icon-camera"></i>新闻动态</a>
		</li>
        <li>
			<a <c:if test="${viewname == 'course_list'}"> class="open" </c:if> href="/course/list"><i class="icon-inbox"></i>充电站</a>
        </li>
        
        <li>
			<a <c:if test="${viewname == 'comments_list'}"> class="open" </c:if> href="/comments/list"><i class="icon-comments"></i>评论管理</a>
        </li>
		
		<!--
		<li class="has_sub">
			<a href="#" <c:if test="${viewname == 'project_list' || viewname == 'demand_list' || viewname == 'financing_list' || viewname == 'comments_list'}"> class="subdrop" </c:if>>
				<i class="icon-star"></i>融资融智<span class="pull-right"><i class="icon-chevron-right"></i></span>
			</a>
			<ul <c:if test="${viewname == 'project_list' || viewname == 'demand_list' || viewname == 'financing_list' || viewname == 'comments_list'}"> style="display: block;" </c:if>>
			  	<li>
					<a <c:if test="${viewname == 'project_list'}"> class="open" </c:if> href="/project/list"><i class="icon-bookmark"></i>项目管理</a>
				</li>
		        <li>
					<a <c:if test="${viewname == 'demand_list'}"> class="open" </c:if> href="/demand/list"><i class="icon-tint"></i>融智管理</a>
				</li>
		        <li>
					<a <c:if test="${viewname == 'financing_list'}"> class="open" </c:if> href="/finance/list"><i class="icon-asterisk"></i>融资管理</a>
				</li>
		        <li>
					<a <c:if test="${viewname == 'comments_list'}"> class="open" </c:if> href="/comments/list"><i class="icon-comments"></i>评论管理</a>
		        </li>
			</ul>
        </li>
		-->
		
		<li class="has_sub">
			<a href="#" <c:if test="${viewname == 'resources_manager'}"> class="subdrop" </c:if>>
				<i class="icon-star"></i>系统配置<span class="pull-right"><i class="icon-chevron-right"></i></span>
			</a>
			<ul <c:if test="${viewname == 'resources_manager'}"> style="display: block;" </c:if>>
			  <li><a href="/srvarch/list"><i class="icon-screenshot"></i>服务机构</a></li>
			  <li><a href="/industry/list"><i class="icon-screenshot"></i>行业管理</a></li>
			  <li><a href="/phase/list"><i class="icon-list"></i>项目阶段管理</a></li>
			  <li><a href="/finaphase/list"><i class="icon-key"></i>融资阶段管理</a></li>
			  <li><a href="/role/list"><i class="icon-asterisk"></i>角色管理</a></li>
			  <li><a href="/status/list"><i class="icon-key"></i>状态管理</a></li>
			  <li><a href="/team/list"><i class="icon-key"></i>团队规模</a></li>
			</ul>
        </li>
        <li>
			<a <c:if test="${viewname == 'question_list'}"> class="open" </c:if> href="/question/list"><i class="icon-question"></i>问题管理</a>
        </li>
        <li>
			<a <c:if test="${viewname == 'feedback_list'}"> class="open" </c:if> href="/feedback/list"><i class="icon-envelope"></i>意见反馈</a>
        </li>
        
	</ul>
</div>