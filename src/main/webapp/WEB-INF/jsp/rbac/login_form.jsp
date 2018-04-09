<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<form id="loginForm" method="post">
		<div class="grid_g margin-bottom-10">
			<div class="grid_2">&nbsp;</div>		
			<div class="grid_12"><h5 class="red">${message}</h5></div>
			<div class="grid_2"></div>					
			<div class="clear"></div>
		</div>
	
		<div class="grid_g margin-bottom-10">
			<div class="grid_2">&nbsp;</div>		
			<div class="grid_12">
				<input class="easyui-textbox" name="username" id="username" value="admin" 
					data-options="prompt:'用户名', iconCls:'icon-man', iconWidth:38, width: '100%', required: 'true', missingMessage : '请输入用户名！'">			
			</div>
			<div class="grid_2"></div>					
			<div class="clear"></div>
		</div>
		
		<div class="grid_g">
			<div class="grid_2">&nbsp;</div>		
			<div class="grid_12">
				<input class="easyui-textbox" type="password" name="password" id="password" value="admin" 
					data-options="prompt:'密码', iconCls:'icon-lock', iconWidth:38, width: '100%', required: 'true', missingMessage : '请输入密码！'">
				<input type="hidden" value="loginDialog" name="loginDialog">		
			</div>
			
			<div class="grid_2"></div>					
			<div class="clear"></div>
		</div>	
	</form>
</div>
<div class="space-10"></div>