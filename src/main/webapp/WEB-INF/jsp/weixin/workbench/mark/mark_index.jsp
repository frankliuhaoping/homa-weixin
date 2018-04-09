<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>营销经验</title>
	<link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.5.4/css/sm-extend.min.css">
</head>
<body>
<div class="page" id="mark-friend-list-page">
	<form id="searchForm">
		<input type="hidden" name="type" value="0">
		<input type="hidden" name="sort" value="createdTime">
		<input type="hidden" name="order" value="desc">
		<input type="hidden" name="search.id_eq" value="">
		<input type="hidden" name="page" value="1">
		<input type="hidden" name="rows" value="10">
	</form>
	<div class="content infinite-scroll" data-toggle="scroller">
		<div class="marketing_top">
			
			<div class="tx">
				<a href="<c:url value='/weixin/workbench/marketing/experience/mine?employeeId=${employee.id}'/>" external>
					<img src="<c:url value='${employee.headImgUrl}'/>" width="100%"/>
					
				</a>
			</div>
			<div class="name">${employee.name}</div>
			<div class="fb"><a href="<c:url value='/weixin/workbench/marketing/experience/add?employeeId=${employee.id}'/>" external/><i class="iconfont_m">&#xe636;</i></a></div>
		</div>

		<div class="share_div">
		</div>
		
		<div class="infinite-scroll-preloader">
	        <div class="preloader"></div>
	    </div>
	    
	</div>
	
	
	<!-- 营销经验 评论 Popup -->
	<div class="popup popup-comment" style="overflow: hidden;">
		<div class="Release p8">
			<input type="hidden" value="" id="mark-exce">
			<textarea id="comment-content" placeholder="请输入评论" style="width:100%; height:70%;"></textarea>
			<p style="margin: 1em 0;">
			<a href="#" class="button_m orange" id="send-comment"><i class="iconfont_m">&#xe636;</i> 发布评论</a>
			</p>
			<p style="margin: 1em 0;">
			<a href="#" class="button_m blue" id="popup-comment-close"><i class="iconfont_m">&#xe63b;</i> 关闭</a>
			</p>
		</div>
	</div>
</div>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.5.6/js/sm-extend.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/mark/mark.js' charset='utf-8'></script>
</body>
</html>