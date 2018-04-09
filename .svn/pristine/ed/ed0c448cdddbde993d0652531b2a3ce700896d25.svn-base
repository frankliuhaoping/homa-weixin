window.commonSelect = {
	/**
	 * 打开对话框
	 * 
	 * @param selectOptions
	 * @param dialogOptions
	 * @param dataGridOptions
	 * @param customDataGridOptions
	 * @returns {Boolean}
	 */
	openSelectDialog : function(selectOptions, dialogOptions, dataGridOptions, customDataGridOptions) {
		if (typeof ryUILib == 'undefined') {
			base.loadJs(contextPath + '/static/js/easyuiDataGrid.js');
		}
		var defaultSelectOptions = {
			textField : 'name',
			valueField : 'id',
		};
		var selectOptions = $.extend(true, defaultSelectOptions, selectOptions);

		var defaultDataGridOptions = {
			singleSelect : true,
			toolbar : '#datagrid-selectData-toolbar',
		};
		var dataGridOptions = $.extend(true, defaultDataGridOptions, dataGridOptions);

		var defaultCustomDataGridOptions = {
			dataGridId : '#datagrid-selectData-list',
			searchFormId : '#searchForm-selectData',
			rowToolbarId : '',
			addVersionColumn : false,
			addRecordInfoColumns : false,
		};
		var customDataGridOptions = $.extend(true, defaultCustomDataGridOptions, customDataGridOptions);

		var defaultDialogOptions = {
			id : 'selectDataDialog',
			title : '请选择...',
			href : customDataGridOptions.url.urlPrefix + 'select',
			width : 600,
			height : 500,
			onBeforeOpen : function() {
				selectData = ryUILib.EasyuiDataGrid(customDataGridOptions, dataGridOptions);
				if (selectOptions.searchCallback) {
					selectData.search = selectOptions.searchCallback;
				}

			}
		};
		var dialogOptions = $.extend(true, defaultDialogOptions, dialogOptions);

		var selectButtonOptions = {
			text : '选择',
			iconCls : 'icon-ok',
			handler : function() {
				var row = $(customDataGridOptions.dataGridId).datagrid('getSelected');
				if (row == null) {
					base.alert('请先选择一行记录！');
					return false;
				}
				$(selectOptions.selecttextboxId).selecttextbox('setText', row[selectOptions.textField]);
				$(selectOptions.selecttextboxId).selecttextbox('setValue', row[selectOptions.valueField]);
				if (selectOptions.setValueCallback) {
					selectOptions.setValueCallback.call(this, row[selectOptions.textField], row[selectOptions.valueField]);
				}
				$('#' + dialogOptions.id).dialog('close');
			}
		};

		var oldOnDblClickRow = dataGridOptions.onDblClickRow;
		dataGridOptions.onDblClickRow = function(rowIndex, rowData) {
			selectButtonOptions.handler.call();
			$('#' + dialogOptions.id).dialog('close');
			if (oldOnDblClickRow) {
				oldOnDblClickRow.call(this, rowIndex, rowData)
			}
		}

		base.dialog(dialogOptions, null, false, null, selectButtonOptions);
		return false;
	},

	/**
	 * 添加事件
	 * 
	 * @param selectOptions
	 * @param dialogOptions
	 * @param dataGridOptions
	 * @param customDataGridOptions
	 */
	initSelect : function(selectOptions, dialogOptions, dataGridOptions, customDataGridOptions) {
		if (!$(selectOptions.selecttextboxId).hasClass('easyui-selecttextbox')) {
			return;
		}

		var textboxOptions = $(selectOptions.selecttextboxId).selecttextbox('options');
		if (textboxOptions.readonly != true) {
			$($(selectOptions.selecttextboxId).selecttextbox('button')).on('click', function() {
				commonSelect.openSelectDialog(selectOptions, dialogOptions, dataGridOptions, customDataGridOptions);
				return false;
			});
		}
	},

	/**
	 * 选择员工对话框
	 * 
	 * <pre>
	 * selecttextboxId
	 * 
	 * textField
	 * valueField
	 * 
	 * searchCallback
	 * setValueCallback(text, value)
	 * </pre>
	 * 
	 * @param options
	 */
	initSelectEmployee : function(selectOptions) {
		var defaultSelectOptions = {
			selecttextboxId : '#select_employee_textbox',
		};
		var selectOptions = $.extend(true, defaultSelectOptions, selectOptions);

		var dataGridOptions = {
			fitColumns : false,
			columns : [ [ {
				field : 'name',
				title : '名称',
				width : 80,
			}, {
				field : 'organization.parentNames',
				title : '组织架构',
				width : 220,
			}, {
				field : 'idNumber',
				title : '身份证号',
				width : 150,
			}, {
				field : 'code',
				title : '工号',
				width : 80,
			} ] ]
		};

		var customDataGridOptions = {
			url : {
				urlPrefix : contextPath + '/backend/employee/',
			}
		};

		var dialogOptions = {
			title : '请选择员工',
		};
		commonSelect.initSelect(selectOptions, dialogOptions, dataGridOptions, customDataGridOptions);
	},

	/**
	 * 选择组织架构对话框
	 * 
	 * <pre>
	 * selecttextboxId
	 * 
	 * textField
	 * valueField
	 * 
	 * searchCallback
	 * setValueCallback(text, value)
	 * </pre>
	 * 
	 * @param options
	 */
	initSelectOrganization : function(selectOptions) {
		var defaultSelectOptions = {
			selecttextboxId : '#select_organization_textbox',
			searchCallback : function() {
				if ($('#searchForm-selectData [name="search.name_like"]').val() != '') {
					var params = $('#searchForm-selectData').form2json();
					$('#datagrid-selectData-list').treegrid({
						queryParams : params,
						url : contextPath + '/backend/organization/jsonList',
						pagination : true,
					});
				} else {
					$('#datagrid-selectData-list').treegrid({
						queryParams : null,
						url : contextPath + '/backend/common/organizationTreeGrid',
						pagination : false,
					});
				}
			}
		};
		var selectOptions = $.extend(true, defaultSelectOptions, selectOptions);

		var dataGridOptions = {
			pagination : false,
			rownumbers : false,
			url : contextPath + '/backend/common/organizationTreeGrid',
			treeField : 'name',
			columns : [ [ {
				field : 'name',
				title : '名称',
				width : 30,
			}, {
				field : 'parentNames',
				title : '完整名称',
				width : 30,
			} ] ],
			onLoadSuccess : function(row, data) {
				if (row == null) {
					var rootRow = $(this).treegrid('getRoot');
					if (rootRow != null && rootRow.state == 'closed') {
						$(this).treegrid('toggle', rootRow.id);
					}
				}
			},
		};

		var customDataGridOptions = {
			url : {
				urlPrefix : contextPath + '/backend/organization/',
			}
		};

		var dialogOptions = {
			title : '请选择组织架构',
		};
		commonSelect.initSelect(selectOptions, dialogOptions, dataGridOptions, customDataGridOptions);
	}
}
