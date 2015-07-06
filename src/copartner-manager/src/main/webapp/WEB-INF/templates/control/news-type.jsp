<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select id="type" class="form-control" name="type">
	<c:if test="${param.has_all}">
		<option value="0">全部</option>
	</c:if>
	<option value="1" <c:if test="${param.type == 1}"> selected="selected" </c:if>>行业新闻</option>
	<option value="2" <c:if test="${param.type == 2}"> selected="selected" </c:if>>地方新闻</option>
</select>