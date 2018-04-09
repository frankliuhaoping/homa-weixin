<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<form id="editForm" method="post">
		<input type="hidden" name="id" value="${model.id}">
		<input type="hidden" name="version" value="${model.version}">
		<input type="hidden" name="action" value="">		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>同步时间</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="createdTime" value="${model.createdTime}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>		

		<div class="grid_g">
			<div class="grid_4 align-right height-30"><label>日志内容</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="content" value="${model.content}" data-options="width: '100%', multiline: true, height: 300"></div>
			<div class="clear"></div>
		</div>			
	</form>
</div>
<div class="space-10"></div>