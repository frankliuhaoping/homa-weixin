<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>考勤签到</title>
</head>
<body>

<div class="page">
	<div class="content">
	
		<!-- 头部信息-->
		<header class="sign_in">
		     <div class="logo_img"><img src="${weixinUser.headImgUrl}" width="25" height="25"/></div>
		     <div class="logo_name f22">${employee.name}</div>
		     <div class="right_but"><i class="iconfont"></i></div>
		</header>

		<!-- 日期时间 -->
		<section class="sign_in_middle">
		   <h3 class="f16 y999"><i class="iconfont_m">&#xe656;</i> ${date}&nbsp;${weekDay}&nbsp;今天</h3>
		</section>

		<!-- 已签到 -->
		<c:if test="${fn:length(startSignList) gt 0}">
		<aside class="aoma_con cc sing_in_aoma_con sing_in_in">
		    <div class="con_left sing_in_con_left">
		        <p class="p1">上班</p>
		        <p class="p2"><i class="iconfont_m" id="start">&#xe622;</i>&nbsp;&nbsp;<fmt:formatDate value="${startSignList[0].signTime}" pattern="HH:mm:ss"/></p>
		    </div>
		
		    <div class="sing_in_con_right">
		        <button class="" id="sign">已签到</button>
		    </div>
		    
		</aside>
	    </c:if>
    
	    <!-- 未签到 -->
	    <c:if test="${fn:length(startSignList) eq 0}">
		<aside class="aoma_con cc sing_in_aoma_con sing_in_in">
		    <div class="con_left sing_in_con_left">
		        <p class="p1">上班<font class="fots" id="weiqiandao">&nbsp;&nbsp;未签到</font></p>
		        <p class="p2"><i class="iconfont_m" id="i1">&#xe622;</i>&nbsp;&nbsp;</p>
		    </div>
		
		    <div class="sing_in_con_right ">
		        <button class="buttones" id="singBtn">签到</button>
		    </div>
		    
		</aside>
	    </c:if>
    
        <!-- 未签退 -->
		<c:if test="${fn:length(offSignList) eq 0}">
		<aside class="aoma_con cc sing_in_aoma_con sing_in_exit" style="border:none;">
		     <div class="con_left sing_in_con_left">
		        <p class="p1">下班<font class="fots" id="weiqiandao2">&nbsp;&nbsp;未签到</font></p>
		        <p class="p2"><i class="iconfont_m" id="i2">&#xe622;</i>&nbsp;&nbsp;</p>
		    </div>
		    
		    <div class="sing_in_con_right">
		        <button  class="buttones" id="signOffBtn">签退</button>
		    </div>
		</aside>
		</c:if>
 
        <!-- 已签退 -->
		<c:if test="${fn:length(offSignList) gt 0}">
		<aside class="aoma_con cc sing_in_aoma_con sing_in_exit" style="border:none;">
		     <div class="con_left sing_in_con_left">
		        <p class="p1">下班</p>
		        <p class="p2"><i class="iconfont_m" id="off">&#xe622;</i>&nbsp;&nbsp;<fmt:formatDate value="${offSignList[0].signTime}" pattern="HH:mm:ss"/></p>
		    </div>
		    
		    <div class="sing_in_con_right">
		        <button  class="">已签退</button>
		    </div>
		</aside>
		</c:if>
		
		<footer class="more_css_footer">
		    <div class="div_but p8">
		        <a href="${contextPath}/weixin/workbench/sign/historyList" external> 
		        <button class="button_m orange" id="lookRecord"><i class="iconfont_m">&#xe614;</i> 查看签到记录</button>
		        </a>
		    </div>
		    <div class="h30"></div>
		</footer>

  </div>
</div>
<script type="text/javascript">
	var weiXinConfigUrl = location.href.split('#')[0];
	var weiXinConfigDebug = false;
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/sign/sign.js' charset='utf-8'></script>
</body>
</html>