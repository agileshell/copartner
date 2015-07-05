<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select id="cron_expression_select" class="form-control">
  <option value="0 0 0 * * ?">凌晨0点执行</option>
  <option value="0 0 1 * * ?">凌晨1点执行</option>
</select>