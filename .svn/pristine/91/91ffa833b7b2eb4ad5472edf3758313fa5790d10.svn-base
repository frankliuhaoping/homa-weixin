<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    

<table id="datagrid-permission-list"></table>
<div id='datagrid-permission-toolbar' class="datagrid-toolbar">
	<div class="toolbar-buttons">
		<a href="#" class="easyui-linkbutton" onclick="pCrud.add();return false;" data-options="iconCls:'icon-add',plain:true">添加</a>
       	<a class="datagrid-btn-separator no-float"></a>
	</div>   		
	<div class="searchForm">					
		<div class="container_16 no-margin">
			<div class="grid_g">		
				<div class="grid_3 align-right height-30" ><h5>当前组织架构：</h5></div>		
				<div class="grid_13 height-30"><h5>${organization.parentNames}</h5></div>		
				<div class="clear"></div>
			</div>	
			<div class="grid_g">		
				<div class="grid_3 align-right height-30"><label>负责人：</label></div>
				<div class="grid_5"><input class="easyui-selecttextbox" name="director.id" id="organizationDirector" data-options="value: '${organization.director.id}', text : '${organization.director.name}', buttonIcon: 'icon-search', width: '100%'" ></div>						
				<div class="clear"></div>
			</div>			
		</div>			
	</div>		
</div>
<div id="datagrid-permission-row-toolbar" class="hidden">
	<div id="datagrid-permission-row-toolbar-browse-row.index">
			<a href="#" class="easyui-linkbutton" title="删除当前记录" onclick="pCrud.del(row.id, row.index);return false;" data-options="iconCls:'icon-remove', plain:true"></a>
	</div>   
	<div id="datagrid-permission-row-toolbar-save-row.index" style="display:none" >
		<a href="#" class="easyui-linkbutton" title="保存当前记录" onclick="pCrud.saveRow();return false;" data-options="iconCls:'icon-save', plain:true"></a>
		<a href="#" class="easyui-linkbutton" title="取消修改" onclick="pCrud.cancelRow();return false;" data-options="iconCls:'icon-undo', plain:true"></a>
	</div>
	
</div>

<script>
	$(function() {	
		if (typeof ryUILib == 'undefined') {
			base.loadJs(contextPath + '/static/js/easyuiDataGrid.js');
		}
		var dataGridOptions = {
			pagination : false,
			toolbar : '#datagrid-permission-toolbar',
			columns : [ [ {
				field : 'name',
				title : '可查看数据人员',
				width : 10,
				editor : {					
					type:'selecttextbox',
					options :{
						width: '100%',
						height : 39,
					}
				},
			}] ],
			onBeginEdit : function(index, row) {
				var nameEditor = $(this).datagrid('getEditor', {
					index : index,
					field : 'name'
				});
				if (nameEditor == null) {
					return;
				}
				commonSelect.initSelectEmployee({
					selecttextboxId : nameEditor.target,
					setValueCallback : function(text, value){
						row.id = value;
						row.name = text;
					}
				});				
			}			
		};

		var options = {
			addCkColumn : false,
			dataGridId : '#datagrid-permission-list',
			rowToolbarId : '#datagrid-permission-row-toolbar',
			editMode : 'cell',
			url : {
				//urlPrefix : contextPath + '${controllerConfig.urlPrefix}',
				jsonListUrl : contextPath + '/backend/organization/getOrganizationPermissionJson/${organization.id}'
			},
			edatagrid:{				
				defaultEditField: 'name'
			}
		}

		pCrud = ryUILib.EasyuiDataGrid(options, dataGridOptions);
		setTimeout(function() {
			commonSelect.initSelectEmployee({selecttextboxId : '#organizationDirector'});
		}, 300);		
	});
</script>
