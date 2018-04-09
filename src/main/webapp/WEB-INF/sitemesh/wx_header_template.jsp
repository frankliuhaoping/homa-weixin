<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.cnyirui.framework.utils.CurrentUserUtils"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><sitemesh:write property='title' /></title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />	    
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

	<link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.5.6/css/sm.min.css">
	<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/aoma.css">
	<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/aoma1.css">
	<!-- chat -->
	<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/chat.css">
	<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/group.css">
	<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/me.css" >
	<link rel="stylesheet" href="${contextPath}/static/wxworkbench/css/me1.css" >
	<script>
		var contextPath = "${contextPath}";
	</script>
	<sitemesh:write property='head' />
  </head>
  <body>
	<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.5.6/js/sm.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/moveBtn/fx.js' charset='utf-8'></script>
	<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/moveBtn/moveBtn.js' charset='utf-8'></script>
  	<sitemesh:write property='body' />
  	<div id="layer_Bg" class="move_btn_hide"></div>
    
    
    <div id="fixBtn">
    	<div class="fixBtn_one"></div>
    	<div class="fixBtn_c" onclick="toUrl('${contextPath}/weixin/workbench/index')">
			<i class="iconfont_m" style="position: relative;">&#xe64b</i>
			<span>首页</span>
		</div>
    	<c:set var="index" value="0"/> 
    	<c:forEach items="<%=CurrentUserUtils.getSysMenuTreeList()%>" var="parent">
			<c:if test="${parent.hasChildren()}">
				<c:forEach items="${parent.children}" var="child" varStatus="status">
					<c:set var="index" value="${index + 1 }"/> 
					
					<c:if test="${index < 5 }">
					<div class="fixBtn_c" onclick="toUrl('${contextPath}${child.attributes}')">
						<i class="iconfont_m ${child.iconCls}" style="position: relative;"></i>
						<span>${child.text}</span>
					</div>
					</c:if>
					
				</c:forEach>
			</c:if>
		</c:forEach>
		<div class="fixBtn_c" onclick="toUrl('${contextPath}/weixin/workbench/login/logout')">
			<i class="iconfont_m icon-tuichu"></i>
			<span>退出</span>
		</div>
    </div>
    <script>
	    //调间隔
	    $("#fixBtn").phoneMoveBtn({
	    	timer:"250",
	        _R:"80",
            cBtn_width:"64px",
            cBtn_height:"84px"
	    });
	    
	    function toUrl(url){
	    	window.location.href = url;
	    }
	</script>    		
  </body>
</html>