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
			<div class="grid_4 align-right height-30"><label><span class="red">*&nbsp;</span>产品名称</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="name" value="${model.name}" data-options="width: '100%', required: true"></div>
			<div class="clear"></div>
		</div>	
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>产品编码</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="code" value="${model.code}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>产品描述</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="description" value="${model.description}" data-options="width: '100%'"></div>
			<div class="clear"></div>
		</div>
		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label><span class="red">*&nbsp;</span>产品分类</label></div>
			<div class="grid_11"><input id="cc" class="easyui-combobox" name="category.id" data-options="valueField:'id',textField:'name',url:'${contextPath}/backend/product/findProductCategory'" /></div>
			<div class="clear"></div>
		</div>
		
		
	</form>
</div>
<div class="space-10"></div>