$(function() {

	var dataGridOptions = {
		showFooter : true,
		singleSelect : true,
		columns : [ [ {
			field : 'ORGANIZATIONNAME',
			title : '组织架构',
			width : 30,
			exportable : true,
		},

		{
			field : 'SALERNAME',
			title : '导购员',
			width : 30,
			exportable : true,
		},

		{
			field : 'SALERTYPETEXT',
			title : '类型',
			width : 10,
			exportable : true,
		},

		{
			field : 'IDNUMBER',
			title : '身份证号',
			width : 30,
			exportable : true,
		},

		{
			field : 'QTY',
			title : '总数量',
			width : 30,
			sortable : true,
			exportable : true,
		},

		{
			field : 'AMOUNT',
			title : '总金额',
			width : 15,
			sortable : true,
			exportable : true,
		} ] ],
	};

	var options = {
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
