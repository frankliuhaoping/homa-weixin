$(function() {

	var dataGridOptions = {
		nowrap : false,
		columns : [ [ {
			field : 'organization.name',
			title : '组织架构',
			align : 'center',
			width : 13,
			sortable : true,
			exportable : true,
		},

		{
			field : 'employee.name',
			title : '名称',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'year',
			title : '年份',
			align : 'center',
			width : 4,
			sortable : false,
			exportable : true,
		},

		{
			field : 'type',
			title : '类型',
			align : 'center',
			width : 4,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month1',
			title : '一月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month2',
			title : '二月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month3',
			title : '三月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month4',
			title : '四月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month5',
			title : '五月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month6',
			title : '六月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month7',
			title : '七月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month8',
			title : '八月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month9',
			title : '九月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month10',
			title : '十月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month11',
			title : '十一月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		},

		{
			field : 'month12',
			title : '十二月',
			align : 'center',
			width : 7,
			sortable : true,
			exportable : true,
		} ] ],
	};

	var options = {
		formDialog : {
			title : '销售任务',
			width : '600px',
			height : 'auto',
			dialogId : '#editdialog',
			formId : '#editForm',
			onSubmit : function(form, action) {
				return true;
			},

			onOpen : function(dialog, action) {
				if (action != 'add') {
					$('#org').combobox('select', organizationId);
				}
				calcTotal();

			},

		},
		url : {
			urlPrefix : contextPath + urlPrefix,
			sequenceUrl : contextPath + urlPrefix + 'sequence',
		}
	};

	crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

});

function organizationSelect() {
	var orgId = $('#org').combobox('getValue');
	$('#empl').combobox('reload', contextPath + '/backend/employee/getEmployeeByOrganization?id=' + orgId);
}

function calcTotal() {
	var total = 0;
	for (var i = 1; i <= 12; i++) {
		total += parseInt($('#editForm input[numberboxname="month' + i + '"]').numberbox('getValue'));
	}
	$('#editForm input[numberboxname="total"]').numberbox('setValue', total);

}
