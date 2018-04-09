<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.cnyirui.framework.utils.CurrentUserUtils"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>考勤签到</title>
</head>
<body>
<div class="page ">
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

	    <!-- 未签到 -->
		<aside class="aoma_con cc sing_in_aoma_con sing_in_in">
		    <div class="con_left sing_in_con_left">
		        <p class="p1"><font class="fots" id="weiqiandao">&nbsp;&nbsp;</font></p>
		        <p class="p2" id="time88"><!-- <i class="iconfont_m" id="i1">&#xe622;</i> -->&nbsp;&nbsp;</p>
		    </div>
		
		    <div class="sing_in_con_right ">
		        <button class="buttones"  id="singBtn" style="width: 120px;padding: 5px 25px;font-size:22px;">签到</button>
		    </div>
		    
		</aside>

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
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/sign/salesSign.js' charset='utf-8'></script>
</body>
</html>