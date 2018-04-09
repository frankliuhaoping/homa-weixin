$(function() {

	var dataGridOptions = {
		pagination : false,
		treeField : 'name',
		columns : [ [ {
			field : 'name',
			title : '分类名称',
			width : 50,
			sortable : true,
			exportable : true,
		}, {
			field : 'remark',
			title : '备注',
			width : 50,
			sortable : true,
			exportable : true,
		} ] ],
	};

	var options = {
		rowToolbarFormatter : function(toolbar, value, row, index) {
			if (row.isIMSData == 1) {
				toolbar.find('#editRowBtn_' + index).remove();
				toolbar.find('#delRowBtn_' + index).remove();
			}
			return toolbar.html();
		},

		formDialog : {
			title : '产品分类管理',

			onOpen : function(dialog, action) {
				dialog.find('input[textboxname="name"]').textbox('textbox').focus();
			},

			onSubmit : function(form, action) {
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
