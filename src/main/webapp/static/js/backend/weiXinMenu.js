$(function() {

	var dataGridOptions = {
		pagination : false,
		treeField : 'name',
		columns : [ [ {
			field : 'name',
			title : '名称',
			width : 30,
			exportable : true,
		}, {
			field : 'menuType',
			title : '类型',
			width : 30,
			exportable : true,
		}, {
			field : 'key',
			title : 'KEY',
			width : 25,
			exportable : true,
		}, {
			field : 'url',
			title : 'URL',
			width : 10,
			exportable : true,
		}, {
			field : 'mediaId',
			title : 'mediaId',
			width : 10,
			exportable : true,
		}, {
			field : 'isDisabled',
			title : '是否屏蔽',
			width : 10,
			exportable : true,
		}, {
			field : 'remark',
			title : '备注',
			width : 15,
			exportable : true,
		} ] ],
	};

	var options = {
		formDialog : {
			title : '微信菜单管理',

			onOpen : function(dialog, action) {
				dialog.find('input[textboxname="name"]').textbox('textbox').focus();
				if (action != 'add') {
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
			sequenceUrl : contextPath + urlPrefix + 'sequence',
		}
	};

	crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

});
