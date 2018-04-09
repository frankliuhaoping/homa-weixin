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
        <div class="searchForm">
			<form id="searchForm" class="easyui-form">
				<label>标题查找 </label><input class="easyui-textbox" name="search.title_like">
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
			<c:if test="${controllerConfig.hasDelPermission()}">
				<a href="#" class="easyui-linkbutton" title="删除当前记录" id="delRowBtn_row.index" onclick="crud.del(row.id, row.index);return false;" data-options="iconCls:'icon-remove', plain:true"></a>
			</c:if>
		</div>
	</div>
	
	<script src="${contextPath}/static/js/easyuiDataGrid.js"></script>
	<script src="${contextPath}/static/js/TouchSlide.1.1.js"></script>
    

	
	<script>
		$(function() {
			var dataGridOptions = {
				columns : [ [{
					field : 'title',
					title : '标题',
					width : 6,
					sortable : true,
					exportable : true,
				}, {
					field : 'content',
					title : '内容',
					width : 5,
					sortable : true,
					exportable : true,
				}, {
					field : 'category',
					title : '营销经验分类',
					width : 5,
					sortable : true,
					exportable : true,
				}, {
					field : 'isTop',
					title : '是否置顶显示',
					width : 5,
					sortable : true,
					exportable : true,
				}] ],
			};
			
			var options = {
					formDialog : {
						width : '50%',
						height : '80%',
						dialogId : '#editdialog',
						formId : '#editForm',
						title : '${controllerConfig.pageTitle}',
						onOpen : function(dialog, action) {
						///UE.delEditor('myEditor');
						},
					},					
					url : {
						urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
					}
				};
			
			crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);
			
		});		
	</script>
	<!-- 
	<textarea name="content" id="myEditor"></textarea>
	<script type="text/javascript">
		//UEDITOR_CONFIG.UEDITOR_HOME_URL = './ueditor/';
		UE.getEditor('myEditor');
	</script> -->
	

	
</body>
</html>