<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select id="status" class="form-control" name="status">
	<c:if test="${param.has_all}">
		<option value="">全部</option>
	</c:if>
	<option value="active" <c:if test="${param.status == 'active'}"> selected="selected" </c:if>>激活</option>
	<option value="inactive" <c:if test="${param.status == 'inactive'}"> selected="selected" </c:if>>无效</option>
	<option value="deleted" <c:if test="${param.status == 'deleted'}"> selected="selected" </c:if>>已删除</option>
</select>
