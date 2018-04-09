$(function() {

	var dataGridOptions = {
		showFooter : true,
		singleSelect : true,
		columns : [ [ {
			field : 'PRODUCTCATEGORYNAME',
			title : '产品分类',
			width : 30,
			exportable : true,
		},

		{
			field : 'PRODUCTNAME',
			title : '产品名称',
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
