<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cn.cnyirui.framework.utils.CurrentUserUtils"  %>    
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} 首页" />
<title>${webAppTitle}-首页</title>
</head>
<body>
	<!-- top -->
	<div data-options="region:'north', border:false" class="header" id="header">
		<!-- title -->
		<div class="float-left"><h4>${webAppTitle}</h4></div>
		<!-- function -->
		<div class="float-right">
			<label class="align-middle" for="cb-theme"></label>		
			<label class="align-middle" for="cb-theme">更换颜色</label>
			<select id="cb-theme" class="easyui-combobox"></select>			
			<a href="#" class="easyui-linkbutton" onClick="modifyPassword();return false;">修改密码</a>
			<a href="${contextPath}/rbac/login/logout" class="easyui-linkbutton" id="logoutLinkButton">退出系统</a>			
		</div>
	</div>
	<!-- left -->
	<div data-options="region:'west', split:true, title:'当前用户：<%=CurrentUserUtils.getSysUser().getRealName() %>', minWidth: 180, width: 180">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<c:forEach items="<%=CurrentUserUtils.getSysMenuTreeList() %>" var="parent">
				<c:if test="${parent.hasChildren()}">
					<div title="${parent.text}" class="no-padding overfloat-auto" data-options="iconCls:'${parent.iconCls}'" >
						<ul id="${parent.id}" class="easyui-tree" data-options="url: '${contextPath}/rbac/sysMenu/permisssionTreeJson/${parent.id}', onClick: treeNodeClick">
						</ul>						
					</div>				
				</c:if>
			</c:forEach>					
		</div>
	</div>
	<!-- footer -->
	<div data-options="region:'south', border:false" class="footer">
		翼睿科技 2015-2017
	</div>
	<!-- center -->
	<div data-options="region:'center', title:'Center', border:'false', noheader:'true'">
		<div id="contentTab" class="easyui-tabs" data-options="tools:'#tab-tools', fit:true, border:false, onBeforeClose:beforeTabClose, onSelect: onSelectTab"></div>
		<div id="tab-tools">
			<a href="#" class="easyui-linkbutton" title="刷新当前页" data-options="plain:true, iconCls:'icon-reload'" onclick="refreshTab();return false;"></a>
			<a href="#" class="easyui-linkbutton" title="关闭当前页" data-options="plain:true, iconCls:'icon-no'" onclick="closeTab();return false;"></a>
		</div>
	</div>
	<div id="tabsMenu" class="easyui-menu" data-options="width: 160, onClick: tabsMenuClick">
		<div data-options="name: 'refresh', iconCls:'icon-reload'">刷新</div>
		<div data-options="name: 'newWindow'">在新窗口打开</div>
		<div class="menu-sep"></div>
		<div data-options="name: 'close', iconCls:'icon-no'">关闭</div>
		<div data-options="name: 'closeAll'">全部关闭</div>
		<div data-options="name: 'closeOther'">关闭其它</div>
		<div class="menu-sep"></div>
		<div data-options="name: 'closeRight'">右侧全部关闭</div>
		<div data-options="name: 'closeLeft'">左侧全部关闭</div>
	</div>
	<script src="${contextPath}/static/js/rbac/index.js"></script>
</body>
</html>