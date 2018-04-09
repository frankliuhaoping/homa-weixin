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
    <table id="datagrid-listOne"></table>
    <div id='datagrid-toolbarOne' class="datagrid-toolbar">
    	<div class="toolbar-buttons">
	        <a href="#" class="easyui-linkbutton" id="refreshBtn" onclick="pCrud.reload();return false;" data-options="iconCls:'icon-reload',plain:true">刷新</a>
	        <a class="datagrid-btn-separator no-float"></a>
	        <c:if test="${controllerConfig.hasExportPermission()}">	        
	        	<a href="#" class="easyui-linkbutton" id="exportBtn" onclick="pCrud.exportToExcel();return false;" data-options="iconCls:'icon-export-to-excel',plain:true">导出</a>
	        	<a class="datagrid-btn-separator no-float"></a>
	        </c:if>	        
        </div>
    </div>
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script>
		$(function() {
			var dataGridOptions = {
				pagination : false,
				toolbar : '#datagrid-toolbarOne',
				queryParams : {
					'search.weiXinUser.id_eq' : '${weiXinUser.id}'
				},					
				columns : [ [{
					field : 'createdTime',
					title : '时间',
					width : 30,
					sortable : true,
					exportable : true,
				}, {
					field : 'isSubscribe',
					title : '类型',	
					width : 30,
					sortable : true,
					exportable : true,
				}] ],
			};
			
			var options = {
					dataGridId : '#datagrid-listOne',
					rowToolbarId : null,
					addCkColumn : false,
					formDialog : {
						title : '${controllerConfig.pageTitle}',
					},
					
					url : {
						urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
					}
				};
			
			pCrud = ryUILib.EasyuiDataGrid(options, dataGridOptions);			
		});		
	</script>
</body>
</html>