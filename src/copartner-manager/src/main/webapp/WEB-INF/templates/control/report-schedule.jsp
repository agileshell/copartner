<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select name="schedule" id="schedule" class="form-control">
  <option value="1" <c:if test="${req.schedule == 1}"> selected="selected" </c:if>>开启</option>
  <option value="2" <c:if test="${req.schedule == 2}"> selected="selected" </c:if>>关闭</option>
</select>