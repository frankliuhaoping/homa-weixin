$(function() {

	var dataGridOptions = {
		pagination : false,
		treeField : 'name',
		columns : [ [ {
			field : 'name',
			title : '名称',
			width : 30,
			sortable : true,
			exportable : true,
		}, {
			field : 'url',
			title : 'URL',
			width : 30,
			sortable : true,
			exportable : true,
		}, {
			field : 'icon',
			title : '图标',
			width : 25,
			sortable : false,
			exportable : true,
		}, {
			field : 'useOf',
			title : '用途',
			width : 10,
			sortable : false,
			exportable : true,
		}, {
			field : 'remark',
			title : '备注',
			width : 15,
			sortable : false,
			exportable : true,
		} ] ],
	};

	var options = {
		rowToolbarFormatter : function(toolbar, value, row, index) {
			if (row._parentId == 0 || row._hasChildren) {
				toolbar.find('#sysPermissionCrudBtn_' + index).remove();
			}
			return toolbar.html();
		},

		formDialog : {
			title : '菜单管理',

			onOpen : function(dialog, action) {
				dialog.find('input[textboxname="name"]').textbox('textbox').focus();
				if (action != 'add') {
					$('#defaultPermissionDiv').remove();
				}
			},

			onSubmit : function(form, action) {
				if (action == 'add') {
					var permissionCode = $('#permissionCodeDiv .easyui-textbox');
					if ($('#defaultPermissionDiv input:checked').length > 0 && permissionCode.textbox('getValue') == "") {
						base.alert('请输入权限代码！', '', function() {
							permissionCode.textbox('textbox').focus();
						})
						return false;
					}
				}
				return true;
			},
		},

		url : {
			urlPrefix : contextPath + urlPrefix,
			sequenceUrl : contextPath + urlPrefix + 'sequence',
		}
	};

	crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

});

function sysPermissionCrud(id) {
	base.dialog({
		title : '菜单权限管理',
		width : 800,
		height : 500,
		href : contextPath + '/rbac/sysPermission/list?sysMenuId=' + id,
		onBeforeClose : function() {
			var dialog = this;
			if (pCrud.isEditing) {
				base.confirm('数据尚未保存，是否保存数据后再关闭窗体？', null, function() {
					pCrud.saveRow();
					setTimeout(function() {
						$(dialog).dialog('close');
					}, 300);
				});
			}
			return !pCrud.isEditing;
		},
	});
}
