<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>聊天详情</title>
	<link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.5.4/css/sm-extend.min.css">
	<style>
	.swiper-container {
	    padding-bottom: 0;
	}
	</style>
</head>
<body>
<div class="page" id="chat_detail" style="overflow:hidden;">
	<input type="hidden" id="sessionId" value="${sessionId}"/>
	<input type="hidden" id="chatType" value="${type}"/>
	<header class="bar bar-nav">
	    <a class="button button-link button-nav pull-left" href="javascript:void(0)" onclick="backToIndex();" external>
	    	<span class="icon icon-left"></span>
	    </a>
	    
	    <c:if test="${type ==1 }">
	    <a class="button button-link button-nav pull-right" href="<c:url value="/weixin/workbench/chatgroup/personal?otherId=${otherId }"/>" external>
	      <span class="icon icon-friends"></span>
	    </a>
	    </c:if>
	    <c:if test="${type ==2 }">
	    <a class="button button-link button-nav pull-right" href="<c:url value="/weixin/workbench/chatgroup/detail?groupId=${groupId }"/>" external>
	      <span class="icon icon-friends"></span>
	    </a>
	    </c:if>
	    <h1 class="title">${title }</h1>
	</header>
	
	<div class="content pull-to-refresh-content native-scroll" id="msg_content_list">
	    <!-- 默认的下拉刷新层 -->
	    <div class="pull-to-refresh-layer">
	        <div class="preloader"></div>
	        <div class="pull-to-refresh-arrow"></div>
	    </div>
	    <!-- 这里是页面内容区 -->
	    <div class="content1 content-msg-detail">
			<ul class="msg-detail-list" id="j-msg-detail-list">
				
			</ul>
		</div>
	</div>
    <!-- 
    <div>
    	<time id="time">
	        &nbsp;
	    </time>
	
	    <section>
	        <div class="hold-sound" id="holdSound" >
	            <img src="${contextPath}/static/wx/images/sp.gif" id="holdsay">
	        </div>
	        <div id="sound">
	            <div id="aftersound">
	                <img id="speak" src="${contextPath}/static/wx/images/speechh.png">
	                <span></span>
	                <input type="hidden"/>
	            </div>
	        </div>
	    </section>
	
	    <section>
	        <div id="record" >
	            <img src="${contextPath}/static/wx/images/voc.gif">
	            <p class="record_info">手指上滑，取消发送</p>
	        </div>
	        <div id="slideRecord">
	            <span class="iconfont" >&#xe610;</span>
	            <p class="cancel-send" >松开手指，取消发送</p>
	        </div>
	        <div id="endtime">
	            <span class="over" id="over">10</span>
	            <p class="record_info" id="info" >手指上滑，取消发送</p>
	        </div>
	        <div class="shortRecord" id="shortRecord">
	            <span class="over" >!</span>
	            <p class="record_info">说话时间太短</p>
	        </div>
	    </section>
    </div> 
     -->
	<div class="keyborad">
	    <div class="toolbar messagebar key_inp" >
			<div class="toolbar-inner">
				<!-- 
		        <a href="javascript:void(0);" class="link icon-only" external><i class="icon icon_emoji voice"></i></a>
		         -->
		        <textarea placeholder="Message" id="message_box"></textarea>
		        <a href="javascript:void(0);" class="link icon-only" external><i class="icon icon_emoji _emoji"></i></a>
		        <a href="javascript:void(0);" class="link icon-only option" external>
		        	<i class="icons icon_emoji more_option" style="overflow:hidden">
		        	<input type="file" id="upload_image" value="图片上传" accept="image/jpeg,image/gif,image/png" capture="camera">
		        	</i>
		        </a>
		        <a href="javascript:sendTextMsg();" class="link send-message" external style="font-size: 17px;"><span class="mes_send">发送</span></a>
		    </div>
	    </div>
	    <div class="toolbar messagebar key_voice" >
		    <div class="toolbar-inner">
		        <a href="javascript:void(0);" class="link icon-only" external><i class="icon icon_emoji voice"></i></a>
		        <div class="voice_btn" id="mysay">按住&nbsp;说话</div>
		        <a href="javascript:void(0);" class="link icon-only option" external><i class="icons icon_emoji more_option"></i></a>
		    </div>
	    </div> 
	    <div class="emoji_list_container">
	        <!-- Slider -->
	        <div class="swiper-container" >
	            <div class="swiper-wrapper emoji_list">
	
	            </div>
	        </div>
	    </div>
	    <div class="option_list_container">
	        <div class="swiper-container" >
	            <div class="swiper-wrapper option_list">
	
	            </div>
	        </div>   
	    </div>
   </div>
   
   <!-- Popup -->
   <div class="popup popup-img" style="background: rgba(0,0,0,.4);text-align: center;overflow: hidden;">
	  <span style="vertical-align: middle; height: 100%;display: inline-block; margin-left: -10px;"></span>
	  <img src="" id="big_img_p" style="vertical-align: middle;">
   </div>
</div>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.5.6/js/sm-extend.js' charset='utf-8'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/util/util.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/util/localResizeIMG2.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/util/mobileBUGFix.mini.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/chat.js'></script>

<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/websocket/sockjs-0.3.4.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/websocket/stomp.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/connect.js'></script>
<script type="text/javascript">
	var current_employee_id = "${employeeId}";
	var websocket_server_domain = "${webSocketPushCenterUrl}";
</script>
</body>
</html>