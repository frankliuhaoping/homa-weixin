<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="description" content="${webAppTitle} ${controllerConfig.pageTitle}" />
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />	
	<title>${webAppTitle}-${controllerConfig.pageTitle}</title>	
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/easyui/themes/default/easyui.css" id="easyui_theme">
    <!-- <link rel="stylesheet" type="text/css" href="${contextPath}/static/easyui/themes/mobile.css">  -->  	
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/icon.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/font-awesome-4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/easyui_reset.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/function.css">			
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/style.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/grid.css">	
	<script>
		var contextPath = "${contextPath}";
		var webAppTitle = "${webAppTitle}";
	</script>			
	<script src="${contextPath}/static/easyui/jquery.min.js"></script>
	<script src="${contextPath}/static/easyui/jquery.easyui.fast.min.js"></script>
	<script src="${contextPath}/static/easyui/plugins/jquery.selecttextbox.js"></script>	
    <!-- <script src="${contextPath}/static/easyui/jquery.easyui.mobile.js"></script>  --> 	
 	<script src="${contextPath}/static/easyui/locale/easyui-lang-zh_CN.js"></script> 		
 	<script src="${contextPath}/static/easyui/extension/validatebox.rules.js"></script> 		
 	<script src="${contextPath}/static/js/easyuiDefault.js"></script>	
	<script src="${contextPath}/static/js/json2.js"></script> 	
	<script src="${contextPath}/static/js/base.js"></script>
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
        <div class="searchForm">
			<form id="searchForm" class="easyui-form">
				<label>名称 </label><input class="easyui-textbox" name="search.name_like">
				<label>负责人</label><input class="easyui-textbox" name="search.director.name_like" style="width: 100px">
				<input type="hidden"  name="search.deleted_eq" value="0">
				<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="crud.search();return false;">查询</a>	            			
				<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="crud.resetSearchForm();return false;">重置</a>
			</form>            
        </div>        
    </div>
	<div id="datagrid-row-toolbar" class="hidden">
    	<div id="datagrid-row-toolbar-browse-row.index">
    		<c:if test="${controllerConfig.hasViewPermission()}">
	        	<a href="#" class="easyui-linkbutton" title="查看当前记录" id="viewRowBtn_row.index" onclick="crud.view(row.id, row.index);return false;" data-options="iconCls:'icon-view', plain:true"></a>
	        </c:if>
	        <c:if test="${controllerConfig.hasEditPermission()}">
	        	<a href="#" class="easyui-linkbutton" title="修改当前记录" id="editRowBtn_row.index" onclick="crud.edit(row.id, row.index);return false;" data-options="iconCls:'icon-edit', plain:true"></a>
	        	<a href="#" class="easyui-linkbutton" title="数据查看权限维护" id="organizationPermissionCrudBtn_row.index" onclick="organizationPermissionCrud(row.id);return false;" data-options="iconCls:'green fa fa-key fa-lg', plain:true"></a>	        	
	        </c:if>       
	        <c:if test="${controllerConfig.hasDelPermission()}">
		        <a href="#" class="easyui-linkbutton" title="删除当前记录" id="delRowBtn_row.index" onclick="del(row.id,row.index);return false;" data-options="iconCls:'icon-remove', plain:true"></a>
	        </c:if>
        </div>
	</div>
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script>
		var urlPrefix = "${controllerConfig.urlPrefix}"
	</script>
	<script src="${contextPath}/static/js/backend/organization.js"></script>
	<script src="${contextPath}/static/js/backend/commonSelect.js"></script>	
</body>
</html>