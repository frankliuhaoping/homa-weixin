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
				<label>登录名称 </label><input class="easyui-textbox" name="search.loginName_like">
				<label>真实姓名 </label><input class="easyui-textbox" name="search.realName_like">
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
					field : 'loginName',
					title : '登录名称',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'realName',
					title : '真实姓名',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'isAdmin',
					title : '是否管理员',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'isDisabled',
					title : '是否屏蔽',
					width : 15,
					sortable : true,
					exportable : true,
				} ] ],
			};
			
			var options = {
					formDialog : {
						title : '${controllerConfig.pageTitle}',	
						onOpen : function(dialog, action) {
							dialog.find('input[textboxname="loginName"]').textbox('textbox').focus();
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