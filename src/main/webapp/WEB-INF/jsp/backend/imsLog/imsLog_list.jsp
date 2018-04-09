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
	        <a href="#" class="easyui-linkbutton" id="refreshBtn" onclick="crud.reload();return false;" data-options="iconCls:'icon-reload',plain:true">刷新</a>
	        <c:if test="${controllerConfig.hasExportPermission() || controllerConfig.hasImportPermission() }">
	        	<a class="datagrid-btn-separator no-float"></a>
	        </c:if>	        
	        <c:if test="${controllerConfig.hasExportPermission()}">	        
	        	<a href="#" class="easyui-linkbutton" id="exportBtn" onclick="crud.exportToExcel();return false;" data-options="iconCls:'icon-export-to-excel',plain:true">导出</a>
	        </c:if>
			<a class="datagrid-btn-separator no-float"></a>	        
	        <div style="float:right">
		        <a href="#" class="easyui-linkbutton" id="synProduct">下载产品数据</a>	        
		        <a href="#" class="easyui-linkbutton" id="synOrganization">下载组织架构数据</a>	        
		        <a href="#" class="easyui-linkbutton" id="synEmployee">下载员工数据</a>	        
		        <a href="#" class="easyui-linkbutton" id="synEmployeeWage">下载员工工资数据</a>	        
		        <a href="#" class="easyui-linkbutton" id="synSalesOrder">上报销量</a>
	        </div>	        
        </div>
        <div class="searchForm">
			<form id="searchForm" class="easyui-form">
				<label>同步时间 </label><input class="easyui-datebox" name="search.createdTime_gte" data-options="width: 120"> 至 <input class="easyui-datebox" name="search.createdTime_lte" data-options="width: 120">
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
					field : 'createdTime',
					title : '同步时间',
					width : 15,
					sortable : true,
					exportable : true,
				}, {
					field : 'content',
					title : '日志内容',
					width : 40,
					exportable : true,
				}] ],
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
			
			$('#synProduct').on('click', function() {
				base.progress();
				$.post(contextPath+ '/backend/imsLog/synProduct', function() {
					afterSyn();
					base.alert('下载产品数据任务已提交，请稍后查看日志记录！');
				});
			});
			
			$('#synOrganization').on('click', function() {
				base.progress();
				$.post(contextPath+ '/backend/imsLog/synOrganization', function() {
					afterSyn();
					base.alert('下载组织架构数据任务已提交，请稍后查看日志记录！');
				});
			});
			
			$('#synEmployee').on('click', function() {
				base.progress();
				$.post(contextPath+ '/backend/imsLog/synEmployee', function() {
					afterSyn();
					base.alert('下载组员工数据任务已提交，请稍后查看日志记录！');
				});
			});
			
			$('#synSalesOrder').on('click', function() {				
				base.dialog({
					id : 'selectYearMonthDialog',
					title : '选择年月',
					href : contextPath + '/backend/common/selectYearMonth',
				}, null, false, null, {
					text : '上报销量',
					handler : function() {
						base.progress();
						var year = $('#cbSelectYear').combobox('getValue');
						var month = $('#cbSelectMonth').combobox('getValue');
						$.post(contextPath+ '/backend/imsLog/synSalesOrder', {'year' : year, 'month' : month}, function() {
							afterSyn();
							$('#selectYearMonthDialog').dialog('close');
							base.alert('上报销量任务已提交，请稍后查看日志记录！');
						});						
					}
				});				
			});	
			
			$('#synEmployeeWage').on('click', function() {
				base.dialog({
					id : 'selectYearMonthDialog',
					title : '选择年月',
					href : contextPath + '/backend/common/selectYearMonth',
				}, null, false, null, {
					text : '下载工资数据',
					width : 150,
					handler : function() {
						base.progress();
						var year = $('#cbSelectYear').combobox('getValue');
						var month = $('#cbSelectMonth').combobox('getValue');						
						$.post(contextPath+ '/backend/imsLog/synEmployeeWage', {'year' : year, 'month' : month}, function() {
							afterSyn();
							$('#selectYearMonthDialog').dialog('close');
							base.alert('下载员工工资数据已经完成，请稍后查看日志记录！');
						});											
					}
				});						

			});		
			
			function afterSyn(){
				base.progress('close');
				crud.reload();
				setTimeout(function(){
					crud.selectRow(0);
				}, 200);
			}			
		});		
	</script>
</body>
</html>