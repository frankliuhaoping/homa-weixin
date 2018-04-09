<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的营销经验</title>
</head>
<body>
<div class="page " id="mark-mine-list-page">
	<form id="searchForm">
		<input type="hidden" name="type" value="1">
		<input type="hidden" name="sort" value="createdTime">
		<input type="hidden" name="order" value="desc">
		<input type="hidden" name="page" value="1">
		<input type="hidden" name="rows" value="10">
		<input type="hidden" name="search.id_eq" value="">
		<input type="hidden" name="search.createdBy.employeeList.id_eq" value="${employee.id}">
	</form>
	<div class="content infinite-scroll" data-toggle="scroller">
		<div class="marketing_top">
			
			<div class="tx">
				<a href="<c:url value='/weixin/workbench/marketing/experience/mine?employeeId=${employee.id}'/>" external>
					<c:choose>
						<c:when test="${employee.sysUser.weiXinUser.headImgUrl != null}">
							<img src="<c:url value='${employee.sysUser.weiXinUser.headImgUrl}'/>" width="100%">
						</c:when>
						<c:otherwise>
							<img src="<c:url value='/static/images/head.jpg'/>" width="100%">
						</c:otherwise>
					</c:choose>
					<span>${employee.name}</span>
				</a>
			</div>
			<div class="fb"><a href="<c:url value='/weixin/workbench/marketing/experience/add?employeeId=${employee.id}'/>" external><i class="iconfont_m">&#xe636;</i></a></div>
		</div>
		
		<%-- <p class="marketing_p">${date}  &nbsp;${weekDay}</p>
		<c:if test="${employee.id eq mine.id}">
			<div class="p8"><a class="button_m orange" href="<c:url value='/weixin/workbench/marketing/experience/add?employeeId=${employee.id}'/>"><i class="iconfont_m">&#xe642;</i> 发 布</a></div>
		</c:if> --%>
		<div class="marketing_content">
			
		</div>
		
		<div class="infinite-scroll-preloader">
	        <div class="preloader"></div>
	    </div>
	</div>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/mark/mark.js' charset='utf-8'></script>
</body>
</html>