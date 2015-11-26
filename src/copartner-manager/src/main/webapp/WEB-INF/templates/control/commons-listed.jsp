<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<select id="listed" class="form-control" name="listed">
	<c:if test="${param.has_all}">
		<option value="1">全部</option>
	</c:if>
	<option value="1" <c:if test="${param.listed == '1'}"> selected="selected" </c:if>>展示</option>
	<option value="0" <c:if test="${param.listed == '0'}"> selected="selected" </c:if>>隐藏</option>
</select>
