<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<h1>报错啦</h1>
<c:if test="${alert == true }">
	<script src="${contextPath}/static/js/base.js"></script>
	<script>
		base.alert('${message}');
	</script>
</c:if>
<c:if test="${alert == false }">
	<c:out value="${message}" ></c:out>    
</c:if>