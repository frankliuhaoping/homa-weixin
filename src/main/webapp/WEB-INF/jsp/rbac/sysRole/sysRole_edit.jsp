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
			<div class="grid_1">&nbsp;</div>		
			<div class="grid_2 align-right height-30"><label><span class="red">*&nbsp;</span>名称</label></div>
			<div class="grid_12"><input class="easyui-textbox" name="name" value="${model.name}" data-options="width: '100%', required: true, missingMessage : '请输入名称！',"></div>
			<div class="grid_1"></div>					
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_1">&nbsp;</div>		
			<div class="grid_2 align-right height-30"><label>默认</label></div>
			<div class="grid_10">
				<select class="easyui-combobox" name="code" data-options="width: '100%', editable: false">   
				    <option value=""></option>
				    <c:forEach items="${sysRoleTypes}" var="sysRoleType">
				    	<option value="${sysRoleType.value}">${sysRoleType.text}</option>
				    </c:forEach>
				</select>
			</div>
			<div class="grid_2 height-30"><label>角色</label></div>			
			<div class="grid_1"></div>					
			<div class="clear"></div>
		</div>		
		
		<div class="grid_g">
			<div class="grid_1">&nbsp;</div>		
			<div class="grid_2 align-right height-30"><label>备注</label></div>
			<div class="grid_12"><input class="easyui-textbox" name="remark" value="${model.remark}" data-options="width: '100%', height: 100, multiline: true"></div>
			<div class="grid_1"></div>
			<div class="clear"></div>
		</div>							
	</form>
</div>
<div class="space-10"></div>