<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>企业资讯</title>
</head>
<body>
<div class="page" id="notice-page">
	<div class="content infinite-scroll noticScroll" data-toggle="scroller" data-distance="100" id="notice">
		<div class="Tab_Controls cc">
		<c:if test="${type == 0}">
			<div  class="noticScrollflag" title="noticScroll1"><i class="iconfont_m f26">&#xe629;</i><p>产品资料</p></div>
		    <div class="active  noticScrollflag" title="noticScroll2"><i class="iconfont_m f26">&#xe63e;</i><p>促销资料</p></div>
		</c:if>
		<c:if test="${type != 0 }">
			<div  class="active noticScrollflag" title="noticScroll1"><i class="iconfont_m f26">&#xe629;</i><p>产品资料</p></div>
		    <div class="noticScrollflag" title="noticScroll2"><i class="iconfont_m f26">&#xe63e;</i><p>促销资料</p></div>
		</c:if>
		    
		    <div class="noticScrollflag" title="noticScroll3" style="border-right: 1px solid #a9a9aa;"><i class="iconfont_m f26">&#xe63c;</i><p>终端资料</p></div>
		    <div class="noticScrollflag" title="noticScroll4"><i class="iconfont_m f26">&#xe657;</i><p>其他资料</p></div>
		</div>
		
		<c:if test="${type == 0}">
			<div id="notice-page-one" class="notice-page-notice" style="display: none;" >
			</div>	
			<div id="notice-page-two" class="notice-page-notice" style="display: block;">
			</div>	
		</c:if>
		<c:if test="${type != 0}">
			<div id="notice-page-one" class="notice-page-notice" style="display: block;" >
			</div>	
			<div id="notice-page-two" class="notice-page-notice" style="display: none;">
			</div>
		</c:if>
		
		<div id="notice-page-three" class="notice-page-notice" style="display: none;">
		
		</div>		
		
		<div id="notice-page-four" class="notice-page-notice" style="display: none;">
		
		</div>
		
		<c:if test="${type == 0}">
			<div class="infinite-scroll-preloader noticesflag noticScroll1" style="display:none">
	       		<div class="preloader"></div>
	    	</div>
	    	
	    	<div class="infinite-scroll-preloader noticesflag noticScroll2"  style="display:block">
	       		<div class="preloader"></div>
	    	</div>
		</c:if>
		<c:if test="${type != 0}">
			<div class="infinite-scroll-preloader noticesflag noticScroll1" style="display:block">
	       		<div class="preloader"></div>
	    	</div>
	    	
	    	 <div class="infinite-scroll-preloader noticesflag noticScroll2"  style="display:none">
	       		 <div class="preloader"></div>
	    	</div>
		</c:if>
		
    	<div class="infinite-scroll-preloader noticesflag noticScroll3"  style="display:none">
       		 <div class="preloader"></div>
    	</div>
		
		<div class="infinite-scroll-preloader noticesflag noticScroll4"  style="display:none">
       		 <div class="preloader"></div>
    	</div>	
	</div>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/notice/notice.js' charset='utf-8'></script>
</body>
</html>
