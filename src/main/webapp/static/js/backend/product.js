$(function() {

	var dataGridOptions = {
		columns : [ [ {
			field : 'name',
			title : '名称',
			width : 30,
			sortable : true,
			exportable : true,
		},

		{
			field : 'code',
			title : '编码',
			width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'description',
			title : '描述',
			width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'isIMSData',
			hidden : true
		} ] ],
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
			title : '产品列表',
		},

		url : {
			urlPrefix : contextPath + urlPrefix,
			sequenceUrl : contextPath + urlPrefix + 'sequence',
		}
	};

	crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

	$("#tt").tree({
		onClick : function(node) {
			var children = $("#tt").tree('getChildren', node.target);
			var ids = [];
			for (i = 0; i < children.length; i++) {
				ids.push(children[i].id);
			}
			ids.push(node.id);
			crud.reload({
				"search.category.id_in" : ids.join(",")
			});
			return false;
		}
	});
});
