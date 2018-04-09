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
    	<div id="datagrid-row-toolbar-browse-row.index">
    		<c:if test="${controllerConfig.hasViewPermission()}">
	        	<a href="#" class="easyui-linkbutton" title="查看当前记录" id="viewRowBtn_row.index" onclick="crud.view(row.id, row.index);return false;" data-options="iconCls:'icon-view', plain:true"></a>
	        </c:if>
	        <c:if test="${controllerConfig.hasEditPermission()}">
		        <a href="#" class="easyui-linkbutton" title="修改当前记录" id="editRowBtn_row.index" onclick="crud.edit(row.id, row.index);return false;" data-options="iconCls:'icon-edit', plain:true"></a>
		        <a href="#" class="easyui-linkbutton" title="角色授权" id="showAuthorizationForm_row.index" onclick="showAuthorizationForm(row.id);return false;" data-options="iconCls:'green fa fa-key fa-lg', plain:true"></a>
	        </c:if>
	        <c:if test="${controllerConfig.hasDelPermission()}">
	        	<a href="#" class="easyui-linkbutton" title="删除当前记录" id="delRowBtn_row.index" onclick="crud.del(row.id, row.index);return false;" data-options="iconCls:'icon-remove', plain:true"></a>
	        </c:if>
        </div>
   </div>
   
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script>
		$(function() {
			var dataGridOptions = {
				columns : [ [ {
					field : 'name',
					title : '名称',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'code',
					title : '默认角色',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'remark',
					title : '备注',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'isMetaData',
					hidden : true
				} ] ],
			};
	
			var options = {
				rowToolbarFormatter : function(toolbar, value, row, index) {
					if (row.isMetaData == true) {
						toolbar.find('#editRowBtn_' + index).remove();
						toolbar.find('#delRowBtn_' + index).remove();
					}
					return toolbar.html();
				},
	
				formDialog : {
					title : '${controllerConfig.pageTitle}',
	
					onOpen : function(dialog, action) {
						dialog.find('input[textboxname="name"]').textbox('textbox').focus();
					},
				},
				
				url : {
					urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
				}
			}
			
			crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);
		});	
		
		function showAuthorizationForm(id) {			
			base.dialog({
				id: 'authorizationFormDialog',
				title : '角色授权',
				width : 700,
				height : 500,
				href : contextPath + '/rbac/sysRole/showAuthorizationForm/' + id,
				buttons : [ {
					text : '保存',
					height : 35,
					width : 100,
					iconCls : 'icon-save',
					handler : function() {
						var sysPermissionIds = '';
						$('#datagrid-sysPermission-list').treegrid('getPanel').find('td input:checkbox:checked').each(function(){
							sysPermissionIds += $(this).val() + ',';
						});
						if (sysPermissionIds != ''){
							sysPermissionIds = sysPermissionIds.substring(0, sysPermissionIds.length - 1);
						}
						$.post(contextPath + '/rbac/sysRole/saveAuthorizationForm/' + id, 'sysPermissionIds=' + sysPermissionIds, function(data){
							if (data.isError){
								base.alert(data.message);
							}else{
								$('#authorizationFormDialog').dialog('close');
							}
						});
					}
				}],
			});
		}		
	</script>
</body>
</html>