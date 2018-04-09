<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

    	<div class="toolbar-buttons">
	        <a href="#" class="easyui-linkbutton" id="refreshBtn" onclick="crud.search();return false;" data-options="iconCls:'icon-reload',plain:true">刷新</a>
	        <c:if test="${controllerConfig.hasAddPermission() || controllerConfig.hasDelPermission() }">
	        	<a class="datagrid-btn-separator no-float"></a>
	        </c:if>
	        <c:if test="${controllerConfig.hasAddPermission() }">
	        	<a href="#" class="easyui-linkbutton" id="addBtn" onclick="crud.add();return false;" data-options="iconCls:'icon-add',plain:true">添加</a>
	        </c:if>
	        <c:if test="${controllerConfig.hasDelPermission()}">
	        	<a href="#" class="easyui-linkbutton" id="batchDelBtn" onclick="crud.del();return false;" data-options="iconCls:'icon-remove',plain:true">批量删除</a>
	        </c:if>
	        <c:if test="${controllerConfig.hasExportPermission() || controllerConfig.hasImportPermission() }">
	        	<a class="datagrid-btn-separator no-float"></a>
	        </c:if>	        
	        <c:if test="${controllerConfig.hasImportPermission()}">	        		        
	        	<a href="#" class="easyui-linkbutton" id="importBtn" onclick="crud.importFromExcel();return false;" data-options="iconCls:'icon-export-to-excel',plain:true">导入</a>
	        </c:if>	        
	        <c:if test="${controllerConfig.hasExportPermission()}">	        
	        	<a href="#" class="easyui-linkbutton" id="exportBtn" onclick="crud.exportToExcel();return false;" data-options="iconCls:'icon-export-to-excel',plain:true">导出</a>
	        </c:if>
	        <a class="datagrid-btn-separator no-float"></a>
	        <a href="#" class="easyui-linkbutton" id="viewAuditorBtn" onclick="crud.viewAuditor();return false;" data-options="iconCls:'icon-information',plain:true">记录信息</a>
        </div>