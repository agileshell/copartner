<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select id="status" class="form-control" name="status">
	<c:if test="${param.has_all}">
		<option value="">全部</option>
	</c:if>
	<option value="1" <c:if test="${param.listed == '1'}"> selected="selected" </c:if>>展示</option>
	<option value="0" <c:if test="${param.listed == '0'}"> selected="selected" </c:if>>隐藏</option>
</select>
