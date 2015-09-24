<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="widget-foot" id="pagination_footer">
	<ul class="pagination pull-right">
		<li><a href="list?page=1&${query.queryString}">首页</a></li>
		<li><a href="list?page=${query.pre_page}&${query.queryString}">上一页</a></li>
		<li><a href="list?page=${query.next_page}&${query.queryString}">下一页</a></li>
	</ul>
	<div class="clearfix"></div>
</div>