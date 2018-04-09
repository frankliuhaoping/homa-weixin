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
				<label>操作时间 </label><input class="easyui-datebox" name="search.createdTime_gte" data-options="width: 120"> 至 <input class="easyui-datebox" name="search.createdTime_lte" data-options="width: 120">
				<label>操作人 </label><input class="easyui-textbox" name="search.createdBy.realName_like" data-options="prompt: '登录帐号或真实姓名'">
				<label>操作内容 </label><input class="easyui-textbox" name="search.description_like">
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
					field : 'createdBy.realName',
					title : '操作人',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'createdTime',
					title : '操作时间',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'ip',
					title : 'IP',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'description',
					title : '操作内容',
					width : 20,
					sortable : true,
					exportable : true,
				}, {
					field : 'params',
					title : '参数',
					width : 35,
					sortable : true,
					exportable : true,
				} ] ],
			};
			
			var options = {
					formDialog : {
						title : '${controllerConfig.pageTitle}',
						width : 500,
						onOpen : function(dialog, action) {
							//dialog.find('input[textboxname="loginName"]').textbox('textbox').focus();
						},
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