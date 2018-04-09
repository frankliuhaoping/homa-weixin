/**
 * 
 */
var height = 30;

$.fn.textbox.defaults.height = height;
$.fn.numberbox.defaults.height = height;
$.fn.combo.defaults.height = height;
$.fn.combobox.defaults.height = height;
$.fn.combotree.defaults.height = height;
$.fn.datebox.defaults.height = height;
$.fn.linkbutton.defaults.height = height;
$.fn.filebox.defaults.height = height;
$.fn.selecttextbox.defaults.height = height;

$.extend($.fn.datagrid.defaults.editors, {
	selecttextbox : {
		init : function(container, options) {
			var input = $('<input class="easyui-selecttextbox"/>').appendTo(container);
			return input.selecttextbox(options);
		},
		destroy : function(target) {
			$(target).selecttextbox('destroy');
		},
		getValue : function(target) {
			return $(target).selecttextbox('getText');
		},
		setValue : function(target, value) {
			$(target).selecttextbox('setText', value);
		},
		resize : function(target, width) {
			$(target).selecttextbox('resize', width);
		}
	}
});

/**
 * 扩展datagrid
 */
$.extend($.fn.datagrid.methods, {
	/**
	 * 添加toolbar按钮
	 * 
	 * $('#tt').datagrid("addToolbarButton",[{"text":"xxx"},"-",{"text":"xxxsss","iconCls":"icon-ok"}])
	 */
	addToolbarButton : function(jq, items) {
		return jq.each(function() {
			var panel = $(this).datagrid('getPanel');
			var buttonsDiv = panel.children('div.datagrid-toolbar').children('div.toolbar-buttons');
			if (!buttonsDiv.length) {
				return;
			}
			for (var i = 0; i < items.length; i++) {
				var button = items[i];
				if (button == '-') {
					$('<a class="datagrid-btn-separator" style="float:none"></a>').appendTo(buttonsDiv);
				} else {
					var b = $('<a href="javascript:void(0)"></a>').appendTo(buttonsDiv);
					b[0].onclick = eval(button.handler || function() {
					});
					b.linkbutton($.extend({}, button, {
						plain : true
					}));
				}
			}
		});
	},
	/**
	 * 删除toolbar按钮
	 * 
	 * $('#tt').datagrid("removeToolbarButton", "添加") //根据btn的text删除
	 * $('#tt').datagrid("removeToolbarButton", 0) //根据下标删除
	 */
	removeToolbarButton : function(jq, param) {
		return jq.each(function() {
			var panel = $(this).datagrid('getPanel');
			var buttonsDiv = panel.children('div.datagrid-toolbar').children('div.toolbar-buttons');
			var button = null;
			if (typeof param == 'number') {
				button = buttonsDiv.find('a').eq(param).find('span.l-btn-text');
			} else if (typeof param == 'string') {
				button = buttonsDiv.find('span.l-btn-text:contains("' + param + '")');
			}
			if (button && button.length > 0) {
				button.closest('a').remove();
				button = null;
			}
		});
	}
});