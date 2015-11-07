<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<div class="widget-foot" id="pagination_footer">
	<ul class="pagination pull-right">
		<li><a href="#">共${query.page}/${query.lastPageNum}页${query.count}条数据</a></li>
		<c:if test="${!query.homePage}">
			<li><a href="list?page=1${query.queryString}">首页</a></li>
			<li><a href="list?page=${query.pre_page}${query.queryString}">上一页</a></li>
		</c:if>
		
		<c:if test="${query.page_1}">
			<li><a href="list?page=${query.page_1Num}${query.queryString}">${query.page_1Num}</a></li>
		</c:if>
		
		<c:if test="${query.page_2}">
			<li><a href="list?page=${query.page_2Num}${query.queryString}">${query.page_2Num}</a></li>
		</c:if>
		
		<c:if test="${query.page_3}">
			<li><a href="list?page=${query.page_3Num}${query.queryString}">${query.page_3Num}</a></li>
		</c:if>
		
		<c:if test="${!query.lastPage}">
			<li><a href="list?page=${query.next_page}${query.queryString}">下一页</a></li>
			<li><a href="list?page=${query.lastPageNum}${query.queryString}">尾页</a></li>
		</c:if>
	</ul>
	<div class="clearfix"></div>
</div>