<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.cnyirui.framework.utils.CurrentUserUtils"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>我的工作台</title>
</head>
<body>

	<div class="page" id="main-page">

		<div class="content">
			<div class="home_page shadow header_bg">
				<div class="grid-demo">
					<div class="row">

						<div class="header_img con_flexbox">
							<div class="sitting">
								<a href="${contextPath}/weixin/workbench/personal" external><i class="iconfont_m">&#xe659;</i></a>
							</div>
							<div class="logo_images">
								<img src="${weiXinUser.headImgUrl}" />
							</div>
							<div class="con_flexbox-h">
							<h1 id="comLoginName" >${employee.name}</h1>
							<div class="con_flexbox" style="width: 95%;">
								<p >${employee.sysRoleTypeText }|</p>
								<p class="con_flexbox-h con_ellipsis">${employee.organization.name }</p>
								
							</div>
							</div>
						</div>


						<c:if test="${isDgy == true || isDgy == 'true'}">
							<div class="heardr_text">
								<div class="header_left">
									<div class="f14">本月完成</div>
										<div class="f18">
											<fmt:formatNumber value="${countMonth}" pattern="#,##0.#" />(元)
										</div>
								</div>
								<div class="header_right">
									<div class="f14">年度完成</div>
										<div class=f18>
											<fmt:formatNumber value="${countYear}" pattern="#,##0.#" />(元)
										</div>
								</div>
								<div class="clear"></div>
							</div>
						</c:if>

					</div>

				</div>

			</div>

			<div class="content_body">
				<div class="content-padded grid-demo">
					<div class="row index_icon">
						<ul>
							<c:forEach items="<%=CurrentUserUtils.getSysMenuTreeList()%>" var="parent">
								<c:if test="${parent.hasChildren()}">
									<c:forEach items="${parent.children}" var="child" varStatus="status">
										<li><a href="${contextPath}${child.attributes}" external><i class="iconfont_m ${child.iconCls}" style="position: relative;"> <c:if test="${child.text == '消息' && unReadNum > 0}">
														<div id="index_unread_num" style="position: absolute; top: 0; right: 0;">
															<b class="num">${unReadNum }</b>
														</div>
													</c:if>
											</i><span>${child.text}</span></a></li>

									</c:forEach>
								</c:if>
							</c:forEach>
							<li><a href="${contextPath}/weixin/workbench/login/logout" external><i class="iconfont_m icon-tuichu"></i><span>退出系统</span></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/websocket/sockjs-0.3.4.js'></script>
	<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/websocket/stomp.js'></script>
	<script type='text/javascript' src='${contextPath}/static/wxworkbench/js/chat/connect.js'></script>


	<script type="text/javascript">
		var current_employee_id = "${employee.id}";
		var websocket_server_domain = "${webSocketPushCenterUrl}";
		$(function() {
			/**
			 *建立websocket连接
			 */
			connect();
		})
	</script>
</body>
</html>