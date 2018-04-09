<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>
		<c:if test="${type ==1}">消息</c:if>
		<c:if test="${type ==2}">通讯录</c:if>
	</title>
</head>
<body>
<div class="page" id="chat_index">
<div class="content infinite-scroll" id="chat_org_scroll" data-toggle="scroller">
	 <input type="hidden" value="${type }" id="type"/>
     <div class="fastLinkList clearfix num4">
	    <a href="#" class="tab-link active" id="atab1" external>
		    <i class="icon-contact session" id="recent_msg_icon">
		    	<c:if test="${unReadNum > 0 }">
		    	<span class="num">${unReadNum }</span>
		    	</c:if>
		    </i>
		    <p>信息</p>
	    </a>
		<a href="#" class="tab-link" id="atab2" external><i class="icon-contact contacts"></i><p>通讯录</p></a>
		<a href="#" class="tab-link" id="atab3" external><i class="icon-contact group"></i><p>群组</p></a>
     </div>
	  <!-- 这里是页面内容区 -->
	  <div class="tabs">
	  	  <!-- 最近联系人 -->
	      <div id="tab1" class="tab active">
				<div class="j-refreshPanel">
					<ul class="comm-list" id="comm-contact-list">
						
					</ul>
				</div>
				<!-- 加载提示符 -->
			    <div class="infinite-scroll-preloader" id="comm-contact-loading">
			        <div class="preloader"></div>
			    </div>
	      </div>
	      
	  	  <!-- 通讯录 -->
	      <div id="tab2" class="tab">
			  	 <div class="con-search">
					<a href="javascript:;" class="input imitateCon j-showSearch"><i class="icon-msg-search"></i>搜索</a>
			    </div>
			    <section class="friendList j-friendList" id="chat-contact-ul">
					
				</section>
				<!-- 加载提示符 -->
			    <div class="infinite-scroll-preloader" id="chat-contact-loading">
			        <div class="preloader"></div>
			    </div>
	      </div>
	      
	      <!-- 群组 -->
	      <div id="tab3" class="tab">
	        	<div class="j-refreshPanel">
					<div class="con-add-group">
						<a href="<c:url value="/weixin/workbench/chatgroup/addOrUpdateInit"/>" class="input" external>
							<i class="icon_m iconfont_m">&#xe609;</i>创建群组
						</a>
				    </div>
					<ul class="comm-list" id="group_list_ul">
						
					</ul>
				</div>
	      </div>
	</div>
</div>	
<section id="j-searchMain">
	<div class="con-search" >
		<div class="realCon">
			<div class="inputPn">
				<input type="text" placeholder="搜索" class="input bdbox j-conSearchIpt" external>
				<i class="icon-msg-search"></i>
				<em class="clearTxt j-clearTxt">×</em>
			</div>
			<a href="javascript:;" class="cancel j-hideSearch">取消</a>
		</div>
    </div>
	<div class="commMask commMask-search result" id="j-searchMask">
		<p class="txtCenter noResult j-noResult">对不起，无搜索结果</p>
	
		<ul class="comm-list comm-list-friendList" id="seach-friend-result">
						
		</ul>
	</div>
</section>
</div>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/util/util.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/search/search.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/acconding_org.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/contact/contact.js'></script>

<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/websocket/sockjs-0.3.4.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/websocket/stomp.js'></script>
<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/connect.js'></script>
<script type="text/javascript">
	var current_employee_id = "${employeeId}";
	var websocket_server_domain = "${webSocketPushCenterUrl}";
</script>
</body>
</html>