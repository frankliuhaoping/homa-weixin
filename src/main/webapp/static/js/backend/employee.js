var oldClickNodeId = null;

$(function() {
	var dataGridOptions = {
		columns : [ [ {
			field : 'organization.parentNames',
			title : '组织架构',
			width : 30,
			exportable : true,
		},

		{
			field : 'code',
			title : '工号',
			width : 15,
			sortable : true,
			exportable : true,
		},

		{
			field : 'name',
			title : '姓名',
			width : 15,
			sortable : true,
			exportable : true,
		},

		{
			field : 'sex',
			title : '性别',
			width : 8,
			sortable : true,
			exportable : true,
		},

		{
			field : 'mobileNo',
			title : '手机',
			width : 20,
			exportable : true,
		},

		{
			field : 'sysUser.loginName',
			title : '登录帐号',
			width : 20,
			exportable : true,
		},

		{
			field : 'sysRoleType',
			title : '角色',
			width : 10,
			sortable : true,
			exportable : true,
		},

		{
			field : 'salerType',
			title : '类型',
			width : 10,
			sortable : true,
			exportable : true,
		},

		] ],
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
			title : '员工列表',
			width : 700,
			onOpen : function(dialog, action) {
				if (action == 'add') {
					var selected = $('#organizationTree').tree('getSelected');
					if (selected != null) {
						var input = dialog.find('input[textboxname="organization.id"]');
						input.selecttextbox('setValue', selected.id);
						input.selecttextbox('setText', selected.text);
					}
				}
				if (action != 'view') {
					dialog.find('input[textboxname="name"]').textbox('textbox').focus();
				}
			},
		},

		url : {
			urlPrefix : contextPath + urlPrefix,
		}
	};

	crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

	var customTreeOptions = {
		onClick : function(node, nodeType) {
			$('#search_organization').val(node.id);
			crud.search();
		}
	}
	organizationTree = ryUILib.OrganizationTree(customTreeOptions, {});

});
