var oldClickNodeId = null;

$(function() {
	var dataGridOptions = {
		pagination : false,
		columns : [ [ {
			field : 'organization.name',
			title : '组织架构',
			width : 30,
			sortable : true,
			exportable : true,
		},
		              
	   {
			field : 'name',
			title : '名称',
			width : 30,
			sortable : true,
			exportable : true,
		}
		] ],
	};

	var options = {
		rowToolbarFormatter : function(toolbar, value, row, index) {
			if (row.isIMSData == 1) {
				toolbar.find('#rowEditBtn_' + index).remove();
				toolbar.find('#rowDelBtn_' + index).remove();
			}
			return toolbar.html();
		},

		url : {
			urlPrefix : contextPath + urlPrefix,
			sequenceUrl : contextPath + urlPrefix + 'sequence',
		}
	};

	pCrud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

});
