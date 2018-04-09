$(function() {

	var dataGridOptions = {
		showFooter : true,
		fitColumns : false,
		frozenColumns : [ [ {
			field : 'SALESTIME',
			title : '销售时间',
			align : 'center',
			width : 150,
			fixed : false,
			exportable : true,
		},

		{
			field : 'PARENTNAMES',
			title : '组织架构',
			width : 200,
			fixed : false,
			exportable : true,
		},

		{
			field : 'SALENAME',
			title : '导购员',
			align : 'center',
			width : 100,
			fixed : false,
			exportable : true,
		}, ] ],

		columns : [ [

		{
			field : 'INVOICENO',
			title : '发票号',
			width : 200,
			fixed : false,
			exportable : true,
		},

		{
			field : 'NAME',
			title : '产品名称',
			width : 200,
			sortable : false,
			exportable : true,
		},

		{
			field : 'PRICE',
			title : '产品价格(元)',
			align : 'right',
			width : 100,
			sortable : false,
			exportable : true,
		},

		{
			field : 'QTY',
			title : '产品数量',
			align : 'right',
			width : 100,
			sortable : false,
			exportable : true,
		}, {
			field : 'ZJE',
			title : '总金额(元)',
			align : 'right',
			width : 100,
			sortable : false,
			exportable : true,
		},

		{
			field : 'CASHBACK',
			title : '返现',
			align : 'right',
			width : 100,
			exportable : true,
		},

		{
			field : 'CUSTOMERNAME',
			title : '顾客姓名',
			exportable : true,
			hidden : true,
		},

		{
			field : 'CUSTOMERTEL',
			title : '电话',
			exportable : true,
			hidden : true,
		},

		{
			field : 'CUSTOMERADDRESS',
			title : '顾客地址',
			exportable : true,
			hidden : true,
		},

		{
			field : 'INVOICEIMAGEURL',
			title : '发标图片',
			exportable : false,
			hidden : true,
		},

		] ],
	};

	var options = {
		addCkColumn : false,
		addVersionColumn : false, // 添加version列
		addRecordInfoColumns : false, // 记录信息列 createdBy.realName等
		rowToolbarFormatter : function(toolbar, value, row, index) {
			if (row.INVOICEIMAGEURL) {
				var html = toolbar.html();
				html = html.replace(/row.imageUrl/gim, contextPath + row.INVOICEIMAGEURL);
				return html;
			} else {
				return '';
			}
		},
		url : {
			urlPrefix : contextPath + urlPrefix,
		},
	};

	crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

	$("#selects1").combobox({
		editable : false,
		width : 60,
		onSelect : function(record) {
			if (record.value == "false") {
				$(this).attr("name", "search.invoiceNo_isNull")
			}
			if (record.value == "all") {
				$(this).attr("name", "")
			}
			if (record.value == "true") {
				$(this).attr("name", "search.invoiceNo_isNotNull")
			}
			console.log(this);
		}
	});

	var customTreeOptions = {
		onClick : function(node, nodeType) {
			$('#search_organization').val(node.id);
			crud.search();
		}
	}
	organizationTree = ryUILib.OrganizationTree(customTreeOptions, {});
});

function viewInvoiceImage() {
	this.lightBox();
}
