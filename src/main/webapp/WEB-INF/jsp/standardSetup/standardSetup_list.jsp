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
				<label>名称 </label><input class="easyui-textbox" name="search.name_like">
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
			var dataGridOptions = {columns:[[]]};
			if ('${standardSetupConfig.displayCode}' == 'true') {
				dataGridOptions.columns[0].push({
					field : 'code',
					title : '编号',
					width : 10,
					sortable : true,
					exportable : true,
				});
			}
			if ('${standardSetupConfig.displayName}' == 'true') {
				dataGridOptions.columns[0].push({
					field : 'name',
					title : '名称',
					width : 15,
					sortable : true,
					exportable : true,
				});
			}
			if ('${standardSetupConfig.displayRemark}' == 'true') {
				dataGridOptions.columns[0].push({
					field : 'remark',
					title : '备注',
					width : 20,
					sortable : true,
					exportable : true,
				});
			}			
			dataGridOptions.columns[0].push({
				field : 'isMetaData',
				hidden : true
			});
	
			var options = {
				rowToolbarFormatter : function(toolbar, value, row, index) {
					if ('${sysUser.isAdmin}' == 'false' && row.isMetaData == true) {
						toolbar.find('#editRowBtn_' + index).remove();
						toolbar.find('#delRowBtn_' + index).remove();
					}
					return toolbar.html();
				},
	
				formDialog : {
					title : '${controllerConfig.pageTitle}',
					onOpen : function(dialog, action) {
						var textbox = dialog.find('input[textboxname="name"]');
						if (textbox.length > 0) {
							textbox.textbox('textbox').focus();
						}
						
					},
				},
				
				url : {
					urlPrefix : contextPath + '/standardSetup/${entityClassName}/',
				}
			}
			
			crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);
		});		
	</script>
</body>
</html>