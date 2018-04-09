/**
 * 
 * 主要用于弹窗选择数据
 * 
 * <pre>
 * 从textbox继承，增加text选项，text:显示的值，value:保存的值
 * 
 * </pre>
 * 
 * <input class="easyui-selecttextbox" name="director.id"
 * id="organizationDirector" data-options="value: '${model.director.id}', text :
 * '${model.director.name}', buttonIcon: 'icon-search', width: '100%', required:
 * true" >
 * 
 * @param $
 */
(function($) {
	$.parser.plugins.push('selecttextbox');// 注册扩展组件

	$.fn.selecttextbox = function(options, param) {// 定义扩展组件
		if (typeof options == 'string') {
			var method = $.fn.selecttextbox.methods[options];
			if (method) {
				return method(this, param);
			} else {
				return this.textbox(options, param);
			}
		}

		options = options || {};
		// 当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
		return this.each(function() {
			var jq = $(this);
			var opts = $.extend({}, $.fn.selecttextbox.defaults, $.fn.selecttextbox.parseOptions(this), options);

			$.fn.textbox.call(jq, opts);

			if (opts.value != undefined) {
				$(this).selecttextbox("setText", opts.value);
				$(this).selecttextbox("setValue", opts.value);
			}
			if (opts.text != undefined) {
				$(this).selecttextbox("setText", opts.text);
			}
		});
	}

	$.fn.selecttextbox.methods = {
		clear : function(jq) {
			return jq.each(function() {
				$(this).selecttextbox("setValue", "");
				$(this).selecttextbox("setText", "");
			});
		},
		setText : function(jq, text) {
			return jq.each(function() {
				var opts = $(this).textbox("options");
				var target = $(this).textbox("textbox");
				text = text == undefined ? "" : String(text);
				if ($(this).selecttextbox("getText") != text) {
					target.val(text);
				}
				opts.value = text;
				if (!target.is(":focus")) {
					if (text) {
						target.removeClass("textbox-prompt");
					} else {
						target.val(opts.prompt).addClass("textbox-prompt");
					}
				}
				$(this).textbox("validate");
			});
		},
		initValue : function(jq, value) {
			return jq.each(function() {
				var state = $.data(this, "textbox");
				// state.options.value = value;
				// $(this).textbox("setText", value);
				state.textbox.find(".textbox-value").val(value);
				$(this).val(value);

				var icon = $(this).textbox('getIcon', 0);
				if (value) {
					icon.css('visibility', 'visible');
				} else {
					icon.css('visibility', 'hidden');
				}
			});
		},
		setValue : function(jq, value) {
			return jq.each(function() {
				var opts = $.data(this, "textbox").options;
				var oldValue = $(this).textbox("getValue");
				$(this).selecttextbox("initValue", value);
				if (oldValue != value) {
					opts.onChange.call(this, value, oldValue);
					$(this).closest("form").trigger("_change", [ this ]);
				}
			});
		},
		getText : function(jq) {
			var target = jq.textbox("textbox");
			if (target.is(":focus")) {
				return target.val();
			} else {
				return jq.textbox("options").value;
			}
		},
		getValue : function(jq) {
			return jq.data("textbox").textbox.find(".textbox-value").val();
		},
		readonly : function(jq, _41) {
			return jq.each(function() {
				$(this).textbox("readonly", _41);
				if (!_41) {
					$(this).textbox('button').remove();
					$(this).textbox('getIcon', 0).remove();
				}
			})
		},
		reset : function(jq) {
			return jq.each(function() {
				var opts = $(this).textbox("options");
				$(this).selecttextbox("setValue", opts.originalValue);
				$(this).selecttextbox("setText", opts.originalValue);
			});
		}
	}

	// $.fn.selecttextbox.parseOptions(this)作用是获取页面中的data-options中的配置
	$.fn.selecttextbox.parseOptions = function(target) {
		return $.extend({}, $.fn.textbox.parseOptions(target), {});
	};

	$.fn.selecttextbox.defaults = $.extend({}, $.fn.textbox.defaults, {
		editable : false,
		buttonIcon : 'icon-search',
		icons : [ {
			iconCls : 'icon-clear',
			handler : function(e) {
				$(e.data.target).textbox('clear');
				$(this).css('visibility', 'hidden');
			}
		} ],
		inputEvents : {},
	});

})(jQuery);