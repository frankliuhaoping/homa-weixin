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
			<div class="grid_4 align-right height-30"><label>名称</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="dgname" value="${model.dgname}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>数量</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="sl" value="${model.sl}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>金额</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="je" value="${model.je}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>
		
		
		
		
				
	</form>
</div>
<div class="space-10"></div>