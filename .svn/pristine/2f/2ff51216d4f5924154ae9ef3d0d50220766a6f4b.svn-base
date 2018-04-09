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
	<table id="datagrid-sysPermission-list"></table>
	<div id='datagrid-sysPermission-toolbar' class="datagrid-toolbar">
		<div class="toolbar-buttons">
			<a href="#" class="easyui-linkbutton" onclick="pCrud.reload();return false;" data-options="iconCls:'icon-reload',plain:true">刷新</a>
			<c:if test="${not empty sysMenu.name}">
		        <c:if test="${controllerConfig.hasAddPermission() || controllerConfig.hasDelPermission() }">
		        	<a class="datagrid-btn-separator no-float"></a>
		        </c:if>			
				<c:if test="${controllerConfig.hasAddPermission()}">
					<a href="#" class="easyui-linkbutton" onclick="pCrud.add();return false;" data-options="iconCls:'icon-add',plain:true">添加</a>
				</c:if>
				<c:if test="${controllerConfig.hasDelPermission()}">
					<a href="#" class="easyui-linkbutton" onclick="pCrud.del();return false;" data-options="iconCls:'icon-remove',plain:true">批量删除</a>
				</c:if>
				<c:if test="${controllerConfig.hasEditPermission()}">
					<a class="datagrid-btn-separator no-float"></a>
					<a href="#" class="easyui-linkbutton" onclick="pCrud.moveRow('up');return false;" data-options="iconCls:'icon-arrow-up',plain:true" title="上移一行">上移</a>		
					<a href="#" class="easyui-linkbutton" onclick="pCrud.moveRow('down');return false;" data-options="iconCls:'icon-arrow-down',plain:true" title="下移一行">下移</a>
				</c:if>
			</c:if>
			<c:if test="${controllerConfig.hasExportPermission()}">
				<a class="datagrid-btn-separator no-float"></a>			
				<a href="#" class="easyui-linkbutton" id="exportBtn" onclick="pCrud.exportToExcel();return false;" data-options="iconCls:'icon-export-to-excel',plain:true">导出</a>
			</c:if>
			<a class="datagrid-btn-separator no-float"></a>
			<a href="#" class="easyui-linkbutton" onclick="pCrud.viewAuditor();return false;" data-options="iconCls:'icon-information',plain:true">记录信息</a>
		</div>   		
		<div class="searchForm">
			<c:if test="${not empty sysMenu.name}">
				<h4>当前菜单：${sysMenu.name}</h4>
			</c:if>    
			<c:if test="${empty sysMenu.name}">
				<form id="searchForm" class="easyui-form">
					<label>菜单名称 </label><input class="easyui-textbox" name="search.sysMenu.name_like">
					<a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search', width: 80" onclick="pCrud.search();return false;">查询</a>	            			
					<a href="#" class="easyui-linkbutton" data-options="width: 80" onclick="pCrud.resetSearchForm();return false;">重置</a>
				</form>            
			</c:if>			
		</div> 
		
	</div>
	<c:if test="${not empty sysMenu.name}">
		<div id="datagrid-sysPermission-row-toolbar" class="hidden">
			<div id="datagrid-sysPermission-row-toolbar-browse-row.index">
				<c:if test="${controllerConfig.hasEditPermission()}">
					<a href="#" class="easyui-linkbutton" title="修改当前记录" onclick="pCrud.edit(row.id, row.index);return false;" data-options="iconCls:'icon-edit', plain:true"></a>
				</c:if>
				<c:if test="${controllerConfig.hasDelPermission()}">
					<a href="#" class="easyui-linkbutton" title="删除当前记录" onclick="pCrud.del(row.id, row.index);return false;" data-options="iconCls:'icon-remove', plain:true"></a>
				</c:if>
			</div>   
			<c:if test="${controllerConfig.hasEditPermission()}">
				<div id="datagrid-sysPermission-row-toolbar-save-row.index" style="display:none" >
					<a href="#" class="easyui-linkbutton" title="保存当前记录" onclick="pCrud.saveRow();return false;" data-options="iconCls:'icon-save', plain:true"></a>
					<a href="#" class="easyui-linkbutton" title="取消修改" onclick="pCrud.cancelRow();return false;" data-options="iconCls:'icon-undo', plain:true"></a>
				</div>
			</c:if>
		</div>
	</c:if>
	<!-- <script src="${contextPath}/static/js/easyuiDataGrid.js"></script>  -->
	<script>
		$(function() {	
			if (typeof ryUILib == 'undefined') {
				base.loadJs(contextPath + '/static/js/easyuiDataGrid.js');
			}

			var sortName = '${empty sysMenu.name ? "sysMenu.id,seq,id" : "seq"}';
			var pagination = '${empty sysMenu.name ? "true" : "false"}';
			if (sortName == 'null') {
				sortName = null;
			}
			if (pagination == 'false') {
				pagination = false;
			} else {
				pagination = true;
			}
			

			var permissions = [ {
				"value" : "view",
				"text" : "查看"
			}, {
				"value" : "add",
				"text" : "添加"
			}, {
				"value" : "edit",
				"text" : "修改"
			}, {
				"value" : "del",
				"text" : "删除"
			}, {
				"value" : "import",
				"text" : "导入"
			}, {
				"value" : "export",
				"text" : "导出"
			}];
		
			var dataGridOptions = {
				sortName : sortName,
				pagination : pagination,
				toolbar : '#datagrid-sysPermission-toolbar',
				queryParams : {
					'search.sysMenu.id_eq' : '${sysMenu.id}'
				},
				onBeginEdit : function(index, row) {
					var nameEditor = $(this).datagrid('getEditor', {
						index : index,
						field : 'name'
					});
					var descriptionEditor = $(this).datagrid('getEditor', {
						index : index,
						field : 'description'
					});
					var permissionValueEditor = $(this).datagrid('getEditor', {
						index : index,
						field : 'permissionValue'
					});

					
					$(nameEditor.target).combobox({
						value : row.name,
						onChange : function(newValue, oldValue) {
							var permissionValue = ""; 
							$.each(permissions, function(){
								if (this.text == newValue) {
									permissionValue = this.value;
									return false;
								}
							});
							$(descriptionEditor.target).textbox('setValue', '${sysMenu.name} - ' + newValue);
							if (permissionValue != '' || row.permissionValue == '') {
								$(permissionValueEditor.target).textbox('setValue', '${sysMenu.permissionCode}:' + permissionValue);	
							}
							
						}
					});

				},
				columns : [ [ {
					field : 'sysMenu.id',
					hidden : true,
				}, {
					field : 'sysMenu.name',
					title : '菜单',
					width : 25,
					resizable : true,
					exportable : true,
				}, {
					field : 'name',
					title : '名称',
					width : 10,
					resizable : true,					
					exportable : true,
					editor : {
						type : 'combobox',
						options : {
							height : 39,
							required : true,
							missingMessage : '请输入名称！',
							data: permissions, 
							valueField: "text", 
							textField: "text",
							allowNonExists: true
						}
					}
				}, {
					field : 'description',
					title : '描述',
					width : 30,
					resizable : true,					
					exportable : true,
					editor : {
						type : 'textbox',
						options : {
							height : 39,
						}
					}
				}, {
					field : 'permissionValue',
					title : '权限值',
					width : 25,
					resizable : true,
					exportable : true,
					editor : {
						type : 'textbox',
						options : {
							height : 39,
							required : true,
							missingMessage : '请输入权限值！',
						}
					}
				}] ],
			};

			var options = {
				dataGridId : '#datagrid-sysPermission-list',
				rowToolbarId : '#datagrid-sysPermission-row-toolbar',
				editMode : 'row',
				url : {
					urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
					sequenceUrl : contextPath + '${controllerConfig.urlPrefix}sequence',
				},
				swapSeq : false,
				edatagrid : {
					defaultRow : function() {
						var row = {};
						row['sysMenu.id'] = '${sysMenu.id}';
						row['sysMenu.name'] = '${sysMenu.name}';
						row['permissionValue'] = '${sysMenu.permissionCode}' != '' ? '${sysMenu.permissionCode}:' : '';
						return row;
					},

				}
			}

			pCrud = ryUILib.EasyuiDataGrid(options, dataGridOptions);
		});
	</script>
</body>
</html>