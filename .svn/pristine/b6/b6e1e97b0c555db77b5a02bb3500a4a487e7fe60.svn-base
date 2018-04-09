<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} ${controllerConfig.pageTitle}" />
<title>${webAppTitle}-${controllerConfig.pageTitle}</title>
</head>
<body>
    <table id="datagrid-list"></table>
    <div id='datagrid-toolbar' class="datagrid-toolbar">
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
			<c:if test="${controllerConfig.hasEditPermission()}">
				<a class="datagrid-btn-separator no-float"></a>
				<a href="#" class="easyui-linkbutton" onclick="crud.moveRow('up');return false;" data-options="iconCls:'icon-arrow-up',plain:true" title="上移一行">上移</a>		
				<a href="#" class="easyui-linkbutton" onclick="crud.moveRow('down');return false;" data-options="iconCls:'icon-arrow-down',plain:true" title="下移一行">下移</a>
			</c:if>        
	        <c:if test="${controllerConfig.hasExportPermission()}">
	        	<a class="datagrid-btn-separator no-float"></a>	        
	        	<a href="#" class="easyui-linkbutton" id="exportBtn" onclick="crud.exportToExcel();return false;" data-options="iconCls:'icon-export-to-excel',plain:true">导出</a>
	        </c:if>
	        <a class="datagrid-btn-separator no-float"></a>	        
	       	<a href="#" class="easyui-linkbutton" id="viewAuditorBtn" onclick="crud.viewAuditor();return false;" data-options="iconCls:'icon-information',plain:true">记录信息</a>
        </div>
        <div class="searchForm">
			<form id="searchForm" class="easyui-form">
				<label>菜单名称 </label><input class="easyui-textbox" name="search.name_like">
				<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a>	            			
				<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
			</form>            
        </div>        
    </div>
	<div id="datagrid-row-toolbar" class="hidden">
    	<%@ include file="/WEB-INF/jsp/common/datagrid_row_toolbar_browse_buttons.jsp"%>
	</div>
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script>
		var urlPrefix = "${controllerConfig.urlPrefix}"
	</script>
	<script src="${contextPath}/static/js/backend/weiXinMenu.js"></script>
</body>
</html>