var oldClickNodeId = null;

$(function() {
	var dataGridOptions = {
		showFooter : true,
		columns : [ [ {
			field : 'employee.organization.parentNames',
			title : '组织架构',
			width : 20,
			exportable : true,
		},

		{
			field : 'employee.name',
			title : '姓名',
			width : 8,
			exportable : true,
		},

		{
			field : 'employee.sysRoleTypeText',
			title : '角色',
			width : 5,
			exportable : true,
		},

		{
			field : 'signType',
			title : '签到类型',
			width : 5,
			exportable : true,
		},

		{
			field : 'signTime',
			title : '签到时间',
			width : 12,
			exportable : true,
		},

		{
			field : 'address',
			title : '签到地点',
			width : 20,
			exportable : true,
		} ] ],
	};

	var options = {
		rowToolbarId : '',
		addCkColumn : false,
		addVersionColumn : false, // 添加version列
		addRecordInfoColumns : false, // 记录信息列 createdBy.realName等
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
