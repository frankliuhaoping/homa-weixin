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
    	<%@ include file="/WEB-INF/jsp/common/datagrid_toolbar_buttons.jsp"%>
        <div class="searchForm">
			<form id="searchForm" class="easyui-form">
				<label>实体类类名 </label><input class="easyui-textbox" name="search.entityClassName_like">
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
		$(function() {
			var dataGridOptions = {
				columns : [ [ {
					field : 'entityClassName',
					title : '实体类类名',
					width : 20,
					sortable : true,
					exportable : true,
				}, {
					field : 'entityClassDesc',
					title : '实体类描述',
					width : 20,
					sortable : true,
					exportable : true,
				}, {
					field : 'cRUDUrl',
					title : 'URL',
					width : 30,
					sortable : true,
					exportable : true,
				}, {
					field : 'displayCode',
					title : '显示编码',
					width : 10,
					sortable : true,
					exportable : true,
				}, {
					field : 'displayName',
					title : '显示名称',
					width : 10,
					sortable : true,
					exportable : true,
				}, {
					field : 'displayRemark',
					title : '显示备注',
					width : 10,
					sortable : true,
					exportable : true,
				} ] ],
			};
	
			var options = {
				formDialog : {
					width : 600,
					title : '${controllerConfig.pageTitle}',
	
					onOpen : function(dialog, action) {
						dialog.find('input[textboxname="entityClassName"]').textbox('textbox').focus();
					},
				},
				
				url : {
					urlPrefix : contextPath + '/standardSetup/standardSetupConfig/',
				}
			}
			
			crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);
		});		
	</script>
</body>
</html>