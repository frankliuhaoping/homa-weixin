var oldClickNodeId = null;

$(function() {

	var dataGridOptions = {
		pagination : false,

		// fitColumns : false,
		frozenColumns : [ [ {
			title : '年份',
			field : 'y',
			sortable : true,
			exportable : true,
		}, {
			title : '名称',
			field : 'dgname',
			sortable : true,
			exportable : true
		} ] ],
		columns : [ [ {
			"title" : "一月",
			"colspan" : 3
		}, {
			"title" : "二月",
			"colspan" : 3
		}, {
			"title" : "三月",
			"colspan" : 3
		}, {
			"title" : "四月",
			"colspan" : 3
		}, {
			"title" : "五月",
			"colspan" : 3
		}, {
			"title" : "六月",
			"colspan" : 3
		}, {
			"title" : "七月",
			"colspan" : 3
		}, {
			"title" : "八月",
			"colspan" : 3
		}, {
			"title" : "九月",
			"colspan" : 3
		}, {
			"title" : "十月",
			"colspan" : 3
		}, {
			"title" : "十一月",
			"colspan" : 3
		}, {
			"title" : "十二月",
			"colspan" : 3
		} ],

		[

		{
			field : 'month1',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number1',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je1',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month2',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number2',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je2',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month3',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number3',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je3',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month4',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number4',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je4',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month5',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number5',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je5',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month6',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number6',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je6',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month7',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number7',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je7',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month8',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number8',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je8',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month9',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number9',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je9',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month10',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number10',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je10',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month11',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number11',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je11',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'month12',
			title : '任务',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'number12',
			title : '数量',
			// width : 15,
			sortable : false,
			exportable : true,
		},

		{
			field : 'je12',
			title : '金额',
			// width : 15,
			sortable : false,
			exportable : true,

		}

		] ],
	};

	var options = {
		addCkColumn : false,
		addCkColumn : false, // 添加选择框列
		addIdColumn : false, // 添加ID列
		addVersionColumn : false, // 添加version列
		addRecordInfoColumns : false, // 记录信息列 createdBy.realName等
		url : {
			urlPrefix : contextPath + urlPrefix,
			sequenceUrl : contextPath + urlPrefix + 'sequence',
		}
	};

	crud = ryUILib.EasyuiDataGrid(options, dataGridOptions);

	var customTreeOptions = {
		onClick : function(node, nodeType) {
			$('#search_organization').val(node.id);
			$('#search_organization_nodeType').val(nodeType);
			crud.search();
		}
	}
	organizationTree = ryUILib.OrganizationTree(customTreeOptions, {});

	// 双击加载图表
	$("#datagrid-list").datagrid(
			{
				onDblClickRow : function(rowIndex, rowData) {
					$('#dialog').dialog(
							{
								title : '图表',
								width : '80%',
								height : '60%',
								onOpen : function() {
									var jh = [ rowData.month1, rowData.month2, rowData.month3, rowData.month4, rowData.month5, rowData.month6, rowData.month7, rowData.month8, rowData.month9,
											rowData.month10, rowData.month11, rowData.month12 ];
									var je = [ rowData.je1, rowData.je2, rowData.je3, rowData.je4, rowData.je5, rowData.je6, rowData.je7, rowData.je8, rowData.je9, rowData.je10, rowData.je11,
											rowData.je12 ];
									var sl = [ rowData.number1, rowData.number2, rowData.number3, rowData.number4, rowData.number5, rowData.number6, rowData.number7, rowData.number8, rowData.number9,
											rowData.number10, rowData.number11, rowData.number12 ];
									var myChart = echarts.init(document.getElementById('dialog'));
									var option = {
										title : {
											'text' : rowData.y + '年' + rowData.dgname + '的销售表',
										// 'subtext':'奥马公司'
										},
										toolbox : {
											'show' : true,
											'borderColor' : "#ccc",
											'borderWidth' : 1,
											'feature' : {
												'mark' : {
													'show' : true
												},
												'dataView' : {
													'show' : true,
													'readOnly' : false
												},
												'dataZoom' : {
													'show' : true
												},
												'magicType' : {
													'show' : true,
													'type' : [ 'line', 'bar', 'stack', 'tiled' ]
												},
												'restore' : {
													'show' : true,
													'title' : '还原'
												},
												'saveAsImage' : {
													'show' : true,
													'title' : '保存为图片',
													'type' : 'png',
													'lang' : [ '点击保存' ]
												}

											}

										},

										tooltip : {
											trigger : 'items',
											show : true
										},
										legend : {
											vertical : 'horizontal',
											data : [ '任务销售额', '实际销售额', '实际销售数量' ]
										},
										xAxis : [ {
											type : 'category',
											name : '月份',
											data : [ "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" ]
										} ],
										yAxis : [ {
											type : 'value',
											name : '销售金额/销售数量',
										// axisLabel : {
										// formatter: '{value} 万'
										// }
										} ],
										series : [ {
											"name" : "任务销售额",
											"type" : "line",
											"data" : jh,
											"itemStyle" : {
												'normal' : {
													'areaStyle' : {
														'type' : 'default'
													}
												}
											},

										}, {
											"name" : "实际销售额",
											"type" : "line",
											"data" : je,
											"itemStyle" : {
												'normal' : {
													'areaStyle' : {
														'type' : 'default'
													}
												}
											},
										}, {
											"name" : "实际销售数量",
											"type" : "line",
											"data" : sl,
											"itemStyle" : {
												'normal' : {
													'areaStyle' : {
														'type' : 'default'
													}
												}
											},
										}

										]
									};

									// 为echarts对象加载数据
									myChart.setOption(option);
								}

							});
				}

			});
});
