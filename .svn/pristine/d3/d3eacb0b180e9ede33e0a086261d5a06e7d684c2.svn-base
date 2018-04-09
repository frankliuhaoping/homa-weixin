<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>资讯详情</title>
	<style type="text/css">
	.inform_text p {
	    font-size: 18px;
	    text-indent: 0;
	    line-height: 28px;
	}
	</style>
</head>
<body>
<div class="page" id="noticetab2-page">
	<div class="content infinite-scroll noticScroll" data-toggle="scroller" data-distance="100" id="notice">
		<div class="p8"><img src="${contextPath}/static/wxworkbench/img/biglogo.png" width="30%" /></div>
		
		<div class="div_inform plr8">
			<%-- <input type="hidden"  value="${news.id}" id="newsDetailId"/> --%>
	 		<h3 class="f22 fw700">${news.title }</h3>
	    	<p class="f14 y999 ptb8"><i class="iconfont_m">&#xe622;</i> <fmt:formatDate value="${news.releaseTime}" pattern="yyyy-MM-dd"/>&nbsp;<fmt:formatDate value="${news.releaseTime}" pattern="HH:mm"/></p>
	    </div>
	    
	    <div class="inform_text plr8">
		    <div style="text-align:center">
		    
		    <c:if test="${news.coverPictureUrl!=null && news.coverPictureUrl!=''}">
		    <img src="${contextPath }${news.coverPictureUrl}" width="100%"/>
		    </c:if>
		    </div>
		    <p class="p2">${news.content }</p>
		
		   <!--  <div class="append_con" >
		        <input type="text" placeholder="请输入" class="txts"/>
		        <button class="button blue">发表</button>
		    </div> -->
		</div>		
	</div>
</div>
</body>
</html>
