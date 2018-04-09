<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="space-10"></div>
<div class="container_16">
	<form id="editForm" method="post">
		<input type="hidden" name="id" value="${model.id}">
		<input type="hidden" name="version" value="${model.version}">
		<input type="hidden" name="action" value="">
		
		<c:if test="${standardSetupConfig.displayCode }">
			<div class="grid_g margin-bottom-10">
				<div class="grid_4 align-right height-30"><label>编号</label></div>
				<div class="grid_11"><input class="easyui-textbox" name="code" value="${model.code}" data-options="width: '100%'"></div>
				<div class="grid_1"></div>					
				<div class="clear"></div>
			</div>
		</c:if>
		
		<c:if test="${standardSetupConfig.displayName }">		
			<div class="grid_g margin-bottom-10">
				<div class="grid_4 align-right height-30"><label><span class="red">*&nbsp;</span>名称</label></div>
				<div class="grid_11"><input class="easyui-textbox" name="name" value="${model.name}" data-options="width: '100%', required: true, missingMessage : '请输入名称！',"></div>
				<div class="grid_1"></div>					
				<div class="clear"></div>
			</div>
		</c:if>
		
		<c:if test="${sysUser.isAdmin }">		
			<div class="grid_g margin-bottom-10">
				<div class="grid_4 align-right height-30"><label><span class="red">*&nbsp;</span>是否元数据</label></div>
				<div class="grid_10 height-30">
					<input type="radio" class="align-middle" name="isMetaData" id="isMetaDataYes" value="true" <c:if test="${model.isMetaData}">checked="checked"</c:if>> <label for="isMetaDataYes">是</label>
					<input type="radio" class="align-middle" name="isMetaData" id="isMetaDataNo" value="false" <c:if test="${!model.isMetaData}">checked="checked"</c:if>> <label for="isMetaDataNo">否</label>
				</div>
				<div class="grid_1"></div>					
				<div class="clear"></div>
			</div>
		</c:if>		
		
		<c:if test="${standardSetupConfig.displayRemark }">		
			<div class="grid_g">
				<div class="grid_4 align-right height-30"><label>备注</label></div>
				<div class="grid_11"><input class="easyui-textbox" name="remark" value="${model.remark}" data-options="width: '100%', height: 100, multiline: true"></div>
				<div class="grid_1"></div>
				<div class="clear"></div>
			</div>		
		</c:if>					
	</form>
</div>
<div class="space-10"></div>