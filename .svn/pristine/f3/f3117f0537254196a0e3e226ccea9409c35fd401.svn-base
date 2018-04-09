$(function() {

	var dataGridOptions = {
		pagination : false,
		rownumbers : false,
		url : contextPath + '/backend/common/organizationTreeGrid',
		treeField : 'name',
		columns : [ [

		{
			field : 'name',
			title : '名称',
			width : '30%',
			exportable : true,
		},

		{
			field : 'director.name',
			title : '负责人',
			width : '30%',
			exportable : true,
		},

		{
			field : 'remark',
			title : '备注',
			width : '30%',
			exportable : true,
		},

		{
			field : 'parentNames',
			title : '上级组织架构',
			width : '15%',
			hidden : true,
			exportable : true,
		},

		{
			field : 'isIMSData',
			width : '10%',
			hidden : true
		} ] ],

		// 异步加载url
		onBeforeExpand : function(row) {
			console.log("onBeforeExpand");
			$(this).treegrid('options').url = contextPath + '/backend/common/organizationTreeGrid';
		},

		onBeforeLoad : function(row, param) {
			console.log("onBeforeLoad");
			if ($('#searchForm [name="search.name_like"]').val() != '' || $('#searchForm [name="search.director.name_like"]').val() != '') {
				$(this).treegrid('options').url = contextPath + urlPrefix + 'jsonList';
			} else {
				$(this).treegrid('options').url = contextPath + '/backend/common/organizationTreeGrid';
			}
		},

		onLoadSuccess : function(row, data) {
			console.log("onLoadSuccess");
			if (row == null) {
				var rootRow = $(this).treegrid('getRoot');
				if (rootRow != null && rootRow.state == 'closed') {
					$(this).treegrid('toggle', rootRow.id);
				}
			}
		},
	};

	var options = {
		rowToolbarFormatter : function(toolbar, value, row, index) {
			if (row.isIMSData == true) {
				toolbar.find('#editRowBtn_' + index).remove();
				toolbar.find('#delRowBtn_' + index).remove();
			}
			return toolbar.html();
		},

		formDialog : {
			title : '组织架构管理',

			onOpen : function(dialog, action) {
				if (action != 'view') {
					dialog.find('input[textboxname="name"]').textbox('textbox').focus();
				}
				if (action == 'add') {
					var selected = $('#datagrid-list').treegrid('getSelected');
					if (selected != null) {
						var input = dialog.find('input[textboxname="parent.id"]');
						input.selecttextbox('setValue', selected.id);
						input.selecttextbox('setText', selected.name);
					}
				}
			},

			onSubmit : function(form, action) {
				if (action == 'add') {
				}
				return true;
			},
		},

		url : {
			urlPrefix : contextPath + urlPrefix,
		}
	};

	crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

});

function del(id, index) {
	$.ajax({
		data : id,
		type : "POST",
		dataType : "json",
		url : contextPath + "/backend/organization/getSubOrg?id=" + id,
		success : function(data) {
			if (data.length == 0) {
				crud.del(id, index);
				return false;
			} else {
				base.alert("删除失败，该组织架构下有子节点组织架构！");
			}
		}
	});
}

function organizationPermissionCrud(id) {
	base.dialog({
		id : 'organizationPermissionDialog',
		title : '数据查看权限维护',
		width : 800,
		height : 500,
		href : contextPath + '/backend/organization/showOrganizationPermissionForm/' + id,
		buttons : [ {
			text : '保存',
			height : 35,
			width : 100,
			iconCls : 'icon-save',
			handler : function() {
				var rows = $('#datagrid-permission-list').datagrid('getRows');
				var ids = [];
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].id) {
						ids.push(rows[i].id);
					}
				}

				ids.sort();
				for (var i = 0; i < ids.length - 1; i++) {
					if (ids[i] != "" && ids[i] == ids[i + 1]) {
						base.alert('数据查看人员不能重复！');
						return;
					}
				}
				var directorId = $('#organizationDirector').selecttextbox('getValue');
				var options = $('#datagrid-list').treegrid('options');
				$.post(contextPath + '/backend/organization/saveOrganizationPermissionForm/' + id,
						'employeeIds=' + ids.join(',') + '&directorId=' + directorId + '&_dataFields=' + options._dataFields, function(data) {
							if (data.isError) {
								base.alert(data.message);
							} else {
								$('#datagrid-list').treegrid('update', {
									id : data.entity.id,
									row : data.entity
								});
								$('#organizationPermissionDialog').dialog('close');
							}
						}, 'JSON');
			}
		} ],
	});
}
