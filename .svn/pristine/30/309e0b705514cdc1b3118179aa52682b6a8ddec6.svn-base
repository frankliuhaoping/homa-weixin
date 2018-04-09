<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>通知详情</title>
	<style type="text/css">
	.inform_text p {
	    font-size: 18px;
	    text-indent: 0;
	    line-height: 28px;
	}
	</style>
</head>
<body>
<div class="page " id="noticetab1-page">
	<div class="content infinite-scroll noticScroll" data-toggle="scroller" data-distance="100" id="notice">
		<div class="p8"><img src="${contextPath}/static/wxworkbench/img/biglogo.png" width="30%" /></div>
			
		<div class="div_inform plr8 ">
		 	<h3 class="f22 fw700">${notice.title }</h3>
		 	<p class="f14 y999 ptb8"><i class="iconfont_m">&#xe622;</i> <fmt:formatDate value="${notice.releaseTime}" pattern="yyyy-MM-dd"/>&nbsp;<fmt:formatDate value="${notice.createdTime}" pattern="HH:mm"/></p>
		</div>
		<div class="inform_text plr8">
		    ${notice.content }
		</div>
	</div>
</div>
</body>
</html>
