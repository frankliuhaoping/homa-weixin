/**
 * ajax全局事件，进度条，出错提示，登录失效弹出登录框
 */
$(document).ajaxStart(function() {
	// base.progress();
}).ajaxStop(function() {
	// base.progress('close');
}).ajaxError(function(XMLHttpRequest, textStatus, errorThrown) {
	if (textStatus.status == 401) {
		base.loginDialog(true);
		return;
	}

	base.alert('出现未知异常，操作失败！\n' + textStatus.responseText);
});

/**
 * 捕获执行JS中的错误信息，并打印
 */
window.onerror = function(_msg, _url, _line) {
	var error = '发现JS错误，报告如下：<br/>地址 : ' + _url + '<br/>' + '行数 : ' + _line + '<br/>错误 : ' + _msg;
	// base.alert(error);
	return true; // 在IE中可以避免在地址栏中出现错误提示
};

/**
 * 扩展jquery，form序列化json对象
 */
$.fn.form2json = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}

/**
 * 扩展日期对象，格式化
 */
Date.prototype.format = function(format) {
	if (!format) {
		format = 'yyyy-MM-dd';// 默认1997-01-01这样的格式
	}
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

/**
 * 扩展日期对象，添加月数
 */
Date.prototype.addMonths = function(m) {
	var d = this.getDate();
	this.setMonth(this.getMonth() + m);
	if (this.getDate() < d)
		this.setDate(0);
};

/**
 * 扩展日期对象，添加天数
 */
Date.prototype.addDays = function(d) {
	this.setDate(this.getDate() + d);
};

window.base = {
	/**
	 * 动态加载js
	 * 
	 * @param url
	 */
	loadJs : function(url) {
		$.ajax({
			type : 'GET',
			url : url,
			async : false,
			global : false,
			dataType : 'script'
		});
	},

	/**
	 * ajax下载文件
	 * 
	 * @param url
	 * @param queryParams a=1&b=2
	 */
	downloadFile : function(url, queryParams) {
		var $form = $('#download___Form');
		if ($form.length == 0) {
			$form = $('<form>');// 定义一个form表单
			$form.attr('id', 'download___Form');
			$form.attr('style', 'display:none');
			$form.attr('target', '');
			$form.attr('method', 'post');
			var $input = $('<input>');
			$input.attr('type', 'hidden');
			$input.attr('name', 'time');
			$form.append($input);
			if (queryParams) {
				var params = queryParams.split("&");
				for (var i = 0; i < params.length; i++) {
					var param = params[i].split("=");
					if (param[0] && param[0] != "" && param[1] && param[1] != "") {
						var $input = $('<input>');
						$input.attr('type', 'hidden');
						$input.attr('name', param[0]);
						$input.val(param[1]);
						$form.append($input);
					}
				}
			}
			$('body').append($form);// 将表单放置在web中
		}
		$form.attr('action', url);
		$form.find('input[name="time"]').attr('value', (new Date()).getMilliseconds());
		$form.submit();// 表单提交
		$form.remove();
	},

	/**
	 * info图标alert提示框
	 * 
	 * @param msg
	 * @param title
	 * @param callBack
	 */
	alert : function(msg, title, callBack) {
		title = title ? title : webAppTitle;
		$.messager.alert(title, msg, 'info', callBack);
	},

	/**
	 * info图标alert提示框
	 * 
	 * @param msg
	 * @param title
	 * @param callBack
	 */
	info : function(msg, title, callBack) {
		title = title ? title : webAppTitle;
		$.messager.alert(title, msg, 'info', callBack);
	},

	/**
	 * warn图标alert提示框
	 * 
	 * @param msg
	 * @param title
	 * @param callBack
	 */
	warn : function(msg, title, callBack) {
		title = title ? title : webAppTitle;
		$.messager.alert(title, msg, 'warning', callBack);
	},

	/**
	 * error图标alert提示框
	 * 
	 * @param msg
	 * @param title
	 * @param callBack
	 */
	error : function(msg, title, callBack) {
		title = title ? title : webAppTitle;
		$.messager.alert(title, msg, 'error', callBack);
	},

	/**
	 * 确认框
	 * 
	 * @param msg
	 * @param title
	 * @param yesCallBack yes回调函数
	 * @param noCallBack no回调函数
	 */
	confirm : function(msg, title, yesCallBack, noCallBack) {
		title = title ? title : webAppTitle;
		$.messager.confirm(title, msg, function(r) {
			if (r) {
				if (yesCallBack) {
					yesCallBack.call();
				}
			} else {
				if (noCallBack) {
					noCallBack.call();
				}
			}
		});
	},

	/**
	 * 进度条
	 * 
	 * @param close
	 */
	progress : function(close) {
		if (close == 'close') {
			$.messager.progress('close');
		} else {
			$.messager.progress({
				title : '请稍后',
				msg : '数据加载中...'
			});
		}
	},

	/**
	 * 显示一个dialog
	 * 
	 * @param dialogOptions
	 * @param formOptions 如dialog中有form，可设置form option
	 * @param top true在顶层窗口中显示
	 * @param closeButton 关闭按钮选项，如closeButton=false，不显示关闭按钮
	 * @param submitButton 提交按钮选项，如submitButton=false，不显示提交按钮
	 */
	dialog : function(dialogOptions, formOptions, top, closeButton, submitButton) {
		var jq = $;
		if (top) {
			jq = parent.$;
		}
		// dialog选项
		var dialogOpts = jq.extend({
			id : 'ryUILib_dialog' + new Date().format('yyyyMMddhhmmss'),
			width : 400,
			cache : false,
			modal : true,
			buttons : [],
			onClose : function() {
				jq(this).dialog('destroy');
			}
		}, dialogOptions);

		// form选项
		if (formOptions) {
			var formOpts = jq.extend({
				novalidate : true,
				ajax : true,
				// form提交后执行
				success : function(data) {
					data = jq.parseJSON(data);
					if (data.success == 'no') {
						base.alert(data.message);
					} else {
						jq('#' + dialogOpts.id).dialog('close');
					}
				}
			}, formOptions);

			var oldFormOptsOnSubmit = formOpts.onSubmit;
			// form提交前执行
			formOpts.onSubmit = function() {
				// 先禁用按钮
				var dialogButton = jq('#' + dialogOpts.id).dialog('dialog').find('.dialog-button >a');
				dialogButton.linkbutton('disable');
				result = jq(this).form('enableValidation').form('validate');
				if (result && oldFormOptsOnSubmit) {
					result = oldFormOptsOnSubmit.call(this);
				}
				if (!result) {
					// 验证不通过，启用按钮
					dialogButton.linkbutton('enable');
				} else {
					base.progress();
				}
				return result;
			};

			var oldFormOptsSuccess = formOpts.success;
			formOpts.success = function(data) {
				base.progress('close');
				// 判断返回值不是 json 格式
				if (!data.match("^\{(.+:.+,*){1,}\}$")) {
					jq('#' + dialogOpts.id).dialog('dialog').find('.dialog-button >a').linkbutton('enable');
					// 是登录页面
					if (data.match('loginForm')) {
						base.loginDialog(true);
						return;
					}
					base.alert(data);
					// 普通字符串处理
					return;
				}
				if (oldFormOptsSuccess) {
					oldFormOptsSuccess.call(this, data);
				}
			};

		}

		if (submitButton != null && submitButton != false || formOpts) {
			submitButton = jq.extend(true, {
				text : '保存',
				height : 35,
				width : 100,
				iconCls : 'icon-save',
				handler : function() {
					jq('#' + formOpts.id).submit();
				}
			}, submitButton);
			dialogOpts.buttons.push(submitButton);
		}

		var oldDialogOptsOnBeforeOpen = dialogOpts.onBeforeOpen;
		dialogOpts.onBeforeOpen = function() {
			// 初始化form
			if (formOpts) {
				jq('#' + formOpts.id).form(formOpts);
			}

			var windowHeight = jq(window).height();
			// 47 = 按钮高度
			var dialogHeight = jq(this).parent().outerHeight(true) + 47;
			var ratio = 0.4;
			if (windowHeight > 100 && dialogHeight > windowHeight) {
				dialogHeight = windowHeight - 20;
				ratio = 0.5;
			}
			jq(this).dialog('resize', {
				height : dialogHeight,
				top : (windowHeight - dialogHeight) * ratio,
			});
			if (oldDialogOptsOnBeforeOpen) {
				oldDialogOptsOnBeforeOpen.call(this);
			}
		};

		// 添加关闭按钮
		if (closeButton != false) {
			closeButton = jq.extend(true, {
				text : '关闭',
				height : 35,
				width : 100,
				iconCls : 'icon-no',
				handler : function() {
					jq('#' + dialogOpts.id).dialog('close');
				}
			}, closeButton);
			dialogOpts.buttons.push(closeButton);
		}

		if (dialogOpts.href) {
			var url = dialogOpts.href;
			delete dialogOpts.href;
			jq.post(url, dialogOpts.queryParams, function(data) {
				// 提取body中内容
				var content;
				var pattern = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
				var matches = pattern.exec(data);
				if (matches) {
					content = matches[1]; // only extract body content
				} else {
					content = data;
				}
				dialogOpts.content = content;
				jq('<div/>').dialog(dialogOpts);
			});
		} else {
			jq('<div/>').dialog(dialogOpts);
		}
	},

	/**
	 * 如果str中的index位置不是substr， 则在index位置插入substr
	 * 
	 * @param index
	 * @param substr
	 * @param str
	 * @returns
	 */
	insertIfMissing : function(index, substr, str) {
		if (substr && str) {
			if (index < 0) {
				index = 0;
			}
			if (index > str.length) {
				index = str.length - 1;
			}
			if (str.substring(index, substr.length - 1) != substr) {
				if (index == 0) {
					return substr + str;
				}
				if (index == str.length - 1) {
					return str + substr;
				}
				return str.substring(0, index + 1) + substr + str.substring(index + 1);
			}
		} else {
			return "";
		}
	},

	/**
	 * 初始化登录form
	 * 
	 * @param options
	 */
	initLoginForm : function(options) {
		var opts = {
			novalidate : true,
			ajax : true,
			url : contextPath + '/rbac/login/tologin',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				data = $.parseJSON(data);
				if (data.success == 'yes') {
					window.location.href = data.message;
				} else {
					base.alert(data.message);
				}
			}
		};
		opts = $.extend(true, opts, options);

		$('#loginForm').form(opts);
	},

	/**
	 * 显示登录对话框
	 * 
	 * @param top 是否显示在顶层窗体
	 */
	loginDialog : function(top) {
		if (parent) {
			var jq = parent.$;
			parentBase = parent.base;
		} else {
			jq = $;
			parentBase = base;
		}
		base.dialog({
			id : 'loginDialog',
			title : '登录',
			width : 400,
			queryParams : 'message=登录失效，请重新登录',
			href : contextPath + '/rbac/login/showDialog',
			onOpen : function() {
				jq('#username').textbox('textbox').focus();
			},
			onBeforeOpen : function() {
				parentBase.initLoginForm({
					success : function(data) {
						data = jq.parseJSON(data);
						if (data.success == 'no') {
							parentBase.alert(data.message);
							return;
						}
						jq('#loginDialog').dialog('close');
					}
				});
			}
		}, null, top === true, null, {
			text : '登录',
			iconCls : 'icon-ok',
			handler : function() {
				jq('#loginForm').submit();
			}
		});
	},

	/**
	 * 文件上传
	 * 
	 * <pre>
	 * 增加success, error回调函数
	 * 增加dir属性，指定上传后存放的目录
	 * 增加imageId，图片上传后预览的img标签的ID
	 * 增加hiddenId，图片上传后保存图片URL的隐藏域ID
	 * base.plupload({browse_button:'uploadbtn', dir:'kk', imageId:'aa', hiddenId:'bb'});
	 * </pre>
	 * 
	 * @param optionsdir参数
	 * 
	 * @returns {plupload.Uploader}
	 */
	plupload : function(options) {
		if (typeof plupload == 'undefined') {
			base.loadJs(contextPath + '/static/plupload_2.8/plupload.full.min.js');
			// base.loadJs(contextPath +
			// '/static/plupload_2.8/jquery.ui.plupload/jquery.ui.plupload.min.js');
		}
		// 参数参考文档：http://chaping.github.io/plupload/doc/
		var opts = {
			// 服务器端接收和处理上传文件的脚本地址，可以是相对路径(相对于当前调用Plupload的文档)，也可以是绝对路径
			url : contextPath + '/plupload',
			filters : {
				// 最大只能上传10m的文件
				max_file_size : '10240kb',
				// 不允许选取重复文件
				prevent_duplicates : true,
			},
			// 是否可以在文件浏览对话框中选择多个文件，true为可以，false为不可以
			multi_selection : true,
			unique_names : true,
			multipart_params : {},
		};
		$.extend(opts, options);
		if (opts.dir != "") {
			opts.multipart_params.dir = opts.dir;
			delete opts.dir;
		}
		var uploaders = new plupload.Uploader(opts);
		uploaders.init();
		if (opts.success || opts.imageId || opts.hiddenId) {
			uploaders.bind('FileUploaded', function(uploader, file, result) {
				var r = result.response;
				var args = [ r, uploader, file, result ];
				try {
					var data = $.parseJSON(r);
					if (data != null) {
						args[0] = data;
						if (opts.imageId) {
							$('#' + opts.imageId).attr('src', contextPath + data.message);
						}
						if (opts.hiddenId) {
							$('#' + opts.hiddenId).val(data.message);
						}
					}
				} catch (e) {
				}
				if (opts.success) {
					opts.success.apply(uploader, args);
				}
			});
		}
		if (opts.error) {
			uploaders.bind('Error', function(uploader, error) {
				opts.error(error.message);
			});
		} else {
			uploaders.bind('Error', function(uploader, error) {
				base.alert("上传失败，错误消息：" + error.message);
			});
		}
		if (!(opts.init && opts.init.FilesAdded)) {
			uploaders.bind('FilesAdded', function(uploader, files) {
				uploader.start();
			});
		}

		return uploaders;
	},
}
