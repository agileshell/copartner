<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select id="type" class="form-control" name="type">
	<c:if test="${param.has_all}">
		<option value="0">全部</option>
	</c:if>
	<option value="1" <c:if test="${param.type == 0}"> selected="selected" </c:if>>地方政策</option>
	<option value="2" <c:if test="${param.type == 1}"> selected="selected" </c:if>>国家政策</option>
</select>