/**
 * 
 */

var ryUILib = ryUILib || {}; // 命名空间
(function(window, undefined) {
	/**
	 * 外部可调用的对象
	 * 
	 * <pre>
	 * options:{ 
	 * 		dataGridId, 页面中datagrid的id，默认'#datagrid-list'
	 * 		searchFormId, 页面中searchForm的id，默认'#searchForm'
	 * 		rowToolbarId, 页面中searchForm的id，默认'#datagrid-row-toolbar'
	 * 		exportFileName, 输出excel的文件名，如果有设置formDialog.title，则用formDialog.title，如果未设置，按当前时间生成文件名
	 * 		rowToolbarFormatter{function(rowToolBar, value, row, index)}, 动态控制行操作按钮
	 * 		editMode, 编辑模式'dialog', 打开新对话框增改查，'row', 行内直接修改, 'cell', 单元格修改
	 * 		edatagrid{
	 * 			defaultRow{function()} 行修改模式，添加一行的默认值
	 * 			defaultEditField, 
	 * 		}
	 * 		addCkColumn : true, 添加选择框列
	 * 		addIdColumn : true, 添加ID列
	 * 		addVersionColumn : true, 添加version列
	 * 		addRecordInfoColumns : true, // 记录信息列 createdBy.realName等 
	 * 		formDialog { 编辑模式'dialog'，需要配置的属性
	 * 			title, 对话框的标题
	 * 			width, 对话框的宽度，默认400
	 * 			height, 对话框的高度，默认auto
	 * 			dialogId, 对话框的id
	 * 			formId, 对话框中form的id
	 * 			onOpen, 对话框打开前的事件处理
	 * 			onSubmit, 对话框提交form前的事件处理
	 * 		},
	 * 		url{
	 * 			urlPrefix, url前缀，自动成生jsonListUrl, viewUrl, viewAuditorUrl, addUrl, editUrl, delUrl, saveUrl, exportToExcelUrl
	 * 			jsonListUrl, 默认 urlPrefix + 'jsonList'
	 * 			viewUrl, 默认 urlPrefix + 'view'
	 * 			viewAuditorUrl, 默认 urlPrefix + 'viewAuditor'
	 * 			addUrl, 默认 urlPrefix + 'add'
	 * 			editUrl, 默认 urlPrefix + 'edit'
	 * 			delUrl, 默认 urlPrefix + 'delete'
	 * 			saveUrl, 默认 urlPrefix + 'save'
	 * 			sequenceUrl,
	 * 			exportToExcelUrl, 默认 urlPrefix + 'exportToExcel'
	 * 			importFromExcelUrl, 默认 urlPrefix + 'importFromExcel'
	 * 		},
	 * 		dataFields{}
	 * 		swapSeq,
	 * }
	 * </pre>
	 * 
	 */
	ryUILib.EasyuiDataGrid = function(options, dataGridOptions) {
		return new EasyuiDataGrid(options, dataGridOptions);
	}

	var EasyuiDataGrid = function(options, dataGridOptions) {
		var defaultOptions = {
			dataGridId : '#datagrid-list',
			rowToolbarId : '#datagrid-row-toolbar',
			searchFormId : '#searchForm',
			exportFileName : '',
			editMode : 'dialog',
			addCkColumn : true,
			addIdColumn : true,
			addVersionColumn : true,
			addRecordInfoColumns : true, // 记录信息列 createdBy.realName等
			url : {},
			formDialog : {
				width : 'auto',
				height : 'auto',
				dialogId : '#editdialog',
				formId : '#editForm',
				onSubmit : function(form, action) {
					return true;
				},
			},
			edatagrid : {},
			swapSeq : true,
		};

		// dataGrid默认选项
		var defaultDataGridOptions = {
			// 设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
			fitColumns : true,
			// 定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能
			autoRowHeight : false,
			// 如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
			// nowrap : false,
			// 数据网格（datagrid）面板的头部工具栏。可能的值：
			// 1、数组，每个工具选项与链接按钮（linkbutton）一样
			// 2、选择器，只是工具栏。
			toolbar : '#datagrid-toolbar',
			// 设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
			striped : true,
			// 指示哪个字段是标识字段
			idField : 'id',
			// 设置为 true，则显示带有行号的列
			rownumbers : true,
			// 设置为 true，则在数据网格（datagrid）底部显示分页工具栏
			pagination : true,
			// 初始化页面尺寸
			pageSize : 20,
			// 隐藏边框
			border : false,
			// 多字段排序
			multiSort : true,
			// 当设置为 true 时，尺寸就适应它的父容器
			fit : true,
			// 设置为 true，则只允许选中一行
			singleSelect : false,
			// ctrl选择多行
			ctrlSelect : true,
		// loadMsg : '',
		};

		this.options = $.extend(true, defaultOptions, options);
		this.dataGridOptions = $.extend(true, defaultDataGridOptions, dataGridOptions);

		this.options.exportFileName = this.options.exportFileName ? this.options.exportFileName : (this.options.formDialog.title ? this.options.formDialog.title : new Date().format('yyyyMMddhhmm'));
		this.dataGridObject = $(this.options.dataGridId);
		this.currentRowIndex = null;
		this.isEditing = false;

		initUrl.call(this);
		initDataGrid.call(this);
	}

	EasyuiDataGrid.prototype = {
		/**
		 * 是否是行编辑模式
		 * 
		 * @returns {Boolean}
		 */
		isRowEditMode : function() {
			return this.options.editMode == 'row';
		},

		/**
		 * dialog编辑模式
		 * 
		 * @returns {Boolean}
		 */
		isDialogEditMode : function() {
			return this.options.editMode == 'dialog';
		},

		/**
		 * cell编辑模式
		 * 
		 * @returns {Boolean}
		 */
		isCellEditMode : function() {
			return this.options.editMode == 'cell';
		},

		/**
		 * 是否是treegrid
		 * 
		 * @returns {Boolean}
		 */
		isTreeGrid : function() {
			return this.dataGridOptions.treeField != null;
		},

		/**
		 * 查看
		 * 
		 * @param id
		 * @param rowIndex
		 */
		view : function(id, rowIndex) {
			if (this.isDialogEditMode()) {
				openFormDialog.call(this, 'view', id);
			}
		},

		/**
		 * 查看Auditor
		 */
		viewAuditor : function() {
			var dg = this;

			var row = hasSelectedRow.call(dg);
			if (row == null) {
				return;
			}

			base.dialog({
				title : '查看记录信息',
				width : 400,
				href : dg.options.url.viewAuditorUrl,
				onBeforeOpen : function() {
					$(this).find('#auditorForm').form('load', row);
				}
			});
		},

		/**
		 * 添加记录
		 */
		add : function() {
			var dg = this;
			dg.saveCurrentRowIndex(0);
			if (dg.isRowEditMode()) {
				dg.dataGridObject.datagrid('clearSelections');
				addInRow.call(dg);
			} else if (dg.isDialogEditMode()) {
				addInDialog.call(dg);
			} else if (dg.isCellEditMode()) {
				addInCell.call(dg);
			}
		},

		/**
		 * 修改记录
		 * 
		 * @param id
		 * @param rowIndex 当前行的index
		 */
		edit : function(id, rowIndex) {
			var dg = this;
			if (dg.isTreeGrid()) {
				dg.dataGridObject.treegrid('select', id);
			}
			// 保存当前修改行的index
			dg.saveCurrentRowIndex(rowIndex);
			if (!dg.currentRowIndex && dg.currentRowIndex != 0) {
				base.alert('请先选择要修改的行！');
				return;
			}
			if (dg.isRowEditMode()) {
				editInRow.call(dg, rowIndex);
			} else if (dg.isDialogEditMode()) {
				editInDialog.call(dg, id);
			}
		},

		/**
		 * 取消正在添加或修改的行
		 */
		cancelRow : function() {
			if (this.isRowEditMode()) {
				cancelInRow.call(this);
			}
		},

		/**
		 * 保存正在添加或修改的行
		 */
		saveRow : function() {
			if (this.isRowEditMode()) {
				savelInRow.call(this);
			}
		},

		/**
		 * 批量删除或者删除当前记录
		 * 
		 * @param ids id字符串，多个逗号分隔
		 * @param rowIndex 当前行的index
		 */
		del : function(ids, rowIndex) {
			var dg = this;
			if (dg.isCellEditMode()) {
				setTimeout(function() {
					delInCell.call(dg, rowIndex);
				}, 200);
				return;
			}
			if (!ids || ids == '') {
				// 选择行的ID字符串
				ids = dg.getSelectionIds();
			}
			if (ids == '') {
				base.alert('请先选择要删除的行！');
				return;
			}
			base.confirm('确认删除已选择的记录？', '', function() {
				dg.saveCurrentRowIndex(rowIndex);
				// 删除代码
				$.post(dg.options.url.delUrl + '/' + ids, null, function(data) {
					if (data.success == 'no') {
						base.alert(data.message);
						return;
					}
					// 重新加载数据，并设置选择行
					dg.search();
					if (!dg.isTreeGrid()) {
						dg.dataGridObject.datagrid('clearSelections');
						setTimeout(function() {
							dg.selectRow(dg.currentRowIndex)
						}, 300);
					}

				}, 'JSON');
			});
		},

		/**
		 * 移动行
		 * 
		 * @param action 'up' or 'down' 上移或下移
		 */
		moveRow : function(action) {
			if (this.isTreeGrid()) {
				doMoveTreeGridRow.call(this, action);
			} else {
				doMoveRow.call(this, action);
			}
		},

		/**
		 * 刷新datagrid数据
		 */
		reload : function(params) {
			params = $.extend(true, params, this.dataGridOptions.queryParams);
			if (this.isTreeGrid()) {
				this.dataGridObject.treegrid('reload', params);
			} else {
				this.dataGridObject.datagrid('reload', params);
			}
		},

		/**
		 * 查询
		 */
		search : function() {
			var params = $(this.options.searchFormId).form2json();
			this.reload(params);
		},

		/**
		 * 重置
		 */
		resetSearchForm : function() {
			$(this.options.searchFormId).form('reset');
		},

		/**
		 * 导出EXCEL
		 */
		exportToExcel : function() {
			var params = $(this.options.searchFormId).serialize();
			var fields = this.dataGridObject.datagrid('getColumnFields', true).concat(this.dataGridObject.datagrid('getColumnFields'));
			var dataFields = "", fieldTitles = "";
			for (var i = 0; i < fields.length; i++) {
				var field = fields[i];
				var col = this.dataGridObject.datagrid('getColumnOption', field);
				if (col.exportable) {
					dataFields += col.field + ',';
					fieldTitles += col.title + ',';
				}
			}
			var queryParams = $.extend(true, {}, this.dataGridOptions.queryParams);
			delete queryParams._dataFields;
			queryParams = $.param(queryParams);

			base.downloadFile(this.options.url.exportToExcelUrl, params + '&' + queryParams + '&_dataFields=' + dataFields + '&_fieldTitles=' + fieldTitles + '&fileName='
					+ this.options.exportFileName);
		},

		/**
		 * 导入EXCEL
		 */
		importFromExcel : function() {
			var dg = this;
			var plupload;
			var dialogOptions = {
				id : '__importFromExcelDialog',
				title : '导入Excel数据',
				href : dg.options.url.importFromExcelUrl,
				onBeforeOpen : function() {
				},
				onOpen : function() {
					var dialog = $(this);
					if ($('#__downloadTemplateFile').length == 0) {
						return;
					}
					// 下载模板文件
					$('#__downloadTemplateFile').on('click', function() {
						base.downloadFile(dg.options.url.importFromExcelUrl + '/getTemplateFile')
					});

					var button = $('#__chooseFile').textbox('button');
					plupload = base.plupload({
						url : dg.options.url.importFromExcelUrl + '/import',
						browse_button : button[0],
						filters : {
							mime_types : [ // 只允许上传图片和zip文件
							{
								title : "Excel文件",
								extensions : "xls,xlsx"
							} ]
						},
						success : function(data, uploader, file, result) {
							if (data.success == 'yes') {
								base.alert('数据已导入成功！');
								dg.search();
								$(dialog).dialog('close');
							} else {
								base.alert(data.message);
								return;
							}
						},
						init : {
							FilesAdded : function(uploader, files) {
								$.each(files, function(i, file) {
									$('#__chooseFile').textbox('setValue', file.name);
								});
							}
						}
					});

				}
			};
			var importButton = {
				text : '导入',
				iconCls : 'icon-import',
				handler : function() {
					if (plupload) {
						plupload.start();
					}
				}
			};
			base.dialog(dialogOptions, null, false, null, importButton);
		},

		/**
		 * 选择行的ID字符串，逗号分隔
		 */
		getSelectionIds : function() {
			var rows = null;
			if (this.isTreeGrid()) {
				rows = this.dataGridObject.treegrid('getSelections');
			} else {
				rows = this.dataGridObject.datagrid('getSelections');
			}

			if (rows.length == 0) {
				return '';
			}
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			ids = ids.join(',');
			return ids;
		},

		/**
		 * 保存当前选择行的index
		 */
		saveCurrentRowIndex : function(rowIndex) {
			if (rowIndex || rowIndex == 0) {
				this.currentRowIndex = rowIndex;
			} else {
				var row = this.dataGridObject.datagrid('getSelected');
				if (row == null) {
					this.currentRowIndex = null;
				} else {
					this.currentRowIndex = this.dataGridObject.datagrid('getRowIndex', row);
				}

			}
		},

		/**
		 * 选择一行，如果rowIndex大于最大行数，则选择最后一行
		 * 
		 * @param rowIndex
		 */
		selectRow : function(rowIndex) {
			if (!rowIndex) {
				rowIndex = 0;
			}
			var maxRowIndex = this.dataGridObject.datagrid('getRows').length - 1;
			rowIndex = rowIndex > maxRowIndex ? maxRowIndex : rowIndex;
			this.dataGridObject.datagrid('clearSelections');
			this.dataGridObject.datagrid('selectRow', rowIndex)
		},

		/**
		 * datagrid修改行
		 * 
		 * @param index
		 * @param row
		 */
		updateRow : function(index, row) {
			if (this.isTreeGrid()) {
				this.search();
			} else {
				this.dataGridObject.datagrid('updateRow', {
					index : index,
					row : row,
				});
			}
		},

		/**
		 * datagrid插入一行
		 * 
		 * @param index
		 * @param row
		 */
		insertRow : function(index, row) {
			if (this.isTreeGrid()) {
				// this.dataGridObject.treegrid('append', {
				// parent : row._parentId,
				// data : [ row ]
				// });
				// this.dataGridObject.treegrid('clearSelections');
				// this.dataGridObject.treegrid('select', row.id);
				this.search();
			} else {
				this.dataGridObject.datagrid('insertRow', {
					index : index,
					row : row
				});
				this.dataGridObject.datagrid('clearSelections');
				this.selectRow(index);
			}
		},

	};

	// /////////////////////////////// private

	/**
	 * 初始化url
	 */
	function initUrl() {
		var urlOpts = this.options.url;
		var urlPrefix = urlOpts.urlPrefix;
		if (urlPrefix) {
			urlPrefix = urlPrefix.charAt(urlPrefix.length - 1) == '/' ? urlPrefix : urlPrefix + '/';

			urlOpts.jsonListUrl = urlOpts.jsonListUrl ? urlOpts.jsonListUrl : urlPrefix + 'jsonList';
			urlOpts.viewUrl = urlOpts.viewUrl ? urlOpts.viewUrl : urlPrefix + 'view';
			urlOpts.viewAuditorUrl = urlOpts.viewAuditorUrl ? urlOpts.viewAuditorUrl : urlPrefix + 'viewAuditor';
			urlOpts.addUrl = urlOpts.addUrl ? urlOpts.addUrl : urlPrefix + 'add';
			urlOpts.editUrl = urlOpts.editUrl ? urlOpts.editUrl : urlPrefix + 'edit';
			urlOpts.delUrl = urlOpts.delUrl ? urlOpts.delUrl : urlPrefix + 'delete';
			urlOpts.saveUrl = urlOpts.saveUrl ? urlOpts.saveUrl : urlPrefix + 'save';
			urlOpts.exportToExcelUrl = urlOpts.exportToExcelUrl ? urlOpts.exportToExcelUrl : urlPrefix + 'exportToExcel';
			urlOpts.importFromExcelUrl = urlOpts.importFromExcelUrl ? urlOpts.importFromExcelUrl : urlPrefix + 'importFromExcel';
		}
		this.dataGridOptions.url = this.dataGridOptions.url ? this.dataGridOptions.url : urlOpts.jsonListUrl;
	}

	/**
	 * 初始化dataGrid
	 */
	function initDataGrid() {
		if (this.isCellEditMode()) {
			base.loadJs(contextPath + '/static/easyui/extension/datagrid-cellediting.js');
		}
		if (this.isRowEditMode()) {
			base.loadJs(contextPath + '/static/easyui/extension/jquery.edatagrid.js');
		}
		// 通用的列(操作列，ID，Version)
		addCommonColumns.call(this);
		InitDataFields.call(this);
		this.dataGridOptions.queryParams = $.extend(true, {
			_dataFields : this.options.dataFields
		}, this.dataGridOptions.queryParams);

		if (this.isTreeGrid()) {
			this.dataGridObject.treegrid(this.dataGridOptions);

		} else if (this.isRowEditMode()) { // 行编辑模式
			initEditableDataGrid.call(this);

		} else if (this.isCellEditMode()) {
			var dg = this.dataGridObject.datagrid(this.dataGridOptions);
			dg.datagrid('enableCellEditing');
		} else if (this.isDialogEditMode()) {
			this.dataGridObject.datagrid(this.dataGridOptions);
		}

		// pagination
		var pager = this.dataGridObject.datagrid('getPager');
		if (pager) {
			pager.find('.pagination-first').parent().parent().attr('title', '第一页');
			pager.find('.pagination-prev').parent().parent().attr('title', '上一页');
			pager.find('.pagination-next').parent().parent().attr('title', '下一页');
			pager.find('.pagination-last').parent().parent().attr('title', '最后一页');
			pager.find('.pagination-load').parent().parent().attr('title', '刷新数据');

			pager.find('input.pagination-num').attr('title', '跳转到第几页');
			pager.find('select.pagination-page-list').attr('title', '每页显示记录数');
		}
	}

	/**
	 * 初始化行编辑模式datagrid
	 */
	function initEditableDataGrid() {
		var dg = this;
		var options = {
			saveUrl : dg.options.url.saveUrl,
			updateUrl : dg.options.url.saveUrl,
		};
		$.extend(true, options, dg.dataGridOptions);

		options.onAdd = function(index, row) {
			setRowToolBarState.call(dg, 'editing');
			if (dg.dataGridOptions.onAdd) {
				dg.dataGridOptions.onAdd.call(dg, index, row);
			}
		}

		options.onEdit = function(index, row) {
			setRowToolBarState.call(dg, 'editing');
			if (dg.dataGridOptions.onEdit) {
				dg.dataGridOptions.onEdit.call(dg, index, row);
			}
		}

		options.onSuccess = function(index, row) {
			setRowToolBarState.call(dg, 'normal');
			if (dg.dataGridOptions.onSuccess) {
				dg.dataGridOptions.onSuccess.call(dg, index, row);
			}
		}

		options.onError = function(index, row) {
			base.alert(row.message);
			dg.isEditing = true;
		}
		dg.dataGridObject.edatagrid(options);
		dg.dataGridObject.edatagrid('disableEditing');
	}

	/**
	 * 列是否存在
	 */
	function columnExists(field) {
		var dg = this;
		for (var i = 0; i < dg.dataGridOptions.columns[0].length; i++) {
			if (dg.dataGridOptions.columns[0][i].field == field) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 添加通用的Columns, id, version, 操作列
	 */
	function addCommonColumns() {
		var dg = this;

		if (dg.options.addCkColumn && !columnExists.call(dg, '___ck')) {
			dg.dataGridOptions.columns[0].splice(0, 0, {
				field : '___ck',
				checkbox : true
			});
		}
		if (dg.options.addIdColumn && !columnExists.call(dg, 'id')) {
			dg.dataGridOptions.columns[0].push({
				field : 'id',
				hidden : true
			});
		}
		if (dg.options.addVersionColumn && !columnExists.call(dg, 'version')) {
			dg.dataGridOptions.columns[0].push({
				field : 'version',
				hidden : true
			});
		}
		if (dg.options.addRecordInfoColumns) {
			if (!columnExists.call(dg, 'createdBy.realName')) {
				dg.dataGridOptions.columns[0].push({
					field : 'createdBy.realName',
					hidden : true
				});
			}
			if (!columnExists.call(dg, 'createdTime')) {
				dg.dataGridOptions.columns[0].push({
					field : 'createdTime',
					hidden : true
				});
			}
			if (!columnExists.call(dg, 'lastModifiedBy.realName')) {
				dg.dataGridOptions.columns[0].push({
					field : 'lastModifiedBy.realName',
					hidden : true
				});
			}
			if (!columnExists.call(dg, 'lastModifiedTime')) {
				dg.dataGridOptions.columns[0].push({
					field : 'lastModifiedTime',
					hidden : true
				});
			}
		}

		var rowToolbar = $(dg.options.rowToolbarId);
		if (rowToolbar.length > 0) {
			// 操作 列
			dg.dataGridOptions.columns[0].push({
				field : 'datagrid-rowToolbar',
				title : '操作',
				formatter : function(value, row, index) {
					var html = rowToolbar.html();
					html = html.replace(/row.id/gim, "'" + row.id + "'");
					html = html.replace(/row.index/gim, index);
					if (dg.options.rowToolbarFormatter) {
						html = dg.options.rowToolbarFormatter.call(dg, $(html), value, row, index);
					}
					return html;
				}
			});
		}
	}

	/**
	 * ","拼接的Field
	 */
	function InitDataFields() {
		var result = "";
		var dg = this;
		var columns = dg.dataGridOptions.columns[0];
		for (var i = 0; i < columns.length; i++) {
			if (columns[i].field != 'datagrid-rowToolbar' && columns[i].field != '___ck') {
				result += columns[i].field + ","
			}
		}
		dg.options.dataFields = result;
		dg.dataGridOptions._dataFields = result;
	}

	/**
	 * 显示或隐藏修改，删除，保存，取消按钮
	 * 
	 * @param state editing or normal
	 */
	function setRowToolBarState(state) {
		this.isEditing = state == 'editing';
		if (this.currentRowIndex || this.currentRowIndex == 0) {
			$(this.options.rowToolbarId + '-browse-' + this.currentRowIndex).toggle(state != 'editing');
			$(this.options.rowToolbarId + '-save-' + this.currentRowIndex).toggle(state == 'editing');
		}
	}

	/**
	 * 行内添加记录
	 */
	function addInRow() {
		cancelInRow.call(this);
		var row = null;
		if (this.options.edatagrid.defaultRow) {
			row = this.options.edatagrid.defaultRow.call(this);
		}
		this.dataGridObject.edatagrid('addRow', {
			index : 0,
			row : row || {},
		});
	}

	/**
	 * 行内修改记录
	 * 
	 * @param rowIndex
	 */
	function editInRow(rowIndex) {
		cancelInRow.call(this);
		this.dataGridObject.edatagrid('editRow', this.currentRowIndex);
	}

	/**
	 * 取消行内添加或修改
	 */
	function cancelInRow() {
		this.dataGridObject.edatagrid('cancelRow');
		setRowToolBarState.call(this, 'normal');
	}

	/**
	 * 行内添加或修改的保存
	 */
	function savelInRow() {
		this.dataGridObject.edatagrid('saveRow');
	}

	/**
	 * 添加(单元格)
	 */
	function addInCell() {
		// cancelInCell.call(this);
		this.dataGridObject.datagrid('appendRow', {});
		var maxRowIndex = this.dataGridObject.datagrid('getRows').length - 1;
		this.dataGridObject.datagrid('editCell', {
			index : maxRowIndex,
			field : this.options.edatagrid.defaultEditField
		});
	}

	/**
	 * 删除(单元格)
	 */
	function delInCell(rowIndex) {
		var rowIndex = this.dataGridObject.datagrid('getSelected');
		if (rowIndex != null) {
			var rowIndex = this.dataGridObject.datagrid('getRowIndex', rowIndex);
			this.dataGridObject.datagrid('deleteRow', rowIndex);
		}
		return;
	}

	/**
	 * dialog方式添加记录
	 */
	function addInDialog() {
		openFormDialog.call(this, 'add', '');
	}

	/**
	 * dialog方式修改记录
	 * 
	 * @param id
	 */
	function editInDialog(id) {
		openFormDialog.call(this, 'edit', id);
	}

	/**
	 * 上下移动行
	 */
	function doMoveRow(action) {
		var dg = this;
		var selected = hasSelectedRow.call(dg);
		if (selected == null) {
			return;
		}

		var rowIndex = dg.dataGridObject.datagrid('getRowIndex', selected);
		var otherRowIndex;
		// 上移
		if ("up" == action) {
			if (rowIndex == 0) {
				return;
			}
			otherRowIndex = rowIndex - 1;
		} else if ("down" == action) { // 下移
			var rows = this.dataGridObject.datagrid('getRows').length;
			if (rowIndex == rows - 1) {
				return;
			}
			otherRowIndex = rowIndex + 1;
		}

		var rows = dg.dataGridObject.datagrid('getData').rows;
		var row = rows[rowIndex];
		var otherRow = rows[otherRowIndex];

		if (dg.options.url.sequenceUrl != '') {
			var ids = [];
			if (dg.options.swapSeq) {
				if (row.id) {
					ids.push(row.id);
				}
				if (otherRow.id) {
					ids.push(otherRow.id);
				}
			} else {
				// 调换位置
				rows[rowIndex] = otherRow;
				rows[otherRowIndex] = row;
				$.each(rows, function(i, n) {
					if (rows[eval(i)].id) {
						ids.push(rows[eval(i)].id);
					}
				});
			}

			if (ids.length > 0) {
				$.post(dg.options.url.sequenceUrl, 'swapSeq=' + dg.options.swapSeq + '&ids=' + ids.join(','), function(data) {
					if (!data.isError) {
						_doMoveRow();
					} else {
						base.alert(data.message);
					}
				});
			} else {
				_doMoveRow();
			}

		} else {
			_doMoveRow();
		}
		function _doMoveRow() {
			dg.dataGridObject.datagrid('getData').rows[otherRowIndex] = row;
			dg.dataGridObject.datagrid('getData').rows[rowIndex] = otherRow;

			dg.dataGridObject.datagrid('refreshRow', rowIndex);
			dg.dataGridObject.datagrid('refreshRow', otherRowIndex);
			dg.dataGridObject.datagrid('clearSelections');
			dg.dataGridObject.datagrid('selectRow', otherRowIndex);
		}
	}

	/**
	 * 上下移动行 treegrid
	 */
	function doMoveTreeGridRow(action) {
		var dg = this;
		if (!dg.options.url.sequenceUrl) {
			base.alert('未设置sequenceUrl');
			return;
		}
		var selectedNode = hasSelectedRow.call(this);
		if (selectedNode == null) {
			return;
		}
		var parent = dg.dataGridObject.treegrid('getParent', selectedNode.id);
		var siblings = [];
		if (parent == null) {
			siblings = dg.dataGridObject.treegrid('getRoots');
		} else {
			var children = dg.dataGridObject.treegrid('getChildren', parent.id);
			// 同级
			$.each(children, function(i) {
				if (children[i]._parentId == selectedNode._parentId) {
					siblings.push(children[i]);
				}
			});
		}

		var index, otherIndex;
		$.each(siblings, function(i) {
			if (siblings[i].id == selectedNode.id) {
				index = i;
				return false;
			}
		});
		if (action == 'up' && index == 0 || action == 'down' && index == siblings.length - 1) {
			return;
		}
		if (action == 'up') {
			otherIndex = index - 1;
		}
		if (action == 'down') {
			otherIndex = index + 1;
		}
		var otherSelectNode = siblings[otherIndex];
		siblings[otherIndex] = selectedNode;
		siblings[index] = otherSelectNode;

		var ids = [];
		if (dg.options.swapSeq) {
			ids.push(siblings[otherIndex].id);
			ids.push(siblings[index].id);
		} else {
			$.each(siblings, function(i) {
				ids.push(siblings[i].id);
			});
		}
		$.post(dg.options.url.sequenceUrl, 'swapSeq=' + dg.options.swapSeq + '&ids=' + ids.join(','), function(data) {
			if (!data.isError) {
				dg.search();
			} else {
				base.alert(data.message);
			}
		});
	}

	/**
	 * 是否有选择记录，有选择的行，返回选择的行，没有选择的行返回null
	 */
	function hasSelectedRow() {
		var row;
		if (this.isTreeGrid()) {
			row = this.dataGridObject.treegrid('getSelected');
		} else {
			row = this.dataGridObject.datagrid('getSelected');
		}
		if (row == null) {
			base.alert('请先选择一行记录！');
		}
		return row;
	}

	/**
	 * 打开 查看 添加 修改 对话框
	 * 
	 * @param action
	 * @param id
	 */
	function openFormDialog(action, id) {
		var dg = this;
		var url, dialogTitle, buttons = [], saveButton = null, saveAndNewButton = null;

		if (action == 'add') {
			url = dg.options.url.addUrl;
			dialogTitle = dg.options.formDialog.title + ' - 添加';
			var saveAndNewButton = {
				id : 'saveAndNewButton',
				text : '保存再添加',
				height : 35,
				width : 120,
				iconCls : 'icon-add',
				handler : function() {
					$(this).attr('saveAndNew', 'true');
					$(dg.options.formDialog.formId).submit();
				}
			};
			buttons.push(saveAndNewButton);

			saveButton = {
				handler : function() {
					$('#' + saveAndNewButton.id).removeAttr('saveAndNew');
					$(dg.options.formDialog.formId).submit();
				}
			};
		} else if (action == 'edit') {
			url = dg.options.url.editUrl + '/' + id;
			dialogTitle = dg.options.formDialog.title + ' - 修改';
		} else if (action == 'view') {
			url = dg.options.url.viewUrl + '/' + id;
			dialogTitle = dg.options.formDialog.title + ' - 查看';
			saveButton = false;
		}

		var dialogOptions = {
			id : dg.options.formDialog.dialogId.substring(1),
			title : dialogTitle,
			width : dg.options.formDialog.width == 'auto' ? 400 : dg.options.formDialog.width,
			height : dg.options.formDialog.height,
			href : url,
			buttons : buttons,
			onOpen : function() {
				if (dg.options.formDialog.onOpen) {
					dg.options.formDialog.onOpen($(this), action);
				}
			},
			onBeforeOpen : function() {
				$(this).dialog('dialog').find('input[name="action"]').val(action);
				if (action == 'view') {
					setViewFormDialog($(this));
				}
			}
		};

		if (action != 'view') {
			var formOptions = {
				id : dg.options.formDialog.formId.substring(1),
				url : dg.options.url.saveUrl,
				queryParams : {
					_dataFields : dg.options.dataFields
				},
				onSubmit : function() {
					return dg.options.formDialog.onSubmit($(this), action);
				},
				success : function(data) {
					data = $.parseJSON(data);
					if (data.success == 'yes') {
						if (action == 'add') {
							dg.insertRow(0, data.entity);
						} else if (action == 'edit') {
							dg.updateRow(dg.currentRowIndex, data.entity);
						}

						// 保存再添加
						if (saveAndNewButton) {
							if (!$('#' + saveAndNewButton.id).attr('saveAndNew')) {
								$(dg.options.formDialog.dialogId).dialog('close');
							} else {
								$('#' + saveAndNewButton.id).removeAttr('saveAndNew');
								$(dg.options.formDialog.formId).form('reset');
								$(dg.options.formDialog.formId).form('disableValidation');
								$('#' + dialogOptions.id).dialog('dialog').find('.dialog-button >a').linkbutton('enable');
							}
						} else {
							$(dg.options.formDialog.dialogId).dialog('close');
						}
					} else {
						base.alert(data.message);
						$('#' + dialogOptions.id).dialog('dialog').find('.dialog-button >a').linkbutton('enable');
					}
				}
			};
		}

		base.dialog(dialogOptions, formOptions, null, null, saveButton);
	}

	/**
	 * 设置为view模式
	 * 
	 * @param dialog
	 */
	function setViewFormDialog(dialog) {
		dialog.find('input:checkbox').prop("disabled", true);
		dialog.find('input:radio').prop("disabled", true);

		dialog.find(".easyui-textbox").textbox("readonly");

		dialog.find(".easyui-selecttextbox").selecttextbox("readonly");
		dialog.find(".easyui-numberbox").textbox("readonly");

		dialog.find(".easyui-combobox").combotree("readonly");
		dialog.find(".easyui-combotree").combotree("readonly");
		dialog.find(".easyui-combogrid").combogrid("readonly");
		dialog.find(".easyui-datebox").datebox("readonly");
		dialog.find(".easyui-datetimebox").datetimebox("readonly");
		dialog.find(".easyui-filebox").filebox("readonly");

		dialog.find("input").css('background', '#f5f5f5');
		dialog.find("textarea").css('background', '#f5f5f5');
	}
})(window);
