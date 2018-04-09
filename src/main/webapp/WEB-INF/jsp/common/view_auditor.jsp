<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<form id="auditorForm" class="easyui-form">
		<div class="grid_g margin-bottom-10">
			<div class="grid_1">&nbsp;</div>		
			<div class="grid_4 align-right height-30"><label>添加人</label></div>
			<div class="grid_10"><input class="easyui-textbox" name="createdBy.realName" data-options="width: '100%', disabled: true"></div>
			<div class="grid_1"></div>					
			<div class="clear"></div>
		</div>

		<div class="grid_g margin-bottom-10">
			<div class="grid_1">&nbsp;</div>		
			<div class="grid_4 align-right height-30"><label>添加时间</label></div>
			<div class="grid_10"><input class="easyui-textbox" name="createdTime" data-options="width: '100%', disabled: true"></div>
			<div class="grid_1"></div>					
			<div class="clear"></div>
		</div>		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_1">&nbsp;</div>		
			<div class="grid_4 align-right height-30"><label>最后修改人</label></div>
			<div class="grid_10"><input class="easyui-textbox" name="lastModifiedBy.realName" data-options="width: '100%', disabled: true"></div>
			<div class="grid_1"></div>					
			<div class="clear"></div>
		</div>

		<div class="grid_g">
			<div class="grid_1">&nbsp;</div>		
			<div class="grid_4 align-right height-30"><label>最后修改时间</label></div>
			<div class="grid_10"><input class="easyui-textbox" name="lastModifiedTime" data-options="width: '100%', disabled: true"></div>
			<div class="grid_1"></div>					
			<div class="clear"></div>
		</div>				
	</form>
</div>
<div class="space-10"></div>