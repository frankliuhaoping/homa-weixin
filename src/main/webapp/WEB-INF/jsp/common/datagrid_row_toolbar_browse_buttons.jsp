<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

		<div id="datagrid-row-toolbar-browse-row.index">
			<c:if test="${controllerConfig.hasViewPermission()}">
				<a href="#" class="easyui-linkbutton" title="查看当前记录" id="viewRowBtn_row.index" onclick="crud.view(row.id, row.index);return false;" data-options="iconCls:'icon-view', plain:true"></a>
			</c:if>
			<c:if test="${controllerConfig.hasEditPermission()}">
				<a href="#" class="easyui-linkbutton" title="修改当前记录" id="editRowBtn_row.index" onclick="crud.edit(row.id, row.index);return false;" data-options="iconCls:'icon-edit', plain:true"></a>
			</c:if>
			<c:if test="${controllerConfig.hasDelPermission()}">
				<a href="#" class="easyui-linkbutton" title="删除当前记录" id="delRowBtn_row.index" onclick="crud.del(row.id, row.index);return false;" data-options="iconCls:'icon-remove', plain:true"></a>
			</c:if>
		</div>