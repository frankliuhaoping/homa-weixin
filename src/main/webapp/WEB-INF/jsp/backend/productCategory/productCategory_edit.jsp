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
			<div class="grid_4 align-right height-30"><label>分类产品</label></div>			
			<div class="grid_11">
			    <select name="parent.id" class="easyui-combotree" 
			            data-options="url:'${contextPath}/backend/productCategory/treeJson', width: '100%', value: '${model.parent.id}'">
			    </select>			
			</div>
			<div class="clear"></div>
		</div>		
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label><span class="red">*&nbsp;</span>分类名称</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="name" value="${model.name}" data-options="width: '100%', required: true"></div>
			<div class="clear"></div>
		</div>
		
		<div class="grid_g margin-bottom-10">
			<div class="grid_4 align-right height-30"><label>备注</label></div>
			<div class="grid_11"><input class="easyui-textbox" name="remark" value="${model.remark}" data-options="width: '100%', height: 80, multiline: true"></div>
			<div class="clear"></div>
		</div>
				
	</form>
</div>
<div class="space-10"></div>