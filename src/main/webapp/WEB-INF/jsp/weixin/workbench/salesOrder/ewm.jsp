<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>扫描二维码</title>
</head>
<body>
<div class="page " id="ewm-page" style="background: rgba(0,0,0,0.5);">
	<div class="content">
		<input type="hidden" value="${orderId }" id="ewmOrderId"/>
		<div class="two_dimension_code">
		    <div class="two_weima_con">
			       <img src="${contextPath }/weixin/qrCode/so/${orderId}" width="100%" onload="loadComp()"/>
			
			        <p>将二维码/条码放入框内<br />即可自动扫描</p>
			        <button class="button_m orange" onclick="closeEwm()"><i class="icon_m iconfont_m">&#xe63b;</i> 关 闭</button>
			    </div>
			<div class="h30"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var current_order_id = "${orderId}";
	var websocket_server_domain = "${webSocketPushCenterUrl}";
</script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/websocket/sockjs-0.3.4.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/websocket/stomp.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/salesOrder/ewm.js' charset='utf-8'></script>
</body>
</html>
