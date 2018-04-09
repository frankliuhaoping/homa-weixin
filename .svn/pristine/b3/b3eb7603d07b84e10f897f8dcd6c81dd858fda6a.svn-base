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
			<div class="grid_3 align-right height-30"><label><span class="red">*&nbsp;</span>实体类类名</label></div>
			<div class="grid_12"><input class="easyui-textbox" name="entityClassName" value="${model.entityClassName}" data-options="width: '100%', required: true, missingMessage : '请输入实体类名称！',"></div>
			<div class="grid_1"></div>					
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_3 align-right height-30"><label><span class="red">*&nbsp;</span>实体类描述</label></div>
			<div class="grid_12"><input class="easyui-textbox" name="entityClassDesc" value="${model.entityClassDesc}" data-options="width: '100%', required: true, missingMessage : '请输入实体类描述！',"></div>
			<div class="grid_1"></div>					
			<div class="clear"></div>
		</div>		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_3 align-right height-30"><label>显示编码</label></div>			
			<div class="grid_12 height-30">
				<input type="radio" class="align-middle" name="displayCode" id="displayCodeYes" value="true" <c:if test="${model.displayCode}">checked="checked"</c:if>> <label for="displayCodeYes">显示</label>
				<input type="radio" class="align-middle" name="displayCode" id="displayCodeNo" value="false" <c:if test="${!model.displayCode}">checked="checked"</c:if>> <label for="displayCodeNo">不显示</label>
			</div>
			<div class="grid_1"></div>
			<div class="clear"></div>
		</div>	
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_3 align-right height-30"><label>显示名称</label></div>			
			<div class="grid_12 height-30">
				<input type="radio" class="align-middle" name="displayName" id="displayNameYes" value="true" <c:if test="${model.displayName}">checked="checked"</c:if>> <label for="displayNameYes">显示</label>
				<input type="radio" class="align-middle" name="displayName" id="displayNameNo" value="false" <c:if test="${!model.displayName}">checked="checked"</c:if>> <label for="displayNameNo">不显示</label>
			</div>
			<div class="grid_1"></div>
			<div class="clear"></div>
		</div>	
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_3 align-right height-30"><label>显示备注</label></div>			
			<div class="grid_12 height-30">
				<input type="radio" class="align-middle" name="displayRemark" id="displayRemarkYes" value="true" <c:if test="${model.displayRemark}">checked="checked"</c:if>> <label for="displayRemarkYes">显示</label>
				<input type="radio" class="align-middle" name="displayRemark" id="displayRemarkNo" value="false" <c:if test="${!model.displayRemark}">checked="checked"</c:if>> <label for="displayRemarkNo">不显示</label>
			</div>
			<div class="grid_1"></div>
			<div class="clear"></div>
		</div>			
	</form>
</div>
<div class="space-10"></div>