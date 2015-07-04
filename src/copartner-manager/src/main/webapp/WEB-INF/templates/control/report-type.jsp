<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select name="type" id="type" class="form-control">
  <option value="3" <c:if test="${req.type == 3}"> selected="selected" </c:if>>SQL类型</option>
  <option value="2" <c:if test="${req.type == 2}"> selected="selected" </c:if>>脚本类型</option>
  <option value="1" <c:if test="${req.type == 1}"> selected="selected" </c:if>>插件类型</option>
</select>