<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select id="status_${param.id}" class="form-control" name="status">
	<c:if test="${param.has_all}">
		<option value="">全部</option>
	</c:if>
	<option value="active" <c:if test="${param.status == 'active'}"> selected="selected" </c:if>>激活</option>
	<option value="inactive" <c:if test="${param.status == 'inactive'}"> selected="selected" </c:if>>无效</option>
	<option value="deleted" <c:if test="${param.status == 'deleted'}"> selected="selected" </c:if>>已删除</option>
</select>

<c:if test="${param.update}">
<script type="text/javascript">
    $(document).ready(function(){
	   	$('#status_${param.id}').change(function(){
		   	var selectedStatus = $(this).children('option:selected').val();
		   	$.ajax({
	            url : '${param.url}',
	            type : 'POST',
	            data: {status: selectedStatus},
	            dataType : 'JSON',
	            beforeSend : function() {},
	            success : function(data) {},
	            error : function() {}
	        });
	   	})
   	});
</script>
</c:if>
