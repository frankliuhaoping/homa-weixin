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
				<label>顾客姓名 </label><input class="easyui-textbox" name="search.name_like">
				<label>联系方式 </label><input class="easyui-textbox" name="search.telephone_like">
				<label>导购员 </label><input class="easyui-textbox" name="search.saler.name_like">
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
				<a href="#" class="easyui-linkbutton" title="当前顾客关注记录" onclick="showCustomer(row.id);return false;" data-options="iconCls:'icon-search', plain:true"></a>  
			</c:if>
			<c:if test="${controllerConfig.hasDelPermission()}">
				<a href="#" class="easyui-linkbutton" title="删除当前记录" id="delRowBtn_row.index" onclick="crud.del(row.id, row.index);return false;" data-options="iconCls:'icon-remove', plain:true"></a>
			</c:if>
		</div>
	</div>
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script>
	function showCustomer(id) {
		base.dialog({
			title : '顾客关注记录',
			height : 400,
			href : contextPath + '/backend/weiXinUserSubscribeRecord/list?customerId=' + id,
		});
	}
	</script>
	<script>
		$(function() {
			var dataGridOptions = {
				columns : [ [{
					field : 'name',
					title : '顾客姓名',
					width : 10,
					sortable : true,
					exportable : true,
				}, {
					field : 'telephone',
					title : '联系方式',
					width : 10,
					sortable : true,
					exportable : true,
				}, {
					field : 'address',
					title : '通讯地址',
					width : 5,
					sortable : true,
					exportable : true,
				}, {
					field : 'saler.name',
					title : '导购员',
					width : 5,
					sortable : true,
					exportable : true,
				}] ],
			};
			
			var options = {
					addCkColumn : false,
					addIdColumn : false, // 添加ID列
					addVersionColumn : false, // 添加version列
					addRecordInfoColumns : false, // 记录信息列 createdBy.realName等					
					formDialog : {
						title : '${controllerConfig.pageTitle}',

					},					
					url : {
						urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
					}
				};
			
			crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);			
		});		
	</script>
</body>
</html>